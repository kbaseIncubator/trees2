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

Returns the specified tree in the specified format, or an empty string if the tree does not exist.
The options hash provides a way to return the tree with different labels replaced or with different attached meta
information.  Currently, the available flags and understood options are listed 

    options = [
        format => 'newick',
        newick_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' || 'contig_sequence_id',
        newick_bootstrap => 'none' || 'internal_node_labels'
        newick_distance => 'none' || 'raw'
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
how they are returned.  'none' indicates that no bootstrap values are returned. 'internal_node_labels'
indicates that bootstrap values are returned as internal node labels.  Default value is 'internal_node_labels';

The 'newick_distance' key allows control over whether distance labels are generated or not.  If set to
'none', no distances will be output. Default is 'raw', which outputs the distances exactly as they appeared
when loaded into kbase.

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

Performs exactly the same function as get_tree, but accepts a list of ids instead, and returns
a list of trees.

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



=head2 $result = get_tree_ids_by_feature(feature_ids)

Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
is identified and used to retrieve all trees by ID that were built using the given protein sequence.

=cut

sub get_tree_ids_by_feature
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_tree_ids_by_feature (received $n, expecting 1)");
    }
    {
	my($feature_ids) = @args;

	my @_bad_arguments;
        (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"feature_ids\" (value was \"$feature_ids\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_tree_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_tree_ids_by_feature');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_tree_ids_by_feature",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_tree_ids_by_feature',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_tree_ids_by_feature",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_tree_ids_by_feature',
				       );
    }
}



=head2 $result = get_tree_ids_by_protein_sequence(protein_sequence_ids)

Given a list of kbase ids of a protein sequences (their MD5s), retrieve the tree ids of trees that
were built based on these sequences.

=cut

sub get_tree_ids_by_protein_sequence
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_tree_ids_by_protein_sequence (received $n, expecting 1)");
    }
    {
	my($protein_sequence_ids) = @args;

	my @_bad_arguments;
        (ref($protein_sequence_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"protein_sequence_ids\" (value was \"$protein_sequence_ids\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_tree_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_tree_ids_by_protein_sequence');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_tree_ids_by_protein_sequence",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_tree_ids_by_protein_sequence',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_tree_ids_by_protein_sequence",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_tree_ids_by_protein_sequence',
				       );
    }
}



=head2 $result = get_alignment_ids_by_feature(feature_ids)

Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
is identified and used to retrieve all alignments by ID that were built using the given protein sequence.

=cut

sub get_alignment_ids_by_feature
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_alignment_ids_by_feature (received $n, expecting 1)");
    }
    {
	my($feature_ids) = @args;

	my @_bad_arguments;
        (ref($feature_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"feature_ids\" (value was \"$feature_ids\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_alignment_ids_by_feature:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_alignment_ids_by_feature');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_alignment_ids_by_feature",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_alignment_ids_by_feature',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_alignment_ids_by_feature",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_alignment_ids_by_feature',
				       );
    }
}



=head2 $result = get_alignment_ids_by_protein_sequence(protein_sequence_ids)

Given a list of kbase ids of a protein sequences (their MD5s), retrieve the alignment ids of trees that
were built based on these sequences.

=cut

sub get_alignment_ids_by_protein_sequence
{
    my($self, @args) = @_;

    if ((my $n = @args) != 1)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function get_alignment_ids_by_protein_sequence (received $n, expecting 1)");
    }
    {
	my($protein_sequence_ids) = @args;

	my @_bad_arguments;
        (ref($protein_sequence_ids) eq 'ARRAY') or push(@_bad_arguments, "Invalid type for argument 1 \"protein_sequence_ids\" (value was \"$protein_sequence_ids\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to get_alignment_ids_by_protein_sequence:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'get_alignment_ids_by_protein_sequence');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.get_alignment_ids_by_protein_sequence",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'get_alignment_ids_by_protein_sequence',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_alignment_ids_by_protein_sequence",
					    status_line => $self->{client}->status_line,
					    method_name => 'get_alignment_ids_by_protein_sequence',
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



=head2 $result = draw_html_tree(tree, display_options)

Given a tree, render it in HTML/JAVASCRIPT and return the page.

=cut

sub draw_html_tree
{
    my($self, @args) = @_;

    if ((my $n = @args) != 2)
    {
	Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
							       "Invalid argument count for function draw_html_tree (received $n, expecting 2)");
    }
    {
	my($tree, $display_options) = @args;

	my @_bad_arguments;
        (!ref($tree)) or push(@_bad_arguments, "Invalid type for argument 1 \"tree\" (value was \"$tree\")");
        (ref($display_options) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 2 \"display_options\" (value was \"$display_options\")");
        if (@_bad_arguments) {
	    my $msg = "Invalid arguments passed to draw_html_tree:\n" . join("", map { "\t$_\n" } @_bad_arguments);
	    Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
								   method_name => 'draw_html_tree');
	}
    }

    my $result = $self->{client}->call($self->{url}, {
	method => "Tree.draw_html_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
					       code => $result->content->{code},
					       method_name => 'draw_html_tree',
					      );
	} else {
	    return wantarray ? @{$result->result} : $result->result->[0];
	}
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method draw_html_tree",
					    status_line => $self->{client}->status_line,
					    method_name => 'draw_html_tree',
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
                method_name => 'draw_html_tree',
            );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(
            error => "Error invoking method draw_html_tree",
            status_line => $self->{client}->status_line,
            method_name => 'draw_html_tree',
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
