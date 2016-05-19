################################################################################
# Makefile - This make parameter file is used to copy the JSON files in this   #
#            directory to the share hierarchy associated with the jASL         #
#            program, as well as to build HTML documentation for them.         #
#                                                                              #
# Written By : Craig R. Campbell  -  May 2016                                  #
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

# Copy the JSON files in this directory to the one in the share output hierarchy
# with the same name.

.PHONY : all
all:
	@$(INSTALL_DIR) $(SCENARIOS_DATA_PATH)
	@$(INSTALL_STD_FILE) $(SCENARIO_SRC_FILES) $(SCENARIOS_DATA_PATH)

# Remove all of the .class and/or object files from the binary output directory.

.PHONY : clean
clean:
	@$(RM) $(SCENARIO_DATA_FILES)

# Create an HTML version of this file and the JSON files. Each one is converted
# using the source-highlight program, which generates an "image" of the file
# with syntax color coding. After the files are generated, the htls program is
# used to link the files together on an HTML page.

SCENARIOS_DOCS_PATH := $(SRC_DOCS_PATH)/$(SCENARIOS_DIRECTORY)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(SCENARIOS_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(SCENARIOS_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(basename $(SCENARIO_FILES)) ; do \
		if [ "$$i" = $(SCENARIO_FILES) ]; then \
			title=`basename -s .json $$i` ;\
			$(JSON2HTML) --title="$$title" --input="$$i" --output="$(SCENARIOS_DOCS_PATH)/$$i.html" ;\
		fi \
	done
	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(SCENARIOS_DOCS_PATH)
	@$(HTLS) $(SCENARIOS_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(SCENARIOS_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
