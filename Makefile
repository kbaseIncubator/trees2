SERVICE = kbasetrees
SERVICE_CAPS = KBaseTrees
SPEC_FILE = KBaseTrees.spec
URL = https://kbase.us/services/kbasetrees
DIR = $(shell pwd)
LIB_DIR = lib
SCRIPTS_DIR = scripts
TEST_DIR = test
LBIN_DIR = bin
TARGET ?= /kb/deployment
JARS_DIR = $(TARGET)/lib/jars
EXECUTABLE_SCRIPT_NAME = run_$(SERVICE_CAPS)_async_job.sh
STARTUP_SCRIPT_NAME = start_server.sh
TEST_SCRIPT_NAME = run_tests.sh
KB_RUNTIME ?= /kb/runtime
ANT = $(KB_RUNTIME)/ant/bin/ant

PERL_SERVICE_NAME = Tree
PERL_SERVICE_PSGI_FILE = Tree.psgi
PERL_EXECUTABLE_SCRIPT_NAME = run_$(PERL_SERVICE_NAME)_async_job.sh

.PHONY: test

default: compile build-startup-script build-executable-script build-test-script

compile:
	kb-sdk compile $(SPEC_FILE) \
		--out $(LIB_DIR) \
		--plclname Bio::KBase::$(SERVICE_CAPS)::$(SERVICE_CAPS)Client \
		--jsclname javascript/$(SERVICE_CAPS)/Client \
		--pyclname biokbase.$(SERVICE_CAPS).$(SERVICE_CAPS)Client \
		--javasrc src \
		--java \
		--javasrv \
		--javapackage us.kbase;
	kb-sdk compile $(PERL_SERVICE_NAME).spec \
		--out $(LIB_DIR) \
		--plsrvname Bio::KBase::$(PERL_SERVICE_NAME)::Service \
		--plimplname Bio::KBase::$(PERL_SERVICE_NAME)::$(PERL_SERVICE_NAME)Impl \
		--plpsginame $(PERL_SERVICE_PSGI_FILE);
	$(ANT) war -Djars.dir=$(JARS_DIR)
	chmod +x $(SCRIPTS_DIR)/entrypoint.sh

build-executable-script:
	# Java
	mkdir -p $(LBIN_DIR)
	$(ANT) build-executable-script -Djars.dir=$(JARS_DIR) -Dexec.cmd.file=$(EXECUTABLE_SCRIPT_NAME)
	chmod +x $(LBIN_DIR)/$(EXECUTABLE_SCRIPT_NAME)
	# Perl
	echo '#!/bin/bash' > $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'script_dir=$$(dirname "$$(readlink -f "$$0")")' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'export PERL5LIB=$$script_dir/../$(LIB_DIR):$$PATH:$$PERL5LIB' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'export KB_SERVICE_NAME=$(SERVICE_CAPS)' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'export TREE_DEPLOYMENT_CONFIG=$$KB_DEPLOYMENT_CONFIG' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'export TREE_DEPLOYMENT_SERVICE_NAME=$(SERVICE_CAPS)' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	echo 'perl $$script_dir/../$(LIB_DIR)/Bio/KBase/$(PERL_SERVICE_NAME)/Service.pm $$1 $$2 $$3' >> $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)
	chmod +x $(LBIN_DIR)/$(PERL_EXECUTABLE_SCRIPT_NAME)

build-startup-script:
	mkdir -p $(LBIN_DIR)
	echo '#!/bin/bash' > $(SCRIPTS_DIR)/$(STARTUP_SCRIPT_NAME)
	echo 'script_dir=$$(dirname "$$(readlink -f "$$0")")' >> $(SCRIPTS_DIR)/$(STARTUP_SCRIPT_NAME)
	echo 'cd $(SCRIPTS_DIR)' >> $(SCRIPTS_DIR)/$(STARTUP_SCRIPT_NAME)
	echo 'java -cp $(JARS_DIR)/jetty/jetty-start-7.0.0.jar:$(JARS_DIR)/jetty/jetty-all-7.0.0.jar:$(JARS_DIR)/servlet/servlet-api-2.5.jar \
		-DKB_DEPLOYMENT_CONFIG=$$script_dir/../deploy.cfg -Djetty.port=5000 org.eclipse.jetty.start.Main jetty.xml' >> $(SCRIPTS_DIR)/$(STARTUP_SCRIPT_NAME)
	chmod +x $(SCRIPTS_DIR)/$(STARTUP_SCRIPT_NAME)

build-test-script:
	echo '#!/bin/bash' > $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	echo 'script_dir=$$(dirname "$$(readlink -f "$$0")")' >> $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	echo 'export KB_DEPLOYMENT_CONFIG=$$script_dir/../deploy.cfg' >> $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	echo 'export KB_AUTH_TOKEN=`cat /kb/module/work/token`' >> $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	echo 'export JAVA_HOME=$(JAVA_HOME)' >> $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	echo '$(ANT) test -Djars.dir=$(JARS_DIR)' >> $(TEST_DIR)/$(TEST_SCRIPT_NAME)
	chmod +x $(TEST_DIR)/$(TEST_SCRIPT_NAME)

test:
	bash $(TEST_DIR)/$(TEST_SCRIPT_NAME)

clean:
	rm -rfv $(LBIN_DIR)
	