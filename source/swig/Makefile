################################################################################
# Makefile - This make parameter file is used to build wrapper libraries using #
#            SWIG. These libraries interact with the jASL libraries, allowing  #
#            the public classes to be used in scripting languages.             #
#                                                                              #
# Written By : Craig R. Campbell  -  July 2008                                 #
#                                                                              #
# $Id: Makefile,v 1.3 2008/09/11 15:33:01 campbell Exp $
################################################################################

## System-wide build variables.

include $(JASL_BASE)/util/jasl.make

## Build variables used in generating all of the wrapper libraries.

# SWIG command and target language specific options.

SWIG_CMD = swig -c++
SWIG_PERL_CMD = $(SWIG_CMD) -perl -outdir $(PERL_BIN_PATH)
SWIG_PYTHON_CMD = $(SWIG_CMD) -python -shadow -outdir $(PYTHON_BIN_PATH)

# GCC compile commands with options specific to each target language.

GCC_PERL_COMPILE_CMD = $(GCC_COMPILE_CMD) -I/usr/lib/perl5/5.8.8/i686-linux/CORE
GCC_PYTHON_COMPILE_CMD = $(GCC_COMPILE_CMD) -I/usr/include/python2.5

# GCC build/link options common to all libraries and target languages.

GCC_COMMON_BUILD_OPTIONS = -lgcj -lstdc++ -L$(LIB_PATH)

## CniWrapper ##################################################################

