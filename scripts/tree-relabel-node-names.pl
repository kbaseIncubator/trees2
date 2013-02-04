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
      tree-relabel-node-names -- allows you to relabel node names in a newick tree

SYNOPSIS
      tree-relabel-node-names -t newick_tree -r=replacement_names [--input=inputFileName --output=outputFileName]

DESCRIPTION
     Given a tree in newick format, relabel the specified nodes with replacement names.


      -t, --tree
                        a string in newick format
      
      -r, --replacement
                        specify the names to relabel and thier replacements
                        
      -i, --input
                        specify an input file to read the tree from
      -o, --output
                        specify an output file to write the results to
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        
EXAMPLES
      Retrieve all the leaf nodes of a given newick tree
      > tree-replace-node-names -t='(l1,((l2,l3)n2,(l4,l5)n3)n1)root;\tl1\tl55' -r='l1\tnode'
      
AUTHORS
      Matt Henderson (mhenderson\@lbl.gov)
      Michael Sneddon (mwsneddon\@lbl.gov)

      
";

my $n_args = $#ARGV + 1;

my $help = '';
my $treeString = '';
my $replacementString = '';

my $inputFile = '';
my $outputFile = '';

my $stdinString = "";

my $opt = GetOptions("help" => \$help,
                     "input=s" => \$inputFile,
                     "output=s" => \$outputFile,
                     "tree=s" => \$treeString,
                     "replacement=s" => \$replacementString);

if($help) {
     print $DESCRIPTION;
     exit 0;
}

if($n_args == 0) {
    while(my $line = <STDIN>) {
        $n_args = 1;
        $stdinString = $stdinString.$line;
    }

    if ($n_args == 0) {
        print "FAILURE - no feature or sequence specified.  Run with --help for usage.\n";
        exit 1;
    }
} 


#create client
my $treeClient;

eval{ $treeClient = get_tree_client(); };

my $client_error = $@;
if ($client_error) { print Dumper($client_error);  exit 1;}

if(!$treeClient) {
    print "FAILURE - unable to create tree service client.  Is your tree URL correct? see tree-url.\n";
    exit 1;
}


if ($n_args == 1) {
        my $inputFileHandle;
        if ($inputFile) {
            open($inputFileHandle, "<", $inputFile) or die "Cannot open $inputFile: $!";
        }
        else {
            $inputFileHandle = \*STDIN;
        }

        my $inputString = $stdinString;

        eval {
            while (my $line = <$inputFileHandle>) {
                $inputString = $inputString.$line;
            }
            close $inputFileHandle;
        };

        my @inputList = split('\t', $inputString);

        $treeString = shift(@inputList);
        $replacementString = join('=>', @inputList);

        my %replacements;

        my $i;
        for($i = 0; $i < @inputList; $i+=2) {
            $replacements{$inputList[$i]} = $inputList[$i+1];
        }

        my $new_tree;
        eval {
            $new_tree = $treeClient->replace_node_names($treeString, \%replacements);
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        print $new_tree."\t";
        exit 0;
}
elsif ($n_args == 2) {
    
    if($treeString && $replacementString) {
        my %replacements;

        $replacementString = substr($replacementString, 1, -1);
        my @replacementList = split(',', $replacementString);

        my @hash_elements;
        foreach (@replacementList) {
            @hash_elements = split('=>', $_);
            $replacements{$hash_elements[0]} = $hash_elements[1];
        }

        my $new_tree;
        eval {
            $new_tree = $treeClient->replace_node_names($treeString, \%replacements);
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        print $new_tree."\t";
        exit 0;
    } 
    elsif ($inputFile && $outputFile) {
        my $inputFileHandle;
        if ($inputFile) {
            open($inputFileHandle, "<", $inputFile) or die "Cannot open $inputFile: $!";
        }
        else {
            $inputFileHandle = \*STDIN;
        }

        my $treeString = $stdinString;

        eval {
            while (my $line = <$inputFileHandle>) {
                $treeString = $treeString.$line;
            }
            close $inputFileHandle;
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my %replacements;

        $replacementString = substr($replacementString, 1, -1);
        my @replacementList = split(',', $replacementString);

        my @hash_elements;
        foreach (@replacementList) {
            @hash_elements = split('=>', $_);
            $replacements{$hash_elements[0]} = $hash_elements[1];
        }

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $new_tree;
        eval {
            $new_tree = $treeClient->replace_node_names($treeString, %replacements);
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $outputFileHandle;
        if ($outputFile) {
            open($outputFileHandle, ">", $outputFile) or die "Cannot open $outputFile: $!";
        }
        else {
            $outputFileHandle = \*STDOUT;
        }

        print $outputFileHandle $new_tree."\t";
        close($outputFileHandle);
        exit 0;
    }        
}
else {
    print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
    exit 1;
}

