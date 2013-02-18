################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be re-created.                             #
#                                                                              #
# Written By : Craig R. Campbell  -  June 1999                                 #
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

TEXT_OUTPUT_CLASS    := TextOutput.class
TEXT_OUTPUT_OBJ      := ${TEXT_OUTPUT_CLASS:.class=.o}
TEXT_OUTPUT_SRC      := ${TEXT_OUTPUT_CLASS:.class=.java}

DESCRIPTION_CLASS    := Description.class
DESCRIPTION_OBJ      := ${DESCRIPTION_CLASS:.class=.o}
DESCRIPTION_SRC      := ${DESCRIPTION_CLASS:.class=.java}

# This file is generated when Description.java is compiled and represents the
# Descriptions enum defined in that class. The following definitions are used to
# generate the object file.

DESCRIPTIONS_CLASS   := Description\$$Descriptions.class
DESCRIPTIONS_OBJ     := ${DESCRIPTIONS_CLASS:.class=.o}

NATIONALITY_CLASS    := Nationality.class
NATIONALITY_OBJ      := ${NATIONALITY_CLASS:.class=.o}
NATIONALITY_SRC      := ${NATIONALITY_CLASS:.class=.java}

UNIT_TYPE_CLASS      := UnitType.class
UNIT_TYPE_OBJ        := ${UNIT_TYPE_CLASS:.class=.o}
UNIT_TYPE_SRC        := ${UNIT_TYPE_CLASS:.class=.java}

CLASSIFICATION_CLASS := Classification.class
CLASSIFICATION_OBJ   := ${CLASSIFICATION_CLASS:.class=.o}
CLASSIFICATION_SRC   := ${CLASSIFICATION_CLASS:.class=.java}

UNIT_CLASS           := Unit.class
UNIT_OBJ             := ${UNIT_CLASS:.class=.o}
UNIT_SRC             := ${UNIT_CLASS:.class=.java}

FIGHTING_CLASS       := Fighting.class
FIGHTING_OBJ         := ${FIGHTING_CLASS:.class=.o}
FIGHTING_SRC         := ${FIGHTING_CLASS:.class=.java}

MOBILE_CLASS         := Mobile.class
MOBILE_OBJ           := ${MOBILE_CLASS:.class=.o}
MOBILE_SRC           := ${MOBILE_CLASS:.class=.java}

INFANTRY_CLASS       := Infantry.class
INFANTRY_OBJ         := ${INFANTRY_CLASS:.class=.o}
INFANTRY_SRC         := ${INFANTRY_CLASS:.class=.java}

PERSONNEL_CLASS      := Personnel.class
PERSONNEL_OBJ        := ${PERSONNEL_CLASS:.class=.o}
PERSONNEL_SRC        := ${PERSONNEL_CLASS:.class=.java}

SQUAD_CLASS          := Squad.class
SQUAD_OBJ            := ${SQUAD_CLASS:.class=.o}
SQUAD_SRC            := ${SQUAD_CLASS:.class=.java}

LEADER_CLASS         := Leader.class
LEADER_OBJ           := ${LEADER_CLASS:.class=.o}
LEADER_SRC           := ${LEADER_CLASS:.class=.java}

INTERFACES           := $(TEXT_OUTPUT_CLASS) $(DESCRIPTION_CLASS) \
                        $(NATIONALITY_CLASS) $(UNIT_TYPE_CLASS) \
                        $(CLASSIFICATION_CLASS)
ENUMS                := $(DESCRIPTIONS_CLASS)
SUPER_CLASSES        := $(UNIT_CLASS) $(FIGHTING_CLASS) $(MOBILE_CLASS)
INF_CLASSES          := $(INFANTRY_CLASS) $(PERSONNEL_CLASS) $(SQUAD_CLASS) \
                        $(LEADER_CLASS)

ALL_CLASSES          := $(INTERFACES) $(SUPER_CLASSES) $(INF_CLASSES)

GCJ_OBJECT_FILES     := ${ALL_CLASSES:.class=.o}

JAVA_CLASSES         := ${ALL_CLASSES:.class=}

# Build all of the individual class files.

.PHONY : all
all: $(ALL_CLASSES)

# Compile the individual Java classes in this package.

# Interfaces.

$(TEXT_OUTPUT_CLASS): bin_directory
	$(JAVA_OPTIONS) $(TEXT_OUTPUT_SRC)

