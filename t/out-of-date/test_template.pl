#!/usr/bin/perl
use strict;
use warnings;

my $out = "trees_template.t";

open (OUT,">$out") || die "Did not create $out";

print OUT "
#!/usr/bin/perl
use strict;
use warnings;

use Test::More tests => 3;
use Data::Dumper;
use Test::More;
use lib \"../lib/\"; ";

=head1 NAME

Testing template

=head1 DESCRIPTION

Template for the repetitive portions of testing that can be determined from the type spec 

=cut

my $tree_service_url = "http://localhost:7047";
my $module = 'TreesClient';
my @methods = qw [get_tree
 get_trees
 all_tree_ids
 get_kbase_ids_from_alignment_row
 get_trees_with_entire_seq
 get_trees_with_overlapping_seq
 get_trees_with_entire_domain
 get_trees_with_overlapping_domain
 substitute_node_names_with_kbase_ids
 extract_leaf_node_names
 extract_node_names
 get_node_count
 get_leaf_count
 replace_node_names
 remove_node_names_and_simplify
 add_node_to_tree
 build_tree_from_sequences
 build_tree_from_fasta ];

print OUT "use $module\n";

print OUT "my \$client;
\$client = TreesClient->new($tree_service_url);
ok(defined(\$client),\"Did an object get defined with no URL?\");

isa_ok( \$client, $module, \"Is it in the right class\" );   

can_ok(\$client, @methods)\";  \n\n";

my $int = 5;
my $string = 'This is the test string';
my $bool  = 0;
my @list = qw[1];

&params('get_tree',$string,@list);
sub params
{
	my ($name,@parms) = @_;

	print OUT "#  TEST method $name\n#  \$result = $name(@parms)\n#\n";
	print OUT "note(\"Testing $name.  First with too few and too many parameters\"); \n";

	my $num_parms = $#parms;

	unless ($num_parms == 0 )
	{
		&no_parms($name);
	}
	
	
}
sub no_parms
{
	my $name = shift;
	print OUT " note(\"Testing $name.  NO parameters\");\n";
	print OUT " eval { \$result  = \$client->$name(); };\n";
	print OUT " isnt(\$\@, undef, \"Call with no parameters failed properly \$\@\"); \n";

}
sub too_many
{

}
