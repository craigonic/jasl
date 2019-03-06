################################################################################
# Makefile - This make parameter file is used primarily to build the libraries #
#            associated with each sub-directory. In most cases, the targets    #
#            are simply "passed through" to the Makefile in each sub-directory.#
#                                                                              #
# Written By: Craig R. Campbell  -  April 2011                                 #
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

# "Pass through" targets that will be executed in the Makefile in each
# sub-directory. These may be called directly or from the Makefile in the parent
# directory.

.PHONY : all
.PHONY : obj
.PHONY : hdr
.PHONY : libs
.PHONY : clean
.PHONY : clean_java
.PHONY : clean_obj
.PHONY : clean_hdr
.PHONY : clean_libs
all obj hdr libs clean clean_java clean_obj clean_hdr clean_libs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) $@
	$(MAKE) -C $(COUNTERS_PKG_NAME)  $@
	$(MAKE) -C $(UI_PKG_NAME)        $@

# Build either the shared or static libraries associated with each of the
# sub-directories and install them, along with the necessary links (if
# applicable), in the library output directory.

.PHONY : dynamic_libs
dynamic_libs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) dynamic_lib
	$(MAKE) -C $(COUNTERS_PKG_NAME)  dynamic_lib
	$(MAKE) -C $(UI_PKG_NAME)        dynamic_lib

.PHONY : static_libs
static_libs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) static_lib
	$(MAKE) -C $(COUNTERS_PKG_NAME)  static_lib
	$(MAKE) -C $(UI_PKG_NAME)        static_lib

# Remove either the shared or static library files and links, if applicable,
# from the library output directory.

.PHONY : clean_dynamic_libs
clean_dynamic_libs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) clean_dynamic_lib
	$(MAKE) -C $(COUNTERS_PKG_NAME)  clean_dynamic_lib
	$(MAKE) -C $(UI_PKG_NAME)        clean_dynamic_lib

.PHONY : clean_static_libs
clean_static_libs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) clean_static_lib
	$(MAKE) -C $(COUNTERS_PKG_NAME)  clean_static_lib
	$(MAKE) -C $(UI_PKG_NAME)        clean_static_lib

# Create an HTML version of this file. It is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the file is generated, the htls program is used to link it
# to the files in the sub-directories on an HTML page.

JASL_DOCS_PATH := $(SRC_DOCS_PATH)/$(PROGRAM_NAME)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JASL_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JASL_DOCS_PATH)/$(MAKEFILE).html

	$(MAKE) -C $(UTILITIES_PKG_NAME) $@
	$(MAKE) -C $(COUNTERS_PKG_NAME)  $@
	$(MAKE) -C $(UI_PKG_NAME)        $@

	@# This link helps some of the relative links from other packages to work
	@# when accessed from Doxygen output.
	@(cd $(DOCS_PATH);$(LN) source/$(PROGRAM_NAME))

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JASL_DOCS_PATH)
	@$(HTLS) $(JASL_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C $(UTILITIES_PKG_NAME) $@
	$(MAKE) -C $(COUNTERS_PKG_NAME)  $@
	$(MAKE) -C $(UI_PKG_NAME)        $@
	@$(RM) $(JASL_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
