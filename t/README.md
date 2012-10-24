
This is the test directory for the alignment and phylogenetic tree services.
=================================================================================

To run the tests:
---------------------------
 - Enter the top-level directory of the trees module in dev_container/modules.
 - To deploy the server on localhost, follow deployment instructions in the top-level readme file
 - If testing a server on a rem

 - Type make deploy-all to deploy the client, scripts, and server
 

To add a new test:
---------------------------
 - create a new file in the appropriate directory (either "t/client-tests", "t/script-tests",
   or "t/server-tests") OR open an existing test script file.
 - if creating a new test file, add the name of the file to the list of available tests below and
   add the command to run the script under the appropriate makefile target in the trees makefile
 - if creating a new set of tests in perl, look at an existing test script to see how to read
   the test configuration file (test.cfg) to point to the client to the proper host.


Available Tests:
----------------------------

    client-tests: (tests which use the Tree Service client in the 'lib' directory)
        - testBasicResponses.t -
        - testQueryMethods.t -
        - testIntrospectionMethods.t -
    
    script-tests: (tests which call scripts in the 'scripts' directory)
    (none at this time because there are no scripts to deploy)
    
    server-tests: (tests which connect directly to a server without invoking the Tree service client)
        - testServerUp.t - simple diagnostic to directly connect to a url to see if it is properly running a Tree server







