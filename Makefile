TARGET ?= /kb/deployment
SERVICE = trees
SERVICE_DIR = $(TARGET)/services/$(SERVICE)

all:

deploy: deploy-services

deploy-services:
	echo '#!/bin/sh\necho nothing to start yet.\n' > ./start_service
	echo '#!/bin/sh\nstopping nothing' > ./stop_service	
	chmod +x start_service stop_service
	mkdir -p $(SERVICE_DIR)
	cp -r . $(SERVICE_DIR)/
	echo "OK ... Done Deploying Tree Services."

