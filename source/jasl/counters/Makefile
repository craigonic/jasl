################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be recreated.                              #
#                                                                              #
# Written By : Craig R. Campbell  -  June 1999                                 #
#                                                                              #
# $Id: Makefile,v 1.16 2007/04/14 04:24:30 craig Exp $
################################################################################

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Directory specific build variables.

BIN_OUTPUT_PATH      = $(BIN_PATH)/$(COUNTERS_PKG_PATH)
INCLUDE_OUTPUT_PATH  = $(INCLUDE_PATH)/$(COUNTERS_PKG_PATH)

# Define variables to represent the source, object, and class files in/of/for
# this package.

COUNTER_CLASS        = Counter.class
COUNTER_EXEC         = ${COUNTER_CLASS:.class=}
COUNTER_HDR          = ${COUNTER_CLASS:.class=.h}
COUNTER_OBJ          = ${COUNTER_CLASS:.class=.o}
COUNTER_SRC          = ${COUNTER_CLASS:.class=.java}

DESCRIPTION_CLASS    = Description.class
DESCRIPTION_EXEC     = ${DESCRIPTION_CLASS:.class=}
DESCRIPTION_HDR      = ${DESCRIPTION_CLASS:.class=.h}
DESCRIPTION_OBJ      = ${DESCRIPTION_CLASS:.class=.o}
DESCRIPTION_SRC      = ${DESCRIPTION_CLASS:.class=.java}

NATIONALITY_CLASS    = Nationality.class
NATIONALITY_EXEC     = ${NATIONALITY_CLASS:.class=}
NATIONALITY_HDR      = ${NATIONALITY_CLASS:.class=.h}
NATIONALITY_OBJ      = ${NATIONALITY_CLASS:.class=.o}
NATIONALITY_SRC      = ${NATIONALITY_CLASS:.class=.java}

UNIT_TYPE_CLASS      = UnitType.class
UNIT_TYPE_EXEC       = ${UNIT_TYPE_CLASS:.class=}
UNIT_TYPE_HDR        = ${UNIT_TYPE_CLASS:.class=.h}
UNIT_TYPE_OBJ        = ${UNIT_TYPE_CLASS:.class=.o}
UNIT_TYPE_SRC        = ${UNIT_TYPE_CLASS:.class=.java}

CLASSIFICATION_CLASS = Classification.class
CLASSIFICATION_EXEC  = ${CLASSIFICATION_CLASS:.class=}
CLASSIFICATION_HDR   = ${CLASSIFICATION_CLASS:.class=.h}
CLASSIFICATION_OBJ   = ${CLASSIFICATION_CLASS:.class=.o}
CLASSIFICATION_SRC   = ${CLASSIFICATION_CLASS:.class=.java}

UNIT_CLASS           = Unit.class
UNIT_EXEC            = ${UNIT_CLASS:.class=}
UNIT_HDR             = ${UNIT_CLASS:.class=.h}
UNIT_OBJ             = ${UNIT_CLASS:.class=.o}
UNIT_SRC             = ${UNIT_CLASS:.class=.java}

FIGHTING_CLASS       = Fighting.class
FIGHTING_EXEC        = ${FIGHTING_CLASS:.class=}
FIGHTING_HDR         = ${FIGHTING_CLASS:.class=.h}
FIGHTING_OBJ         = ${FIGHTING_CLASS:.class=.o}
FIGHTING_SRC         = ${FIGHTING_CLASS:.class=.java}

MOBILE_CLASS         = Mobile.class
MOBILE_EXEC          = ${MOBILE_CLASS:.class=}
MOBILE_HDR           = ${MOBILE_CLASS:.class=.h}
MOBILE_OBJ           = ${MOBILE_CLASS:.class=.o}
MOBILE_SRC           = ${MOBILE_CLASS:.class=.java}

INFANTRY_CLASS       = Infantry.class
INFANTRY_EXEC        = ${INFANTRY_CLASS:.class=}
INFANTRY_HDR         = ${INFANTRY_CLASS:.class=.h}
INFANTRY_OBJ         = ${INFANTRY_CLASS:.class=.o}
INFANTRY_SRC         = ${INFANTRY_CLASS:.class=.java}

PERSONNEL_CLASS      = Personnel.class
PERSONNEL_EXEC       = ${PERSONNEL_CLASS:.class=}
PERSONNEL_HDR        = ${PERSONNEL_CLASS:.class=.h}
PERSONNEL_OBJ        = ${PERSONNEL_CLASS:.class=.o}
PERSONNEL_SRC        = ${PERSONNEL_CLASS:.class=.java}

SQUAD_CLASS          = Squad.class
SQUAD_EXEC           = ${SQUAD_CLASS:.class=}
SQUAD_HDR            = ${SQUAD_CLASS:.class=.h}
SQUAD_OBJ            = ${SQUAD_CLASS:.class=.o}
SQUAD_SRC            = ${SQUAD_CLASS:.class=.java}

LEADER_CLASS         = Leader.class
LEADER_EXEC          = ${LEADER_CLASS:.class=}
LEADER_HDR           = ${LEADER_CLASS:.class=.h}
LEADER_OBJ           = ${LEADER_CLASS:.class=.o}
LEADER_SRC           = ${LEADER_CLASS:.class=.java}

