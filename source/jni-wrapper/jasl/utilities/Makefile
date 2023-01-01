################################################################################
# Makefile - This make parameter file is used to build JNI wrapper library for #
#            the utilities package of the jASL program. It also installs the   #
#            header files and generates the documentation.                     #
#                                                                              #
# Written By: Craig R. Campbell  -  June 2011                                  #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : libs

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source, object, and header files in/of/for
# this package.

CPP_FILES := Dice.cpp

HDR_FILES := ${CPP_FILES:.cpp=.h}
OBJ_FILES := ${CPP_FILES:.cpp=.o}
SRC_FILES := $(HDR_FILES) $(CPP_FILES)

# Build all of the individual object files.

%.o: %.cpp
	$(JNI_BUILD_CMD) -I$(INCLUDE_PATH) -c $^ -o $(OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: obj_sub_directory $(OBJ_FILES)

# Install the header (.h) files in the appropriate include directory.

.PHONY : hdr
hdr: $(HDR_FILES)
	$(INSTALL_DIR) $(JNI_UTILITIES_HDR_PATH)
	$(INSTALL_STD_FILE) $(HDR_FILES) $(JNI_UTILITIES_HDR_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(JNI_UTILITIES_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).2

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

JNI_UTILITIES_OBJ_FILES := $(OBJ_SUB_DIRECTORY)/*.o

.PHONY : libs
libs: dynamic_lib static_lib clean

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(JNI_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) $(JNI_UTILITIES_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(JNI_UTILITIES_STATIC_LIB_PATH) $(JNI_UTILITIES_OBJ_FILES)

# Remove the .obj sub-directory and its contents.

.PHONY : clean
clean:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove the include output directory and its contents.

.PHONY : clean_hdr
clean_hdr:
	$(RM_RECURSIVE) $(JNI_UTILITIES_HDR_PATH)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(JNI_UTILITIES_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

JNI_UTILITIES_DOCS_PATH := $(SRC_DOCS_PATH)/$(JNI_DIRECTORY)/$(UTILITIES_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JNI_UTILITIES_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JNI_UTILITIES_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(SRC_FILES) ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(JNI_UTILITIES_DOCS_PATH)/$$i.html ;\
	done

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JNI_UTILITIES_DOCS_PATH)
	@$(HTLS) $(JNI_UTILITIES_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(JNI_UTILITIES_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
