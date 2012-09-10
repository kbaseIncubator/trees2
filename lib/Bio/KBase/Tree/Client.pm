package Bio::KBase::Tree::Client;

use JSON::RPC::Client;
use strict;
use Data::Dumper;
use URI;
use Bio::KBase::Exceptions;

# Client version should match Impl version
# This is a Semantic Version number,
# http://semver.org
our $VERSION = "0.1.0";

=head1 NAME

Bio::KBase::Tree::Client

=head1 DESCRIPTION



=cut

sub new
{
    my($class, $url) = @_;

    my $self = {
	client => Bio::KBase::Tree::Client::RpcClient->new,
	url => $url,
    };
    my $ua = $self->{client}->ua;	 
    my $timeout = $ENV{CDMI_TIMEOUT} || (30 * 60);	 
    $ua->timeout($timeout);
    bless $self, $class;
    #    $self->_validate_version();
    return $self;
}




=head2 $result = convert_newick2phyloXML(tree)

Convert a tree encoded in newick format to a tree encded in phyloXML format.

=cut

sub convert_newick2phyloXML
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function convert_newick2phyloXML (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to convert_newick2phyloXML:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'convert_newick2phyloXML');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.convert_newick2phyloXML",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'convert_newick2phyloXML',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method convert_newick2phyloXML",
					    status_line => $self->{client}->status_line,
					    method_name => 'convert_newick2phyloXML',
				       );
    }
}



=head2 $result = convert_phyloXML2newick(tree)

Convert a tree encoded in newick format to a tree encded in phyloXML format.

=cut

sub convert_phyloXML2newick
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function convert_phyloXML2newick (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to convert_phyloXML2newick:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'convert_phyloXML2newick');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.convert_phyloXML2newick",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'convert_phyloXML2newick',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method convert_phyloXML2newick",
					    status_line => $self->{client}->status_line,
					    method_name => 'convert_phyloXML2newick',
				       );
    }
}



=head2 $result = convert_newick2json(tree)

Convert a tree encoded in newick format to a tree encded in JSON format.

=cut

sub convert_newick2json
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function convert_newick2json (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to convert_newick2json:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'convert_newick2json');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.convert_newick2json",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'convert_newick2json',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method convert_newick2json",
					    status_line => $self->{client}->status_line,
					    method_name => 'convert_newick2json',
				       );
    }
}



=head2 $result = convert_json2newick(tree)

Convert a tree encoded in JSON format to a tree encded in newick format.

=cut

sub convert_json2newick
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function convert_json2newick (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to convert_json2newick:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'convert_json2newick');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.convert_json2newick",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'convert_json2newick',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method convert_json2newick",
					    status_line => $self->{client}->status_line,
					    method_name => 'convert_json2newick',
				       );
    }
}



=head2 $result = replace_node_names(tree, replacements)

Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
regular expression patterns.

=cut

sub replace_node_names
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function replace_node_names (received $n, expecting 2)");
    }
    {
	my($tree, $replacements) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        (ref($replacements) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"replacements\" (value was \"$replacements\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to replace_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'replace_node_names');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.replace_node_names",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'replace_node_names',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method replace_node_names",
					    status_line => $self->{client}->status_line,
					    method_name => 'replace_node_names',
				       );
    }
}



=head2 $result = remove_node_names_and_simplify(tree, removal_list)

Given a tree in newick format, remove the nodes with the given names indicated in the list, and
simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.

=cut

sub remove_node_names_and_simplify
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function remove_node_names_and_simplify (received $n, expecting 2)");
    }
    {
	my($tree, $removal_list) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        (ref($removal_list) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 2 \"removal_list\" (value was \"$removal_list\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to remove_node_names_and_simplify:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'remove_node_names_and_simplify');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.remove_node_names_and_simplify",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'remove_node_names_and_simplify',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method remove_node_names_and_simplify",
					    status_line => $self->{client}->status_line,
					    method_name => 'remove_node_names_and_simplify',
				       );
    }
}



=head2 $result = extract_leaf_node_names(tree)

Given a tree in newick format, list the names of the leaf nodes.

=cut

sub extract_leaf_node_names
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function extract_leaf_node_names (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to extract_leaf_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'extract_leaf_node_names');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.extract_leaf_node_names",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'extract_leaf_node_names',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method extract_leaf_node_names",
					    status_line => $self->{client}->status_line,
					    method_name => 'extract_leaf_node_names',
				       );
    }
}



=head2 $result = extract_node_names(tree)

Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
those originating from MicrobesOnline, the names of internal nodes are bootstrap values, but will still
be returned by this function.

=cut

