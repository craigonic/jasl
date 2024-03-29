################################################################################
# Makefile - This make parameter file is used to build the Java Native         #
#            Interface (JNI) wrapper library, which is used to create a Java   #
#            Virtual Machine (JVM) to enable the execution of Java code from a #
#            C++ program.                                                      #
#                                                                              #
# Written By: Craig R. Campbell  -  July 2018                                  #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : libs

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source and object files in/of/for this
# library.

JNI_WRAPPER_SRC        := JniWrapper.cpp
JNI_WRAPPER_HDR        := ${JNI_WRAPPER_SRC:.cpp=.h}
JNI_WRAPPER_OBJ        := ${JNI_WRAPPER_SRC:.cpp=.o}

#JASL_ERROR_MESSAGE_SRC := JaslErrorMessage.cpp
#JASL_ERROR_MESSAGE_HDR := ${JASL_ERROR_MESSAGE_SRC:.cpp=.h}
#JASL_ERROR_MESSAGE_OBJ := ${JASL_ERROR_MESSAGE_SRC:.cpp=.o}

# This header file is not part of the library. It provides typedef declarations
# to simplify access to classes in the jasl libraries through programs that use
# it.

CPP_FILES := $(JNI_WRAPPER_SRC) # $(JASL_ERROR_MESSAGE_SRC)

HDR_FILES := ${CPP_FILES:.cpp=.h}
OBJ_FILES := ${CPP_FILES:.cpp=.o}
SRC_FILES := $(CPP_FILES) $(HDR_FILES)

# Compile individual object files.

$(JNI_WRAPPER_OBJ): $(JNI_WRAPPER_HDR) $(JNI_WRAPPER_SRC)
	$(JNI_BUILD_CMD) -c $(JNI_WRAPPER_SRC) \
	                 -o $(OBJ_SUB_DIRECTORY)/$(JNI_WRAPPER_OBJ)

#$(JASL_ERROR_MESSAGE_OBJ): $(JASL_ERROR_MESSAGE_HDR) $(JASL_ERROR_MESSAGE_SRC)
#	$(GCC_COMPILE_CMD) $(JASL_ERROR_MESSAGE_SRC) \
#	                   -o $(OBJ_SUB_DIRECTORY)/$(JASL_ERROR_MESSAGE_OBJ)

# Build all of the object (.o) files.

.PHONY : obj
obj: obj_sub_directory $(OBJ_FILES)

# Install the header (.h) files in the appropriate include directory.

.PHONY : hdr
hdr: $(HDR_FILES)
	$(INSTALL_DIR) $(JNI_HDR_PATH)
	$(INSTALL_STD_FILE) $(HDR_FILES) $(JNI_HDR_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(JNI_WRAPPER_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).1

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

JNI_WRAPPER_OBJ_FILES := $(OBJ_SUB_DIRECTORY)/*.o

.PHONY : libs
libs: dynamic_lib static_lib clean

.PHONY : dynamic_lib
dynamic_lib: obj lib_directory
	$(JNI_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) $(JNI_WRAPPER_OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: obj lib_directory
	$(AR) $(JNI_WRAPPER_STATIC_LIB_PATH) $(JNI_WRAPPER_OBJ_FILES)

# Remove the object files from this directory.

.PHONY : clean
clean:
	$(RM_RECURSIVE) $(OBJ_SUB_DIRECTORY)

# Remove the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(JNI_WRAPPER_HDR_FILES)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(JNI_WRAPPER_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

JNI_DOCS_PATH := $(SRC_DOCS_PATH)/$(JNI_DIRECTORY)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JNI_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JNI_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(SRC_FILES) ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(JNI_DOCS_PATH)/$$i.html ;\
	done

	$(MAKE) -C $(PROGRAM_NAME) $@

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JNI_DOCS_PATH)
	@$(HTLS) $(JNI_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C $(PROGRAM_NAME) $@
	@$(RM) $(JNI_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs

# Create HTML documentation using doxygen.

.PHONY : doxygen
doxygen: docs_directory
	@$(RM_RECURSIVE) $(JNI_DOXYGEN_DOC_PATH)
	@$(DOXYGEN) $(JNI_DOXYGEN_DEF_FILE)
