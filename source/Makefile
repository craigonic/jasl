################################################################################
# Makefile - This make parameter file is used to build the executables (both   #
#            .class files and native (gcj)), documentation, and miscellaneous  #
#            files associated with the jASL program.                           #
#                                                                              #
# Written By : Craig R. Campbell  -  August 1999                               #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : all

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source, object, and class files in/of/for
# the Driver program.

DRIVER_CLASS       := Driver.class
DRIVER_CNI_SRC     := ${DRIVER_CLASS:.class=.cpp}
DRIVER_PERL_SRC    := ${DRIVER_CLASS:.class=.pl}
DRIVER_PYTHON_SRC  := ${DRIVER_CLASS:.class=.py}
DRIVER_EXEC        := ${DRIVER_CLASS:.class=}
DRIVER_MANIFEST    := ${DRIVER_CLASS:.class=.mf}
DRIVER_OUT         := ${DRIVER_CLASS:.class=.out}
DRIVER_SRC         := ${DRIVER_CLASS:.class=.java}
DRIVER_TEST        := ${DRIVER_CLASS:.class=.test}

# The names of the output files generated when linking with the respective
# libraries in $(LIB_PATH).

DRIVER_DYNAMIC     := $(DRIVER_EXEC)_dynamic
DRIVER_STATIC      := $(DRIVER_EXEC)_static

DRIVER_CNI_DYNAMIC := $(DRIVER_EXEC)_cni_dynamic
DRIVER_CNI_STATIC  := $(DRIVER_EXEC)_cni_static

# The name of the jar archive containing the compiled .class files from within
# the jasl package space, excluding those that are specific to a user interface
# (e.g. Android). The file also includes the Driver.class for unit testing.

JASL_JAR_ARCHIVE   := org.craigonic.jasl.jar

# Name and file collection variables.

ALL_CLASSES        := $(DRIVER_CLASS)

JAVA_SRC_FILES     := ${ALL_CLASSES:.class=.java}
JAVA_CLASSES       := ${ALL_CLASSES:.class=}

GCJ_OBJECT_FILES   := $(COUNTERS_OBJ_FILES) $(UTILITIES_OBJ_FILES)

## Build #######################################################################

# Build the java bytecode for all programs. This will also compile the files in
# the jasl directory and its sub-directories.

.PHONY : all
all: $(ALL_CLASSES)

# Build both the java and native executable versions of all programs, along with
# a jar archive of the Java .class files.

.PHONY : build_all
build_all: gcj gcj_dynamic gcj_static build_cni_dynamic build_cni_static $(JASL_JAR_ARCHIVE)

# Build the jar archive(s) and remove all .class files in this directory and its
# sub-directories.

.PHONY : build_jar
build_jar: $(JASL_JAR_ARCHIVE) clean_class clean_java

# Build the native executable version of all programs, removing all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj compilation to work.

.PHONY : build_gcj
build_gcj: gcj clean_obj clean_class clean_java

# Build the native executable version of all programs, linking with one or more
# of the dynamic libraries in $(LIB_PATH), and remove upon completion all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj link process to work.

.PHONY : build_gcj_dynamic
build_gcj_dynamic: gcj_dynamic clean_class clean_java

# Build the native executable version of all programs, linking with one or more
# of the static libraries in $(LIB_PATH), and remove upon completion all of the
# intermediate files generated to build each one. These files are necessary for
# the gcj link process to work.

.PHONY : build_gcj_static
build_gcj_static: gcj_static clean_class clean_java

# Build the native executable version, specifically the C++ source file(s) for
# all programs, using the gcc compiler and linking with the cni-wrapper dynamic
# library and one or more of the jasl dynamic libraries in $(LIB_PATH).

.PHONY : build_cni_dynamic
build_cni_dynamic: $(DRIVER_CNI_DYNAMIC)

# Build the native executable version, specifically the C++ source file(s) for
# all programs, using the gcc compiler and linking with the cni-wrapper static
# library and one or more of the jasl static libraries in $(LIB_PATH).

.PHONY : build_cni_static
build_cni_static: $(DRIVER_CNI_STATIC)

# Build the .o files in each subdirectory. These are used to build the native
# executable(s). Note that use of this target requires that all of the .java
# files in each subdirectory have been compiled (i.e. .class files exist in
# ${JASL_BASE}/bin and its sub-directories.

.PHONY : obj
obj:
	$(MAKE) -C $(PROGRAM_NAME) $@

# Build the native executable version of all programs, using the gcj compiler.
# Note that use of this target requires that all of the .java files in the jasl
# directory and its sub-directories have been compiled into both .class and .o
# files. These files should exist in ${JASL_BASE}/bin and its sub-directories.

.PHONY : gcj
gcj: obj $(DRIVER_EXEC)

