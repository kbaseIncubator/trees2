#!/usr/bin/perl
#
#  author:  msneddon
#  created: 5/21/2012

use strict;
use warnings;

use Data::Dumper;
use Test::More tests => 3;

use lib "../lib/";

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS
#  NOTE: for initial testing, you may have to modify TreesClient.pm to also
#        point to the legacy interface
use_ok("JSON::RPC::Legacy::Client");
use_ok("TreesClient");

# MAKE A CONNECTION
my $tree_service_url = "http://140.221.92.133:5000";
my $client = TreesClient->new($tree_service_url);
ok(defined($client),"instantiating tree client");


my $result = $client->get_tree("123");
if(defined($result)) {
	print "gots \"$result\" !\n";
} else {
	print "epic fail\n";
}

done_testing();