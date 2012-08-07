#!/usr/bin/perl
#
#  This set of tests is simply to see if the service is available and we can access a single basic
#  RPC call, and get something back.  It does not check the validity of the response, nor the
#  auto-gen client libs normally used to retrieve the response.  It does however, require that
#  JSON:RPC module is installed.
#
#  author:  msneddon
#  created: 5/21/2012

use strict;
use warnings;

use Test::More tests => 6;

# MAKE SURE WE LOCALLY HAVE JSON RPC LIBS
#  NOTE: this test points to the legacy client lib for JSON::RPC v0.97 or greater
#        remove the extra Legacy directory for versions JSON::RPC v0.96 or earlier
use_ok("JSON::RPC::Legacy::Client");
#use_ok("JSON::RPC::Client");

# MAKE A CONNECTION
my $client = new JSON::RPC::Legacy::Client;
#my $client = new JSON::RPC::Client;
my $tree_service_url = "http://140.221.92.55:7047";
print "looking at URL: ".$tree_service_url."\n";

# MAKE AN RPC CALL
my $callobj = {
    method  => 'Trees.get_tree',
    params  => [ '123' ],
};
my $res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^200/,"test valid rpc call");
print $client->status_line."\n";
ok($res,"test valid rpc call returned something");
if($res) { ok(!$res->is_error,"test valid rpc call returned a json" ); }
else {ok(0,"test valid rpc call returned a json"); }

# MAKE A BOGUS RPC CALL
$callobj = {
    method  => 'made_up_call!',
    params  => [ '123' ],
};
$res = $client->call($tree_service_url, $callobj);
ok($client->status_line =~ m/^500/,"test invalid rpc call");
ok(!$res,"test invalid rpc call returned nothing");

done_testing();