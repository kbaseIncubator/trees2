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

Set of functions and tools for building, querying, 

created 5/21/2012 - msneddon
last updated oct 2012

=cut

#BEGIN_HEADER
use Data::Dumper;
use Config::Simple;
use Bio::KBase::CDMI::CDMI;
use Bio::KBase::Tree::TreeCppUtil;
#use Bio::KBase::Tree::ForesterParserWrapper;
#END_HEADER

sub new
{
    my($class, @args) = @_;
    my $self = {
    };
    bless $self, $class;
    #BEGIN_CONSTRUCTOR
    # NOTE: most code below copied from CDM library (CDMI_APIImpl.pm) on 10/16/12
    # comments for my own reference are added by msneddon
    # check if ref. to CDMI object was passed in
    my($cdmi) = @args;
    if (! $cdmi) {

	# if not, then go to the config file defined by the deployment and import
	# the deployment settings
	my %params;
	if (my $e = $ENV{KB_DEPLOYMENT_CONFIG})
	{
	    my $CDMI_SERVICE_NAME = "cdmi";
	    
	    #parse the file and 
	    my $c = Config::Simple->new();
	    $c->read($e);
	    my @params = qw(DBD dbName sock userData dbhost port dbms develop);
	    for my $p (@params)
	    {
		my $v = $c->param("$CDMI_SERVICE_NAME.$p");
		if ($v)
		{
		    $params{$p} = $v;
		}
	    }
	}
	#Create a connection to the CDMI (and print a logging debug mssg)
	if( 0 < scalar keys(%params) ) {
	    	warn "Connection to CDMI established with the following non-default parameters:\n";
	    	foreach my $key (sort keys %params) { warn "   $key => $params{$key} \n"; }
	} else { warn "Connection to CDMI established with all default parameters.  See Bio/KBase/CDMI/CDMI.pm\n"; }
        $cdmi = Bio::KBase::CDMI::CDMI->new(%params);
    }
    $self->{db} = $cdmi;
    #END_CONSTRUCTOR

    if ($self->can('_init_instance'))
    {
	$self->_init_instance();
    }
    return $self;
}

=head1 METHODS



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

Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
regular expression patterns.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    my $replacement_str="";
    foreach my $key ( keys %$replacements ) {
        $replacement_str = $replacement_str.$key.";".$$replacements{$key}.";";
    }
    $kb_tree->replaceNodeNames($replacement_str);
    $return = $kb_tree->toNewick(1); # 1 indicates the style to output, with 1=names and edges and comments (basically, output everything)
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

Given a tree in newick format, remove the nodes with the given names indicated in the list, and
simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    my $nodes_to_remove="";
    foreach my $val (@$removal_list) {
        $nodes_to_remove=$nodes_to_remove.$val.";";
    }
    $kb_tree->removeNodesByNameAndSimplify($nodes_to_remove);
    $return = $kb_tree->toNewick(1); # 1 indicates the style to output, with 1=names and edges and comments (basically, output everything)
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

Given a tree in newick format, list the names of the leaf nodes.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    my $leaf_names = $kb_tree->getAllLeafNames();
    my @leaf_name_list = split(';', $leaf_names);
    $return = \@leaf_name_list;
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

Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
those originating from MicrobesOnline, the names of internal nodes are bootstrap values, but will still
be returned by this function.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    my $node_names = $kb_tree->getAllNodeNames();
    my @node_name_list = split(';', $node_names);
    $return = \@node_name_list;
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

Given a tree, return the total number of nodes, including internal nodes and the root node.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    $return = $kb_tree->getNodeCount();
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

Given a tree, return the total number of leaf nodes, (internal and root nodes are ignored).  When the
tree was based on a multiple sequence alignment, the number of leaves will match the number of sequences
that were aligned.

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
    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree($tree);
    $return = $kb_tree->getLeafCount();
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




=head2 get_tree

  $return = $obj->get_tree($tree_id, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a tree
kbase_id is a string
tree is a string

</pre>

=end html

=begin text

$tree_id is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a tree
kbase_id is a string
tree is a string


=end text



=item Description

Returns the specified tree in the specified format, or an empty string if the tree does not exist.
The options hash provides a way to return the tree with different labels replaced or with different attached meta
information.  Currently, the available flags and understood options are listed 

    options = [
        format => 'newick',
        newick_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' || 'contig_sequence_id',
        newick_bootstrap => 'none' || 'internal_node_names'
    ];
 
The 'format' key indicates what string format the tree should be returned in.  Currently, there is only
support for 'newick'. The default value if not specified is 'newick'.

The 'newick_label' key only affects trees returned as newick format, and specifies what should be
placed in the label of each leaf.  'none' indicates that no label is added, so you get the structure
of the tree only.  'raw' indicates that the raw label mapping the leaf to an alignement row is used.
'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
feature that was originally used to define the sequence).  'protein_sequence_id' indicates that the
kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
the contig sequence id is added.  Note that trees are typically built with protein sequences OR
contig sequences. If you select one type of sequence, but the tree was built with the other type, then
no labels will be added.  The default value if none is specified is 'raw'.

