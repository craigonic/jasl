################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be recreated.                              #
#                                                                              #
# Written By    : Craig R. Campbell  -  June 1999                              #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Makefile,v 1.9 2001/03/22 06:07:08 craig Exp $
################################################################################

# Define variables.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

HTLS              = htls -genpage
JAVA2HTML         = java2html -doc

COUNTERS_PACKAGE  = Counters

CLASSPATH         = ../../jasl
OUTPUT_PATH       = $(CLASSPATH)/$(COUNTERS_PACKAGE)
DOCS_PATH         = ../../docs/$(COUNTERS_PACKAGE)

CLASSPATH_CMD     = -classpath $(CLASSPATH)
GCJ_CLASSPATH_CMD = --CLASSPATH=$(CLASSPATH)
OUTPUT_DIR_CMD    = -d $(CLASSPATH)

#OPTIMIZE          = -O

JAVA_OPTIONS      = $(JAVA_COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD)
GCJ_OPTIONS       = $(GCJ_COMPILER) -c

# Define variables to represent the source, object, and class files in/of/for
# the Counters package.

UNIT_CLASS        = Unit.class
UNIT_OBJ          = ${UNIT_CLASS:.class=.o}
UNIT_SRC          = ${UNIT_CLASS:.class=.java}

FIGHTING_CLASS    = Fighting.class
FIGHTING_OBJ      = ${FIGHTING_CLASS:.class=.o}
FIGHTING_SRC      = ${FIGHTING_CLASS:.class=.java}

MOBILE_CLASS      = Mobile.class
MOBILE_OBJ        = ${MOBILE_CLASS:.class=.o}
MOBILE_SRC        = ${MOBILE_CLASS:.class=.java}

INFANTRY_CLASS    = Infantry.class
INFANTRY_OBJ      = ${INFANTRY_CLASS:.class=.o}
INFANTRY_SRC      = ${INFANTRY_CLASS:.class=.java}

PERSONNEL_CLASS   = Personnel.class
PERSONNEL_OBJ     = ${PERSONNEL_CLASS:.class=.o}
PERSONNEL_SRC     = ${PERSONNEL_CLASS:.class=.java}

SQUAD_CLASS       = Squad.class
SQUAD_OBJ         = ${SQUAD_CLASS:.class=.o}
SQUAD_SRC         = ${SQUAD_CLASS:.class=.java}

LEADER_CLASS      = Leader.class
LEADER_OBJ        = ${LEADER_CLASS:.class=.o}
LEADER_SRC        = ${LEADER_CLASS:.class=.java}

DICE_CLASS        = Dice.class
DICE_OBJ          = ${DICE_CLASS:.class=.o}
DICE_SRC          = ${DICE_CLASS:.class=.java}

SUPER_CLASSES     = $(UNIT_CLASS) $(FIGHTING_CLASS) $(MOBILE_CLASS)
INF_CLASSES       = $(INFANTRY_CLASS) $(PERSONNEL_CLASS) $(SQUAD_CLASS) $(LEADER_CLASS)
UTIL_CLASSES      = $(DICE_CLASS)

ALL_CLASSES       = $(SUPER_CLASSES) $(INF_CLASSES) $(UTIL_CLASSES)

GCJ_OBJECT_FILES  = ${ALL_CLASSES:.class=.o}

JAVA_SRC_FILES    = ${ALL_CLASSES:.class=.java}

# Define system commands.

MV                = /bin/mv

# Define a parameter to build all of the individual class files. 

all build: $(ALL_CLASSES)

# Define parameters to compile the individual Java classes in the Counters
# package.

$(UNIT_CLASS): $(UNIT_SRC)
	$(JAVA_OPTIONS) $(UNIT_SRC)

$(FIGHTING_CLASS): $(FIGHTING_SRC) $(OUTPUT_PATH)/$(UNIT_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(FIGHTING_SRC)

$(MOBILE_CLASS): $(MOBILE_SRC) $(OUTPUT_PATH)/$(FIGHTING_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(MOBILE_SRC)

$(INFANTRY_CLASS): $(INFANTRY_SRC) $(OUTPUT_PATH)/$(MOBILE_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(INFANTRY_SRC)

$(PERSONNEL_CLASS): $(PERSONNEL_SRC) $(OUTPUT_PATH)/$(INFANTRY_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(PERSONNEL_SRC)

$(SQUAD_CLASS): $(SQUAD_SRC) $(OUTPUT_PATH)/$(PERSONNEL_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(SQUAD_SRC)

$(LEADER_CLASS): $(LEADER_SRC) $(OUTPUT_PATH)/$(INFANTRY_CLASS)
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) $(LEADER_SRC)

$(DICE_CLASS): $(DICE_SRC)
	$(JAVA_OPTIONS) $(DICE_SRC)

# Define a parameter to build all of the individual object files. 

gcj gcj_obj: $(GCJ_OBJECT_FILES)

# Define parameters to compile individual object files used to build a native
# executable with the gcj compiler.

$(UNIT_OBJ): $(UNIT_SRC)
	$(GCJ_OPTIONS) $(UNIT_SRC)
	$(MV) $(UNIT_OBJ) $(OUTPUT_PATH)

$(FIGHTING_OBJ): $(FIGHTING_SRC) $(OUTPUT_PATH)/$(UNIT_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(FIGHTING_SRC)
	$(MV) $(FIGHTING_OBJ) $(OUTPUT_PATH)

$(MOBILE_OBJ): $(MOBILE_SRC) $(OUTPUT_PATH)/$(FIGHTING_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(MOBILE_SRC)
	$(MV) $(MOBILE_OBJ) $(OUTPUT_PATH)

$(INFANTRY_OBJ): $(INFANTRY_SRC) $(OUTPUT_PATH)/$(MOBILE_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(INFANTRY_SRC)
	$(MV) $(INFANTRY_OBJ) $(OUTPUT_PATH)

$(PERSONNEL_OBJ): $(PERSONNEL_SRC) $(OUTPUT_PATH)/$(INFANTRY_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(PERSONNEL_SRC)
	$(MV) $(PERSONNEL_OBJ) $(OUTPUT_PATH)

$(SQUAD_OBJ): $(SQUAD_SRC) $(OUTPUT_PATH)/$(PERSONNEL_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(SQUAD_SRC)
	$(MV) $(SQUAD_OBJ) $(OUTPUT_PATH)

$(LEADER_OBJ): $(LEADER_SRC) $(OUTPUT_PATH)/$(INFANTRY_OBJ)
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) $(LEADER_SRC)
	$(MV) $(LEADER_OBJ) $(OUTPUT_PATH)

$(DICE_OBJ): $(DICE_SRC)
	$(GCJ_OPTIONS) $(DICE_SRC)
	$(MV) $(DICE_OBJ) $(OUTPUT_PATH)

# Define parameters to remove all of the class and object files from the output
# directory or a specific subset.

clean: clean_java clean_obj

clean_java:
	$(RM) $(OUTPUT_PATH)/*.class

clean_obj:
	$(RM) $(OUTPUT_PATH)/*.o

# Define a parameter to create HTML versions of the source files. Each file is
# converted using the java2html program, which generates an "image" of the file
# with syntax color coding. After the files are generated, the htls program is
# used to link the files together on an HTML page.

docs:
	@for i in $(JAVA_SRC_FILES) ; do \
		$(JAVA2HTML) -input $$i -output $(DOCS_PATH)/$$i.html -title "$$i" ;\
	done
	@$(HTLS) $(DOCS_PATH)