# Build the native executable version of all programs, using the gcj compiler
# and linking with one or more of the dynamic libraries in $(LIB_PATH). Note
# that use of this target requires that all of the .java files in the jasl
# directory and its sub-directories have been compiled into .class files. These
# files should exist in ${JASL_BASE}/bin and its sub-directories.

.PHONY : gcj_dynamic
gcj_dynamic: $(DRIVER_DYNAMIC)

# Build the native executable version of all programs, using the gcj compiler
# and linking with one or more of the static libraries in $(LIB_PATH). Note that
# use of this target requires that all of the .java files in the jasl directory
# and its sub-directories have been compiled into .class files. These files
# should exist in ${JASL_BASE}/bin and its sub-directories.

.PHONY : gcj_static
gcj_static: $(DRIVER_STATIC)

# Build the java version of the Driver program, which is used to test the
# classes defined in the jasl directory and its sub-directories.

$(DRIVER_CLASS): bin_directory
	$(MAKE) -C $(PROGRAM_NAME)
	$(JAVA_OPTIONS) $(DRIVER_SRC)

# Create an executable jar archive that includes all of the .class files
# generated from the source files within the jasl package hierarchy, with the
# exception of those exclusive to a specific user interface (e.g. Android). The
# Driver program is also included for unit testing.

$(JASL_JAR_ARCHIVE): $(DRIVER_CLASS)
	@(cd $(BIN_PATH); \
	  $(JAR_BUILD_CMD) $(SRC_PATH)/$(DRIVER_MANIFEST) \
	                   $(JASL_JAR_ARCHIVE) $(DRIVER_CLASS) $(ALL_PACKAGES))

# Build the native executable versions of the Driver program, which is used to
# test the classes defined in the jasl directory and its sub-directories.

# Options common to building all forms of the native executable version of the
# Driver program.

DRIVER_BUILD_OPTIONS := --main=$(DRIVER_EXEC) -o $(BIN_PATH)/

# Build and link with the individual object files.

$(DRIVER_EXEC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_EXEC) $(DRIVER_SRC) \
	                 $(GCJ_OBJECT_FILES)

# Build and link with the dynamic libraries.

$(DRIVER_DYNAMIC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_DYNAMIC) $(DRIVER_SRC) \
	                 -L$(LIB_PATH) -l$(COUNTERS_BASE_LIB_NAME) \
	                 -l$(UTILITIES_BASE_LIB_NAME)

# Build and link with the static libraries.

$(DRIVER_STATIC): $(DRIVER_CLASS)
	$(GCJ_BUILD_CMD) $(DRIVER_BUILD_OPTIONS)$(DRIVER_STATIC) $(DRIVER_SRC) \
	                 $(COUNTERS_STATIC_LIB_PATH) $(UTILITIES_STATIC_LIB_PATH)

# Build the C++ version of the Driver program, which uses the Compiled Native
# Interface (CNI) wrapper, and is used to test the classes defined in the jasl
# directory and its sub-directories.

# Options common to building both forms of the C++ version of the Driver
# program.

DRIVER_CNI_BUILD_OPTIONS := -I$(INCLUDE_PATH) -I$(CNI_HDR_PATH) -o $(BIN_PATH)/
DRIVER_CNI_DYNAMIC_LIBS  := -lgcj -lstdc++

# Build and link with the dynamic libraries.

$(DRIVER_CNI_DYNAMIC): bin_directory
	$(GCC_BUILD_CMD) $(DRIVER_CNI_BUILD_OPTIONS)$(DRIVER_CNI_DYNAMIC) \
	                 $(DRIVER_CNI_SRC) -L$(LIB_PATH) \
	                 $(DRIVER_CNI_DYNAMIC_LIBS) \
	                 -l$(CNI_WRAPPER_BASE_LIB_NAME) \
	                 -l$(COUNTERS_BASE_LIB_NAME) \
	                 -l$(UTILITIES_BASE_LIB_NAME)

# Build and link with the static libraries.

$(DRIVER_CNI_STATIC): bin_directory
	$(GCC_BUILD_CMD) $(DRIVER_CNI_BUILD_OPTIONS)$(DRIVER_CNI_STATIC) \
	                 $(DRIVER_CNI_SRC) $(DRIVER_CNI_DYNAMIC_LIBS) \
	                 $(CNI_WRAPPER_STATIC_LIB_PATH) \
	                 $(COUNTERS_STATIC_LIB_PATH) $(UTILITIES_STATIC_LIB_PATH)

## Install #####################################################################

# Install the respective script versions of the Driver program, which use
# libraries and files generated by SWIG to test the classes defined in the jasl
# directory and its sub-directories.

