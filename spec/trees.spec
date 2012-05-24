
/*
Tree and Multiple Sequence Alignment(MSA) API

Full documentation and API reference will be added here.  What follows is the current
version of the tree/MSA API proposal.

created 5/21/2012 - msneddon
*/
module Trees
{
    typedef int bool;
    typedef string timestamp;
    typedef int position;
    
    /*
    A KBase ID is string starting with the characters "kb|".  KBase IDs are typed. The types are
    designated using a short string. For instance," g" denotes a genome. KBase IDs may be hierarchical.
    If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented
    as "kb|g.1234.fp.771". See the standard KBase documentation for more info.
    */
    typedef string kbase_id;

    /*
    Trees are represented in newick format (http://en.wikipedia.org/wiki/Newick_format) and are
    returned to you in this format by default.  All leaf nodes are by default indexed to a MSA row
    element.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids
    instead, depending on how the tree was built.  Internal nodes may or may not be labeled.  Nodes may
    also be annotated with structured data
    */
    typedef string newick_tree;
    
    /*
    String representation of an alignment, the precise syntax of which is not yet specified but will
    likely be similar to alignments stored in SEED.
    */
    typedef string fasta_alignment;

    /*
    Meta data about a tree, such as when it was created, parameters used in its creation, etc
    Is this actually needed? Or will this info be accessible through the auto generated methods?
    */
    typedef structure {
        /*some comment here*/
        kbase_id tree_id;
        kbase_id alignment_id;
        bool isActive;
        timestamp date_created;
        int tree_generation_method;
        string tree_generation_parameters;
    } tree_meta_data;

    /*
    Meta data about an alignment, such as when it was created, parameters used in its creation, etc.
    Todo: determine if this object is necessary, and determine what it should contain
    */
    typedef structure {
        kbase_id alignment_id;
        bool isActive;
        timestamp date_created;
        int n_rows;
        int tree_generation_method;
        string tree_generation_parameters;
    } alignment_meta_data;
    
    
    
    
    
    

    /*
    Returns the specified tree in newick format, or an empty string if the tree does not exist.
    Note: this function may not be needed if this functionality is provided by the auto-gen ER code
    todo: provide a way to automatically replace alignment row ids with kbase ids
    todo: provide a way to get meta data about this tree, possibly in a separate function
    */
    funcdef get_tree(kbase_id tree_id) returns (newick_tree);
    
    /*
    Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
    was not found.
    Note: this function may not be needed if this functionality is provided by the auto-gen ER code
    */    
    funcdef get_trees(list<kbase_id> tree_ids) returns (list<newick_tree>);
    
    /*
    Returns a list of all IDs of all trees in the database that match the given flags (right now
    the only flag indicates if the tree is active or not, meaning the latest version of the tree,
    but this should be extended to accept more args and possible queries.
    Note: this function may not be needed if this functionality is provided by the auto-gen ER code
    */ 
    funcdef all_tree_ids(bool isActive) returns (list <kbase_id>);







    
    /*
    Returns all tree IDs in which the entire portion of the given sequence (which can optionally
    include start and end positions of the sequence) is used in the alignment which generates the
    tree.
    todo: should beg/end just be included in some options hash?
    todo: define contents of options hash, which will allow more complex queries, such as returning
          only active trees, or trees of a particuar hieght, etc...
    */
    funcdef get_trees_with_entire_seq(kbase_id sequence, position beg, position end, mapping<string,string> options) returns (list<kbase_id>);
    
    /*
    Returns all tree IDs in which some portion of the given sequence (which can optionally
    include start and end positions of the sequence) is used in the alignment which generates the
    tree.
    */
    funcdef get_trees_with_overlapping_seq(kbase_id sequence, position beg, position end, mapping<string,string> options) returns (list<kbase_id>);
    
    /*
    Returns all tree IDs in which the entire portion of the given domain is used in the alignment
    which generates the tree (usually the tree will be constructed based on this domain).
    */
    funcdef get_trees_with_entire_domain(kbase_id domain, mapping<string,string> options) returns (list<kbase_id>);
    
    /*
    Returns all tree IDs in which some portion of the given domain is used in the alignment
    which generates the tree (usually the tree will be constructed based on a similar domain created
    with an alternative method, so the entire domain may not be contained).
    */
    funcdef get_trees_with_overlapping_domain(kbase_id domain, mapping<string,string> options) returns (list<kbase_id>);
    








    /*
    Given a list of kbase identifiers for a tree, substitutes the leaf node labels with actual kbase sequence
    identifiers.  If a particular alignment row maps to a single sequence, this is straightforward.  If an
    alignmnt row maps to multiple sequences, then the current behavior is not yet defined (likely will be
    a concatenated list of sequence ids that compose the alignment row).  Options Hash allows addiional
    parameters to be passed (parameter list is also currently not defined yet.)
    */
    funcdef substitute_node_labels_with_kbase_ids(list <kbase_id> trees, mapping<string,string> options) returns (list<newick_tree>);

    /*
    Given a tree, returns the list of labels of the leaves.  If the 'substitute_node_labels_with_kbase_ids' was already
    called to retrieve the trees, then this method will provide a list of kbase_ids indicating the sequences that comprised
    the tree.
    */
    funcdef extract_leaf_node_labels(newick_tree tree) returns (list<string>);


    /* additional helper functions to be determined based on use-cases */


};