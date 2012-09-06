package Bio::KBase::Tree::TreeImpl;
use strict;
use Bio::KBase::Exceptions;
# Use Semantic Versioning (2.0.0-rc.1)
# http://semver.org 
our $VERSION = "0.1.0";

=head1 NAME

Tree

=head1 DESCRIPTION

KBase Phylogenetic Tree and Multiple Sequence Alignment(MSA) API

Full documentation and API reference will be added here.

created 5/21/2012 - msneddon
last updated 9/4/12

=cut

#BEGIN_HEADER
#END_HEADER

sub new
{
    my($class, @args) = @_;
    my $self = {
    };
    bless $self, $class;
    #BEGIN_CONSTRUCTOR
    #END_CONSTRUCTOR

    if ($self->can('_init_instance'))
    {
	$self->_init_instance();
    }
    return $self;
}

=head1 METHODS



=head2 get_tree

  $return = $obj->get_tree($tree_id, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$tree_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Returns the specified tree in newick format, or an empty string if the tree does not exist.  Options
hash provides a way to return the tree with different labels replaced.

    options = [
        FORMAT => 'raw' || 'first' || 'all' || 'species_name' ...
    ]
 
The FORMAT option selects how node labels are replaced in the output tree.  'raw' returns just the
tree with labels into the AlignmentRowComponent table. 'first' returns the tree with labels replaced
with only a single kbase_id to a sequence (if there are multiple sequences in the alignment row, then
only the first is returned).  'all' returns the tree with all the kbase_ids that make up the alignment
row in a comma-delimited format.  If there is only one sequence in the alignment, then the behavior
is the same as 'first'.  'species_name' replaces labels with the name of the organism, if available.
other options???

Note: the options hash will be the same as for other functions which provide substitution cababilities 

todo: provide a way to get meta data about this tree, possibly in a separate function, but may
not be needed if this is provided by the ER model.

=back

=cut

sub get_tree
{
    my $self = shift;
    my($tree_id, $options) = @_;

    my @_bad_arguments;
    (!ref($tree_id)) or push(@_bad_arguments, "Invalid type for argument \"tree_id\" (value was \"$tree_id\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_tree
    $return = "hello mr. tree., sent from get_tree";
    #END get_tree
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_tree:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree');
    }
    return($return);
}




=head2 get_trees

  $return = $obj->get_trees($tree_ids, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$tree_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
was not found.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=back

=cut

sub get_trees
{
    my $self = shift;
    my($tree_ids, $options) = @_;

    my @_bad_arguments;
    (ref($tree_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"tree_ids\" (value was \"$tree_ids\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_trees:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_trees
    #END get_trees
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_trees:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees');
    }
    return($return);
}




=head2 all_tree_ids

  $return = $obj->all_tree_ids($is_active)

=over 4

=item Parameter and return types

=begin html

<pre>
$is_active is a bool
$return is a reference to a list where each element is a kbase_id
bool is an int
kbase_id is a string

</pre>

=end html

=begin text

$is_active is a bool
$return is a reference to a list where each element is a kbase_id
bool is an int
kbase_id is a string


=end text



=item Description

Returns a list of all IDs of all trees in the database that match the given flags (right now
the only flag indicates if the tree is active or not, meaning the latest version of the tree,
but this should be extended to accept more args and possible queries.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=back

=cut

sub all_tree_ids
{
    my $self = shift;
    my($is_active) = @_;

    my @_bad_arguments;
    (!ref($is_active)) or push(@_bad_arguments, "Invalid type for argument \"is_active\" (value was \"$is_active\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to all_tree_ids:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'all_tree_ids');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN all_tree_ids
    #END all_tree_ids
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to all_tree_ids:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'all_tree_ids');
    }
    return($return);
}




=head2 get_kbase_ids_from_alignment_row

  $return = $obj->get_kbase_ids_from_alignment_row($alignment_id, $row_number)

=over 4

=item Parameter and return types

=begin html

<pre>
$alignment_id is a kbase_id
$row_number is an int
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$alignment_id is a kbase_id
$row_number is an int
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Given an alignment and a row in the alignment, returns all the kbase_ids of the sequences that compose
the given tree.
Note: may not be needed if this functionality is possible via the ER model

=back

=cut

sub get_kbase_ids_from_alignment_row
{
    my $self = shift;
    my($alignment_id, $row_number) = @_;

    my @_bad_arguments;
    (!ref($alignment_id)) or push(@_bad_arguments, "Invalid type for argument \"alignment_id\" (value was \"$alignment_id\")");
    (!ref($row_number)) or push(@_bad_arguments, "Invalid type for argument \"row_number\" (value was \"$row_number\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_kbase_ids_from_alignment_row:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_kbase_ids_from_alignment_row');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_kbase_ids_from_alignment_row
    #END get_kbase_ids_from_alignment_row
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_kbase_ids_from_alignment_row:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_kbase_ids_from_alignment_row');
    }
    return($return);
}




=head2 get_trees_with_entire_seq

  $return = $obj->get_trees_with_entire_seq($sequence, $beg, $end, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequence is a kbase_id
$beg is a position
$end is a position
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int

</pre>

=end html

=begin text

$sequence is a kbase_id
$beg is a position
$end is a position
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int


=end text



=item Description

Returns all tree IDs in which the entire portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.
todo: should beg/end just be included in some options hash?
todo: define contents of options hash, which will allow more complex queries, such as returning
      only active trees, or trees of a particuar hieght, etc...

=back

=cut

sub get_trees_with_entire_seq
{
    my $self = shift;
    my($sequence, $beg, $end, $options) = @_;

    my @_bad_arguments;
    (!ref($sequence)) or push(@_bad_arguments, "Invalid type for argument \"sequence\" (value was \"$sequence\")");
    (!ref($beg)) or push(@_bad_arguments, "Invalid type for argument \"beg\" (value was \"$beg\")");
    (!ref($end)) or push(@_bad_arguments, "Invalid type for argument \"end\" (value was \"$end\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_trees_with_entire_seq:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_entire_seq');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_trees_with_entire_seq
    #END get_trees_with_entire_seq
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_trees_with_entire_seq:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_entire_seq');
    }
    return($return);
}




=head2 get_trees_with_overlapping_seq

  $return = $obj->get_trees_with_overlapping_seq($sequence, $beg, $end, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequence is a kbase_id
$beg is a position
$end is a position
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int

</pre>

=end html

=begin text

$sequence is a kbase_id
$beg is a position
$end is a position
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int


=end text



=item Description

Returns all tree IDs in which some portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=back

=cut

sub get_trees_with_overlapping_seq
{
    my $self = shift;
    my($sequence, $beg, $end, $options) = @_;

    my @_bad_arguments;
    (!ref($sequence)) or push(@_bad_arguments, "Invalid type for argument \"sequence\" (value was \"$sequence\")");
    (!ref($beg)) or push(@_bad_arguments, "Invalid type for argument \"beg\" (value was \"$beg\")");
    (!ref($end)) or push(@_bad_arguments, "Invalid type for argument \"end\" (value was \"$end\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_trees_with_overlapping_seq:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_overlapping_seq');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_trees_with_overlapping_seq
    #END get_trees_with_overlapping_seq
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_trees_with_overlapping_seq:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_overlapping_seq');
    }
    return($return);
}




=head2 get_trees_with_entire_domain

  $return = $obj->get_trees_with_entire_domain($domain, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$domain is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$domain is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Returns all tree IDs in which the entire portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on this domain).

=back

=cut

sub get_trees_with_entire_domain
{
    my $self = shift;
    my($domain, $options) = @_;

    my @_bad_arguments;
    (!ref($domain)) or push(@_bad_arguments, "Invalid type for argument \"domain\" (value was \"$domain\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_trees_with_entire_domain:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_entire_domain');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_trees_with_entire_domain
    #END get_trees_with_entire_domain
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_trees_with_entire_domain:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_entire_domain');
    }
    return($return);
}




=head2 get_trees_with_overlapping_domain

  $return = $obj->get_trees_with_overlapping_domain($domain, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$domain is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$domain is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Returns all tree IDs in which some portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on a similar domain created
with an alternative method, so the entire domain may not be contained).

=back

=cut

sub get_trees_with_overlapping_domain
{
    my $self = shift;
    my($domain, $options) = @_;

    my @_bad_arguments;
    (!ref($domain)) or push(@_bad_arguments, "Invalid type for argument \"domain\" (value was \"$domain\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_trees_with_overlapping_domain:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_overlapping_domain');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_trees_with_overlapping_domain
    #END get_trees_with_overlapping_domain
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_trees_with_overlapping_domain:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_trees_with_overlapping_domain');
    }
    return($return);
}




=head2 substitute_node_names_with_kbase_ids

  $return = $obj->substitute_node_names_with_kbase_ids($trees, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$trees is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$trees is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
parameters to be passed (parameter list is also currently not defined yet and is currently ignored.)

=back

=cut

sub substitute_node_names_with_kbase_ids
{
    my $self = shift;
    my($trees, $options) = @_;

    my @_bad_arguments;
    (ref($trees) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"trees\" (value was \"$trees\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to substitute_node_names_with_kbase_ids:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'substitute_node_names_with_kbase_ids');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN substitute_node_names_with_kbase_ids
    #END substitute_node_names_with_kbase_ids
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to substitute_node_names_with_kbase_ids:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'substitute_node_names_with_kbase_ids');
    }
    return($return);
}




=head2 extract_leaf_node_names

  $return = $obj->extract_leaf_node_names($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a reference to a list where each element is a node_name
newick_tree is a tree
tree is a string
node_name is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a reference to a list where each element is a node_name
newick_tree is a tree
tree is a string
node_name is a string


=end text



=item Description

Given a tree, returns the list of names of the leaves.  If the 'substitute_node_names_with_kbase_ids' was already called
to retrieve the trees, then this method will provide a list of kbase_ids indicating the sequences that comprised the tree.

=back

=cut

sub extract_leaf_node_names
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to extract_leaf_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'extract_leaf_node_names');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN extract_leaf_node_names
    #END extract_leaf_node_names
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to extract_leaf_node_names:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'extract_leaf_node_names');
    }
    return($return);
}




=head2 extract_node_names

  $return = $obj->extract_node_names($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a reference to a list where each element is a node_name
newick_tree is a tree
tree is a string
node_name is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a reference to a list where each element is a node_name
newick_tree is a tree
tree is a string
node_name is a string


=end text



=item Description

Given a tree, returns the list of names of all then nodes (note: for some trees, such as default MO trees, names of internal
nodes are interpreted as bootstrap values, but are still returned here).  If the 'substitute_node_names_with_kbase_ids' was already called
to retrieve the trees, then this method will provide a list of kbase_ids indicating the sequences that comprised the tree.

=back

=cut

sub extract_node_names
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to extract_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'extract_node_names');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN extract_node_names
    #END extract_node_names
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to extract_node_names:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'extract_node_names');
    }
    return($return);
}




