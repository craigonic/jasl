################################################################################
# Makefile - This make parameter file is used to manage the files within this  #
#            directory. Tasks include removing __pycache__ directories and     #
#            generating documentation.                                         #
#                                                                              #
# Written By: Craig R. Campbell  -  February 2022                              #
#                                                                              #
################################################################################

# These entries are included to eliminate pointless searching for the files,
# with many possible extensions, trying to determine if they are actual targets.

.PHONY : Makefile
.PHONY : $(JASL_BASE)/util/jasl.make

# System-wide build variables.

include $(JASL_BASE)/util/jasl.make

# Remove the __pycache__ sub-directories within the jasl hierarchy.

.PHONY : clean
clean:
	$(MAKE) -C $(PROGRAM_NAME) $@

# Create an HTML version of this file using the source-highlight program, which
# generates an "image" of the file with syntax color coding. After the file is
# generated, the htls program is used to link the files together on an HTML
# page.

PYTHON_DOCS_PATH := $(SRC_DOCS_PATH)/$(PYTHON_DIRECTORY)

.PHONY : docs_all
docs_all:
	@$(INSTALL_DIR) $(PYTHON_DOCS_PATH)
	@$(MAKE2HTML) --title="$(MAKEFILE)" --input=$(MAKEFILE) > $(PYTHON_DOCS_PATH)/$(MAKEFILE).html

	$(MAKE) -C DiceRollWizard  $@
	$(MAKE) -C $(PROGRAM_NAME) $@

	@$(INSTALL_STD_FILE) $(HTLS_HEADER_FILE) $(PYTHON_DOCS_PATH)
	@$(HTLS) $(PYTHON_DOCS_PATH)

.PHONY : clean_docs
clean_docs:
	$(MAKE) -C DiceRollWizard  $@
	$(MAKE) -C $(PROGRAM_NAME) $@
	@$(RM) $(PYTHON_DOCS_PATH)/$(HTLS_HEADER_FILE)

.PHONY : docs
docs: docs_all clean_docs

# Create HTML documentation using doxygen.

.PHONY : doxygen
doxygen: docs_directory
	@$(RM_RECURSIVE) $(PY_DOXYGEN_DOC_PATH)
	@$(DOXYGEN) $(PY_DOXYGEN_DEF_FILE)
