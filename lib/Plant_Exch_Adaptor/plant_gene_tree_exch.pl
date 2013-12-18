#------------------------------------------------------------------------------#
# Documentation Section                                                        #
#------------------------------------------------------------------------------#

=head1 NAME

plant_gene_tree_exch.pl - transforms plant compara genetree alignment data into KB exchange format.

=head1 SYNOPSIS

 plant_gene_tree_exch.pl [--? --m] --i --j --k
   --i <input compara gene tree sequences' alignment file>
   --j <input compara newick gene tree file>
   --k <input KBase id file>

=head1 DESCRIPTION

This scripts accepts ensembl compara pipeline generated gramene tree alignemnt file
and transforms it into the exchange format required by the KBase trees.

=head1 OPTIONS

=over 4

=item B<--i>

        Required parameter to input plant genetree alignment file from Gramene (e.g.
        Compara.gene_trees.emf) which is based on ensembl compara pipeline.

=item B<--j>

        Required parameter to input plant genetree file from Gramene in newick format
         (e.g. Compara.gene_trees.20.newick) which is based on ensembl compara pipeline.

=item B<--k>
	Required parameter to input KBase IDs for gene trees. The input file
	must be a single column of data where each line contains a unique KBase
	id (a positive integer) that had been issued by the KBase ID server.

=item B<--help or -? or -h>

        Print quick help message on using plant_gene_tree_exch.pl and exit

=item B<--man or -m>

        Print complete documentation on using plant_gene_tree_exch.pl and exit

=back

=head1 AUTHOR

Dr. Sunita Kumari

=head1 DATE

Fri Sep 6 14:15:09 EST 2013

=head1 USAGE EXAMPLE

How to use plant_gene_tree_exch.pl
 perl plant_gene_tree_exch.pl --i=Compara.gene_trees.emf --j=Compara.gene_trees.20.newick --k=kbid.in
 perl plant_gene_tree_exch.pl --m # use this option only to read the documentation

=cut

#------------------------------------------------------------------------------#
# Import Section: Import perl modules on which this script depends             #
#------------------------------------------------------------------------------#
use Getopt::Long;
use Pod::Usage;
use warnings;
use Digest::MD5 qw(md5 md5_hex md5_base64);
use File::Path qw(make_path);

#------------------------------------------------------------------------------#
# Boiler plate code for extracting command-line options & displaying help/man  #
#------------------------------------------------------------------------------#

my $help;
my $man;
my %opts = ("help" => \$help, "man" => \$man);
Getopt::Long::Configure("bundling", "auto_abbrev");
GetOptions(\%opts, "help|?|h", "man|m", "i=s", "j=s", "k=s");
pod2usage(1) if $help;
pod2usage(-verbose => 2) if $man;
my $inFile = $opts{'i'} || die pod2usage(1);
my $inFile2 = $opts{'j'} || die pod2usage(1); # file in newick tree file format
my $kbidFile = $opts{'k'} || die pod2usage(1);
#------------------------------------------------------------------------------#
# Start: plant_gene_tree_exch.pl Main Logic                                    #
#------------------------------------------------------------------------------#

open(IN, "<$kbidFile");
my @kbid = ();
while(<IN>) {
	chomp;
	push(@kbid,$_);
}
close(IN);

open(A, ">Alignment.tab");
open(AR, ">AlignmentRow.tab");
open(AA, ">AlignmentAttribute.tab");
open(AN, ">ContainsAlignedNucleotides.tab");
open(T, ">Tree.tab");
open(TA, ">TreeAttribute.tab");

my $fastadir = undef;
my $treedir = undef;
opendir(D, ".");
while(my $file = readdir(D)) {
	if($file eq "Raw_Alignment_Files") {
		$fastadir = $file;
	} elsif($file eq "Raw_Tree_Files") {
		$treedir = $file;
	}
}
if(!defined($fastadir)) {
	$fastadir = "./Raw_Alignment_Files";
	make_path($fastadir) || die "Failed to create $fastadir directory\n";
}
if(!defined($treedir)) {
	$treedir = "./Raw_Tree_Files";
	make_path($treedir) || die "Failed to create $treedir directory\n";
}