=head2 get_node_count

  $return = $obj->get_node_count($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is an int
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$return is an int
newick_tree is a tree
tree is a string


=end text



=item Description

Given a tree, returns the total number of nodes, including internal nodes

=back

=cut

sub get_node_count
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_node_count:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_node_count');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_node_count
    #END get_node_count
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_node_count:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_node_count');
    }
    return($return);
}




=head2 get_leaf_count

  $return = $obj->get_leaf_count($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is an int
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$return is an int
newick_tree is a tree
tree is a string


=end text



=item Description

Given a tree, returns the total number of leaf nodes, (internal and root nodes are ignored)

=back

=cut

sub get_leaf_count
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_leaf_count:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_leaf_count');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_leaf_count
    #END get_leaf_count
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_leaf_count:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_leaf_count');
    }
    return($return);
}




=head2 replace_node_names

  $return = $obj->replace_node_names($tree, $replacements)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$replacements is a reference to a hash where the key is a node_name and the value is a node_name
$return is a newick_tree
newick_tree is a tree
tree is a string
node_name is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$replacements is a reference to a hash where the key is a node_name and the value is a node_name
$return is a newick_tree
newick_tree is a tree
tree is a string
node_name is a string


=end text



=item Description

Given a tree, replace the node names indicated as keys in the input map, and replace them with the values contained in the map.