DICE_CLASS           = Dice.class
DICE_EXEC            = ${DICE_CLASS:.class=}
DICE_HDR             = ${DICE_CLASS:.class=.h}
DICE_OBJ             = ${DICE_CLASS:.class=.o}
DICE_SRC             = ${DICE_CLASS:.class=.java}

INTERFACES           = $(COUNTER_CLASS) $(DESCRIPTION_CLASS) \
                       $(NATIONALITY_CLASS) $(UNIT_TYPE_CLASS) \
                       $(CLASSIFICATION_CLASS)
SUPER_CLASSES        = $(UNIT_CLASS) $(FIGHTING_CLASS) $(MOBILE_CLASS)
INF_CLASSES          = $(INFANTRY_CLASS) $(PERSONNEL_CLASS) $(SQUAD_CLASS) \
                       $(LEADER_CLASS)
UTIL_CLASSES         = $(DICE_CLASS)

ALL_CLASSES          = $(INTERFACES) $(SUPER_CLASSES) $(INF_CLASSES) \
                       $(UTIL_CLASSES)

GCJ_HEADER_FILES     = ${ALL_CLASSES:.class=.h}

GCJ_OBJECT_FILES     = ${ALL_CLASSES:.class=.o}

JAVA_SRC_FILES       = ${ALL_CLASSES:.class=.java}

JAVA_CLASSES         = ${ALL_CLASSES:.class=}

# Build all of the individual class files.

all build: $(ALL_CLASSES)

# Compile the individual Java classes in this package.

$(COUNTER_CLASS):
	$(JAVA_OPTIONS) $(COUNTER_SRC)

$(DESCRIPTION_CLASS):
	$(JAVA_OPTIONS) $(DESCRIPTION_SRC)

$(NATIONALITY_CLASS):
	$(JAVA_OPTIONS) $(NATIONALITY_SRC)

$(UNIT_TYPE_CLASS):
	$(JAVA_OPTIONS) $(UNIT_TYPE_SRC)

$(CLASSIFICATION_CLASS):
	$(JAVA_OPTIONS) $(CLASSIFICATION_SRC)

$(UNIT_CLASS): $(COUNTER_CLASS) $(DESCRIPTION_CLASS)
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

$(DICE_CLASS): $(UNIT_CLASS)
	$(JAVA_OPTIONS) $(DICE_SRC)

# Build all of the individual object files. The gcj_obj target is used by the
# Makefile in the parent directory for recursive builds.

gcj gcj_obj: $(GCJ_OBJECT_FILES)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(COUNTER_OBJ):
	$(GCJ_COMPILE_CMD) $(COUNTER_SRC)
	$(MV) $(COUNTER_OBJ) $(COUNTERS_OBJ_PATH)

$(DESCRIPTION_OBJ):
	$(GCJ_COMPILE_CMD) $(DESCRIPTION_SRC)
	$(MV) $(DESCRIPTION_OBJ) $(COUNTERS_OBJ_PATH)

$(NATIONALITY_OBJ):
	$(GCJ_COMPILE_CMD) $(NATIONALITY_SRC)
	$(MV) $(NATIONALITY_OBJ) $(COUNTERS_OBJ_PATH)

$(UNIT_TYPE_OBJ):
	$(GCJ_COMPILE_CMD) $(UNIT_TYPE_SRC)
	$(MV) $(UNIT_TYPE_OBJ) $(COUNTERS_OBJ_PATH)

$(CLASSIFICATION_OBJ):
	$(GCJ_COMPILE_CMD) $(CLASSIFICATION_SRC)
	$(MV) $(CLASSIFICATION_OBJ) $(COUNTERS_OBJ_PATH)

$(UNIT_OBJ): $(COUNTER_CLASS) $(DESCRIPTION_CLASS)
	$(GCJ_COMPILE_CMD) $(UNIT_SRC)
	$(MV) $(UNIT_OBJ) $(COUNTERS_OBJ_PATH)

$(FIGHTING_OBJ): $(UNIT_CLASS) $(NATIONALITY_CLASS) $(UNIT_TYPE_CLASS)
	$(GCJ_COMPILE_CMD) $(FIGHTING_SRC)
	$(MV) $(FIGHTING_OBJ) $(COUNTERS_OBJ_PATH)

$(MOBILE_OBJ): $(FIGHTING_CLASS)
	$(GCJ_COMPILE_CMD) $(MOBILE_SRC)
	$(MV) $(MOBILE_OBJ) $(COUNTERS_OBJ_PATH)

$(INFANTRY_OBJ): $(MOBILE_CLASS)
	$(GCJ_COMPILE_CMD) $(INFANTRY_SRC)
	$(MV) $(INFANTRY_OBJ) $(COUNTERS_OBJ_PATH)

$(PERSONNEL_OBJ): $(CLASSIFICATION_CLASS) $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(PERSONNEL_SRC)
	$(MV) $(PERSONNEL_OBJ) $(COUNTERS_OBJ_PATH)

