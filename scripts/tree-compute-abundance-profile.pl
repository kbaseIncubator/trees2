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
      tree-compute-abundance-profile -- maps reads from a metagenomic sample to abundance counts on a tree

SYNOPSIS
      tree-compute-abundance-profile [OPTIONS]

DESCRIPTION
      Retrieve the specified tree or meta information associated with the tree.  The
      raw tree is returned in newick format by default with leaf node labels in an
      arbitrary internal id that is unique only within the given tree.  By default, the
      raw tree stored in KBase is returned.  To return the tree with node labels replaced
      with KBase protein sequence IDs or cannonical feature IDs, use the options below.
      
      -t [ID], --tree-id [ID]
                        set this flag to specify the KBase ID of the tree to compute
                        abundances for; the tree is used to identify the set of sequences
                        that were aligned to build the tree; each leaf node of a tree built
                        from an alignment willbe mapped to a sequence; this script assumes
                        that trees are built from protein sequences
                        
      -m [ID], --metagenomic-sample-id [ID]
                        set this flag to specify the ID of the metagenomic sample to lookup;
                        see the KBase communities service to identifiy metagenomic samples
                        
      -a [KEY], --auth [KEY]
                        set this flag to specify the authentication key that you generated with
                        MG Rast to access private datasets; public data sets do not require
                        this flag
                        
      -s [SOURCE], --source-family [SOURCE]
                        set this flag to specify the name of the source of the protein family;
                        currently supported protein families are: 'COG';
                        default value is set to 'COG'
                        
      -f [NAME], --family-name [NAME]
                        set this flag to specify the name of the protein family used to pull
                        a small set of reads from a metagenomic sample; currently only COG
                        families are supported
                        
      -p [VALUE], --percent-identity-threshold [VALUE]
                        set this flag to specify the minimum acceptable percent identity for
                        hits, provided as a percentage and not a fraction (i.e. set to 87.5
                        for 87.5%); default value is set to 50%
                        
      -l [VALUE], --length-threshold [VALUE]
                        set this flag to specify the minimum acceptable length of a match to
                        consider a hit; default value is set to 20
                        
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        

EXAMPLES
      Map reads to a tree and get an abundance profile
      > tree-compute-abundance-profile -t 'kb|tree.18428' -m '4447970.3' -f 'COG0593' -s 'COG'
      
      
AUTHORS
      Michael Sneddon (mwsneddon\@lbl.gov)
      Keith Keller (kkeller\@lbl.gov)
      
";

my $help = '';
my $treeId = '';
my $mgId='';
my $protFamSrc='COG';
my $protFamName='';
my $pctIdt=50;
my $matchThreshold=20;
my $auth="";
my $opt = GetOptions (
        "help" => \$help,
        "tree-id=s" => \$treeId,
        "metagenomic-sample-id=s" => \$mgId,
        "source-family=s" => \$protFamSrc,
        "family-name=s" => \$protFamName,
        "percent-identity-threshold=f" => \$pctIdt,
        "length-threshold=i" => \$matchThreshold,
        "auth=s" => \$auth
        );

if($help) {
     print $DESCRIPTION;
     exit 0;
}

my $n_args = $#ARGV+1;
if($n_args==0) {
    
    # make sure we have all we need
    if(!$treeId) {
        print STDERR "FAILURE - missing flag to specify a Tree ID.  Run with --help for usage.\n";
        exit 1;
    }
    if(!$mgId) {
        print STDERR "FAILURE - missing flag to specify a metagenomic sample ID.  Run with --help for usage.\n";
        exit 1;
    }
    if(!$protFamName) {
        print STDERR "FAILURE - missing flag to specify a protein family name.  Run with --help for usage.\n";
        exit 1;
    }
    
    #create client
    my $treeClient;
    eval{ $treeClient = get_tree_client(); };
    if(!$treeClient) {
        print STDERR "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
        exit 1;
    }
    
    # make the call
    my $result;
    #eval {   #add eval block so errors don't crash execution, should really handle exceptions here.
        my $params = {};
        $params->{'protein_family_name'} = $protFamName;
        $params->{'protein_family_source'} = $protFamSrc;
        $params->{'metagenomic_sample_id'} = $mgId;
        $params->{'tree_id'} = $treeId;
        $params->{'percent_identity_threshold'} = $pctIdt;
        $params->{'match_length_threshold'} = $matchThreshold;
        $params->{'mg_auth_key'} = $auth;
        
        $result = $treeClient->compute_abundance_profile($params);
    #};

    if($result) {
        my $abundance_profile = $result->{abundances};
        foreach my $leaf (keys %$abundance_profile) {
            print $leaf."\t".$abundance_profile->{$leaf}."\n";
        }
        print STDERR "found $result->{n_hits} hits of $result->{n_reads} metagenomic reads\n";
    } else {
        print STDERR "FAILURE - command did not execute successfully.\n";
        exit 1;
    }
    exit 0;
}

print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
exit 1;

