
Tree and Multiple Sequence Alignment (MSA) Services
=============================================

Overview
----------
Services to retrieve trees and MSAs and perform some typical operations / computations
using trees and MSAs.  More to come....



Dependencies
----------
perl5
...


Deploying on KBase infrastructure
----------
*start a Magellan image (currently tested on kbase v10 image)
*as root, cd to /kb/dev_container/modules, a directory setup to checkout code for deployment
*clone the repo to the kbase machine (git clone kbase@git.kbase.us:trees.git trees)
*to deploy these services (with all other checked out modules) type 'make deploy' in the 'dev_container' dir
*alternatively, type 'make deploy' in the 'trees' directory to deploy only the tree services
*deployment means the cpp libs are built, and all other files are copied to /kb/deployment/services/trees
*type 'make clean' from the 'trees' dir to delete all deployed files (and any files you created in the deployed directory!!)
*type 'make redeploy' to perform a fresh deployment (clean followed by deploy)
*to start and stop the service, use the 'start_service' and 'stop_service' scripts in /kb/deployment/services/trees
*after starting the service, the process id of the serivice is stored in the 'service.pid' file in /kb/deployment/services/trees
*log files are currently dumped in the /kb/deployment/services/trees/log directory, but this will change once central logging is adopted


Testing
----------
The backend of the tree services are written in Perl (for compatibility with the type compiler), so
tests are also implemented in Perl and can be found in the 't' directory.  For compliance with KBase,
there is also a 'test' directory which is currently a sym link to the 't' directory.  In the future,
if additional non-perl tests are added, the 'test' directory may be converted to an explicit directory.

To run tests, enter the 't' directory and run any of the available scripts (which should all be executable).
Each test script has documentation indicating 


To Do
----------
*pretty much everything


Authors
---------
Michael Sneddon, LBL (mwsneddon@lbl.gov)
Fangfang Xia, ANL (fangfang.xia@gmail.com)


Log / Major Release Notes
---------
v0.00a - 5/16/2012 - initial repo created
v0.00a - 8/9/2012 - added / tested link to some MO functions and the cpp library