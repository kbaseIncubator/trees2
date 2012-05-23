
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
    element.  You can use the appropriate functionality to replace these IDs with other KBase Ids
    instead, depending on how the tree was built.
    */
    typedef string newick_tree;

    /*
    Meta data about a tree, such as when it was created, parameters used in its creation, the
    alignment id used to build the tree, etc.
    */
    typedef structure {
        kbase_id tree_id;
        kbase_id alignment_id;
        bool isActive;
        timestamp date_created;
        int tree_generation_method;
        string tree_generation_parameters;
    } tree_meta_data;

    /*
    Meta data about an alignment, such as when it was created, parameters used in its creation, etc
    */
    typedef structure {
        kbase_id alignment_id;
        bool isActive;
        timestamp date_created;
        int tree_generation_method;
        string tree_generation_parameters;
    } alignment_meta_data;
    
    
    
    
    

    /*
    Returns the specified tree in newick format, or an empty string if the tree does not exist.
    todo: provide a way to automatically replace alignment row ids with kbase ids
    todo: provide a way to get meta data about this tree, possibly in a separate function
    */
    funcdef get_tree(kbase_id tree_id) returns (newick_tree);
    
    /*
    Returns a list of the specifed trees in newick format, or an empty string for each tree_id that
    was not found.
    */    
    funcdef get_trees(list<kbase_id> tree_ids) returns (list<newick_tree>);
    
    /*
    Returns a list of all IDs of all trees in the database that match the given flags (right now
    the only flag indicates if the tree is active or not, meaning the latest version of the tree,
    but this should be extended to accept more args.
    */ 
    funcdef all_tree_ids(string isActive) returns (list <kbase_id>);

    
    
    
    

    funcdef getTreeByLocusID(int number, kbase_id id) returns(list<string>);
};