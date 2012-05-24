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



=head2 $result = get_tree(tree_id)

Returns the specified tree in newick format, or an empty string if the tree does not exist.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code
todo: provide a way to automatically replace alignment row ids with kbase ids
todo: provide a way to get meta data about this tree, possibly in a separate function

=cut

sub get_tree
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




=head2 $result = get_trees(tree_ids)

Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
was not found.
Note: this function may not be needed if this functionality is provided by the auto-gen ER code

=cut

sub get_trees
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




=head2 $result = all_tree_ids(isActive)

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




1;