$(SQUAD_OBJ): $(PERSONNEL_CLASS)
	$(GCJ_COMPILE_CMD) $(SQUAD_SRC)
	$(MV) $(SQUAD_OBJ) $(COUNTERS_OBJ_PATH)

$(LEADER_OBJ): $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(LEADER_SRC)
	$(MV) $(LEADER_OBJ) $(COUNTERS_OBJ_PATH)

$(DICE_OBJ): $(UNIT_CLASS)
	$(GCJ_COMPILE_CMD) $(DICE_SRC)
	$(MV) $(DICE_OBJ) $(COUNTERS_OBJ_PATH)

# Build all of the individual header files.

hdr: $(GCJ_HEADER_FILES)

# Using the installed .class files, build the header file that defines each one
# and install it in the include output directory.

GCJH_BUILD_CMD = $(GCJH_CMD) $(GCJ_CLASSPATH_CMD)/$(COUNTERS_PKG_PATH) \
                 -d $(INCLUDE_OUTPUT_PATH)

$(COUNTER_HDR): $(BIN_OUTPUT_PATH)/$(COUNTER_CLASS)
	$(GCJH_BUILD_CMD) $(COUNTER_EXEC)

$(DESCRIPTION_HDR): $(BIN_OUTPUT_PATH)/$(DESCRIPTION_CLASS)
	$(GCJH_BUILD_CMD) $(DESCRIPTION_EXEC)

$(NATIONALITY_HDR): $(BIN_OUTPUT_PATH)/$(NATIONALITY_CLASS)
	$(GCJH_BUILD_CMD) $(NATIONALITY_EXEC)

$(UNIT_TYPE_HDR): $(BIN_OUTPUT_PATH)/$(UNIT_TYPE_CLASS)
	$(GCJH_BUILD_CMD) $(UNIT_TYPE_EXEC)

$(CLASSIFICATION_HDR): $(BIN_OUTPUT_PATH)/$(CLASSIFICATION_CLASS)
	$(GCJH_BUILD_CMD) $(CLASSIFICATION_EXEC)

$(UNIT_HDR): $(BIN_OUTPUT_PATH)/$(UNIT_CLASS)
	$(GCJH_BUILD_CMD) $(UNIT_EXEC)

$(FIGHTING_HDR): $(BIN_OUTPUT_PATH)/$(FIGHTING_CLASS)
	$(GCJH_BUILD_CMD) $(FIGHTING_EXEC)

$(MOBILE_HDR): $(BIN_OUTPUT_PATH)/$(MOBILE_CLASS)
	$(GCJH_BUILD_CMD) $(MOBILE_EXEC)

$(INFANTRY_HDR): $(BIN_OUTPUT_PATH)/$(INFANTRY_CLASS)
	$(GCJH_BUILD_CMD) $(INFANTRY_EXEC)

$(PERSONNEL_HDR): $(BIN_OUTPUT_PATH)/$(PERSONNEL_CLASS)
	$(GCJH_BUILD_CMD) $(PERSONNEL_EXEC)

$(SQUAD_HDR): $(BIN_OUTPUT_PATH)/$(SQUAD_CLASS)
	$(GCJH_BUILD_CMD) $(SQUAD_EXEC)

$(LEADER_HDR): $(BIN_OUTPUT_PATH)/$(LEADER_CLASS)
	$(GCJH_BUILD_CMD) $(LEADER_EXEC)

$(DICE_HDR): $(BIN_OUTPUT_PATH)/$(DICE_CLASS)
	$(GCJH_BUILD_CMD) $(DICE_EXEC)

# Define variables to represent, based on extension and destination, groups of
# files generated in this directory.

CLASS_FILES_LIST       = $(BIN_OUTPUT_PATH)/*.class
HEADER_FILES_LIST      = $(INCLUDE_OUTPUT_PATH)/*.h

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    = $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME = $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME = $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               = $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

libs: gcj dynamic_lib static_lib clean_obj

dynamic_lib:
	$(GCJ_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(COUNTERS_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

static_lib:
	$(AR) $(COUNTERS_STATIC_LIB_PATH) $(COUNTERS_OBJ_FILES)

# Remove all of the .class and/or object files from the binary output directory.

clean: clean_java clean_obj

clean_java:
	$(RM) $(CLASS_FILES_LIST)

clean_obj:
	$(RM) $(COUNTERS_OBJ_FILES)

# Remove all of the header (.h) files from the include output directory.

clean_hdr:
	$(RM) $(HEADER_FILES_LIST)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

clean_libs: clean_dynamic_lib clean_static_lib

clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

clean_static_lib:
	$(RM) $(COUNTERS_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. Symbolic links are also created to each file from the doxygen
# documentation output directory. This is done so that the references in the
# doxygen files to the files generated by this target will work. After the files
# are generated, the htls program is used to link the files together on an HTML
# page.

docs:
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(COUNTERS_PKG_PATH)/$$i.html ;\
		(cd $(DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(COUNTERS_PKG_PATH)/$$i.html) ;\
	done
	@$(HTLS) $(SRC_DOCS_PATH)/$(COUNTERS_PKG_PATH)
