################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be recreated.                              #
#                                                                              #
# Written By : Craig R. Campbell  -  June 1999                                 #
#                                                                              #
# $Id: Makefile,v 1.15 2006/12/01 17:28:18 campbell Exp $
################################################################################

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Directory specific build variables.

OUTPUT_PATH       = $(CLASSPATH)/$(COUNTERS_PACKAGE)

# Define variables to represent the source, object, and class files in/of/for
# this package.

COUNTER_CLASS        = Counter.class
COUNTER_OBJ          = ${COUNTER_CLASS:.class=.o}
COUNTER_SRC          = ${COUNTER_CLASS:.class=.java}

CLASSIFICATION_CLASS = Classification.class
CLASSIFICATION_OBJ   = ${CLASSIFICATION_CLASS:.class=.o}
CLASSIFICATION_SRC   = ${CLASSIFICATION_CLASS:.class=.java}

UNIT_CLASS           = Unit.class
UNIT_OBJ             = ${UNIT_CLASS:.class=.o}
UNIT_SRC             = ${UNIT_CLASS:.class=.java}

FIGHTING_CLASS       = Fighting.class
FIGHTING_OBJ         = ${FIGHTING_CLASS:.class=.o}
FIGHTING_SRC         = ${FIGHTING_CLASS:.class=.java}

MOBILE_CLASS         = Mobile.class
MOBILE_OBJ           = ${MOBILE_CLASS:.class=.o}
MOBILE_SRC           = ${MOBILE_CLASS:.class=.java}

INFANTRY_CLASS       = Infantry.class
INFANTRY_OBJ         = ${INFANTRY_CLASS:.class=.o}
INFANTRY_SRC         = ${INFANTRY_CLASS:.class=.java}

PERSONNEL_CLASS      = Personnel.class
PERSONNEL_OBJ        = ${PERSONNEL_CLASS:.class=.o}
PERSONNEL_SRC        = ${PERSONNEL_CLASS:.class=.java}

SQUAD_CLASS          = Squad.class
SQUAD_OBJ            = ${SQUAD_CLASS:.class=.o}
SQUAD_SRC            = ${SQUAD_CLASS:.class=.java}

LEADER_CLASS         = Leader.class
LEADER_OBJ           = ${LEADER_CLASS:.class=.o}
LEADER_SRC           = ${LEADER_CLASS:.class=.java}

DICE_CLASS           = Dice.class
DICE_OBJ             = ${DICE_CLASS:.class=.o}
DICE_SRC             = ${DICE_CLASS:.class=.java}

INTERFACES           = $(COUNTER_CLASS) $(CLASSIFICATION_CLASS)
SUPER_CLASSES        = $(UNIT_CLASS) $(FIGHTING_CLASS) $(MOBILE_CLASS)
INF_CLASSES          = $(INFANTRY_CLASS) $(PERSONNEL_CLASS) $(SQUAD_CLASS) \
                       $(LEADER_CLASS)
UTIL_CLASSES         = $(DICE_CLASS)

ALL_CLASSES          = $(INTERFACES) $(SUPER_CLASSES) $(INF_CLASSES) \
                       $(UTIL_CLASSES)

GCJ_OBJECT_FILES     = ${ALL_CLASSES:.class=.o}

JAVA_SRC_FILES       = ${ALL_CLASSES:.class=.java}

JAVA_CLASSES         = ${ALL_CLASSES:.class=}

# Build all of the individual class files.

all build: $(ALL_CLASSES)

# Compile the individual Java classes in this package.

$(COUNTER_CLASS):
	$(JAVA_OPTIONS) $(COUNTER_SRC)

$(CLASSIFICATION_CLASS):
	$(JAVA_OPTIONS) $(CLASSIFICATION_SRC)

$(UNIT_CLASS): $(COUNTER_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(UNIT_SRC)

$(FIGHTING_CLASS): $(UNIT_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(FIGHTING_SRC)

$(MOBILE_CLASS): $(FIGHTING_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(MOBILE_SRC)

$(INFANTRY_CLASS): $(MOBILE_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(INFANTRY_SRC)

$(PERSONNEL_CLASS): $(CLASSIFICATION_CLASS) $(INFANTRY_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(PERSONNEL_SRC)

$(SQUAD_CLASS): $(PERSONNEL_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(SQUAD_SRC)

$(LEADER_CLASS): $(INFANTRY_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(LEADER_SRC)

$(DICE_CLASS): $(UNIT_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(DICE_SRC)

# Build all of the individual object files. The gcj_obj target is used by the
# Makefile in the parent directory for recursive builds.

gcj gcj_obj: $(GCJ_OBJECT_FILES)

# Compile individual object files used to build a native executable with the gcj
# compiler.

$(COUNTER_OBJ):
	$(GCJ_COMPILE_CMD) $(COUNTER_SRC)
	$(MV) $(COUNTER_OBJ) $(OUTPUT_PATH)

$(CLASSIFICATION_OBJ):
	$(GCJ_COMPILE_CMD) $(CLASSIFICATION_SRC)
	$(MV) $(CLASSIFICATION_OBJ) $(OUTPUT_PATH)

$(UNIT_OBJ): $(COUNTER_CLASS)
	$(GCJ_COMPILE_CMD) $(UNIT_SRC)
	$(MV) $(UNIT_OBJ) $(OUTPUT_PATH)

$(FIGHTING_OBJ): $(UNIT_CLASS)
	$(GCJ_COMPILE_CMD) $(FIGHTING_SRC)
	$(MV) $(FIGHTING_OBJ) $(OUTPUT_PATH)

$(MOBILE_OBJ): $(FIGHTING_CLASS)
	$(GCJ_COMPILE_CMD) $(MOBILE_SRC)
	$(MV) $(MOBILE_OBJ) $(OUTPUT_PATH)

$(INFANTRY_OBJ): $(MOBILE_CLASS)
	$(GCJ_COMPILE_CMD) $(INFANTRY_SRC)
	$(MV) $(INFANTRY_OBJ) $(OUTPUT_PATH)

$(PERSONNEL_OBJ): $(CLASSIFICATION_CLASS) $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(PERSONNEL_SRC)
	$(MV) $(PERSONNEL_OBJ) $(OUTPUT_PATH)

$(SQUAD_OBJ): $(PERSONNEL_CLASS)
	$(GCJ_COMPILE_CMD) $(SQUAD_SRC)
	$(MV) $(SQUAD_OBJ) $(OUTPUT_PATH)

$(LEADER_OBJ): $(INFANTRY_CLASS)
	$(GCJ_COMPILE_CMD) $(LEADER_SRC)
	$(MV) $(LEADER_OBJ) $(OUTPUT_PATH)

$(DICE_OBJ): $(UNIT_CLASS)
	$(GCJ_COMPILE_CMD) $(DICE_SRC)
	$(MV) $(DICE_OBJ) $(OUTPUT_PATH)

# Remove all of the .class and/or object files from the output directory.

clean: clean_java clean_obj

clean_java:
	$(RM) $(OUTPUT_PATH)/*.class

clean_obj:
	$(RM) $(OUTPUT_PATH)/*.o

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

docs:
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(COUNTERS_PACKAGE)/$$i.html ;\
	done
	@$(HTLS) $(SRC_DOCS_PATH)/$(COUNTERS_PACKAGE)
