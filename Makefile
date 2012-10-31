# configurable variables 
SERVICE = trees
SERVICE_PSGI_FILE = Tree.psgi
SERVICE_PORT = 7047
CLIENT_TESTS = $(wildcard t/client-tests/*.t)

#standalone variables which are replaced when run via /kb/dev_container/Makefile
TOP_DIR = ../..
DEPLOY_RUNTIME ?= /kb/runtime
TARGET ?= /kb/deployment

#for the reboot_service script, we need to get a path to dev_container/modules/trees.  We can do this  simply
#by getting the absolute path to this makefile.  Note that old versions of make might not support this line.
ROOT_DEV_MODULE_DIR := $(abspath $(dir $(lastword $(MAKEFILE_LIST))))

# including the common makefile gives us a handle to the service directory.  This is
# where we will (for now) dump the service log files
include $(TOP_DIR)/tools/Makefile.common
PID_FILE = $(SERVICE_DIR)/service.pid
ACCESS_LOG_FILE = $(SERVICE_DIR)/log/access.log
ERR_LOG_FILE = $(SERVICE_DIR)/log/error.log


.PHONY : test


# default target is all, which for now simply builds the CPP libs only
default: all

all: cpp-libs

# building the CPP libs amounts to calling another makefile
cpp-libs:
	cd "$(TOP_DIR)/modules/$(SERVICE)/lib/KBTree_cpp_lib"; make all

# here are the standard KBase test targets (test, test-all, deploy-client, deploy-scripts, & deploy-server)
test: test-client test-scripts
	echo "running client and script tests"

test-all: test-server test-client test-scripts

# probably this should be updated to search the proper directory and run all tests...
test-client:
	$(DEPLOY_RUNTIME)/bin/perl $(TOP_DIR)/modules/$(SERVICE)/t/client-tests/testBasicResponses.t
	$(DEPLOY_RUNTIME)/bin/perl $(TOP_DIR)/modules/$(SERVICE)/t/client-tests/testIntrospectionMethods.t
	$(DEPLOY_RUNTIME)/bin/perl $(TOP_DIR)/modules/$(SERVICE)/t/client-tests/testQueryMethods.t

test-scripts:
	echo "no scripts to test yet.  Run test-client instead."

test-server:
	$(DEPLOY_RUNTIME)/bin/perl $(TOP_DIR)/modules/$(SERVICE)/t/server-tests/testServerUp.t


# here are the standard KBase deployment targets (deploy, deploy-all, deploy-client, deploy-scripts, & deploy-server)
deploy: deploy-client deploy-scripts
	echo "OK... Done deploying clients and scripts of $(SERVICE)."

deploy-all: deploy-client deploy-scripts deploy-server
	echo "OK... Done deploying ALL artifacts (includes clients, scripts and server) of $(SERVICE)."

deploy-client:
	mkdir -p $(TARGET)/lib/Bio/KBase/Tree
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Bio/KBase/Tree/Client.pm $(TARGET)/lib/Bio/KBase/Tree/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Tree.js $(TARGET)/lib/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Tree.py $(TARGET)/lib/.
	echo "deployed clients of $(SERVICE)."

# this will eventually copy over any command line 
deploy-scripts:
	#export KB_TOP=$(TARGET); \
	#export KB_RUNTIME=$(DEPLOY_RUNTIME); \
	#export KB_PERL_PATH=$(TARGET)/lib bash ; \
	#for src in $(SRC_PERL) ; do \
	#	basefile=`basename $$src`; \
	#	base=`basename $$src .pl`; \
	#	echo install $$src $$base ; \
	#	cp $$src $(TARGET)/plbin ; \
	#	$(WRAP_PERL_SCRIPT) "$(TARGET)/plbin/$$basefile" $(TARGET)/bin/$$base ; \
	#done
	echo "No scripts yet to deploy."

# deploys all libraries and scripts needed to start the server
deploy-server: cpp-libs deploy-server-libs deploy-server-start_scripts

deploy-server-libs:
	mkdir -p $(TARGET)/lib/Bio/KBase/Tree
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Bio/KBase/Tree/Service.pm $(TARGET)/lib/Bio/KBase/Tree/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Bio/KBase/Tree/TreeImpl.pm $(TARGET)/lib/Bio/KBase/Tree/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Bio/KBase/Tree/ForesterParserWrapper.pm $(TARGET)/lib/Bio/KBase/Tree/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/Tree.psgi $(TARGET)/lib/.
	cp -vr $(TOP_DIR)/modules/$(SERVICE)/lib/KBTree_cpp_lib/lib/perl_interface/Bio/KBase/Tree/TreeCppUtil.pm $(TARGET)/lib/Bio/KBase/Tree/.
	cp -vr $(TOP_DIR)/modules/$(SERVICE)/lib/KBTree_cpp_lib/lib/perl_interface/KBTreeUtil.bundle $(TARGET)/lib/.
	cp $(TOP_DIR)/modules/$(SERVICE)/lib/forester_1005.jar $(TARGET)/lib/.
	echo "deployed server for $(SERVICE)."

# creates start/stop/reboot scripts and copies them to the deployment target
deploy-server-start_scripts:
	# First create the start script (should be a better way to do this...)
	echo '#!/bin/sh' > ./start_service
	echo "echo starting $(SERVICE) server." >> ./start_service
	echo 'export PERL5LIB=$$PERL5LIB:$(TARGET)/lib' >> ./start_service
	echo '#uncomment to debug: export STARMAN_DEBUG=1' >> ./start_service
	echo 'export PERL_INLINE_JAVA_DIRECTORY=/kb/deployment/services/trees/' >> ./start_service
	echo "$(DEPLOY_RUNTIME)/bin/starman --listen :$(SERVICE_PORT) --pid $(PID_FILE) --daemonize \\" >> ./start_service
	echo "  --access-log $(ACCESS_LOG_FILE) \\" >>./start_service
	echo "  --error-log $(ERR_LOG_FILE) \\" >> ./start_service
	echo "  $(TARGET)/lib/$(SERVICE_PSGI_FILE)" >> ./start_service
	echo "echo $(SERVICE) server is listening on port $(SERVICE_PORT).\n" >> ./start_service
	# Second create the stop script (should be a better way to do this...)
	echo '#!/bin/sh' > ./stop_service
	echo "echo trying to stop $(SERVICE) server." >> ./stop_service
	echo "pid_file=$(PID_FILE)" >> ./stop_service
	echo "if [ ! -f \$$pid_file ] ; then " >> ./stop_service
	echo "\techo \"No pid file: \$$pid_file found for server $(SERVICE).\"\n\texit 1\nfi" >> ./stop_service
	echo "pid=\$$(cat \$$pid_file)\nkill \$$pid\n" >> ./stop_service
	# Finally create a script to reboot the service by stopping, redeploying the service, and starting again
	echo '#!/bin/sh' > ./reboot_service
	echo '# auto-generated script to stop the service, redeploy server implementation, and start the servce' >> ./reboot_service
	echo "./stop_service\ncd $(ROOT_DEV_MODULE_DIR)\nmake deploy-server-libs\ncd -\n./start_service" >> ./reboot_service
	# Actually run the deployment of these scripts
	chmod +x start_service stop_service reboot_service
	mkdir -p $(SERVICE_DIR)
	mkdir -p $(SERVICE_DIR)/log
	cp start_service $(SERVICE_DIR)/
	cp stop_service $(SERVICE_DIR)/
	cp reboot_service $(SERVICE_DIR)/

# this undeploy target is a custom hack for Trees.  we might want to create a general version of this functionality
undeploy-all: clean
	rm -rfv $(SERVICE_DIR)
	rm -rfv $(TARGET)/lib/Bio/KBase/Tree
	rm -rfv $(TARGET)/lib/KBTree_cpp_lib
	rm -rfv $(TARGET)/lib/Tree.*
	rm -fv  $(TARGET)/lib/forester_1005.jar
	rm -rfv $(TARGET)/lib/_Inline 
	echo "OK ... Removed all deployed files."

# remove files generated by building the server
clean:
	rm -f start_service stop_service reboot_service

