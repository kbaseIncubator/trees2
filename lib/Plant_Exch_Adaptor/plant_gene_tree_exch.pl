#------------------------------------------------------------------------------#
# Documentation Section                                                        #
#------------------------------------------------------------------------------#

=head1 NAME

plant_gene_tree_exch.pl - transforms plant compara genetree alignment data into KB exchange format.

=head1 SYNOPSIS

 plant_gene_tree_exch.pl [--? --m] --i --j
   --i <input compara gene tree sequences' alignment file>
   --j <input compara newick gene tree file>

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
 perl plant_gene_tree_exch.pl --i=Compara.gene_trees.emf --j=Compara.gene_trees.20.newick
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
GetOptions(\%opts, "help|?|h", "man|m", "i=s", "j=s");
pod2usage(1) if $help;
pod2usage(-verbose => 2) if $man;
my $inFile = $opts{'i'} || die pod2usage(1);
my $inFile2 = $opts{'j'} || die pod2usage(1); # file in newick tree file format

#------------------------------------------------------------------------------#
# Start: plant_gene_tree_exch.pl Main Logic                                    #
#------------------------------------------------------------------------------#

open(A, ">Alignment.tab");
open(AR, ">AlignmentRow.tab");
open(AA, ">AlignmentAttribute.tab");
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
while(<F2>) {
	chomp;
	if(/tree_stable_id:(.*) root_id/) {
		my $id = $1;
		my $tree = <F2>;
		# Fields for Tree.tab file
		# kb-tree-id	 M	 unique kbase id reserved for the tree from ID server: 'kb|tree.XXXXX'
		# kb-aln-id	 M	 the kbase id of the alignment from which this tree was built
		# status	 M	 string indicating if the tree is "active", "superseded" or "bad"
		# data-type	 M	 lowercase string indicating the type of data this tree is built from; we set this to "sequence_alignment" for all alignment-based trees, but we may support "taxonomy", "gene_content" trees and more in the future
		# timestamp	 M	 the time at which this tree was loaded into KBase. Other timestamps can be added to TreeAttribute; the time format is an integer indicating seconds since epoch
		# method	 R	 string that either maps to another object that captures workflows, or is simple alignment method name, e.g. "MOPipeline"
	 	# parameters	 R	 free form string that might be a hash to provide additional tree parameters e.g., the program option values used
		# protocol	 O	 human readable description of the tree, if needed
		# source-db	 M	 the database where this tree originated, eg MO, SEED
		# source-db-tree-id	 M	 the id of this tree in the original database
		my $kbTreeId = $id;                  # Required; unique kbase id reserved for the alignment from ID server
		my $kbAlnId = $id;                   # Required
		my $status = "active";               # Required
		my $dataType = "sequence_alignment"; # Required
		my $ts = time();                     # Required# get the timestamp in seconds since epoch
		my $method = "Compara";              # Recommended
		my $params = "";                     # Recommended
		my $protocol = "";                   # Optional
		my $srcDb = "Gramene";               # Required
		my $srcDbAlnId = $id;                # Required
		print T "kb_tree.$kbTreeId\tkb_aln.$kbAlnId\t$status\t$dataType\t$ts\t$method\t$params\t$protocol\t$srcDb\t$srcDbAlnId\n";
		
		my $fn = "$treedir/$id.tree";
		open(O, ">$fn");
		print O $tree;
		close(O);
		
		print TA "kb_tree.$kbTreeId\trooted\tOutgroup\n";
		print TA "kb_tree.$kbTreeId\tbranch_length\tArbitrary units\n";
		print TA "kb_tree.$kbTreeId\tstyle\tPhylogram\n";
		print TA "kb_tree.$kbTreeId\tFelsenstein 1985\n";
		print TA "kb_tree.$kbTreeId\tcopyright\t.\n";
		
		chomp($tree);
		$tree =~ s/\(//g;
		$tree =~ s/\)//g;
		my @a = split(/,/, $tree);
		foreach my $val (@a) {
			$val =~ s/:.*//g;
			$idmap{$val} = $id;
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
			my $rowId = $arr[2];
			if(!defined($kbAlnId)) {
				$kbAlnId = $idmap{$rowId};
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
	$srcDbAlnId = $kbAlnId;
	print A "kb_aln.$kbAlnId\t$nRows\t$nCols\t$status\t$isConcat\t$seqType\t$ts\t$method\t$params\t$protocol\t$srcDb\t$srcDbAlnId\n";
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
		print AR "kb_aln.$kbAlnId\t$rowNum\t$rowId\t$rowDesc\t$nComp\t$sPos\t$ePos\t$md5\n";
		print AA "kb_aln.$kbAlnId\tseeded_by_src\tGramene\n"; # The database source of a gene family, e.g.PFAM
		print AA "kb_aln.$kbAlnId\tseeded_by_id\t$rowId\n";  # The id to locate the gene family or model from an external database, e.g. PF02574
		print AA "kb_aln.$kbAlnId\tcopyright\t.\n";
	}
	close(O);
}
close(A);
close(AR);
close(AA);