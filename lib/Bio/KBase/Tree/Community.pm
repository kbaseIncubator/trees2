=head1 NAME

Bio::KBase::Tree::Community

=head1 DESCRIPTION


Methods exposed by the Tree service which perform functionality for microbial communities and
metagenomic analysis.


created 1/31/2013 - msneddon

=cut

package Bio::KBase::Tree::Community;

use strict;
use warnings;
use Data::Dumper;
use Benchmark;

use JSON;
use LWP::UserAgent;
use File::Temp;
use Digest::MD5 qw(md5_hex);

use Bio::KBase::ERDB_Service::Client;

#
# create a new Community object with the URLs and scratch space specified.  The order
# of arguments is 1) erdb URL, 2) communities base URL (including 'sequence') and 3)
# path to scratch space.
#
sub new
{
    my($class, @args) = @_;
    my $self = {};
    bless $self, $class;
    
    my $n_args=scalar(@args);
    if($n_args==0) {
	$self->{erdb} = Bio::KBase::ERDB_Service::Client->new("http://kbase.us/services/erdb_service");
	$self->{mg_base_url} = "http://api.metagenomics.anl.gov/sequences/";
	$self->{scratch} = "/mnt/";
    } else {
	if($n_args!=3) {
	    die "Incorrect number of arguments passed to Bio::KBase::Tree::Community!\n";
	}
	$self->{erdb} = Bio::KBase::ERDB_Service::Client->new($args[0]);
	$self->{mg_base_url} = $args[1];
	$self->{scratch} = $args[2];
    }
    
    return $self;
}



#
#  run uclust and parse the results for the given parameters
#    this method accepts a single argument, a ref to a hash with the following keys defined:
#       protFamName => the ID of the protein family
#       protFamSource => the type of protein family, only COG is supported currently
#       mgId => the ID of the metagenomic sample to operate on
#
#
sub runQiimeUclust {
    my $self=shift;
    my $params=shift;
    my $protFamName=$params->{'protein_family_name'};
    my $protFamSource=$params->{'protein_family_source'};
    my $mgId=$params->{'metagenomic_sample_id'};
    my $treeId=$params->{'tree_id'};
    # may be possible to lookup tree based on protein family name and type, but this is not yet implemented
    #my $treeId = $self->findKBaseTreeByProteinFamilyName($params);
    my $percentIdentityThreshold=$params->{'percent_identity_threshold'};
    my $sequenceLengthThreshold=$params->{'match_length_threshold'};
    
    # step 1 - get the isolate sequences used to build the tree, and save it to a fasta file
    my $isolate_seq_list = $self->getTreeProtSeqList($treeId);
    my $isolate_seq_fasta = $self->convertSeqListToFasta($isolate_seq_list);
    my $isolate_seq_file_name_template = $self->{scratch}.$protFamName.'.XXXXXX';
    my $isoFh = new File::Temp(TEMPLATE=>$isolate_seq_file_name_template,SUFFIX=>'.faa',UNLINK=>1);
    print $isoFh $isolate_seq_fasta;
    close $isoFh;
	
    # step 2 - query the communities service to get metagenomic reads for the specified mgId, save the reads to a fasta file
    # note: here we make sure we only save a single copy of each unique read sequence, and count the total number of each
    # unique read, then encode that in the ID of the read
    my $mg_seq_list = $self->getMgSeqsByProtFam($params);
    my $unique_mg_seq_list = $self->getUniqueSequenceList($mg_seq_list);
    my $mg_seq_fasta = $self->convertSeqListToFasta($unique_mg_seq_list);
    my $mg_seq_file_name_template = $self->{scratch}.$mgId.'.XXXXXX';
    my $mgFh = new File::Temp(TEMPLATE=>$isolate_seq_file_name_template,SUFFIX=>'.faa',UNLINK=>1);
    print $mgFh $mg_seq_fasta;
    close $mgFh;
    
    
    # step 3 - prep the output file, make the system call, handle errors if the run failed
    my $uclust_out_file_name_template = $self->{scratch}.$protFamName."-".$mgId.'.XXXXXX';
    my $uclustFh=new File::Temp (TEMPLATE=>$uclust_out_file_name_template,SUFFIX=>'.uc',UNLINK=>1);
    close $uclustFh;
    my $uclustProcOut=`uclust --quiet --amino --libonly --id 0.70 --input $mgFh --lib $isoFh --uc $uclustFh`;
    my $exit_code = ($? >> 8);
    if($exit_code!=0) {
	die 'ERROR RUNNING UCLUST';
    }
    
    
    # step 4 - parse and return the results
    my ($abundance_counts,$n_hits,$n_query_seqs) = $self->computeAbundancesFromUclustOut($uclustFh,$percentIdentityThreshold,$sequenceLengthThreshold);
    
    #print "could map ".$n_hits." of ".$n_query_seqs."\n";
    #foreach my $isolate_seq (@$isolate_seq_list) {
#	$abundance_counts->{$isolate_seq->[0]} = 0;
 #   }

    return ($abundance_counts,$n_hits,$n_query_seqs);
}





