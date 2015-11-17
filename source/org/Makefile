################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the org.json package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler and use them to create shared and static libraries.      #
#                                                                              #
# Written By : Craig R. Campbell  -  November 2015                             #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : all

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source, object, and class files in/of/for
# this package.

ORG_JSON_SRC_FILES    := JSONException.java JSONTokener.java JSONObject.java \
                         JSONStringer.java JSONString.java JSONWriter.java   \
                         JSONArray.java

ORG_JSON_OBJ_FILES    := ${ORG_JSON_SRC_FILES:.java=.o}
ORG_JSON_CLASS_FILES  := ${ORG_JSON_SRC_FILES:.java=.class}

ORG_JSON_PATH         := org/json
ORG_JSON_BIN_PATH     := $(BIN_PATH)/$(ORG_JSON_PATH)
ORG_JSON_INCLUDE_PATH := $(INCLUDE_PATH)/$(ORG_JSON_PATH)

# Build all of the individual class files.

.PHONY : all
all: bin_directory
	@(cd json;$(JAVA_OPTIONS) $(ORG_JSON_SRC_FILES))

# Build all of the individual object files.

%.o: $(ORG_JSON_BIN_PATH)/%.class
	$(GCJ_COMPILE_CMD) $^ -o $(OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: obj_sub_directory $(ORG_JSON_OBJ_FILES)

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(ORG_JSON_BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)org-json.so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).1
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

STATIC_LIB_FILENAME                := ${DYNAMIC_LIB_NO_VERSION_LINKNAME:.so=.a}

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

LOCAL_OBJ_FILES := $(OBJ_SUB_DIRECTORY)/*.o

.PHONY : libs
libs: dynamic_lib static_lib clean_obj

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(GCJ_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(LOCAL_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(LIB_PATH)/$(STATIC_LIB_FILENAME) $(LOCAL_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(ORG_JSON_BIN_PATH)/*.class

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(ORG_JSON_INCLUDE_PATH)/*.h

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(LIB_PATH)/$(STATIC_LIB_FILENAME)