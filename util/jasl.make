################################################################################
# jasl.make - This make parameter file is used to define constants used by all #
#             of the Makefiles in the source hierarchy. All paths are relative #
#             to JASL_BASE, which is a required environment variable that must #
#             refer to the topmost directory named jasl.                       #
#                                                                              #
# Written By : Craig R. Campbell  -  November 2002                             #
#                                                                              #
# $Id: jasl.make,v 1.17 2010/02/04 00:45:57 campbell Exp $
################################################################################

## Program name.

PROGRAM_NAME       := jasl

## Directory definitions.

# Note that if the installation directory is changed from ${JASL_BASE} it will
# be necessary to change the OUTPUT_DIRECTORY setting in doxygen.jasl to the
# expanded version of ${DOXYGEN_DOC_PATH} (see below) in order for the files to
# be installed correctly.

INSTALL_DIRECTORY  := ${JASL_BASE}

BIN_PATH           := ${INSTALL_DIRECTORY}/bin
DOCS_PATH          := ${INSTALL_DIRECTORY}/share/${PROGRAM_NAME}/doc
INCLUDE_PATH       := ${INSTALL_DIRECTORY}/include
LIB_PATH           := ${INSTALL_DIRECTORY}/lib

SRC_PATH           := ${JASL_BASE}/source
UTIL_PATH          := ${JASL_BASE}/util

# The following are sub-directories of the source directory.

CNI_DIRECTORY      := cni-wrapper
SWIG_DIRECTORY     := swig
PERL_DIRECTORY     := perljASL
PYTHON_DIRECTORY   := pyjASL

PERL_BIN_PATH      := $(BIN_PATH)/$(PERL_DIRECTORY)
PERL_LIB_PATH      := $(LIB_PATH)/perl

PYTHON_BIN_PATH    := $(BIN_PATH)/$(PYTHON_DIRECTORY)
PYTHON_LIB_PATH    := $(LIB_PATH)/python

## Java, gcc and gcj compiler related settings.

JAVA_COMPILER      := javac
GCC_COMPILER       := gcc
GCJ_COMPILER       := gcj

CLASSPATH_CMD      := -classpath $(BIN_PATH)
GCJ_CLASSPATH_CMD  := --classpath=$(BIN_PATH)
OUTPUT_DIR_CMD     := -d $(BIN_PATH)

JAVA_OPTIMIZE      := -O
GCJ_OPTIMIZE       := -O3 -s -pipe

JAVA_OPTIONS       := $(JAVA_COMPILER) $(JAVA_OPTIMIZE) $(OUTPUT_DIR_CMD) \
                      $(CLASSPATH_CMD)

GCC_BUILD_CMD      := $(GCC_COMPILER) $(GCJ_OPTIMIZE)
GCC_COMPILE_CMD    := $(GCC_BUILD_CMD) -c

GCJ_BUILD_CMD      := $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD) $(GCJ_OPTIMIZE)
GCJ_COMPILE_CMD    := $(GCJ_BUILD_CMD) -c

GCJH_CMD           := gcjh -force
GJAVAH_CMD         := gjavah -verbose -cni -force -d $(INCLUDE_PATH) -all

SHARED_LIB_OPTIONS := $(GCJ_OPTIMIZE) -shared -o
GCC_LIB_BUILD_CMD  := $(GCC_COMPILER) $(SHARED_LIB_OPTIONS)
GCJ_LIB_BUILD_CMD  := $(GCJ_COMPILER) $(SHARED_LIB_OPTIONS)

## Java runtime program.

JAVA_RUN_CMD       := java

## jar.

JAR_BUILD_CMD      := jar cmf
JAR_RUN_CMD        := $(JAVA_RUN_CMD) -jar

## ctags.

CTAGS_BUILD_CMD    := ctags --file-tags=yes -R --excmd=number

## Documentation build variables.

# Linked source files.

# The LC_ALL enviroment variable must be set or overridden in order for the
# [a-z] pattern in a regular expression (see htmlconv) to work correctly.

export LC_ALL      := C

