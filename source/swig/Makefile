################################################################################
# Makefile - This make parameter file is used to build wrapper libraries using #
#            SWIG. These libraries interact with the jASL libraries, allowing  #
#            the public classes to be used in scripting languages.             #
#                                                                              #
# Written By : Craig R. Campbell  -  July 2008                                 #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# Default target, executed when one is not specified on the command line.

.PHONY : default
default : libs

## System-wide build variables.

include $(JASL_BASE)/util/jasl.make

## General/Combination targets ################################################

# Build all of the wrapper libraries and install them, along with their
# respective module files, in the appropriate output directories.

.PHONY : libs
libs:
	$(MAKE) -C CniWrapper $@
	$(MAKE) -C Counters   $@
	$(MAKE) -C UiData     $@
	$(MAKE) -C Utilities  $@

# Remove all of the library and module files from their respective output
# directories.

.PHONY : clean_all
clean_all:
	$(MAKE) -C CniWrapper $@
	$(MAKE) -C Counters   $@
	$(MAKE) -C UiData     $@
	$(MAKE) -C Utilities  $@

# Create an HTML version of this file and the files in this directory. They are
# converted using the source-highlight program, which generates an "image" of
# the files with syntax color coding. After the files are generated, the htls
# program is used to link them to the files in the sub-directories on an HTML
# page.

SWIG_DOCS_PATH := $(SRC_DOCS_PATH)/$(SWIG_DIRECTORY)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(SWIG_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SWIG_DOCS_PATH)/$(MAKEFILE).html
	@for i in `ls *.swig` ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(SWIG_DOCS_PATH)/$$i.html ;\
	done

	$(MAKE) -C CniWrapper $@
	$(MAKE) -C Counters   $@
	$(MAKE) -C UiData     $@
	$(MAKE) -C Utilities  $@

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(SWIG_DOCS_PATH)
	@$(HTLS) $(SWIG_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C CniWrapper $@
	$(MAKE) -C Counters   $@
	$(MAKE) -C UiData     $@
	$(MAKE) -C Utilities  $@
	@$(RM) $(SWIG_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