open(F2, "<$inFile2");
my %idmap = ();
my %stableId = ();
while(<F2>) {
	chomp;
	if(/tree_stable_id:(.*) root_id/) {
		my $id = shift @kbid;                # positive integer part of the KBase id
		my $tree = <F2>;
		# Fields for Tree.tab file
		my $kbTreeId = "kb|tree.".$id;       # Required; unique kbase id reserved for the alignment from ID server
		my $kbAlnId = "kb|aln.".$id;         # Required
		$stableId{$kbAlnId} = $1;            # $1=Ensembl-Tree-StableID e.g. EPlGT00140000000068
		my $status = "active";               # Required
		my $dataType = "sequence_alignment"; # Required
		my $ts = time();                     # Required# get the timestamp in seconds since epoch
		my $method = "Compara";              # Recommended
		my $params = "";                     # Recommended
		my $protocol = "";                   # Optional
		my $srcDb = "Gramene";               # Required
		my $srcDbAlnId = $1;                 # Required
		print T "$kbTreeId\t$kbAlnId\t$status\t$dataType\t$ts\t$method\t$params\t$protocol\t$srcDb\t$srcDbAlnId\n";
		
		my $fn = "$treedir/$kbTreeId.newick";
		open(O, ">$fn");
		print O $tree;
		close(O);
		
		print TA "$kbTreeId\trooted\tOutgroup\n";
		print TA "$kbTreeId\tbranch_length\tArbitrary units\n";
		print TA "$kbTreeId\tstyle\tPhylogram\n";
		print TA "$kbTreeId\tbootstrap_type\tFelsenstein 1985\n";
		#print TA "$kbTreeId\tcopyright\tNone\n";
		
		chomp($tree);
		$tree =~ s/\(//g;
		$tree =~ s/\)//g;
		my @a = split(/,/, $tree);
		foreach my $val (@a) {
			$val =~ s/:.*//g;
			$idmap{$val} = $kbAlnId;
		}
	}
}

close(F2);
close(T);
close(TA);

$/ = "//\n";  # change default record separator.

