# This script uses the ID server to reserve IDs for trees and alignments
#
# It stores those ids into a file named reserved_kbase_id_list.txt in the format
# kb|tree.xx kb|aln.xx
#
# Then, another script should lookup all the trees in MO, assign each one
# to this list, and save that mapping to yet another file (ordered by MO tree IDs).
#
# Next, this list should be registered with the ID server so that we have these
# mappings established.
#
# Any export of MO should then use this file to correctly assign a KBase ID when
# needed.
#
#
use strict;
use warnings;
use Data::Dumper;
use Benchmark;

# input tree names are fetched from file names
my $groupName = "COG";
my $inputTreeDir = "../../../$groupName/rooted";
my $outputIdPath = "../../../$groupName/kbTreeIdMap.txt";

opendir(IN, $inputTreeDir) or die $!;
my @originalIdListUnsorted;
while (my $file = readdir(IN)) {
    next if ($file =~ m/^\./);
    $file =~ s/.tree.rooted$//;
    push(@originalIdListUnsorted,$file);
}
closedir(IN);
my @originalIdList = sort @originalIdListUnsorted;
my $n_ids = scalar @originalIdList;
print Dumper($n_ids);


# connect to the ID Server
use Bio::KBase::IDServer::Client;
my $id_server_url = "http://kbase.us/services/idserver";
my $idserver = Bio::KBase::IDServer::Client->new($id_server_url);


my $kbase_tree_id_prefix="kb|tree";
my $kbase_alignment_id_prefix="kb|aln";

my $starting_value_TREE = 5;# $idserver->allocate_id_range($kbase_tree_id_prefix, $n_ids);
my $starting_value_ALN = 5; # $idserver->allocate_id_range($kbase_alignment_id_prefix, $n_ids);

print "tree start: ".$starting_value_TREE."\n";
print "aln start:  ".$starting_value_ALN."\n";

# open up a stream to the input public loci file and output location
my $OUT; open ($OUT, ">".$outputIdPath);
for my $i (0 .. $n_ids-1)
{
    my $t = $starting_value_TREE + $i;
    my $a = $starting_value_ALN + $i;
    print $OUT $originalIdList[$i]." ".$kbase_tree_id_prefix.'.'.$t." ".$kbase_alignment_id_prefix.'.'.$a."\n";
}
close $OUT;
