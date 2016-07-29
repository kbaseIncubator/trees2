/*
Phylogenetic Tree and Multiple Sequence Alignment Services

This service provides a set of data types and methods for operating with multiple
sequence alignments (MSAs) and phylogenetic trees.

Authors
---------
Michael Sneddon, LBL (mwsneddon@lbl.gov)
Fangfang Xia, ANL (fangfang.xia@gmail.com)
Keith Keller, LBL (kkeller@lbl.gov)
Matt Henderson, LBL (mhenderson@lbl.gov)
Dylan Chivian, LBL (dcchivian@lbl.gov)
Roman Sutormin, LBL (rsutormin@lbl.gov)
*/
module KBaseTrees
{
    /* *********************************************************************************************** */
    /* ALIGNMENT AND TREE DATA TYPES */
    /* *********************************************************************************************** */

    /*
        Indicates true or false values, false = 0, true = 1
        @range [0,1]
    */
    typedef int boolean;
    
    /*
        Time in units of number of seconds since the epoch
    */
    typedef string timestamp;
    
    /*
        Integer number indicating a 1-based position in an amino acid / nucleotide sequence
        @range [1,
    */
    typedef int position;
    
    /*
        A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are
        designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and
        "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome
        identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as
        "kb|g.1234.peg.771".
        @id kb
    */
    typedef string kbase_id;

    /*
        A string representation of a phylogenetic tree.  The format/syntax of the string is
        specified by using one of the available typedefs declaring a particular format, such as 'newick_tree',
        'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return
        trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes
        in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality
        of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named.
        Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and
        distances.
    */
    typedef string tree;

    /*
        Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format)
        and are returned to you in this format by default.  
    */
    typedef tree newick_tree;
    
    /*
        Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
        but can optionally be converted to the more verbose phyloXML format, which is useful for compatibility or
        when additional information/annotations decorate the tree.
    */
    typedef tree phylo_xml_tree;
    
    /*
        Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format),
        but can optionally be converted to JSON format where the structure of the tree matches the structure of
        the JSON object.  This is useful when interacting with the tree in JavaScript, for instance. 
    */
    typedef tree json_tree;
    
    /*
        String representation of a sequence alignment, the format of which may be different depending on
        input options for retrieving the alignment.
    */
    typedef string alignment;
    
    /*
        @id ws
    */
    typedef string ws_obj_id;
    
    /* A workspace ID that references a Tree data object.
        @id ws KBaseTrees.Tree
    */
    typedef string ws_tree_id;
    
    /*
    	###  KBaseTrees.ConcatMSA KBaseTrees.MS
        @id ws KBaseTrees.MSA
    */
    typedef string ws_alignment_id;
    
    /* A workspace ID that references a Genome data object.
        @id ws KBaseGenomes.Genome KBaseGenomeAnnotations.GenomeAnnotation
    */
    typedef string ws_genome_id;
    
    /* A workspace ID that references a GenomeSet data object.
        @id ws KBaseSearch.GenomeSet
    */
    typedef string ws_genomeset_id;

    /* A workspace ID that references a FeatureSet data object.
        @id ws KBaseSearch.FeatureSet
    */
    typedef string ws_featureset_id;

    /* */
    typedef string node_id;
    
    
    typedef boolean is_leaf;
    
    
    typedef string label;
    
    /*
    An enumeration of reference types for a node.  Either the one letter abreviation or full
    name can be given.  For large trees, it is strongly advised you use the one letter abreviations.
    Supported types are:
        g | genome  => genome typed object or CDS data
        p | protein => protein sequence object or CDS data, often given as the MD5 of the sequence
        n | dna     => dna sequence object or CDS data, often given as the MD5 of the sequence
        f | feature => feature object or CDS data
    */
    typedef string ref_type;
    
    
    /* Data type for phylogenetic trees.
    
        @optional name description type tree_attributes
        @optional default_node_labels ws_refs kb_refs leaf_list
    */
    typedef structure {
        string name;
        string description;
        string type;
        
        newick_tree tree;
        
        mapping <string,string> tree_attributes;
        
        mapping <node_id,label> default_node_labels;
        mapping <node_id,mapping<ref_type,list<ws_obj_id>>> ws_refs;
        mapping <node_id,mapping<ref_type,list<kbase_id>>> kb_refs;
        
        list <node_id> leaf_list;
    } Tree;
    
    
    
