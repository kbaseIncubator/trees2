
This directory contains tests of the KBaseTrees service.

All tests in perl-tests (which use the perl clients and scripts to run tests
against a Tree server) require that a KBaseTrees server is up and listening.

By default, the perl-tests assume the server is at "http://127.0.0.1:7047", but
this can be changed by manually editing the perl-tests/TreeTestConfig.pm file
before running tests.

Tests can be executed via make using the standard KBase targets:
   make test
   make test-scripts
   make test-client
   make test-server

Or directly in perl, e.g.:
   perl test/perl-tests/testIntrospectionMethods.t
   
Or using 'prove', e.g.:
   prove test/perl-tests/testIntrospectionMethods.t
   
Prove can also be used to run all the perl tests:
   prove test/perl-tests
