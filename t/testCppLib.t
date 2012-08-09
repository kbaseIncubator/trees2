#!/usr/bin/perl
#
#  This set of tests is designed to test the C++ set of functions (wrapped by swig)
#  in the KBTree Library located in the lib/KBTree_cpp_lib directory.  The tests are
#  local, meaning there are no service calls tested here!!! It also means that you
#  have to have the KBTree libs compiled locally for this to work!!  To instead test
#  the service calls which invoke KBTreeUtil remotely, use the testKBTreeLibFunctions.t
#  script instead.
#
#  NOTE: this test suite is not currently up-to-date, and needs to be updated.  Use
#  testKBTreeLibFunctions.t for now...
#
#  author:  msneddon
#  created: 7/17/2012

use strict;
use warnings;
use Test::More tests => 2;

use lib "../lib/KBTree_cpp_lib/lib/perl_interface";
use_ok('KBTreeUtil');




print "Testing KBTreeUtil Interface\n";

my $TreeString = "((A,C)D:50,A)E;";
my $t = new KBTreeUtil::KBTree($TreeString, 0);
my $NewTreeString =$t->toNewick();
ok( $TreeString eq $NewTreeString );
print $TreeString."\n";
print $NewTreeString."\n";
$t->printTree();
my %map = ("A"=>"");
$t->removeNodesByNameAndSimplify(%map);



#print $t->getNodeCount();
#print "\ndone.\n";

done_testing();