=back

=cut

sub replace_node_names
{
    my $self = shift;
    my($tree, $replacements) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    (ref($replacements) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"replacements\" (value was \"$replacements\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to replace_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'replace_node_names');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN replace_node_names
    #END replace_node_names
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to replace_node_names:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'replace_node_names');
    }
    return($return);
}




=head2 remove_node_names_and_simplify

  $return = $obj->remove_node_names_and_simplify($tree, $removal_list)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$removal_list is a reference to a list where each element is a node_name
$return is a newick_tree
newick_tree is a tree
tree is a string
node_name is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$removal_list is a reference to a list where each element is a node_name
$return is a newick_tree
newick_tree is a tree
tree is a string
node_name is a string


=end text



=item Description

Given a tree, remove the node names indicated in the list, and simplify the tree.  Simplifying a tree involves removing
unnamed internal nodes that have only one child, and removing unnamed leaf nodes.  During the removal process, edge lengths
(if they exist) are conserved so that the end to end distance between any two nodes left in the tree will remain the same.

=back

=cut

sub remove_node_names_and_simplify
{
    my $self = shift;
    my($tree, $removal_list) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    (ref($removal_list) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"removal_list\" (value was \"$removal_list\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to remove_node_names_and_simplify:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'remove_node_names_and_simplify');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN remove_node_names_and_simplify
    #END remove_node_names_and_simplify
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to remove_node_names_and_simplify:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'remove_node_names_and_simplify');
    }
    return($return);
}




