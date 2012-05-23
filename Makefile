TARGET ?= /kb/deployment
SERVICE = trees
SERVICE_DIR = $(TARGET)/services/$(SERVICE)

all:

deploy: deploy-services

redeploy: clean deploy-services

deploy-services:
	echo '#!/bin/sh\necho nothing to start yet.\n' > ./start_service
	echo '#!/bin/sh\necho stopping nothing.\n' > ./stop_service	
	chmod +x start_service stop_service
	mkdir -p $(SERVICE_DIR)
	cp -r . $(SERVICE_DIR)/
	echo "OK ... Done Deploying Tree Services."

clean:
	rm -rf $(SERVICE_DIR)
        rm start_service stop_service
	echo "OK ... Removed all deployed files."