sub extract_node_names
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function extract_node_names (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to extract_node_names:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'extract_node_names');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.extract_node_names",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'extract_node_names',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method extract_node_names",
					    status_line => $self->{client}->status_line,
					    method_name => 'extract_node_names',
				       );
    }
}



=head2 $result = get_node_count(tree)

Given a tree, return the total number of nodes, including internal nodes and the root node.

=cut

sub get_node_count
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_node_count (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_node_count:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_node_count');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_node_count",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_node_count',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_node_count",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_node_count',
				       );
    }
}



=head2 $result = get_leaf_count(tree)

Given a tree, return the total number of leaf nodes, (internal and root nodes are ignored).  When the
tree was based on a multiple sequence alignment, the number of leaves will match the number of sequences
that were aligned.

=cut

sub get_leaf_count
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_leaf_count (received $n, expecting 1)");
    }
    {
	my($tree) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_leaf_count:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_leaf_count');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_leaf_count",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_leaf_count',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_leaf_count",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_leaf_count',
				       );
    }
}



=head2 $result = get_tree(tree_id, options)

Returns the specified tree in newick format, or an empty string if the tree does not exist.  Options
hash provides a way to return the tree with different labels replaced or with different attached meta
information.

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

Note: the options hash will be the same as for other functions which provide substitution capabilities 

todo: provide a way to get meta data about this tree, possibly in a separate function, but may
not be needed if this is provided by the ER model.

=cut

sub get_tree
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_tree (received $n, expecting 2)");
    }
    {
	my($tree_id, $options) = @args;

	my @_bad_arguments;
        (!ref($tree_id)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree_id\" (value was \"$tree_id\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_tree');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_tree',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_tree",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_tree',
				       );
    }
}



=head2 $result = get_trees(tree_ids, options)

Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
was not found. Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=cut

sub get_trees
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_trees (received $n, expecting 2)");
    }
    {
	my($tree_ids, $options) = @args;

	my @_bad_arguments;
        (ref($tree_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"tree_ids\" (value was \"$tree_ids\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_trees:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_trees');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_trees",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_trees',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_trees",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_trees',
				       );
    }
}



=head2 $result = all_tree_ids(is_active)

Returns a list of all IDs of all trees in the database that match the given flags (right now
the only flag indicates if the tree is active or not, meaning the latest version of the tree,
but this should be extended to accept more args and possible queries.

=cut

sub all_tree_ids
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function all_tree_ids (received $n, expecting 1)");
    }
    {
	my($is_active) = @args;

	my @_bad_arguments;
        (!ref($is_active)) or push(@_bad_arguments, "Invalid type for argument 1 \"is_active\" (value was \"$is_active\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to all_tree_ids:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'all_tree_ids');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.all_tree_ids",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'all_tree_ids',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method all_tree_ids",
					    status_line => $self->{client}->status_line,
					    method_name => 'all_tree_ids',
				       );
    }
}



=head2 $result = get_trees_with_entire_seq(sequence, beg, end, options)

Returns all tree IDs in which the entire portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.
todo: should beg/end just be included in some options hash?
todo: define contents of options hash, which will allow more complex queries, such as returning
      only active trees, or trees of a particuar hieght, etc...

=cut

sub get_trees_with_entire_seq
{
    my($self, @args) = @_;

    if ((my $n = @args) != 4)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_trees_with_entire_seq (received $n, expecting 4)");
    }
    {
	my($sequence, $beg, $end, $options) = @args;

	my @_bad_arguments;
        (!ref($sequence)) or push(@_bad_arguments, "Invalid type for argument 1 \"sequence\" (value was \"$sequence\")");
        (!ref($beg)) or push(@_bad_arguments, "Invalid type for argument 2 \"beg\" (value was \"$beg\")");
        (!ref($end)) or push(@_bad_arguments, "Invalid type for argument 3 \"end\" (value was \"$end\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 4 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_trees_with_entire_seq:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_trees_with_entire_seq');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_trees_with_entire_seq",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_trees_with_entire_seq',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_trees_with_entire_seq",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_trees_with_entire_seq',
				       );
    }
}



=head2 $result = get_trees_with_overlapping_seq(sequence, beg, end, options)

Returns all tree IDs in which some portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the tree.

=cut

sub get_trees_with_overlapping_seq
{
    my($self, @args) = @_;

    if ((my $n = @args) != 4)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_trees_with_overlapping_seq (received $n, expecting 4)");
    }
    {
	my($sequence, $beg, $end, $options) = @args;

	my @_bad_arguments;
        (!ref($sequence)) or push(@_bad_arguments, "Invalid type for argument 1 \"sequence\" (value was \"$sequence\")");
        (!ref($beg)) or push(@_bad_arguments, "Invalid type for argument 2 \"beg\" (value was \"$beg\")");
        (!ref($end)) or push(@_bad_arguments, "Invalid type for argument 3 \"end\" (value was \"$end\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 4 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_trees_with_overlapping_seq:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_trees_with_overlapping_seq');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_trees_with_overlapping_seq",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_trees_with_overlapping_seq',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_trees_with_overlapping_seq",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_trees_with_overlapping_seq',
				       );
    }
}



