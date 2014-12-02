################################################################################
# Makefile - This make parameter file is used to build wrapper libraries for   #
#            the CniWrapper library using SWIG. These libraries interact with  #
#            the wrapped library, allowing the it to be used in scripting      #
#            languages.                                                        #
#                                                                              #
# Written By : Craig R. Campbell  -  December 2014                             #
#                                                                              #
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

#GCC_PERL_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/lib/perl5/5.14.2/i686-linux/CORE
GCC_PERL_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/lib64/perl5/5.18.0/x86_64-linux-thread-multi/CORE
GCC_PYTHON_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/include/python2.7

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

# The library/module name
LIBRARY_NAME := CniWrapper
# Input file for SWIG.
SWIG_SRC_FILE := $(LIBRARY_NAME).swig
# swig command output file.
SWIG_WRAPPER_SRC := $(LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
SWIG_WRAPPER_OBJ := ${SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
SWIG_WRAPPER_LIB := $(LIBRARY_NAME).so
# Perl module file.
SWIG_WRAPPER_PM := ${SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
SWIG_WRAPPER_PY := ${SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
SWIG_WRAPPER_PYC := ${SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

.PHONY : perl_src
perl_src: $(SWIG_SRC_FILE) perl_bin_directory
	$(SWIG_PERL_CMD) $(SWIG_SRC_FILE)

.PHONY : python_src
python_src: $(SWIG_SRC_FILE) python_bin_directory
	$(SWIG_PYTHON_CMD) $(SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

COMMON_COMPILE_OPTIONS := -I$(CNI_HDR_PATH) -include CniWrapper.h \
                          $(SWIG_WRAPPER_SRC)

.PHONY : perl_obj
perl_obj: perl_src
	$(GCC_PERL_COMPILE_CMD) $(COMMON_COMPILE_OPTIONS)

.PHONY : python_obj
python_obj: python_src
	$(GCC_PYTHON_COMPILE_CMD) $(COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

COMMON_BUILD_OPTIONS := $(GCC_COMMON_BUILD_OPTIONS) \
                        -l$(CNI_WRAPPER_BASE_LIB_NAME) \
                        $(SWIG_WRAPPER_OBJ)

.PHONY : perl_lib
perl_lib: perl_obj perl_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(SWIG_WRAPPER_LIB) \
	                     $(COMMON_BUILD_OPTIONS)

.PHONY : python_lib
python_lib: python_obj python_lib_directory
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(SWIG_WRAPPER_LIB) \
	                     $(COMMON_BUILD_OPTIONS)

# Remove the wrapper source and object files (works for all target languages).

.PHONY : clean_src
clean_src:
	$(RM) $(SWIG_WRAPPER_SRC)

.PHONY : clean_obj
clean_obj:
	$(RM) $(SWIG_WRAPPER_OBJ)

# Remove the files generated for use with Perl.

.PHONY : clean_perl_module
clean_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(SWIG_WRAPPER_PM)

.PHONY : clean_perl_lib
clean_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(SWIG_WRAPPER_LIB)

.PHONY : clean_perl
clean_perl: clean_perl_module clean_perl_lib

# Remove the files generated for use with Python.

.PHONY : clean_python_module
clean_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(SWIG_WRAPPER_PYC)

.PHONY : clean_python_lib
clean_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(SWIG_WRAPPER_LIB)

.PHONY : clean_python
clean_python: clean_python_module clean_python_lib

## General/Combination targets ################################################

# Build all of the wrapper libraries and install them, along with their
# respective module files, in the appropriate output directories.

.PHONY : libs
libs: perl_lib python_lib clean

# Remove the generated files from this directory.

.PHONY : clean
clean: clean_src clean_obj

# Remove all of the library and module files from their respective output
# directories.

.PHONY : clean_libs
clean_libs : clean_perl clean_python

# Remove all of the files generated by this Makefile.

.PHONY : clean_all
clean_all: clean clean_libs

# Create an HTML version of this file using the source-highlight program, which
# generates an "image" of the file with syntax color coding.

SWIG_DOCS_PATH := $(SRC_DOCS_PATH)/$(SWIG_DIRECTORY)/$(LIBRARY_NAME)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(SWIG_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SWIG_DOCS_PATH)/$(MAKEFILE).html
	@for i in `ls *.swig` ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(SWIG_DOCS_PATH)/$$i.html ;\
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(SWIG_DOCS_PATH)
	@$(HTLS) $(SWIG_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(SWIG_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
