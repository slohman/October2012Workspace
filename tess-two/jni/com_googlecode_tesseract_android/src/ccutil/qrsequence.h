///////////////////////////////////////////////////////////////////////
// File:        qrsequence.h
// Description: Quasi-random sequence generator class.
// Author:      Ranjith Unnikrishnan
// Created:     Wed May 20 2009
//
// Class to generate a (deterministic) quasi-random Van der Corput sequence that
// covers the interval [0,N) without repetition.
//
// The sequence is generated by reversing the base-2 representation of the
// sequence of natural numbers {0, 1,... M-1}, where M is 2^{num_bits_} and
// num_bits is the minimum number of bits required to represent N. If a reversed
// numbers is >= N it is rejected and the next natural number is considered
// until a valid output number is found.
//
// (C) Copyright 2009, Google Inc.
// Licensed under the Apache License, Version 2.0 (the "License"); you may not
// use this file except in compliance with the License.  You may obtain a copy
// of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required
// by applicable law or agreed to in writing, software distributed under the
// License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
// OF ANY KIND, either express or implied.  See the License for the specific
// language governing permissions and limitations under the License.
//
///////////////////////////////////////////////////////////////////////

#ifndef TESSERACT_CCUTIL_QRSEQUENCE_H_
#define TESSERACT_CCUTIL_QRSEQUENCE_H_

#include <math.h>

class QRSequenceGenerator {
 public:
  // Object is initalized with the size of the output range.
  explicit QRSequenceGenerator(int N) : N_(N), next_num_(0) {
    num_bits_ = static_cast<int>(ceil(log(static_cast<double>(N)) / log(2.0)));
  }

  // Main worker method that retrieves the next number in the sequence.
  // Returns kInvalidVal if called more than N times after object initialization
  int GetVal() {
    const int kInvalidVal = -1;
    const int kMaxNaturalNumberValue = 1 << num_bits_;
    if (next_num_ >= kMaxNaturalNumberValue)
      return kInvalidVal;
    int n = next_num_;

    while (next_num_ < kMaxNaturalNumberValue) {
      n = GetBinaryReversedInteger(next_num_++);
      if (n < N_) break;
    }
    return (next_num_ > kMaxNaturalNumberValue) ? kInvalidVal : n;
  }

 protected:
  // Outputs the integer formed by reversing the bits of the input integer. Only
  // the lowest num_bits_ bits of the input integer are reversed.
  int GetBinaryReversedInteger(int in_val) const {
    int bit_pos = num_bits_;
    int out_val = 0;
    while(bit_pos--) {
      // Set the value of the last bit.
      out_val |= (in_val & 0x1);
      if (bit_pos > 0) {
        // Left-shift output value to prepare for storing the next bit.
        out_val <<= 1;
      }
      // Right-shift input value to prepare for retrieving the next bit.
      in_val >>= 1;
    }
    return out_val;
  }
  int N_;
  // Next number to be considered for reversal and output.
  int next_num_;
  // number of bits required to represent the numbers of the sequence
  int num_bits_;
};

#endif  // TESSERACT_CCUTIL_QRSEQUENCE_H_
