################################################################################
# Makefile - This make parameter file is used to build the bytecode (.class)   #
#            files for the counters package of the jASL program. It also       #
#            includes parameters to build object (.o) files using the gcj      #
#            compiler. In both cases, the files can compiled individually or   #
#            the entire package can be re-created.                             #
#                                                                              #
# Written By:  Craig R. Campbell  -  June 1999                                 #
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
# this package.

INTERFACES              := TextOutput.java Description.java Identity.java \
                           Nationality.java Status.java UnitType.java \
                           Mobility.java Firepower.java Morale.java \
                           Portability.java BasicPointValue.java \
                           ExperienceLevelRating.java Classification.java \
                           MaximumELR.java Leadership.java SprayingFire.java
SUPER_CLASSES           := Unit.java Fighting.java Mobile.java
INF_CLASSES             := Infantry.java Personnel.java Squad.java Leader.java

JASL_COUNTERS_SRC_FILES := $(INTERFACES) $(SUPER_CLASSES) $(INF_CLASSES)

GCJ_OBJECT_FILES        := ${JASL_COUNTERS_SRC_FILES:.java=.o}

JAVA_CLASSES            := ${JASL_COUNTERS_SRC_FILES:.java=}

# Build all of the individual class files.

%.class: %.java
	$(JAVA_OPTIONS) $^

.PHONY : all
all: bin_directory
	$(JAVA_OPTIONS) $(JASL_COUNTERS_SRC_FILES)

# Build all of the individual object files.

%.o: %.class
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/$^ -o $(OBJ_SUB_DIRECTORY)/$@

.PHONY : obj
obj: bin_directory obj_sub_directory $(GCJ_OBJECT_FILES)
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/Description\$$Descriptions.class \
	                   -o $(OBJ_SUB_DIRECTORY)/Description\$$Descriptions.o
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/Nationality\$$Nationalities.class \
	                   -o $(OBJ_SUB_DIRECTORY)/Nationality\$$Nationalities.o
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/Status\$$States.class \
	                   -o $(OBJ_SUB_DIRECTORY)/Status\$$States.o
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/UnitType\$$InfantryTypes.class \
	                   -o $(OBJ_SUB_DIRECTORY)/UnitType\$$InfantryTypes.o
	$(GCJ_COMPILE_CMD) $(COUNTERS_BIN_PATH)/Classification\$$Classifications.class \
	                   -o $(OBJ_SUB_DIRECTORY)/Classification\$$Classifications.o

# Build all of the individual header files.

.PHONY : hdr
hdr: include_directory
	$(GJAVAH_CMD) $(COUNTERS_BIN_PATH) -classpath $(BIN_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(COUNTERS_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).8
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).1

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

LOCAL_OBJ_FILES := $(OBJ_SUB_DIRECTORY)/*.o

.PHONY : libs
libs: dynamic_lib static_lib clean_obj

.PHONY : dynamic_lib
dynamic_lib: lib_directory obj
	$(GCJ_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(LOCAL_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: lib_directory obj
	$(AR) $(COUNTERS_STATIC_LIB_PATH) $(LOCAL_OBJ_FILES)

# Remove all of the .class and/or object files from the their respective output
# directory.

.PHONY : clean
clean: clean_java clean_obj

.PHONY : clean_java
clean_java:
	$(RM) $(COUNTERS_CLASS_FILES)

.PHONY : clean_obj
clean_obj:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove all of the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(COUNTERS_HDR_FILES)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(COUNTERS_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. Symbolic links are also created to each file from the doxygen
# documentation output directory. This is done so that the references in the
# doxygen files to the files generated by this target will work. After the files
# are generated, the htls program is used to link the files together on an HTML
# page.

COUNTERS_DOCS_PATH := $(SRC_DOCS_PATH)/$(COUNTERS_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(COUNTERS_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(COUNTERS_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(JAVA_CLASSES) ; do \
		$(CAT) $$i.java | $(JAVA2HTML) --title="$$i.java" | $(SED_CONV_CMD) > $(COUNTERS_DOCS_PATH)/$$i.html ;\
		if [ -d $(DOXYGEN_DOC_PATH)/html ]; then \
			(cd $(DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(COUNTERS_PKG_PATH)/$$i.html) ;\
		fi \
	done

	@# This link helps some of the relative links from other packages to work
	@# when accessed from Doxygen output.
	@(cd $(DOCS_PATH);$(LN) source/$(COUNTERS_PKG_PATH))

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(COUNTERS_DOCS_PATH)
	@$(HTLS) $(COUNTERS_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(COUNTERS_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
