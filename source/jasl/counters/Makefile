################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be recreated.                              #
#                                                                              #
# Written By    : Craig R. Campbell  -  June 1999                              #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Makefile,v 1.7 1999/12/29 04:21:18 craig Exp $
################################################################################

# Define variables.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

HTLS              = htls -genpage
JAVA2HTML         = java2html -doc

CLASSPATH         = ../../jasl
OUTPUT_PATH       = $(CLASSPATH)/Counters
DOCS_PATH         = ../../docs/Counters

CLASSPATH_CMD     = -classpath $(CLASSPATH)
GCJ_CLASSPATH_CMD = --CLASSPATH=$(CLASSPATH)
OUTPUT_DIR_CMD    = -d $(CLASSPATH)

#OPTIMIZE          = -O

JAVA_OPTIONS      = $(JAVA_COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD)
GCJ_OPTIONS       = $(GCJ_COMPILER) -c

SUPER_CLASSES     = Unit Fighting Mobile
INF_CLASSES       = Infantry Squad Leader
UTIL_CLASSES      = Dice

ALL_CLASSES       = $(SUPER_CLASSES) $(INF_CLASSES) $(UTIL_CLASSES)

GCJ_SUPER_OBJS    = Unit.o Fighting.o Mobile.o
GCJ_INF_OBJS      = Infantry.o Squad.o Leader.o
GCJ_UTIL_OBJS     = Dice.o

GCJ_OBJECT_FILES  = $(GCJ_SUPER_OBJS) $(GCJ_INF_OBJS) $(GCJ_UTIL_OBJS)

MV                = /bin/mv

# Define a parameter to build all of the individual class files. 

all: $(ALL_CLASSES)

# Define parameters to compile the individual Java classes in the Counters
# package.

Unit: Unit.java
	$(JAVA_OPTIONS) Unit.java

Fighting: Fighting.java $(OUTPUT_PATH)/Unit.class
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) Fighting.java

Mobile: Mobile.java $(OUTPUT_PATH)/Fighting.class
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) Mobile.java

Infantry: Infantry.java $(OUTPUT_PATH)/Mobile.class
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) Infantry.java

Squad: Squad.java $(OUTPUT_PATH)/Infantry.class
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) Squad.java

Leader: Leader.java $(OUTPUT_PATH)/Infantry.class
	$(JAVA_OPTIONS) $(CLASSPATH_CMD) Leader.java

Dice: Dice.java
	$(JAVA_OPTIONS) Dice.java

# Define a parameter to build all of the individual object files. 

gcj: $(GCJ_OBJECT_FILES)

# Define parameters to compile individual object files used to build a native
# executable with the gcj compiler.

Unit.o: Unit.java
	$(GCJ_OPTIONS) Unit.java
	$(MV) Unit.o $(OUTPUT_PATH)

Fighting.o: Fighting.java $(OUTPUT_PATH)/Unit.o
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) Fighting.java
	$(MV) Fighting.o $(OUTPUT_PATH)

Mobile.o: Mobile.java $(OUTPUT_PATH)/Fighting.o
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) Mobile.java
	$(MV) Mobile.o $(OUTPUT_PATH)

Infantry.o: Infantry.java $(OUTPUT_PATH)/Mobile.o
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) Infantry.java
	$(MV) Infantry.o $(OUTPUT_PATH)

Squad.o: Squad.java $(OUTPUT_PATH)/Infantry.o
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) Squad.java
	$(MV) Squad.o $(OUTPUT_PATH)

Leader.o: Leader.java $(OUTPUT_PATH)/Infantry.o
	$(GCJ_OPTIONS) $(GCJ_CLASSPATH_CMD) Leader.java
	$(MV) Leader.o $(OUTPUT_PATH)

Dice.o: Dice.java
	$(GCJ_OPTIONS) Dice.java
	$(MV) Dice.o $(OUTPUT_PATH)

# Define parameters to remove all of the class and object files from the output
# directory or a specific subset.

clean:
	$(RM) $(OUTPUT_PATH)/*.class $(RM) $(OUTPUT_PATH)/*.o

clean.class:
	$(RM) $(OUTPUT_PATH)/*.class

clean.o:
	$(RM) $(OUTPUT_PATH)/*.o

# Define a parameter to create HTML versions of the source files. Each file is
# converted using the java2html program, which generates an "image" of the file
# with syntax color coding. After the files are generated, the htls program is
# used to link the files together on an HTML page.

docs:
	@for i in $(ALL_CLASSES) ; do \
		$(JAVA2HTML) -input $$i.java -output $(DOCS_PATH)/$$i.html -title "$$i class" ;\
	done
	@$(HTLS) $(DOCS_PATH)
	@(cd ..;make docs)
