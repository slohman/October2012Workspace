AM_CPPFLAGS += \
    -DUSE_STD_NAMESPACE \
    -I$(top_srcdir)/ccmain -I$(top_srcdir)/api \
    -I$(top_srcdir)/ccutil -I$(top_srcdir)/ccstruct \
    -I$(top_srcdir)/image -I$(top_srcdir)/viewer \
    -I$(top_srcdir)/textord -I$(top_srcdir)/dict \
    -I$(top_srcdir)/classify -I$(top_srcdir)/display \
    -I$(top_srcdir)/wordrec -I$(top_srcdir)/cutil

# TODO: training programs can not be linked to shared library created 
# with -fvisibility 
if VISIBILITY
AM_LDFLAGS += -all-static
endif

noinst_HEADERS = \
    commontraining.h tessopt.h mergenf.h

noinst_LTLIBRARIES = libtesseract_training.la libtesseract_tessopt.la

libtesseract_training_la_SOURCES = \
    commontraining.cpp

libtesseract_tessopt_la_SOURCES = \
    tessopt.cpp

bin_PROGRAMS = ambiguous_words classifier_tester cntraining combine_tessdata dawg2wordlist mftraining shapeclustering unicharset_extractor wordlist2dawg

ambiguous_words_SOURCES = ambiguous_words.cpp
ambiguous_words_LDADD = \
    libtesseract_training.la \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
ambiguous_words_LDADD += \
    ../api/libtesseract_api.la \
    ../textord/libtesseract_textord.la \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../ccutil/libtesseract_ccutil.la
else
ambiguous_words_LDADD += \
    ../api/libtesseract.la
endif

classifier_tester_SOURCES = classifier_tester.cpp
#classifier_tester_LDFLAGS = -static
classifier_tester_LDADD = \
    libtesseract_training.la \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
classifier_tester_LDADD += \
    ../api/libtesseract_api.la \
    ../textord/libtesseract_textord.la \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../ccutil/libtesseract_ccutil.la
else
classifier_tester_LDADD += \
    ../api/libtesseract.la
endif

combine_tessdata_SOURCES = combine_tessdata.cpp
#combine_tessdata_LDFLAGS = -static
if USING_MULTIPLELIBS
combine_tessdata_LDADD = \
    ../ccutil/libtesseract_ccutil.la
else
combine_tessdata_LDADD = \
    ../api/libtesseract.la
endif

cntraining_SOURCES = cntraining.cpp
#cntraining_LDFLAGS = -static
cntraining_LDADD = \
    libtesseract_training.la \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
cntraining_LDADD += \
    ../textord/libtesseract_textord.la \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../ccutil/libtesseract_ccutil.la
else
cntraining_LDADD += \
    ../api/libtesseract.la
endif

dawg2wordlist_SOURCES = dawg2wordlist.cpp
#dawg2wordlist_LDFLAGS = -static
dawg2wordlist_LDADD = \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
dawg2wordlist_LDADD += \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../textord/libtesseract_textord.la \
    ../ccutil/libtesseract_ccutil.la
else
dawg2wordlist_LDADD += \
    ../api/libtesseract.la
endif

mftraining_SOURCES = mftraining.cpp mergenf.cpp
#mftraining_LDFLAGS = -static
mftraining_LDADD = \
    libtesseract_training.la \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
mftraining_LDADD += \
    ../textord/libtesseract_textord.la \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../ccutil/libtesseract_ccutil.la
else
mftraining_LDADD += \
    ../api/libtesseract.la
endif

shapeclustering_SOURCES = shapeclustering.cpp
#shapeclustering_LDFLAGS = -static
shapeclustering_LDADD = \
    libtesseract_training.la \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
shapeclustering_LDADD += \
    ../textord/libtesseract_textord.la \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../ccutil/libtesseract_ccutil.la
else
shapeclustering_LDADD += \
    ../api/libtesseract.la
endif

unicharset_extractor_SOURCES = unicharset_extractor.cpp
#unicharset_extractor_LDFLAGS = -static
unicharset_extractor_LDADD = \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
unicharset_extractor_LDADD += \
    ../ccutil/libtesseract_ccutil.la \
    ../ccstruct/libtesseract_ccstruct.la
else
unicharset_extractor_LDADD += \
    ../api/libtesseract.la
endif

wordlist2dawg_SOURCES = wordlist2dawg.cpp
#wordlist2dawg_LDFLAGS = -static
wordlist2dawg_LDADD = \
    libtesseract_tessopt.la
if USING_MULTIPLELIBS
wordlist2dawg_LDADD += \
    ../classify/libtesseract_classify.la \
    ../dict/libtesseract_dict.la \
    ../ccstruct/libtesseract_ccstruct.la \
    ../image/libtesseract_image.la \
    ../cutil/libtesseract_cutil.la \
    ../viewer/libtesseract_viewer.la \
    ../ccmain/libtesseract_main.la \
    ../cube/libtesseract_cube.la \
    ../neural_networks/runtime/libtesseract_neural.la \
    ../wordrec/libtesseract_wordrec.la \
    ../textord/libtesseract_textord.la \
    ../ccutil/libtesseract_ccutil.la
else
wordlist2dawg_LDADD += \
    ../api/libtesseract.la
endif

if MINGW
ambiguous_words_LDADD += -lws2_32
classifier_tester_LDADD += -lws2_32
cntraining_LDADD += -lws2_32
combine_tessdata_LDADD += -lws2_32
dawg2wordlist_LDADD += -lws2_32
mftraining_LDADD += -lws2_32
shapeclustering_LDADD += -lws2_32
unicharset_extractor_LDADD += -lws2_32
wordlist2dawg_LDADD += -lws2_32
endif