#!/usr/bin/perl
#
#  author:  msneddon
#  created: 5/21/2012

use strict;
use warnings;

use Data::Dumper;
use Test::More tests => 3;

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS
use_ok("JSON::RPC::Client");

# MAKE A CONNECTION
my $client = new JSON::RPC::Client;
my $tree_service_url = "http://140.221.92.133:5000";

# MAKE AN RPC CALL
my $callobj = {
    method  => 'Trees.get_tree',
    params  => [ '123' ], # ex.) params => { a => 20, b => 10 } for JSON-RPC v1.1
};
my $res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^200/,"test valid rpc call");


$callobj = {
    method  => 'made_up_call!',
    params  => [ '123' ], # ex.) params => { a => 20, b => 10 } for JSON-RPC v1.1
};
$res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^500/,"test invalid rpc call");



#my $client = TreesClient->new($tree_service_url);
#ok(defined($client),"instantiating tree client");


#my $result = $client->get_tree("123");
#if(defined($result)) {
#	print "gots \"$result\" !\n";
#} else {
#	print "epic fail\n";
#}






done_testing();