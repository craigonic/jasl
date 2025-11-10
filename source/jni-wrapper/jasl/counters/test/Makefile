################################################################################
# Makefile - This make parameter file is used to build and run the unit tests  #
#            for the elements of the JNI counters package of the jASL program. #
#            It also generates the documentation.                              #
#                                                                              #
# Written By: Craig R. Campbell  -  May 2025                                   #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : test

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the public header files in/of/for this package.

CPP_FILES := EnumTest.cpp

EXE_FILES := ${CPP_FILES:.cpp=}

%: %.cpp
	$(JNI_BUILD_CMD) -I$(JNI_HDR_PATH) -I$(GOOGLE_TEST_INCLUDE_PATH) \
	                 -o $(JNI_UNIT_TEST_SUB_DIRECTORY)/$@ $^ \
	                 $(JNI_COUNTERS_STATIC_LIB_PATH) \
	                 $(JNI_WRAPPER_STATIC_LIB_PATH) \
	                 $(GOOGLE_TEST_LIB_GTEST) \
	                 $(GOOGLE_TEST_LIB_GTEST_MAIN) \
	                 $(JNI_LINK_OPTIONS) -lpthread -lm

.PHONY : test
test: bin_sub_directory $(EXE_FILES)
	@(/bin/sh -c "$(JNI_LD_LIB_PATH) ./.bin/$(EXE_FILES)")

# Remove all of the unit test executable files from the .bin sub-directory.

.PHONY : clean
clean:
	$(RM_RECURSIVE) $(JNI_UNIT_TEST_SUB_DIRECTORY)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

JNI_COUNTERS_TEST_DOCS_PATH := $(SRC_DOCS_PATH)/$(JNI_DIRECTORY)/$(COUNTERS_PKG_PATH)/test

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JNI_COUNTERS_TEST_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JNI_COUNTERS_TEST_DOCS_PATH)/$(MAKEFILE).html

	@for i in $(CPP_FILES) ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(JNI_COUNTERS_TEST_DOCS_PATH)/$$i.html ;\
	done

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JNI_COUNTERS_TEST_DOCS_PATH)
	@$(HTLS) $(JNI_COUNTERS_TEST_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(JNI_COUNTERS_TEST_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
