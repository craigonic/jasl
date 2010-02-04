################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the utilities package of the jASL program. It also      #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be re-created.                             #
#                                                                              #
# Written By : Craig R. Campbell  -  January 2009                              #
#                                                                              #
# $Id: Makefile,v 1.4 2010/02/04 21:22:26 campbell Exp $
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

MESSAGES_CLASS       := Messages.class
MESSAGES_EXEC        := ${MESSAGES_CLASS:.class=}
MESSAGES_HDR         := ${MESSAGES_CLASS:.class=.h}
MESSAGES_OBJ         := ${MESSAGES_CLASS:.class=.o}
MESSAGES_SRC         := ${MESSAGES_CLASS:.class=.java}

DICE_CLASS           := Dice.class
DICE_EXEC            := ${DICE_CLASS:.class=}
DICE_HDR             := ${DICE_CLASS:.class=.h}
DICE_OBJ             := ${DICE_CLASS:.class=.o}
DICE_SRC             := ${DICE_CLASS:.class=.java}

ALL_CLASSES          := $(MESSAGES_CLASS) $(DICE_CLASS)

GCJ_HEADER_FILES     := ${ALL_CLASSES:.class=.h}

GCJ_OBJECT_FILES     := ${ALL_CLASSES:.class=.o}

JAVA_SRC_FILES       := ${ALL_CLASSES:.class=.java}

JAVA_CLASSES         := ${ALL_CLASSES:.class=}

# Build all of the individual class files.

.PHONY : all
all: $(ALL_CLASSES)

# Compile the individual Java classes in this package.

$(MESSAGES_CLASS): bin_directory
	$(JAVA_OPTIONS) $(MESSAGES_SRC)

$(DICE_CLASS): $(MESSAGES_CLASS)
	$(JAVA_OPTIONS) $(DICE_SRC)

# Build all of the individual object files.

.PHONY : obj
obj: $(GCJ_OBJECT_FILES)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(MESSAGES_OBJ):
	$(GCJ_COMPILE_CMD) $(MESSAGES_SRC)
	$(INSTALL_DIR) $(UTILITIES_OBJ_PATH)
	$(MV) $(MESSAGES_OBJ) $(UTILITIES_OBJ_PATH)

$(DICE_OBJ): $(MESSAGES_CLASS)
	$(GCJ_COMPILE_CMD) $(DICE_SRC)
	$(INSTALL_DIR) $(UTILITIES_OBJ_PATH)
	$(MV) $(DICE_OBJ) $(UTILITIES_OBJ_PATH)

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory $(GCJ_HEADER_FILES)
#	$(GJAVAH_CMD) $(UTILITIES_BIN_PATH)

# Using the installed .class files, build the header file that defines each one
# and install it in the include output directory.

GCJH_BUILD_CMD := $(GCJH_CMD) $(GCJ_CLASSPATH_CMD)/$(UTILITIES_PKG_PATH) \
                  -d $(UTILITIES_HDR_PATH)

$(MESSAGES_HDR): $(MESSAGES_CLASS)
	$(GCJH_BUILD_CMD) $(MESSAGES_EXEC)

$(DICE_HDR): $(DICE_CLASS)
	$(GCJH_BUILD_CMD) $(DICE_EXEC)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(UTILITIES_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).1

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

.PHONY : libs
libs: dynamic_lib static_lib clean_obj

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(GCJ_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(UTILITIES_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(UTILITIES_STATIC_LIB_PATH) $(UTILITIES_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(UTILITIES_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM) $(UTILITIES_OBJ_FILES)

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
HTLS_CFG_FILE       := $(UTILITIES_PKG_NAME).cfg

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
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(HTLS_CFG_FILE) \
	                     $(UTILITIES_DOCS_PATH)
	@$(HTLS) $(UTILITIES_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(UTILITIES_DOCS_PATH)/$(HTLS_HEADER_FILE)
	@$(RM) $(UTILITIES_DOCS_PATH)/$(HTLS_CFG_FILE)

.PHONY : docs
docs: docs_all clean_docs
