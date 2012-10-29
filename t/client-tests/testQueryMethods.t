#!/usr/bin/perl
# This script tests the set of functions that retrieve records from kbase.  Because it currently
# connects to the CDM, it is difficult to test results for exact matches because this will change
# as data gets loaded or new trees are built.  So to 
#
#  author:  msneddon
#  created: 10/25/2012
#  last updated: 10/28/2012
use strict;
use warnings;

use Test::More tests => 3;
use Data::Dumper;
use Test::More;

use FindBin;
use lib "$FindBin::Bin/..";
use TreeTestConfig qw(getHost getPort);

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS INSTALLED
use_ok("JSON::RPC::Client");
use_ok("Bio::KBase::Tree::Client");

# MAKE A CONNECTION (DETERMINE THE URL TO USE BASED ON THE CONFIG MODULE)
my $host=getHost(); my $port=getPort();
print "-> attempting to connect to:'".$host.":".$port."'\n";
my $client = Bio::KBase::Tree::Client->new($host.":".$port);
ok(defined($client),"instantiating tree client");

my $tree_ids=[];
######### TEST SET 1 ######### 
# TRY TO RETRIEVE TREES BASED ON FEATURE ID
my $listOfFeatureIds = ['kb|g.9988.peg.1744','kb|g.9988.peg.1741'];
$tree_ids=$client->get_tree_ids_by_feature($listOfFeatureIds);
print Dumper($tree_ids);

######### TEST SET 2 ######### 
# TRY TO RETRIEVE TREES BASED ON PROTEIN SEQUENCE ID
my $listOfProtSeqIds = ['b3421022c78785ebfd349762870e9fef','2845879451b5c84036e9284018669922'];
$tree_ids=$client->get_tree_ids_by_protein_sequence($listOfProtSeqIds);
print Dumper($tree_ids);

######### TEST SET 3 ######### 
# TRY TO RETRIEVE ALIGNMENTS BASED ON FEATURE ID
my $listOfFeatureIds = ['kb|g.9988.peg.1744','kb|g.9988.peg.1741'];
$tree_ids=$client->get_alignment_ids_by_feature($listOfFeatureIds);
print Dumper($tree_ids);

######### TEST SET 4 ######### 
# TRY TO RETRIEVE ALIGNMENTS BASED ON PROTEIN SEQUENCE ID
my $listOfProtSeqIds = ['b3421022c78785ebfd349762870e9fef','2845879451b5c84036e9284018669922'];
$tree_ids=$client->get_alignment_ids_by_protein_sequence($listOfProtSeqIds);
print Dumper($tree_ids);

######### TEST SET 5 ######### 
# TRY TO GET THE ACTUAL TREE FROM A TREE ID
my $tree_id = 'kb|tree.41'; #36363;
my $options = {format=>"newick"};
my $tree=$client->get_tree($tree_id, $options);
print Dumper($tree);


done_testing();