=head2 add_node_to_tree

  $return = $obj->add_node_to_tree($tree_id, $sequence_id, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_id is a kbase_id
$sequence_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$tree_id is a kbase_id
$sequence_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Given a tree and a sequence in kbase, attempt to map that sequence onto the tree, returning the newick representation
of the tree.

=back

=cut

sub add_node_to_tree
{
    my $self = shift;
    my($tree_id, $sequence_id, $options) = @_;

    my @_bad_arguments;
    (!ref($tree_id)) or push(@_bad_arguments, "Invalid type for argument \"tree_id\" (value was \"$tree_id\")");
    (!ref($sequence_id)) or push(@_bad_arguments, "Invalid type for argument \"sequence_id\" (value was \"$sequence_id\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to add_node_to_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'add_node_to_tree');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN add_node_to_tree
    #END add_node_to_tree
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to add_node_to_tree:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'add_node_to_tree');
    }
    return($return);
}




=head2 build_tree_from_sequences

  $return = $obj->build_tree_from_sequences($sequences, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequences is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$sequences is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
kbase_id is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Not sure if we want this, but would allow users to submit a set of sequences, then build a tree.  Here, options
would support the various alignment options, trimming options, tree-building algorithms.
todo: this isn't well thought out -> do we return alignments as well?  do we support building MSAs?

=back

=cut

sub build_tree_from_sequences
{
    my $self = shift;
    my($sequences, $options) = @_;

    my @_bad_arguments;
    (ref($sequences) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"sequences\" (value was \"$sequences\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to build_tree_from_sequences:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'build_tree_from_sequences');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN build_tree_from_sequences
    #END build_tree_from_sequences
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to build_tree_from_sequences:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'build_tree_from_sequences');
    }
    return($return);
}




=head2 build_tree_from_fasta

  $return = $obj->build_tree_from_fasta($fasta_files, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$fasta_files is a reference to a list where each element is a string
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$fasta_files is a reference to a list where each element is a string
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
newick_tree is a tree
tree is a string


=end text



=item Description



=back

=cut

sub build_tree_from_fasta
{
    my $self = shift;
    my($fasta_files, $options) = @_;

    my @_bad_arguments;
    (ref($fasta_files) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"fasta_files\" (value was \"$fasta_files\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to build_tree_from_fasta:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'build_tree_from_fasta');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN build_tree_from_fasta
    #END build_tree_from_fasta
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to build_tree_from_fasta:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'build_tree_from_fasta');
    }
    return($return);
}




