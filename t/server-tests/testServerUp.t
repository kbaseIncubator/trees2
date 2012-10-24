#!/usr/bin/perl
#
#  This set of tests is simply to see if the service is available and we can access a single basic
#  RPC call, and get something back.  It does not check the validity of the response, nor the
#  auto-gen client libs normally used to retrieve the response.  It does however, require that
#  JSON::RPC module is installed.
#
#  author:  msneddon
#  created: 5/21/2012
use strict;
use warnings;

use FindBin;
use lib "$FindBin::Bin/..";
use TreeTestConfig qw(getHost getPort);

use Test::More tests => 6;

#  MAKE SURE WE LOCALLY HAVE JSON RPC LIBS (TEST 1)
use_ok("JSON::RPC::Client");

# CREATE A JSON RPC CLIENT AND DETERMINE THE URL TO USE BASED ON THE CONFIG MODULE
my $client = new JSON::RPC::Client;
my $host=getHost(); my $port=getPort();
print "-> attempting to connect to:'".$host.":".$port."'\n";


# MAKE A VALID RPC CALL (TEST 2,3,4)
my $callobj = {
    method  => 'Tree.get_node_count',
    params  => ["(a,(b,c))root"],
};
my $res = $client->call($host.":".$port, $callobj);
ok($client->status_line =~ m/^200/,"test a valid rpc call");
if(!($client->status_line =~ m/^200/)) { print "SERVER RESPONSE: '".$client->status_line."'\n"; }
ok($res,"test that a valid RPC call returned something");
if($res) { ok(!$res->is_error,"test that valid RPC call returned a json" ); }
else {ok(0,"test that a valid RPC call returned a json"); }
#print  $res->result."\n";

# MAKE A BOGUS RPC CALL (TEST 5,6)
$callobj = {
    method  => 'made_up_call_which_does_not_exist!',
    params  => [ '123fakeParameter' ],
};
$res = $client->call($host.":".$port, $callobj);
ok($client->status_line =~ m/^500/,"test invalid rpc call");
ok(!$res,"test invalid rpc call returned nothing");

done_testing();
