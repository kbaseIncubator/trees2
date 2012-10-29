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

    /* A string representation of a phylogenetic tree.  The format/syntax of the string is
    specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
    'phyloXML_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return
    trees in different formats depending on addtional option parameters. Regardless of format, all leaf nodes
    in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality
    of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named.
    Nodes, depending on the format, may also be annotated with structured data such as bootstrap values.
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
    
    /* The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note, this
    is not the full, raw label in a newick_tree (which may include comments or distances).
    */
    typedef string node_name;
    
    /* String in HTML format, used in the KBase Tree library for returning rendered trees. */
    typedef string html_file;
    
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
    
    /* NOTE: methods that are commented out are not yet fully functional or implemented */
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    /* funcdef convert_newick2phyloXML(newick_tree tree) returns (phyloXML_tree); */
    
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    /* funcdef convert_phyloXML2newick(newick_tree tree) returns (phyloXML_tree); */
    
    /* Convert a tree encoded in newick format to a tree encded in JSON format.
    */
    /* funcdef convert_newick2json(newick_tree tree) returns (json_tree); */
    
    /* Convert a tree encoded in JSON format to a tree encded in newick format.
    */
    /* funcdef convert_json2newick(json_tree tree) returns (newick_tree); */
    
    
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
    
    /* Returns the specified tree in the specified format, or an empty string if the tree does not exist.
    The options hash provides a way to return the tree with different labels replaced or with different attached meta
    information.  Currently, the available flags and understood options are listed 
    
        options = [
            format => 'newick',
            newick_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' || 'contig_sequence_id',
            newick_bootstrap => 'none' || 'internal_node_labels'
            newick_distance => 'none' || 'raw'
        ];
 
    The 'format' key indicates what string format the tree should be returned in.  Currently, there is only
    support for 'newick'. The default value if not specified is 'newick'.
    
    The 'newick_label' key only affects trees returned as newick format, and specifies what should be
    placed in the label of each leaf.  'none' indicates that no label is added, so you get the structure
    of the tree only.  'raw' indicates that the raw label mapping the leaf to an alignement row is used.
    'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
    feature that was originally used to define the sequence).  'protein_sequence_id' indicates that the
    kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
    the contig sequence id is added.  Note that trees are typically built with protein sequences OR
    contig sequences. If you select one type of sequence, but the tree was built with the other type, then
    no labels will be added.  The default value if none is specified is 'raw'.
    
    The 'newick_bootstrap' key allows control over whether bootstrap values are returned if they exist, and
    how they are returned.  'none' indicates that no bootstrap values are returned. 'internal_node_labels'
    indicates that bootstrap values are returned as internal node labels.  Default value is 'internal_node_labels';
    
    The 'newick_distance' key allows control over whether distance labels are generated or not.  If set to
    'none', no distances will be output. Default is 'raw', which outputs the distances exactly as they appeared
    when loaded into kbase.
    
    */
    funcdef get_tree(kbase_id tree_id, mapping<string,string> options) returns (tree);
    
    /* Performs exactly the same function as get_tree, but accepts a list of ids instead, and returns
    a list of trees.
    */    
    funcdef get_trees(list<kbase_id> tree_ids, mapping<string,string> options) returns (list<tree>);
    
    /* Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
    is identified and used to retrieve all trees by ID that were built using the given protein sequence. */
    funcdef get_tree_ids_by_feature(list <kbase_id> feature_ids) returns (list<kbase_id>);
    
    /* Given a list of kbase ids of a protein sequences (their MD5s), retrieve the tree ids of trees that
    were built based on these sequences. */
    funcdef get_tree_ids_by_protein_sequence(list <kbase_id> protein_sequence_ids) returns (list<kbase_id>);
    
    /* Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
    is identified and used to retrieve all alignments by ID that were built using the given protein sequence. */
    funcdef get_alignment_ids_by_feature(list <kbase_id> feature_ids) returns (list<kbase_id>);
    
    /* Given a list of kbase ids of a protein sequences (their MD5s), retrieve the alignment ids of trees that
    were built based on these sequences. */
    funcdef get_alignment_ids_by_protein_sequence(list <kbase_id> protein_sequence_ids) returns (list<kbase_id>);
  
    
    
    
    
    
    /* Returns a list of all IDs of all trees in the database that match the given flags (right now
    the only flag indicates if the tree is active or not, meaning the latest version of the tree,
    but this should be extended to accept more args and possible queries.
    NOTE: This method no longer is needed because it can be done via the CDMI
    */ 
    /* funcdef all_tree_ids(bool is_active) returns (list <kbase_id>); */

    /* Returns all tree IDs in which the entire portion of the given sequence (which can optionally
    include start and end positions of the sequence) is used in the alignment which generates the tree.
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
    /*funcdef substitute_node_names_with_kbase_ids(list <kbase_id> trees, mapping<string,string> options) returns (list<newick_tree>);*/

 
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
    /* funcdef add_node_to_tree(kbase_id tree_id, kbase_id sequence_id, mapping<string,string> options) returns (newick_tree); */

 
    /* *********************************************************************************************** */
    /* METHODS FOR ALIGNMENT / TREE BUILDING */
    /* *********************************************************************************************** */
 
    /* Given an alignment in FASTA format, build a phylogenetic tree using the options indicated.
    */
    /* funcdef build_tree_from_fasta(fasta_alignment alignment, mapping<string,string>options) returns (newick_tree); */
    
    /* Given a set of sequences in FASTA format, construct a sequence alignment with the options indicated.
    */
    /* funcdef align_sequences(list<fasta> sequences, mapping<string,string>options) returns (fasta_alignment); */
 
 
    /* *********************************************************************************************** */
    /* METHODS FOR TREE VISUALIZATION */
    /* *********************************************************************************************** */

    /* Given a tree, render it in HTML/JAVASCRIPT and return the page. */
    funcdef draw_html_tree(newick_tree tree, mapping<string,string>display_options) returns (html_file);
    
    /* Given a tree, render it as an SVG object and return the drawing. */
    /* funcdef draw_svg_tree(newick_tree tree, mapping<string,string>display_options) returns (svg_file); */


    




    


    






};
