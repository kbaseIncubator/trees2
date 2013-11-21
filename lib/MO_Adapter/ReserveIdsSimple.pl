# This script uses the ID server to reserve IDs for trees and alignments
#
# It stores those ids into a file you specify in the format
# kb|tree.xx kb|aln.xx
#
#
#
use strict;
use warnings;
use Data::Dumper;
use Benchmark;


my $outputIdPath = "reservedIdList.txt";

my $n_ids = 5; #40594;


# connect to the ID Server
use Bio::KBase::IDServer::Client;
my $id_server_url = "http://kbase.us/services/idserver";
my $idserver = Bio::KBase::IDServer::Client->new($id_server_url);



my $kbase_tree_id_prefix="kb|tree";
my $kbase_alignment_id_prefix="kb|aln";

my $starting_value_TREE = 3; #$idserver->allocate_id_range($kbase_tree_id_prefix, $n_ids);
my $starting_value_ALN = 3; #$idserver->allocate_id_range($kbase_alignment_id_prefix, $n_ids);

print "tree start: ".$starting_value_TREE."\n";
print "aln start:  ".$starting_value_ALN."\n";

# open up a stream to the input public loci file and output location
my $OUT; open ($OUT, ">".$outputIdPath);
for my $i (0 .. $n_ids-1)
{
    my $t = $starting_value_TREE + $i;
    my $a = $starting_value_ALN + $i;
    print $OUT $kbase_tree_id_prefix.'.'.$t." ".$kbase_alignment_id_prefix.'.'.$a."\n";
}
close $OUT;
