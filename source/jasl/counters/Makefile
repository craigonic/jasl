################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. The files can #
#            compiled individually or the entire package can be recreated.     #
#                                                                              #
# Written By    : Craig R. Campbell  -  June 1999                              #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Makefile,v 1.5 1999/09/13 04:59:13 craig Exp $
################################################################################

# Define variables.

COMPILER         = javac

CLASSPATH        = ../../jasl
OUTPUT_PATH      = $(CLASSPATH)/Counters

CLASSPATH_CMD    = -classpath $(CLASSPATH)
OUTPUT_DIR_CMD   = -d $(CLASSPATH)

#OPTIMIZE         = -O
OPTIMIZE =

SUPER_CLASSES    = Unit Fighting Mobile
INFANTRY_CLASSES = Infantry Squad Leader
UTILITY_CLASSES  = Dice

ALL_CLASSES      = $(SUPER_CLASSES) $(INFANTRY_CLASSES) $(UTILITY_CLASSES)

# Define a parameter to build all of the individual class files and the test
# program (driver).

all: $(ALL_CLASSES)

# Define parameters to compile the individual Java classes in the Counters
# package.

Unit: Unit.java
	$(COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD) Unit.java

Fighting: Fighting.java $(OUTPUT_PATH)/Unit.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Fighting.java

Mobile: Mobile.java $(OUTPUT_PATH)/Fighting.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Mobile.java

Infantry: Infantry.java $(OUTPUT_PATH)/Mobile.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Infantry.java

Squad: Squad.java $(OUTPUT_PATH)/Infantry.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Squad.java

Leader: Leader.java $(OUTPUT_PATH)/Infantry.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Leader.java

Dice: Dice.java
	$(COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD) Dice.java
