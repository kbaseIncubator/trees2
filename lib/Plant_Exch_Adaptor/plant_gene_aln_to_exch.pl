#------------------------------------------------------------------------------#
# Documentation Section                                                        #
#------------------------------------------------------------------------------#

=head1 NAME

plant_gene_aln_to_exch.pl - transforms plant compara genetree alignment data into KB exchange format.

=head1 SYNOPSIS

 plant_gene_aln_to_exch.pl [--? --m] --i
   --i <input compara gene tree sequences' alignment file>

=head1 DESCRIPTION

This scripts accepts ensembl compara pipeline generated gramene tree alignemnt file
and transforms it into the exchange format required by the KBase trees.

=head1 OPTIONS

=over 4

=item B<--i>

        Required parameter to input plant genetree alignment file from Gramene (e.g.
        Compara.gene_trees.emf) which is based on ensembl compara pipeline.

=item B<--help or -? or -h>

        Print quick help message on using plant_gene_aln_to_exch.pl and exit

=item B<--man or -m>

        Print complete documentation on using plant_gene_aln_to_exch.pl and exit

=back

=head1 AUTHOR

Dr. Sunita Kumari

=head1 DATE

Fri Sep 6 14:15:09 EST 2013

=head1 USAGE EXAMPLE

How to use plant_gene_aln_to_exch.pl
 perl plant_gene_aln_to_exch.pl --i=Compara.gene_trees.emf
 perl plant_gene_aln_to_exch.pl --m # use this option only to read the documentation

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
GetOptions(\%opts, "help|?|h", "man|m", "i=s");
pod2usage(1) if $help;
pod2usage(-verbose => 2) if $man;
my $inFile = $opts{'i'} || die pod2usage(1);

#------------------------------------------------------------------------------#
# Start: plant_gene_aln_to_exch.pl Main Logic                                  #
#------------------------------------------------------------------------------#

open(A, ">Alignment.tab");
open(AR, ">AlignmentRow.tab");
my $kbPre = "kb_aln.";
my $alnFileCtr = 0;
$/ = "//\n";  # change default record separator.
my $dir = undef;
opendir(D, ".");
while(my $file = readdir(D)) {
	if($file eq "Raw_Alignment_Files") {
		$dir = $file;
		last;
	}
}
if(!defined($dir)) {
	$dir = "./Raw_Alignment_Files";
	make_path($dir) || die "Failed to create $dir directory\n";
}
open(IN, "<$inFile");

while(<IN>) {
	chomp;
	$alnFileCtr++;
	# Fields for Alignment.tab file
	# kbaseAlignmentID	nRows	nCols	status	concat	seqType	Timestamp	method-method-method-met	params	protocol	sourceDb	sourceDbAlignmentId
	# kb|aln.1024572	521	54	active	0	Protein	1359668460	FastBLAST-topHomologs.pl	<\s>	MO Pipeline	MOL:Tree	13910730_1
	my $kbAlnId = $kbPre.$alnFileCtr; # Required; unique kbase id reserved for the alignment from ID server
	my $nRows = 0;                   # Required
	my $nCols = 0;                   # Recommended
	my $status = "active";           # Required
	my $isConcat = 0;                # Required
	my $seqType = "DNA";             # Required
	my $ts = time();                 # Required# get the timestamp in seconds since epoch
	my $method = "ComparaPipeline";  # Recommended
	my $params = "";                 # Recommended
	my $protocol = "";               # Optional
	my $srcDb = "Gramene";           # Required
	my $srcDbAlnId = "TODO";         # Required
	
	# Fields for AlignmentRow.tab file
	# kbase-align-id	row-num	row-identifier	row-description	n-com	sPos	ePos	md5-of-ungapped-seq
	# kb|aln.1024572	1	20778842_1_179_232	<\s>	1	1	54	a744a22e9d33a95a5c87bb08676c56b5
	my $rowNum;    # Required; row number in the alignment file, count starts at '1'
	my $rowId;     # Required; must be unique in the alignment & must also be the first word in the aln fasta file.
	my $rowDesc;   # Optional
	my $nComp = 1; # Required; number of components (e.g. concatenated seqs) that correspond to the alignment row
	my $sPos;      # Required; column (index starting at '1') in the alignment where seq begins; ignore leading gap
	my $ePos;      # Required; column (index starting at '1') in the alignment where seq ends; ignore trailing gap
	my $md5;       # Required; the MD5 (uppercase) of the aligned sequence on this row with gaps stripped
	
	my @aln = split(/\n/, $_);
	my %rMap = (); # key={0..(n-1)rows}; value={species-transcriptId-chr-start-end}
	my %aln = ();  # sequence alignment data
	foreach my $l (@aln) {
		if($l =~ m/^SEQ/) {
			# SEQ oryza_brachyantha OB03G46150.1 3 28096381 28098204 -1 OB03G46150 OB03G46150
			my @a = split(/\s/,$l);
			my $rowId = "";
			for(my $i = 1; $i < 6; $i++) {
				if(length($rowId) == 0) {
					$rowId = $a[$i];
				} else {
					$rowId .= "-".$a[$i];
				}
			}
			$rMap{$nRows} = $rowId;
			++$nRows;
		} elsif($l =~ m/^DATA/) {
			# initialize alignment sequence map
			for(my $i = 0; $i < $nRows; $i++) {
				$seq{$i} = "";
			}
		} else {
			# Alignment data: -CTGG---GACCCATGTAC-G-C-GT-CCCCTGCCT---AT--GGT-------AC-CCC--C-
			for(my $i = 0; $i < $nRows; $i++) {
				$aln{$i} = $aln{$i}.substr($l, $i, 1);
			}
			$nCols++;
		}
	}
	print A "$kbAlnId\t$nRows\t$nCols\t$status\t$isConcat\t$seqType\t$ts\t$method\t$params\t$protocol\t$srcDb\t$srcDbAlnId\n";
	my $fn = "$dir/$kbPre$alnFileCtr.fasta";
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
	}
	close(O);
}
close(A);
close(AR);