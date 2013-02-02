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
      tree-get-leaf-nodes -- 

SYNOPSIS
      tree-get-leaf-nodes newick_tree [--input=inputFileName --output=outputFileName]

DESCRIPTION

      -t, --tree
                        a string in newick format
      -i, --input
                        specify an input file to read from
      -o, --output
                        specify an output file to write the results to
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        
EXAMPLES
      Retrieve all the leaf nodes of a given newick tree
      > tree-get-leaf-nodes -t='(l1,((l2,l3)n2,(l4,l5)n3)n1)root;'

SEE ALSO
      tree-
      
AUTHORS

      
";

my $n_args = $#ARGV + 1;

my $help = '';
my $treeString = '';
my $inputFile = '';
my $outputFile = '';

my $opt = GetOptions("help" => \$help,
                     "input=s" => \$inputFile,
                     "output=s" => \$outputFile,
                     "tree=s" => \$treeString);

if($help) {
     print $DESCRIPTION;
     exit 0;
}

if($n_args == 0) {
    print "FAILURE - no feature or sequence specified.  Run with --help for usage.\n";
    exit 1;
} 


#create client
my $treeClient;

eval{ $treeClient = get_tree_client(); };

my $client_error = $@;
if ($client_error) { print Dumper($client_error);  exit 1;}

if(!$treeClient) {
    print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
    exit 1;
}


if($n_args == 1) {
    
    if($treeString) {
        my @treeList;

        eval {
            @treeList = split(',', $treeString);
	};

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $i = 0;
        my @tree_leafnodes = ();
        eval {
            for($i = 0; $i < @treeList; $i++) {
                push(@tree_leafnodes, $treeClient->extract_leaf_node_names($treeList[$i]));
            }
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        foreach my $node (@tree_leafnodes) {
            foreach my $obj (@$node) {
                print $obj;
                print '\t';
            }
        }
        exit 0;
    } 
    else {
        my $inputFileHandle;
        if ($inputFile) {
            open($inputFileHandle, "<", $inputFile) or die "Cannot open $inputFile: $!";
        }
        else {
            $inputFileHandle = \*STDIN;
        }

        my $treeString = "";

        eval {
            while (my $line = <$inputFileHandle>) {
                $treeString = $treeString.$line;
            }
            close $inputFileHandle;
        };

        my @treeList = split('\t', $treeString);

        my $i = 0;
        my @tree_leafnodes = ();
        eval {
            for($i = 0; $i < @treeList; $i++) {
                push(@tree_leafnodes, $treeClient->extract_leaf_node_names($treeList[$i]));
            }
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        foreach my $node (@tree_leafnodes) {
            foreach my $obj (@$node) {
                print $obj;
                print '\t';
            }
        }
        exit 0;
    }        
}
elsif ($n_args == 2) {
    my $inputFileHandle;
    if ($inputFile) {
        open($inputFileHandle, "<", $inputFile) or die "Cannot open $inputFile: $!";
    }
    else {
        $inputFileHandle = \*STDIN;
    }

    my $tree_html;
    my $treeString = "";

    eval {
        while (my $line = <$inputFileHandle>) {
            $treeString = $treeString.$line;
        }
        close $inputFileHandle;
    };

    my $client_error = $@;
    if ($client_error) {
        print Dumper($client_error);
        exit 1;
    }

    my @treeList;
    eval {
        @treeList = split('\t', $treeString);
    };

    $client_error = $@;
    if ($client_error) {
        print Dumper($client_error);
        exit 1;
    }

    my $i = 0;
    my @tree_leafnodes = ();
    eval {
        for($i = 0; $i < @treeList; $i++) {
            push(@tree_leafnodes, $treeClient->extract_leaf_node_names($treeList[$i]));
        }
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


    foreach my $node (@tree_leafnodes) {
        foreach my $obj (@$node) {
            print $outputFileHandle $obj;
            print $outputFileHandle '\t';
        }
    }
    close($outputFileHandle);
    exit 0;
}
else {
    print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
    exit 1;
}

