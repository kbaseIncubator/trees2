use TreesImpl;
use TreesServer;



my $impl_obj = TreesImpl->new;

my $server = TreesServer->new(instance_dispatch => { 'Trees' => $impl_obj },

				allow_get => 0,
			       );

my $handler = sub { $server->handle_input(@_) };

$handler;
