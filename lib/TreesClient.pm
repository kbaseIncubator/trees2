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




=head2 $result = get_trees_with_entire_seq(seqence)

Returns all tree IDs in which the entire portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=cut

sub get_trees_with_entire_seq
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




=head2 $result = get_trees_with_entire_seq(sequence, beg, end)



=cut

sub get_trees_with_entire_seq
{
    my($self, @args) = @_;

    @args == 3 or die "Invalid argument count (expecting 3)";
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




=head2 $result = get_trees_with_overlapping_seq(sequence)

Returns all tree IDs in which some portion of the given sequence (which can optionally
include start and end positions of the sequence) is used in the alignment which generates the
tree.

=cut

sub get_trees_with_overlapping_seq
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




=head2 $result = get_trees_with_overlapping_seq(sequence, beg, end)



=cut

sub get_trees_with_overlapping_seq
{
    my($self, @args) = @_;

    @args == 3 or die "Invalid argument count (expecting 3)";
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




=head2 $result = get_trees_with_entire_domain(domain)

Returns all tree IDs in which the entire portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on this domain).

=cut

sub get_trees_with_entire_domain
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




=head2 $result = get_trees_with_overlapping_domain(domain)

Returns all tree IDs in which some portion of the given domain is used in the alignment
which generates the tree (usually the tree will be constructed based on a similar domain created
with an alternative method, so the entire domain may not be contained).

=cut

sub get_trees_with_overlapping_domain
{
    my($self, @args) = @_;

    @args == 1 or die "Invalid argument count (expecting 1)";
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




1;
