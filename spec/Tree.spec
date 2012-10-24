/*
KBase Phylogenetic Tree and Multiple Sequence Alignment(MSA) API

Set of functions and tools for building, querying, 

created 5/21/2012 - msneddon
last updated oct 2012
*/
module Tree
{
    /* *********************************************************************************************** */
    /* ALIGNMENT AND TREE DATA TYPES */
    /* *********************************************************************************************** */

    /* indicates true or false values, false <= 0, true >=1 */
    typedef int bool;
    /* time in units of number of seconds since the epoch */
    typedef string timestamp;
    /* integer number indicating a 1-based position in an amino acid / nucleotide sequence */
    typedef int position;
    
    /* A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are
    designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and
    "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome
    identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".
    See the standard KBase documentation for more information.
    */
    typedef string kbase_id;

    /* A string representation of a phylogenetic tree.  The format/syntax of the string can be
    specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
    'phyloXML_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to accept/return
    trees in different formats depending on addtional option parameters. Regardless of format, all leaf nodes
    in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality
    of the API to replace these IDs with other KBase Ids instead, depending on how the tree was built.  Internal
    nodes may or may not be named.  Nodes, depending on the format, may also be annotated with structured data
    such as bootstrap values.
    */
    typedef string tree;

    /* Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format)
    and are returned to you in this format by default.  
    */
    typedef tree newick_tree;
    
    /* Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
    but can optionally be converted to the more verbose phyloXML format, which is useful for compatibility or
    when additional information/annotations decorate the tree.
    */
    typedef tree phyloXML_tree;
    
    /* Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
    but can optionally be converted to JSON format where the structure of the tree matches the structure of
    the JSON object.  This is useful when interacting with the tree in JavaScript, for instance. 
    */
    typedef tree json_tree;
    
    /* String representation of a sequence or set of sequences in FASTA format.  The precise alphabet used is
    not yet specified, but will be similar to sequences stored in KBase.
    */
    typedef string fasta;
    
    /* String representation of an alignment in FASTA format.  The precise alphabet and syntax of the alignment
    string (e.g. symbol for gaps) is not yet specified, but will be similar to alignments currently in SEED.
    */
    typedef fasta fasta_alignment;
    
    /* The string representation of the parsed node name (may be a kbase_id, but does not have to).  Note, this
    is not the full label in a newick_tree (which may include comments or distances).
    */
    typedef string node_name;
    
    /* String in HTML format, used in the KBase Tree library for returning rendered trees. */
    typedef string html_file;
    
    /* String in SVG format, used in the KBase Tree library for returning rendered trees. */
    typedef string svg_file;
    
    
    /* Meta data about a tree, such as when it was created, parameters used in its creation, etc
    Is this actually needed? Or will this info be accessible through the auto generated methods?
    */
    typedef structure {
        /*some comment here*/
        kbase_id tree_id;
        kbase_id alignment_id;
        string meta_info_hash;
        bool is_active;
        timestamp date_created;
        int tree_generation_method;
        string tree_generation_parameters;
        string source_db;
        string source_db_id;
    } tree_meta_data;
    
    /* Meta data about an alignment, such as when it was created, parameters used in its creation, etc.
    Todo: determine if this object is necessary, and determine what it should contain
    */
    typedef structure {
        kbase_id alignment_id;
        string meta_info_hash;
        bool is_active;
        bool is_concatenation;
        timestamp date_created;
        int n_rows;
        int alignment_method;
        string alignment_parameters;
        string alignment_protocol_description;
        string source_db;
        string source_db_id;
    } alignment_meta_data;
    
    
    /* *********************************************************************************************** */
    /* METHODS FOR TREE PARSING AND STRING MANIPULATIONS */
    /* *********************************************************************************************** */
     
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    funcdef convert_newick2phyloXML(newick_tree tree) returns (phyloXML_tree);
    
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    funcdef convert_phyloXML2newick(newick_tree tree) returns (phyloXML_tree);
    
    /* Convert a tree encoded in newick format to a tree encded in JSON format.
    */
    funcdef convert_newick2json(newick_tree tree) returns (json_tree);
    
