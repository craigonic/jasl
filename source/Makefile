################################################################################
# Makefile - This make parameter file is used to build the executables (both   #
#            .class files and native (gcj)), documentation, and miscellaneous  #
#            files associated with the jASL program.                           #
#                                                                              #
# Written By : Craig R. Campbell  -  August 1999                               #
#                                                                              #
# $Id: Makefile,v 1.19 2008/09/08 20:08:07 campbell Exp $
################################################################################

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source, object, and class files in/of/for
# the Driver program.

DRIVER_CLASS       = Driver.class
DRIVER_CNI_SRC     = ${DRIVER_CLASS:.class=.cpp}
DRIVER_EXEC        = ${DRIVER_CLASS:.class=}
DRIVER_JAR         = ${DRIVER_CLASS:.class=.jar}
DRIVER_MANIFEST    = ${DRIVER_CLASS:.class=.mf}
DRIVER_OUT         = ${DRIVER_CLASS:.class=.out}
DRIVER_SRC         = ${DRIVER_CLASS:.class=.java}
DRIVER_TEST        = ${DRIVER_CLASS:.class=.test}

# The names of the output files generated when linking with the respective
# libraries in $(LIB_PATH).

DRIVER_DYNAMIC     = $(DRIVER_EXEC)_dynamic
DRIVER_STATIC      = $(DRIVER_EXEC)_static

DRIVER_CNI_DYNAMIC = $(DRIVER_EXEC)_cni_dynamic
DRIVER_CNI_STATIC  = $(DRIVER_EXEC)_cni_static

# Name and file collection variables.

ALL_CLASSES        = $(DRIVER_CLASS)

JAVA_SRC_FILES     = ${ALL_CLASSES:.class=.java}
JAVA_CLASSES       = ${ALL_CLASSES:.class=}

GCJ_OBJECT_FILES   = $(COUNTERS_OBJ_FILES)

## Build #######################################################################

# Build the java bytecode for all programs. This will also compile the files in
# the jasl directory and its sub-directories.

all build: $(ALL_CLASSES)

# Build both the java and native executable versions of all programs, along with
# a jar archive of the java files. The object files will be removed as part of
# the processing of the jar target to prevent them from being included in the
# jar archive.

build_all: gcj gcj_dynamic gcj_static cni_dynamic cni_static jar

# Build the native executable version of all programs, removing all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj compilation to work.

build_gcj: gcj clean_obj clean_class clean_java

# Build the native executable version of all programs, linking with one or more
# of the dynamic libraries in $(LIB_PATH), and remove upon completion all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj link process to work.

build_gcj_dynamic: gcj_dynamic clean_class clean_java

# Build the native executable version of all programs, linking with one or more
# of the static libraries in $(LIB_PATH), and remove upon completion all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj link process to work.

build_gcj_static: gcj_static clean_class clean_java

# Build the .o files in each subdirectory. These are used to build the native
# executable(s). Note that use of this target requires that all of the .java
# files in each subdirectory have been compiled (i.e. .class files exist in
# ${JASL_BASE}/bin and its sub-directories.

gcj_obj:
	@(cd $(COUNTERS_PKG_PATH); $(MAKE) $@)

# Build the native executable version of all programs, using the gcj compiler.
# Note that use of this target requires that all of the .java files in the jasl
# directory and its sub-directories have been compiled into both .class and .o
# files. These files should exist in ${JASL_BASE}/bin and its sub-directories.

gcj: gcj_obj $(DRIVER_EXEC)

# Build the native executable version of all programs, using the gcj compiler
# and linking with one or more of the dynamic libraries in $(LIB_PATH). Note
# that use of this target requires that all of the .java files in the jasl
# directory and its sub-directories have been compiled into .class files. These
# files should exist in ${JASL_BASE}/bin and its sub-directories.

gcj_dynamic: $(DRIVER_DYNAMIC)

# Build the native executable version of all programs, using the gcj compiler
# and linking with one or more of the static libraries in $(LIB_PATH). Note that
# use of this target requires that all of the .java files in the jasl directory
# and its sub-directories have been compiled into .class files. These files
# should exist in ${JASL_BASE}/bin and its sub-directories.

