///////////////////////////////////////////////////////////////////////
// File:        fontinfo.cpp
// Description: Font information classes abstracted from intproto.h/cpp.
// Author:      rays@google.com (Ray Smith)
// Created:     Wed May 18 10:39:01 PDT 2011
//
// (C) Copyright 2011, Google Inc.
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
///////////////////////////////////////////////////////////////////////

#include "fontinfo.h"

namespace tesseract {

// Compare FontInfo structures.
bool CompareFontInfo(const FontInfo& fi1, const FontInfo& fi2) {
  // The font properties are required to be the same for two font with the same
  // name, so there is no need to test them.
  // Consequently, querying the table with only its font name as information is
  // enough to retrieve its properties.
  return strcmp(fi1.name, fi2.name) == 0;
}
// Compare FontSet structures.
bool CompareFontSet(const FontSet& fs1, const FontSet& fs2) {
  if (fs1.size != fs2.size)
    return false;
  for (int i = 0; i < fs1.size; ++i) {
    if (fs1.configs[i] != fs2.configs[i])
      return false;
  }
  return true;
}

// Callbacks for GenericVector.
void FontInfoDeleteCallback(FontInfo f) {
  if (f.spacing_vec != NULL) {
    f.spacing_vec->delete_data_pointers();
    delete f.spacing_vec;
  }
  delete[] f.name;
}
void FontSetDeleteCallback(FontSet fs) {
  delete[] fs.configs;
}

/*---------------------------------------------------------------------------*/
// Callbacks used by UnicityTable to read/write FontInfo/FontSet structures.
bool read_info(FILE* f, FontInfo* fi, bool swap) {
  inT32 size;
  if (fread(&size, sizeof(size), 1, f) != 1) return false;
  if (swap)
    Reverse32(&size);
  char* font_name = new char[size + 1];
  fi->name = font_name;
  if (fread(font_name, sizeof(*font_name), size, f) != size) return false;
  font_name[size] = '\0';
  if (fread(&fi->properties, sizeof(fi->properties), 1, f) != 1) return false;
  if (swap)
    Reverse32(&fi->properties);
  return true;
}

bool write_info(FILE* f, const FontInfo& fi) {
  inT32 size = strlen(fi.name);
  if (fwrite(&size, sizeof(size), 1, f) != 1) return false;
  if (fwrite(fi.name, sizeof(*fi.name), size, f) != size) return false;
  if (fwrite(&fi.properties, sizeof(fi.properties), 1, f) != 1) return false;
  return true;
}

bool read_spacing_info(FILE *f, FontInfo* fi, bool swap) {
  inT32 vec_size, kern_size;
  if (fread(&vec_size, sizeof(vec_size), 1, f) != 1) return false;
  if (swap) Reverse32(&vec_size);
  ASSERT_HOST(vec_size >= 0);
  if (vec_size == 0) return true;
  fi->init_spacing(vec_size);
  for (int i = 0; i < vec_size; ++i) {
    FontSpacingInfo *fs = new FontSpacingInfo();
    if (fread(&fs->x_gap_before, sizeof(fs->x_gap_before), 1, f) != 1 ||
        fread(&fs->x_gap_after, sizeof(fs->x_gap_after), 1, f) != 1 ||
        fread(&kern_size, sizeof(kern_size), 1, f) != 1) {
      return false;
    }
    if (swap) {
      ReverseN(&(fs->x_gap_before), sizeof(fs->x_gap_before));
      ReverseN(&(fs->x_gap_after), sizeof(fs->x_gap_after));
      Reverse32(&kern_size);
    }
    if (kern_size < 0) {  // indication of a NULL entry in fi->spacing_vec
      delete fs;
      continue;
    }
    if (kern_size > 0 && (!fs->kerned_unichar_ids.DeSerialize(swap, f) ||
                          !fs->kerned_x_gaps.DeSerialize(swap, f))) {
      return false;
    }
    fi->add_spacing(i, fs);
  }
  return true;
}

bool write_spacing_info(FILE* f, const FontInfo& fi) {
  inT32 vec_size = (fi.spacing_vec == NULL) ? 0 : fi.spacing_vec->size();
  if (fwrite(&vec_size,  sizeof(vec_size), 1, f) != 1) return false;
  inT16 x_gap_invalid = -1;
  for (int i = 0; i < vec_size; ++i) {
    FontSpacingInfo *fs = fi.spacing_vec->get(i);
    inT32 kern_size = (fs == NULL) ? -1 : fs->kerned_x_gaps.size();
    if (fs == NULL) {
      if (fwrite(&(x_gap_invalid), sizeof(x_gap_invalid), 1, f) != 1 ||
          fwrite(&(x_gap_invalid), sizeof(x_gap_invalid), 1, f) != 1 ||
          fwrite(&kern_size, sizeof(kern_size), 1, f) != 1) {
        return false;
      }
    } else {
      if (fwrite(&(fs->x_gap_before), sizeof(fs->x_gap_before), 1, f) != 1 ||
          fwrite(&(fs->x_gap_after), sizeof(fs->x_gap_after), 1, f) != 1 ||
          fwrite(&kern_size, sizeof(kern_size), 1, f) != 1) {
        return false;
      }
    }
    if (kern_size > 0 && (!fs->kerned_unichar_ids.Serialize(f) ||
                          !fs->kerned_x_gaps.Serialize(f))) {
      return false;
    }
  }
  return true;
}

bool read_set(FILE* f, FontSet* fs, bool swap) {
  if (fread(&fs->size, sizeof(fs->size), 1, f) != 1) return false;
  if (swap)
    Reverse32(&fs->size);
  fs->configs = new int[fs->size];
  for (int i = 0; i < fs->size; ++i) {
    if (fread(&fs->configs[i], sizeof(fs->configs[i]), 1, f) != 1) return false;
    if (swap)
      Reverse32(&fs->configs[i]);
  }
  return true;
}

bool write_set(FILE* f, const FontSet& fs) {
  if (fwrite(&fs.size, sizeof(fs.size), 1, f) != 1) return false;
  for (int i = 0; i < fs.size; ++i) {
    if (fwrite(&fs.configs[i], sizeof(fs.configs[i]), 1, f) != 1) return false;
  }
  return true;
}

}  // namespace tesseract.