.PHONY : perl
perl: $(DRIVER_PERL_SRC) perl_bin_directory
	$(INSTALL_EXE_FILE) $(DRIVER_PERL_SRC) $(PERL_BIN_PATH)/$(DRIVER_EXEC)

.PHONY : python
python: $(DRIVER_PYTHON_SRC) python_bin_directory
	$(INSTALL_STD_FILE) $(DRIVER_PYTHON_SRC) $(PYTHON_BIN_PATH)/$(DRIVER_EXEC)

## Clean #######################################################################

# Remove all of the existing program files from the output directory or a
# specific subset.

.PHONY : clean_all
clean_all: clean_java clean_obj clean

.PHONY : clean_java
clean_java:
	$(RM_RECURSIVE) ${JASL_BASE}/bin/$(PROGRAM_NAME)

.PHONY : clean_obj
clean_obj:
	$(MAKE) -C $(PROGRAM_NAME) $@

.PHONY : clean
clean: clean_class clean_jar clean_gcj clean_gcj_dynamic clean_gcj_static \
       clean_cni_dynamic clean_cni_static clean_perl clean_python

.PHONY : clean_class
clean_class:
	$(RM) $(BIN_PATH)/$(DRIVER_CLASS)

.PHONY : clean_jar
clean_jar:
	$(RM) $(BIN_PATH)/$(JASL_JAR_ARCHIVE)

.PHONY : clean_gcj
clean_gcj:
	$(RM) $(BIN_PATH)/$(DRIVER_EXEC)

.PHONY : clean_gcj_dynamic
clean_gcj_dynamic:
	$(RM) $(BIN_PATH)/$(DRIVER_DYNAMIC)

.PHONY : clean_gcj_static
clean_gcj_static:
	$(RM) $(BIN_PATH)/$(DRIVER_STATIC)

.PHONY : clean_cni_dynamic
clean_cni_dynamic:
	$(RM) $(BIN_PATH)/$(DRIVER_CNI_DYNAMIC)

.PHONY : clean_cni_static
clean_cni_static:
	$(RM) $(BIN_PATH)/$(DRIVER_CNI_STATIC)

.PHONY : clean_perl
clean_perl:
	$(RM) $(PERL_BIN_PATH)/$(DRIVER_EXEC)

.PHONY : clean_python
clean_python:
	$(RM) $(PYTHON_BIN_PATH)/$(DRIVER_EXEC)

## Test ########################################################################

# Run the Driver program in its various forms and compare the output to the
# previously saved result (Driver.out).

TEST_EXEC := $(JAVA_RUN_CMD) $(DRIVER_EXEC)

.PHONY : run_test
run_test:
	@$(DATE) > $(DRIVER_TEST)
	@(cd $(BIN_PATH); $(TEST_EXEC) >> $(SRC_PATH)/$(DRIVER_TEST))
	@-$(DIFF) $(DRIVER_OUT) $(DRIVER_TEST)
	@$(RM) $(DRIVER_TEST)

.PHONY : test
test: $(BIN_PATH)/$(DRIVER_CLASS) run_test

.PHONY : test_jar
test_jar: TEST_EXEC := $(JAR_RUN_CMD) $(JASL_JAR_ARCHIVE)
test_jar: $(BIN_PATH)/$(JASL_JAR_ARCHIVE) run_test

.PHONY : test_gcj
test_gcj: TEST_EXEC := ./$(DRIVER_EXEC)
test_gcj: $(BIN_PATH)/$(DRIVER_EXEC) run_test

.PHONY : test_gcj_dynamic
test_gcj_dynamic: TEST_EXEC := /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH) ./$(DRIVER_DYNAMIC)"
test_gcj_dynamic: $(BIN_PATH)/$(DRIVER_DYNAMIC) run_test

.PHONY : test_gcj_static
test_gcj_static: TEST_EXEC := ./$(DRIVER_STATIC)
test_gcj_static: $(BIN_PATH)/$(DRIVER_STATIC) run_test

.PHONY : test_cni_dynamic
test_cni_dynamic: TEST_EXEC := /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH) ./$(DRIVER_CNI_DYNAMIC)"
test_cni_dynamic: $(BIN_PATH)/$(DRIVER_CNI_DYNAMIC) run_test

.PHONY : test_cni_static
test_cni_static: TEST_EXEC := ./$(DRIVER_CNI_STATIC)
test_cni_static: $(BIN_PATH)/$(DRIVER_CNI_STATIC) run_test

.PHONY : test_perl
test_perl: TEST_EXEC := /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH):$(PERL_LIB_PATH) ./$(DRIVER_EXEC)"
test_perl: $(PERL_BIN_PATH)/$(DRIVER_EXEC)
	@(cd $(PERL_BIN_PATH); $(TEST_EXEC))