$(DESCRIPTION_CLASS): bin_directory
	$(JAVA_OPTIONS) $(DESCRIPTION_SRC)

$(NATIONALITY_CLASS): bin_directory
	$(JAVA_OPTIONS) $(NATIONALITY_SRC)

$(UNIT_TYPE_CLASS): bin_directory
	$(JAVA_OPTIONS) $(UNIT_TYPE_SRC)

$(CLASSIFICATION_CLASS): bin_directory
	$(JAVA_OPTIONS) $(CLASSIFICATION_SRC)

# Object classes.

$(UNIT_CLASS): $(TEXT_OUTPUT_CLASS) $(DESCRIPTION_CLASS)
	$(JAVA_OPTIONS) $(UNIT_SRC)

$(FIGHTING_CLASS): $(UNIT_CLASS) $(NATIONALITY_CLASS) $(UNIT_TYPE_CLASS)
	$(JAVA_OPTIONS) $(FIGHTING_SRC)

$(MOBILE_CLASS): $(FIGHTING_CLASS)
	$(JAVA_OPTIONS) $(MOBILE_SRC)

$(INFANTRY_CLASS): $(MOBILE_CLASS)
	$(JAVA_OPTIONS) $(INFANTRY_SRC)

$(PERSONNEL_CLASS): $(INFANTRY_CLASS) $(CLASSIFICATION_CLASS)
	$(JAVA_OPTIONS) $(PERSONNEL_SRC)

$(SQUAD_CLASS): $(PERSONNEL_CLASS)
	$(JAVA_OPTIONS) $(SQUAD_SRC)

$(LEADER_CLASS): $(INFANTRY_CLASS)
	$(JAVA_OPTIONS) $(LEADER_SRC)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(TEXT_OUTPUT_OBJ): $(TEXT_OUTPUT_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(TEXT_OUTPUT_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(TEXT_OUTPUT_OBJ)

$(DESCRIPTION_OBJ): $(DESCRIPTION_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(DESCRIPTION_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(DESCRIPTION_OBJ)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(DESCRIPTIONS_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(DESCRIPTIONS_OBJ)

$(NATIONALITY_OBJ): $(NATIONALITY_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(NATIONALITY_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(NATIONALITY_OBJ)

$(UNIT_TYPE_OBJ): $(UNIT_TYPE_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(UNIT_TYPE_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(UNIT_TYPE_OBJ)

$(CLASSIFICATION_OBJ): $(CLASSIFICATION_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(CLASSIFICATION_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(CLASSIFICATION_OBJ)

$(UNIT_OBJ): $(UNIT_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(UNIT_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(UNIT_OBJ)

$(FIGHTING_OBJ): $(FIGHTING_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(FIGHTING_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(FIGHTING_OBJ)

$(MOBILE_OBJ): $(MOBILE_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(MOBILE_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(MOBILE_OBJ)

$(INFANTRY_OBJ): $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(INFANTRY_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(INFANTRY_OBJ)

$(PERSONNEL_OBJ): $(PERSONNEL_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(PERSONNEL_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(PERSONNEL_OBJ)

$(SQUAD_OBJ): $(SQUAD_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(SQUAD_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(SQUAD_OBJ)

$(LEADER_OBJ): $(LEADER_CLASS)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$(LEADER_CLASS) \
	                   -o $(OBJ_SUB_DIRECTORY)/$(LEADER_OBJ)

# Build all of the individual object files.

.PHONY : obj
obj: obj_sub_directory $(GCJ_OBJECT_FILES)

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(COUNTERS_BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).1
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
	$(AR) $(COUNTERS_STATIC_LIB_PATH) $(LOCAL_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(COUNTERS_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(COUNTERS_HDR_FILES)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(COUNTERS_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. Symbolic links are also created to each file from the doxygen
# documentation output directory. This is done so that the references in the
# doxygen files to the files generated by this target will work. After the files
# are generated, the htls program is used to link the files together on an HTML
# page.

COUNTERS_DOCS_PATH := $(SRC_DOCS_PATH)/$(COUNTERS_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(COUNTERS_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(COUNTERS_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(COUNTERS_DOCS_PATH)/$$i.html ;\
		if [ -d $(DOXYGEN_DOC_PATH)/html ]; then \
			(cd $(DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(COUNTERS_PKG_PATH)/$$i.html) ;\
		fi \
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(COUNTERS_DOCS_PATH)
	@$(HTLS) $(COUNTERS_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(COUNTERS_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