=head2 version 

  $return = $obj->version()

=over 4

=item Parameter and return types

=begin html

<pre>
$return is a string
</pre>

=end html

=begin text

$return is a string

=end text

=item Description

Return the module version. This is a Semantic Versioning number.

=back

=cut

sub version {
    return $VERSION;
}

=head1 TYPES



=head2 bool

=over 4



=item Definition

=begin html

<pre>
an int
</pre>

=end html

=begin text

an int

=end text

=back



=head2 timestamp

=over 4



=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 position

=over 4



=item Definition

=begin html

<pre>
an int
</pre>

=end html

=begin text

an int

=end text

=back



=head2 kbase_id

=over 4



=item Description

A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are
designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and
"aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome
identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".
See the standard KBase documentation for more information.


=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 tree

=over 4



=item Description

A string representation of a phylogenetic tree.  The format/syntax of the string can be
specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
'phyloXML_tree' or 'json_tree'.  Regardless of format, all leaf nodes in trees built from MSAs are indexed
to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with
other KBase Ids instead, depending on how the tree was built.  Internal nodes may or may not be named.
Nodes, depending on the format, may also be annotated with structured data such as bootstrap values.


=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 newick_tree

=over 4



=item Description

Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format)
and are returned to you in this format by default.


=item Definition

=begin html

<pre>
a tree
</pre>

=end html

=begin text

a tree

=end text

=back



=head2 phyloXML_tree

=over 4



=item Description

Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
but can optionally be converted to the more verbose phyloXML format, which is useful for compatibility or
when additional information/annotations decorate the tree.


=item Definition

=begin html

<pre>
a tree
</pre>

=end html

=begin text

a tree

=end text

=back



=head2 json_tree

=over 4



=item Description

Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
but can optionally be converted to JSON format where the structure of the tree matches the structure of
the JSON object.  This is useful when interacting with the tree in JavaScript, for instance.


=item Definition

=begin html

<pre>
a tree
</pre>

=end html

=begin text

a tree

=end text

=back



=head2 node_name

=over 4



=item Description

The string representation of the parsed node name (may be a kbase_id, but does not have to).  Note, this
is not the full label in a newick_tree (which may include comments or distances)


=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 fasta_alignment

=over 4



=item Description

String representation of an alignment, the precise syntax and alphabet of which is not yet specified but will
likely be similar to alignments stored in SEED.


=item Definition

=begin html

<pre>
a string
</pre>

=end html

=begin text

a string

=end text

=back



=head2 tree_meta_data

=over 4



=item Description

some comment here


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
tree_id has a value which is a kbase_id
alignment_id has a value which is a kbase_id
meta_info_hash has a value which is a string
is_active has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string
source_db has a value which is a string
source_db_id has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
tree_id has a value which is a kbase_id
alignment_id has a value which is a kbase_id
meta_info_hash has a value which is a string
is_active has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string
source_db has a value which is a string
source_db_id has a value which is a string


=end text

=back



=head2 alignment_meta_data

=over 4



=item Description

Meta data about an alignment, such as when it was created, parameters used in its creation, etc.
Todo: determine if this object is necessary, and determine what it should contain


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
alignment_id has a value which is a kbase_id
meta_info_hash has a value which is a string
is_active has a value which is a bool
is_concatenation has a value which is a bool
date_created has a value which is a timestamp
n_rows has a value which is an int
alignment_method has a value which is an int
alignment_parameters has a value which is a string
alignment_protocol_description has a value which is a string
source_db has a value which is a string
source_db_id has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
alignment_id has a value which is a kbase_id
meta_info_hash has a value which is a string
is_active has a value which is a bool
is_concatenation has a value which is a bool
date_created has a value which is a timestamp
n_rows has a value which is an int
alignment_method has a value which is an int
alignment_parameters has a value which is a string
alignment_protocol_description has a value which is a string
source_db has a value which is a string
source_db_id has a value which is a string


=end text

=back



=cut

1;
