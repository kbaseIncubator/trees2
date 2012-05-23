
Tree and Multiple Sequence Alignment Services
=============================================

Overview
----------
Repo for services to retrieve trees and MSAs and perform some typical operations / computations
using trees and MSAs.

More to come....






Deploying on KBase infrastructure
----------
*as root, cd to /kb/dev_container/modules, a directory setup to checkout code for deployment
*clone the repo to the kbase machine (git clone kbase@git.kbase.us:trees.git trees)
*cd to the 'trees' directory
*type 'make deploy', which will build and copy necessary files to the /deployment/services/trees directory
*type 'make clean' to delete all deployed files (and any files you may have created in the deployed directory)
*type 'make redeploy' to perform a fresh deployment


Testing
----------
The backend of the tree services are written in Perl (for compatibility with the type compiler), so
tests are also implemented in Perl and can be found in the 't' directory.  For compliance with KBase,
there is also a 'test' directory which is currently a sym link to the 't' directory.  In the future,
if additional non-perl tests are added, the 'test' directory may be converted to an explicit directory.

No tests currently exist.  Again, more to come...

To Do
----------
pretty much everything


Authors
---------
Michael Sneddon, LBL (mwsneddon@lbl.gov)
Fangfang Xia, ANL (fangfang.xia@gmail.com)


Log / Major Release Notes
---------
v0.00a - 5/16/2012 - repo created