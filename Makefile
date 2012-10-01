# configurable variables
SERVICE = trees
SERVICE_PSGI_FILE = Tree.psgi
SERVICE_PORT = 7047

#standalone variables, replaced when run via /kb/dev_container/Makefile
TARGET ?= /kb/deployment
DEPLOY_RUNTIME ?= /kb/runtime

#you should not have to make changes below this line
TOP_DIR = ../..
include $(TOP_DIR)/tools/Makefile.common
PID_FILE = $(SERVICE_DIR)/service.pid
ACCESS_LOG_FILE = $(SERVICE_DIR)/log/access.log
ERR_LOG_FILE = $(SERVICE_DIR)/log/error.log

all: build-cpp-libs

redeploy: clean deploy

deploy: build-cpp-libs deploy-libs deploy-services
	echo "OK... Done deploying $(SERVICE)."

build-cpp-libs:
	cd "$(TOP_DIR)/modules/$(SERVICE)/lib/KBTree_cpp_lib"; make all

# this target copies files from the dev_container directory to the deployment directory
# deploy libs has been modified for Trees, we should fix this for general services...
deploy-libs:
	mkdir -p $(TARGET)/lib/Bio/KBase
	cp -vr $(TOP_DIR)/modules/$(SERVICE)/lib/Bio/KBase/Tree $(TARGET)/lib/Bio/KBase/.
	cp -v  $(TOP_DIR)/modules/$(SERVICE)/lib/Tree.* $(TARGET)/lib/.
	cp -vr $(TOP_DIR)/modules/$(SERVICE)/lib/KBTree_cpp_lib $(TARGET)/lib/.
	cp -v  $(TOP_DIR)/modules/$(SERVICE)/lib/forester_1005.jar $(TARGET)/lib/.

deploy-services:
	echo '#!/bin/sh' > ./start_service
	echo "echo starting $(SERVICE) services." >> ./start_service
	echo 'export PERL5LIB=$$PERL5LIB:$(TARGET)/lib' >> ./start_service
	echo '#uncomment to debug: export STARMAN_DEBUG=1' >> ./start_service
	echo "$(DEPLOY_RUNTIME)/bin/starman --listen :$(SERVICE_PORT) --pid $(PID_FILE) --daemonize \\" >> ./start_service
	echo "  --access-log $(ACCESS_LOG_FILE) \\" >>./start_service
	echo "  --error-log $(ERR_LOG_FILE) \\" >> ./start_service
	echo "  $(TARGET)/lib/$(SERVICE_PSGI_FILE)" >> ./start_service
	echo "echo $(SERVICE) service is listening on port $(SERVICE_PORT).\n" >> ./start_service
	echo '#!/bin/sh' > ./stop_service
	echo "echo trying to stop $(SERVICE) services." >> ./stop_service
	echo "pid_file=$(PID_FILE)" >> ./stop_service
	echo "if [ ! -f \$$pid_file ] ; then " >> ./stop_service
	echo "\techo \"No pid file: \$$pid_file found for service $(SERVICE).\"\n\texit 1\nfi" >> ./stop_service
	echo "pid=\$$(cat \$$pid_file)\nkill \$$pid\n" >> ./stop_service
	chmod +x start_service stop_service
	mkdir -p $(SERVICE_DIR)
	mkdir -p $(SERVICE_DIR)/log
	cp -v start_service $(SERVICE_DIR)/
	cp -v stop_service $(SERVICE_DIR)/

# this clean target is a custom hack for Trees.  we should create a general version of this functionality
clean:
	rm -rfv $(SERVICE_DIR)
	rm -rfv $(TARGET)/lib/Bio/KBase/Tree
	rm -rfv $(TARGET)/lib/KBTree_cpp_lib
	rm -rfv $(TARGET)/lib/Tree.*
	rm -fv  $(TARGET)/lib/forester_1005.jar
	rm -f start_service stop_service
	echo "OK ... Removed all deployed files."
