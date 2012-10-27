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



=head2 convert_newick2phyloXML

  $return = $obj->convert_newick2phyloXML($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a phyloXML_tree
newick_tree is a tree
tree is a string
phyloXML_tree is a tree

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a phyloXML_tree
newick_tree is a tree
tree is a string
phyloXML_tree is a tree


=end text



=item Description

Convert a tree encoded in newick format to a tree encded in phyloXML format.

=back

=cut

sub convert_newick2phyloXML
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to convert_newick2phyloXML:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_newick2phyloXML');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN convert_newick2phyloXML
    #$return = Bio::KBase::Tree::ForesterParserWrapper::convertToPhyloXML($tree)."\n";
    #END convert_newick2phyloXML
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to convert_newick2phyloXML:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_newick2phyloXML');
    }
    return($return);
}




=head2 convert_phyloXML2newick

  $return = $obj->convert_phyloXML2newick($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a phyloXML_tree
newick_tree is a tree
tree is a string
phyloXML_tree is a tree

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a phyloXML_tree
newick_tree is a tree
tree is a string
phyloXML_tree is a tree


=end text



=item Description

Convert a tree encoded in newick format to a tree encded in phyloXML format.

=back

=cut

sub convert_phyloXML2newick
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to convert_phyloXML2newick:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_phyloXML2newick');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN convert_phyloXML2newick
    
    # call forester parser wrapper method
    
    #END convert_phyloXML2newick
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to convert_phyloXML2newick:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_phyloXML2newick');
    }
    return($return);
}




=head2 convert_newick2json

  $return = $obj->convert_newick2json($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$return is a json_tree
newick_tree is a tree
tree is a string
json_tree is a tree

</pre>

=end html

=begin text

$tree is a newick_tree
$return is a json_tree
newick_tree is a tree
tree is a string
json_tree is a tree


=end text



=item Description

Convert a tree encoded in newick format to a tree encded in JSON format.

=back

=cut

sub convert_newick2json
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to convert_newick2json:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_newick2json');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN convert_newick2json
    
    # call c++ tree lib method
    
    #END convert_newick2json
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to convert_newick2json:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_newick2json');
    }
    return($return);
}




=head2 convert_json2newick

  $return = $obj->convert_json2newick($tree)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a json_tree
$return is a newick_tree
json_tree is a tree
tree is a string
newick_tree is a tree

</pre>

=end html

=begin text

$tree is a json_tree
$return is a newick_tree
json_tree is a tree
tree is a string
newick_tree is a tree


=end text



=item Description

Convert a tree encoded in JSON format to a tree encded in newick format.

=back

=cut

sub convert_json2newick
{
    my $self = shift;
    my($tree) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to convert_json2newick:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_json2newick');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN convert_json2newick
    
    # call c++ tree lib
    
    
    #END convert_json2newick
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to convert_json2newick:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'convert_json2newick');
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
    my $kb_tree = new Bio::KBase::Tree::Util::KBTree($tree);
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
was not found. Note: this function may not be needed if this functionality is provided by the auto-gen ER code

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
    $return = "(mr_trees)";
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
    $return = "(mr_tree)";
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




