################################################################################
# jasl.make - This make parameter file is used to define constants used by all #
#             of the Makefiles in the source hierarchy. All paths are relative #
#             to JASL_BASE, which is a required environment variable that must #
#             refer to the topmost directory named jasl.                       #
#                                                                              #
# Written By: Craig R. Campbell  -  November 2002                              #
#                                                                              #
################################################################################

## Program name.

PROGRAM_NAME       := jasl

## Directory definitions.

# Note that if the installation directory is changed from ${JASL_BASE} it will
# be necessary to change the OUTPUT_DIRECTORY setting in doxygen.jasl to the
# expanded version of ${DOXYGEN_DOC_PATH} (see below) in order for the files to
# be installed correctly.

INSTALL_DIRECTORY  := ${JASL_BASE}

SRC_PATH           := ${JASL_BASE}/source
TOOLS_PATH         := ${JASL_BASE}/tools
UTIL_PATH          := ${JASL_BASE}/util

BIN_PATH           := $(INSTALL_DIRECTORY)/bin
DOCS_PATH          := $(INSTALL_DIRECTORY)/share/$(PROGRAM_NAME)/doc
DATA_PATH          := $(INSTALL_DIRECTORY)/share/$(PROGRAM_NAME)/data
INCLUDE_PATH       := $(INSTALL_DIRECTORY)/include
LIB_PATH           := $(INSTALL_DIRECTORY)/lib/$(PROGRAM_NAME)
TOOLS_LIB_PATH     := $(TOOLS_PATH)/lib

# The name of the sub-directory in each directory where object files generated
# for the purpose of building the correponding libraries (shared and static) are
# placed.

OBJ_SUB_DIRECTORY  := .obj

# The following are sub-directories of the source directory. The ones associated
# with Perl and Python are also used to name the SWIG output directories.

CNI_DIRECTORY      := cni-wrapper
JNI_DIRECTORY      := jni-wrapper
SWIG_DIRECTORY     := swig
PERL_DIRECTORY     := perl-jasl
PYTHON_DIRECTORY   := py-jasl

PERL_BIN_PATH      := $(BIN_PATH)/$(PERL_DIRECTORY)
PERL_LIB_PATH      := $(LIB_PATH)/perl

PYTHON_BIN_PATH    := $(BIN_PATH)/$(PYTHON_DIRECTORY)
PYTHON_LIB_PATH    := $(LIB_PATH)/python

## Java, gcc and gcj compiler related settings.
##
## To compile the java files for use within an Android application, it is
## (currently) necessary to set the source and target versions to 1.6. Set the
## environment variables as indicated below to enable this capability. Note that
## it is really only applicable when building the jar archive, since that is
## what will actually be used.
##
##     export ANDROID_SOURCE_VERSION="-source 1.7"
##     export ANDROID_TARGET_VERSION="-target 1.7"

JAVA_COMPILER      := javac
GCC_COMPILER       := $(TOOLS_PATH)/bin/gcc
GCJ_COMPILER       := $(TOOLS_PATH)/bin/gcj
GCJ_LIBRARIES      := $(TOOLS_PATH)/lib

CLASSPATH_CMD      := -classpath $(BIN_PATH)
GCJ_CLASSPATH_CMD  := --classpath=$(BIN_PATH)
OUTPUT_DIR_CMD     := -d $(BIN_PATH)

JAVA_OPTIMIZE      := -O
GCJ_OPTIMIZE       := -O2 -s -pipe -fPIC

JAVA_OPTIONS       := $(JAVA_COMPILER) $(JAVA_OPTIMIZE) \
                      ${ANDROID_SOURCE_VERSION} ${ANDROID_TARGET_VERSION} \
                      $(OUTPUT_DIR_CMD) $(CLASSPATH_CMD)

# If the JASL_COMPILER_FLAGS environment variable is set, it will be applied
# instead of the default above. This is intended primarily for use in building
# the code to gather coverage data. See the README file in the source directory
# for more details.

ifdef JASL_COMPILER_FLAGS
    GCJ_OPTIMIZE   := ${JASL_COMPILER_FLAGS} -fPIC
endif

