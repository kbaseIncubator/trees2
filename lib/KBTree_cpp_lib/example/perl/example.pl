#!/usr/bin/perl

use lib "../../lib/perl_interface";
use KBTreeUtil;

print "Testing KBTreeUtil Interface\n";

my $TreeString = "((A,C)D,A)E;";
$t = new KBTreeUtil::KBTree($TreeString);
my $nodeCount = $t->getNodeCount();



print $t->getNodeCount();
print "\ndone.\n";