=head2 get_tree_ids_by_feature

  $return = $obj->get_tree_ids_by_feature($feature_ids, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description

Returns all tree IDs in which some portion of the given domain is used in the alignment
which generates the tree (usually such trees will be constructed based on a similar domain created
with an alternative method, so the entire domain may not be contained).  NOT FUNCTIONAL UNTIL KBASE HAS HOMOLOGUE/DOMAIN LOOKUPS
funcdef get_trees_with_overlapping_domain(kbase_id domain, mapping<string,string>options) returns (list<kbase_id>);

=back

=cut

sub get_tree_ids_by_feature
{
    my $self = shift;
    my($feature_ids, $options) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_tree_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_feature');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_tree_ids_by_feature
    my $kb = $self->{db};
    my @rows = $kb->GetAll('Tree IsBuiltFromAlignment Alignment IsAlignmentRowIn AlignmentRow ContainsAlignedProtein ProteinSequence IsProteinFor Feature',
	    'Feature(id) = ?', @{ $feature_ids },
	    [qw(Tree(id))]);
    $return = \@rows;
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

  $return = $obj->get_tree_ids_by_protein_sequence($feature_ids, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description



=back

=cut

sub get_tree_ids_by_protein_sequence
{
    my $self = shift;
    my($feature_ids, $options) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_tree_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_tree_ids_by_protein_sequence');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_tree_ids_by_protein_sequence
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

  $return = $obj->get_alignment_ids_by_feature($feature_ids, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description



=back

=cut

sub get_alignment_ids_by_feature
{
    my $self = shift;
    my($feature_ids, $options) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_alignment_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_feature');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_alignment_ids_by_feature
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

  $return = $obj->get_alignment_ids_by_protein_sequence($feature_ids, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string

</pre>

=end html

=begin text

$feature_ids is a reference to a list where each element is a kbase_id
$options is a reference to a hash where the key is a string and the value is a string
$return is a reference to a list where each element is a kbase_id
kbase_id is a string


=end text



=item Description



=back

=cut

sub get_alignment_ids_by_protein_sequence
{
    my $self = shift;
    my($feature_ids, $options) = @_;

    my @_bad_arguments;
    (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"feature_ids\" (value was \"$feature_ids\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to get_alignment_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'get_alignment_ids_by_protein_sequence');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN get_alignment_ids_by_protein_sequence
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
    
    # 1) retrieve the tree
    # 2) find cooresponding alignment and alignment rows
    # 3) extract the feature ids or sequence IDs from the alignment row table with the original internal tree node id
    # 4) create a c++ tree lib tree object
    # 5) replace the names with the feature IDs or sequenceIDs
    # 6) (optionally) lookup and add annotations
    # 7) return the newick tree
    
    $return = "hello mr. tree., sent from substitute_node_labels_with_kbase_ids";
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
    $return = "(mr_tree)";
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

Given a tree in KBASE and a sequence in FASTA format, attempt to add the new sequence into the tree.  This
method requires that the tree was built from a multiple sequence alignment and that the tree/alignment is stored
in KBASE.  This method returns

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




=head2 build_tree_from_fasta

  $return = $obj->build_tree_from_fasta($alignment, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$alignment is a fasta_alignment
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
fasta_alignment is a fasta
fasta is a string
newick_tree is a tree
tree is a string

</pre>

=end html

=begin text

$alignment is a fasta_alignment
$options is a reference to a hash where the key is a string and the value is a string
$return is a newick_tree
fasta_alignment is a fasta
fasta is a string
newick_tree is a tree
tree is a string


=end text



=item Description

Given an alignment in FASTA format, build a phylogenetic tree using the options indicated.

=back

=cut

sub build_tree_from_fasta
{
    my $self = shift;
    my($alignment, $options) = @_;

    my @_bad_arguments;
    (!ref($alignment)) or push(@_bad_arguments, "Invalid type for argument \"alignment\" (value was \"$alignment\")");
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




=head2 align_sequences

  $return = $obj->align_sequences($sequences, $options)

=over 4

=item Parameter and return types

=begin html

<pre>
$sequences is a reference to a list where each element is a fasta
$options is a reference to a hash where the key is a string and the value is a string
$return is a fasta_alignment
fasta is a string
fasta_alignment is a fasta

</pre>

=end html

=begin text

$sequences is a reference to a list where each element is a fasta
$options is a reference to a hash where the key is a string and the value is a string
$return is a fasta_alignment
fasta is a string
fasta_alignment is a fasta


=end text



=item Description

Given a set of sequences in FASTA format, construct a sequence alignment with the options indicated.

=back

=cut

sub align_sequences
{
    my $self = shift;
    my($sequences, $options) = @_;

    my @_bad_arguments;
    (ref($sequences) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument \"sequences\" (value was \"$sequences\")");
    (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"options\" (value was \"$options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to align_sequences:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'align_sequences');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN align_sequences
    #END align_sequences
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to align_sequences:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'align_sequences');
    }
    return($return);
}




=head2 draw_web_tree

  $return = $obj->draw_web_tree($tree, $display_options)

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

sub draw_web_tree
{
    my $self = shift;
    my($tree, $display_options) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"display_options\" (value was \"$display_options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to draw_web_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_web_tree');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN draw_web_tree
    #END draw_web_tree
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to draw_web_tree:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_web_tree');
    }
    return($return);
}




=head2 draw_svg_tree

  $return = $obj->draw_svg_tree($tree, $display_options)

=over 4

=item Parameter and return types

=begin html

<pre>
$tree is a newick_tree
$display_options is a reference to a hash where the key is a string and the value is a string
$return is a svg_file
newick_tree is a tree
tree is a string
svg_file is a string

</pre>

=end html

=begin text

$tree is a newick_tree
$display_options is a reference to a hash where the key is a string and the value is a string
$return is a svg_file
newick_tree is a tree
tree is a string
svg_file is a string


=end text



=item Description

Given a tree, render it as an SVG object and return the drawing.

=back

=cut

sub draw_svg_tree
{
    my $self = shift;
    my($tree, $display_options) = @_;

    my @_bad_arguments;
    (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument \"tree\" (value was \"$tree\")");
    (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument \"display_options\" (value was \"$display_options\")");
    if (@_bad_arguments) {
	my $msg = "Invalid arguments passed to draw_svg_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_svg_tree');
    }

    my $ctx = $Bio::KBase::Tree::Service::CallContext;
    my($return);
    #BEGIN draw_svg_tree
    #END draw_svg_tree
    my @_bad_returns;
    (!ref($return)) or push(@_bad_returns, "Invalid type for return variable \"return\" (value was \"$return\")");
    if (@_bad_returns) {
	my $msg = "Invalid returns passed to draw_svg_tree:\n" . join("", map { "\t$_\n" } @_bad_returns);
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
							       method_name => 'draw_svg_tree');
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

A string representation of a phylogenetic tree.  The format/syntax of the string can be
specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
'phyloXML_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to accept/return
trees in different formats depending on addtional option parameters. Regardless of format, all leaf nodes
in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality
of the API to replace these IDs with other KBase Ids instead, depending on how the tree was built.  Internal
nodes may or may not be named.  Nodes, depending on the format, may also be annotated with structured data
such as bootstrap values.


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

The string representation of the parsed node name (may be a kbase_id, but does not have to).  Note, this
is not the full label in a newick_tree (which may include comments or distances).


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



=head2 svg_file

=over 4



=item Description

String in SVG format, used in the KBase Tree library for returning rendered trees.


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