gcj_static: $(DRIVER_STATIC)

# Build the native executable version, specifically the C++ source file(s) for
# all programs, using the gcc compiler and linking with the cni-wrapper dynamic
# library and one or more of the jasl dynamic libraries in $(LIB_PATH).

cni_dynamic: $(DRIVER_CNI_DYNAMIC)

# Build the native executable version, specifically the C++ source file(s) for
# all programs, using the gcc compiler and linking with the cni-wrapper static
# library and one or more of the jasl static libraries in $(LIB_PATH).

cni_static: $(DRIVER_CNI_STATIC)

# Build the java version of the Driver program, which is used to test the
# classes defined in the jasl directory and its sub-directories.

$(DRIVER_CLASS):
	@(cd $(COUNTERS_PKG_PATH); $(MAKE))
	$(JAVA_OPTIONS) $(DRIVER_SRC)

# Build the native executable versions of the Driver program, which is used to
# test the classes defined in the jasl directory and its sub-directories.

# Options common to building all forms of the native executable version of the
# Driver program.

DRIVER_BUILD_OPTIONS = --main=$(DRIVER_EXEC) -o $(BIN_PATH)/

# Build and link with the individual object files.

$(DRIVER_EXEC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_EXEC) $(DRIVER_SRC) \
	                 $(GCJ_OBJECT_FILES)

# Build and link with the dynamic libraries.

$(DRIVER_DYNAMIC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_DYNAMIC) $(DRIVER_SRC) \
	                 -L$(LIB_PATH) -l$(COUNTERS_BASE_LIB_NAME)

# Build and link with the static libraries.

$(DRIVER_STATIC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_STATIC) $(DRIVER_SRC) \
	                 $(COUNTERS_STATIC_LIB_PATH)

# Build the C++ version of the Driver program, which uses the Compiled Native
# Interface (CNI) wrapper, and is used to test the classes defined in the jasl
# directory and its sub-directories.

# Options common to building both forms of the C++ version of the Driver
# program.

DRIVER_CNI_BUILD_OPTIONS = -I$(INCLUDE_PATH) -o $(BIN_PATH)/
DRIVER_CNI_DYNAMIC_LIBS  = -lgcj -lstdc++

# Build and link with the dynamic libraries.

$(DRIVER_CNI_DYNAMIC):
	$(GCC_BUILD_CMD) $(DRIVER_CNI_BUILD_OPTIONS)$(DRIVER_CNI_DYNAMIC) \
	                 $(DRIVER_CNI_SRC) -L$(LIB_PATH) \
	                 $(DRIVER_CNI_DYNAMIC_LIBS) \
	                 -l$(CNI_WRAPPER_BASE_LIB_NAME) \
	                 -l$(COUNTERS_BASE_LIB_NAME)

# Build and link with the static libraries.

$(DRIVER_CNI_STATIC):
	$(GCC_BUILD_CMD) $(DRIVER_CNI_BUILD_OPTIONS)$(DRIVER_CNI_STATIC) \
	                 $(DRIVER_CNI_SRC) $(DRIVER_CNI_DYNAMIC_LIBS) \
	                 $(CNI_WRAPPER_STATIC_LIB_PATH) $(COUNTERS_STATIC_LIB_PATH)

## Clean #######################################################################

# Remove all of the existing program files from the output directory or a
# specific subset.

clean_all: clean_java clean_obj clean

clean_java clean_obj:
	@(cd $(COUNTERS_PKG_PATH); $(MAKE) $@)

clean: clean_class clean_gcj clean_gcj_dynamic clean_gcj_static \
       clean_cni_dynamic clean_cni_static clean_jar

clean_class:
	$(RM) $(BIN_PATH)/$(DRIVER_CLASS)

clean_gcj:
	$(RM) $(BIN_PATH)/$(DRIVER_EXEC)

clean_gcj_dynamic:
	$(RM) $(BIN_PATH)/$(DRIVER_DYNAMIC)

clean_gcj_static:
	$(RM) $(BIN_PATH)/$(DRIVER_STATIC)

clean_cni_dynamic:
	$(RM) $(BIN_PATH)/$(DRIVER_CNI_DYNAMIC)

