package us.kbase.kbasetrees;

import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.Tuple11;


//BEGIN_HEADER
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.ArrayList;

import org.ini4j.Ini;

import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.common.taskqueue.JobStatuses;
import us.kbase.common.taskqueue.TaskQueue;
import us.kbase.common.taskqueue.TaskQueueConfig;
import us.kbase.kbasetrees.SpeciesTreeBuilder;
import us.kbase.kbasetrees.util.CdsUtil;
import us.kbase.kbasetrees.util.TreeImportPackage;
import us.kbase.tree.TreeClient;
import us.kbase.userandjobstate.InitProgress;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspace.ListWorkspacesParams;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.common.service.UObject;
import us.kbase.workspace.WorkspaceClient;

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
    private static TaskQueue taskHolder = null;
    
	private static final String defaultWsUrl = "http://dev04.berkeley.kbase.us:7058";
	//private static final String defaultWsUrl = "https://kbase.us/services/ws/";
	private static final String defaultCdmiUrl = "https://kbase.us/services/cdmi_api/";
    private static final String defaultUjsUrl = "https://kbase.us/services/userandjobstate/";
    private static final String specServiceName = "trees";
    
    public static final String SYS_PROP_KB_DEPLOYMENT_CONFIG = "KB_DEPLOYMENT_CONFIG";
    
    public static final String CFG_PROP_THREAD_COUNT = "thread.count";
    public static final String CFG_PROP_QUEUE_DB_DIR = "queue.db.dir";
    public static final String CFG_PROP_WS_SRV_URL = "workspace.srv.url";
    public static final String CFG_PROP_JSS_SRV_URL = "jobstatus.srv.url";
    public static final String CFG_PROP_TEMP_DIR = "scratch";
    public static final String CFG_PROP_DATA_DIR = "data.dir";

    static {
    	// Setup service name
    	String KB_SERVNAME = "KB_SERVICE_NAME";
    	System.setProperty(KB_SERVNAME, specServiceName);
    	System.out.println(KBaseTreesServer.class.getName() + ": Service name was defined: " + specServiceName);
    	// Setup deployment configuration path
		String configPath = System.getProperty(SYS_PROP_KB_DEPLOYMENT_CONFIG);
		if (configPath == null)
			configPath = System.getenv(SYS_PROP_KB_DEPLOYMENT_CONFIG);
		if (configPath == null) {
			InputStream is = KBaseTreesServer.class.getResourceAsStream("config_path.properties");
			try {
				Properties props = new Properties();
				props.load(is);
				configPath = props.getProperty("config_path");
				System.setProperty(SYS_PROP_KB_DEPLOYMENT_CONFIG, configPath);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try { is.close(); } catch (Exception ignore) {}
			}
		}
    }
    
    public static synchronized TaskQueue getTaskQueue() throws Exception {
    	if (taskHolder == null) {
    		int threadCount = 1;
    		File queueDbDir = new File(".");
    		String wsUrl = defaultWsUrl;
    		String ujsUrl = defaultUjsUrl;
    		
    		Map<String, String> allConfigProps = loadConfig();
    		if (allConfigProps.containsKey(CFG_PROP_THREAD_COUNT))
    			threadCount = Integer.parseInt(allConfigProps.get(CFG_PROP_THREAD_COUNT));
    		if (allConfigProps.containsKey(CFG_PROP_QUEUE_DB_DIR))
    			queueDbDir = new File(allConfigProps.get(CFG_PROP_QUEUE_DB_DIR));
    		if (allConfigProps.containsKey(CFG_PROP_WS_SRV_URL))
    			wsUrl = allConfigProps.get(CFG_PROP_WS_SRV_URL);
    		if (allConfigProps.containsKey(CFG_PROP_JSS_SRV_URL))
    			ujsUrl = allConfigProps.get(CFG_PROP_JSS_SRV_URL);
    		for (Object key : allConfigProps.keySet())
    			allConfigProps.put(key.toString(), allConfigProps.get(key.toString()));
    		final String finalWsUrl = wsUrl;
    		final String finalUjsUrl = ujsUrl;
    		JobStatuses jobStatuses = new JobStatuses() {
				@Override
				public String createAndStartJob(String token, String status, String desc,
						String initProgressPtype, String estComplete) throws Exception {
    				return createJobClient(finalUjsUrl, token).createAndStartJob(token, status, desc, 
    						new InitProgress().withPtype(initProgressPtype), estComplete);
				}
				@Override
				public void updateJob(String job, String token, String status,
						String estComplete) throws Exception {
    				createJobClient(finalUjsUrl, token).updateJob(job, token, status, estComplete);
				}
				@Override
				public void completeJob(String job, String token, String status,
						String error, String wsUrl, String outRef) throws Exception {
    				createJobClient(finalUjsUrl, token).completeJob(job, token, status, error, 
    						new Results().withWorkspaceurl(finalWsUrl).withWorkspaceids(Arrays.asList(outRef)));
				}
			};
			taskHolder = new TaskQueue(new TaskQueueConfig(threadCount, queueDbDir, jobStatuses, wsUrl, 
					allConfigProps), new SpeciesTreeBuilder(), new MultipleAlignmentBuilder(),
					new TreeForAlignmentBuilder());
			System.out.println("Initial queue size: " + TaskQueue.getDbConnection(queueDbDir).collect(
					"select count(*) from " + TaskQueue.QUEUE_TABLE_NAME, new us.kbase.common.utils.DbConn.SqlLoader<Integer>() {
				public Integer collectRow(java.sql.ResultSet rs) throws java.sql.SQLException { return rs.getInt(1); }
			}));
    	}
    	return taskHolder;
    }
    
    private static Map<String, String> loadConfig() throws Exception {
		String configPath = System.getProperty(SYS_PROP_KB_DEPLOYMENT_CONFIG);
		System.out.println(KBaseTreesServer.class.getName() + ": Deployment config path was defined: " + configPath);
		return new Ini(new File(configPath)).get(specServiceName);
    }
    
	private static UserAndJobStateClient createJobClient(String jobSrvUrl, String token) throws IOException, JsonClientException {
		try {
			UserAndJobStateClient ret = new UserAndJobStateClient(new URL(jobSrvUrl), new AuthToken(token));
			ret.setAuthAllowedForHttp(true);
			return ret;
		} catch (TokenFormatException e) {
			throw new JsonClientException(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			throw new JsonClientException(e.getMessage(), e);
		}
	}
	
	private TreeClient fwd() throws Exception {
		String forwardUrl = super.config.get("forward.url");
		if (forwardUrl == null)
			throw new IllegalStateException("Parameter forward.url is not defined in configuration");
		TreeClient ret = null;
		Exception err = null;
		for (int i = 0; i < 3; i++) {
			try {
				TreeClient cl = new TreeClient(new URL(forwardUrl));
				cl.extractLeafNodeNames("(k);");
				ret = cl;
				break;
			} catch (Exception ex) {
				err = ex;
			}
		}
		if (ret == null)
			throw err;
		return ret;
	}
	
	static private boolean isDouble(String s) {
		try { Double.parseDouble(s); }
		catch (final Exception e ) { return false; }
		return true;
	}
	
	/**
	 * For legacy support, we make sure that if the module has the old name,
	 * we redirect to use the new name
	 */
	@Override
	protected String correctRpcMethod(String methodWithModule) {
		int pos = methodWithModule.indexOf('.');
		String module = methodWithModule.substring(0, pos);
		String method = methodWithModule.substring(pos + 1);
		if(module.equals("Tree")) {
			module = "KBaseTrees";
		}
		return module+"."+method;
	}
	
	@Override
	public void stopServer() throws Exception {
		super.stopServer();
		if (taskHolder != null)
			taskHolder.stopAllThreads();
	}
	
	private static String registerConfigFile(File configFile) {
		System.setProperty(SYS_PROP_KB_DEPLOYMENT_CONFIG, configFile.getAbsolutePath());
		return "";
	}
	
	public KBaseTreesServer(File configFile) throws Exception {
        super("KBaseTrees" + registerConfigFile(configFile));
	}
	
	public static String getServiceVersion() { 
		return "1.0";
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
    @JsonServerMethod(rpc = "KBaseTrees.replace_node_names")
    public String replaceNodeNames(String tree, Map<String,String> replacements) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.remove_node_names_and_simplify")
    public String removeNodeNamesAndSimplify(String tree, List<String> removalList) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.merge_zero_distance_leaves")
    public String mergeZeroDistanceLeaves(String tree) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.extract_leaf_node_names")
    public List<String> extractLeafNodeNames(String tree) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.extract_node_names")
    public List<String> extractNodeNames(String tree) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.get_node_count")
    public Long getNodeCount(String tree) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.get_leaf_count")
    public Long getLeafCount(String tree) throws Exception {
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
     * <p>Original spec-file function name: get_tree</p>
     * <pre>
     * Returns the specified tree in the specified format, or an empty string if the tree does not exist.
     * The options hash provides a way to return the tree with different labels replaced or with different attached meta
     * information.  Currently, the available flags and understood options are listed below. 
     *     options = [
     *         format => 'newick',
     *         newick_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' ||
     *                         'contig_sequence_id' || 'best_feature_id' || 'best_genome_id',
     *         newick_bootstrap => 'none' || 'internal_node_labels'
     *         newick_distance => 'none' || 'raw'
     *     ];
     *  
     * The 'format' key indicates what string format the tree should be returned in.  Currently, there is only
     * support for 'newick'. The default value if not specified is 'newick'.
     * The 'newick_label' key only affects trees returned as newick format, and specifies what should be
     * placed in the label of each leaf.  'none' indicates that no label is added, so you get the structure
     * of the tree only.  'raw' indicates that the raw label mapping the leaf to an alignement row is used.
     * 'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
     * feature that was originally used to define the sequence). Note that exemplar feature_ids are not
     * defined for all trees, so this may result in an empty tree! 'protein_sequence_id' indicates that the
     * kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
     * the contig sequence id is added.  Note that trees are typically built with protein sequences OR
     * contig sequences. If you select one type of sequence, but the tree was built with the other type, then
     * no labels will be added.  'best_feature_id' is used in the frequent case where a protein sequence has
     * been mapped to multiple feature ids, and an example feature_id is used.  Similarly, 'best_genome_id'
     * replaces the labels with the best example genome_id.  The default value if none is specified is 'raw'.
     * The 'newick_bootstrap' key allows control over whether bootstrap values are returned if they exist, and
     * how they are returned.  'none' indicates that no bootstrap values are returned. 'internal_node_labels'
     * indicates that bootstrap values are returned as internal node labels.  Default value is 'internal_node_labels';
     * The 'newick_distance' key allows control over whether distance labels are generated or not.  If set to
     * 'none', no distances will be output. Default is 'raw', which outputs the distances exactly as they appeared
     * when loaded into KBase.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @param   options   instance of mapping from String to String
     * @return   instance of original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_tree")
    public String getTree(String treeId, Map<String,String> options) throws Exception {
        String returnVal = null;
        //BEGIN get_tree
        returnVal = fwd().getTree(treeId, options);
        //END get_tree
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_alignment</p>
     * <pre>
     * Returns the specified alignment in the specified format, or an empty string if the alignment does not exist.
     * The options hash provides a way to return the alignment with different labels replaced or with different attached meta
     * information.  Currently, the available flags and understood options are listed below. 
     *     options = [
     *         format => 'fasta',
     *         sequence_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' || 'contig_sequence_id',
     *     ];
     *  
     * The 'format' key indicates what string format the alignment should be returned in.  Currently, there is only
     * support for 'fasta'. The default value if not specified is 'fasta'.
     * The 'sequence_label' specifies what should be placed in the label of each sequence.  'none' indicates that
     * no label is added, so you get the sequence only.  'raw' indicates that the raw label of the alignement row
     * is used. 'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
     * feature that was originally used to define the sequence). Note that exemplar feature_ids are not
     * defined for all alignments, so this may result in an unlabeled alignment.  'protein_sequence_id' indicates
     * that the kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
     * the contig sequence id is used.  Note that trees are typically built with protein sequences OR
     * contig sequences. If you select one type of sequence, but the alignment was built with the other type, then
     * no labels will be added.  The default value if none is specified is 'raw'.
     * </pre>
     * @param   alignmentId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @param   options   instance of mapping from String to String
     * @return   instance of original type "alignment" (String representation of a sequence alignment, the format of which may be different depending on input options for retrieving the alignment.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_alignment")
    public String getAlignment(String alignmentId, Map<String,String> options) throws Exception {
        String returnVal = null;
        //BEGIN get_alignment
        returnVal = fwd().getAlignment(alignmentId, options);
        //END get_alignment
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_tree_data</p>
     * <pre>
     * Get meta data associated with each of the trees indicated in the list by tree id.  Note that some meta
     * data may not be available for trees which are not built from alignments.  Also note that this method
     * computes the number of nodes and leaves for each tree, so may be slow for very large trees or very long
     * lists.  If you do not need this full meta information structure, it may be faster to directly query the
     * CDS for just the field you need using the CDMI.
     * </pre>
     * @param   treeIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb) to type {@link us.kbase.kbasetrees.TreeMetaData TreeMetaData}
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_tree_data")
    public Map<String,TreeMetaData> getTreeData(List<String> treeIds) throws Exception {
        Map<String,TreeMetaData> returnVal = null;
        //BEGIN get_tree_data
        returnVal = fwd().getTreeData(treeIds);
        //END get_tree_data
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_alignment_data</p>
     * <pre>
     * Get meta data associated with each of the trees indicated in the list by tree id.  Note that some meta
     * data may not be available for trees which are not built from alignments.  Also note that this method
     * computes the number of nodes and leaves for each tree, so may be slow for very large trees or very long
     * lists.  If you do not need this full meta information structure, it may be faster to directly query the
     * CDS for just the field you need using the CDMI.
     * </pre>
     * @param   alignmentIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb) to type {@link us.kbase.kbasetrees.AlignmentMetaData AlignmentMetaData}
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_alignment_data")
    public Map<String,AlignmentMetaData> getAlignmentData(List<String> alignmentIds) throws Exception {
        Map<String,AlignmentMetaData> returnVal = null;
        //BEGIN get_alignment_data
        returnVal = fwd().getAlignmentData(alignmentIds);
        //END get_alignment_data
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_feature</p>
     * <pre>
     * Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
     * is identified and used to retrieve all trees by ID that were built using the given protein seqence.
     * </pre>
     * @param   featureIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_tree_ids_by_feature")
    public List<String> getTreeIdsByFeature(List<String> featureIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_tree_ids_by_feature
        returnVal = fwd().getTreeIdsByFeature(featureIds);
        //END get_tree_ids_by_feature
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_protein_sequence</p>
     * <pre>
     * Given a list of kbase ids of a protein sequences (their MD5s), retrieve the tree ids of trees that
     * were built based on these sequences.
     * </pre>
     * @param   proteinSequenceIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_tree_ids_by_protein_sequence")
    public List<String> getTreeIdsByProteinSequence(List<String> proteinSequenceIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_tree_ids_by_protein_sequence
        returnVal = fwd().getTreeIdsByProteinSequence(proteinSequenceIds);
        //END get_tree_ids_by_protein_sequence
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_alignment_ids_by_feature</p>
     * <pre>
     * Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
     * is identified and used to retrieve all alignments by ID that were built using the given protein sequence.
     * </pre>
     * @param   featureIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_alignment_ids_by_feature")
    public List<String> getAlignmentIdsByFeature(List<String> featureIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_alignment_ids_by_feature
        returnVal = fwd().getAlignmentIdsByFeature(featureIds);
        //END get_alignment_ids_by_feature
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_alignment_ids_by_protein_sequence</p>
     * <pre>
     * Given a list of kbase ids of a protein sequences (their MD5s), retrieve the alignment ids of trees that
     * were built based on these sequences.
     * </pre>
     * @param   proteinSequenceIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_alignment_ids_by_protein_sequence")
    public List<String> getAlignmentIdsByProteinSequence(List<String> proteinSequenceIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_alignment_ids_by_protein_sequence
        returnVal = fwd().getAlignmentIdsByProteinSequence(proteinSequenceIds);
        //END get_alignment_ids_by_protein_sequence
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_source_id_pattern</p>
     * <pre>
     * This method searches for a tree having a source ID that matches the input pattern.  This method accepts
     * one argument, which is the pattern.  The pattern is very simple and includes only two special characters,
     * wildcard character, '*', and a match-once character, '.'  The wildcard character matches any number (including
     * 0) of any character, the '.' matches exactly one of any character.  These special characters can be escaped
     * with a backslash.  To match a blackslash literally, you must also escape it.  Note that source IDs are
     * generally defined by the gene family model which was used to identifiy the sequences to be included in
     * the tree.  Therefore, matching a source ID is a convenient way to find trees for a specific set of gene
     * families.
     * </pre>
     * @param   pattern   instance of String
     * @return   instance of list of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_tree_ids_by_source_id_pattern")
    public List<List<String>> getTreeIdsBySourceIdPattern(String pattern) throws Exception {
        List<List<String>> returnVal = null;
        //BEGIN get_tree_ids_by_source_id_pattern
        returnVal = fwd().getTreeIdsBySourceIdPattern(pattern);
        //END get_tree_ids_by_source_id_pattern
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_leaf_to_protein_map</p>
     * <pre>
     * Given a tree id, this method returns a mapping from a tree's unique internal ID to
     * a protein sequence ID.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_leaf_to_protein_map")
    public Map<String,String> getLeafToProteinMap(String treeId) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_leaf_to_protein_map
        returnVal = fwd().getLeafToProteinMap(treeId);
        //END get_leaf_to_protein_map
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_leaf_to_feature_map</p>
     * <pre>
     * Given a tree id, this method returns a mapping from a tree's unique internal ID to
     * a KBase feature ID if and only if a cannonical feature id exists.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771". @id kb)
     */
    @JsonServerMethod(rpc = "KBaseTrees.get_leaf_to_feature_map")
    public Map<String,String> getLeafToFeatureMap(String treeId) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_leaf_to_feature_map
        returnVal = fwd().getLeafToFeatureMap(treeId);
        //END get_leaf_to_feature_map
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: import_tree_from_cds</p>
     * <pre>
     * </pre>
     * @param   selection   instance of list of type {@link us.kbase.kbasetrees.CdsImportTreeParameters CdsImportTreeParameters}
     * @param   targetWsNameOrId   instance of String
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. obj_id objid - the numerical id of the object. obj_name name - the name of the object. type_string type - the type of the object. timestamp save_date - the save date of the object. obj_ver ver - the version of the object. username saved_by - the user that saved or copied the object. ws_id wsid - the workspace containing the object. ws_name workspace - the workspace containing the object. string chsum - the md5 checksum of the object. int size - the size of the object in bytes. usermeta meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of Long, parameter "name" of String, parameter "type" of String, parameter "save_date" of String, parameter "version" of Long, parameter "saved_by" of String, parameter "wsid" of Long, parameter "workspace" of String, parameter "chsum" of String, parameter "size" of Long, parameter "meta" of mapping from String to String
     */
    @JsonServerMethod(rpc = "KBaseTrees.import_tree_from_cds")
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> importTreeFromCds(List<CdsImportTreeParameters> selection, String targetWsNameOrId, AuthToken authPart) throws Exception {
        List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> returnVal = null;
        //BEGIN import_tree_from_cds
        
        CdsUtil cds = new CdsUtil(new URL(defaultCdmiUrl));
        WorkspaceClient ws = new WorkspaceClient(new URL(defaultWsUrl), authPart);
        ws.setAuthAllowedForHttp(true);
        List <TreeImportPackage> tips = cds.getTreesForImport(selection, targetWsNameOrId);
        List<ObjectSaveData> msaData = new ArrayList<ObjectSaveData>(tips.size());
        List<ObjectSaveData> treeData = new ArrayList<ObjectSaveData>(tips.size());
        for(TreeImportPackage tip : tips) {
        	msaData.add(new ObjectSaveData()
        						.withName(tip.getMsaWsName())
        						.withMeta(tip.getMsaMetadata())
        						.withProvenance(tip.getMsaProv())
        						.withData(new UObject(tip.getMsa()))
        						.withType("KBaseTrees.MSA")
        					);
        	treeData.add(new ObjectSaveData()
        					.withName(tip.getTreeWsName())
        					.withMeta(tip.getTreeMetadata())
        					.withProvenance(tip.getTreeProv())
        					.withData(new UObject(tip.getTree()))
        					.withType("KBaseTrees.Tree")
        			);
        }
        long wsId = -1;
        try {
        	wsId = Long.parseLong(targetWsNameOrId);
        } catch(NumberFormatException e) { wsId=-1; }
        
        SaveObjectsParams sopMsa = new SaveObjectsParams().withObjects(msaData);
        SaveObjectsParams sopTree = new SaveObjectsParams().withObjects(treeData);
        if(wsId>=0) { sopMsa.setId(wsId); sopTree.setId(wsId); }
        else { sopMsa.setWorkspace(targetWsNameOrId); sopTree.setWorkspace(targetWsNameOrId); }
        returnVal = ws.saveObjects(sopMsa);
        returnVal.addAll(ws.saveObjects(sopTree));
        
        //END import_tree_from_cds
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: compute_abundance_profile</p>
     * <pre>
     * Given an input KBase tree built from a sequence alignment, a metagenomic sample, and a protein family, this method
     * will tabulate the number of reads that match to every leaf of the input tree.  First, a set of assembled reads from
     * a metagenomic sample are pulled from the KBase communities service which have been determined to be a likely hit
     * to the specified protein family.  Second, the sequences aligned to generate the tree are retrieved.  Third, UCLUST [1]
     * is used to map reads to target sequences of the tree.  Finally, for each leaf in the tree, the number of hits matching
     * the input search criteria is tabulated and returned.  See the defined objects 'abundance_params' and 'abundance_result'
     * for additional details on specifying the input parameters and handling the results.
     * [1] Edgar, R.C. (2010) Search and clustering orders of magnitude faster than BLAST, Bioinformatics 26(19), 2460-2461.
     * </pre>
     * @param   abundanceParams   instance of type {@link us.kbase.kbasetrees.AbundanceParams AbundanceParams}
     * @return   parameter "abundance_result" of type {@link us.kbase.kbasetrees.AbundanceResult AbundanceResult}
     */
    @JsonServerMethod(rpc = "KBaseTrees.compute_abundance_profile")
    public AbundanceResult computeAbundanceProfile(AbundanceParams abundanceParams) throws Exception {
        AbundanceResult returnVal = null;
        //BEGIN compute_abundance_profile
        returnVal = fwd().computeAbundanceProfile(abundanceParams);
        //END compute_abundance_profile
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: filter_abundance_profile</p>
     * <pre>
     * ORDER OF OPERATIONS:
     * 1) using normalization scope, defines whether process should occur per column or globally over every column
     * 2) using normalization type, normalize by dividing values by the option indicated
     * 3) apply normalization post process if set (ie take log of the result)
     * 4) apply the cutoff_value threshold to all records, eliminating any that are not above the specified threshold
     * 5) apply the cutoff_number_of_records (always applies per_column!!!), discarding any record that are not in the top N record values for that column
     * - if a value is not a valid number, it is ignored
     * </pre>
     * @param   abundanceData   instance of original type "abundance_data" (map the name of the profile with the profile data) &rarr; mapping from String to original type "abundance_profile" (map an id to a number (e.g. feature_id mapped to a log2 normalized abundance value)) &rarr; mapping from String to Double
     * @param   filterParams   instance of type {@link us.kbase.kbasetrees.FilterParams FilterParams}
     * @return   parameter "abundance_data_processed" of original type "abundance_data" (map the name of the profile with the profile data) &rarr; mapping from String to original type "abundance_profile" (map an id to a number (e.g. feature_id mapped to a log2 normalized abundance value)) &rarr; mapping from String to Double
     */
    @JsonServerMethod(rpc = "KBaseTrees.filter_abundance_profile")
    public Map<String,Map<String,Double>> filterAbundanceProfile(Map<String,Map<String,Double>> abundanceData, FilterParams filterParams) throws Exception {
        Map<String,Map<String,Double>> returnVal = null;
        //BEGIN filter_abundance_profile
        returnVal = fwd().filterAbundanceProfile(abundanceData, filterParams);
        //END filter_abundance_profile
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
    @JsonServerMethod(rpc = "KBaseTrees.draw_html_tree")
    public String drawHtmlTree(String tree, Map<String,String> displayOptions) throws Exception {
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
    @JsonServerMethod(rpc = "KBaseTrees.construct_species_tree")
    public String constructSpeciesTree(ConstructSpeciesTreeParams input, AuthToken authPart) throws Exception {
        String returnVal = null;
        //BEGIN construct_species_tree
        TaskQueue tq = getTaskQueue();
        returnVal = tq.addTask(input, authPart.toString());
        //END construct_species_tree
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: construct_multiple_alignment</p>
     * <pre>
     * Build a multiple sequence alignment based on gene sequences.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.ConstructMultipleAlignment ConstructMultipleAlignment}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     */
    @JsonServerMethod(rpc = "KBaseTrees.construct_multiple_alignment")
    public String constructMultipleAlignment(ConstructMultipleAlignment params, AuthToken authPart) throws Exception {
        String returnVal = null;
        //BEGIN construct_multiple_alignment
        TaskQueue tq = getTaskQueue();
        returnVal = tq.addTask(params, authPart.toString());
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
    @JsonServerMethod(rpc = "KBaseTrees.construct_tree_for_alignment")
    public String constructTreeForAlignment(ConstructTreeForAlignmentParams params, AuthToken authPart) throws Exception {
        String returnVal = null;
        //BEGIN construct_tree_for_alignment
        TaskQueue tq = getTaskQueue();
        returnVal = tq.addTask(params, authPart.toString());
        //END construct_tree_for_alignment
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: <program> <server_port>");
            return;
        }
        new KBaseTreesServer().startupServer(Integer.parseInt(args[0]));
    }
}