    /* Convert a tree encoded in JSON format to a tree encded in newick format.
    */
    funcdef convert_json2newick(json_tree tree) returns (newick_tree);
    
    /* Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
    with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
    regular expression patterns.
    */
    funcdef replace_node_names(newick_tree tree, mapping<node_name,node_name>replacements) returns (newick_tree);
    
    /* Given a tree in newick format, remove the nodes with the given names indicated in the list, and
    simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
    child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
    conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.
    */
    funcdef remove_node_names_and_simplify(newick_tree tree, list<node_name>removal_list) returns (newick_tree);
   
    
    
    
    /* *********************************************************************************************** */
    /* METHODS FOR TREE INTROSPECTION */
    /* These are methods that make a tree tell you about itself.  These methods operate on any newick  */
    /* tree that is passed in, and requires no direct connecion to the CDM.                            */
    /* *********************************************************************************************** */
  
    /* Given a tree in newick format, list the names of the leaf nodes.
    */
    funcdef extract_leaf_node_names(newick_tree tree) returns (list<node_name>);
    
    /* Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
    those originating from MicrobesOnline, the names of internal nodes are bootstrap values, but will still
    be returned by this function.
    */
    funcdef extract_node_names(newick_tree tree) returns (list<node_name>);
    
    /* Given a tree, return the total number of nodes, including internal nodes and the root node.
    */
    funcdef get_node_count(newick_tree tree) returns (int);
    
    /* Given a tree, return the total number of leaf nodes, (internal and root nodes are ignored).  When the
    tree was based on a multiple sequence alignment, the number of leaves will match the number of sequences
    that were aligned.
    */
    funcdef get_leaf_count(newick_tree tree) returns (int);
    
    
    
    
    
    /* *********************************************************************************************** */
    /* METHODS FOR ALIGNMENT AND TREE RETRIEVAL */
    /* *********************************************************************************************** */
    
    /* Returns the specified tree in newick format, or an empty string if the tree does not exist.  Options
    hash provides a way to return the tree with different labels replaced or with different attached meta
    information.
    
        options = [
            FORMAT => 'raw' || 'first' || 'all' || 'species_name' ...
        ]
 
    The FORMAT option selects how node labels are replaced in the output tree.  'raw' returns just the
    tree with labels into the AlignmentRowComponent table. 'first' returns the tree with labels replaced
    with only a single kbase_id to a sequence (if there are multiple sequences in the alignment row, then
    only the first is returned).  'all' returns the tree with all the kbase_ids that make up the alignment
    row in a comma-delimited format.  If there is only one sequence in the alignment, then the behavior
    is the same as 'first'.  'species_name' replaces labels with the name of the organism, if available.
    other options???
    
    Note: the options hash will be the same as for other functions which provide substitution capabilities 
    
    todo: provide a way to get meta data about this tree, possibly in a separate function, but may
    not be needed if this is provided by the ER model.
    funcdef get_tree(kbase_id tree_id, mapping<string,string> options) returns (newick_tree);
    */
    
    /* Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
    was not found. Note: this function may not be needed if this functionality is provided by the auto-gen ER code
    */    
    funcdef get_trees(list<kbase_id> tree_ids, mapping<string,string> options) returns (list<newick_tree>);
    
    /* Returns a list of all IDs of all trees in the database that match the given flags (right now
    the only flag indicates if the tree is active or not, meaning the latest version of the tree,
    but this should be extended to accept more args and possible queries.
    */ 
    funcdef all_tree_ids(bool is_active) returns (list <kbase_id>);

    /* Returns all tree IDs in which the entire portion of the given sequence (which can optionally
    include start and end positions of the sequence) is used in the alignment which generates the
    tree.
    todo: should beg/end just be included in some options hash?
    todo: define contents of options hash, which will allow more complex queries, such as returning
          only active trees, or trees of a particuar hieght, etc...
    funcdef get_trees_with_entire_seq(fasta sequence, position beg, position end, mapping<string,string> options) returns (list<kbase_id>);
    */
    
