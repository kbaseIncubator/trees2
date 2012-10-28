#!/usr/bin/perl
#
#  author:  msneddon
#  created: 8/9/2012
#  modified: landml

use strict;
use warnings;

use Test::More tests => 27;
use Data::Dumper;
use Test::More;
use lib "../lib/";
use TreesClient;

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS INSTALLED
#  NOTE: for initial testing, you may have to modify TreesClient.pm to also
#        point to the legacy interface
my $client;
$client = TreesClient->new();
ok(defined($client),"Did an object get defined with no URL?");

#
#  Test 2 - Is the object in the right class?
#
isa_ok( $client, 'TreesClient', "Is it in the right class" );   

#
#  Test 3 - Can the object do all of the methods
#

can_ok($client, qw[    get_tree
 get_trees
 all_tree_ids
 get_kbase_ids_from_alignment_row
 get_trees_with_entire_seq
 get_trees_with_overlapping_seq
 get_trees_with_entire_domain
 get_trees_with_overlapping_domain
 substitute_node_names_with_kbase_ids
 extract_leaf_node_names
 extract_node_names
 get_node_count
 get_leaf_count
 replace_node_names
 remove_node_names_and_simplify
 add_node_to_tree
 build_tree_from_sequences
 build_tree_from_fasta 
]);

#
#  Test 4 - Can a new object be created with valid parameter? 
#

# MAKE A CONNECTION AND ENSURE WE ARE CONNECTED
#my $tree_service_url = "http://140.221.92.55:7047";
my $tree_service_url = "http://localhost:7047";


$client = TreesClient->new('tree_service_url');
ok(defined($client),"instantiating tree client with invalid URL");

$client = TreesClient->new($tree_service_url);
ok(defined($client),"instantiating tree client with the valid URL");

#  TEST method get_tree
#  $result = get_tree(tree_id, options)
#
my $result;
note("Testing get_tree.  First with too few and too many parameters");
eval { $result  = $client->get_tree(); };
isnt($@, undef, "Call with no parameters failed properly $@");

eval { $result  = $client->get_tree(1); };
isnt($@, undef, "Call with too few parameters failed properly $@");

eval { $result  = $client->get_tree(1,2,3); };
isnt($@, undef, "Call with too many parameters failed properly $@");

eval { $result  = $client->get_tree('one','two'); };
isnt($result, undef, "Call with character parameters worked properly ");
note ("Return=$@ and Result=$result");

# CREATE A SIMPLE NEWICK TREE STRING TO USE WITH 5 LEAVES (l1...l5)
# AND FOUR INTERNAL NODES (n1...n3 + root)
my $newick = "(l1,((l2,l3)n2,(l4,l5)n3)n1)root;";

######### TEST SET 1 ######### 
# RETRIEVE THE NUMBER OF NODES AND LEAVES
my $leaf_node_count=$client->get_leaf_count($newick);
my $all_node_count=$client->get_node_count($newick);
ok($leaf_node_count == 5,"leaf node count should  5");
ok($all_node_count == 9,"tree should have 9 total nodes");

######### TEST SET 2 ######### 
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
ok(!exists $leaf_node_hash{"mr. node"} ,"node named 'mr. node' should certainly NOT be in the list");


######### TEST SET 3 ######### 
# TRY TO EXTRACT THE NAMES OF ALL NODES IN A LIST
my $all_node_list_ref=$client->extract_node_names($newick);
# make sure length is 5
ok(9==scalar @$all_node_list_ref,"length of full node list must be 9");
# convert it to a hash for quick lookups, then make sure we find all 5 leaves and nothing else
my %all_node_hash; foreach (@$all_node_list_ref) { $all_node_hash{$_}=1;}
ok(exists $all_node_hash{"l1"} ,"leaf named l1 should be in the list");
ok(exists $all_node_hash{"l2"} ,"leaf named l2 should be in the list");
ok(exists $all_node_hash{"l3"} ,"leaf named l3 should be in the list");
ok(exists $all_node_hash{"l4"} ,"leaf named l4 should be in the list");
ok(exists $all_node_hash{"l5"} ,"leaf named l5 should be in the list");
ok(exists $all_node_hash{"n1"} ,"node named n1 should be in the list");
ok(exists $all_node_hash{"n2"} ,"node named n2 should be in the list");
ok(exists $all_node_hash{"n3"} ,"node named n3 should be in the list");
ok(exists $all_node_hash{"root"} ,"node named root should be in the list");
ok(!exists $all_node_hash{"mr. node"} ,"node named 'mr. node' should still NOT be in the list");


######### TEST SET 4 ######### 
# TRY TO RENAME NODES IN THE TREE
my %replacements;
$replacements{"n2"}="mr. node";
$replacements{"n3"}="dr. node";
$replacements{"l2"}="myLeaf";
$replacements{"l5"}="yourLeaf";
my $relabeled_tree=$client->replace_node_names($newick,\%replacements);
my $correct_relabeled_tree= "(l1,((myLeaf,l3)mr. node,(l4,yourLeaf)dr. node)n1)root;";
#print $relabeled_tree."\n";
ok($relabeled_tree eq $correct_relabeled_tree, "relabeled tree should match a correct relabeled tree");

######### TEST SET 5 (note: has dependency on test set 4) ######### 
# TRY TO REMOVE NODES IN THE TREE
my @removal;
push(@removal,"mr. node");
push(@removal,"myLeaf");
my $smaller_tree=$client->remove_node_names_and_simplify($relabeled_tree,\@removal);
my $correct_smaller_tree= "(l1,(l3,(l4,yourLeaf)dr. node)n1)root;";
#print $smaller_tree."\n";
ok($smaller_tree eq $correct_smaller_tree, "smaller tree should match a correct smaller tree");




done_testing();
