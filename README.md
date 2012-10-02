
Phylogenetic Tree and Multiple Sequence Alignment (MSA) Services
=============================================

Overview
----------
This KBase service provides helper methods for performing data access and manipulation 
of multiple sequence alignments (MSAs) and phylogenetic trees.  It also includes support
to build alignments and trees from sequence data using a variety of popular methods.
This KBase module includes a c++ tree library wrapped in a SWIG interface which can
be downloaded and used locally.  Because it is wrapped in SWIG, the functions can be
called currently from Java and Perl, but potentially from a host of other languages
as well (see http://www.swig.org/).


Dependencies
----------
-kbase deployment image (kbase-image-v14)
-KBase typespec module deployed (git repo: typecomp)
-KBase CDM module deployed (git repo: kb_seed)
-installation of perl module Inline::Java (which is not yet added to the bootstrap
 scripts).  To install on kbase-image-v14, source the user-env.sh file (see below)
 so that your environment will point to the kbase runtime.  Then to enter:
    sudo JAVA_HOME=/kb/runtime/jdk1.6.0_30 cpan install Inline::Java
 Running cpan for the first time will require setting up some initial config settings.
 All of the default options (answering yes) worked for us.  Inline::Java also asks to
 install/compile a JNI option (so that a separate JVM process is not required at
 runtime), but this is not necessary and has not been tested.

Deploying on KBase infrastructure
----------
* boot a fresh KBase image (last tested on v14)
* login in as ubuntu and get root access with a sudo su
* enter the following commands:

cd /kb
git clone ssh://kbase@git.kbase.us/dev_container
cd /kb/dev_container/modules
git clone ssh://kbase@git.kbase.us/trees
git clone ssh://kbase@git.kbase.us/typecomp
git clone ssh://kbase@git.kbase.us/kb_seed
cd /kb/dev_container
./bootstrap /kb/runtime
source user-env.sh
# NOTE: see above for more details about the Inline::Java dependency
JAVA_HOME=/kb/runtime/jdk1.6.0_30 cpan install Inline::Java
make deploy


Starting/Stopping the service, and other notes
---------------------------
* to start and stop the service, use the 'start_service' and 'stop_service'
  scripts in /kb/deployment/services/trees
* on test machines, tree services listen on port 7047, so this port must be open
* after starting the service, the process id of the serivice is stored in the 
  'service.pid' file in /kb/deployment/services/trees
* log files are currently dumped in the /kb/deployment/services/trees/log
  directory, but this will change once central logging is adopted
* 'make clean' from the '/kb/dev_container/modules/trees' dir will delete all
  deployed files
* 'make redeploy' will perform a fresh deployment (clean followed by deploy)
* cleaning and redeploying will not kill a running service!
* The first time the service is run, an error stating that a file cannot be unlinked
  may appear in the log/error.log file.  This is expected, and occurs because the
  first time Inline:Java is run, some temporary files are created and removed. The
  slave Starman processes cannot delete this file because the master already does.
  As far as we can tell, this does not affect the actual functioning of the service.


Testing
----------
The backend of the tree services are written in Perl (for compatibility with the type compiler), so
tests are also implemented in Perl and can be found in the 't' directory.  For compliance with KBase,
there is also a 'test' directory which is currently a sym link to the 't' directory.  In the future,
if additional non-perl tests are added, the 'test' directory may be converted to an explicit directory.

To run tests, enter the 't' directory and run any of the available scripts (which should all be executable).
Each test script has internal documentation indicating what functionality is tested.


To Do (Task list, created Aug 8, 2012)
----------
x) Write Loader scripts for importing data in the exchange format into the CDS
2) Load MO Trees (which involves finishing the processing of raw alignment and tree files, and handling the species tree)
x) Load SEED Trees (should be more straightforward)
x) Test basic API calls generated for us, and use these API calls to ensure loaded data is accurate
    (e.g. all_entities_Tree, get_entity_Alignmnent, chained queries to get all Trees with a given sequence included)
5) Discuss / agree on most critical workflow patterns that we need to support
6) Discuss / agree / prioritize API function calls beyond the auto-generated CDMI needed to support the workflows
7) Complete and test (with review by Dan/Bob) the makefiles, deployment scripts, start/stop service scripts

The following 3 tasks should happen concurrently and iteratively, and will be expanded after steps 5/6 above are completed:
8) Implement API function calls in order of prioritized list.
9) Create a broad set of unit tests for each set of new API function calls
10) Write API function call documentation (in the type spec indicating arguments, return values, error handling) and make sure
    this documentaton is available to kbase.us and in KBase style

11) Wrap server calls as command-line scripts that can be released with the rest of the KBase Library
    (in theory the wrap_perl script written by Bob can automate this)
12) Test/debug command-line scripts and ensure that the same results as the client libs are generated
13) Deploy command-line scripts to IRIS

14) Expand the IRIS tutorial, or write a new tutorial, that walks users through a basic biological workflow
     (for example, given a new genome, find a gene family for each group, map each to a set of trees, analyze the tree ...)
15) Write tutorials for the command-line scripts (for the same workflows) in KBase style and have it added to kbase.us
16) Write tutorials for the client libs (for the same workflow, but in Perl? other languages?) in KBase style and have it added to kbase.us

17) Sketch / develop / prototype / implement tree-based and alignment-based visualizations both for displaying existing data
    and for displaying results of meta
    
18) Add support for Sifter (this should be included in the above discussions and list of workflows / API calls)




Authors
---------
Michael Sneddon, LBL (mwsneddon@lbl.gov)
Fangfang Xia, ANL (fangfang.xia@gmail.com)


Log / Major Release Notes
---------
v0.00a - 5/16/2012 - initial repo created
v0.00a - 8/9/2012 - added / tested link to some MO functions and the cpp library
v0.01  - 8/16/2012 - independent deployment and test at build meeting of basic configuration (tested on images v9-14)
