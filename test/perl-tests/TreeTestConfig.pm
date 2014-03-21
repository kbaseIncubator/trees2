#  Simple module that returns the host and port to use to test the service.  Could be extended to
#  read a config file if more config options are required in the future.
#
#  created Oct 2012 by msneddon

package TreeTestConfig;

use strict;
use warnings;
require Exporter;
our @ISA = qw(Exporter);
our @EXPORT_OK = qw(getURL);


# CHANGE THE HOST AND PORT CONFIGURATION HERE

#my $URL  = "https://kbase.us/services/trees";
my $URL  = "http://127.0.0.1:7047";

sub getURL   { return $URL; }

1;
