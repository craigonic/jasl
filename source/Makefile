################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Driver program used to test the Counters package.   #
#            It also includes a parameter to build a native executable version #
#            of the same program using the gcj compiler.                       #
#                                                                              #
# Written By    : Craig R. Campbell  -  August 1999                            #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/Makefile,v 1.4 2000/08/05 07:34:12 craig Exp $
################################################################################

# Define variables.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

HTLS              = htls -genpage -index
JAVA2HTML         = java2html -doc

CLASSPATH         = ../jasl
OUTPUT_PATH       = $(CLASSPATH)
OBJS_PATH         = $(CLASSPATH)/Counters
DOCS_PATH         = ../docs

CLASSPATH_CMD     = -classpath $(CLASSPATH)
GCJ_CLASSPATH_CMD = --CLASSPATH=$(CLASSPATH)
OUTPUT_DIR_CMD    = -d $(CLASSPATH)

#OPTIMIZE          = -O

JAVA_OPTIONS      = $(JAVA_COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD)
GCJ_OPTIONS       = $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD)

DRIVER_CLASS      = Driver

ALL_CLASSES       = $(DRIVER_CLASS)

GCJ_SUPER_OBJS    = $(OBJS_PATH)/Unit.o $(OBJS_PATH)/Fighting.o $(OBJS_PATH)/Mobile.o
GCJ_INF_OBJS      = $(OBJS_PATH)/Infantry.o $(OBJS_PATH)/Personnel.o $(OBJS_PATH)/Squad.o $(OBJS_PATH)/Leader.o
GCJ_UTIL_OBJS     = $(OBJS_PATH)/Dice.o

MV                = /bin/mv

# Define a parameter to build the java version of the test program
# (Driver.class).

all: $(ALL_CLASSES)

# Define a parameter to build the java version of the driver program used to
# test the classes defined in the Counters package.

Driver: Driver.java
	$(JAVA_OPTIONS) Driver.java

# Define a parameter to build the native executable version of the test program
# (Driver) using the gcj compiler.

gcj: $(ALL_CLASSES).exe

# Define a parameter to build the native executable version of the driver
# program used to test the classes defined in the Counters package.

Driver.exe: Driver.java
	$(GCJ_OPTIONS) --main=$(DRIVER_CLASS) -o $(OUTPUT_PATH)/$(DRIVER_CLASS) Driver.java $(GCJ_SUPER_OBJS) $(GCJ_INF_OBJS) $(GCJ_UTIL_OBJS)

# Define parameters to remove all of the existing program files from the output
# directory or a specific subset.

clean: clean.class clean.exe

clean.class:
	$(RM) $(OUTPUT_PATH)/$(DRIVER_CLASS).class

clean.exe:
	$(RM) $(OUTPUT_PATH)/$(DRIVER_CLASS)

# Define a parameter to create HTML versions of the source files. Each file is
# converted using the java2html program, which generates an "image" of the file
# with syntax color coding. After the files are generated, the htls program is
# used to link the files together on an HTML page.

docs:
	@for i in $(ALL_CLASSES) ; do \
		$(JAVA2HTML) -input $$i.java -output $(DOCS_PATH)/$$i.html -title "$$i program" ;\
	done
	@$(HTLS) $(DOCS_PATH)
