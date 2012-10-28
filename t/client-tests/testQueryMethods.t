#!/usr/bin/perl
# This script tests the service calls which provide tree introspection methods - which basically means
# that these methods take an input tree (usually) in newick format, and returns some property of the
# tree, such as a list of leaves or the number of nodes.  Most of these methods use a compiled C++
# library on the backend, so this suite of tests also makes sure that this c++ library is functioning properly.
#
#  author:  msneddon
#  created: 8/9/2012
#  last updated: 10/27/2012, msneddon
use strict;
use warnings;

use Test::More tests => 50;
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

######### TEST SET 1 ######### 
# TRY TO RENAME NODES IN THE TREE
my $listOfIds = ['kb|g.9988.peg.1744','kb|g.9988.peg.1741'];
my $tree_ids=$client->get_tree_ids_by_feature($listOfIds);
print Dumper($tree_ids);


done_testing();
