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
      tree-get-alignment -- 

SYNOPSIS
      tree-get-alignment [-f=FEATURE_IDS | -p=PROTEIN_SEQUENCE_IDS] [-i=inputFileName -o=outputFileName]

DESCRIPTION

                        
      -f, --feature
                        get alignments based on a list of feature_ids
      -s, --sequence
                        get alignments based on a list of protein_sequence_ids                        
      -i, --input
                        specify an input file to read from
      -o, --output      
                        specify an output file to write to
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        
EXAMPLES
      Retrieve alignments based on a set of feature_ids
      > tree-get-alignment -f='kb|g.9988.peg.1744\tkb|g.9988.peg.1741'
      
      Retrieve alignments based on a set of protein_sequence_ids
      > tree-get-alignment -s='b3421022c78785ebfd349762870e9fef\t2845879451b5c84036e9284018669922'

SEE ALSO
      tree-
      
AUTHORS

      
";

my $n_args = $#ARGV + 1;

my $help = '';
my $featureString = "";
my $sequenceString = "";
my $inputFile = "";
my $outputFile = "";

my $stdinString = "";

my $opt = GetOptions (
        "help" => \$help,
        "feature=s" => \$featureString,
        "sequence=s" => \$sequenceString,
        "input=s" => \$inputFile,
        "output=s" => \$outputFile
        );


if($help) {
     print $DESCRIPTION;
     exit 0;
}

if ($n_args == 0) {
    while (my $line = <STDIN>) {
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
    print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
    exit 1;
}



if ($n_args == 1) {
        
    if($featureString) {
        my @featureList;

        eval {
            @featureList = split(',', $featureString);
	};

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $tree_alignment;
        eval {
            ($tree_alignment) = $treeClient->get_alignment_ids_by_feature(\@featureList);
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $i;
        for($i = 0; $i < @$tree_alignment; $i++) {
            print $$tree_alignment[$i];
            print "\t";
        }
        exit 0;
    } 
    elsif($sequenceString) {
        my @sequenceList;
        
        eval {
            @sequenceList = split(',', $sequenceString);
	};

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $tree_alignment;
        eval {
            ($tree_alignment) = $treeClient->get_alignment_ids_by_protein_sequence(\@sequenceList);
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        my $i;
        for($i = 0; $i < @$tree_alignment; $i++) {
            print $$tree_alignment[$i];
            print "\t";
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

        my $tree_alignment;
        my $inputString = $stdinString;

        eval {
            while (my $line = <$inputFileHandle>) {
                $inputString = $inputString.$line;
            }
            close $inputFileHandle;
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        eval {
            my @inputList = split('\t', $inputString);

            if (index($inputString, "kb|g.") != -1) {
                ($tree_alignment) = $treeClient->get_alignment_ids_by_feature(\@inputList);
            }
            else {
                ($tree_alignment) = $treeClient->get_alignment_ids_by_protein_sequence(\@inputList);
            }
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }
        
        my $i;
        for($i = 0; $i < @$tree_alignment; $i++) {
            print $$tree_alignment[$i];
            print "\t";
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

    my $tree_alignment;
    my $inputString = "";

    eval {
        while (my $line = <$inputFileHandle>) {
            $inputString = $inputString.$line;
        }
        close $inputFileHandle;
    };

    $client_error = $@;
    if ($client_error) {
        print Dumper($client_error);
        exit 1;
    }

    eval {
        my @inputList = split('\t', $inputString);

        if (index($inputString, "kb|g.") != -1) {
            ($tree_alignment) = $treeClient->get_alignment_ids_by_feature(\@inputList);
        }
        else {
            ($tree_alignment) = $treeClient->get_alignment_ids_by_protein_sequence(\@inputList);
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


    my $i;
    for($i = 0; $i < @$tree_alignment; $i++) {
        print $outputFileHandle $$tree_alignment[$i];
        print $outputFileHandle "\t";
    }
    close($outputFileHandle);
    exit 0;
}
else {
    print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
    exit 1;
}