    /* Returns all tree IDs in which some portion of the given sequence (which can optionally
    include start and end positions of the sequence) is used in the alignment which generates the tree.
    funcdef get_trees_with_overlapping_seq(fasta sequence, position beg, position end, mapping<string,string> options) returns (list<kbase_id>);
    */
    
    /* Returns all tree IDs in which the entire portion of the given domain is used in the alignment
    which generates the tree (usually the tree will be constructed based on this domain). NOT FUNCTIONAL UNTIL KBASE HAS HOMOLOGUE/DOMAIN LOOKUPS
    funcdef get_trees_with_entire_domain(kbase_id domain, mapping<string,string>options) returns (list<kbase_id>);
    */
    
    /* Returns all tree IDs in which some portion of the given domain is used in the alignment
    which generates the tree (usually such trees will be constructed based on a similar domain created
    with an alternative method, so the entire domain may not be contained).  NOT FUNCTIONAL UNTIL KBASE HAS HOMOLOGUE/DOMAIN LOOKUPS
    funcdef get_trees_with_overlapping_domain(kbase_id domain, mapping<string,string>options) returns (list<kbase_id>);
    */
    
    
    funcdef get_tree_ids_by_feature(list <kbase_id> feature_ids, mapping<string,string>options) returns (list<kbase_id>);
    
    funcdef get_tree_ids_by_protein_sequence(list <kbase_id> feature_ids, mapping<string,string>options) returns (list<kbase_id>);
    
    funcdef get_alignment_ids_by_feature(list <kbase_id> feature_ids, mapping<string,string>options) returns (list<kbase_id>);
    
    funcdef get_alignment_ids_by_protein_sequence(list <kbase_id> feature_ids, mapping<string,string>options) returns (list<kbase_id>);
  
    
    /* *********************************************************************************************** */
    /* METHODS FOR TREE-BASED FEATURE/SEQUENCE LOOKUPS */
    /* *********************************************************************************************** */
 
    /*
    Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
    identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
    alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
    a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
    parameters to be passed (parameter list is also currently not defined yet and is currently ignored.)
    */
    funcdef substitute_node_names_with_kbase_ids(list <kbase_id> trees, mapping<string,string> options) returns (list<newick_tree>);

 
     /* Given an alignment and a row in the alignment, returns all the kbase_ids of the sequences that compose
    the given tree. Note: may not be needed if this functionality is possible via the ER model
    */
    funcdef get_kbase_ids_from_alignment_row(kbase_id alignment_id, int row_number) returns (list<kbase_id>);

 
 
    /* *********************************************************************************************** */
    /* METHODS FOR TREE RESTRUCTURING */
    /* *********************************************************************************************** */
 
    /* Given a tree in KBASE and a sequence in FASTA format, attempt to add the new sequence into the tree.  This
    method requires that the tree was built from a multiple sequence alignment and that the tree/alignment is stored
    in KBASE.  This method returns
    */
    funcdef add_node_to_tree(kbase_id tree_id, kbase_id sequence_id, mapping<string,string> options) returns (newick_tree);

 
    /* *********************************************************************************************** */
    /* METHODS FOR ALIGNMENT / TREE BUILDING */
    /* *********************************************************************************************** */
 
    /* Given an alignment in FASTA format, build a phylogenetic tree using the options indicated.
    */
    funcdef build_tree_from_fasta(fasta_alignment alignment, mapping<string,string>options) returns (newick_tree);
    
    /* Given a set of sequences in FASTA format, construct a sequence alignment with the options indicated.
    */
    funcdef align_sequences(list<fasta> sequences, mapping<string,string>options) returns (fasta_alignment);
 
 
    /* *********************************************************************************************** */
    /* METHODS FOR TREE VISUALIZATION */
    /* *********************************************************************************************** */

    /* Given a tree, render it in HTML/JAVASCRIPT and return the page. */
    funcdef draw_web_tree(newick_tree tree, mapping<string,string>display_options) returns (html_file);
    
    /* Given a tree, render it as an SVG object and return the drawing. */
    funcdef draw_svg_tree(newick_tree tree, mapping<string,string>display_options) returns (svg_file);


    




    


    






};