#  search kbase for a tree built from a protein family that is specified
#    this method accepts a single argument, a ref to a hash with the following keys defined:
#       protFamName => the ID of the protein family
#       protFamSource => the type of protein family, only COG is supported currently
sub findKBaseTreeByProteinFamilyName {
    my $self=shift;
    my $params=shift;
    
    # perform some kind of erdb query here
    my $protFamName=$params->{'protein_family_name'};
    my $protFamSource=$params->{'protein_family_source'};
    
    # return the tree
    #return "kb|tree.0";
    #return "kb|tree.2173";
    return "kb|tree.18428";
}



# given a reference to a list of sequences, return a FASTA encoding as a string
# the list should be formatted as so:
#   [
#      [ seqId1, seq1],
#      [ seqId2, seq2],
#      ...
#      [ seqIdN, seqN]
#   ];
#
# note that this is the default output format of the getTreeProtSeqs method
sub convertSeqListToFasta {
    my $self=shift;
    my $seq_list=shift;
    
    my $fasta_encoding = '';
    foreach my $seq (@$seq_list) {
	$fasta_encoding .= '>'.$seq->[0]."\n";
	$fasta_encoding .= $seq->[1]."\n";
    }
    return $fasta_encoding;
}



# given a kbase tree id, get all the sequences that comprise the alignment that was
# used to build the tree.  call this method as: $c->getTreeProtSeqs("kb|tree.0");
sub getTreeProtSeqList {
    my $self=shift;
    my $treeId=shift;
    
    # grab the erdb service, which we need for queries
    my $erdb = $self->{'erdb'};

    # get the sequences that comprise the alignment that was used to build the tree
    my $objectNames = 'ProteinSequence IsAlignedProteinComponentOf AlignmentRow IsAlignmentRowIn Alignment IsUsedToBuildTree';
    my $filterClause = 'IsUsedToBuildTree(to-link)=?';
    my $parameters = [$treeId];
    my $fields = 'AlignmentRow(row-id) AlignmentRow(sequence)';
    my $count = 0; #as per ERDB doc, setting to zero returns all results
    my @tree_seq_list = @{$erdb->GetAll($objectNames, $filterClause, $parameters, $fields, $count)};
	
    # remove gap characters from the sequences
    foreach my $row (@tree_seq_list) {
	$row->[1] =~ s/-//g;
    }
    
    return \@tree_seq_list;
}



# given a raw list of sequences, this method eliminates repeats (based on MD5), but counts the number of
# sequences
#   raw sequence information should be passed in a list like so:
#   [
#      [ seqId1, seq1],
#      [ seqId2, seq2],
#      ...
#      [ seqIdN, seqN]
#   ];
#
#  results are returned in the same structure, with seqIDs in the form "MD5_[md5]_[n]" where [md5] is the MD5
#  of the unique sequence, and [n] is the number of matching reads.  The sum over all n, therefore, should be
#  equal to the total number of raw input sequences
sub getUniqueSequenceList {
    my $self=shift;
    my $raw_sequence_list=shift;
    
    # identify unique sequences
    my $unique_seqs = {}; my $unique_seq_counts = {};
    foreach my $seq (@$raw_sequence_list) {
	my $md5 = md5_hex($seq->[1]);
	if(exists($unique_seqs->{$md5})) { $unique_seq_counts->{$md5}++; }
	else {
	    $unique_seq_counts->{$md5} = 1;
	    $unique_seqs->{$md5} = $seq->[1];
	}
    }
    #print Dumper($unique_seq_counts)."\n";
    
    # save the unique sequences to a list that we can return
    my $unique_sequence_list = [];
    foreach my $md5 (keys %$unique_seqs) {
	my $row = [$md5."_".$unique_seq_counts->{$md5},$unique_seqs->{$md5}];
	push @$unique_sequence_list, $row;
    }
    return $unique_sequence_list;
}