=head2 $result = get_trees_with_entire_domain(domain, options)

Returns all tree IDs in which the entire portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on this domain). NOT FUNCTIONAL UNTIL KBASE HAS HOMOLOGUE/DOMAIN LOOKUPS

=cut

sub get_trees_with_entire_domain
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_trees_with_entire_domain (received $n, expecting 2)");
    }
    {
	my($domain, $options) = @args;

	my @_bad_arguments;
        (!ref($domain)) or push(@_bad_arguments, "Invalid type for argument 1 \"domain\" (value was \"$domain\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_trees_with_entire_domain:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_trees_with_entire_domain');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_trees_with_entire_domain",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_trees_with_entire_domain',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_trees_with_entire_domain",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_trees_with_entire_domain',
				       );
    }
}



=head2 $result = get_trees_with_overlapping_domain(domain, options)

Returns all tree IDs in which some portion of the given domain is used in the alignment
which generates the tree (usually such trees will be constructed based on a similar domain created
with an alternative method, so the entire domain may not be contained).  NOT FUNCTIONAL UNTIL KBASE HAS HOMOLOGUE/DOMAIN LOOKUPS

=cut

sub get_trees_with_overlapping_domain
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_trees_with_overlapping_domain (received $n, expecting 2)");
    }
    {
	my($domain, $options) = @args;

	my @_bad_arguments;
        (!ref($domain)) or push(@_bad_arguments, "Invalid type for argument 1 \"domain\" (value was \"$domain\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_trees_with_overlapping_domain:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_trees_with_overlapping_domain');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_trees_with_overlapping_domain",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_trees_with_overlapping_domain',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_trees_with_overlapping_domain",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_trees_with_overlapping_domain',
				       );
    }
}



=head2 $result = substitute_node_names_with_kbase_ids(trees, options)

Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
parameters to be passed (parameter list is also currently not defined yet and is currently ignored.)

=cut

sub substitute_node_names_with_kbase_ids
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function substitute_node_names_with_kbase_ids (received $n, expecting 2)");
    }
    {
	my($trees, $options) = @args;

	my @_bad_arguments;
        (ref($trees) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"trees\" (value was \"$trees\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to substitute_node_names_with_kbase_ids:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'substitute_node_names_with_kbase_ids');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.substitute_node_names_with_kbase_ids",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'substitute_node_names_with_kbase_ids',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method substitute_node_names_with_kbase_ids",
					    status_line => $self->{client}->status_line,
					    method_name => 'substitute_node_names_with_kbase_ids',
				       );
    }
}



=head2 $result = get_kbase_ids_from_alignment_row(alignment_id, row_number)

Given an alignment and a row in the alignment, returns all the kbase_ids of the sequences that compose
the given tree. Note: may not be needed if this functionality is possible via the ER model

=cut

sub get_kbase_ids_from_alignment_row
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_kbase_ids_from_alignment_row (received $n, expecting 2)");
    }
    {
	my($alignment_id, $row_number) = @args;

	my @_bad_arguments;
        (!ref($alignment_id)) or push(@_bad_arguments, "Invalid type for argument 1 \"alignment_id\" (value was \"$alignment_id\")");
        (!ref($row_number)) or push(@_bad_arguments, "Invalid type for argument 2 \"row_number\" (value was \"$row_number\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_kbase_ids_from_alignment_row:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_kbase_ids_from_alignment_row');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_kbase_ids_from_alignment_row",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_kbase_ids_from_alignment_row',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_kbase_ids_from_alignment_row",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_kbase_ids_from_alignment_row',
				       );
    }
}



=head2 $result = add_node_to_tree(tree_id, sequence_id, options)

Given a tree in KBASE and a sequence in FASTA format, attempt to add the new sequence into the tree.  This
method requires that the tree was built from a multiple sequence alignment and that the tree/alignment is stored
in KBASE.  This method returns

=cut

sub add_node_to_tree
{
    my($self, @args) = @_;

    if ((my $n = @args) != 3)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function add_node_to_tree (received $n, expecting 3)");
    }
    {
	my($tree_id, $sequence_id, $options) = @args;

	my @_bad_arguments;
        (!ref($tree_id)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree_id\" (value was \"$tree_id\")");
        (!ref($sequence_id)) or push(@_bad_arguments, "Invalid type for argument 2 \"sequence_id\" (value was \"$sequence_id\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 3 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to add_node_to_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'add_node_to_tree');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.add_node_to_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'add_node_to_tree',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method add_node_to_tree",
					    status_line => $self->{client}->status_line,
					    method_name => 'add_node_to_tree',
				       );
    }
}



