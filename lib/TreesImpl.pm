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

  $return = $obj->get_trees_with_entire_seq($seqence)

=over 4

=item Parameter and return types

=begin html

<pre>
$seqence is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$seqence is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Returns all tree IDs in which the entire portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=back

=cut

sub get_trees_with_entire_seq
{
    my($self, $ctx, $seqence) = @_;
    my($return);
    #BEGIN get_trees_with_entire_seq
        #END get_trees_with_entire_seq
    return($return);
}




=head2 get_trees_with_entire_seq

  $return = $obj->get_trees_with_entire_seq($sequence, $beg, $end)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequence is a kbase_id
$beg is a position
$end is a position
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int

</pre>

=end html

=begin text

$sequence is a kbase_id
$beg is a position
$end is a position
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int


=end text



=item Description



=back

=cut

sub get_trees_with_entire_seq
{
    my($self, $ctx, $sequence, $beg, $end) = @_;
    my($return);
    #BEGIN get_trees_with_entire_seq
        #END get_trees_with_entire_seq
    return($return);
}




=head2 get_trees_with_overlapping_seq

  $return = $obj->get_trees_with_overlapping_seq($sequence)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequence is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$sequence is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Returns all tree IDs in which some portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=back

=cut

sub get_trees_with_overlapping_seq
{
    my($self, $ctx, $sequence) = @_;
    my($return);
    #BEGIN get_trees_with_overlapping_seq
        #END get_trees_with_overlapping_seq
    return($return);
}




=head2 get_trees_with_overlapping_seq

  $return = $obj->get_trees_with_overlapping_seq($sequence, $beg, $end)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequence is a kbase_id
$beg is a position
$end is a position
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int

</pre>

=end html

=begin text

$sequence is a kbase_id
$beg is a position
$end is a position
$return is a reference to a list where each element is a kbase_id
kbase_id is a string
position is an int


=end text



=item Description



=back

=cut

sub get_trees_with_overlapping_seq
{
    my($self, $ctx, $sequence, $beg, $end) = @_;
    my($return);
    #BEGIN get_trees_with_overlapping_seq
        #END get_trees_with_overlapping_seq
    return($return);
}




=head2 get_trees_with_entire_domain

  $return = $obj->get_trees_with_entire_domain($domain)

=over 4

=item Parameter and return types

=begin html

<pre>
$domain is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$domain is a kbase_id
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
    my($self, $ctx, $domain) = @_;
    my($return);
    #BEGIN get_trees_with_entire_domain
        #END get_trees_with_entire_domain
    return($return);
}




=head2 get_trees_with_overlapping_domain

  $return = $obj->get_trees_with_overlapping_domain($domain)

=over 4

=item Parameter and return types

=begin html

<pre>
$domain is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$domain is a kbase_id
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
    my($self, $ctx, $domain) = @_;
    my($return);
    #BEGIN get_trees_with_overlapping_domain
        #END get_trees_with_overlapping_domain
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



=head2 alignment

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
Is this actually needed?


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

Meta data about an alignment, such as when it was created, parameters used in its creation, etc


=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
alignment_id has a value which is a kbase_id
isActive has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
alignment_id has a value which is a kbase_id
isActive has a value which is a bool
date_created has a value which is a timestamp
tree_generation_method has a value which is an int
tree_generation_parameters has a value which is a string


=end text

=back



=cut

1;
