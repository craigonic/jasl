################################################################################
# jasl.make - This make parameter file is used to define constants used by all #
#             of the Makefiles in the source hierarchy. All paths are relative #
#             to JASL_HOME, which is a required environment variable that      #
#             should refer to the topmost directory named jasl.                #
#                                                                              #
# Written By : Craig R. Campbell  -  November 2002                             #
#                                                                              #
# $Id: jasl.make,v 1.3 2006/10/18 22:50:57 campbell Exp $
################################################################################

## Directory definitions.

CLASSPATH         = ${JASL_BASE}/jasl
DOCS_PATH         = ${JASL_BASE}/docs
SRC_PATH          = ${JASL_BASE}/source
UTIL_PATH         = ${JASL_BASE}/util

## Java and gcj compiler related settings.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

CLASSPATH_CMD     = -classpath $(CLASSPATH)
GCJ_CLASSPATH_CMD = --CLASSPATH=$(CLASSPATH)
OUTPUT_DIR_CMD    = -d $(CLASSPATH)

JAVA_OPTIMIZE     = -O
GCJ_OPTIMIZE      = -O3 -s -pipe

JAVA_OPTIONS      = $(JAVA_COMPILER) $(JAVA_OPTIMIZE) $(OUTPUT_DIR_CMD)
GCJ_BUILD_CMD     = $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD) $(GCJ_OPTIMIZE)
GCJ_COMPILE_CMD   = $(GCJ_BUILD_CMD) -c

## Documentation build variables.

# Linked source files.

SRC_DOCS_PATH     = $(DOCS_PATH)/source
HTLS              = $(UTIL_PATH)/htls -genpage -index
JAVA2HTML         = source-highlight --src-lang=java --doc --tab=4 --ctags=''
SED_CONV_CMD      = sed -f $(UTIL_PATH)/htmlconv

# Javadoc.

JAVADOC_COMPILER  = javadoc
JAVADOCS_PATH     = -d $(DOCS_PATH)/javadocs
JAVADOCS_LINK     = -link http://java.sun.com/j2se/1.4.2/docs/api
JAVADOC_OPTIONS   = -version -author -package -nodeprecatedlist -use \
                    -breakiterator
JAVADOC_TITLE     = -windowtitle "jASL Programming Documentation"
JAVADOC_CMD_LIST  = $(JAVADOCS_PATH) $(JAVADOCS_LINK) $(JAVADOC_OPTIONS) \
                    $(JAVADOC_TITLE)

# gjdoc.

GJDOC             = gjdoc

# Doxygen.

DOXYGEN          = doxygen
DOXYGEN_DEF_FILE = $(UTIL_PATH)/doxygen.jasl

## Packages.

COUNTERS_PACKAGE  = Counters

## System commands.

CAT               = /bin/cat
MV                = /bin/mv
RM                = /bin/rm -f
