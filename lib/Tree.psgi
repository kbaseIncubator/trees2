use TreeImpl;

use Bio::KBase::Tree::Service;



my @dispatch;

{
    my $obj = TreeImpl->new;
    push(@dispatch, 'Tree' => $obj);
}


my $server = Bio::KBase::Tree::Service->new(instance_dispatch => { @dispatch },
				allow_get => 0,
			       );

my $handler = sub { $server->handle_input(@_) };

$handler;