The 'newick_bootstrap' key allows control over whether bootstrap values are returned if they exist, and
how they are returned.  'none' indicates that no bootstrap values are returned.

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
    
    my $kb = $self->{db};
    my @rows = $kb->GetAll('Tree',
	'Tree(id) = ? ORDER BY Tree(id)', $tree_id,
	[qw(Tree(newick))]);
    
    #check if query found something
    if(@rows) {
	my @return_rows=();
	foreach(@rows) {
	    #process the tree according the command-line options
	    my $raw_newick = $_;
	    
	    #read in the tree
	    my $kb_tree = new Bio::KBase::Tree::TreeCppUtil::KBTree(${$raw_newick}[0]);
	    
	    #$return = $kb_tree->toNewick(4);
	    
	    # push back the tree in the desired format
	    push(@return_rows, $kb_tree->toNewick(1));
	}
    
	#should only ever be one return, so get the first element, and then the first and only newick
	$return = @return_rows[0];
    } else {
	$return = "";
    }
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
$return is a reference to a list where each element is a tree
kbase_id is a string
tree is a string

</pre>

=end html

=begin text

$tree_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a tree
kbase_id is a string
tree is a string


=end text



=item Description

Performs exactly the same function as get_tree, but accepts a list of ids instead, and returns
a list of trees.

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
    $return = ["(mr_tree_1)","mr.tree2"];
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




=head2 get_tree_ids_by_feature

  $return = $obj->get_tree_ids_by_feature($feature_ids)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
is identified and used to retrieve all trees by ID that were built using the given protein sequence.

=back

=cut

sub get_tree_ids_by_feature
{
    my $self = shift;
    my($feature_ids) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_tree_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_feature');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_tree_ids_by_feature
    
    # not sure how to construct this query without just looping through...
    # also, it would be nice to make sure trees are distinct
    my $kb = $self->{db};
    my @allrows = ();
    foreach (@{$feature_ids}) {
	my @rows = $kb->GetAll('Tree IsBuiltFromAlignment Alignment IsAlignmentRowIn AlignmentRow ContainsAlignedProtein ProteinSequence IsProteinFor Feature',
	    'Feature(id) = ? ORDER BY Tree(id)', $_,
	    [qw(Tree(id))]);
	@allrows = (@allrows,@rows);
    }
    #put the tree ids in a single straight-up list
    my @return_list = ();
    foreach (@allrows) { push(@return_list,${$_}[0]); }
    # @return_list = sort(@return_list); #could sort here if we want
    $return = \@return_list;
    
    #END get_tree_ids_by_feature
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_tree_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_feature');
    }
    return($return);
}




=head2 get_tree_ids_by_protein_sequence

  $return = $obj->get_tree_ids_by_protein_sequence($protein_sequence_ids)

=over 4

=item Parameter and return types

=begin html

<pre>
$protein_sequence_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$protein_sequence_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Given a list of kbase ids of a protein sequences (their MD5s), retrieve the tree ids of trees that
were built based on these sequences.

=back

=cut

sub get_tree_ids_by_protein_sequence
{
    my $self = shift;
    my($protein_sequence_ids) = @_;

    my @_bad_arguments;
    (ref($protein_sequence_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"protein_sequence_ids\" (value was \"$protein_sequence_ids\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_tree_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_protein_sequence');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_tree_ids_by_protein_sequence
    # not sure how to construct this query without just looping through...
    # also, it would be nice to make sure trees are distinct
    my $kb = $self->{db};
    my @allrows = ();
    foreach (@{$protein_sequence_ids}) {
	my @rows = $kb->GetAll('Tree IsBuiltFromAlignment Alignment IsAlignmentRowIn AlignmentRow ContainsAlignedProtein',
	    'ContainsAlignedProtein(to-link) = ? ORDER BY Tree(id)', $_,
	    [qw(Tree(id))]);
	@allrows = (@allrows,@rows);
    }
    #put the tree ids in a single straight-up list
    my @return_list = ();
    foreach (@allrows) { push(@return_list,${$_}[0]); }
    # @return_list = sort(@return_list); #could sort here if we want
    $return = \@return_list;
    #END get_tree_ids_by_protein_sequence
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_tree_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_protein_sequence');
    }
    return($return);
}




=head2 get_alignment_ids_by_feature

  $return = $obj->get_alignment_ids_by_feature($feature_ids)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
is identified and used to retrieve all alignments by ID that were built using the given protein sequence.

=back

=cut

sub get_alignment_ids_by_feature
{
    my $self = shift;
    my($feature_ids) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_alignment_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_feature');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_alignment_ids_by_feature
    # not sure how to construct this query without just looping through...
    # also, it would be nice to make sure trees are distinct
    my $kb = $self->{db};
    my @allrows = ();
    foreach (@{$feature_ids}) {
	my @rows = $kb->GetAll('Alignment IsAlignmentRowIn AlignmentRow ContainsAlignedProtein ProteinSequence IsProteinFor Feature',
	    'Feature(id) = ? ORDER BY Alignment(id)', $_,
	    [qw(Alignment(id))]);
	@allrows = (@allrows,@rows);
    }
    #put the tree ids in a single straight-up list
    my @return_list = ();
    foreach (@allrows) { push(@return_list,${$_}[0]); }
    # @return_list = sort(@return_list); #could sort here if we want
    $return = \@return_list;
    #END get_alignment_ids_by_feature
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_alignment_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_feature');
    }
    return($return);
}




