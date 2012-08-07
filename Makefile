# configurable variables
TARGET ?= /kb/deployment
KB_RUNTIME ?= /kb/runtime
SERVICE = trees
PSGI_FILENAME = Trees.psgi
PORT = 7047

#do not make changes below this line

SERVICE_DIR = $(TARGET)/services/$(SERVICE)
PID_FILE = $(SERVICE_DIR)/service.pid
ACCESS_LOG_FILE = $(SERVICE_DIR)/log/access.log
ERR_LOG_FILE = $(SERVICE_DIR)/log/error.log

all:

deploy: deploy-services

redeploy: clean deploy-services

deploy-services:
	echo '#!/bin/sh' > ./start_service
	echo "echo starting $(SERVICE) services." >> ./start_service
	echo 'export PERL5LIB=$$PERL5LIB:$(SERVICE_DIR)/lib:/kb/deployment/lib' >> ./start_service
	echo "$(KB_RUNTIME)/bin/starman --listen :$(PORT) --pid $(PID_FILE) --daemonize \\" >> ./start_service
	echo "  --access-log $(ACCESS_LOG_FILE) \\" >>./start_service
	echo "  --error-log $(ERR_LOG_FILE) \\" >> ./start_service
	echo "  $(SERVICE_DIR)/lib/$(PSGI_FILENAME)" >> ./start_service
	echo "echo $(SERVICE) service is listening on port $(PORT).\n" >> ./start_service
	echo '#!/bin/sh' > ./stop_service
	echo "echo trying to stop $(SERVICE) services." >> ./stop_service
	echo "pid_file=$(PID_FILE)" >> ./stop_service
	echo "if [ ! -f \$$pid_file ] ; then " >> ./stop_service
	echo "\techo \"No pid file: \$$pid_file found for service $(SERVICE).\"\n\texit 1\nfi" >> ./stop_service
	echo "pid=\$$(cat \$$pid_file)\nkill \$$pid\n" >> ./stop_service
	chmod +x start_service stop_service
	mkdir -p $(SERVICE_DIR)
	mkdir -p $(SERVICE_DIR)/log
	cp -rv . $(SERVICE_DIR)/
	echo "OK ... Done deploying $(SERVICE) services."

clean:
	rm -rfv $(SERVICE_DIR)
	rm start_service stop_service
	echo "OK ... Removed all deployed files."