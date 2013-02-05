#!/usr/bin/perl
# This script tests the Tree scripts by calling each one
#
#  author:  msneddon
#  created: jan 2013
use strict;
use warnings;

use Test::More;
use Data::Dumper;

my $test_url = "http://localhost:7047";

my ($out,$exit_code);


#######################################################
# [tree-url] script tests
$out = `tree-url`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-url with no parameters returns exit code 0');
ok($out,'tree-url with no parameters returns a url');

$out = `tree-url --help`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-url with long help flag returns exit code 0');
ok($out,'tree-url with long help flag returns some text');

$out = `tree-url $test_url`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-url with update URL returns exit code 0');
ok($out=~m/$test_url/g,'tree-url with update URL includes expected return string');

#######################################################
# [tree-get-tree] script tests
$out = `tree-get-tree`;
$exit_code = ($? >> 8);
ok($exit_code!=0,'tree-get-tree with no parameters returns error exit code that is not 0');
ok($out,'tree-get-tree with no parameters returns a message');

$out = `tree-get-tree madeUpID`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-get-tree with bogus treeId still exits with error code 0');
ok($out eq '','tree-get-tree with bogus treeId returns nothing');

$out = `tree-get-tree 'kb|tree.0'`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-get-tree with real treeId  exits with error code 0');
ok($out,'tree-get-tree with real treeId returns something');

$out = `tree-get-tree -m 'kb|tree.0'`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-get-tree with real treeId and -m flag exits with error code 0');
ok($out=~m/\[leaf_count\]/g,'tree-get-tree with real treeId and -m flag returns with string indicating meta data was returned');


#######################################################
# [tree-get-tree] script tests
$out = `echo '' | tree-get-leaf-nodes`;
$exit_code = ($? >> 8);
ok($exit_code!=0,'tree-get-leaf-nodes with no parameters returns error exit code that is not 0');
ok($out,'tree-get-tree with no parameters returns a message');

$out = `tree-get-leaf-nodes '(a,b,c,d,e,f,g)root;'`;
$exit_code = ($? >> 8);
ok($exit_code==0,'tree-get-leaf-nodes with a tree returned with error code 0');
ok($out eq "a\nb\nc\nd\ne\nf\ng\n",'tree-get-leaf-nodes with a tree returns proper output');


#print $out."\n";
#print $exit_code."\n";








done_testing();