=head2 $result = build_tree_from_fasta(alignment, options)

Given an alignment in FASTA format, build a phylogenetic tree using the options indicated.

=cut

sub build_tree_from_fasta
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function build_tree_from_fasta (received $n, expecting 2)");
    }
    {
	my($alignment, $options) = @args;

	my @_bad_arguments;
        (!ref($alignment)) or push(@_bad_arguments, "Invalid type for argument 1 \"alignment\" (value was \"$alignment\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to build_tree_from_fasta:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'build_tree_from_fasta');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.build_tree_from_fasta",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'build_tree_from_fasta',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method build_tree_from_fasta",
					    status_line => $self->{client}->status_line,
					    method_name => 'build_tree_from_fasta',
				       );
    }
}



=head2 $result = align_sequences(sequences, options)

Given a set of sequences in FASTA format, construct a sequence alignment with the options indicated.

=cut

sub align_sequences
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function align_sequences (received $n, expecting 2)");
    }
    {
	my($sequences, $options) = @args;

	my @_bad_arguments;
        (ref($sequences) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"sequences\" (value was \"$sequences\")");
        (ref($options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"options\" (value was \"$options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to align_sequences:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'align_sequences');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.align_sequences",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'align_sequences',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method align_sequences",
					    status_line => $self->{client}->status_line,
					    method_name => 'align_sequences',
				       );
    }
}



=head2 $result = draw_web_tree(tree, display_options)

Given a tree, render it in HTML/JAVASCRIPT and return the page.

=cut

sub draw_web_tree
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function draw_web_tree (received $n, expecting 2)");
    }
    {
	my($tree, $display_options) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"display_options\" (value was \"$display_options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to draw_web_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'draw_web_tree');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.draw_web_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'draw_web_tree',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method draw_web_tree",
					    status_line => $self->{client}->status_line,
					    method_name => 'draw_web_tree',
				       );
    }
}



=head2 $result = draw_svg_tree(tree, display_options)

Given a tree, render it as an SVG object and return the drawing.

=cut

sub draw_svg_tree
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function draw_svg_tree (received $n, expecting 2)");
    }
    {
	my($tree, $display_options) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"display_options\" (value was \"$display_options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to draw_svg_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'draw_svg_tree');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.draw_svg_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'draw_svg_tree',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method draw_svg_tree",
					    status_line => $self->{client}->status_line,
					    method_name => 'draw_svg_tree',
				       );
    }
}



sub version {
    my ($self) = @_;
    my $result = $self->{client}->call($self->{url}, {
        method => "Tree.version",
        params => [],
    });
    if ($result) {
        if ($result->is_error) {
            Bio::KBase::Exceptions::JSONRPC->throw(
                error => $result->error_message,
                code => $result->content->{code},
                method_name => 'draw_svg_tree',
            );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(
            error => "Error invoking method draw_svg_tree",
            status_line => $self->{client}->status_line,
            method_name => 'draw_svg_tree',
        );
    }
}

sub _validate_version {
    my ($self) = @_;
    my $svr_version = $self->version();
    my $client_version = $VERSION;
    my ($cMajor, $cMinor) = split(/\./, $client_version);
    my ($sMajor, $sMinor) = split(/\./, $svr_version);
    if ($sMajor != $cMajor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Major version numbers differ.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor < $cMinor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Client minor version greater than Server minor version.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor > $cMinor) {
        warn "New client version available for Bio::KBase::Tree::Client\n";
    }
    if ($sMajor == 0) {
        warn "Bio::KBase::Tree::Client version is $svr_version. API subject to change.\n";
    }
}

package Bio::KBase::Tree::Client::RpcClient;
use base 'JSON::RPC::Client';

#
# Override JSON::RPC::Client::call because it doesn't handle error returns properly.
#

sub call {
    my ($self, $uri, $obj) = @_;
    my $result;

    if ($uri =~ /\?/) {
       $result = $self->_get($uri);
    }
    else {
        Carp::croak "not hashref." unless (ref $obj eq 'HASH');
        $result = $self->_post($uri, $obj);
    }

    my $service = $obj->{method} =~ /^system\./ if ( $obj );

    $self->status_line($result->status_line);

    if ($result->is_success) {

        return unless($result->content); # notification?

        if ($service) {
            return JSON::RPC::ServiceObject->new($result, $self->json);
        }

        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    elsif ($result->content_type eq 'application/json')
    {
        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    else {
        return;
    }
}

1;
