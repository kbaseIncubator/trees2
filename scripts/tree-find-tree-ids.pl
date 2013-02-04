#!/usr/bin/env perl
use strict;
use warnings;
use Getopt::Long;
use Data::Dumper;

use Bio::KBase::Tree::Client;
use Bio::KBase::Tree::Util qw(get_tree_client);


my $DESCRIPTION =
"
NAME
      tree-find-tree-ids -- returns the tree IDs that include the specified sequence or feature

SYNOPSIS
      tree-find-tree-ids [OPTIONS] [ID]

DESCRIPTION
      Given a KBase feature or sequence ID, retrieve the set of trees that include the
      given feature/sequence.  By default, if the type of ID is not specified with one of the
      options below, then this method assumes that IDs are feature IDs.  If an ID is to be
      passed in as an argument, then only a single ID can be used.  If you wish to call this
      method on multiple feature/sequence IDs, then you must pass in the list through standard-in
      or a text file, with one ID per line.
                        
      -f, --feature
                        indicate that the IDs provided are feature IDs
      -p, --protein-sequence
                        indicate that the IDs provided are protein_sequence_ids (MD5s)                        
      -i, --input
                        specify input file to read from;  each feature/sequence id must
                        be on a separate line in this file
      -h, --help
                        diplay this help message, ignore all arguments
                        
EXAMPLES
      Retrieve tree ids based on a set of feature_ids
      > tree-find-tree-ids -f='kb|g.9988.peg.1744\tkb|g.9988.peg.1741'
      
      Retrieve tree ids based on a set of protein_sequence_ids
      > echo cf9e9e74e06748fb161d07c8420e1097 | tree-find-tree-ids -p

AUTHORS
      Matt Henderson (mhenderson\@lbl.gov)
      Michael Sneddon (mwsneddon\@lbl.gov)
      
";



# declare variables that come from user input
my $help = '';
my $usingFeature = "";
my $usingSequence = "";
my $inputFile = "";

# first parse command line options
my $opt = GetOptions (
        "help" => \$help,
        "feature" => \$usingFeature,
        "protein-sequence" => \$usingSequence,
        "input=s" => \$inputFile,
        );
if ($help) {
     print $DESCRIPTION;
     exit 0;
}

my $n_args = $#ARGV + 1;

my $id_list=[];
# if we have specified an input file, then read the file
if($inputFile) {
     my $inputFileHandle;
     open($inputFileHandle, "<", $inputFile);
     if(!$inputFileHandle) {
          print "FAILURE - cannot open '$inputFile' \n$!\n";
          exit 1;
     }
     eval {
          while (my $line = <$inputFileHandle>) {
               chomp($line);
               push @$id_list,$line;
          }
          close $inputFileHandle;
     };
}

# if we have a single argument, then accept it as the treeString
elsif($n_args==1) {
     my $id = $ARGV[0];
     chomp($id);
     push @$id_list,$id;
     print "reading $id\n";
}

# if we have no arguments, then read the tree from standard-in
elsif($n_args == 0) {
     while(my $line = <STDIN>) {
          chomp($line);
          push @$id_list,$line;
     }
}

#create client
my $treeClient;
eval{ $treeClient = get_tree_client(); };
my $client_error = $@;
if ($client_error) {
     print Dumper($client_error);
     print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
     exit 1;
}

# if we have some ids, we can continue;
if(scalar(@$id_list)>0) {
     if($usingSequence) {
          my $tree_ids;
          eval {
               $tree_ids = $treeClient->get_tree_ids_by_protein_sequence($id_list);
          };
          $client_error = $@;
          if ($client_error) {
               print Dumper($client_error);
               print "FAILURE - error calling Tree service.\n";
               exit 1;
          }
          foreach my $t (@$tree_ids) {
               print $t."\n";
          }
     } else {
          my $tree_ids;
          eval {
               $tree_ids = $treeClient->get_tree_ids_by_feature($id_list);
          };
          $client_error = $@;
          if ($client_error) {
               print Dumper($client_error);
               print "FAILURE - error calling Tree service.\n";
               exit 1;
          }
          foreach my $t (@$tree_ids) {
               print $t."\n";
          }
     }
     exit 0;
} else {
     print "FAILURE - no ids provided.  Run with --help for usage.\n";
     exit 1;
}