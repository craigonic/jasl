################################################################################
# Makefile - This make parameter file is used to build JNI wrapper library for #
#            the counters package of the jASL program. It also generates the   #
#            documentation.                                                    #
#                                                                              #
# Written By: Craig R. Campbell  -  January 2025                               #
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

ENUM_FILES        := Descriptions_p.cpp Nationalities_p.cpp

CPP_FILES         := $(ENUM_FILES) Unit_p.cpp

HDR_FILES         := ${CPP_FILES:.cpp=.h}
OBJ_FILES         := ${CPP_FILES:.cpp=.o}
SRC_FILES         := $(HDR_FILES) $(CPP_FILES)

# Build all of the individual object files.

%.o: %.cpp
	$(JNI_BUILD_CMD) -I$(JNI_HDR_PATH) -c $^ -o $(OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: obj_sub_directory $(OBJ_FILES)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(JNI_COUNTERS_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).5

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

JNI_COUNTERS_OBJ_FILES := $(OBJ_SUB_DIRECTORY)/*.o

.PHONY : libs
libs: dynamic_lib static_lib clean

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(JNI_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) $(JNI_COUNTERS_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(JNI_COUNTERS_STATIC_LIB_PATH) $(JNI_COUNTERS_OBJ_FILES)

# Remove the .obj sub-directory and its contents.

.PHONY : clean
clean:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(JNI_COUNTERS_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

JNI_COUNTERS_PRIVATE_DOCS_PATH := $(SRC_DOCS_PATH)/$(JNI_DIRECTORY)/$(COUNTERS_PKG_PATH)/private

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JNI_COUNTERS_PRIVATE_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JNI_COUNTERS_PRIVATE_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(SRC_FILES) ; do \
		if [ -f $$i ]; then \
			$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(JNI_COUNTERS_PRIVATE_DOCS_PATH)/$$i.html ;\
			if [ -d $(JNI_DOXYGEN_DOC_PATH)/html ]; then \
				(cd $(JNI_DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(JNI_DIRECTORY)/$(COUNTERS_PKG_PATH)/private/$$i.html) ;\
			fi \
		fi \
	done

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JNI_COUNTERS_PRIVATE_DOCS_PATH)
	@$(HTLS) $(JNI_COUNTERS_PRIVATE_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(JNI_COUNTERS_PRIVATE_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
