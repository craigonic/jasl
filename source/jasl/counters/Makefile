################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be recreated.                              #
#                                                                              #
# Written By : Craig R. Campbell  -  June 1999                                 #
#                                                                              #
# $Id: Makefile,v 1.22 2010/09/25 03:53:21 craig Exp $
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
TEXT_OUTPUT_EXEC     := ${TEXT_OUTPUT_CLASS:.class=}
TEXT_OUTPUT_HDR      := ${TEXT_OUTPUT_CLASS:.class=.h}
TEXT_OUTPUT_OBJ      := ${TEXT_OUTPUT_CLASS:.class=.o}
TEXT_OUTPUT_SRC      := ${TEXT_OUTPUT_CLASS:.class=.java}

DESCRIPTION_CLASS    := Description.class
DESCRIPTION_EXEC     := ${DESCRIPTION_CLASS:.class=}
DESCRIPTION_HDR      := ${DESCRIPTION_CLASS:.class=.h}
DESCRIPTION_OBJ      := ${DESCRIPTION_CLASS:.class=.o}
DESCRIPTION_SRC      := ${DESCRIPTION_CLASS:.class=.java}

# This file is generated when Description.java is compiled and represents the
# Descriptions enum defined in that class. The following definitions are used to
# generate the header file.

DESCRIPTIONS_CLASS   := Description\$$Descriptions.class
DESCRIPTIONS_EXEC    := ${DESCRIPTIONS_CLASS:.class=}
DESCRIPTIONS_HDR     := ${DESCRIPTIONS_CLASS:.class=.h}

NATIONALITY_CLASS    := Nationality.class
NATIONALITY_EXEC     := ${NATIONALITY_CLASS:.class=}
NATIONALITY_HDR      := ${NATIONALITY_CLASS:.class=.h}
NATIONALITY_OBJ      := ${NATIONALITY_CLASS:.class=.o}
NATIONALITY_SRC      := ${NATIONALITY_CLASS:.class=.java}

UNIT_TYPE_CLASS      := UnitType.class
UNIT_TYPE_EXEC       := ${UNIT_TYPE_CLASS:.class=}
UNIT_TYPE_HDR        := ${UNIT_TYPE_CLASS:.class=.h}
UNIT_TYPE_OBJ        := ${UNIT_TYPE_CLASS:.class=.o}
UNIT_TYPE_SRC        := ${UNIT_TYPE_CLASS:.class=.java}

CLASSIFICATION_CLASS := Classification.class
CLASSIFICATION_EXEC  := ${CLASSIFICATION_CLASS:.class=}
CLASSIFICATION_HDR   := ${CLASSIFICATION_CLASS:.class=.h}
CLASSIFICATION_OBJ   := ${CLASSIFICATION_CLASS:.class=.o}
CLASSIFICATION_SRC   := ${CLASSIFICATION_CLASS:.class=.java}

UNIT_CLASS           := Unit.class
UNIT_EXEC            := ${UNIT_CLASS:.class=}
UNIT_HDR             := ${UNIT_CLASS:.class=.h}
UNIT_OBJ             := ${UNIT_CLASS:.class=.o}
UNIT_SRC             := ${UNIT_CLASS:.class=.java}

FIGHTING_CLASS       := Fighting.class
FIGHTING_EXEC        := ${FIGHTING_CLASS:.class=}
FIGHTING_HDR         := ${FIGHTING_CLASS:.class=.h}
FIGHTING_OBJ         := ${FIGHTING_CLASS:.class=.o}
FIGHTING_SRC         := ${FIGHTING_CLASS:.class=.java}

MOBILE_CLASS         := Mobile.class
MOBILE_EXEC          := ${MOBILE_CLASS:.class=}
MOBILE_HDR           := ${MOBILE_CLASS:.class=.h}
MOBILE_OBJ           := ${MOBILE_CLASS:.class=.o}
MOBILE_SRC           := ${MOBILE_CLASS:.class=.java}

