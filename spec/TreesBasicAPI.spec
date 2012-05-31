module TreesBasicAPI {
typedef string diamond;
typedef string countVector;
typedef string rectangle;

typedef structure {
	string id;
	string alignmentID nullable;
	string alignment nullable;
	int nRows nullable;
	string metaInfoHash nullable;
	 timestamp nullable;
	int isActive nullable;
	int isConcatenation nullable;
	string alignmentMethod nullable;
	string alignmentParameters nullable;
	string alignmentProtocolDescription nullable;
	string source_db nullable;
	string source_db_id nullable;
} fields_Alignment ;

/*
An alignment of sequences (usually 
It has the following fields:

=over 4


=item alignmentID

will be unique kbase id: 'kb|ali.XXXXX'


=item alignment

Ref to file storing the alignment (using SHOCK?)


=item nRows

number of rows in the alignment


=item metaInfoHash

e.g. name, human readable description, etc


=item timestamp




=item isActive




=item isConcatenation

boolean value that indicates if leaves map to single sequences, or multiple sequences


=item alignmentMethod

string that either maps to another dataase, to capture workflows, or is a simple method name


=item alignmentParameters

hash that stores parameter values used in the alignment


=item alignmentProtocolDescription

human readable, how did you get here with these sequences? could also map to a separate table


=item source_db

for indicating the ID in the db where this alignment originated from


=item source_db_id

for indicating the ID in the db where this alignment originated from



=back


*/
funcdef get_entity_Alignment(list<string> ids, list<string> fields)
	returns(mapping<string, fields_Alignment>);
funcdef all_entities_Alignment(int start, int count, list<string> fields)
	returns(mapping<string, fields_Alignment>);

typedef structure {
	string id;
	string alignmentID nullable;
	float alignmentRow nullable;
	string alignmentRowID nullable;
	int concatenationOrder nullable;
	string label nullable;
	string sequenceID nullable;
	string kbaseID nullable;
	int isProtein nullable;
	int begin nullable;
	int end nullable;
} fields_AlignmentRowComponent ;

/*
Individual components that make up rows in the alignment
It has the following fields:

=over 4


=item alignmentID

needed for efficiency


=item alignmentRow

maybe needed for efficiency


=item alignmentRowID

will be unique kbase id: 'kb|ali.XXXXX.row.XXX'


=item concatenationOrder

ordering starting from left to right in alignment row


=item label

text description copied from original fasta file


=item sequenceID

MD5 for protein, probably contigChunk for DNA/RNA


=item kbaseID

kbaseID for the sequence that was actually used in the alignment


=item isProtein




=item begin




=item end





=back


*/
funcdef get_entity_AlignmentRowComponent(list<string> ids, list<string> fields)
	returns(mapping<string, fields_AlignmentRowComponent>);
funcdef all_entities_AlignmentRowComponent(int start, int count, list<string> fields)
	returns(mapping<string, fields_AlignmentRowComponent>);

typedef structure {
	string id;
	string treeID nullable;
	string newick nullable;
	string alignmentID nullable;
	string timestamp nullable;
	string metaInfoHash nullable;
	int isActive nullable;
	string treeGenerationMethod nullable;
	string treeGenerationParameters nullable;
	string source_db nullable;
	string source_db_id nullable;
} fields_Tree ;

/*
A tree built from a multiple sequence alignment.
It has the following fields:

=over 4


=item treeID

A unique kbase id: eg 'kb|tree.XXXX'


=item newick

leaf nodes provide a key into the AlignmentRowComponent table


=item alignmentID

maps this tree to an alignment, needed primarily for convenience


=item timestamp

time of creation (of original tree, or of kbase version?)


=item metaInfoHash

e.g. name, human readable description, etc


=item isActive




=item treeGenerationMethod




=item treeGenerationParameters




=item source_db




=item source_db_id





=back


*/
funcdef get_entity_Tree(list<string> ids, list<string> fields)
	returns(mapping<string, fields_Tree>);
funcdef all_entities_Tree(int start, int count, list<string> fields)
	returns(mapping<string, fields_Tree>);

};
