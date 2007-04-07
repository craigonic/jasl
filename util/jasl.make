################################################################################
# jasl.make - This make parameter file is used to define constants used by all #
#             of the Makefiles in the source hierarchy. All paths are relative #
#             to JASL_BASE, which is a required environment variable that must #
#             refer to the topmost directory named jasl.                       #
#                                                                              #
# Written By : Craig R. Campbell  -  November 2002                             #
#                                                                              #
# $Id: jasl.make,v 1.6 2007/04/07 05:34:12 craig Exp $
################################################################################

## Program name.

PROGRAM_NAME      = jasl

## Directory definitions.

# Note that if the installation directory is changed from ${JASL_BASE} it will
# be necessary to change the OUTPUT_DIRECTORY setting in doxygen.jasl to the
# expanded version of ${DOXYGEN_DOC_PATH} (see below) in order for the files to
# be installed correctly.

INSTALL_DIRECTORY = ${JASL_BASE}

BIN_PATH          = ${INSTALL_DIRECTORY}/bin
DOCS_PATH         = ${INSTALL_DIRECTORY}/share/${PROGRAM_NAME}/doc
INCLUDE_PATH      = ${INSTALL_DIRECTORY}/include
LIB_PATH          = ${INSTALL_DIRECTORY}/lib

SRC_PATH          = ${JASL_BASE}/source
UTIL_PATH         = ${JASL_BASE}/util

## Java and gcj compiler related settings.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

CLASSPATH_CMD     = -classpath $(BIN_PATH)
GCJ_CLASSPATH_CMD = --classpath=$(BIN_PATH)
OUTPUT_DIR_CMD    = -d $(BIN_PATH)

JAVA_OPTIMIZE     = -O
GCJ_OPTIMIZE      = -O3 -s -pipe

JAVA_OPTIONS      = $(JAVA_COMPILER) $(JAVA_OPTIMIZE) $(OUTPUT_DIR_CMD) \
                    $(CLASSPATH_CMD)
GCJ_BUILD_CMD     = $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD) $(GCJ_OPTIMIZE)
GCJ_COMPILE_CMD   = $(GCJ_BUILD_CMD) -c

GCJH_CMD          = gcjh -force

GCJ_LIB_BUILD_CMD = $(GCJ_COMPILER) $(GCJ_OPTIMIZE) -shared -o

## Java runtime program.

JAVA_RUN_CMD      = java

## jar.

JAR_BUILD_CMD     = jar cmf
JAR_RUN_CMD       = $(JAVA_RUN_CMD) -jar

## ctags.

CTAGS_BUILD_CMD   = /usr/bin/ctags --file-tags=yes -R --excmd=number

## Documentation build variables.

# Linked source files.

SRC_DOCS_PATH     = $(DOCS_PATH)/source
HTLS              = $(UTIL_PATH)/htls -genpage -index
JAVA2HTML         = source-highlight --src-lang=java --doc --tab=4 --ctags=''
SED_CONV_CMD      = sed -f $(UTIL_PATH)/htmlconv

# javadoc.

JAVADOC_COMPILER  = javadoc
JAVADOCS_PATH     = $(DOCS_PATH)/javadocs
JAVADOCS_LINK     = -link http://java.sun.com/javase/6/docs/api
JAVADOC_OPTIONS   = -version -author -package -nodeprecatedlist -use \
                    -breakiterator
JAVADOC_TITLE     = -windowtitle "jASL Programming Documentation"
JAVADOC_CMD_LIST  = -d $(JAVADOCS_PATH) $(JAVADOCS_LINK) $(JAVADOC_OPTIONS) \
                    $(JAVADOC_TITLE)

# gjdoc.

GJDOC             = gjdoc

# doxygen.

DOXYGEN          = doxygen
DOXYGEN_DOC_PATH = $(DOCS_PATH)/doxygen
DOXYGEN_DEF_FILE = $(UTIL_PATH)/doxygen.jasl

## Package path and file descriptions.

LIB_PREFIX               = lib

COUNTERS_PKG_NAME        = Counters

COUNTERS_PKG_PATH        = $(PROGRAM_NAME)/$(COUNTERS_PKG_NAME)
COUNTERS_OBJ_PATH        = ${JASL_BASE}/bin/$(COUNTERS_PKG_PATH)

COUNTERS_SRC_FILES       = $(COUNTERS_PKG_PATH)/*.java
COUNTERS_OBJ_FILES       = $(COUNTERS_OBJ_PATH)/*.o

COUNTERS_BASE_LIB_NAME   = $(PROGRAM_NAME)-$(COUNTERS_PKG_NAME)
COUNTERS_STATIC_LIB_NAME = $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).a
COUNTERS_STATIC_LIB_PATH = $(LIB_PATH)/$(COUNTERS_STATIC_LIB_NAME)

ALL_PACKAGES             = $(COUNTERS_PKG_PATH)

## Miscellaneous programs.

AR               = /usr/bin/ar rcs
CAT              = /bin/cat
DATE             = /bin/date
DIFF             = /usr/bin/diff
LN               = /bin/ln -sf
MAKE             = /usr/bin/make
MV               = /bin/mv
RM               = /bin/rm -f
RM_RECURSIVE     = $(RM) -r