INFANTRY_CLASS       := Infantry.class
INFANTRY_EXEC        := ${INFANTRY_CLASS:.class=}
INFANTRY_HDR         := ${INFANTRY_CLASS:.class=.h}
INFANTRY_OBJ         := ${INFANTRY_CLASS:.class=.o}
INFANTRY_SRC         := ${INFANTRY_CLASS:.class=.java}

PERSONNEL_CLASS      := Personnel.class
PERSONNEL_EXEC       := ${PERSONNEL_CLASS:.class=}
PERSONNEL_HDR        := ${PERSONNEL_CLASS:.class=.h}
PERSONNEL_OBJ        := ${PERSONNEL_CLASS:.class=.o}
PERSONNEL_SRC        := ${PERSONNEL_CLASS:.class=.java}

SQUAD_CLASS          := Squad.class
SQUAD_EXEC           := ${SQUAD_CLASS:.class=}
SQUAD_HDR            := ${SQUAD_CLASS:.class=.h}
SQUAD_OBJ            := ${SQUAD_CLASS:.class=.o}
SQUAD_SRC            := ${SQUAD_CLASS:.class=.java}

LEADER_CLASS         := Leader.class
LEADER_EXEC          := ${LEADER_CLASS:.class=}
LEADER_HDR           := ${LEADER_CLASS:.class=.h}
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

GCJ_HEADER_FILES     := ${ALL_CLASSES:.class=.h} ${ENUMS:.class=.h}

GCJ_OBJECT_FILES     := ${ALL_CLASSES:.class=.o}

JAVA_SRC_FILES       := ${ALL_CLASSES:.class=.java}

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

# Build all of the individual object files.

