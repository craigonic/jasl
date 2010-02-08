################################################################################
# Makefile - This make parameter file is used to build wrapper libraries using #
#            SWIG. These libraries interact with the jASL libraries, allowing  #
#            the public classes to be used in scripting languages.             #
#                                                                              #
# Written By : Craig R. Campbell  -  July 2008                                 #
#                                                                              #
# $Id: Makefile,v 1.10 2010/02/08 21:08:29 campbell Exp $
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : libs

## System-wide build variables.

include $(JASL_BASE)/util/jasl.make

## Build variables used in generating all of the wrapper libraries.

# SWIG command and target language specific options.

SWIG_CMD := swig -c++
SWIG_PERL_CMD := $(SWIG_CMD) -perl -outdir $(PERL_BIN_PATH)
SWIG_PYTHON_CMD := $(SWIG_CMD) -python -shadow -outdir $(PYTHON_BIN_PATH)

# GCC compile commands with options specific to each target language.

GCC_PERL_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/lib/perl5/5.10.0/i686-linux/CORE
GCC_PYTHON_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/include/python2.6

# GCC build/link options common to all libraries and target languages.

GCC_COMMON_BUILD_OPTIONS := -lgcj -lstdc++ -L$(LIB_PATH)

## Targets to create the output directories for the Perl and Python libraries.
## The targets for the language module files, which are stored in
## sub-directories of bin, are defined in jasl.make

.PHONY : perl_lib_directory
perl_lib_directory:
	$(INSTALL_DIR) $(PERL_LIB_PATH)

.PHONY : python_lib_directory
python_lib_directory:
	$(INSTALL_DIR) $(PYTHON_LIB_PATH)

## CniWrapper ##################################################################

