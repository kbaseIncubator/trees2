#!/usr/bin/perl
# This script illustrates the usage of some of the basic features of the Tree Services API
#
use strict;
use warnings;

use Data::Dumper;


my $str = 'adf(as:,,df)a  sdf';

        $str =~ s/[\s]/_/g;
        $str =~ s/[,:\(\)]//g;
        print $str."\n";

# EX. 1
use Bio::KBase::CDMI::CDMIClient;
my $cdmi = Bio::KBase::CDMI::CDMIClient->new_for_script();
my $tree_data = $cdmi->get_entity_Tree(["kb|tree.41"], ["status", "method", "parameters", "newick"]);
#print Dumper($tree_data);


#my $md5H = $cdmi->fids_to_proteins(["kb|g.371.peg.4539"]);
#my $md5H = $cdmi->fids_to_proteins(["kb|g.371.peg.3636"]);
#my $md5H = $cdmi->proteins_to_fids(['cf3c6b5c1f4f24f69866333d4daeb3c8']);
#print Dumper($md5H);

# EX. 2
use Bio::KBase::Tree::Client;
my $TreeService = Bio::KBase::Tree::Client->new("http://localhost:7047");
my $tree_ids = $TreeService->get_tree_ids_by_protein_sequence(['582562ea961a7644b3a8aa8e4f3fafce']);
#my $tree_ids=$TreeService->get_tree_ids_by_protein_sequence(['cf3c6b5c1f4f24f69866333d4daeb3c8']);
print Dumper($tree_ids);

# EX 3
$tree_ids = $TreeService->get_tree_ids_by_feature(["kb|g.371.peg.4539"]);
#$tree_ids=$TreeService->get_tree_ids_by_feature(["kb|g.2931.peg.3636"]);
print Dumper($tree_ids);


# EX 4
$tree_data = $TreeService->get_tree_data($tree_ids);
print Dumper($tree_data);

my $alignment_data = $TreeService->get_alignment_data([$tree_data->{'kb|tree.15024'}->{'alignment_id'}]);
#my $alignment_data = $TreeService->get_alignment_data([$tree_data->{'kb|tree.249'}->{'alignment_id'}]);
print Dumper($alignment_data);


# EX 5
my $tree_id = 'kb|tree.15024';
#my $tree_id = 'kb|tree.41';
my $options = {
               newick_label=>"protein_sequence_id",
               newick_bootstrap=>"none",
               newick_distance=>"raw"};
my $tree = $TreeService->get_tree($tree_id, $options);

# EX 6
my $node_count = $TreeService->get_node_count($tree);
my $leaf_count = $TreeService->get_leaf_count($tree);
print "Node Count: ".$node_count."\nLeaf Count: ".$leaf_count."\n";

# EX 7
my $leaf_labels = $TreeService->extract_leaf_node_names($tree);
my $fid_map = $cdmi->proteins_to_fids($leaf_labels);
my @list_of_first_features = ();
foreach my $leaf ( keys %{$fid_map} ) {
    # arbitrarily get the first feature id and map it to a role
    push @list_of_first_features, %{$fid_map}->{$leaf}[0];
}
print "working\n";
# save the result in a hash
my $fid_to_role_map = $cdmi->fids_to_roles(\@list_of_first_features);
print Dumper($fid_to_role_map);
print "working\n";
# replace the fid list for a single role
foreach my $leaf ( keys %{$fid_map} ) {
    my $fid = %{$fid_map}->{$leaf}[0];
    my $role = $fid_to_role_map->{$fid}[0];
    if($role) {
        $role =~ s/[\s]/_/g;
        $role =~ s/[,:\(\)\[\]]//g;
        print $role."\n";
        $fid_map->{$leaf} = $fid.'-----'.$role; }
    else { $fid_map->{$leaf} = $fid.'----NONE_FOUND'; }
}

print Dumper($fid_map);


my $updated_tree = $TreeService->replace_node_names($tree, $fid_map);




my $display_options = {};
my $tree_in_html = $TreeService->draw_html_tree($updated_tree,$display_options);
open (TREE_HTML_FILE, '>tree.html');
print TREE_HTML_FILE $tree_in_html;
close (TREE_HTML_FILE);



#print Dumper($tree);


