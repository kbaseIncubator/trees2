package TreesImpl;
use strict;

=head1 NAME

Trees

=head1 DESCRIPTION

Tree and Multiple Sequence Alignment(MSA) API

Full documentation and API reference will be added here.  What follows is the current
version of the tree/MSA API proposal.

created 5/21/2012 - msneddon

=cut

#BEGIN_HEADER
#END_HEADER

sub new
{
    my($class) = @_;
    my $self = {
    };
    #BEGIN_CONSTRUCTOR
    #END_CONSTRUCTOR

    bless $self, $class;
    if ($self->can('_init_instance'))
    {
	$self->_init_instance();
    }
    return $self;
}

=head1 METHODS



=head2 get_tree

  $return = $obj->get_tree($tree_id)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_id is a kbase_id
$return is a newick_tree
kbase_id is a string
newick_tree is a string

</pre>

=end html

=begin text

$tree_id is a kbase_id
$return is a newick_tree
kbase_id is a string
newick_tree is a string


=end text



=item Description

Returns the specified tree in newick format, or an empty string if the tree does not exist.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code
todo: provide a way to automatically replace alignment row ids with kbase ids
todo: provide a way to get meta data about this tree, possibly in a separate function

=back

=cut

sub get_tree
{
    my($self, $ctx, $tree_id) = @_;
    my($return);
    #BEGIN get_tree
        #END get_tree
    return($return);
}




=head2 get_trees

  $return = $obj->get_trees($tree_ids)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a string

</pre>

=end html

=begin text

$tree_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a string


=end text



=item Description

Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
was not found.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=back

=cut

sub get_trees
{
    my($self, $ctx, $tree_ids) = @_;
    my($return);
    #BEGIN get_trees
        #END get_trees
    return($return);
}




=head2 all_tree_ids

  $return = $obj->all_tree_ids($isActive)

=over 4

=item Parameter and return types

=begin html

<pre>
$isActive is a bool
$return is a reference to a list where each element is a kbase_id
bool is an int
kbase_id is a string

</pre>

=end html

=begin text

$isActive is a bool
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
    my($self, $ctx, $isActive) = @_;
    my($return);
    #BEGIN all_tree_ids
        #END all_tree_ids
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
    my($self, $ctx, $sequence, $beg, $end, $options) = @_;
    my($return);
    #BEGIN get_trees_with_entire_seq
        #END get_trees_with_entire_seq
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
    my($self, $ctx, $sequence, $beg, $end, $options) = @_;
    my($return);
    #BEGIN get_trees_with_overlapping_seq
        #END get_trees_with_overlapping_seq
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
    my($self, $ctx, $domain, $options) = @_;
    my($return);
    #BEGIN get_trees_with_entire_domain
        #END get_trees_with_entire_domain
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
    my($self, $ctx, $domain, $options) = @_;
    my($return);
    #BEGIN get_trees_with_overlapping_domain
        #END get_trees_with_overlapping_domain
    return($return);
}




=head2 substitute_node_labels_with_kbase_ids

  $return = $obj->substitute_node_labels_with_kbase_ids($trees, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$trees is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a string

</pre>

=end html

=begin text

$trees is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a newick_tree
kbase_id is a string
newick_tree is a string


=end text



=item Description

Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
parameters to be passed (parameter list is also currently not defined yet.)

=back

=cut

sub substitute_node_labels_with_kbase_ids
{
    my($self, $ctx, $trees, $options) = @_;
    my($return);
    #BEGIN substitute_node_labels_with_kbase_ids
        #END substitute_node_labels_with_kbase_ids
    return($return);
}




=head2 extract_leaf_node_labels

  $return = $obj->extract_leaf_node_labels($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a reference to a list where each element is a string
newick_tree is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a reference to a list where each element is a string
newick_tree is a string


=end text



=item Description

Given a tree, returns the list of labels of the leaves.  If the 'substitute_node_labels_with_kbase_ids' was already
called to retrieve the trees, then this method will provide a list of kbase_ids indicating the sequences that comprised
the tree.

=back

=cut

sub extract_leaf_node_labels
{
    my($self, $ctx, $tree) = @_;
    my($return);
    #BEGIN extract_leaf_node_labels
        #END extract_leaf_node_labels
    return($return);
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

A KBase ID is string starting with the characters "kb|".  KBase IDs are typed. The types are
designated using a short string. For instance," g" denotes a genome. KBase IDs may be hierarchical.
If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented
as "kb|g.1234.fp.771". See the standard KBase documentation for more info.


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

Trees are represented in newick format (http://en.wikipedia.org/wiki/Newick_format) and are
returned to you in this format by default.  All leaf nodes are by default indexed to a MSA row
element.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids
instead, depending on how the tree was built.  Internal nodes may or may not be labeled.  Nodes may
also be annotated with structured data


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

String representation of an alignment, the precise syntax of which is not yet specified but will
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

Meta data about a tree, such as when it was created, parameters used in its creation, etc
Is this actually needed? Or will this info be accessible through the auto generated methods?


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
tree_id has a value which is a kbase_id
alignment_id has a value which is a kbase_id
isActive has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
tree_id has a value which is a kbase_id
alignment_id has a value which is a kbase_id
isActive has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string


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
isActive has a value which is a bool
date_created has a value which is a timestamp
n_rows has a value which is an int
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
alignment_id has a value which is a kbase_id
isActive has a value which is a bool
date_created has a value which is a timestamp
n_rows has a value which is an int
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string


=end text

=back



=cut

1;