    /* note: we need to merge tree_node_id with node_id ... */
    
    /* may include leaves, nodes, and branches */
    typedef string tree_elt_id;
    typedef string tree_leaf_id;
    typedef string tree_node_id;
    typedef string tree_branch_id;
    typedef string collapsed_node_flag;
    typedef tuple<string substructure_label, string substructure_class> substructure;
    typedef tuple<string value,string viz_type> viz_val_string;
    typedef tuple<int value,string viz_type> viz_val_int;
    typedef tuple<float value,string viz_type> viz_val_float;
    
    /*
       something looks strange with this field:  it was removed so we can compile
        mapping<tree_leaf_id tree_leaf_id, tuple<string substructure_label, string> substructure_by_leaf;
    */
    typedef structure {
        ws_tree_id tree_id;
        string viz_title;
        list<string> string_dataset_labels;
        list<string> string_dataset_viz_types;
        list<string> int_dataset_labels;
        list<string> int_dataset_viz_types;
        list<string> float_dataset_labels;
        list<string> float_dataset_viz_types;
        mapping<tree_elt_id,list<viz_val_string>> tree_val_list_string;
        mapping<tree_elt_id,list<viz_val_int>> tree_val_list_int;
        mapping<tree_elt_id,list<viz_val_float>> tree_val_list_float;
        mapping<tree_node_id,collapsed_node_flag> collapsed_by_node;
        mapping<tree_node_id,substructure> substructure_by_node;
        string rooted_flag;
        node_id alt_root_id;
    } TreeDecoration;


    typedef string row_id;
    typedef string sequence;
    
    typedef int start_pos_in_parent;
    typedef int end_pos_in_parent;
    typedef int parent_len;
    typedef string parent_md5;
    
    typedef tuple <
        start_pos_in_parent,
        end_pos_in_parent,
        parent_len,
        parent_md5>
            trim_info;

    /* Type for multiple sequence alignment.
	sequence_type - 'protein' in case sequences are amino acids, 'dna' in case of 
		nucleotides.
	int alignment_length - number of columns in alignment.
	mapping<row_id, sequence> alignment - mapping from sequence id to aligned sequence.
	list<row_id> row_order - list of sequence ids defining alignment order (optional). 
	ws_alignment_id parent_msa_ref - reference to parental alignment object to which 
		this object adds some new aligned sequences (it could be useful in case of
		profile alignments where you don't need to insert new gaps in original msa).
	@optional name description sequence_type
	@optional trim_info alignment_attributes row_order 
	@optional default_row_labels ws_refs kb_refs
	@optional parent_msa_ref
    */
    typedef structure {
        string name;
        string description;
        string sequence_type;
        
        int alignment_length;
        mapping <row_id, sequence> alignment;
        
        mapping <row_id, trim_info> trim_info;
        
        mapping <string,string> alignment_attributes;
        list <row_id> row_order;
        
        mapping <node_id,label> default_row_labels;
        mapping <node_id,mapping<ref_type,list<ws_obj_id>>> ws_refs;
        mapping <node_id,mapping<ref_type,list<kbase_id>>> kb_refs;
        
        ws_alignment_id parent_msa_ref;
    } MSA;

    /*
        Type for MSA collection element. There could be mutual exclusively 
        defined either ref or data field.
        @optional metadata
        @optional ref
        @optional data
    */
    typedef structure {
        mapping<string, string> metadata;
        ws_alignment_id ref;
        MSA data;
    } MSASetElement;

    /*
        Type for collection of MSA objects. 
    */
    typedef structure {
        string description;
        list<MSASetElement> elements;
    } MSASet;



    
    
    /* *********************************************************************************************** */
    /* METHODS FOR TREE PARSING AND STRING MANIPULATIONS */
    /* *********************************************************************************************** */

    /*
        The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this
        is not the full, raw label in a newick_tree (which may include comments).
    */
    typedef string node_name;
    
    
    /* Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
    with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
    regular expression patterns.
    */
    funcdef replace_node_names(newick_tree tree, mapping<node_id,node_name>replacements) returns (newick_tree);
    
    /* Given a tree in newick format, remove the nodes with the given names indicated in the list, and
    simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
    child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
    conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.
    */
    funcdef remove_node_names_and_simplify(newick_tree tree, list<node_id>removal_list) returns (newick_tree);
   
