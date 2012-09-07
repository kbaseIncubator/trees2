#!/usr/bin/perl
#
#  This set of tests is simply to see if the service is available and we can access a single basic
#  RPC call, and get something back.  It does not check the validity of the response, nor the
#  auto-gen client libs normally used to retrieve the response.  It does however, require that
#  JSON:LEGACY::RPC module is installed.
#
#  author:  msneddon
#  created: 5/21/2012
use strict;
use warnings;

use Test::More tests => 6;

#  MAKE SURE WE LOCALLY HAVE JSON RPC LIBS
#  NOTE: this test points to the legacy client lib for JSON::RPC v0.97 or greater
#        remove the extra "Legacy" directory for versions JSON::RPC v0.96 or earlier
#        as in: JSON::RPC::Client
use_ok("JSON::RPC::Legacy::Client");

# MAKE A CONNECTION
my $client = new JSON::RPC::Legacy::Client;
my $tree_service_url = "http://140.221.92.144:7047";
print "-> looking at URL: ".$tree_service_url."\n";

# MAKE A VALID RPC CALL
my $callobj = {
    method  => 'Tree.get_node_count',
    params  => ["(a,(b,c))root"],
};
my $res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^200/,"test a valid rpc call");
if(!($client->status_line =~ m/^200/)) { print "SERVER RESPONSE: '".$client->status_line."'\n"; }
ok($res,"test that a valid RPC call returned something");
if($res) { ok(!$res->is_error,"test that valid RPC call returned a json" ); }
else {ok(0,"test that a valid RPC call returned a json"); }
#print  $res->result."\n";

# MAKE A BOGUS RPC CALL
$callobj = {
    method  => 'made_up_call_which_does_not_exist!',
    params  => [ '123' ],
};
$res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^500/,"test invalid rpc call");
ok(!$res,"test invalid rpc call returned nothing");

done_testing();
