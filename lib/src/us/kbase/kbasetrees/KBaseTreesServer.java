package us.kbase.kbasetrees;

import java.io.File;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import us.kbase.kbasetrees.SpeciesTreeBuilder;
import us.kbase.tree.TreeLocalClient;

import org.forester.io.parsers.nhx.NHXParser;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.iterators.PhylogenyNodeIterator;
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseTrees</p>
 * <pre>
 * Phylogenetic Tree and Multiple Sequence Alignment Services
 * This service provides a set of data types and methods for operating with multiple
 * sequence alignments (MSAs) and phylogenetic trees.
 * Authors
 * ---------
 * Michael Sneddon, LBL (mwsneddon@lbl.gov)
 * Fangfang Xia, ANL (fangfang.xia@gmail.com)
 * Keith Keller, LBL (kkeller@lbl.gov)
 * Matt Henderson, LBL (mhenderson@lbl.gov)
 * Dylan Chivian, LBL (dcchivian@lbl.gov)
 * Roman Sutormin, LBL (rsutormin@lbl.gov)
 * </pre>
 */
public class KBaseTreesServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;

    //BEGIN_CLASS_HEADER
    public static final String CFG_PROP_WS_SRV_URL = "workspace.srv.url";
    public static final String CFG_PROP_JSS_SRV_URL = "jobstatus.srv.url";
    public static final String CFG_PROP_TEMP_DIR = "scratch";
    public static final String CFG_PROP_DATA_DIR = "data.dir";
    public static final String CFG_PROP_PUBLIC_GENOMES_WS = "public.genomes.ws";
    public static final String SERVICE_VERSION = "1.0";
    
    public synchronized void runTask(ConstructSpeciesTreeParams inputData, String token) throws Exception {
        SpeciesTreeBuilder runner = new SpeciesTreeBuilder();
        runner.init(getWorkspaceUrl(), config);
        runner.run(token, inputData, runner.getOutRef(inputData));
    }
    
    public synchronized void runTask(ConstructMultipleAlignmentParams inputData, String token) throws Exception {
        MultipleAlignmentBuilder runner = new MultipleAlignmentBuilder();
        runner.init(getWorkspaceUrl(), config);
        runner.run(token, inputData, runner.getOutRef(inputData));
    }
    
    public synchronized void runTask(ConstructTreeForAlignmentParams inputData, String token) throws Exception {
        TreeForAlignmentBuilder runner = new TreeForAlignmentBuilder();
        runner.init(getWorkspaceUrl(), config);
        runner.run(token, inputData, runner.getOutRef(inputData));
    }
    
    public Map<String, String> getConfig() throws Exception {
		return config;
    }
    
    private String getWorkspaceUrl() throws Exception {
		return getConfig().get(CFG_PROP_WS_SRV_URL);
    }

    private String getTempDir() throws Exception {
        return getConfig().get(CFG_PROP_TEMP_DIR);
    }

	private TreeLocalClient fwd() throws Exception {
		TreeLocalClient ret = new TreeLocalClient(new File(getTempDir()));
		ret.setBinDir(new File("bin"));
		return ret;
	}
	
	static private boolean isDouble(String s) {
		try { Double.parseDouble(s); }
		catch (final Exception e ) { return false; }
		return true;
	}
	
	public String getServiceVersion() { 
		return SERVICE_VERSION;
	}
	
    //END_CLASS_HEADER

    public KBaseTreesServer() throws Exception {
        super("KBaseTrees");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: replace_node_names</p>
     * <pre>
     * Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
     * with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
     * regular expression patterns.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   replacements   instance of mapping from original type "node_id" to original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.replace_node_names", async=true)
    public String replaceNodeNames(String tree, Map<String,String> replacements, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN replace_node_names
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        StringBuilder relabeledTrees = new StringBuilder();
        for(int k=0; k<trees.length; k++) {
            for( final PhylogenyNodeIterator it = trees[k].iteratorPostorder(); it.hasNext(); ) {
            	PhylogenyNode node = it.next();
            	String replacement = replacements.get(node.getName());
            	if(replacement != null) {
            		node.setName(replacement);
            	}
            }
            relabeledTrees.append(trees[k].toNewHampshire());
        }
        returnVal = relabeledTrees.toString();
        //END replace_node_names
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: remove_node_names_and_simplify</p>
     * <pre>
     * Given a tree in newick format, remove the nodes with the given names indicated in the list, and
     * simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
     * child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
     * conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   removalList   instance of list of original type "node_id"
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.remove_node_names_and_simplify", async=true)
    public String removeNodeNamesAndSimplify(String tree, List<String> removalList, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN remove_node_names_and_simplify
        
        // convert removal list to a map for fast searching
        Map<String,String> removalMap = new HashMap<String,String>();
        for (String r : removalList) { removalMap.put(r,""); }
        
        // parse the tree
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        
        // restructure the tree
        StringBuilder relabeledTrees = new StringBuilder();
        for(int k=0; k<trees.length; k++) {
            // for each tree, iterate over the nodes and remove any that we can match
            for( final PhylogenyNodeIterator it = trees[k].iteratorPostorder(); it.hasNext(); ) {
                PhylogenyNode node = it.next();
                if(removalMap.containsKey(node.getName())) {
                    PhylogenyMethods.removeNode(node, trees[k]);
                } else if ((!node.isRoot()) && (node.getName().equals("") || isDouble(node.getName()))) {
                    // simplify the tree by 1) removing leaf nodes that have no name (or only a bootstrap value)
                    if(node.isExternal()) {
                        PhylogenyMethods.removeNode(node, trees[k]);
                    // and by 2) removing internal nodes that are not named and have only one child
                    } else if(node.getNumberOfDescendants()==1){
                        PhylogenyMethods.removeNode(node, trees[k]);
                    }
                }
            }
            relabeledTrees.append(trees[k].toNewHampshire());
        }
        returnVal = relabeledTrees.toString();
        //END remove_node_names_and_simplify
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: merge_zero_distance_leaves</p>
     * <pre>
     * Some KBase trees keep information on canonical feature ids, even if they have the same protien sequence
     * in an alignment.  In these cases, some leaves with identical sequences will have zero distance so that
     * information on canonical features is maintained.  Often this information is not useful, and a single
     * example feature or genome is sufficient.  This method will accept a tree in newick format (with distances)
     * and merge all leaves that have zero distance between them (due to identical sequences), and keep arbitrarily
     * only one of these leaves.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.merge_zero_distance_leaves", async=true)
    public String mergeZeroDistanceLeaves(String tree, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN merge_zero_distance_leaves
        // parse the tree
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        
        // restructure the tree
        StringBuilder relabeledTrees = new StringBuilder();
        for(int k=0; k<trees.length; k++) {
            // first pass over leaf nodes, flag the parents if the distance to the parent is zero
            Map <Long,Integer> parentListTarget = new HashMap<Long,Integer>();
            for( final PhylogenyNodeIterator it = trees[k].iteratorExternalForward(); it.hasNext(); ) {
                PhylogenyNode leaf = it.next();
                if(leaf.getDistanceToParent()==0) {
                    if(leaf.getParent().getName().equals("") || isDouble(leaf.getParent().getName())) {
                    	long parentId = leaf.getParent().getId();
                        Integer zeroCount = parentListTarget.get(parentId);
                        if(zeroCount==null) {
                        	parentListTarget.put(new Long(parentId), new Integer(1));
                        } else {
                        	parentListTarget.put(new Long(parentId), new Integer(zeroCount.intValue()+1));
                        }
                    }
                }
            }
            // now pass over the marked parents, check if all leaf nodes were distance zero, and if
            // so, we remove the parent and replace it with the first child node
            for (Map.Entry<Long, Integer> pair : parentListTarget.entrySet()) {
                PhylogenyNode parent = trees[k].getNode((int)(long)pair.getKey());
                if(pair.getValue().intValue() == parent.getNumberOfDescendants()) {
                    // remove it.
                    for(int c=parent.getNumberOfDescendants()-1; c>0; c--) {
                        parent.removeChildNode(c);
                    }
                    PhylogenyMethods.removeNode(parent, trees[k]);
                }
            }
            relabeledTrees.append(trees[k].toNewHampshire());
        }
        returnVal = relabeledTrees.toString();
        //END merge_zero_distance_leaves
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: extract_leaf_node_names</p>
     * <pre>
     * Given a tree in newick format, list the names of the leaf nodes.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     */
    @JsonServerMethod(rpc = "KBaseTrees.extract_leaf_node_names", async=true)
    public List<String> extractLeafNodeNames(String tree, RpcContext jsonRpcContext) throws Exception {
        List<String> returnVal = null;
        //BEGIN extract_leaf_node_names
        // parse the tree
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        //if(trees.length!=1) {
        	// might want to handle errors if we did not parse out one tree... for now we allow multiple trees
        //}
        List <String> leafNodeNames = new ArrayList<String>();
        for(int k=0; k<trees.length; k++) {
            String [] names = trees[k].getAllExternalNodeNames();
            leafNodeNames.addAll(Arrays.asList(names));
		}
        returnVal = leafNodeNames;
        //END extract_leaf_node_names
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: extract_node_names</p>
     * <pre>
     * Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
     * those originating from MicrobesOnline, the names of internal nodes may be bootstrap values, but will still
     * be returned by this function.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     */
    @JsonServerMethod(rpc = "KBaseTrees.extract_node_names", async=true)
    public List<String> extractNodeNames(String tree, RpcContext jsonRpcContext) throws Exception {
        List<String> returnVal = null;
        //BEGIN extract_node_names
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        //if(trees.length!=1) {
            // might want to handle errors if we did not parse out one tree... for now we allow multiple trees
        //}
        List <String> nodeNames = new ArrayList<String>();
        for(int k=0; k<trees.length; k++) {
            // we need to loop because forester does not provide another fast way to get all node names
            for( final PhylogenyNodeIterator it = trees[k].iteratorPostorder(); it.hasNext(); ) {
                nodeNames.add(it.next().getName());
            }
        }
        returnVal = nodeNames;
        //END extract_node_names
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_node_count</p>
     * <pre>
     * Given a tree, return the total number of nodes, including internal nodes and the root node.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of Long
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_node_count", async=true)
    public Long getNodeCount(String tree, RpcContext jsonRpcContext) throws Exception {
        Long returnVal = null;
        //BEGIN get_node_count
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        //if(trees.length!=1) {
            //might want to handle errors if we did not parse out one tree... for now we allow multiple trees
        //}
        long totalCount=0;
        for(int k=0; k<trees.length; k++) {
        	totalCount += trees[k].getNodeCount();
        }
        returnVal = totalCount;
        //END get_node_count
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_leaf_count</p>
     * <pre>
     * Given a tree, return the total number of leaf nodes, (internal and root nodes are ignored).  When the
     * tree was based on a multiple sequence alignment, the number of leaves will match the number of sequences
     * that were aligned.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of Long
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_leaf_count", async=true)
    public Long getLeafCount(String tree, RpcContext jsonRpcContext) throws Exception {
        Long returnVal = null;
        //BEGIN get_leaf_count
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        //if(trees.length!=1) {
            // TODO: might want to handle errors if we did not parse out one tree... for now we allow multiple trees
        //}
        long leafCount=0;
        for(int k=0; k<trees.length; k++) {
        	leafCount += trees[k].getNumberOfExternalNodes();
        }
        returnVal = leafCount;
        //END get_leaf_count
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: draw_html_tree</p>
     * <pre>
     * Given a tree structure in newick, render it in HTML/JAVASCRIPT and return the page as a string. display_options
     * provides a way to pass parameters to the tree rendering algorithm, but currently no options are recognized.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   displayOptions   instance of mapping from String to String
     * @return   instance of original type "html_file" (String in HTML format, used in the KBase Tree library for returning rendered trees.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.draw_html_tree", async=true)
    public String drawHtmlTree(String tree, Map<String,String> displayOptions, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN draw_html_tree
        returnVal = fwd().drawHtmlTree(tree, displayOptions);
        //END draw_html_tree
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: construct_species_tree</p>
     * <pre>
     * Build a species tree out of a set of given genome references.
     * </pre>
     * @param   input   instance of type {@link us.kbase.kbasetrees.ConstructSpeciesTreeParams ConstructSpeciesTreeParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.construct_species_tree", async=true)
    public String constructSpeciesTree(ConstructSpeciesTreeParams input, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN construct_species_tree
        runTask(input, authPart.toString());
        //END construct_species_tree
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: construct_multiple_alignment</p>
     * <pre>
     * Build a multiple sequence alignment based on gene sequences.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.ConstructMultipleAlignmentParams ConstructMultipleAlignmentParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.construct_multiple_alignment", async=true)
    public String constructMultipleAlignment(ConstructMultipleAlignmentParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN construct_multiple_alignment
        runTask(params, authPart.toString());
        //END construct_multiple_alignment
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: construct_tree_for_alignment</p>
     * <pre>
     * Build a tree based on MSA object.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.ConstructTreeForAlignmentParams ConstructTreeForAlignmentParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.construct_tree_for_alignment", async=true)
    public String constructTreeForAlignment(ConstructTreeForAlignmentParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN construct_tree_for_alignment
        runTask(params, authPart.toString());
        //END construct_tree_for_alignment
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: find_close_genomes</p>
     * <pre>
     * Find closely related public genomes based on COG of ribosomal s9 subunits.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.FindCloseGenomesParams FindCloseGenomesParams}
     * @return   instance of list of original type "genome_ref" (A convenience type representing a genome id reference. This might be a kbase_id (in the case of a CDM genome) or, more likely, a workspace reference of the structure "ws/obj/ver")
     */
    @JsonServerMethod(rpc = "KBaseTrees.find_close_genomes", async=true)
    public List<String> findCloseGenomes(FindCloseGenomesParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<String> returnVal = null;
        //BEGIN find_close_genomes
        returnVal = CloseGenomesFinder.findGenomes(authPart.toString(), params, getWorkspaceUrl(), config);
        //END find_close_genomes
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: guess_taxonomy_path</p>
     * <pre>
     * Search for taxonomy path from closely related public genomes (approach similar to find_close_genomes).
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.GuessTaxonomyPathParams GuessTaxonomyPathParams}
     * @return   instance of String
     */
    @JsonServerMethod(rpc = "KBaseTrees.guess_taxonomy_path", async=true)
    public String guessTaxonomyPath(GuessTaxonomyPathParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN guess_taxonomy_path
        returnVal = CloseGenomesFinder.guessTaxonomy(authPart.toString(), params, getWorkspaceUrl(), config);
        //END guess_taxonomy_path
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: build_genome_set_from_tree</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.BuildGenomeSetFromTreeParams BuildGenomeSetFromTreeParams}
     * @return   parameter "genomeset_ref" of original type "ws_genomeset_id" (A workspace ID that references a GenomeSet data object. @id ws KBaseSearch.GenomeSet)
     */
    @JsonServerMethod(rpc = "KBaseTrees.build_genome_set_from_tree", async=true)
    public String buildGenomeSetFromTree(BuildGenomeSetFromTreeParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN build_genome_set_from_tree
        returnVal = GenomeSetBuilder.buildGenomeSetFromTree(getWorkspaceUrl(), authPart.toString(), params.getTreeRef(), params.getGenomesetRef());
        //END build_genome_set_from_tree
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new KBaseTreesServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new KBaseTreesServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
