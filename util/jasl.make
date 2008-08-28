################################################################################
# jasl.make - This make parameter file is used to define constants used by all #
#             of the Makefiles in the source hierarchy. All paths are relative #
#             to JASL_BASE, which is a required environment variable that must #
#             refer to the topmost directory named jasl.                       #
#                                                                              #
# Written By : Craig R. Campbell  -  November 2002                             #
#                                                                              #
# $Id: jasl.make,v 1.10 2008/08/28 20:03:25 campbell Exp $
################################################################################

## Program name.

PROGRAM_NAME       = jasl

## Directory definitions.

# Note that if the installation directory is changed from ${JASL_BASE} it will
# be necessary to change the OUTPUT_DIRECTORY setting in doxygen.jasl to the
# expanded version of ${DOXYGEN_DOC_PATH} (see below) in order for the files to
# be installed correctly.

INSTALL_DIRECTORY  = ${JASL_BASE}

BIN_PATH           = ${INSTALL_DIRECTORY}/bin
DOCS_PATH          = ${INSTALL_DIRECTORY}/share/${PROGRAM_NAME}/doc
INCLUDE_PATH       = ${INSTALL_DIRECTORY}/include
LIB_PATH           = ${INSTALL_DIRECTORY}/lib

SRC_PATH           = ${JASL_BASE}/source
UTIL_PATH          = ${JASL_BASE}/util

# A sub-directory of the source directory.
WRAPPERS_DIRECTORY = wrappers
# A sub-directory of the wrappers directory.
SWIG_DIRECTORY     = swig

PERL_SRC_PATH      = $(SRC_PATH)/perljASL
PERL_BIN_PATH      = $(BIN_PATH)/perljASL
PERL_LIB_PATH      = $(LIB_PATH)/perl

PYTHON_SRC_PATH    = $(SRC_PATH)/pyjASL
PYTHON_BIN_PATH    = $(BIN_PATH)/pyjASL
PYTHON_LIB_PATH    = $(LIB_PATH)/python

## Java, gcc and gcj compiler related settings.

JAVA_COMPILER      = javac
GCC_COMPILER       = gcc
GCJ_COMPILER       = gcj

CLASSPATH_CMD      = -classpath $(BIN_PATH)
GCJ_CLASSPATH_CMD  = --classpath=$(BIN_PATH)
OUTPUT_DIR_CMD     = -d $(BIN_PATH)

JAVA_OPTIMIZE      = -O
GCJ_OPTIMIZE       = -O3 -s -pipe

JAVA_OPTIONS       = $(JAVA_COMPILER) $(JAVA_OPTIMIZE) $(OUTPUT_DIR_CMD) \
                     $(CLASSPATH_CMD)

GCC_BUILD_CMD      = $(GCC_COMPILER) $(GCJ_OPTIMIZE)
GCC_COMPILE_CMD    = $(GCC_BUILD_CMD) -c

GCJ_BUILD_CMD      = $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD) $(GCJ_OPTIMIZE)
GCJ_COMPILE_CMD    = $(GCJ_BUILD_CMD) -c

GCJH_CMD           = gcjh -force

SHARED_LIB_OPTIONS = $(GCJ_OPTIMIZE) -shared -o
GCC_LIB_BUILD_CMD  = $(GCC_COMPILER) $(SHARED_LIB_OPTIONS)
GCJ_LIB_BUILD_CMD  = $(GCJ_COMPILER) $(SHARED_LIB_OPTIONS)

## Java runtime program.

JAVA_RUN_CMD       = java

## jar.

JAR_BUILD_CMD      = jar cmf
JAR_RUN_CMD        = $(JAVA_RUN_CMD) -jar

## ctags.

CTAGS_BUILD_CMD    = ctags --file-tags=yes -R --excmd=number

## Documentation build variables.

# Linked source files.

MAKEFILE           = Makefile
SRC_DOCS_PATH      = $(DOCS_PATH)/source

SRC_HIGHLIGHT_CMD  = source-highlight --doc --tab=4 --ctags=''
CPP2HTML           = $(SRC_HIGHLIGHT_CMD) --src-lang=cpp
JAVA2HTML          = $(SRC_HIGHLIGHT_CMD) --src-lang=java
MAKE2HTML          = $(SRC_HIGHLIGHT_CMD) --src-lang=makefile

HTLS               = $(UTIL_PATH)/htls -genpage -index
SED_CONV_CMD       = sed -f $(UTIL_PATH)/htmlconv

# javadoc.

JAVADOC_COMPILER   = javadoc
JAVADOCS_PATH      = $(DOCS_PATH)/javadocs
JAVADOCS_LINK      = -link http://java.sun.com/javase/6/docs/api
JAVADOC_OPTIONS    = -version -author -package -nodeprecatedlist -use \
                     -breakiterator
JAVADOC_TITLE      = -windowtitle "jASL Programming Documentation"
JAVADOC_CMD_LIST   = -d $(JAVADOCS_PATH) $(JAVADOCS_LINK) $(JAVADOC_OPTIONS) \
                     $(JAVADOC_TITLE)

# gjdoc.

GJDOC              = gjdoc

# doxygen.

DOXYGEN            = doxygen
DOXYGEN_DOC_PATH   = $(DOCS_PATH)/doxygen
DOXYGEN_DEF_FILE   = $(UTIL_PATH)/doxygen.jasl

## Library and package path and file descriptions.

LIB_PREFIX                   = lib

# CNI (Compiled Native Interface) wrapper.

CNI_WRAPPER_BASE_LIB_NAME    = cni-wrapper
CNI_WRAPPER_STATIC_LIB_NAME  = $(LIB_PREFIX)$(CNI_WRAPPER_BASE_LIB_NAME).a
CNI_WRAPPER_STATIC_LIB_PATH  = $(LIB_PATH)/$(CNI_WRAPPER_STATIC_LIB_NAME)

# counters package.

COUNTERS_PKG_NAME            = counters

COUNTERS_PKG_PATH            = $(PROGRAM_NAME)/$(COUNTERS_PKG_NAME)
COUNTERS_OBJ_PATH            = ${JASL_BASE}/bin/$(COUNTERS_PKG_PATH)
COUNTERS_HDR_PATH            = $(INCLUDE_PATH)/$(COUNTERS_PKG_PATH)

COUNTERS_SRC_FILES           = $(COUNTERS_PKG_PATH)/*.java
COUNTERS_OBJ_FILES           = $(COUNTERS_OBJ_PATH)/*.o

COUNTERS_BASE_LIB_NAME       = $(PROGRAM_NAME)-$(COUNTERS_PKG_NAME)
COUNTERS_STATIC_LIB_NAME     = $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).a
COUNTERS_STATIC_LIB_PATH     = $(LIB_PATH)/$(COUNTERS_STATIC_LIB_NAME)

ALL_PACKAGES                 = $(COUNTERS_PKG_PATH)

## Miscellaneous programs.

AR               = /usr/bin/ar rcs
CAT              = /bin/cat
CP               = /bin/cp -p
DATE             = /bin/date
DIFF             = /usr/bin/diff
LN               = /bin/ln -sf
MAKE             = /usr/bin/make
MV               = /bin/mv
RM               = /bin/rm -f
RM_RECURSIVE     = $(RM) -r
