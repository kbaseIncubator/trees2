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
      tree-draw -- 

SYNOPSIS
      tree-draw [OPTIONS] -t TREE

DESCRIPTION

      -t, --tree        
                        a tree structure in newick format
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        
EXAMPLES

      Draw a simple tree
      > tree-draw -t '(l1,((l2,l3)n2,(l4,l5)n3)n1)root;'

SEE ALSO
      tree-
      
AUTHORS

      
";


my $n_args = $#ARGV + 1;

my $help = '';
my $treeString = "";
my $inputFile = "";
my $outputFile = "";

my $opt = GetOptions (
        "help" => \$help,
        "tree=s" => \$treeString,
        "input=s" => \$inputFile,
        "output=s" => \$outputFile
        );


if($help) {
     print $DESCRIPTION;
     exit 0;
}


if($n_args==0) {
    print "FAILURE - no input specified.  Run with --help for usage.\n";
    exit 1;
} 



#create client
my $treeClient;

eval{ $treeClient = get_tree_client(); };

my $client_error = $@;
if ($client_error) { print Dumper($client_error);  exit 1;}

if (!$treeClient) {
    print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
    exit 1;
}

if ($n_args == 1) {        

    if($treeString) {
        my $tree_html;
        eval {
            ($tree_html) = $treeClient->draw_html_tree($treeString, {});
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        print $tree_html;
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

        my $tree_html;
        my $treeString = "";
    
        eval {
            while (my $line = <$inputFileHandle>) {
                $treeString = $treeString.$line;
            }
            close $inputFileHandle;
            ($tree_html) = $treeClient->draw_html_tree($treeString, {});
        };

        $client_error = $@;
        if ($client_error) {
            print Dumper($client_error);
            exit 1;
        }

        print $tree_html;
        exit 0;
    }
}
elsif($n_args == 2) {
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
        ($tree_html) = $treeClient->draw_html_tree($treeString, {});
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

    print $outputFileHandle $tree_html;
    close($outputFileHandle);
    exit 0;
}
else {
    print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
    exit 1;
}

