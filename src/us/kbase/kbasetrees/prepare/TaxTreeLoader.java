package us.kbase.kbasetrees.prepare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.ServerException;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple3;
import us.kbase.common.service.UObject;
import us.kbase.kbasetaxonomy.GenomeTaxon;
import us.kbase.kbasetaxonomy.Taxon;
import us.kbase.kbasetrees.util.WorkspaceUtil;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.GetModuleInfoParams;
import us.kbase.workspace.ListModuleVersionsParams;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class TaxTreeLoader {
	private static final String ncbiFtpZipUrl = "ftp://ftp.ncbi.nlm.nih.gov/pub/taxonomy/taxdmp.zip";
	private static final Pattern div = Pattern.compile(Pattern.quote("\t|\t")); 
	private static final String defaultTaxonomyWorkspaceName = "KBaseTaxonomyPublic";
	private static final int saveBlockSize = 100;
	
	public static void main(String[] args) throws Exception {
		//Map<String, Object> tree = loadTaxTreeFromFiles();
		//System.out.println("Json tax tree: " + new ObjectMapper().writeValueAsString(tree));
		//checkData();
		Map<String, String> testCfg = SpeciesTreePreparation.loadTestCfg();
		String user = testCfg.get("test.user1");
		String pwd = testCfg.get("test.pwd1");
		String token = AuthService.login(user, pwd).getTokenString();
		WorkspaceClient wc = new WorkspaceClient(new URL(testCfg.get("workspace.srv.url")), new AuthToken(token));
		wc.setIsInsecureHttpConnectionAllowed(true);
		String genomeWsName = testCfg.get("public.genomes.ws");
		Map<String, Tuple2<Integer, String>> mapGenomeRefToTaxId = GenomeIdTaxMapping.loadMapGenomeRefToTaxIdAndGenomeId(wc, genomeWsName);
		loadTaxTreeFromFiles(mapGenomeRefToTaxId, wc);
		//reg(wc);
	}
	
	public static void loadTaxTreeFromFiles(Map<String, Tuple2<Integer, String>> mapGenomeRefToTaxId, WorkspaceClient wc) throws Exception {
		Map<Integer, String> divisionName = new HashMap<Integer, String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(findZipEntry(ncbiFtpZipUrl, "division.dmp")));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			if (l.trim().length() == 0)
				continue;
			if (l.endsWith("\t|"))
				l = l.substring(0, l.length() - 2);
			String[] parts = div.split(l);
			if (parts.length < 3)
				throw new IllegalStateException("Wrong line format: [" + l + "]");
			int divCode = Integer.parseInt(parts[0]);
			divisionName.put(divCode, parts[2]);
		}
		br.close();
		Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>();
		br = new BufferedReader(new InputStreamReader(findZipEntry(ncbiFtpZipUrl, "names.dmp")));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			if (l.trim().length() == 0)
				continue;
			if (l.endsWith("\t|"))
				l = l.substring(0, l.length() - 2);
			String[] parts = div.split(l);
			if (parts.length != 4)
				throw new IllegalStateException("Wrong line format: [" + l + "]");
			boolean isMainName = parts[3].equals("scientific name");
			int nodeId = Integer.parseInt(parts[0]);
			String name = parts[1];
			TreeNode node = nodeMap.get(nodeId);
			if (node == null) {
				node = new TreeNode(new Taxon().withTaxId((long)nodeId).withAliases(new ArrayList<String>()));
				nodeMap.put(nodeId, node);
			}
			if (isMainName) {
				node.node.setName(name);
			} else {
				node.node.getAliases().add(name);
			}
		}
		br.close();
		System.out.println("All nodes=" + nodeMap.size() + ", genomes=" + mapGenomeRefToTaxId.size());
		int rootId = -1;
		int hidden = 0;
		br = new BufferedReader(new InputStreamReader(findZipEntry(ncbiFtpZipUrl, "nodes.dmp")));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			if (l.trim().length() == 0)
				continue;
			if (l.endsWith("\t|"))
				l = l.substring(0, l.length() - 2);
			String[] parts = div.split(l);
			if (parts.length < 12)
				throw new IllegalStateException("Wrong line format (" + parts.length + " fields): [" + l + "]");
			int nodeId = Integer.parseInt(parts[0]);
			int parentId = Integer.parseInt(parts[1]);
			TreeNode node = nodeMap.get(nodeId);
			if (node == null)
				throw new IllegalStateException("No node for id=" + nodeId);
			String rank = parts[2];
			int divisionId = Integer.parseInt(parts[4]);
			String division = divisionName.get(divisionId);
			if (division == null)
				throw new IllegalStateException("Can't find division for id: " + divisionId);
			int geneticCode = Integer.parseInt(parts[6]);
			int mitoGeneticCode = Integer.parseInt(parts[8]);
			node.node.withRank(rank).withDivision(division).withGeneticCode((long)geneticCode)
				.withMitoGeneticCode((long)mitoGeneticCode);
			node.hidden = parts[10].equals("1");
			if (nodeId == parentId) {
				rootId = nodeId;
				continue;
			}
			if (node.hidden) {
				hidden++;
			}
			TreeNode parent = nodeMap.get(parentId);
			if (parent == null)
				throw new IllegalStateException("No node for parent id=" + parentId);
			if (parent.children == null)
				parent.children = new ArrayList<TreeNode>();
			parent.children.add(node);
			node.node.setParentId((long)parentId);
		}
		br.close();
		System.out.println("Hidden in genbank: " + hidden);
		TreeNode root = nodeMap.get(rootId);
		TreeNode unknown = new TreeNode(new Taxon().withName("Unknown").withTaxId((long)0)
				.withParentId((long)rootId).withAliases(new ArrayList<String>()).withGeneticCode((long)0));
		root.children.add(unknown);
		nodeMap.put(0, unknown);
		for (String genomeRef : mapGenomeRefToTaxId.keySet()) {
			Tuple2<Integer, String> genomeTaxId = mapGenomeRefToTaxId.get(genomeRef);
			int nodeId = genomeTaxId.getE1();
			String genomeName = genomeTaxId.getE2();
			if (!nodeMap.containsKey(nodeId))
				nodeId = 0;
			TreeNode node = nodeMap.get(nodeId);
			node.genomeRefsToIds.put(genomeRef, genomeName);
		}
		removeHidden(null, root);
		System.out.println("After hidden removed: nodes=" + countNodes(root) + ", genomes=" + countGenomes(root));
		//removeNoGenomes(root);
		//System.out.println("After no-genome removed: nodes=" + countNodes(root) + ", genomes=" + countGenomes(root));
		final Map<String, String> taxonNameToRef = new HashMap<String, String>();
		for (Tuple2<String, String> entry : WorkspaceUtil.listAllObjectsRefAndName(wc, defaultTaxonomyWorkspaceName, "KBaseTaxonomy.Taxon"))
			taxonNameToRef.put(entry.getE2(), entry.getE1());
		final Map<String, String> genomeTaxonNameToRef = new HashMap<String, String>();
		for (Tuple2<String, String> entry : WorkspaceUtil.listAllObjectsRefAndName(wc, defaultTaxonomyWorkspaceName, "KBaseTaxonomy.GenomeTaxon"))
			genomeTaxonNameToRef.put(entry.getE2(), entry.getE1());
		//////////////////////////////////////////// Saving into Workspace //////////////////////////////////////////////
		try {
			wc.getWorkspaceInfo(new WorkspaceIdentity().withWorkspace(defaultTaxonomyWorkspaceName));
		} catch (Exception ex) {
			System.err.println("Error reading woikspace: " + ex.getMessage());
			System.out.println(wc.createWorkspace(new CreateWorkspaceParams().withWorkspace(defaultTaxonomyWorkspaceName).withGlobalread("r")));
		}
		List<TreeNode> layer = new ArrayList<TreeNode>();
		layer.add(root);
		for (int level = 0; layer.size() > 0; level++) {
			System.out.println("Layer [" + level + "] size: " + layer.size());
			SaveCache<Taxon> nodesCache = new SaveCache<Taxon>();
			long time = System.currentTimeMillis();
			int prevNodesSaved = 0;
			for (TreeNode node : layer) {
				if (node.node.getParentRef() == null && node.node.getTaxId() != 1L)
					throw new IllegalStateException("No ref to parent for node id=" + node.node.getTaxId());
				String objName = "tax" + node.node.getTaxId();
				if (!taxonNameToRef.containsKey(objName)) {
					saveObject(wc, defaultTaxonomyWorkspaceName, "KBaseTaxonomy.Taxon", objName, node.node, nodesCache, 
							new SaveListener<Taxon>() {
						@Override
						public void afterSave(Taxon obj, String objName, String ref) {
							taxonNameToRef.put(objName, ref);
						}
					});
					if (nodesCache.saved != prevNodesSaved) {
						System.out.println("Nodes saved: " + nodesCache.saved + ", time-per-item: " + 
								(System.currentTimeMillis() - time) / Math.max(1, nodesCache.saved) + " ms");
						prevNodesSaved = nodesCache.saved;
					}
				}
			}
			flush(wc, nodesCache);
			System.out.println("Nodes saved: " + nodesCache.saved + ", time-per-item: " + 
					(System.currentTimeMillis() - time) / Math.max(1, nodesCache.saved) + " ms");
			List<TreeNode> nextLayer = new ArrayList<TreeNode>();
			SaveCache<GenomeTaxon> genomesCache = new SaveCache<GenomeTaxon>();
			time = System.currentTimeMillis();
			int prevGenomesSaved = 0;
			for (TreeNode node : layer) {
				String nodeRef = taxonNameToRef.get("tax" + node.node.getTaxId());
				if (nodeRef == null)
					throw new IllegalStateException();
				node.taxonRef = nodeRef;
				if (node.children != null)
					for (TreeNode ch : node.children) {
						ch.node.setParentRef(nodeRef);
						nextLayer.add(ch);
					}
				for (String genomeRef : node.genomeRefsToIds.keySet()) {
					String objName = node.genomeRefsToIds.get(genomeRef);
					if (genomeTaxonNameToRef.containsKey(objName))
						continue;
					saveObject(wc, defaultTaxonomyWorkspaceName, "KBaseTaxonomy.GenomeTaxon", objName, 
							new GenomeTaxon().withTaxonRef(nodeRef).withGenomeRef(genomeRef), genomesCache,
							new SaveListener<GenomeTaxon>() {
						@Override
						public void afterSave(GenomeTaxon obj, String name, String ref) {
							genomeTaxonNameToRef.put(name, ref);
						}
					});
					if (prevGenomesSaved != genomesCache.saved) {
						System.out.println("Genomes saved: " + genomesCache.saved + ", time-per-item: " + 
								(System.currentTimeMillis() - time) / Math.max(1, genomesCache.saved) + " ms");
						prevGenomesSaved = genomesCache.saved;
					}
				}
			}
			System.out.println("Genomes saved: " + genomesCache.saved + ", time-per-item: " + 
					(System.currentTimeMillis() - time) / Math.max(1, genomesCache.saved) + " ms");
			layer = nextLayer;
		}
		//Map<String, Object> ret = transform(root);
		//return null;
	}
	
	private static Object saveObject(WorkspaceClient wc, String wsName, String type, String objName, Object data) throws Exception {
		int attempts = 5;
		for (int i = 0; i < attempts; i++) {
			try {
				return wc.saveObjects(new SaveObjectsParams().withWorkspace(wsName).withObjects(Arrays.asList(
						new ObjectSaveData().withType(type).withName(objName).withData(new UObject(data))))).get(0);
			} catch (ServerException ex) {
				System.err.println("Error saving object: " + objName);
				System.err.println(ex.getData());
				if (i + 1 == attempts)
					throw ex;
			}
		}
		return null;
	}

	private static <T> void saveObject(WorkspaceClient wc, String wsName, String type, String objName, T data,
			SaveCache<T> cache, SaveListener<T> lst) throws Exception {
		if (cache.wsName == null) {
			cache.wsName = wsName;
		} else if (!cache.wsName.equals(wsName)) {
			throw new IllegalStateException("Save cache can't support more than one workspace: " + cache.wsName + ", " + wsName);
		}
		cache.cache.add(new Tuple3<T, ObjectSaveData, SaveListener<T>>().withE1(data)
				.withE2(new ObjectSaveData().withType(type).withName(objName).withData(new UObject(data))).withE3(lst));
		if (cache.cache.size() >= saveBlockSize)
			flush(wc, cache);
	}
	
	private static <T> void flush(WorkspaceClient wc, SaveCache<T> cache) throws Exception {
		if (cache.cache.size() == 0)
			return;
		List<ObjectSaveData> objects = new ArrayList<ObjectSaveData>();
		List<String> names = new ArrayList<String>();
		for (Tuple3<T, ObjectSaveData, SaveListener<T>> entry : cache.cache) {
			objects.add(entry.getE2());
			names.add(entry.getE2().getName());
		}
		int attempts = 5;
		for (int i = 0; i < attempts; i++) {
			try {
				List<?> infos = wc.saveObjects(new SaveObjectsParams().withWorkspace(cache.wsName).withObjects(objects));
				for (int infoPos = 0; infoPos < infos.size(); infoPos++) {
					Object info = infos.get(infoPos);
					T src = cache.cache.get(infoPos).getE1();
					String ref = WorkspaceUtil.getRefFromObjectInfo(info);
					cache.cache.get(infoPos).getE3().afterSave(src, objects.get(infoPos).getName(), ref);
					cache.saved++;
				}
				cache.cache.clear();
				break;
			} catch (ServerException ex) {
				System.err.println("Error saving objects (attempt " + (i + 1) + "): " + names);
				System.err.println(ex.getData());
				if (i + 1 == attempts)
					throw ex;
			}
		}
	}

	
	
	private static InputStream findZipEntry(String zipUrl, String entryName) throws Exception {
		File localCopy = new File("temp_files", "taxdmp.zip");
		if (!localCopy.exists()) {
			InputStream is = new URL(zipUrl).openStream();
			OutputStream os = new FileOutputStream(localCopy);
			IOUtils.copy(is, os);
			is.close();
			os.close();
		}
		ZipInputStream zis = new ZipInputStream(new FileInputStream(localCopy));
		while (true) {
			ZipEntry ze = zis.getNextEntry();
			if (ze == null)
				break;
			if (ze.getName().equals(entryName)) {
				return zis;
			}
		}
		zis.close();
		throw new IllegalStateException("Can't find entry " + entryName + " in zip file");
	}
	
	private static void removeHidden(TreeNode parent, TreeNode node) {
		TreeNode futPar = node;
		if (node.hidden && node.genomeRefsToIds.isEmpty()) {
			parent.children.remove(node);
			if (node.children != null)
				for (TreeNode ch : node.children)
					parent.children.add(ch);
			futPar = parent;
		}
		if (node.children != null) {
			List<TreeNode> children = new ArrayList<TreeNode>(node.children);
			for (TreeNode ch : children)
				removeHidden(futPar, ch);
		}
	}

	private static int removeNoGenomes(TreeNode node) {
		int ret = node.genomeRefsToIds.size();
		if (node.children != null) {
			List<TreeNode> children = new ArrayList<TreeNode>(node.children);
			for (TreeNode ch : children) {
				int genomes = removeNoGenomes(ch);
				if (genomes == 0)
					node.children.remove(ch);
				ret += genomes;
			}
		}
		return ret;
	}

	private static int countNodes(TreeNode node) {
		int ret = 1;
		if (node.children != null) {
			for (TreeNode ch : node.children)
				ret += countNodes(ch);
		}
		return ret;
	}

	private static int countGenomes(TreeNode node) {
		int ret = node.genomeRefsToIds.size();
		if (node.children != null) {
			for (TreeNode ch : node.children)
				ret += countGenomes(ch);
		}
		return ret;
	}

	private static void reg(WorkspaceClient wc) throws Exception {
		String module = "KBaseTaxonomy";
		String[] types = {
				"Taxon",
				"GenomeTaxon"
		};
		//wc.requestModuleOwnership(module);
		//if (true)
		//	return;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(new File("KBaseTaxonomy.spec")));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			sb.append(l).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
		System.out.println(wc.registerTypespec(new RegisterTypespecParams()
				.withSpec(sb.toString()).withNewTypes(Arrays.asList(types)).withDryrun(0L)));
		System.out.println(wc.listModuleVersions(new ListModuleVersionsParams().withMod(module)));
		System.out.println(wc.getModuleInfo(new GetModuleInfoParams().withMod(module)));
		System.out.println(wc.releaseModule(module));
	}

	private static class TreeNode {
        public Taxon node;
        public boolean hidden = false;
        public String taxonRef;
        public Map<String, String> genomeRefsToIds = new TreeMap<String, String>();
        public List<TreeNode> children = new ArrayList<TreeNode>();

        TreeNode(Taxon node) {
        	this.node = node;
        }
    }
	
	private static interface SaveListener<T> {
		public void afterSave(T obj, String name, String ref);
	}
	
	private static class SaveCache<T> {
		String wsName = null;
		List<Tuple3<T, ObjectSaveData, SaveListener<T>>> cache = 
				new ArrayList<Tuple3<T, ObjectSaveData, SaveListener<T>>>();
		int saved = 0;
	}
}
