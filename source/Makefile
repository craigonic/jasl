################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Driver program used to test the packages in each    #
#            subdirectory.                                                     #
#                                                                              #
# Written By    : Craig R. Campbell  -  August 1999                            #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/Makefile,v 1.1 1999/08/21 06:29:45 craig Exp $
################################################################################

# Define variables.

COMPILER         = javac

CLASSPATH        = ../jasl
OUTPUT_PATH      = $(CLASSPATH)/Counters

CLASSPATH_CMD    = -classpath $(CLASSPATH)
OUTPUT_DIR_CMD   = -d $(CLASSPATH)

#OPTIMIZE         = -O
OPTIMIZE =

DRIVER_CLASS     = Driver

ALL_CLASSES      = $(DRIVER_CLASS)

# Define a parameter to build all of the individual class files and the test
# program (driver).

all: $(ALL_CLASSES)

# Define a parameter to build the driver program used to test the classes
# defined in the Counters package.

Driver: Driver.java
	$(COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD) Driver.java
