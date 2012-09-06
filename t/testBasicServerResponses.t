#!/usr/bin/perl
#  
#  The purpose of this test is to make sure we recieve some response from the server for the list of functions
#  given.  Each of these functions listed should return some value, but the actual value is not checked here.
#  Thus, this test is ideal for making sure you are actually recieving something from a service call even if
#  that service is not yet implemented yet.
#
#  If you add functionality to the Tree service API, you should add an appropriate test here.
#
#  author:  msneddon
#  created: 5/21/2012

use strict;
use warnings;

use Data::Dumper;
use Test::More;
use lib "../lib/";

#############################################################################
# HERE IS A LIST OF METHODS AND PARAMETERS THAT WE WANT TO TEST
# NOTE THAT THE PARAMETERS ARE JUST MADE UP AT THE MOMENT
my $func_calls = {  get_tree => ["kb|t123", [FORMAT =>'first']],
                    get_trees => [ ["kb|t1","kb|t2","kb|t4"], [FORMAT =>'first']],
                    all_tree_ids => [ "1" ],
                    get_trees_with_entire_seq => ["kb|g44.1", "1", "50", "opts"],
                    get_trees_with_overlapping_seq => ["kb|g44.1", "1", "50", "opts"],
                    get_trees_with_entire_domain => ["kb|d33.1", "opts"],
                    get_trees_with_overlapping_domain => ["kb|d33.1", "opts"],
                    substitute_node_names_with_kbase_ids => ["tree","opts"],
                    extract_leaf_node_names => ["(a,b)c;"]
                 };
#############################################################################
my $n_tests = (scalar(keys %$func_calls)+3); # set this to be the number of function calls + 3


# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS
#  NOTE: for initial testing, you may have to modify TreesClient.pm to also
#        point to the legacy interface
use_ok("JSON::RPC::Legacy::Client");
use_ok("Bio::KBase::Tree::Client");

# MAKE A CONNECTION
my $tree_service_url = "http://140.221.92.144:7047";
my $client = Bio::KBase::Tree::Client->new($tree_service_url);
ok(defined($client),"instantiating tree client");


# LOOP THROUGH ALL THE REMOTE CALLS AND MAKE SURE WE GOT SOMETHING
my $method_name;
for $method_name (keys %$func_calls) {
        #print "==========\n$method_name => @{ $func_calls->{$method_name}}\n";
        #my $n_args = scalar @{ $func_calls->{$method_name}};
        my $result;
        print "calling function: \"$method_name\"\n";
        {
            no strict "refs";
            $result = $client->$method_name(@{ $func_calls->{$method_name}});
        }
        ok($result,"looking for a response from \"$method_name\"");
}

done_testing($n_tests);