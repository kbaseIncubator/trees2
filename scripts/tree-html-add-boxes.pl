#!/usr/bin/env perl
use strict;
use warnings;
use Getopt::Long;
use Data::Dumper;

#use Bio::KBase::Tree::Client;
#use Bio::KBase::Tree::Util qw(get_tree_client);

my $DESCRIPTION =
"
NAME
      tree-html-add-boxes -- add data values to tree leaves

SYNOPSIS
      tree-html-add-boxes [OPTIONS]

DESCRIPTION
      Modify html tree to include data values for each leaf as a heatmap.  Labels are justified if not already aligned.  Html tree input is taken as STDIN or as a command line arg. Modified tree is STDOUT.

      -d, --data-file
                        Required. Give the path to the data associated with each leaf. Only one value per leaf is considered. If require multiple data sets, rerun method with new data file and additional column will be appended. Data file format is tab-delimited, one row per leaf with the leaf ID as the first column and the data value as the second column.

      -t, --tree-html-file
                        Optional. Give the path to the html tree file, or provide as STDIN.

      -c, --color-scheme
                        Optional. Configure the color scheme for the heatmap. May be 'YB' for blue-black-yellow spectrum or 'RYB' for blue-yellow-red spectrum.
                        
      -h, --help
                        diplay this help message, ignore all arguments
                        
EXAMPLES
      Add the data from data file 'leaf_data_set1.txt' to html tree file 'tree1.html' using default RYB color scheme
      > cat tree1.html | tree-html-add-boxes -d leaf_data_set1.txt > tree1_with_data1.html
      
      Add the data from data file 'leaf_data_set2.txt' to previous output with color scheme 'YB'
      > tree-html-add_boxes -d leaf_data_set2.txt -t tree1_with_with_data1.html -c YB > tree1_with_data1+2.html
      
SEE ALSO
      tree-to-html
      tree-html-relabel-leaves

AUTHORS
      Dylan Chivian (dcchivian\@lbl.gov)
      
";

my $help = '';
my $data_file = '';
my $tree_html_file = '';
my $color_scheme = '';
my $opt = GetOptions (
    "help" => \$help,
    "data-file=s" => \$data_file,
    "tree-html-file=s" => \$tree_html_file,
    "color-scheme=s" => \$color_scheme
    );

if ($help) {
    print $DESCRIPTION;
    exit 0;
}