# The library/module name
CNI_WRAPPER_LIBRARY_NAME := CniWrapper
# Input file for SWIG.
CNI_WRAPPER_SWIG_SRC_FILE := $(CNI_WRAPPER_LIBRARY_NAME).swig
# CNI_WRAPPER class header file.
CNI_WRAPPER_HDR := cniWrapper.h
# swig command output file.
CNI_SWIG_WRAPPER_SRC := $(CNI_WRAPPER_LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
CNI_SWIG_WRAPPER_OBJ := ${CNI_SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
CNI_SWIG_WRAPPER_LIB := $(CNI_WRAPPER_LIBRARY_NAME).so
# Perl module file.
CNI_SWIG_WRAPPER_PM := ${CNI_SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
CNI_SWIG_WRAPPER_PY := ${CNI_SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
CNI_SWIG_WRAPPER_PYC := ${CNI_SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

.PHONY : cni_perl_src
cni_perl_src: $(CNI_WRAPPER_SWIG_SRC_FILE) perl_bin_directory
	$(SWIG_PERL_CMD) $(CNI_WRAPPER_SWIG_SRC_FILE)

.PHONY : cni_python_src
cni_python_src: $(CNI_WRAPPER_SWIG_SRC_FILE) python_bin_directory
	$(SWIG_PYTHON_CMD) $(CNI_WRAPPER_SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

CNI_COMMON_COMPILE_OPTIONS := -I$(INCLUDE_PATH) -include $(CNI_WRAPPER_HDR) \
                              $(CNI_SWIG_WRAPPER_SRC)

.PHONY : cni_perl_obj
cni_perl_obj: cni_perl_src
	$(GCC_PERL_COMPILE_CMD) $(CNI_COMMON_COMPILE_OPTIONS)

.PHONY : cni_python_obj
cni_python_obj: cni_python_src
	$(GCC_PYTHON_COMPILE_CMD) $(CNI_COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

CNI_COMMON_BUILD_OPTIONS := $(GCC_COMMON_BUILD_OPTIONS) \
                            -l$(CNI_WRAPPER_BASE_LIB_NAME) \
                            $(CNI_SWIG_WRAPPER_OBJ)

.PHONY : cni_perl_lib
cni_perl_lib: cni_perl_obj perl_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(CNI_SWIG_WRAPPER_LIB) \
	                     $(CNI_COMMON_BUILD_OPTIONS)

.PHONY : cni_python_lib
cni_python_lib: cni_python_obj python_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(CNI_SWIG_WRAPPER_LIB) \
	                     $(CNI_COMMON_BUILD_OPTIONS)

.PHONY : cni_libs
cni_libs: cni_perl_lib cni_python_lib

# Remove the wrapper source and object files (works for all target languages).

.PHONY : clean_cni_src
clean_cni_src:
	$(RM) $(CNI_SWIG_WRAPPER_SRC)

.PHONY : clean_cni_obj
clean_cni_obj:
	$(RM) $(CNI_SWIG_WRAPPER_OBJ)

.PHONY : clean_cni
clean_cni: clean_cni_src clean_cni_obj

# Remove the files generated for use with Perl.

.PHONY : clean_cni_perl_module
clean_cni_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(CNI_SWIG_WRAPPER_PM)

.PHONY : clean_cni_perl_lib
clean_cni_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(CNI_SWIG_WRAPPER_LIB)

.PHONY : clean_cni_perl
clean_cni_perl: clean_cni_perl_module clean_cni_perl_lib

# Remove the files generated for use with Python.

.PHONY : clean_cni_python_module
clean_cni_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(CNI_SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(CNI_SWIG_WRAPPER_PYC)

.PHONY : clean_cni_python_lib
clean_cni_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(CNI_SWIG_WRAPPER_LIB)

.PHONY : clean_cni_python
clean_cni_python: clean_cni_python_module clean_cni_python_lib

# Remove installed libraries and module files for all of the target languages.

.PHONY : clean_cni_libs
clean_cni_libs : clean_cni_perl clean_cni_python

## Counters ###################################################################

# The library/module name
COUNTERS_LIBRARY_NAME := Counters
# Input file for SWIG.
COUNTERS_SWIG_SRC_FILE := $(COUNTERS_LIBRARY_NAME).swig
# jasl-counters library header files.
COUNTERS_HDR_FILES := -include jaslWrapper.h
# swig command output file.
COUNTERS_SWIG_WRAPPER_SRC := $(COUNTERS_LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
COUNTERS_SWIG_WRAPPER_OBJ := ${COUNTERS_SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
COUNTERS_SWIG_WRAPPER_LIB := $(COUNTERS_LIBRARY_NAME).so
# Perl module file.
COUNTERS_SWIG_WRAPPER_PM := ${COUNTERS_SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
COUNTERS_SWIG_WRAPPER_PY := ${COUNTERS_SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
COUNTERS_SWIG_WRAPPER_PYC := ${COUNTERS_SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

.PHONY : counters_perl_src
counters_perl_src: $(COUNTERS_SWIG_SRC_FILE) perl_bin_directory
	$(SWIG_PERL_CMD) $(COUNTERS_SWIG_SRC_FILE)

.PHONY : counters_python_src
counters_python_src: $(COUNTERS_SWIG_SRC_FILE) python_bin_directory
	$(SWIG_PYTHON_CMD) $(COUNTERS_SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

COUNTERS_COMMON_COMPILE_OPTIONS := -I$(INCLUDE_PATH) $(COUNTERS_HDR_FILES) \
                                   $(COUNTERS_SWIG_WRAPPER_SRC)

.PHONY : counters_perl_obj
counters_perl_obj: counters_perl_src
	$(GCC_PERL_COMPILE_CMD) $(COUNTERS_COMMON_COMPILE_OPTIONS)

.PHONY : counters_python_obj
counters_python_obj: counters_python_src
	$(GCC_PYTHON_COMPILE_CMD) $(COUNTERS_COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

COUNTERS_COMMON_BUILD_OPTIONS := $(GCC_COMMON_BUILD_OPTIONS) \
                                 -l$(COUNTERS_BASE_LIB_NAME) \
                                 -l$(UTILITIES_BASE_LIB_NAME) \
                                 $(COUNTERS_SWIG_WRAPPER_OBJ)

.PHONY : counters_perl_lib
counters_perl_lib: counters_perl_obj perl_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(COUNTERS_SWIG_WRAPPER_LIB) \
	                     $(COUNTERS_COMMON_BUILD_OPTIONS)

.PHONY : counters_python_lib
counters_python_lib: counters_python_obj python_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(COUNTERS_SWIG_WRAPPER_LIB) \
	                     $(COUNTERS_COMMON_BUILD_OPTIONS)

.PHONY : counters_libs
counters_libs: counters_perl_lib counters_python_lib

# Remove the wrapper source and object files (works for all target languages).

.PHONY : clean_counters_src
clean_counters_src:
	$(RM) $(COUNTERS_SWIG_WRAPPER_SRC)

.PHONY : clean_counters_obj
clean_counters_obj:
	$(RM) $(COUNTERS_SWIG_WRAPPER_OBJ)

.PHONY : clean_counters
clean_counters: clean_counters_src clean_counters_obj

# Remove the files generated for use with Perl.

.PHONY : clean_counters_perl_module
clean_counters_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PM)

.PHONY : clean_counters_perl_lib
clean_counters_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(COUNTERS_SWIG_WRAPPER_LIB)

.PHONY : clean_counters_perl
clean_counters_perl: clean_counters_perl_module clean_counters_perl_lib

# Remove the files generated for use with Python.

.PHONY : clean_counters_python_module
clean_counters_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PYC)

.PHONY : clean_counters_python_lib
clean_counters_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(COUNTERS_SWIG_WRAPPER_LIB)

.PHONY : clean_counters_python
clean_counters_python: clean_counters_python_module clean_counters_python_lib

# Remove installed libraries and module files for all of the target languages.

.PHONY : clean_counters_libs
clean_counters_libs : clean_counters_perl clean_counters_python

## Utilities ###################################################################

# The library/module name
UTILITIES_LIBRARY_NAME := Utilities
# Input file for SWIG.
UTILITIES_SWIG_SRC_FILE := $(UTILITIES_LIBRARY_NAME).swig
# jasl-utilities library header files.
UTILITIES_HDR_FILES := -include jaslWrapper.h
# swig command output file.
UTILITIES_SWIG_WRAPPER_SRC := $(UTILITIES_LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
UTILITIES_SWIG_WRAPPER_OBJ := ${UTILITIES_SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
UTILITIES_SWIG_WRAPPER_LIB := $(UTILITIES_LIBRARY_NAME).so
# Perl module file.
UTILITIES_SWIG_WRAPPER_PM := ${UTILITIES_SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
UTILITIES_SWIG_WRAPPER_PY := ${UTILITIES_SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
UTILITIES_SWIG_WRAPPER_PYC := ${UTILITIES_SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

.PHONY : utilities_perl_src
utilities_perl_src: $(UTILITIES_SWIG_SRC_FILE) perl_bin_directory
	$(SWIG_PERL_CMD) $(UTILITIES_SWIG_SRC_FILE)

.PHONY : utilities_python_src
utilities_python_src: $(UTILITIES_SWIG_SRC_FILE) python_bin_directory
	$(SWIG_PYTHON_CMD) $(UTILITIES_SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

UTILITIES_COMMON_COMPILE_OPTIONS := -I$(INCLUDE_PATH) $(UTILITIES_HDR_FILES) \
                                    $(UTILITIES_SWIG_WRAPPER_SRC)

.PHONY : utilities_perl_obj
utilities_perl_obj: utilities_perl_src
	$(GCC_PERL_COMPILE_CMD) $(UTILITIES_COMMON_COMPILE_OPTIONS)

.PHONY : utilities_python_obj
utilities_python_obj: utilities_python_src
	$(GCC_PYTHON_COMPILE_CMD) $(UTILITIES_COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

UTILITIES_COMMON_BUILD_OPTIONS := $(GCC_COMMON_BUILD_OPTIONS) \
                                  -l$(UTILITIES_BASE_LIB_NAME) \
                                  $(UTILITIES_SWIG_WRAPPER_OBJ)

.PHONY : utilities_perl_lib
utilities_perl_lib: utilities_perl_obj perl_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(UTILITIES_SWIG_WRAPPER_LIB) \
	                     $(UTILITIES_COMMON_BUILD_OPTIONS)

.PHONY : utilities_python_lib
utilities_python_lib: utilities_python_obj python_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(UTILITIES_SWIG_WRAPPER_LIB) \
	                     $(UTILITIES_COMMON_BUILD_OPTIONS)

.PHONY : utilities_libs
utilities_libs: utilities_perl_lib utilities_python_lib

# Remove the wrapper source and object files (works for all target languages).

.PHONY : clean_utilities_src
clean_utilities_src:
	$(RM) $(UTILITIES_SWIG_WRAPPER_SRC)

.PHONY : clean_utilities_obj
clean_utilities_obj:
	$(RM) $(UTILITIES_SWIG_WRAPPER_OBJ)

.PHONY : clean_utilities
clean_utilities: clean_utilities_src clean_utilities_obj

# Remove the files generated for use with Perl.

.PHONY : clean_utilities_perl_module
clean_utilities_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(UTILITIES_SWIG_WRAPPER_PM)

.PHONY : clean_utilities_perl_lib
clean_utilities_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(UTILITIES_SWIG_WRAPPER_LIB)

.PHONY : clean_utilities_perl
clean_utilities_perl: clean_utilities_perl_module clean_utilities_perl_lib

# Remove the files generated for use with Python.

.PHONY : clean_utilities_python_module
clean_utilities_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(UTILITIES_SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(UTILITIES_SWIG_WRAPPER_PYC)

.PHONY : clean_utilities_python_lib
clean_utilities_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(UTILITIES_SWIG_WRAPPER_LIB)

.PHONY : clean_utilities_python
clean_utilities_python: clean_utilities_python_module clean_utilities_python_lib

# Remove installed libraries and module files for all of the target languages.

.PHONY : clean_utilities_libs
clean_utilities_libs : clean_utilities_perl clean_utilities_python

## General/Combination targets ################################################

# Build all of the wrapper libraries and install them, along with their
# respective module files, in the appropriate output directories.

.PHONY : libs
libs: cni_libs counters_libs utilities_libs clean

# Remove the generated files from this directory.

.PHONY : clean
clean: clean_cni clean_counters clean_utilities

# Remove all of the library and module files from their respective output
# directories.

.PHONY : clean_libs
clean_libs: clean_cni_libs clean_counters_libs clean_utilities_libs

# Remove all of the files generated by this Makefile.

.PHONY : clean_all
clean_all: clean clean_libs

# Create an HTML version of this file using the source-highlight program, which
# generates an "image" of the file with syntax color coding.

SWIG_DOCS_PATH := $(SRC_DOCS_PATH)/$(SWIG_DIRECTORY)
HTLS_CFG_FILE  := $(SWIG_DIRECTORY).cfg

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(SWIG_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SWIG_DOCS_PATH)/$(MAKEFILE).html
	@for i in `ls *.swig` ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(SWIG_DOCS_PATH)/$$i.html ;\
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(HTLS_CFG_FILE) $(SWIG_DOCS_PATH)
	@$(HTLS) $(SWIG_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(SWIG_DOCS_PATH)/$(HTLS_HEADER_FILE)
	@$(RM) $(SWIG_DOCS_PATH)/$(HTLS_CFG_FILE)

.PHONY : docs
docs: docs_all clean_docs
