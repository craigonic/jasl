################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the Driver program used to test the Counters package.   #
#            It also includes a parameter to build a native executable version #
#            of the same program using the gcj compiler.                       #
#                                                                              #
# Written By : Craig R. Campbell  -  August 1999                               #
#                                                                              #
# $Id: Makefile,v 1.9 2003/02/14 06:32:25 craig Exp $
################################################################################

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Directory specific build variables.

COUNTERS_OBJ_FILES = $(CLASSPATH)/$(COUNTERS_PACKAGE)/*.o

# Define variables to represent the source, object, and class files in/of/for
# this program.

DRIVER_CLASS       = Driver.class
DRIVER_EXEC        = ${DRIVER_CLASS:.class=}
DRIVER_MANIFEST    = ${DRIVER_CLASS:.class=.mf}
DRIVER_JAR         = ${DRIVER_CLASS:.class=.jar}

ALL_CLASSES        = $(DRIVER_CLASS)

GCJ_OBJECT_FILES   = $(COUNTERS_OBJ_FILES)

JAVA_SRC_FILES     = ${ALL_CLASSES:.class=.java}

JAVA_CLASSES       = ${ALL_CLASSES:.class=}

# Build the java version of the test program (Driver.class). This will also
# compile the files in the Counters subdirectory.

all build: $(ALL_CLASSES)

# Build the java version of the driver program used to test the classes defined
# in the Counters package.

$(DRIVER_CLASS):
	$(JAVA_OPTIONS) $(JAVA_SRC_FILES)

# Build both the java and native executable versions of the test program. Create
# a jar archive. The object files will be removed as part of the processing of
# the jar target to prevent them from being included in the jar archive.

build_all: gcj jar

# Build the complete native executable version of the test program (Driver),
# including the supporting files in the Counters package, using the gcj
# compiler. Note that the building of $(DRIVER_CLASS) will automatically cause
# all of the necessary .java files in the Counters subdirectory to be compiled
# as well. These files are necessary for the gcj compilation to work.

build_gcj: gcj clean_obj clean_class clean_java

# Build the .o files in each subdirectory. These are used to build the native
# executable. Note that use of this parameter requires that all of the
# .java files in each subdirectory have been compiled (i.e. .class files exist
# in $(CLASSPATH)/$(COUNTERS_PACKAGE)).

gcj_obj:
	@(cd $(COUNTERS_PACKAGE); make $@)

# Build the native executable version of the test program (Driver) using the gcj
# compiler. Note that use of this parameter requires that all of the .java files
# in the $(COUNTERS_PACKAGE) subdirectory have been compiled into both .class
# and .o files, which should exist in $(CLASSPATH)/$(COUNTERS_PACKAGE).

gcj: $(DRIVER_CLASS) gcj_obj $(DRIVER_EXEC)

# Build the native executable version of the driver program used to test the
# classes defined in the Counters package.

$(DRIVER_EXEC):
	$(GCJ_BUILD_CMD) --main=$(DRIVER_EXEC) -o $(CLASSPATH)/$(DRIVER_EXEC) $(JAVA_SRC_FILES) $(GCJ_OBJECT_FILES)

# Remove all of the existing program files from the output directory or a
# specific subset.

clean_all: clean_java clean_obj clean

clean_java clean_obj:
	@(cd $(COUNTERS_PACKAGE); make $@)

clean: clean_class clean_gcj clean_jar

clean_class:
	$(RM) $(CLASSPATH)/$(DRIVER_CLASS)

clean_gcj:
	$(RM) $(CLASSPATH)/$(DRIVER_EXEC)

clean_jar:
	$(RM) $(CLASSPATH)/$(DRIVER_JAR)

# Create HTML versions of the source files. Each file is converted using the
# java2html program, which generates an "image" of the file with syntax color
# coding. After the files are generated, the htls program is used to link the
# files together on an HTML page.

docs:
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$$i.html ;\
	done
	@(cd $(COUNTERS_PACKAGE); make $@)
	@$(HTLS) $(SRC_DOCS_PATH)

# Create HTML documentation using javadoc.

javadocs:
	@$(JAVADOC_COMPILER) $(JAVADOC_CMD_LIST) $(JAVA_SRC_FILES) \
    $(COUNTERS_PACKAGE)/*.java


# Create HTML documentation using doxygen.

doxygen:
	@$(DOXYGEN) $(DOXYGEN_DEF_FILE)

# Create an executable jar archive of the Driver program. Execution of this
# target will automatically remove any object files that exist in order to
# prevent them from being included in the jar archive file.

jar: clean_obj $(DRIVER_CLASS)
	@(cd $(CLASSPATH); jar cmf $(SRC_PATH)/$(DRIVER_MANIFEST) $(DRIVER_JAR) $(DRIVER_CLASS) $(COUNTERS_PACKAGE))