clean_cni_static:
	$(RM) $(BIN_PATH)/$(DRIVER_CNI_STATIC)

clean_jar:
	$(RM) $(BIN_PATH)/$(DRIVER_JAR)

## Test ########################################################################

# Run the Driver program in its various forms and compare the output to the
# previously saved result (Driver.out).

TEST_EXEC = $(JAVA_RUN_CMD) $(DRIVER_EXEC)

run_test:
	@$(DATE) > $(DRIVER_TEST)
	@(cd $(BIN_PATH); $(TEST_EXEC) >> $(SRC_PATH)/$(DRIVER_TEST))
	@-$(DIFF) $(DRIVER_OUT) $(DRIVER_TEST)
	@$(RM) $(DRIVER_TEST)

test: $(BIN_PATH)/$(DRIVER_CLASS) run_test

test_gcj: TEST_EXEC = ./$(DRIVER_EXEC)
test_gcj: $(BIN_PATH)/$(DRIVER_EXEC) run_test

test_gcj_dynamic: TEST_EXEC = /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH) ./$(DRIVER_DYNAMIC)"
test_gcj_dynamic: $(BIN_PATH)/$(DRIVER_DYNAMIC) run_test

test_gcj_static: TEST_EXEC = ./$(DRIVER_STATIC)
test_gcj_static: $(BIN_PATH)/$(DRIVER_STATIC) run_test

test_cni_dynamic: TEST_EXEC = /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH) ./$(DRIVER_CNI_DYNAMIC)"
test_cni_dynamic: $(BIN_PATH)/$(DRIVER_CNI_DYNAMIC) run_test

test_cni_static: TEST_EXEC = ./$(DRIVER_CNI_STATIC)
test_cni_static: $(BIN_PATH)/$(DRIVER_CNI_STATIC) run_test

test_jar: TEST_EXEC = $(JAR_RUN_CMD) $(DRIVER_JAR)
test_jar: $(BIN_PATH)/$(DRIVER_JAR) run_test

## Documentation ###############################################################

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

docs:
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SRC_DOCS_PATH)/$(MAKEFILE).html
	@$(CAT) $(DRIVER_CNI_SRC) | $(CPP2HTML) --title="$(DRIVER_CNI_SRC)" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(DRIVER_CNI_SRC).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$$i.html ;\
	done
	@(cd $(CNI_DIRECTORY); $(MAKE) $@)
	@(cd $(SWIG_DIRECTORY); $(MAKE) $@)
	@(cd $(COUNTERS_PKG_PATH); $(MAKE) $@)
	@$(HTLS) $(SRC_DOCS_PATH)

# Create HTML documentation using javadoc.

javadocs:
	@$(RM_RECURSIVE) $(JAVADOCS_PATH)
	@$(JAVADOC_COMPILER) $(JAVADOC_CMD_LIST) $(COUNTERS_SRC_FILES)

# Create HTML documentation using gjdoc.

gjdocs:
	@$(RM_RECURSIVE) $(JAVADOCS_PATH)
	@$(GJDOC) $(JAVADOC_CMD_LIST) $(COUNTERS_SRC_FILES)

# Create HTML documentation using doxygen.

doxygen:
	@$(RM_RECURSIVE) $(DOXYGEN_DOC_PATH)
	@$(DOXYGEN) $(DOXYGEN_DEF_FILE)

# Create all the forms of documentation, except gjdoc, which places its output
# in the same directory as javadoc.

all_docs: javadocs doxygen docs

## Miscellaneous ###############################################################

# Create an index of all of the language objects in the source code. This is
# used to enable and simplify source code navigation from most text editors.

ctags:
	@$(CTAGS_BUILD_CMD) $(CNI_WRAPPER_SRC_FILES) $(COUNTERS_SRC_FILES)

# Create an executable jar archive of the Driver program. Execution of this
# target will automatically remove any object files that exist in order to
# prevent them from being included in the jar archive file.

jar: clean_obj $(DRIVER_CLASS)
	@(cd $(BIN_PATH); $(JAR_BUILD_CMD) $(SRC_PATH)/$(DRIVER_MANIFEST) $(DRIVER_JAR) $(DRIVER_CLASS) $(ALL_PACKAGES))
