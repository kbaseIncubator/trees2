#!/usr/bin/perl
# This script tests the service calls which use the compiled KBTreeUtil functionality, which is implemented
# on the backend in C++.  These functions do not query the CDS, but instead manipulate tree objects.
#
#  author:  msneddon
#  created: 8/9/2012
use strict;
use warnings;

use Test::More tests => 12;
use Data::Dumper;
use Test::More;
use lib "../lib/";

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS INSTALLED
#  NOTE: for initial testing, you may have to modify TreesClient.pm to also
#        point to the legacy interface
use_ok("JSON::RPC::Legacy::Client");
use_ok("TreesClient");

# MAKE A CONNECTION AND ENSURE WE ARE CONNECTED
my $tree_service_url = "http://140.221.92.55:7047";
my $client = TreesClient->new($tree_service_url);
ok(defined($client),"instantiating tree client");

# CREATE A SIMPLE NEWICK TREE STRING TO USE WITH 5 LEAVES (l1...l5)
# AND FOUR INTERNAL NODES (n1...n3 + root)
my $newick = "(l1,((l2,l3)n2,(l4,l5)n3)n1)root;";

######### TEST SET 1 ######### 
# TRY TO EXTRACT THE NAMES OF THE LEAF NODES IN A LIST
my $leaf_node_list_ref=$client->extract_leaf_node_names($newick);
# make sure length is 5
ok(5==scalar @$leaf_node_list_ref,"length of leaf list must be 5");
# convert it to a hash for quick lookups, then make sure we find all 5 leaves and nothing else
my %leaf_node_hash; foreach (@$leaf_node_list_ref) { $leaf_node_hash{$_}=1;}
ok(exists $leaf_node_hash{"l1"} ,"leaf named l1 should be in the list");
ok(exists $leaf_node_hash{"l2"} ,"leaf named l2 should be in the list");
ok(exists $leaf_node_hash{"l3"} ,"leaf named l3 should be in the list");
ok(exists $leaf_node_hash{"l4"} ,"leaf named l4 should be in the list");
ok(exists $leaf_node_hash{"l5"} ,"leaf named l5 should be in the list");
ok(!exists $leaf_node_hash{"n2"} ,"node named n2 should NOT be in the list");
ok(!exists $leaf_node_hash{"root"} ,"node named root should NOT be in the list");





done_testing();
