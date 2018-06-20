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

ORG_JSON_SRC_FILES     := JSONPointerException.java JSONPointer.java       \
                          JSONArray.java JSONPropertyIgnore.java           \
                          JSONPropertyName.java JSONObject.java            \
                          JSONStringer.java JSONTokener.java               \
                          JSONException.java JSONString.java JSONWriter.java

GCJ_OBJECT_FILES       := ${ORG_JSON_SRC_FILES:.java=.o}

ORG_JSON_JAR_ARCHIVE   := org.json.jar

# Build all of the individual class files.

.PHONY : all
all: bin_directory
	@(cd json;$(JAVA_OPTIONS) $(ORG_JSON_SRC_FILES))

# Create a jar archive containing all of the .class files generated from the
# source files selected from the json sub-directory (ORG_JSON_SRC_FILES).

.PHONY : jar
jar: all
	@(cd $(BIN_PATH);jar cf $(ORG_JSON_JAR_ARCHIVE) $(ORG_JSON_PKG_PATH))

# Build all of the individual object files.

JSON_OBJ_SUB_DIRECTORY := $(ORG_JSON_PKG_NAME)/$(OBJ_SUB_DIRECTORY)

.PHONY : json_obj_sub_directory
json_obj_sub_directory:
	$(INSTALL_DIR) $(JSON_OBJ_SUB_DIRECTORY)

%.o: $(ORG_JSON_BIN_PATH)/%.class
	$(GCJ_COMPILE_CMD) $^ -o $(JSON_OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: json_obj_sub_directory $(GCJ_OBJECT_FILES)
	$(GCJ_COMPILE_CMD) $(ORG_JSON_BIN_PATH)/JSONObject\$$1.class \
	                   -o $(JSON_OBJ_SUB_DIRECTORY)/JSONObject\$$1.o
	$(GCJ_COMPILE_CMD) $(ORG_JSON_BIN_PATH)/JSONObject\$$Null.class \
	                   -o $(JSON_OBJ_SUB_DIRECTORY)/JSONObject\$$Null.o
	$(GCJ_COMPILE_CMD) $(ORG_JSON_BIN_PATH)/JSONPointer\$$Builder.class \
	                   -o $(JSON_OBJ_SUB_DIRECTORY)/JSONPointer\$$Builder.o

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(ORG_JSON_BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(ORG_JSON_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).1
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

STATIC_LIB_FILENAME                := ${DYNAMIC_LIB_NO_VERSION_LINKNAME:.so=.a}

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

LOCAL_OBJ_FILES := $(JSON_OBJ_SUB_DIRECTORY)/*.o

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
	$(RM) $(ORG_JSON_CLASS_FILES)

.PHONY : clean_jar
clean_jar:
	$(RM) $(BIN_PATH)/$(ORG_JSON_JAR_ARCHIVE)

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(JSON_OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(ORG_JSON_HDR_FILES)

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

# Create an HTML version of this file. It is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the file is generated, the htls program is used to link it
# to the files in the sub-directories on an HTML page.

ORG_JSON_DOCS_PATH := $(SRC_DOCS_PATH)/org

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(ORG_JSON_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(ORG_JSON_DOCS_PATH)/$(MAKEFILE).html

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(ORG_JSON_DOCS_PATH)
	@$(HTLS) $(ORG_JSON_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(ORG_JSON_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs

# A reference implementation for using JSON in Java must be "cloned" into the
# json sub-directory. The following targets are used to get the code and refresh
# the local copy of the (git) repository.

.PHONY : clone
clone:
	@git clone https://github.com/douglascrockford/JSON-java.git json

.PHONY : refresh
refresh:
	@(cd json;git pull)
