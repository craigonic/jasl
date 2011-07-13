################################################################################
# Makefile - This make parameter file is used to build the Compiled Native     #
#            Interface (CNI) wrapper library, which is used to create a Java   #
#            Virtual Machine (JVM) to enable the execution of Java code        #
#            compiled with GCJ.                                                #
#                                                                              #
# Written By: Craig R. Campbell  -  May 2007                                   #
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

CNI_WRAPPER_SRC        := CniWrapper.cpp
CNI_WRAPPER_HDR        := ${CNI_WRAPPER_SRC:.cpp=.h}
CNI_WRAPPER_OBJ        := ${CNI_WRAPPER_SRC:.cpp=.o}

JASL_ERROR_MESSAGE_SRC := JaslErrorMessage.cpp
JASL_ERROR_MESSAGE_HDR := ${JASL_ERROR_MESSAGE_SRC:.cpp=.h}
JASL_ERROR_MESSAGE_OBJ := ${JASL_ERROR_MESSAGE_SRC:.cpp=.o}

# This header file is not part of the library. It provides typedef declarations
# to simplify access to classes in the jasl libraries through programs that use
# it.

JASL_WRAPPER_HDR := jaslWrapper.h

# Collections of the source files contained in this directory.

CPP_FILES := $(CNI_WRAPPER_SRC) $(JASL_ERROR_MESSAGE_SRC)
HDR_FILES := ${CPP_FILES:.cpp=.h} $(JASL_WRAPPER_HDR)
OBJ_FILES := ${CPP_FILES:.cpp=.o}
SRC_FILES := $(CPP_FILES) $(HDR_FILES)

# Build the object (.o) files.

.PHONY : obj
obj: $(SRC_FILES)
	$(GCC_COMPILE_CMD) $(CNI_WRAPPER_SRC)
	$(GCC_COMPILE_CMD) $(JASL_ERROR_MESSAGE_SRC)

# Install the header (.h) files in the include directory.

.PHONY : hdr
hdr: $(HDR_FILES)
	$(INSTALL_DIR) $(CNI_HDR_PATH)
	$(INSTALL_STD_FILE) $(HDR_FILES) $(CNI_HDR_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    := $(LIB_PREFIX)$(CNI_WRAPPER_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME := $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME := $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).1
DYNAMIC_LIB_FILENAME               := $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).0

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

.PHONY : libs
libs: dynamic_lib static_lib clean

.PHONY : dynamic_lib
dynamic_lib: obj lib_directory
	$(GCC_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) $(OBJ_FILES)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

.PHONY : static_lib
static_lib: obj lib_directory
	$(AR) $(CNI_WRAPPER_STATIC_LIB_PATH) $(OBJ_FILES)

# Remove the object files from this directory.

.PHONY : clean
clean:
	$(RM) $(OBJ_FILES)

# Remove the header (.h) files from the include output directory.

.PHONY : clean_hdr
clean_hdr:
	$(RM) $(INCLUDE_PATH)/$(CNI_WRAPPER_HDR)
	$(RM) $(INCLUDE_PATH)/$(JASL_ERROR_MESSAGE_HDR)
	$(RM) $(INCLUDE_PATH)/$(JASL_WRAPPER_HDR)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

.PHONY : clean_libs
clean_libs: clean_dynamic_lib clean_static_lib

.PHONY : clean_dynamic_lib
clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

.PHONY : clean_static_lib
clean_static_lib:
	$(RM) $(CNI_WRAPPER_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

CNI_DOCS_PATH := $(SRC_DOCS_PATH)/$(CNI_DIRECTORY)
HTLS_CFG_FILE := $(CNI_DIRECTORY).cfg

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(CNI_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(CNI_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(SRC_FILES) ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(CNI_DOCS_PATH)/$$i.html ;\
	done

	$(MAKE) -C $(PROGRAM_NAME) $@

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(HTLS_CFG_FILE) $(CNI_DOCS_PATH)
	@$(HTLS) $(CNI_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C $(PROGRAM_NAME) $@
	@$(RM) $(CNI_DOCS_PATH)/$(HTLS_HEADER_FILE) \
	       $(CNI_DOCS_PATH)/$(HTLS_CFG_FILE)

.PHONY : docs
docs: docs_all clean_docs
