TARGET ?= /kb/deployment
KB_RUNTIME ?= /kb/runtime
SERVICE = trees
PORT = 5004
SERVICE_DIR = $(TARGET)/services/$(SERVICE)
PID_FILE = $(SERVICE_DIR)/service.pid

all:

deploy: deploy-services

redeploy: clean deploy-services

deploy-services:
	echo '#!/bin/sh\necho trying to start tree service.' > ./start_service
	echo 'export PERL5LIB=$(SERVICE_DIR)/lib' >> ./start_service
	echo "exec $(KB_RUNTIME)/bin/starman --listen :$(PORT) --pid $(PID_FILE) $(SERVICE_DIR)/lib/Trees.psgi" >> ./start_service
	echo 'echo tree service is listening.\n' >> ./start_service
	echo '#!/bin/sh\necho trying to stop tree services.' > ./stop_service
	echo "pid_file=$(SERVICE_DIR)/service.pid" >> ./stop_service
	echo "if [ ! -f \$$pid_file ] ; then " >> ./stop_service
	echo "\techo \"No pid file: \$$pid_file found for service $(SERVICE).\"\n\texit 1\nfi" >> ./stop_service
	echo "pid='cat \$$pid_file'\nkill \$$pid\n" >> ./stop_service
	chmod +x start_service stop_service
	mkdir -p $(SERVICE_DIR)
	cp -r . $(SERVICE_DIR)/
	echo "OK ... Done Deploying Tree Services."

clean:
	rm -rf $(SERVICE_DIR)
	rm start_service stop_service
	echo "OK ... Removed all deployed files."

