package us.kbase.kbasetrees.util;

import java.util.List;
import java.util.Map;

import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.Tree;
import us.kbase.workspace.ProvenanceAction;

public class TreeImportPackage {

	protected Tree tree;
	protected String treeWsName;
	protected Map<String,String> treeMetadata;
	protected List<ProvenanceAction> treeProv;
	
	protected MSA msa;
	protected String msaWsName;
	protected Map<String,String> msaMetadata;
	protected List<ProvenanceAction> msaProv;
	
	public TreeImportPackage() { }

	public Tree getTree() {
		return tree;
	}

	public String getTreeWsName() {
		return treeWsName;
	}

	public Map<String, String> getTreeMetadata() {
		return treeMetadata;
	}

	public List<ProvenanceAction> getTreeProv() {
		return treeProv;
	}

	public MSA getMsa() {
		return msa;
	}

	public String getMsaWsName() {
		return msaWsName;
	}

	public Map<String, String> getMsaMetadata() {
		return msaMetadata;
	}

	public List<ProvenanceAction> getMsaProv() {
		return msaProv;
	}



	public void setTreeData(Tree tree, String treeWsName, Map<String, String> treeMetadata, List<ProvenanceAction> treeProv) {
		this.tree = tree;
		this.treeWsName = treeWsName;
		this.treeMetadata = treeMetadata;
		this.treeProv = treeProv;
	}

	public void setMsa(MSA msa, String msaWsName, Map<String, String> msaMetadata, List<ProvenanceAction> msaProv) {
		this.msa = msa;
		this.msaWsName = msaWsName;
		this.msaMetadata = msaMetadata;
		this.msaProv = msaProv;
	}
}
