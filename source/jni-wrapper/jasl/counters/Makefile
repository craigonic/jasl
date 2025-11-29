################################################################################
# Makefile - This make parameter file is used to install the JNI wrapper       #
#            headers for the counters package of the jASL program. It also     #
#            generates the documentation.                                      #
#                                                                              #
# Written By: Craig R. Campbell  -  August 2018                                #
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

# "Pass through" targets that will be executed in the Makefile in the private
# sub-directory. These may be called directly or from the Makefile in the parent
# directory.

.PHONY : obj
.PHONY : libs
.PHONY : clean_libs
.PHONY : dynamic_lib
.PHONY : static_lib
.PHONY : clean_dynamic_lib
.PHONY : clean_static_lib
obj libs clean clean_libs dynamic_lib static_lib clean_dynamic_lib clean_static_lib:
	$(MAKE) -C private $@

.PHONY : clean
clean:
	$(MAKE) -C private $@
	$(MAKE) -C test $@

# Define variables to represent the public header files in/of/for this package.

ENUMS      := Descriptions.h Nationalities.h
HDR_FILES  := Unit.h $(ENUMS) 

# Install the header (.h) files in the appropriate include directory.

.PHONY : hdr
hdr: $(HDR_FILES)
	$(INSTALL_DIR) $(JNI_COUNTERS_HDR_PATH)
	$(INSTALL_STD_FILE) $(HDR_FILES) $(JNI_COUNTERS_HDR_PATH)

# Remove the include output directory for the header files and its contents.

.PHONY : clean_hdr
clean_hdr:
	$(RM_RECURSIVE) $(JNI_COUNTERS_HDR_PATH)

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

JNI_COUNTERS_DOCS_PATH := $(SRC_DOCS_PATH)/$(JNI_DIRECTORY)/$(COUNTERS_PKG_PATH)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(JNI_COUNTERS_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(JNI_COUNTERS_DOCS_PATH)/$(MAKEFILE).html

	$(MAKE) -C private $@
	$(MAKE) -C test $@

	@for i in $(HDR_FILES) ; do \
		$(CAT) $$i | $(CPP2HTML) --title="$$i" | $(SED_CONV_CMD) > $(JNI_COUNTERS_DOCS_PATH)/$$i.html ;\
		if [ -d $(JNI_DOXYGEN_DOC_PATH)/html ]; then \
			(cd $(JNI_DOXYGEN_DOC_PATH)/html;$(LN) ../../source/$(JNI_DIRECTORY)/$(COUNTERS_PKG_PATH)/$$i.html) ;\
		fi \
	done

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(JNI_COUNTERS_DOCS_PATH)
	@$(HTLS) $(JNI_COUNTERS_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C private $@
	@$(RM) $(JNI_COUNTERS_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