# prepare key
#
my @spectrum_boxes = ();
for (my $i=0; $i <= 16; $i += 2) {
    my $box_color = &getHexColor ($i, 16, $color_scheme);
    push (@spectrum_boxes, '<font color=#'.$box_color.'>&#9608;</font>');
}
my $spectrum = join ('&nbsp;', @spectrum_boxes);
my @alpha = qw (A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9 ~ ! @ $ % ^ & * + = { } | [ ] \ : ; " ' < > ?);


# Read html tree
#
my @tree_html_buf = ();
if ($tree_html_file) {
    my $tree_html_file_handle;
    open ($tree_html_file_handle, '<', $tree_html_file);
    if (! $tree_html_file_handle) {
	print STDERR "FAILURE - cannot open '$tree_html_file'\n$!\n";
	exit 1;
    }
    push (@tree_html_buf, $_) while (<$tree_html_file_handle>);
    close ($tree_html_file_handle);
} else {
    push (@tree_html_buf, $_) while (<STDIN>);
}

my $data_set_name = $data_file;
$data_set_name =~ s!^.*/!!;
$data_set_name =~ s!\.[^\.]*$!!;


# Read abundance info
#
my %leaf_data = ();
my $min_val =  10000000000;
my $max_val = -10000000000;
my @data_buf = ();
my $data_file_handle;
open ($data_file_handle, '<', $data_file);
if (! $data_file_handle) {
    print STDERR "FAILURE - cannot open '$data_file'\n$!\n";
    exit 1;
}
push (@data_buf, $_)  while (<$data_file_handle>);
close ($data_file_handle);
foreach my $line (@data_buf) {
    chomp $line;
    my ($id, $val) = split (/\t/, $line);
    $leaf_data{$id} = $val;
    $min_val = $val  if ($val < $min_val);
    $max_val = $val  if ($val > $max_val);
}
my $range = $max_val - $min_val;


# Filter html tree to remove color, add boxes, and justify labels
#
my $big_tree_chunk = 0;

my $in_pre = undef;
foreach my $line (@tree_html_buf) {
    if (! $in_pre) {
	if ($line =~ /^\<pre\>/) {
	    $in_pre = 'true';
	}
    }
    else {
	if ($line =~ /^\<\/pre\>/) {
	    $in_pre = undef;
	    next;
	}

	next if ($line =~ /^\s*$/);
	next if ($line =~ /^\s*\<a name="key"\>/);
	next if ($line =~ /^\s*\<\/?table[^\>]*\>/);
	next if ($line =~ /^\s*\<tr\>/);

	my ($tree_chunk) = split (/\s+/, $line);
	next if ($tree_chunk =~ /\<font$/);                    # already justified
	my $len_tree_chunk = $#{[split(/\&/, $tree_chunk)]};
	$big_tree_chunk = $len_tree_chunk  if ($len_tree_chunk > $big_tree_chunk);
    }
}

$in_pre = undef;
my ($col_i, $col_a, $line_copy, $new_key_html, $odd_even_adjust) = ();
my ($seen_key, $remove_header) = (undef, undef);
my $col_header_line = '';
my ($id, $box_color, $box, $tree_chunk, $one_more_space, $extra_spaces, $label_html, $len_tree_chunk) = ();
foreach my $line (@tree_html_buf) {
    if (! $in_pre) {
	if ($line =~ /^\<pre\>/) {
	    $in_pre = 'true';
	}
	print $line;
    }
    else {
	if ($line =~ /^\<\/pre\>/) {
	    $in_pre = undef;
	    print $line;
	    next;
	}

	# add key
	if (! $seen_key) {
	    if ($line =~ /^\s*\<a name="key"\>/) {
		$col_i = 0;
		$line_copy = $line;
		++$col_i  while ($line_copy =~ s/\<tr\>//);
		
		$col_a = $alpha[$col_i];
		$new_key_html = qq{<tr><td>$col_a</td><td>$data_set_name</td><td>$min_val</td><td>$spectrum</td><td>$max_val</td></tr>};
		$line =~ s/\<\/table>\s*$/$new_key_html\<\/table\>/;
		print $line."\n";

		$seen_key = 'true';
		$remove_header = 'true';
		next;
	    }
	    else {
		$col_i = 0;
		$new_key_html = qq{<tr><td>A</td><td>$data_set_name</td><td>$min_val</td><td>$spectrum</td><td>$max_val</td></tr>};
		print qq{<a name="key"><table border=0 cellspacing=5 cellpadding=0>$new_key_html</table>\n};

		$seen_key = 'true';

		$odd_even_adjust = ((($big_tree_chunk+1) % 2) == 0) ? 1 : 0;
		$col_header_line = '&nbsp;'x($big_tree_chunk+$odd_even_adjust).' ';
		for (my $label_i=0; $label_i <= $col_i; ++$label_i) {
		    $col_header_line .= ' '.$alpha[$label_i];
		}
		print "$col_header_line\n";
		$remove_header = undef;
	    }
	}

	if ($remove_header) {
	    $col_header_line = '&nbsp;'x$big_tree_chunk.' ';
	    for (my $label_i=0; $label_i <= $col_i; ++$label_i) {
		$col_header_line .= ' '.$alpha[$label_i];
	    }
	    print "$col_header_line\n";
	    $remove_header = undef;
	    next;
	}


	next if ($line =~ /^\s*$/);
	next if ($line =~ /^\s*\<\/?table[^\>]*\>/);
	next if ($line =~ /^\s*\<tr\>/);

	# determine leaf id and remove label color
	$id = undef;
	$line =~ s/\<span class=\"c\d+\"\>/\<span\>/;
	if ($line =~ s/(<span[^\>]*\>[^\<]+\<\/span\>\s*\<span[^\>]*\>[^\<]*\<\/span\>\s*\<span[^\>]*\>[^\<]*\<\/span\>\s*)$/\%\%LABEL\%\%/) {
#	if ($line =~ s/ \<span class=\"c\d+\"\>([^\<]+)\<\/span\>/$1/) {
	    $label_html = $id = $1;
	    $id =~ s/^<span[^\>]*\>([^\<]+).*$/$1/;
	    $id =~ s/\s+//g;
	}

	# make box
	if (! defined $leaf_data{$id}) {
	    $box_color = 'cccccc';
	} else {
	    $box_color = &getHexColor (($leaf_data{$id}-$min_val), $range, $color_scheme);
	}
	$box = '<font color=#'.$box_color.'>&#9608;</font>';

	# justify labels by adding dashes to tree
	($tree_chunk) = split (/\s+/, $line);
	if ($tree_chunk !~ /\<font$/) { 
	    $len_tree_chunk = $#{[split(/\&/, $tree_chunk)]};

	    $odd_even_adjust = ((($big_tree_chunk+1) % 2) == 0) ? 1 : 0;
	    $one_more_space = ((($len_tree_chunk+1) % 2) == 0) ? '&nbsp;' : '';
	    $extra_spaces = '<font color=#cccccc>'.('&nbsp;&#9472;' x int (($big_tree_chunk-$len_tree_chunk+$odd_even_adjust)/2)).'</font>';

	    $line =~ s/^($tree_chunk)/$tree_chunk$one_more_space$extra_spaces/;
	}

	# add box and restore label
	$line =~ s/\%\%LABEL\%\%/$box $label_html/;


	# output
	print $line;
    }
}



exit 0;

###############################################################################

sub getHexColor {
    my ($val, $range, $color_scheme) = @_;
    $color_scheme = 'RYB'  if (! $color_scheme);
    my $hexColor = undef;
    my ($color_i, $r_color_i, $g_color_i, $b_color_i) = ();
    my $slope = 15/0.5;
    my @hexMap = qw (0 1 2 3 4 5 6 7 8 9 a b c d e f);

    my $mid_val = $range/2;

    if ($color_scheme =~ /^Y/i) {
	if ($val == $mid_val) {
	    $hexColor = '000000';
	}
	elsif ($val < $mid_val) {
	    $color_i = int (-$slope * $val/$range + 15 + 0.5);
	    $hexColor = '00'.'00'.$hexMap[$color_i].$hexMap[$color_i];
	}
	else {
	    $color_i = int ($slope * $val/$range - 15 + 0.5);
	    $hexColor = $hexMap[$color_i].$hexMap[$color_i].$hexMap[$color_i].$hexMap[$color_i].'00';
	}
    } 
    elsif ($color_scheme =~ /^R/i) {
	if ($val == $mid_val) {
	    $hexColor = 'ffff00';
	}
	elsif ($val < $mid_val) {
	    $r_color_i = int ( $slope * $val/$range +  0 + 0.5);
	    $g_color_i = int ( $slope * $val/$range +  0 + 0.5);
	    $b_color_i = int (-$slope * $val/$range + 15 + 0.5);
	    $hexColor = $hexMap[$r_color_i].$hexMap[$r_color_i].
		        $hexMap[$g_color_i].$hexMap[$g_color_i].
			$hexMap[$b_color_i].$hexMap[$b_color_i];
	}
	else {
	    $r_color_i = 15;
	    $g_color_i = int (-$slope * $val/$range + 30 + 0.5);
	    $b_color_i = 0;
	    $hexColor = $hexMap[$r_color_i].$hexMap[$r_color_i].
		        $hexMap[$g_color_i].$hexMap[$g_color_i].
			$hexMap[$b_color_i].$hexMap[$b_color_i];
	}
    }

    else {
	print STDERR "unknown color scheme: '$color_scheme'\n";
	exit -1;
    }

    return $hexColor;
}
