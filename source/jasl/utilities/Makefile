################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the utilities package of the jASL program. It also      #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be re-created.                             #
#                                                                              #
# Written By:  Craig R. Campbell  -  January 2009                              #
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

JASL_UTILITIES_SRC_FILES := Messages.java Dice.java Serialization.java \
                            JsonData.java

GCJ_OBJECT_FILES         := ${JASL_UTILITIES_SRC_FILES:.java=.o}

JAVA_CLASSES             := ${JASL_UTILITIES_SRC_FILES:.java=}

# Build all of the individual class files.

%.class: %.java
	$(JAVA_OPTIONS) $^

.PHONY : all
all: bin_directory
	$(JAVA_OPTIONS) $(JASL_UTILITIES_SRC_FILES)

# Build all of the individual object files.

%.o: %.class
	$(GCJ_COMPILE_CMD) $(UTILITIES_BIN_PATH)/$^ -o $(OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: bin_directory obj_sub_directory $(GCJ_OBJECT_FILES)
	$(GCJ_COMPILE_CMD) $(UTILITIES_BIN_PATH)/JsonData\$$JsonOutput.class \
	                   -o $(OBJ_SUB_DIRECTORY)/JsonData\$$JsonOutput.o

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(UTILITIES_BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(UTILITIES_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).5
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

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
	$(AR) $(UTILITIES_STATIC_LIB_PATH) $(LOCAL_OBJ_FILES)

# Remove all of the .class and/or object files from the their respective output
# directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(UTILITIES_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(UTILITIES_HDR_FILES)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(UTILITIES_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. Symbolic links are also created to each file from the doxygen
# documentation output directory. This is done so that the references in the
# doxygen files to the files generated by this target will work. After the files
# are generated, the htls program is used to link the files together on an HTML
# page.

UTILITIES_DOCS_PATH := $(SRC_DOCS_PATH)/$(UTILITIES_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(UTILITIES_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(UTILITIES_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(UTILITIES_DOCS_PATH)/$$i.html ;\
		if [ -d $(DOXYGEN_DOC_PATH)/html ]; then \
			(cd $(DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(UTILITIES_PKG_PATH)/$$i.html) ;\
		fi \
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(UTILITIES_DOCS_PATH)
	@$(HTLS) $(UTILITIES_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(UTILITIES_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