open(IN, "<$inFile");
while(<IN>) {
	chomp;
	# Fields for Alignment.tab file
	# kbaseAlignmentID	nRows	nCols	status	concat	seqType	Timestamp	method-method-method-met	params	protocol	sourceDb	sourceDbAlignmentId
	# kb|aln.1024572	521	54	active	0	Protein	1359668460	FastBLAST-topHomologs.pl	<\s>	MO Pipeline	MOL:Tree	13910730_1
	my $kbAlnId = undef;     # Required; unique kbase id reserved for the alignment from ID server
	my $nRows = 0;           # Required
	my $nCols = 0;           # Recommended
	my $status = "active";   # Required
	my $isConcat = 0;        # Required
	my $seqType = "DNA";     # Required
	my $ts = time();         # Required# get the timestamp in seconds since epoch
	my $method = "Compara";  # Recommended
	my $params = "";         # Recommended
	my $protocol = "";       # Optional
	my $srcDb = "Gramene";   # Required
	my $srcDbAlnId = undef;  # Required
	
	# Fields for AlignmentRow.tab file
	# kbase-align-id	row-num	row-identifier	row-description	n-com	sPos	ePos	md5-of-ungapped-seq
	# kb|aln.1024572	1	20778842_1_179_232	<\s>	1	1	54	a744a22e9d33a95a5c87bb08676c56b5
	my $rowNum;          # Required; row number in the alignment file, count starts at '1'
	my $rowId;           # Required; must be unique in the alignment & must also be the first word in the aln fasta file.
	my $rowDesc = ".";   # Optional
	my $nComp = 1;       # Required; number of components (e.g. concatenated seqs) that correspond to the alignment row
	my $sPos;            # Required; column (index starting at '1') in the alignment where seq begins; ignore leading gap
	my $ePos;            # Required; column (index starting at '1') in the alignment where seq ends; ignore trailing gap
	my $md5;             # Required; the MD5 (uppercase) of the aligned sequence on this row with gaps stripped
	
	my @aln = split(/\n/, $_);
	my %rMap = (); # key={0..(n-1)rows}; value={EnsemblTreeId_GeneId}
	my %aln = ();  # sequence alignment data
	foreach my $l (@aln) {
		if($l =~ m/^SEQ/) {
			# SEQ oryza_brachyantha OB03G46150.1 3 28096381 28098204 -1 OB03G46150 OB03G46150
			my @arr = split(/\s/,$l);
			my $rowId = $arr[2]; # external Gene ID
			if(!defined($kbAlnId)) {
				$kbAlnId = $idmap{$rowId}; # This is KBase alignment id (e.g. kb|aln.1111222)
				if(!defined($kbAlnId)) {
					print STDOUT "$l\n$rowId exists in alignment but not in tree file\n";
				}
			}
			if($kbAlnId ne $idmap{$rowId}) {
				print STDOUT "$l\nFor $rowId expected id $kbAlnId but found $idmap{$rowId}\n";
				#exit(1);
			}
			$rMap{$nRows} = $kbAlnId."_".$rowId;
			++$nRows;
		} elsif($l =~ m/^DATA/) {
			# initialize alignment sequence map
			#for(my $i = 0; $i < $nRows; $i++) {
			#	$seq{$i} = "";
			#}
		} else {
			# Alignment data: -CTGG---GACCCATGTAC-G-C-GT-CCCCTGCCT---AT--GGT-------AC-CCC--C-
			for(my $i = 0; $i < $nRows; $i++) {
				if(!defined($aln{$i})) {
					$aln{$i} = substr($l, $i, 1);
				} else {
					$aln{$i} = $aln{$i}.substr($l, $i, 1);
				}
			}
			$nCols++;
		}
	}
	$srcDbAlnId = $stableId{$kbAlnId};
	print A "$kbAlnId\t$nRows\t$nCols\t$status\t$isConcat\t$seqType\t$ts\t$method\t$params\t$protocol\t$srcDb\t$srcDbAlnId\n";
	my $fn = "$fastadir/$kbAlnId.fasta";
	open(O, ">$fn");
	foreach my $k (sort {$a <=> $b} (keys(%rMap))) {
		my $seq = $aln{$k};
		$rowId = $rMap{$k};
		print O ">$rowId\n$seq\n";
		my $idx = 0;
		my $seqLen = length($seq);
		while(substr($seq, $idx, 1) eq '-') {
			$idx++;
		}
		$sPos = $idx+1;
		$idx = -1;
		while(substr($seq, $idx, 1) eq '-') {
			$idx--;
		}
		$ePos = $seqLen+$idx+1;
		$rowNum = $k+1;
		$seq =~ s/-//g;
		$md5 = md5_hex($seq);
		print AR "$kbAlnId\t$rowNum\t$rowId\t$rowDesc\t$nComp\t$sPos\t$ePos\t$md5\n";
		print AN "$kbAlnId\t$rowNum\t1\t$md5\t1\t$seqLen\t$seqLen\t$sPos\t$ePos\t.\n";
		print AA "$kbAlnId\tseeded_by_src\tGramene\n"; # The database source of a gene family, e.g.PFAM
		print AA "$kbAlnId\tseeded_by_id\t$rowId\n";  # The id to locate the gene family or model from an external database, e.g. PF02574
		#print AA "$kbAlnId\tcopyright\tNone\n";
	}
	close(O);
}
close(A);
close(AR);
close(AA);