=head2 get_alignment_ids_by_protein_sequence

  $return = $obj->get_alignment_ids_by_protein_sequence($protein_sequence_ids)

=over 4

=item Parameter and return types

=begin html

<pre>
$protein_sequence_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$protein_sequence_ids is a reference to a list where each element is a kbase_id
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Given a list of kbase ids of a protein sequences (their MD5s), retrieve the alignment ids of trees that
were built based on these sequences.

=back

=cut

sub get_alignment_ids_by_protein_sequence
{
    my $self = shift;
    my($protein_sequence_ids) = @_;

    my @_bad_arguments;
    (ref($protein_sequence_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"protein_sequence_ids\" (value was \"$protein_sequence_ids\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_alignment_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_protein_sequence');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_alignment_ids_by_protein_sequence
    # not sure how to construct this query without just looping through...
    # also, it would be nice to make sure trees are distinct
    my $kb = $self->{db};
    my @allrows = ();
    foreach (@{$protein_sequence_ids}) {
	my @rows = $kb->GetAll('Alignment IsAlignmentRowIn AlignmentRow ContainsAlignedProtein',
	    'ContainsAlignedProtein(to-link) = ? ORDER BY Alignment(id)', $_,
	    [qw(Alignment(id))]);
	@allrows = (@allrows,@rows);
    }
    #put the tree ids in a single straight-up list
    my @return_list = ();
    foreach (@allrows) { push(@return_list,${$_}[0]); }
    # @return_list = sort(@return_list); #could sort here if we want
    $return = \@return_list;
    #END get_alignment_ids_by_protein_sequence
    my @_bad_returns;
    (ref($return) eq 'ARRAY') or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to get_alignment_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_protein_sequence');
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
the given tree. Note: may not be needed if this functionality is possible via the ER model

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
    $return = [""];
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




=head2 draw_html_tree

  $return = $obj->draw_html_tree($tree, $display_options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$display_options is a reference to a hash where the key is a string and the value is a string
$return is a html_file
newick_tree is a tree
tree is a string
html_file is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$display_options is a reference to a hash where the key is a string and the value is a string
$return is a html_file
newick_tree is a tree
tree is a string
html_file is a string


=end text



=item Description

Given a tree, render it in HTML/JAVASCRIPT and return the page.

=back

=cut

sub draw_html_tree
{
    my $self = shift;
    my($tree, $display_options) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"display_options\" (value was \"$display_options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to draw_html_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_html_tree');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN draw_html_tree
    $return = "<html>\n<head></head>\n<body>\n".$tree."\n</body>\n</html>\n";
    #END draw_html_tree
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to draw_html_tree:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_html_tree');
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



=item Description

indicates true or false values, false <= 0, true >=1


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



=item Description

time in units of number of seconds since the epoch


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



=item Description

integer number indicating a 1-based position in an amino acid / nucleotide sequence


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

A string representation of a phylogenetic tree.  The format/syntax of the string is
specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
'phyloXML_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return
trees in different formats depending on addtional option parameters. Regardless of format, all leaf nodes
in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality
of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named.
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



=head2 fasta

=over 4



=item Description

String representation of a sequence or set of sequences in FASTA format.  The precise alphabet used is
not yet specified, but will be similar to sequences stored in KBase.


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

String representation of an alignment in FASTA format.  The precise alphabet and syntax of the alignment
string (e.g. symbol for gaps) is not yet specified, but will be similar to alignments currently in SEED.


=item Definition

=begin html

<pre>
a fasta
</pre>

=end html

=begin text

a fasta

=end text

=back



=head2 node_name

=over 4



=item Description

The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note, this
is not the full, raw label in a newick_tree (which may include comments or distances).


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



=head2 html_file

=over 4



=item Description

String in HTML format, used in the KBase Tree library for returning rendered trees.


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