    /* Some KBase trees keep information on canonical feature ids, even if they have the same protien sequence
    in an alignment.  In these cases, some leaves with identical sequences will have zero distance so that
    information on canonical features is maintained.  Often this information is not useful, and a single
    example feature or genome is sufficient.  This method will accept a tree in newick format (with distances)
    and merge all leaves that have zero distance between them (due to identical sequences), and keep arbitrarily
    only one of these leaves.
    */
    funcdef merge_zero_distance_leaves(newick_tree tree) returns (newick_tree);
   
   
    /* NOTE: methods that are commented out are not yet fully implemented yet, but will likely appear in future
    versions of the Tree service as they are needed or requested.*/
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    /* funcdef convert_newick2phyloXML(newick_tree tree) returns (phylo_xml_tree); */
    /* Convert a tree encoded in newick format to a tree encded in phyloXML format.
    */
    /* funcdef convert_phyloXML2newick(newick_tree tree) returns (phylo_xml_tree); */
    /* Convert a tree encoded in newick format to a tree encded in JSON format.
    */
    /* funcdef convert_newick2json(newick_tree tree) returns (json_tree); */
    /* Convert a tree encoded in JSON format to a tree encded in newick format.
    */
    /* funcdef convert_json2newick(json_tree tree) returns (newick_tree); */
    
    

    
    
    
    /* *********************************************************************************************** */
    /* METHODS FOR TREE INTROSPECTION */
    /* These are methods that make a tree tell you about itself.  These methods operate on any newick  */
    /* tree that is passed in, and requires no direct connecion to the CDM.                            */
    /* *********************************************************************************************** */
  
    /* Given a tree in newick format, list the names of the leaf nodes.
    */
    funcdef extract_leaf_node_names(newick_tree tree) returns (list<node_name>);
    
    /* Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
    those originating from MicrobesOnline, the names of internal nodes may be bootstrap values, but will still
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


    /* *********************************************************************************************** */
    /* METHODS FOR TREE VISUALIZATION */
    /* *********************************************************************************************** */

    /* String in HTML format, used in the KBase Tree library for returning rendered trees.
    */
    typedef string html_file;
    
    /* Given a tree structure in newick, render it in HTML/JAVASCRIPT and return the page as a string. display_options
    provides a way to pass parameters to the tree rendering algorithm, but currently no options are recognized. */
    funcdef draw_html_tree(newick_tree tree, mapping<string,string>display_options) returns (html_file);
    

    /* *********************************************************************************************** */
    /* DATA TYPES AND METHODS FOR SPECIES TREE CONSTRUCTION / INSERTION */
    /* *********************************************************************************************** */

    /* A convenience type representing a genome id reference. This might be a kbase_id (in the case of 
    a CDM genome) or, more likely, a workspace reference of the structure "ws/obj/ver"
    */
    typedef string genome_ref;

    /* A string representation of the scientific name of a species.
    */
    typedef string scientific_name;

    /* A tuple that gives very basic information about a single genome in a SpeciesTree - enough to decorate 
    the nodes with the species name, and fetch more genome information from the KBase datastores.
    */
    /*typedef tuple<genome_ref ref, scientific_name name> genome_info;*/

    /* An id for a cluster of orthologous groups (COG). A species tree is built by aligning genomes
    based on several of these. */
    /*typedef string cog_id;*/

    /* The structure of a tree itself.

        tree - the Newick string representing the tree itself
        id_map - maps from internal tree node ids to workspace references for each genome
        alignment_ref - (optional) the reference to the alignment from which the tree was built
        cogs - the list of NCBI COG ids that were used to build the tree
    */
    /*typedef structure {
        newick_tree species_tree;
        mapping<node_name, genome_info> id_map;
        string alignment_ref;
        list<cog_id> cogs;
    } SpeciesTree;*/

    /* Input data type for construct_species_tree method. Method produces object of Tree type.

        new_genomes - (optional) the list of genome references to use in constructing a tree; either
            new_genomes or genomeset_ref field should be defined.
        genomeset_ref - (optional) reference to genomeset object; either new_genomes or genomeset_ref
            field should be defined.
        out_workspace - (required) the workspace to deposit the completed tree
        out_tree_id - (optional) the name of the newly constructed tree (will be random if not present or null)
        use_ribosomal_s9_only - (optional) 1 means only one protein family (Ribosomal S9) is used for 
            tree construction rather than all 49 improtant families, default value is 0.
        nearest_genome_count - (optional) defines maximum number of public genomes nearest to
            requested genomes that will show in output tree.
    */
    typedef structure {
        list<genome_ref> new_genomes;
        ws_genomeset_id genomeset_ref;
        string out_workspace;
        string out_tree_id;
        int use_ribosomal_s9_only;
        int nearest_genome_count;
    } ConstructSpeciesTreeParams;

