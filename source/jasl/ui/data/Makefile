################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the ui.data package of the jASL program. It also        #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be re-created.                             #
#                                                                              #
# Written By:  Craig R. Campbell  -  February 2013                             #
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

GAME_CLASS           := Game.class
GAME_OBJ             := ${GAME_CLASS:.class=.o}
GAME_SRC             := ${GAME_CLASS:.class=.java}

SIDE_CLASS           := Side.class
SIDE_OBJ             := ${SIDE_CLASS:.class=.o}
SIDE_SRC             := ${SIDE_CLASS:.class=.java}

# This file is generated when Side.java is compiled and represents the Sides
# enum defined in that class. The following definitions are used to generate the
# object file.

SIDES_CLASS          := Side\$$Sides.class
SIDES_OBJ            := ${SIDES_CLASS:.class=.o}

PLAYER_CLASS         := Player.class
PLAYER_OBJ           := ${PLAYER_CLASS:.class=.o}
PLAYER_SRC           := ${PLAYER_CLASS:.class=.java}

HEX_CLASS            := Hex.class
HEX_OBJ              := ${HEX_CLASS:.class=.o}
HEX_SRC              := ${HEX_CLASS:.class=.java}

ALL_CLASSES          := $(GAME_CLASS) $(SIDE_CLASS) $(PLAYER_CLASS) $(HEX_CLASS)

GCJ_OBJECT_FILES     := ${ALL_CLASSES:.class=.o}

JAVA_CLASSES         := ${ALL_CLASSES:.class=}

# Build all of the individual class files.

.PHONY : all
all: $(ALL_CLASSES)

# Compile the individual Java classes in this package.

$(GAME_CLASS): $(SIDE_CLASS)
	$(JAVA_OPTIONS) $(GAME_SRC)

$(SIDE_CLASS): $(PLAYER_CLASS)
	$(JAVA_OPTIONS) $(SIDE_SRC)

$(PLAYER_CLASS): $(HEX_CLASS)
	$(JAVA_OPTIONS) $(PLAYER_SRC)

$(HEX_CLASS): bin_directory
	$(JAVA_OPTIONS) $(HEX_SRC)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(GAME_OBJ): $(GAME_CLASS)
	$(GCJ_COMPILE_CMD) $(UI_DATA_BIN_PATH)/$(GAME_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(GAME_OBJ)

$(SIDE_OBJ): $(SIDE_CLASS)
	$(GCJ_COMPILE_CMD) $(UI_DATA_BIN_PATH)/$(SIDE_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(SIDE_OBJ)
	$(GCJ_COMPILE_CMD) $(UI_DATA_BIN_PATH)/$(SIDES_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(SIDES_OBJ)

$(PLAYER_OBJ): $(PLAYER_CLASS)
	$(GCJ_COMPILE_CMD) $(UI_DATA_BIN_PATH)/$(PLAYER_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(PLAYER_OBJ)

$(HEX_OBJ): $(HEX_CLASS)
	$(GCJ_COMPILE_CMD) $(UI_DATA_BIN_PATH)/$(HEX_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(HEX_OBJ)

# Build all of the individual object files.

.PHONY : obj
obj: obj_sub_directory $(GCJ_OBJECT_FILES)

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(UI_DATA_BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(UI_DATA_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).2

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
	$(AR) $(UI_DATA_STATIC_LIB_PATH) $(LOCAL_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(UI_DATA_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(UI_DATA_HDR_FILES)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(UI_DATA_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. Symbolic links are also created to each file from the doxygen
# documentation output directory. This is done so that the references in the
# doxygen files to the files generated by this target will work. After the files
# are generated, the htls program is used to link the files together on an HTML
# page.

UI_DATA_DOCS_PATH := $(SRC_DOCS_PATH)/$(UI_DATA_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(UI_DATA_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(UI_DATA_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(UI_DATA_DOCS_PATH)/$$i.html ;\
		if [ -d $(DOXYGEN_DOC_PATH)/html ]; then \
			(cd $(DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(UI_DATA_PKG_PATH)/$$i.html) ;\
		fi \
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(UI_DATA_DOCS_PATH)
	@$(HTLS) $(UI_DATA_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(UI_DATA_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