# The following LD_LIBRARY_PATH variables are used to build and test executables
# that rely on the jASL and/or gcj libraries.
#
JASL_LD_LIB_PATH   := LD_LIBRARY_PATH=$(LIB_PATH)
GCJ_LD_LIB_PATH    := LD_LIBRARY_PATH=$(TOOLS_LIB_PATH)
LD_LIB_PATH        := LD_LIBRARY_PATH=$(LIB_PATH):$(TOOLS_LIB_PATH)

GCC_BUILD_CMD      := $(GCC_COMPILER) $(GCJ_OPTIMIZE)
GCC_COMPILE_CMD    := $(GCC_BUILD_CMD) -c

GCJ_BUILD_CMD      := $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD) $(GCJ_OPTIMIZE)
GCJ_COMPILE_CMD    := $(GCJ_BUILD_CMD) -c

GCJH_CMD           := $(TOOLS_PATH)/bin/gcjh -force
GJAVAH_CMD         := $(TOOLS_PATH)/bin/gjavah -verbose -cni -force -d $(INCLUDE_PATH) -all

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
JSON2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=json
MAKE2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=makefile
PERL2HTML          := $(SRC_HIGHLIGHT_CMD) --src-lang=perl
PYTHON2HTML        := $(SRC_HIGHLIGHT_CMD) --src-lang=python

HTLS               := $(UTIL_PATH)/htls -genpage -index
SED_CONV_CMD       := sed -f $(UTIL_PATH)/htmlconv

HTLS_HEADER_FILE   := header.html

# javadoc.

JAVADOC_COMPILER   := javadoc
JAVADOCS_PATH      := $(DOCS_PATH)/javadocs
JAVADOCS_LINK      := -link https://docs.oracle.com/en/java/javase/21/docs/api
JAVADOC_OPTIONS    := -version -author -package -nodeprecatedlist -use \
                      -breakiterator
JAVADOC_TITLE      := -windowtitle "jASL Programming Documentation"
JAVADOC_CMD_LIST   := -d $(JAVADOCS_PATH) $(JAVADOCS_LINK) $(JAVADOC_OPTIONS) \
                      $(JAVADOC_TITLE)
# gjdoc.

GJDOC              := gjdoc

# doxygen.

DOXYGEN              := doxygen
DOXYGEN_DOC_PATH     := $(DOCS_PATH)/doxygen
DOXYGEN_DEF_FILE     := $(UTIL_PATH)/doxygen.jasl

JNI_DOXYGEN_DOC_PATH := $(DOCS_PATH)/jni-doxygen
JNI_DOXYGEN_DEF_FILE := $(UTIL_PATH)/jni-doxygen.jasl

PY_DOXYGEN_DOC_PATH  := $(DOCS_PATH)/py-doxygen
PY_DOXYGEN_DEF_FILE  := $(UTIL_PATH)/py-doxygen.jasl

# global (gtags and htags)

GTAGS              := gtags
GTAGS_FIND         := find $(PROGRAM_NAME) -type f -name "*.java" | grep -v android
GTAGS_OPTIONS      := -f -
GTAGS_PATH         := $(SRC_PATH)/$(GTAGS)

HTAGS              := htags
HTAGS_DOCS_PATH    := $(DOCS_PATH)/global
HTAGS_TITLE        := "jASL Source Navigation"
HTAGS_OPTIONS      := -h -I -T --tabs 4 -F --title $(HTAGS_TITLE) \
                      -d ${GTAGSDBPATH} $(HTAGS_DOCS_PATH)
HTAGS_OUTPUT_PATH  := $(HTAGS_DOCS_PATH)/HTML
HTAGS_SED_CMD      := $(SED_CONV_CMD) --in-place
HTAGS_FIND_CNV_CMD := find $(HTAGS_OUTPUT_PATH) -type f -name "*.html" \
                      -exec $(HTAGS_SED_CMD) {} \;

HTAGS_ADDLINKS_CMD := $(UTIL_PATH)/addlinks $(HTAGS_OUTPUT_PATH)

## Library and package path and file descriptions.

CNI_PREFIX                   := cni
JNI_PREFIX                   := jni
LIB_PREFIX                   := lib

