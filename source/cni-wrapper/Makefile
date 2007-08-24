################################################################################
# Makefile - This make parameter file is used to build the Compiled Native     #
#            Interface (CNI) wrapper library, which is used to create a Java   #
#            Virtual Machine (JVM) to enable the execution of Java code        #
#            compiled with GCJ.                                                #
#                                                                              #
# Written By : Craig R. Campbell  -  May 2007                                  #
#                                                                              #
# $Id: Makefile,v 1.4 2007/08/24 04:02:02 craig Exp $
################################################################################

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the source and object files in/of/for this
# library.

CNI_WRAPPER_SRC   = cniWrapper.cpp
CNI_WRAPPER_EXEC  = ${CNI_WRAPPER_SRC:.cpp=}
CNI_WRAPPER_HDR   = ${CNI_WRAPPER_SRC:.cpp=.h}
CNI_WRAPPER_OBJ   = ${CNI_WRAPPER_SRC:.cpp=.o}

# This header file is not part of the library. It provides typedef declarations
# to simplify access to classes in the jasl libraries through programs that use
# it.

JASL_WRAPPER_HDR  = jaslWrapper.h

# Build the object (.o) file.

obj: $(CNI_WRAPPER_SRC)
	$(GCC_COMPILE_CMD) $(CNI_WRAPPER_SRC)

# Install the header (.h) files in the include directory.

hdr: $(CNI_WRAPPER_HDR) $(JASL_WRAPPER_HDR)
	$(CP) -f $(CNI_WRAPPER_HDR)  $(INCLUDE_PATH)
	$(CP) -f $(JASL_WRAPPER_HDR) $(INCLUDE_PATH)

# Define variables used to represent the dynamic and static libraries.

DYNAMIC_LIB_NO_VERSION_LINKNAME    = $(LIB_PREFIX)$(CNI_WRAPPER_BASE_LIB_NAME).so
DYNAMIC_LIB_MAJOR_VERSION_LINKNAME = $(DYNAMIC_LIB_NO_VERSION_LINKNAME).0
DYNAMIC_LIB_MINOR_VERSION_LINKNAME = $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME).0
DYNAMIC_LIB_FILENAME               = $(DYNAMIC_LIB_MINOR_VERSION_LINKNAME).1

# Using the .o files, build either a static or dynamic library file, or both,
# along with the necessary links, and install it/them in the library output
# directory.

libs: obj dynamic_lib static_lib clean

dynamic_lib:
	$(GCC_LIB_BUILD_CMD) $(LIB_PATH)/$(DYNAMIC_LIB_FILENAME) \
	                     $(CNI_WRAPPER_OBJ)
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_MAJOR_VERSION_LINKNAME))
	@(cd $(LIB_PATH); $(LN) $(DYNAMIC_LIB_FILENAME) $(DYNAMIC_LIB_NO_VERSION_LINKNAME))

static_lib:
	$(AR) $(CNI_WRAPPER_STATIC_LIB_PATH) $(CNI_WRAPPER_OBJ)

# Remove the object file from this directory.

clean:
	$(RM) $(CNI_WRAPPER_OBJ)

# Remove the header (.h) files from the include output directory.

clean_hdr:
	$(RM) $(INCLUDE_PATH)/$(CNI_WRAPPER_HDR)
	$(RM) $(INCLUDE_PATH)/$(JASL_WRAPPER_HDR)

# Remove all of the dynamic and/or static library files and links from the
# library output directory.

clean_libs: clean_dynamic_lib clean_static_lib

clean_dynamic_lib:
	$(RM) $(LIB_PATH)/$(DYNAMIC_LIB_NO_VERSION_LINKNAME)*

clean_static_lib:
	$(RM) $(CNI_WRAPPER_STATIC_LIB_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

docs:
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SRC_DOCS_PATH)/$(WRAPPERS_DIRECTORY)/$(MAKEFILE).html
	@for i in $(CNI_WRAPPER_HDR) $(CNI_WRAPPER_SRC) $(JASL_WRAPPER_HDR) ; do \
 		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(SRC_DOCS_PATH)/$(WRAPPERS_DIRECTORY)/$$i.html ;\
	done
	@$(HTLS) $(SRC_DOCS_PATH)/$(WRAPPERS_DIRECTORY)
