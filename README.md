
Phylogenetic Tree and Multiple Sequence Alignment (MSA) Services
=============================================

Overview
----------
This service provides a set of methods for querying, manipulating, and analyzing multiple
sequence alignments and phylogenetic trees.  It also has functions to build alignments and
trees.

Authors
---------
Michael Sneddon, LBL (mwsneddon@lbl.gov)
Fangfang Xia, ANL (fangfang.xia@gmail.com)
Matt Henderson, LBL (mhenderson@lbl.gov)
Roman Sutormin, LBL (rsutormin@lbl.gov)

Special deployment instructions
----------
* The Tree service is currently only supported for server deployment in a linux environment.  Only clients and
scripts should be deployed on a Mac.  (to deploy only clients/scripts, use make deploy-client; make deploy-scripts)
* follow the standard KBase deployment and testing procedures


Starting/Stopping the service, and other notes
---------------------------
* to start and stop the service, use the 'start_service' and 'stop_service'
  scripts in (the default location) /kb/deployment/services/trees



