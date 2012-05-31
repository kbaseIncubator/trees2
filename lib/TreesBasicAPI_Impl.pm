
#BEGIN_HEADER

use CDMI;

our $entity_field_defs = {
    'Alignment' => {
		    'alignmentID' => 1,
		    'alignment' => 1,
		    'nRows' => 1,
		    'metaInfoHash' => 1,
		    'timestamp' => 1,
		    'isActive' => 1,
		    'isConcatenation' => 1,
		    'alignmentMethod' => 1,
		    'alignmentParameters' => 1,
		    'alignmentProtocolDescription' => 1,
		    'source_db' => 1,
		    'source_db_id' => 1,
	
    },
    'AlignmentRowComponent' => {
		    'alignmentID' => 1,
		    'alignmentRow' => 1,
		    'alignmentRowID' => 1,
		    'concatenationOrder' => 1,
		    'label' => 1,
		    'sequenceID' => 1,
		    'kbaseID' => 1,
		    'isProtein' => 1,
		    'begin' => 1,
		    'end' => 1,
	
    },
    'Tree' => {
		    'treeID' => 1,
		    'newick' => 1,
		    'alignmentID' => 1,
		    'timestamp' => 1,
		    'metaInfoHash' => 1,
		    'isActive' => 1,
		    'treeGenerationMethod' => 1,
		    'treeGenerationParameters' => 1,
		    'source_db' => 1,
		    'source_db_id' => 1,
	
    },

};

sub _init_instance
{
    my($self) = @_;
    $self->{db} = CDMI->new(dbhost => 'seed-db-read', sock => '', DBD => '/home/parrello/FIGdisk/dist/releases/current/WinBuild/KSaplingDBD.xml');
}

sub _get_entity
{
    my($self, $ctx, $tbl, $ids, $fields) = @_;

    my $valid_fields = $entity_field_defs->{$tbl};

    my $have_id;

    my $cdmi = $self->{db};
    my $q = $cdmi->{_dbh}->quote;

    my @qfields;
    my @sfields;
    my @bad_fields;
    for my $field (@$fields)
    {
	$field =~ s/-/_/g;
	if (!$valid_fields->{$field})
	{
	    push(@bad_fields, $field);
	    next;
	}
		
	push(@sfields, $field);
	my $qfield = $q . $field . $q;
	$have_id = 1 if $field eq 'id';
	push(@qfields, $qfield);
    }

    if (@bad_fields)
    {
	die "The following fields are invalid in entity $tbl: @bad_fields";
    }

    if (!$have_id)
    {
	unshift(@sfields, 'id');
	unshift(@qfields, $q . 'id' . $q);
    }
    
    my $filter = "id IN (" . join(", ", map { '?' } @$ids) . ")";

    my $qstr = join(", ", @qfields);
    my $qry = "SELECT $qstr FROM $q$tbl$q WHERE $filter";

    my $attrs = {};
    my $dbk = $cdmi->{_dbh};
    if ($dbk->dbms eq 'mysql')
    {
	$attrs->{mysql_use_result} = 1;
    }

    my $sth = $dbk->{_dbh}->prepare($qry, $attrs);
    
    print STDERR "$qry\n";
    $sth->execute(@$ids);
    my $out = $sth->fetchall_hashref('id');
    return $out;
}    

sub _all_entities
{
    my($self, $ctx, $tbl, $start, $count, $fields) = @_;

    my $valid_fields = $entity_field_defs->{$tbl};

    my $have_id;

    my $cdmi = $self->{db};
    my $q = $cdmi->{_dbh}->quote;

    my @qfields;
    my @sfields;
    my @bad_fields;
    for my $field (@$fields)
    {
	$field =~ s/-/_/g;
	if (!$valid_fields->{$field})
	{
	    push(@bad_fields, $field);
	    next;
	}
		
	push(@sfields, $field);
	my $qfield = $q . $field . $q;
	$have_id = 1 if $field eq 'id';
	push(@qfields, $qfield);
    }

    if (@bad_fields)
    {
	die "The following fields are invalid in entity $tbl: @bad_fields";
    }

    if (!$have_id)
    {
	unshift(@sfields, 'id');
	unshift(@qfields, $q . 'id' . $q);
    }
    
    my $qstr = join(", ", @qfields);

    my $attrs = {};
    my $dbk = $cdmi->{_dbh};
    my $limit;
    
    if ($dbk->dbms eq 'mysql')
    {
	$attrs->{mysql_use_result} = 1;
	$limit = "LIMIT $start, $count";
    }
    elsif ($dbk->dbms eq 'Pg')
    {
	$limit = "ORDER BY id LIMIT $count OFFSET $start";
    }

    my $qry = "SELECT $qstr FROM $q$tbl$q $limit";

    my $sth = $dbk->{_dbh}->prepare($qry, $attrs);
    
    print STDERR "$qry\n";
    $sth->execute();
    my $out = $sth->fetchall_hashref('id');
    return $out;
}    

#END_HEADER



sub get_entity_Alignment
{
    my($self, $ctx, $ids, $fields) = @_;

    my $return;
    #BEGIN get_entity_Alignment

    $return = $self->_get_entity($ctx, 'Alignment', $ids, $fields);

    #END get_entity_Alignment
    return $return;
}

sub all_entities_Alignment
{
    my($self, $ctx, $start, $count, $fields) = @_;

    my $return;
    #BEGIN all_entities_Alignment

    $return = $self->_all_entities($ctx, 'Alignment', $start, $count, $fields);

    #END all_entities_Alignment
    return $return;
}


sub get_entity_AlignmentRowComponent
{
    my($self, $ctx, $ids, $fields) = @_;

    my $return;
    #BEGIN get_entity_AlignmentRowComponent

    $return = $self->_get_entity($ctx, 'AlignmentRowComponent', $ids, $fields);

    #END get_entity_AlignmentRowComponent
    return $return;
}

sub all_entities_AlignmentRowComponent
{
    my($self, $ctx, $start, $count, $fields) = @_;

    my $return;
    #BEGIN all_entities_AlignmentRowComponent

    $return = $self->_all_entities($ctx, 'AlignmentRowComponent', $start, $count, $fields);

    #END all_entities_AlignmentRowComponent
    return $return;
}


sub get_entity_Tree
{
    my($self, $ctx, $ids, $fields) = @_;

    my $return;
    #BEGIN get_entity_Tree

    $return = $self->_get_entity($ctx, 'Tree', $ids, $fields);

    #END get_entity_Tree
    return $return;
}

sub all_entities_Tree
{
    my($self, $ctx, $start, $count, $fields) = @_;

    my $return;
    #BEGIN all_entities_Tree

    $return = $self->_all_entities($ctx, 'Tree', $start, $count, $fields);

    #END all_entities_Tree
    return $return;
}

