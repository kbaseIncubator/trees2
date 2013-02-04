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
      tree-get-tree -- retrieve tree or tree meta data from the CDS

SYNOPSIS
      tree-get-tree [OPTIONS] [TREE_ID]

DESCRIPTION
      Retrieve the specified tree or meta information associated with the tree.  The
      raw tree is returned in newick format by default with leaf node labels in an
      arbitrary internal id that is unique only within the given tree.  By default, the
      raw tree stored in KBase is returned.  To return the tree with node labels replaced
      with KBase protein sequence IDs or cannonical feature IDs, use the options below.
      
      -p, --protein-sequence
                        set this flag to return the tree with node labels replaced with
                        protein sequence IDs
                        
      -f, --feature
                        set this flag to return the tree with node labels replaced with KBase
                        feature IDs.  Note that some trees may not have assigned cannonical
                        feature IDs for each node, in which case blank labels will be returned.
                        
      -b, --bootstrap-remove
                        set this flag to return the tree with bootstrap values removed
                        
      -d, --distance-remove
                        set this flag to return the tree without distance values removed
                        
      -m, --meta
                        set this flag to return meta data instead of the tree itself
                        
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        

EXAMPLES
      Retrieve the raw tree newick string
      > tree-get-tree 'kb|tree.25'
      
      Retrieve meta data about a tree
      > tree-get-tree 'kb|tree.25'

SEE ALSO
      tree-find-tree-ids
      
AUTHORS
      Michael Sneddon (mwsneddon\@lbl.gov)
      
";

my $help = '';
my $metaFlag = '';
my $replaceFeature='';
my $replaceSequence='';
my $noBootstrap='';
my $noDist='';
my $opt = GetOptions (
        "help" => \$help,
        "meta" => \$metaFlag,
        "feature" => \$replaceFeature,
        "protein-sequence" => \$replaceSequence,
        "bootstrap-remove" => \$noBootstrap,
        "distance-remove" => \$noDist
        );

if($help) {
     print $DESCRIPTION;
     exit 0;
}

my $n_args = $#ARGV+1;
if($n_args==0) {
    print "FAILURE - no tree ID specified.  Run with --help for usage.\n";
    exit 1;
} elsif($n_args==1) {
    my $treeId = $ARGV[0];
    #create client
    my $treeClient;
    eval{ $treeClient = get_tree_client(); };
    if(!$treeClient) {
        print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
        exit 1;
    }
    if($metaFlag) {
        #get meta data
        my $tree_data;
        # eval {   #add eval block so errors don't crash execution, should really handle exceptions here.
            ($tree_data) = $treeClient->get_tree_data([$treeId]);
        # };
        if(exists $tree_data->{$treeId}) {
            my $metaData = $tree_data->{$treeId};
            foreach my $label (keys %$metaData) {
                print "[".$label."]: ".$metaData->{$label}."\n";
            }
        }
        exit 0;
    } else {
        #get actual tree
        my $tree;
        # eval {   #add eval block so errors don't crash execution, should really handle exceptions here.
            my $options = {};
            if($replaceFeature && $replaceSequence) { print "FAILURE - cannot use -p option with -f; choose one or the other.\n"; exit 1; }
            if($replaceFeature) {$options->{newick_label}='feature_id';}
            if($replaceSequence) {$options->{newick_label}='protein_sequence_id';}
            if($noBootstrap) {$options->{newick_bootstrap}='none';}
            if($noDist) {$options->{newick_distance}='none';}
            ($tree) = $treeClient->get_tree($treeId,$options);
        # };
        if($tree) { print $tree."\n"; }
        exit 0;
    }
        
}

print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
exit 1;

