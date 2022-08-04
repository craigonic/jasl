################################################################################
# Makefile - This make parameter file is used to generate documentation for    #
# the files within this directory.                                             #
#                                                                              #
# Written By: Craig R. Campbell  -  March 2022                                 #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Define variables to represent the Python files in/of/for this package.

PYTHON_FILES   := drw.py drw_check.py drw_settings.py
PYTHON_CLASSES := ${PYTHON_FILES:.py=}

# Create HTML versions of the source files. Each file is converted using the
# source-highlight program, which generates an "image" of the file with syntax
# color coding. After the files are generated, the htls program is used to link
# the files together on an HTML page.

PYTHON_DOCS_PATH := $(SRC_DOCS_PATH)/$(PYTHON_DIRECTORY)/DiceRollWizard

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(PYTHON_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(PYTHON_DOCS_PATH)/$(MAKEFILE).html
	@for i in $(PYTHON_CLASSES) ; do \
		$(CAT) $$i.py | $(PYTHON2HTML) --title="$$i.py" | $(SED_CONV_CMD) > $(PYTHON_DOCS_PATH)/$$i.html ;\
	done

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(PYTHON_DOCS_PATH)
	@$(HTLS) $(PYTHON_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	@$(RM) $(PYTHON_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs
