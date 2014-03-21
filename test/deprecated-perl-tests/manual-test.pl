


use Bio::KBase::ERDB_Service::Client;
# first get the tree
my $erdb_url = "https://kbase.us/services/erdb_service";
    my $erdb = Bio::KBase::ERDB_Service::Client->new($erdb_url);
    my $rows = $erdb->GetAll('Tree','Tree(id) = ?', ["madeupid"],'Tree(newick)',0);
    
    use Data::Dumper;
    print Dumper($rows)."\n";
    
eval {
use Bio::KBase::Tree::Client;
my $client = Bio::KBase::Tree::Client->new("http://localhost:50000");

my $res = $client->get_tree("madeupid",{});
print "old client res:".Dumper($res)."\n";
};
    
use Bio::KBase::KBaseTrees::Client;
my $client2 = Bio::KBase::KBaseTrees::Client->new("http://localhost:7047");

my $res2 = $client2->get_tree("madeupid",{});
print "new client res:".Dumper($res2)."\n";