# CNI (Compiled Native Interface) wrapper.

CNI_HDR_PATH                 := $(INCLUDE_PATH)/$(PROGRAM_NAME)/$(CNI_PREFIX)

CNI_WRAPPER_HDR_FILES        := $(CNI_HDR_PATH)/*.h

CNI_WRAPPER_BASE_LIB_NAME    := $(CNI_DIRECTORY)
CNI_WRAPPER_STATIC_LIB_NAME  := $(LIB_PREFIX)$(CNI_WRAPPER_BASE_LIB_NAME).a
CNI_WRAPPER_STATIC_LIB_PATH  := $(LIB_PATH)/$(CNI_WRAPPER_STATIC_LIB_NAME)

# JNI (Java Native Interface) wrapper.

JNI_GCC_COMPILER             := /usr/bin/gcc

JNI_HDR_PATH                 := $(INCLUDE_PATH)/$(PROGRAM_NAME)/$(JNI_PREFIX)

JNI_WRAPPER_HDR_FILES        := $(JNI_HDR_PATH)/*.h

JNI_WRAPPER_BASE_LIB_NAME    := $(JNI_DIRECTORY)
JNI_WRAPPER_STATIC_LIB_NAME  := $(LIB_PREFIX)$(JNI_WRAPPER_BASE_LIB_NAME).a
JNI_WRAPPER_STATIC_LIB_PATH  := $(LIB_PATH)/$(JNI_WRAPPER_STATIC_LIB_NAME)

JNI_INCLUDE_DIRECTIVES       := -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux
JNI_BUILD_CMD                := $(JNI_GCC_COMPILER) $(GCJ_OPTIMIZE) \
                                -Wall -Wextra \
                                $(JNI_INCLUDE_DIRECTIVES) -std=c++17
JNI_LIBRARY_PATH             := ${JAVA_HOME}/lib/server
JNI_LINK_OPTIONS             := -L$(JNI_LIBRARY_PATH) -ljvm -lstdc++

JNI_LIB_BUILD_CMD            := $(JNI_GCC_COMPILER) $(GCJ_OPTIMIZE) -shared -o

JNI_LD_LIB_PATH              := LD_LIBRARY_PATH=$(LIB_PATH):$(JNI_LIBRARY_PATH)

# counters package.

COUNTERS_PKG_NAME            := counters

COUNTERS_PKG_PATH            := $(PROGRAM_NAME)/$(COUNTERS_PKG_NAME)
COUNTERS_OBJ_PATH            := $(SRC_PATH)/$(COUNTERS_PKG_PATH)
COUNTERS_BIN_PATH            := $(BIN_PATH)/$(COUNTERS_PKG_PATH)
COUNTERS_HDR_PATH            := $(INCLUDE_PATH)/$(COUNTERS_PKG_PATH)

COUNTERS_SRC_FILES           := $(COUNTERS_PKG_PATH)/*.java
COUNTERS_CLASS_FILES         := $(COUNTERS_BIN_PATH)/*.class
COUNTERS_OBJ_FILES           := $(COUNTERS_OBJ_PATH)/$(OBJ_SUB_DIRECTORY)/*.o
COUNTERS_HDR_FILES           := $(COUNTERS_HDR_PATH)/*.h

COUNTERS_BASE_LIB_NAME       := $(PROGRAM_NAME)-$(COUNTERS_PKG_NAME)
COUNTERS_STATIC_LIB_NAME     := $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).a
COUNTERS_STATIC_LIB_PATH     := $(LIB_PATH)/$(COUNTERS_STATIC_LIB_NAME)

# JNI wrapper library for the counters package.

JNI_COUNTERS_HDR_PATH        := $(JNI_HDR_PATH)/$(COUNTERS_PKG_NAME)
JNI_COUNTERS_HDR_FILES       := $(JNI_COUNTERS_HDR_PATH)/*.h

JNI_COUNTERS_BASE_LIB_NAME   := $(JNI_PREFIX)-$(COUNTERS_BASE_LIB_NAME)
JNI_COUNTERS_STATIC_LIB_NAME := $(LIB_PREFIX)$(JNI_COUNTERS_BASE_LIB_NAME).a
JNI_COUNTERS_STATIC_LIB_PATH := $(LIB_PATH)/$(JNI_COUNTERS_STATIC_LIB_NAME)

# utilities package.

UTILITIES_PKG_NAME           := utilities

UTILITIES_PKG_PATH           := $(PROGRAM_NAME)/$(UTILITIES_PKG_NAME)
UTILITIES_OBJ_PATH           := $(SRC_PATH)/$(UTILITIES_PKG_PATH)
UTILITIES_BIN_PATH           := $(BIN_PATH)/$(UTILITIES_PKG_PATH)
UTILITIES_HDR_PATH           := $(INCLUDE_PATH)/$(UTILITIES_PKG_PATH)

UTILITIES_SRC_FILES          := $(UTILITIES_PKG_PATH)/*.java
UTILITIES_CLASS_FILES        := $(UTILITIES_BIN_PATH)/*.class
UTILITIES_OBJ_FILES          := $(UTILITIES_OBJ_PATH)/$(OBJ_SUB_DIRECTORY)/*.o
UTILITIES_HDR_FILES          := $(UTILITIES_HDR_PATH)/*.h

UTILITIES_BASE_LIB_NAME      := $(PROGRAM_NAME)-$(UTILITIES_PKG_NAME)
UTILITIES_STATIC_LIB_NAME    := $(LIB_PREFIX)$(UTILITIES_BASE_LIB_NAME).a
UTILITIES_STATIC_LIB_PATH    := $(LIB_PATH)/$(UTILITIES_STATIC_LIB_NAME)

# JNI wrapper library for the utilities package.

JNI_UTILITIES_HDR_PATH        := $(JNI_HDR_PATH)/$(UTILITIES_PKG_NAME)
JNI_UTILITIES_HDR_FILES       := $(JNI_UTILITIES_HDR_PATH)/*.h

JNI_UTILITIES_BASE_LIB_NAME   := $(JNI_PREFIX)-$(UTILITIES_BASE_LIB_NAME)
JNI_UTILITIES_STATIC_LIB_NAME := $(LIB_PREFIX)$(JNI_UTILITIES_BASE_LIB_NAME).a
JNI_UTILITIES_STATIC_LIB_PATH := $(LIB_PATH)/$(JNI_UTILITIES_STATIC_LIB_NAME)

# ui "package" (really just a parent directory for component packages?)

UI_PKG_NAME                   := ui

# ui.data package.

UI_DATA_PKG_NAME              := data

UI_DATA_PKG_PATH              := $(PROGRAM_NAME)/$(UI_PKG_NAME)/$(UI_DATA_PKG_NAME)
UI_DATA_OBJ_PATH              := $(SRC_PATH)/$(UI_DATA_PKG_PATH)
UI_DATA_BIN_PATH              := $(BIN_PATH)/$(UI_DATA_PKG_PATH)
UI_DATA_HDR_PATH              := $(INCLUDE_PATH)/$(UI_DATA_PKG_PATH)

UI_DATA_SRC_FILES             := $(UI_DATA_PKG_PATH)/*.java
UI_DATA_CLASS_FILES           := $(UI_DATA_BIN_PATH)/*.class
UI_DATA_OBJ_FILES             := $(UI_DATA_OBJ_PATH)/$(OBJ_SUB_DIRECTORY)/*.o
UI_DATA_HDR_FILES             := $(UI_DATA_HDR_PATH)/*.h

UI_DATA_BASE_LIB_NAME         := $(PROGRAM_NAME)-$(UI_PKG_NAME)-$(UI_DATA_PKG_NAME)
UI_DATA_STATIC_LIB_NAME       := $(LIB_PREFIX)$(UI_DATA_BASE_LIB_NAME).a
UI_DATA_STATIC_LIB_PATH       := $(LIB_PATH)/$(UI_DATA_STATIC_LIB_NAME)

ALL_PACKAGES                  := $(COUNTERS_PKG_PATH) $(UTILITIES_PKG_PATH) \
                                 $(UI_DATA_PKG_PATH)

# org "package" (really just a parent directory for external packages?)

ORG_PKG_NAME                  := org

# org.json package.

ORG_JSON_PKG_NAME             := json

ORG_JSON_PKG_PATH             := $(ORG_PKG_NAME)/$(ORG_JSON_PKG_NAME)
ORG_JSON_OBJ_PATH             := $(SRC_PATH)/$(ORG_JSON_PKG_PATH)
ORG_JSON_BIN_PATH             := $(BIN_PATH)/$(ORG_JSON_PKG_PATH)
ORG_JSON_INCLUDE_PATH         := $(INCLUDE_PATH)/$(ORG_JSON_PKG_PATH)
ORG_JSON_SRC_PATH             := $(ORG_JSON_PKG_PATH)/src/main/java/$(ORG_JSON_PKG_PATH)

ORG_JSON_SRC_FILES            := $(ORG_JSON_SRC_PATH)/*.java
ORG_JSON_CLASS_FILES          := $(ORG_JSON_BIN_PATH)/*.class
ORG_JSON_OBJ_FILES            := $(ORG_JSON_OBJ_PATH)/$(OBJ_SUB_DIRECTORY)/*.o
ORG_JSON_HDR_FILES            := $(ORG_JSON_INCLUDE_PATH)/*.h

ORG_JSON_BASE_LIB_NAME        := $(ORG_PKG_NAME)-$(ORG_JSON_PKG_NAME)
ORG_JSON_STATIC_LIB_NAME      := $(LIB_PREFIX)$(ORG_JSON_BASE_LIB_NAME).a
ORG_JSON_STATIC_LIB_PATH      := $(LIB_PATH)/$(ORG_JSON_STATIC_LIB_NAME)

## Scenarios

SCENARIOS_DIRECTORY           := scenarios
SCENARIO_FILES                := *.json

SCENARIOS_DATA_PATH           := $(DATA_PATH)/$(SCENARIOS_DIRECTORY)
SCENARIO_DATA_FILES           := $(SCENARIOS_DATA_PATH)/$(SCENARIO_FILES)

SCENARIOS_SRC_PATH            := $(SRC_PATH)/$(SCENARIOS_DIRECTORY)
SCENARIO_SRC_FILES            := $(SCENARIOS_SRC_PATH)/$(SCENARIO_FILES)

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

.PHONY : obj_sub_directory
obj_sub_directory:
	$(INSTALL_DIR) $(OBJ_SUB_DIRECTORY)

## Build variables used in generating wrapper libraries with SWIG.

# Command and target language specific options.

SWIG_CMD := swig -c++
SWIG_PERL_CMD := $(SWIG_CMD) -perl -outdir $(PERL_BIN_PATH)
SWIG_PYTHON_CMD := $(SWIG_CMD) -python -shadow -outdir $(PYTHON_BIN_PATH)

# GCC compile commands with options specific to each target language.

GCC_PERL_COMPILE_CMD   := $(GCC_COMPILE_CMD) -I/usr/lib/x86_64-linux-gnu/perl/5.32.1/CORE
GCC_PYTHON_COMPILE_CMD := $(GCC_COMPILE_CMD) -I/usr/include/python3.9

# GCC build/link options common to all libraries and target languages.

GCC_COMMON_BUILD_OPTIONS := -lgcj -lstdc++ -L$(LIB_PATH)

# Destination directories of the module files associated with the libraries for
# each scripting language. The files are actually generated in sub-directories
# of the swig directory, but the targets are defined here for use in installing
# the scripts that use the libraries.

.PHONY : perl_bin_directory
perl_bin_directory:
	$(INSTALL_DIR) $(PERL_BIN_PATH)

.PHONY : python_bin_directory
python_bin_directory:
	$(INSTALL_DIR) $(PYTHON_BIN_PATH)

# Output directories for the Perl and Python libraries. The targets for the
# language module files, which are stored in sub-directories of bin, are defined
# above.

.PHONY : perl_lib_directory
perl_lib_directory:
	$(INSTALL_DIR) $(PERL_LIB_PATH)

.PHONY : python_lib_directory
python_lib_directory:
	$(INSTALL_DIR) $(PYTHON_LIB_PATH)

# Python scripts / implementation

PYCACHE_SUB_DIRECTORY := __pycache__