# The library/module name
CNI_WRAPPER_LIBRARY_NAME = CniWrapper
# Input file for SWIG.
CNI_WRAPPER_SWIG_SRC_FILE = $(CNI_WRAPPER_LIBRARY_NAME).swig
# CNI_WRAPPER class header file.
CNI_WRAPPER_HDR = cniWrapper.h
# swig command output file.
CNI_SWIG_WRAPPER_SRC = $(CNI_WRAPPER_LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
CNI_SWIG_WRAPPER_OBJ = ${CNI_SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
CNI_SWIG_WRAPPER_LIB = $(CNI_WRAPPER_LIBRARY_NAME).so
# Perl module file.
CNI_SWIG_WRAPPER_PM = ${CNI_SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
CNI_SWIG_WRAPPER_PY = ${CNI_SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
CNI_SWIG_WRAPPER_PYC = ${CNI_SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

cni_perl_src: $(CNI_WRAPPER_SWIG_SRC_FILE)
	$(SWIG_PERL_CMD) $(CNI_WRAPPER_SWIG_SRC_FILE)

cni_python_src: $(CNI_WRAPPER_SWIG_SRC_FILE)
	$(SWIG_PYTHON_CMD) $(CNI_WRAPPER_SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

CNI_COMMON_COMPILE_OPTIONS = -I$(INCLUDE_PATH) -include $(CNI_WRAPPER_HDR) \
                             $(CNI_SWIG_WRAPPER_SRC)

cni_perl_obj: cni_perl_src
	$(GCC_PERL_COMPILE_CMD) $(CNI_COMMON_COMPILE_OPTIONS)

cni_python_obj: cni_python_src
	$(GCC_PYTHON_COMPILE_CMD) $(CNI_COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

CNI_COMMON_BUILD_OPTIONS = $(GCC_COMMON_BUILD_OPTIONS) \
                           -l$(CNI_WRAPPER_BASE_LIB_NAME) \
                           $(CNI_SWIG_WRAPPER_OBJ)

cni_perl_lib: cni_perl_obj
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(CNI_SWIG_WRAPPER_LIB) \
	                     $(CNI_COMMON_BUILD_OPTIONS)

cni_python_lib: cni_python_obj
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(CNI_SWIG_WRAPPER_LIB) \
	                     $(CNI_COMMON_BUILD_OPTIONS)

cni_libs: cni_perl_lib cni_python_lib

# Remove the wrapper source and object files (works for all target languages).

clean_cni_src:
	$(RM) $(CNI_SWIG_WRAPPER_SRC)

clean_cni_obj:
	$(RM) $(CNI_SWIG_WRAPPER_OBJ)

clean_cni: clean_cni_src clean_cni_obj

# Remove the files generated for use with Perl.

clean_cni_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(CNI_SWIG_WRAPPER_PM)

clean_cni_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(CNI_SWIG_WRAPPER_LIB)

clean_cni_perl: clean_cni_perl_module clean_cni_perl_lib

# Remove the files generated for use with Python.

clean_cni_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(CNI_SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(CNI_SWIG_WRAPPER_PYC)

clean_cni_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(CNI_SWIG_WRAPPER_LIB)

clean_cni_python: clean_cni_python_module clean_cni_python_lib

# Remove installed libraries and module files for all of the target languages.

clean_cni_libs : clean_cni_perl clean_cni_python

## Counters ###################################################################

# The library/module name
COUNTERS_LIBRARY_NAME = Counters
# Input file for SWIG.
COUNTERS_SWIG_SRC_FILE = $(COUNTERS_LIBRARY_NAME).swig
# jasl-counters library header files.
COUNTERS_HDR_FILES = -include jaslWrapper.h
# swig command output file.
COUNTERS_SWIG_WRAPPER_SRC = $(COUNTERS_LIBRARY_NAME)_wrap.cxx
# Wrapper object file.
COUNTERS_SWIG_WRAPPER_OBJ = ${COUNTERS_SWIG_WRAPPER_SRC:.cxx=.o}
# The library file.
COUNTERS_SWIG_WRAPPER_LIB = $(COUNTERS_LIBRARY_NAME).so
# Perl module file.
COUNTERS_SWIG_WRAPPER_PM = ${COUNTERS_SWIG_WRAPPER_LIB:.so=.pm}
# Python module file.
COUNTERS_SWIG_WRAPPER_PY = ${COUNTERS_SWIG_WRAPPER_LIB:.so=.py}
# Python byte-compiled module file (generated the first time the library is
# used).
COUNTERS_SWIG_WRAPPER_PYC = ${COUNTERS_SWIG_WRAPPER_LIB:.so=.pyc}

# Generate the wrapper source file for the desired target language.

counters_perl_src: $(COUNTERS_SWIG_SRC_FILE)
	$(SWIG_PERL_CMD) $(COUNTERS_SWIG_SRC_FILE)

counters_python_src: $(COUNTERS_SWIG_SRC_FILE)
	$(SWIG_PYTHON_CMD) $(COUNTERS_SWIG_SRC_FILE)

# Build the object (.o) file for the desired target language.

COUNTERS_COMMON_COMPILE_OPTIONS = -I$(INCLUDE_PATH) $(COUNTERS_HDR_FILES) \
                                  $(COUNTERS_SWIG_WRAPPER_SRC)

counters_perl_obj: counters_perl_src
	$(GCC_PERL_COMPILE_CMD) $(COUNTERS_COMMON_COMPILE_OPTIONS)

counters_python_obj: counters_python_src
	$(GCC_PYTHON_COMPILE_CMD) $(COUNTERS_COMMON_COMPILE_OPTIONS)

# Build the library for the desired target language.

COUNTERS_COMMON_BUILD_OPTIONS = $(GCC_COMMON_BUILD_OPTIONS) \
                                -l$(COUNTERS_BASE_LIB_NAME) \
                                $(COUNTERS_SWIG_WRAPPER_OBJ)

counters_perl_lib: counters_perl_obj
	$(GCC_LIB_BUILD_CMD) $(PERL_LIB_PATH)/$(COUNTERS_SWIG_WRAPPER_LIB) \
	                     $(COUNTERS_COMMON_BUILD_OPTIONS)

counters_python_lib: counters_python_obj
	$(GCC_LIB_BUILD_CMD) $(PYTHON_LIB_PATH)/_$(COUNTERS_SWIG_WRAPPER_LIB) \
	                     $(COUNTERS_COMMON_BUILD_OPTIONS)

counters_libs: counters_perl_lib counters_python_lib

# Remove the wrapper source and object files (works for all target languages).

clean_counters_src:
	$(RM) $(COUNTERS_SWIG_WRAPPER_SRC)

clean_counters_obj:
	$(RM) $(COUNTERS_SWIG_WRAPPER_OBJ)

clean_counters: clean_counters_src clean_counters_obj

# Remove the files generated for use with Perl.

clean_counters_perl_module:
	$(RM) $(PERL_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PM)

clean_counters_perl_lib:
	$(RM) $(PERL_LIB_PATH)/$(COUNTERS_SWIG_WRAPPER_LIB)

clean_counters_perl: clean_counters_perl_module clean_counters_perl_lib

# Remove the files generated for use with Python.

clean_counters_python_module:
	$(RM) $(PYTHON_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PY)
	$(RM) $(PYTHON_BIN_PATH)/$(COUNTERS_SWIG_WRAPPER_PYC)

clean_counters_python_lib:
	$(RM) $(PYTHON_LIB_PATH)/_$(COUNTERS_SWIG_WRAPPER_LIB)

clean_counters_python: clean_counters_python_module clean_counters_python_lib

# Remove installed libraries and module files for all of the target languages.

clean_counters_libs : clean_counters_perl clean_counters_python

## General/Combination targets ################################################

# Build all of the wrapper libraries and install them, along with their
# respective module files, in the appropriate output directories.

libs: cni_libs counters_libs clean

# Remove the generated files from this directory.

clean: clean_cni clean_counters

# Remove all of the library and module files from their respective output
# directories.

clean_libs: clean_cni_libs clean_counters_libs

# Remove all of the files generated by this Makefile.

clean_all: clean clean_libs

# Create an HTML version of this file using the source-highlight program, which
# generates an "image" of the file with syntax color coding.

SWIG_DOCS_PATH = $(SRC_DOCS_PATH)/$(SWIG_DIRECTORY)

docs:
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SWIG_DOCS_PATH)/$(MAKEFILE).html
	@for i in `ls *.swig` ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(SWIG_DOCS_PATH)/$$i.html ;\
	done
	@$(HTLS) $(SWIG_DOCS_PATH)