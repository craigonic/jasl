################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Driver program used to test the Counters package.   #
#            It also includes a parameter to build a native executable version #
#            of the same program using the gcj compiler.                       #
#                                                                              #
# Written By    : Craig R. Campbell  -  August 1999                            #
#                                                                              #
# $Header: /tmp/java/jasl.cvs/jasl/source/Makefile,v 1.5 2001/03/22 06:03:11 craig Exp $
################################################################################

# Define variables.

JAVA_COMPILER     = javac
GCJ_COMPILER      = gcj

HTLS              = htls -genpage -index
JAVA2HTML         = java2html -doc

COUNTERS_PACKAGE  = Counters

CLASSPATH         = ../jasl
OUTPUT_PATH       = $(CLASSPATH)
OBJS_PATH         = $(CLASSPATH)/$(COUNTERS_PACKAGE)
DOCS_PATH         = ../docs

CLASSPATH_CMD     = -classpath $(CLASSPATH)
GCJ_CLASSPATH_CMD = --CLASSPATH=$(CLASSPATH)
OUTPUT_DIR_CMD    = -d $(CLASSPATH)

#OPTIMIZE          = -O

JAVA_OPTIONS      = $(JAVA_COMPILER) $(OPTIMIZE) $(OUTPUT_DIR_CMD)
GCJ_OPTIONS       = $(GCJ_COMPILER) $(GCJ_CLASSPATH_CMD)

DRIVER_CLASS      = Driver.class
DRIVER_EXEC       = ${DRIVER_CLASS:.class=}

ALL_CLASSES       = $(DRIVER_CLASS)

GCJ_OBJECT_FILES  = $(OBJS_PATH)/*.o

JAVA_SRC_FILES    = ${DRIVER_CLASS:.class=.java}

# Define system commands.

MV                = /bin/mv

# Define a parameter to build the java version of the test program
# (Driver.class).

all: $(ALL_CLASSES)

# Define a parameter to build both the java and native executable versions of
# the test program. Remove the object files upon completion.

build_all: build_gcj
	@(make clean_obj)

# Define parameters to build the .class files in the Counters subdirectory for
# use with the java version of the driver OR the object files used to build the
# native executable. With either parameter, all of the .class files in Counters
# will be created and the java version of the driver program will be generated.

build gcj_obj: $(DRIVER_CLASS)
	@(cd $(COUNTERS_PACKAGE); make $@)

# Define a parameter to build the java version of the driver program used to
# test the classes defined in the Counters package.

$(DRIVER_CLASS): $(JAVA_SRC_FILES)
	$(JAVA_OPTIONS) $(JAVA_SRC_FILES)

# Define a parameter to build the native executable version of the test program
# (Driver) using the gcj compiler.

gcj: $(DRIVER_EXEC)

# Define a parameter to build the complete native executable version of the
# test program (Driver), including the supporting files in the Counters package,
# using the gcj compiler

build_gcj: gcj_obj gcj

# Define a parameter to build the native executable version of the driver
# program used to test the classes defined in the Counters package.

$(DRIVER_EXEC): $(JAVA_SRC_FILES)
	$(GCJ_OPTIONS) --main=$(DRIVER_EXEC) -o $(OUTPUT_PATH)/$(DRIVER_EXEC) $(JAVA_SRC_FILES) $(GCJ_OBJECT_FILES)

# Define parameters to remove all of the existing program files from the output
# directory or a specific subset.

clean_all: clean_java clean_obj clean

clean_java clean_obj:
	@(cd $(COUNTERS_PACKAGE); make $@)

clean: clean_class clean_gcj

clean_class:
	$(RM) $(OUTPUT_PATH)/$(DRIVER_CLASS)

clean_gcj:
	$(RM) $(OUTPUT_PATH)/$(DRIVER_EXEC)

# Define a parameter to create HTML versions of the source files. Each file is
# converted using the java2html program, which generates an "image" of the file
# with syntax color coding. After the files are generated, the htls program is
# used to link the files together on an HTML page.

docs:
	@for i in $(JAVA_SRC_FILES) ; do \
		$(JAVA2HTML) -input $$i -output $(DOCS_PATH)/$$i.html -title "$$i" ;\
	done
	@(cd $(COUNTERS_PACKAGE); make $@)
	@$(HTLS) $(DOCS_PATH)
