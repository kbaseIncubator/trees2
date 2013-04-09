#!/usr/bin/env perl
use strict;
use warnings;
use POSIX;
use Getopt::Long;
use Data::Dumper;

use Bio::KBase::Tree::Client;
use Bio::KBase::Tree::Util qw(get_tree_client);

my $DESCRIPTION =
"
NAME
      tree-normalize-abundance-profile -- normalize and filter metagenomic sample abundance data

SYNOPSIS
      tree-normalize-abundance-profile [OPTIONS]

DESCRIPTION
      Normalize metagenomic abundance read counts by sum, mean, min, or max of a column or globally,
      and optionally post-process the normalized counts with a 

      The expected input is a tab-delimited string with tree node labels in the first column
      and abundance counts in the second column.

      The -g, -t, -n options can all be supplied in a single call, or can be acheived using multiple script calls.
      If you supply multiple options (-g,-t,-n), the order they will be applied is minimum thresholding, 
      normalization, and then grouping.

      -g, --groups
                        specify a number of evenly spaced groups to divide the data into,
                        which will bin the data into groups of size (max - min + 1)/number_of_groups,
                        where data is in a group if (data >= group_min and data < next_group_min)
      -t, --threshold
                        specify a global minimum threshold to filter the input counts with,
                        which will remove all nodes with counts strictly less than the threshold
      -n, --normalize
                        normalize the data by a constant factor (i.e. divide by some factor)
      -i, --input                                                                                           
                        specify an input file to read from; if provided, any other arguments
                        and standard-in are ignored
      -h, --help
                        diplay this help message, ignore all arguments
                                                
EXAMPLES
    
AUTHORS
      Michael Sneddon (mwsneddon\@lbl.gov)
      
";

# first, get options and set up the parameters to the call
my $help = '';
my $inputFile = '';

my $cutoff_value='';
my $cutoff_number_of_records='';
my $normalization_scope='per_column';
my $normalization_type='none';
my $normalization_post_process='none';

my $opt = GetOptions (
        "help" => \$help,
        "input|i=s" => \$inputFile,
        "cutoff-value|v=s" => \$cutoff_value,
        "cutoff-num-records|r=s" => \$cutoff_number_of_records,
        "normalization-scope|s=s" => \$normalization_scope,
        "normalization-type|t=s" => \$normalization_type,
        "normalization-post-process|p=s" => \$normalization_post_process
        );
if($help) { print $DESCRIPTION; exit 0; }

my $use_cutoff_value = 0;
if($cutoff_value) { $use_cutoff_value=1; }
else { $cutoff_value = 0; }

my $use_cutoff_number_of_records = 0;
if($cutoff_number_of_records) { $use_cutoff_number_of_records=1; }
else { $cutoff_number_of_records = 0; }

my $params = {
                     cutoff_value  =>  $cutoff_value,
                 use_cutoff_value  =>  $use_cutoff_value,
         cutoff_number_of_records  =>  $cutoff_number_of_records,
     use_cutoff_number_of_records  =>  $use_cutoff_number_of_records,
              normalization_scope  =>  $normalization_scope,
               normalization_type  =>  $normalization_type,
       normalization_post_process  =>  $normalization_post_process
    };



# parse the actual input data, be it from a filename or from standard-in
my $n_args = $#ARGV + 1;
my $data={}; my $labels=[];

# if we have specified an input file, then read the file
if($inputFile) {
     my $inputFileHandle;
     open($inputFileHandle, "<", $inputFile);
     if(!$inputFileHandle) {
          print "FAILURE - cannot open '$inputFile' \n$!\n";
          exit 1;
     }
     eval {
          my $first_line=1;
          while (my $line = <$inputFileHandle>) {
               chomp($line);
               my @tokens = split("\t",$line);
               if($first_line) {
                   shift @tokens;
                   foreach my $label (@tokens) { $data->{$label} = {}; push @$labels, $label; }
                   $first_line = 0;
               } else {
                   for(my $i=0; $i<scalar(@$labels); $i++){
                       $data->{$labels->[$i]}->{$tokens[0]} = $tokens[$i+1];
                   }
               }
          }
          close $inputFileHandle;
     };
}
# if we have no arguments, then read the data from standard-in
elsif($n_args == 0) {
     my $first_line=1;
     while(my $line = <STDIN>) {
          chomp($line);
          my @tokens = split("\t",$line);
          if($first_line) {
              shift @tokens;
              foreach my $label (@tokens) { $data->{$label} = {}; push @$labels, $label; }
              $first_line = 0;
          } else {
              for(my $i=0; $i<scalar(@$labels); $i++) {
                $data->{$labels->[$i]}->{$tokens[0]} = $tokens[$i+1];
              }
          }
     }
}

#print Dumper($data)."\n";

if(scalar keys %$data) {
    
    #create client
    my $treeClient;
    eval{ $treeClient = get_tree_client(); };
    if(!$treeClient) {
        print STDERR "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
        exit 1;
    }
    
    #make the call
    my $result = $treeClient->filter_abundance_profile($data,$params);
    
    #TODO: optimize this
    #output the result to standard out, first by assembling the output matrix
    my $output_matrix = {}; my $label_index=0;
    foreach my $label (sort keys %$result) {
        foreach my $feature (keys %{$result->{$label}}) {
            $output_matrix->{$feature}=[] if(!exists $output_matrix->{$feature});
            $output_matrix->{$feature}->[$label_index] = $result->{$label}->{$feature};
        }
        $label_index++;
    }
    foreach my $value_array (values %$output_matrix) {
        $value_array->[$label_index-1]=undef if(!exists $value_array->[$label_index-1]);
    }
    
    # print the header
    print "#";
    foreach my $label (sort keys %$result) {
        print "\t".$label;
    }
    print "\n";
    
    #print the actual data
    foreach my $feature (sort keys %$output_matrix) {
        print $feature;
        my $values = $output_matrix->{$feature};
        foreach my $v (@$values) {
            print "\t";
            print $v if(defined $v);
        }
        print "\n";
    }
   
    exit 0;
} else {
    print STDERR "FAILURE - unable to parse input file or standard-in.  Check your syntax.\n";
    exit 1;
}


print "Bad options / Invalid number of arguments.  Run with --help for usage.\n";
exit 1;