.PHONY : test_python
test_python: TEST_EXEC := /bin/sh -c "LD_LIBRARY_PATH=$(LIB_PATH):$(PYTHON_LIB_PATH) python ./$(DRIVER_EXEC)"
test_python: $(PYTHON_BIN_PATH)/$(DRIVER_EXEC)
	@(cd $(PYTHON_BIN_PATH); $(TEST_EXEC))

## Documentation ###############################################################

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(SRC_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SRC_DOCS_PATH)/$(MAKEFILE).html
	@$(CAT) $(DRIVER_CNI_SRC) | $(CPP2HTML) --title="$(DRIVER_CNI_SRC)" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(DRIVER_CNI_SRC).html
	@$(CAT) $(DRIVER_PERL_SRC) | $(PERL2HTML) --title="$(DRIVER_PERL_SRC)" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(DRIVER_PERL_SRC).html
	@$(CAT) $(DRIVER_PYTHON_SRC) | $(PYTHON2HTML) --title="$(DRIVER_PYTHON_SRC)" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(DRIVER_PYTHON_SRC).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$$i.html ;\
	done
	$(MAKE) -C $(CNI_DIRECTORY) $@
	$(MAKE) -C $(SWIG_DIRECTORY) $@
	$(MAKE) -C $(PROGRAM_NAME) $@
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(SRC_DOCS_PATH)
	@$(HTLS) $(SRC_DOCS_PATH)
	@$(INSTALL_STD_FILE) vim.png $(SRC_DOCS_PATH)

	# This link is necessary in order for the "Source code" links in the
	# Doxygen output to be able to open the files generated using
	# source-highlight.

	@$(LN) doc/source $(INSTALL_DIRECTORY)/share/$(PROGRAM_NAME)/source

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C $(CNI_DIRECTORY) $@
	$(MAKE) -C $(SWIG_DIRECTORY) $@
	$(MAKE) -C $(PROGRAM_NAME) $@
	@$(RM) $(SRC_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs

# Create HTML documentation using javadoc.

.PHONY : javadocs
javadocs: docs_directory
	@$(RM_RECURSIVE) $(JAVADOCS_PATH)
	@$(JAVADOC_COMPILER) $(JAVADOC_CMD_LIST) $(COUNTERS_SRC_FILES) \
	                     $(UTILITIES_SRC_FILES) $(UI_DATA_SRC_FILES)

# Create HTML documentation using gjdoc.

.PHONY : gjdocs
gjdocs: docs_directory
	@$(RM_RECURSIVE) $(JAVADOCS_PATH)
	@$(GJDOC) $(JAVADOC_CMD_LIST) $(COUNTERS_SRC_FILES) \
	          $(UTILITIES_SRC_FILES) $(UI_DATA_SRC_FILES)

# Create HTML documentation using doxygen.

.PHONY : doxygen
doxygen: docs_directory
	@$(RM_RECURSIVE) $(DOXYGEN_DOC_PATH)
	@$(DOXYGEN) $(DOXYGEN_DEF_FILE)

# Create HTML documentation using htags.

.PHONY : htags
htags: global
	@$(RM_RECURSIVE) $(HTAGS_OUTPUT_PATH)
	@$(INSTALL_DIR) $(HTAGS_DOCS_PATH)
	@$(HTAGS) $(HTAGS_OPTIONS)
	@$(HTAGS_FIND_CNV_CMD)
	@$(HTAGS_ADDLINKS_CMD)

# Create all the forms of documentation, except gjdoc, which places its output
# in the same directory as javadoc.

all_docs: javadocs doxygen htags docs

## Miscellaneous ###############################################################

# Create an index of all of the language objects in the source code. This is
# used to enable and simplify source code navigation from most text editors.

.PHONY : ctags
ctags:
	@$(CTAGS_BUILD_CMD) $(CNI_WRAPPER_SRC_FILES) $(COUNTERS_SRC_FILES) \
	                    $(UTILITIES_SRC_FILES) $(UI_DATA_SRC_FILES)

# Create another source file symbol index. This target generates a set of files,
# which are stored in the gtags sub-directory, that allow command line queries
# with global to find the file(s) where the specified symbol (e.g. class,
# method, or member name) is found. The files are also used by htags (see target
# above) to generate (yet another) means to navigate through the source with a
# web browser.
#
# Note that in order for the global and htags targets to work correctly, the
# following environment variables must be set as indicated, as well as having
# GNU GLOBAL installed in the first place.
#
#     export GTAGSROOT=$JASL_BASE/source
#     export GTAGSDBPATH=$GTAGSROOT/gtags

.PHONY : global
global:
	@$(INSTALL_DIR) $(GTAGS_PATH)
	@$(GTAGS_FIND) | $(GTAGS) $(GTAGS_OPTIONS) $(GTAGS_PATH)