.PHONY : obj
obj: $(GCJ_OBJECT_FILES)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(TEXT_OUTPUT_OBJ):
	$(GCJ_COMPILE_CMD) $(TEXT_OUTPUT_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(TEXT_OUTPUT_OBJ) $(COUNTERS_OBJ_PATH)

$(DESCRIPTION_OBJ):
	$(GCJ_COMPILE_CMD) $(DESCRIPTION_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(DESCRIPTION_OBJ) $(COUNTERS_OBJ_PATH)

$(NATIONALITY_OBJ):
	$(GCJ_COMPILE_CMD) $(NATIONALITY_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(NATIONALITY_OBJ) $(COUNTERS_OBJ_PATH)

$(UNIT_TYPE_OBJ):
	$(GCJ_COMPILE_CMD) $(UNIT_TYPE_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(UNIT_TYPE_OBJ) $(COUNTERS_OBJ_PATH)

$(CLASSIFICATION_OBJ):
	$(GCJ_COMPILE_CMD) $(CLASSIFICATION_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(CLASSIFICATION_OBJ) $(COUNTERS_OBJ_PATH)

$(UNIT_OBJ): $(TEXT_OUTPUT_CLASS) $(DESCRIPTION_CLASS)
	$(GCJ_COMPILE_CMD) $(UNIT_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(UNIT_OBJ) $(COUNTERS_OBJ_PATH)

$(FIGHTING_OBJ): $(UNIT_CLASS) $(NATIONALITY_CLASS) $(UNIT_TYPE_CLASS)
	$(GCJ_COMPILE_CMD) $(FIGHTING_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(FIGHTING_OBJ) $(COUNTERS_OBJ_PATH)

$(MOBILE_OBJ): $(FIGHTING_CLASS)
	$(GCJ_COMPILE_CMD) $(MOBILE_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(MOBILE_OBJ) $(COUNTERS_OBJ_PATH)

$(INFANTRY_OBJ): $(MOBILE_CLASS)
	$(GCJ_COMPILE_CMD) $(INFANTRY_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(INFANTRY_OBJ) $(COUNTERS_OBJ_PATH)

$(PERSONNEL_OBJ): $(INFANTRY_CLASS) $(CLASSIFICATION_CLASS)
	$(GCJ_COMPILE_CMD) $(PERSONNEL_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(PERSONNEL_OBJ) $(COUNTERS_OBJ_PATH)

$(SQUAD_OBJ): $(PERSONNEL_CLASS)
	$(GCJ_COMPILE_CMD) $(SQUAD_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(SQUAD_OBJ) $(COUNTERS_OBJ_PATH)

$(LEADER_OBJ): $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(LEADER_SRC)
	$(INSTALL_DIR) $(COUNTERS_OBJ_PATH)
	$(MV) $(LEADER_OBJ) $(COUNTERS_OBJ_PATH)

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(COUNTERS_BIN_PATH)

# Using the installed .class files, build the header file that defines each one
# and install it in the include output directory.

GCJH_BUILD_CMD := $(GCJH_CMD) $(GCJ_CLASSPATH_CMD)/$(COUNTERS_PKG_PATH) \
                  -d $(COUNTERS_HDR_PATH)

$(TEXT_OUTPUT_HDR): $(TEXT_OUTPUT_CLASS)
	$(GCJH_BUILD_CMD) $(TEXT_OUTPUT_EXEC)

$(DESCRIPTION_HDR): $(DESCRIPTION_CLASS)
	$(GCJH_BUILD_CMD) $(DESCRIPTION_EXEC)

$(DESCRIPTIONS_HDR): $(DESCRIPTION_HDR)
	$(GCJH_BUILD_CMD) $(DESCRIPTIONS_EXEC)

$(NATIONALITY_HDR): $(NATIONALITY_CLASS)
	$(GCJH_BUILD_CMD) $(NATIONALITY_EXEC)

$(UNIT_TYPE_HDR): $(UNIT_TYPE_CLASS)
	$(GCJH_BUILD_CMD) $(UNIT_TYPE_EXEC)

$(CLASSIFICATION_HDR): $(CLASSIFICATION_CLASS)
	$(GCJH_BUILD_CMD) $(CLASSIFICATION_EXEC)

$(UNIT_HDR): $(UNIT_CLASS)
	$(GCJH_BUILD_CMD) $(UNIT_EXEC)

$(FIGHTING_HDR): $(FIGHTING_CLASS)
	$(GCJH_BUILD_CMD) $(FIGHTING_EXEC)

$(MOBILE_HDR): $(MOBILE_CLASS)
	$(GCJH_BUILD_CMD) $(MOBILE_EXEC)

$(INFANTRY_HDR): $(INFANTRY_CLASS)
	$(GCJH_BUILD_CMD) $(INFANTRY_EXEC)

$(PERSONNEL_HDR): $(PERSONNEL_CLASS)
	$(GCJH_BUILD_CMD) $(PERSONNEL_EXEC)

$(SQUAD_HDR): $(SQUAD_CLASS)
	$(GCJH_BUILD_CMD) $(SQUAD_EXEC)

$(LEADER_HDR): $(LEADER_CLASS)
	$(GCJH_BUILD_CMD) $(LEADER_EXEC)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).1
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

.PHONY : libs
libs: dynamic_lib static_lib clean_obj

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(GCJ_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(COUNTERS_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(COUNTERS_STATIC_LIB_PATH) $(COUNTERS_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(COUNTERS_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM) $(COUNTERS_OBJ_FILES)

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
HTLS_CFG_FILE      := $(COUNTERS_PKG_NAME).cfg

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
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(HTLS_CFG_FILE) \
	                     $(COUNTERS_DOCS_PATH)
	@$(HTLS) $(COUNTERS_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(COUNTERS_DOCS_PATH)/$(HTLS_HEADER_FILE)
	@$(RM) $(COUNTERS_DOCS_PATH)/$(HTLS_CFG_FILE)

.PHONY : docs
docs: docs_all clean_docs