MAKEFILE           := Makefile
SRC_DOCS_PATH      := $(DOCS_PATH)/source

SRC_HIGHLIGHT_CMD  := source-highlight --doc --tab=4 --ctags=''
CPP2HTML           := $(SRC_HIGHLIGHT_CMD) --src-lang=cpp
JAVA2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=java
MAKE2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=makefile
PERL2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=perl
PYTHON2HTML        := $(SRC_HIGHLIGHT_CMD) --src-lang=python

HTLS               := $(UTIL_PATH)/htls -genpage -index
SED_CONV_CMD       := sed -f $(UTIL_PATH)/htmlconv

HTLS_HEADER_FILE   := header.html

# javadoc.

JAVADOC_COMPILER   := javadoc
JAVADOCS_PATH      := $(DOCS_PATH)/javadocs
JAVADOCS_LINK      := -link http://java.sun.com/javase/6/docs/api
JAVADOC_OPTIONS    := -version -author -package -nodeprecatedlist -use \
                      -breakiterator
JAVADOC_TITLE      := -windowtitle "jASL Programming Documentation"
JAVADOC_CMD_LIST   := -d $(JAVADOCS_PATH) $(JAVADOCS_LINK) $(JAVADOC_OPTIONS) \
                      $(JAVADOC_TITLE)
# gjdoc.

GJDOC              := gjdoc

# doxygen.

DOXYGEN            := doxygen
DOXYGEN_DOC_PATH   := $(DOCS_PATH)/doxygen
DOXYGEN_DEF_FILE   := $(UTIL_PATH)/doxygen.jasl

# global (gtags and htags)

GTAGS              := gtags
GTAGS_FIND         := find . -type f -name "*.java"
GTAGS_OPTIONS      := -f -
GTAGS_PATH         := $(SRC_PATH)/$(GTAGS)

HTAGS              := htags
HTAGS_DOCS_PATH    := $(DOCS_PATH)/global
HTAGS_TITLE        := "jASL Source Navigation"
HTAGS_OPTIONS      := -h -I -T --tabs 4 -F -x --title $(HTAGS_TITLE) \
                      -d ${GTAGSDBPATH} $(HTAGS_DOCS_PATH)
HTAGS_OUTPUT_PATH  := $(HTAGS_DOCS_PATH)/HTML
HTAGS_SED_CMD      := $(SED_CONV_CMD) --in-place
HTAGS_FIND_CNV_CMD := find $(HTAGS_OUTPUT_PATH) -type f -name "*.html" \
                      -exec $(HTAGS_SED_CMD) {} \;

HTAGS_ADDLINKS_CMD := $(UTIL_PATH)/addlinks $(HTAGS_OUTPUT_PATH)

## Library and package path and file descriptions.

LIB_PREFIX                   := lib

# CNI (Compiled Native Interface) wrapper.