#
# Fetches metagenomic reads from MG rast
# call with one argument, which is a reference to a hash with the following keys defined:
#  protFamName => the ID of the protein family
#  protFamSource => the type of protein family, only COG is supported currently
#  mgId => the ID of the metagenomic sample to operate on
#
# returns the json response of the query, or empty string if nothing was returned
#
sub getMgSeqsByProtFam {
    my $self=shift;
    my $params=shift;
    
    my $protFamName=$params->{'protein_family_name'};
    my $protFamSource=$params->{'protein_family_source'};
    my $mgId=$params->{'metagenomic_sample_id'};
    
    my $base_url = $self->{mg_base_url};
    my $full_url=$base_url.$mgId."/?type=ontology&seq=protein&function=".$protFamName."&source=".$protFamSource;
    
    my $ua = LWP::UserAgent->new;
    $ua->timeout(10);
    
    my $response=$ua->get($full_url);
    if ($response->is_success) {
	my $json = new JSON;
	my $mgSeqs=$json->decode($response->content);
	return $mgSeqs->{$protFamName};
    }
    return [];
}


#
# Given the full path name of the uclust output file, this method computes abundance counts.  It accepts and requires
# three args, the name of the file, a threshold for percent identity for the hit, and a threshold for the sequence
# length, in that order.  Thresholds are interpreted as greater than or equal to.   Percent identity is given as a
# percent, not a fraction (e.g use 87.5 to specify 87.5% identity, NOT 0.875!). This method returns three
# values in a list.  The first element is a ref to a hash where the keys are the names of the target sequences, and
# the value is the number of hits. The second element is the total number of hits.  The last element is the total
# number of query read sequences that were run.
#   ex. my ($abundance_counts,$n_hits,$n_query_seqs) = $c->computeAbundancesFromUclustOut("uclust.out",90,20);
#
sub computeAbundancesFromUclustOut {
    my $self=shift;
    my $results_file_name=shift;
    my $percentIdentityThreshold=shift;
    my $sequenceLengthThreshold=shift;
    
    my $abundance_counts = {};
    
    my $hit_counter=0;  my $seq_counter=0;
    open (my $QIIME_UCLUST_OUT, $results_file_name);
    while (my $line = <$QIIME_UCLUST_OUT>) {
	chomp $line;
	next if ($line !~ /^(H|N)/); # skip everything except for hits and nonhits
	
	my ($Type, $ClusterNr, $SeqLength, $PctId, $Strand, $QueryStart, $SeedStart, $Alignment, $QueryLabel, $TargetLabel) = split (/\t/, $line);
	my ($md5,$n_seq) = split(/_/,$QueryLabel);
	
	#print $md5." ---- ".$n_seq."\n";
	$seq_counter += $n_seq;
	
	# if we found a hit, check if it meets our threshold constraints, then count it.
	if( ($Type eq 'H') && ($PctId >= $percentIdentityThreshold) && ($SeqLength >= $sequenceLengthThreshold) ) {
	    $hit_counter += $n_seq;
	    if(exists($abundance_counts->{$TargetLabel})) {
		$abundance_counts->{$TargetLabel} += $n_seq;
	    } else {
		$abundance_counts->{$TargetLabel} = $n_seq;
	    }
	}
    }
    close ($QIIME_UCLUST_OUT);
    return ($abundance_counts, $hit_counter, $seq_counter);
}




1;