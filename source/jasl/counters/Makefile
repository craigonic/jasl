################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. The files can #
#            compiled individually or the entire package can be recreated.     #
#                                                                              #
# Written By    : Craig R. Campbell  -  June 1999                              #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Makefile,v 1.1 1999/06/22 05:29:34 craig Exp $
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
INFANTRY_CLASSES = Infantry Personnel Squad Leader

ALL_CLASSES      = $(SUPER_CLASSES) $(INFANTRY_CLASSES)

# Define commands.

all: $(ALL_CLASSES)

Unit: Unit.java
	$(COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD) Unit.java

Fighting: Fighting.java $(OUTPUT_PATH)/Unit.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Fighting.java

Mobile: Mobile.java $(OUTPUT_PATH)/Fighting.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Mobile.java

Infantry: Infantry.java $(OUTPUT_PATH)/Mobile.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Infantry.java

Personnel: Personnel.java $(OUTPUT_PATH)/Infantry.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Personnel.java

Squad: Squad.java $(OUTPUT_PATH)/Personnel.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Squad.java

Leader: Leader.java $(OUTPUT_PATH)/Infantry.class
	$(COMPILER) $(OPTIMIZE) $(CLASSPATH_CMD) $(OUTPUT_DIR_CMD) Leader.java