CNI_WRAPPER_SRC_FILES        := $(CNI_DIRECTORY)/*.cpp $(CNI_DIRECTORY)/*.h

CNI_WRAPPER_BASE_LIB_NAME    := $(CNI_DIRECTORY)
CNI_WRAPPER_STATIC_LIB_NAME  := $(LIB_PREFIX)$(CNI_WRAPPER_BASE_LIB_NAME).a
CNI_WRAPPER_STATIC_LIB_PATH  := $(LIB_PATH)/$(CNI_WRAPPER_STATIC_LIB_NAME)

# counters package.

COUNTERS_PKG_NAME            := counters

COUNTERS_PKG_PATH            := $(PROGRAM_NAME)/$(COUNTERS_PKG_NAME)
COUNTERS_OBJ_PATH            := ${JASL_BASE}/bin/$(COUNTERS_PKG_PATH)
COUNTERS_BIN_PATH            := $(BIN_PATH)/$(COUNTERS_PKG_PATH)
COUNTERS_HDR_PATH            := $(INCLUDE_PATH)/$(COUNTERS_PKG_PATH)

COUNTERS_SRC_FILES           := $(COUNTERS_PKG_PATH)/*.java
COUNTERS_CLASS_FILES         := $(COUNTERS_BIN_PATH)/*.class
COUNTERS_OBJ_FILES           := $(COUNTERS_OBJ_PATH)/*.o
COUNTERS_HDR_FILES           := $(COUNTERS_HDR_PATH)/*.h

COUNTERS_BASE_LIB_NAME       := $(PROGRAM_NAME)-$(COUNTERS_PKG_NAME)
COUNTERS_STATIC_LIB_NAME     := $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).a
COUNTERS_STATIC_LIB_PATH     := $(LIB_PATH)/$(COUNTERS_STATIC_LIB_NAME)

# utilities package.

UTILITIES_PKG_NAME           := utilities

UTILITIES_PKG_PATH           := $(PROGRAM_NAME)/$(UTILITIES_PKG_NAME)
UTILITIES_OBJ_PATH           := ${JASL_BASE}/bin/$(UTILITIES_PKG_PATH)
UTILITIES_BIN_PATH           := $(BIN_PATH)/$(UTILITIES_PKG_PATH)
UTILITIES_HDR_PATH           := $(INCLUDE_PATH)/$(UTILITIES_PKG_PATH)

UTILITIES_SRC_FILES          := $(UTILITIES_PKG_PATH)/*.java
UTILITIES_CLASS_FILES        := $(UTILITIES_BIN_PATH)/*.class
UTILITIES_OBJ_FILES          := $(UTILITIES_OBJ_PATH)/*.o
UTILITIES_HDR_FILES          := $(UTILITIES_HDR_PATH)/*.h

UTILITIES_BASE_LIB_NAME      := $(PROGRAM_NAME)-$(UTILITIES_PKG_NAME)
UTILITIES_STATIC_LIB_NAME    := $(LIB_PREFIX)$(UTILITIES_BASE_LIB_NAME).a
UTILITIES_STATIC_LIB_PATH    := $(LIB_PATH)/$(UTILITIES_STATIC_LIB_NAME)

ALL_PACKAGES                 := $(COUNTERS_PKG_PATH) $(UTILITIES_PKG_PATH)

## Miscellaneous programs.

AR               := /usr/bin/ar rcs
CAT              := /bin/cat
CP               := /bin/cp -p
DATE             := /bin/date
DIFF             := /usr/bin/diff
INSTALL          := /usr/bin/install
LN               := /bin/ln -sf
MAKE             := /usr/bin/make
MV               := /bin/mv
RM               := /bin/rm -f
RM_RECURSIVE     := $(RM) -r

## Constants and arguments associated with the install program, which is used to
## create directories and install files directly.

INSTALL_EXE_MODE := --mode=0755
INSTALL_STD_MODE := --mode=0644

INSTALL_DIR      := $(INSTALL) --directory $(INSTALL_EXE_MODE)

INSTALL_FILE     := $(INSTALL) --preserve-timestamps
INSTALL_EXE_FILE := $(INSTALL_FILE) $(INSTALL_EXE_MODE)
INSTALL_STD_FILE := $(INSTALL_FILE) $(INSTALL_STD_MODE)

## Targets to create the top-level directories for output files.

.PHONY : bin_directory
bin_directory:
	$(INSTALL_DIR) $(BIN_PATH)

.PHONY : docs_directory
docs_directory:
	$(INSTALL_DIR) $(DOCS_PATH)

.PHONY : include_directory
include_directory:
	$(INSTALL_DIR) $(INCLUDE_PATH)

.PHONY : lib_directory
lib_directory:
	$(INSTALL_DIR) $(LIB_PATH)

## These targets are for the destination directories of the module files
## associated with the libraries for each scripting language. The files are
## actually generated in the swig directory, but the targets are defined here
## for use in installing the scripts that use the libraries.

.PHONY : perl_bin_directory
perl_bin_directory:
	$(INSTALL_DIR) $(PERL_BIN_PATH)

.PHONY : python_bin_directory
python_bin_directory:
	$(INSTALL_DIR) $(PYTHON_BIN_PATH)
