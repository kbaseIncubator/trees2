package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.forester.io.parsers.nhx.NHXParser;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.iterators.PhylogenyNodeIterator;
import org.junit.Test;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple9;
import us.kbase.common.service.UObject;
import us.kbase.kbasetrees.ConstructTreeForAlignmentParams;
import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.Tree;
import us.kbase.kbasetrees.TreeForAlignmentBuilder;
import us.kbase.workspace.GetObjectInfoNewParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceIdentity;

public class TreeForAlignmentBuilderTest {

	@Test
	public void testMuscle() throws Exception {
		build("FastTree");
	}

	@Test
	public void testClustal() throws Exception {
		build("Clustal");
	}

	private static Tree build(String method) throws Exception {
		final MSA input = new MSA().withAlignment(loadAlignedSeqs());
		final Tree[] retWrap = new Tree[] { null };
		long time = System.currentTimeMillis();
		TreeForAlignmentBuilder stb = new TreeForAlignmentBuilder().init(
				new File("temp_files"), new File("data"), new ObjectStorage() {
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
					        AuthToken authToken, SaveObjectsParams params) throws Exception {
						retWrap[0] = params.getObjects().get(0).getData().asClassInstance(Tree.class);
						return new ArrayList<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>>();
					}
					@Override
					public List<ObjectData> getObjects(AuthToken authToken,
							List<ObjectIdentity> objectIds) throws Exception {
						return Arrays.asList(new ObjectData().withData(new UObject(input)));
					}
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> listObjects(
					        AuthToken authToken, ListObjectsParams params)
							throws Exception {
						throw new IllegalStateException();
					}
					@Override
					public List<ObjectData> getObjectSubset(AuthToken authToken, List<SubObjectIdentity> objectIds) throws Exception {
						throw new IllegalStateException("Unsupported method");
					}
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> getObjectInfoNew(
					        AuthToken authToken, GetObjectInfoNewParams params) throws Exception {
						throw new IllegalStateException("Unsupported method");
					}
					@Override
					public Tuple9<Long, String, String, String, Long, String, String, String, Map<String, String>> getWorkspaceInfo(
					        AuthToken authToken, WorkspaceIdentity wsi) throws Exception {
                        throw new IllegalStateException("Unsupported method");
					}
				});
		stb.run(null, new ConstructTreeForAlignmentParams().withTreeMethod(method).withMsaRef("ws/msa.1").withOutWorkspace("ws"), "ws/123");
		Tree tree = retWrap[0];
		Map<String, String> replacements = new TreeMap<String, String>(tree.getDefaultNodeLabels());
		relabel(tree.getTree(), replacements);
		Assert.assertEquals(0, replacements.size());
		System.out.println("Tree construction (" + method + "), time: " + (System.currentTimeMillis() - time) + " ms");
		return tree;
	}

	private static Map<String, String> loadAlignedSeqs() throws Exception {
		File testFile = new File(new File("data", "test"), "msa_seqs.txt");
		Map<String, String> ret = new LinkedHashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			String[] parts = l.split(" ");
			ret.put(parts[0].trim(), parts[parts.length - 1].trim());
		}
		br.close();
		return ret;
	}
	
	private static String relabel(String tree, Map<String, String> replacements) throws Exception {
        NHXParser parser = new NHXParser();
        parser.setSource(tree);
        Phylogeny [] trees = parser.parse();
        StringBuilder relabeledTrees = new StringBuilder();
        for(int k=0; k<trees.length; k++) {
            for( final PhylogenyNodeIterator it = trees[k].iteratorPostorder(); it.hasNext(); ) {
            	PhylogenyNode node = it.next();
            	String oldName = node.getName();
            	String replacement = replacements.get(oldName);
            	if (replacement != null) {
            		node.setName(replacement);
            		replacements.remove(oldName);
            	}
            }
            relabeledTrees.append(trees[k].toNewHampshire());
        }
        return relabeledTrees.toString();
	}
}