    /* A string representing a job id for manipulating trees. This is an id for a job that is
    registered with the User and Job State service.
    */
    typedef string job_id;

    /* Build a species tree out of a set of given genome references.
    */
    funcdef construct_species_tree(ConstructSpeciesTreeParams input) returns (job_id) authentication required;


    /* Input data type for construct_multiple_alignment method. Method produces object of MSA type.
		
        gene_sequences - (optional) the mapping from gene ids to their sequences; either gene_sequences
            or featureset_ref should be defined.
		featureset_ref - (optional) reference to FeatureSet object; either gene_sequences or
            featureset_ref should be defined.
        alignment_method - (optional) alignment program, one of: Muscle, Clustal, ProbCons, T-Coffee, 
        	Mafft (default is Clustal).
        is_protein_mode - (optional) 1 in case sequences are amino acids, 0 in case of nucleotides 
        	(default value is 1).
        out_workspace - (required) the workspace to deposit the completed alignment
        out_msa_id - (optional) the name of the newly constructed msa (will be random if not present 
        	or null)
    */
    typedef structure {
        mapping<string, string> gene_sequences;
        ws_featureset_id featureset_ref; 
        string alignment_method;
        int is_protein_mode;
        string out_workspace;
        string out_msa_id;
    } ConstructMultipleAlignmentParams;

	/* Build a multiple sequence alignment based on gene sequences.
	*/
	funcdef construct_multiple_alignment(ConstructMultipleAlignmentParams params) returns (job_id) authentication required;
		
	/* Input data type for construct_tree_for_alignment method. Method produces object of Tree type.
		
		msa_ref - (required) reference to MSA input object.
        tree_method - (optional) tree construction program, one of 'Clustal' (Neighbor-joining approach) or 
        	'FastTree' (where Maximum likelihood is used), (default is 'Clustal').
        min_nongap_percentage_for_trim - (optional) minimum percentage of non-gapped positions in alignment column,
        	if you define this parameter in 50, then columns having less than half non-gapped letters are trimmed
        	(default value is 0 - it means no trimming at all). 
        out_workspace - (required) the workspace to deposit the completed tree
        out_tree_id - (optional) the name of the newly constructed tree (will be random if not present or null)
	*/
	typedef structure {
		ws_alignment_id msa_ref;
		string tree_method;
		int min_nongap_percentage_for_trim;
        string out_workspace;
        string out_tree_id;
	} ConstructTreeForAlignmentParams;

	/* Build a tree based on MSA object.
	*/
	funcdef construct_tree_for_alignment(ConstructTreeForAlignmentParams params) returns (job_id) authentication required; 

    /* Input data type for find_close_genomes method. Method produces list of refereces to public genomes similar to query.

        query_genome - (required) query genome reference
        max_mismatch_percent - (optional) defines maximum mismatch percentage when compare aminoacids from user genome with 
            public genomes (defualt value is 5).
    */
    typedef structure {
        genome_ref query_genome;
        int max_mismatch_percent;
    } FindCloseGenomesParams;

	/*
	Find closely related public genomes based on COG of ribosomal s9 subunits. 
	*/
	funcdef find_close_genomes(FindCloseGenomesParams params) returns (list<genome_ref>) authentication required;
	
	/* Input data type for guess_taxonomy_path method. Method produces taxonomy path string.

        query_genome - (required) query genome reference
    */
    typedef structure {
        genome_ref query_genome;
    } GuessTaxonomyPathParams;
	
	/*
	Search for taxonomy path from closely related public genomes (approach similar to find_close_genomes). 
	*/
	funcdef guess_taxonomy_path(GuessTaxonomyPathParams params) returns (string) authentication required;

		
    typedef structure {
        ws_tree_id tree_ref;
        ws_genomeset_id genomeset_ref;
    } BuildGenomeSetFromTreeParams;
	
	funcdef build_genome_set_from_tree(BuildGenomeSetFromTreeParams params) returns (ws_genomeset_id genomeset_ref) authentication required;
};
