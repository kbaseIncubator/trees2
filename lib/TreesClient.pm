package TreesClient;

use JSON::RPC::Client;
use strict;
use Data::Dumper;
use URI;

=head1 NAME

TreesClient

=head1 DESCRIPTION

Tree and Multiple Sequence Alignment(MSA) API

Full documentation and API reference will be added here.  What follows is the current
version of the tree/MSA API proposal.

created 5/21/2012 - msneddon

=cut

sub new
{
    my($class, $url) = @_;

    my $self = {
	client => JSON::RPC::Client->new,
	url => $url,
    };
    return bless $self, $class;
}



=head2 $result = get_tree(tree_id, options)

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

=cut

sub get_tree
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_tree: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_tree: " . $self->{client}->status_line;
    }
}




=head2 $result = get_trees(tree_ids, options)

Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
was not found.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=cut

sub get_trees
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_trees",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_trees: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_trees: " . $self->{client}->status_line;
    }
}




=head2 $result = all_tree_ids(is_active)

Returns a list of all IDs of all trees in the database that match the given flags (right now
the only flag indicates if the tree is active or not, meaning the latest version of the tree,
but this should be extended to accept more args and possible queries.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=cut

sub all_tree_ids
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.all_tree_ids",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking all_tree_ids: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking all_tree_ids: " . $self->{client}->status_line;
    }
}




=head2 $result = get_kbase_ids_from_alignment_row(alignment_id, row_number)

Given an alignment and a row in the alignment, returns all the kbase_ids of the sequences that compose
the given tree.
Note: may not be needed if this functionality is possible via the ER model

=cut

sub get_kbase_ids_from_alignment_row
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_kbase_ids_from_alignment_row",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_kbase_ids_from_alignment_row: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_kbase_ids_from_alignment_row: " . $self->{client}->status_line;
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

    @args == 4 or die "Invalid argument count (expecting 4)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_trees_with_entire_seq",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_trees_with_entire_seq: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_trees_with_entire_seq: " . $self->{client}->status_line;
    }
}




=head2 $result = get_trees_with_overlapping_seq(sequence, beg, end, options)

Returns all tree IDs in which some portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=cut

sub get_trees_with_overlapping_seq
{
    my($self, @args) = @_;

    @args == 4 or die "Invalid argument count (expecting 4)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_trees_with_overlapping_seq",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_trees_with_overlapping_seq: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_trees_with_overlapping_seq: " . $self->{client}->status_line;
    }
}




=head2 $result = get_trees_with_entire_domain(domain, options)

Returns all tree IDs in which the entire portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on this domain).

=cut

sub get_trees_with_entire_domain
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_trees_with_entire_domain",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_trees_with_entire_domain: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_trees_with_entire_domain: " . $self->{client}->status_line;
    }
}




=head2 $result = get_trees_with_overlapping_domain(domain, options)

Returns all tree IDs in which some portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on a similar domain created
with an alternative method, so the entire domain may not be contained).

=cut

sub get_trees_with_overlapping_domain
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.get_trees_with_overlapping_domain",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking get_trees_with_overlapping_domain: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking get_trees_with_overlapping_domain: " . $self->{client}->status_line;
    }
}




=head2 $result = substitute_node_labels_with_kbase_ids(trees, options)

Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
parameters to be passed (parameter list is also currently not defined yet.)

=cut

sub substitute_node_labels_with_kbase_ids
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.substitute_node_labels_with_kbase_ids",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking substitute_node_labels_with_kbase_ids: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking substitute_node_labels_with_kbase_ids: " . $self->{client}->status_line;
    }
}




=head2 $result = extract_leaf_node_labels(tree)

Given a tree, returns the list of labels of the leaves.  If the 'substitute_node_labels_with_kbase_ids' was already
called to retrieve the trees, then this method will provide a list of kbase_ids indicating the sequences that comprised
the tree.

=cut

sub extract_leaf_node_labels
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.extract_leaf_node_labels",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking extract_leaf_node_labels: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking extract_leaf_node_labels: " . $self->{client}->status_line;
    }
}




=head2 $result = add_node_to_tree(tree_id, sequence_id, options)

Given a tree and a sequence in kbase, attempt to map that sequence onto the tree.  This function could have
multiple prototypes allowing users to compare / build trees with new sequences

=cut

sub add_node_to_tree
{
    my($self, @args) = @_;

    @args == 3 or die "Invalid argument count (expecting 3)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.add_node_to_tree",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking add_node_to_tree: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking add_node_to_tree: " . $self->{client}->status_line;
    }
}




=head2 $result = run_sifter(tree_id, options)

If / when we want to support sifter, we should support a simple function that allows us to run sifter on a given
tree in the DB, in which we pass options giving sifter the particular base annotations to query from.
todo: determine if and how such annotations could be back propogated to the CDM.

=cut

sub run_sifter
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.run_sifter",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking run_sifter: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking run_sifter: " . $self->{client}->status_line;
    }
}




=head2 $result = build_tree_from_sequences(sequences, options)

Not sure if we want this, but would allow users to submit a set of sequences, then build a tree.  Here, options
would support the various alignment options, trimming options, tree-building algorithms.
todo: this isn't well thought out -> do we return alignments as well?  do we support building MSAs?

=cut

sub build_tree_from_sequences
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.build_tree_from_sequences",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking build_tree_from_sequences: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking build_tree_from_sequences: " . $self->{client}->status_line;
    }
}




=head2 $result = build_tree_from_fasta(fasta_files, options)



=cut

sub build_tree_from_fasta
{
    my($self, @args) = @_;

    @args == 2 or die "Invalid argument count (expecting 2)";
    my $result = $self->{client}->call($self->{url}, {
	method => "Trees.build_tree_from_fasta",
	params => \@args,
    });
    if ($result) {
	if ($result->is_error) {
	    die "Error invoking build_tree_from_fasta: " . $result->error_message;
	} else {
	    return $result->result;
	}
    } else {
	die "Error invoking build_tree_from_fasta: " . $self->{client}->status_line;
    }
}




1;
