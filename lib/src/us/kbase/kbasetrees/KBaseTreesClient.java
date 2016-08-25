package us.kbase.kbasetrees;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

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
public class KBaseTreesClient {
    private JsonClientCaller caller;
    private String serviceVersion = null;


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public KBaseTreesClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public KBaseTreesClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public KBaseTreesClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

    /** Constructs a client with a custom URL
     * and a custom authorization service URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @param auth the URL of the authorization server.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public KBaseTreesClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
    }

    /** Get the token this client uses to communicate with the server.
     * @return the authorization token.
     */
    public AuthToken getToken() {
        return caller.getToken();
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String replaceNodeNames(String tree, Map<String,String> replacements, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(replacements);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.replace_node_names", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String removeNodeNamesAndSimplify(String tree, List<String> removalList, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(removalList);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.remove_node_names_and_simplify", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String mergeZeroDistanceLeaves(String tree, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.merge_zero_distance_leaves", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: extract_leaf_node_names</p>
     * <pre>
     * Given a tree in newick format, list the names of the leaf nodes.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> extractLeafNodeNames(String tree, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("KBaseTrees.extract_leaf_node_names", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> extractNodeNames(String tree, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("KBaseTrees.extract_node_names", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_node_count</p>
     * <pre>
     * Given a tree, return the total number of nodes, including internal nodes and the root node.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long getNodeCount(String tree, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("KBaseTrees.get_node_count", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long getLeafCount(String tree, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("KBaseTrees.get_leaf_count", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String drawHtmlTree(String tree, Map<String,String> displayOptions, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(displayOptions);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.draw_html_tree", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: construct_species_tree</p>
     * <pre>
     * Build a species tree out of a set of given genome references.
     * </pre>
     * @param   input   instance of type {@link us.kbase.kbasetrees.ConstructSpeciesTreeParams ConstructSpeciesTreeParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String constructSpeciesTree(ConstructSpeciesTreeParams input, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(input);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.construct_species_tree", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: construct_multiple_alignment</p>
     * <pre>
     * Build a multiple sequence alignment based on gene sequences.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.ConstructMultipleAlignmentParams ConstructMultipleAlignmentParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String constructMultipleAlignment(ConstructMultipleAlignmentParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.construct_multiple_alignment", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: construct_tree_for_alignment</p>
     * <pre>
     * Build a tree based on MSA object.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.ConstructTreeForAlignmentParams ConstructTreeForAlignmentParams}
     * @return   instance of original type "job_id" (A string representing a job id for manipulating trees. This is an id for a job that is registered with the User and Job State service.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String constructTreeForAlignment(ConstructTreeForAlignmentParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.construct_tree_for_alignment", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: find_close_genomes</p>
     * <pre>
     * Find closely related public genomes based on COG of ribosomal s9 subunits.
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.FindCloseGenomesParams FindCloseGenomesParams}
     * @return   instance of list of original type "genome_ref" (A convenience type representing a genome id reference. This might be a kbase_id (in the case of a CDM genome) or, more likely, a workspace reference of the structure "ws/obj/ver")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> findCloseGenomes(FindCloseGenomesParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("KBaseTrees.find_close_genomes", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: guess_taxonomy_path</p>
     * <pre>
     * Search for taxonomy path from closely related public genomes (approach similar to find_close_genomes).
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.GuessTaxonomyPathParams GuessTaxonomyPathParams}
     * @return   instance of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String guessTaxonomyPath(GuessTaxonomyPathParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.guess_taxonomy_path", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_genome_set_from_tree</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.kbasetrees.BuildGenomeSetFromTreeParams BuildGenomeSetFromTreeParams}
     * @return   parameter "genomeset_ref" of original type "ws_genomeset_id" (A workspace ID that references a GenomeSet data object. @id ws KBaseSearch.GenomeSet)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String buildGenomeSetFromTree(BuildGenomeSetFromTreeParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("KBaseTrees.build_genome_set_from_tree", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<Map<String, Object>>> retType = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> res = caller.jsonrpcCall("KBaseTrees.status", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }
}
