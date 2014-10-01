package us.kbase.kbasetrees.prepare;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.ini4j.Ini;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.ServerException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple2;
import us.kbase.kbasetrees.ConstructTreeForAlignmentParams;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.TreeForAlignmentBuilder;
import us.kbase.kbasetrees.util.WorkspaceUtil;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceClient;

public class DomainTrees {
	private static String domainWsName = "KBasePublicGeneDomains";

	public static void main(String[] args) throws Exception {
		Map<String, String> testCfg = new Ini(new File("test.cfg")).get("trees");
		final File tempDir = new File(testCfg.get("scratch"));
		final File dataDir = new File(testCfg.get("data.dir"));
		String user = testCfg.get("test.user1");
		String pwd = testCfg.get("test.pwd1");
		final String token = AuthService.login(user, pwd).getTokenString();
		String wsUrl = testCfg.get("workspace.srv.url");
		final WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(token));
		client.setAuthAllowedForHttp(true);
		final ObjectStorage storage = new ObjectStorage() {
			@Override
			public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
					String authToken, SaveObjectsParams params) throws Exception {
				return client.saveObjects(params);
			}
			@Override
			public List<ObjectData> getObjects(String authToken,
					List<ObjectIdentity> objectIds) throws Exception {
				return client.getObjects(objectIds);
			}
			@Override
			public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> listObjects(
					String authToken, ListObjectsParams params) throws Exception {
				return client.listObjects(params);
			}
			@Override
		    public List<ObjectData> getObjectSubset(String authToken, List<SubObjectIdentity> objectIds) throws Exception {
				return client.getObjectSubset(objectIds);
		    }
		};
		//String domainName = "pfam00325";
		//String msaName = domainName + ".domain.msa";
		final Set<String> treeNames = new TreeSet<String>();
		for (Tuple2<String, String> entry : WorkspaceUtil.listAllObjectsRefAndName(client, domainWsName, "KBaseTrees.Tree"))
			treeNames.add(entry.getE2());
		int cores = args.length == 1 ? Integer.parseInt(args[0]) : 1;
		final List<List<String>> msaLists = new ArrayList<List<String>>();
		List<Tuple2<String, String>> refsNames = WorkspaceUtil.listAllObjectsRefAndName(client, domainWsName, "KBaseTrees.MSA");
		for (int pos = 0; pos < refsNames.size(); pos++) {
			int binPos = pos % cores;
			while (msaLists.size() <= binPos)
				msaLists.add(new ArrayList<String>());
			msaLists.get(binPos).add(refsNames.get(pos).getE2());
		}
		for (int threadPos = 0; threadPos < cores; threadPos++) {
			final List<String> msaList = msaLists.get(threadPos);
			final String threadName = "t" + threadPos;
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (String msaName : msaList)
						try {
							long time = System.currentTimeMillis();
							boolean ret = createTreeForMSA(storage, token, domainWsName, tempDir, dataDir, msaName, treeNames);
							if (ret)
								System.out.println("MSA [" + msaName + "]: " + threadName + ", " +
										"time=" + (System.currentTimeMillis() - time) + " ms");
						} catch (Exception e) {
							System.out.println("Error processing MSA [" + msaName + "]: " + e.getMessage());
							System.err.println("===== Error processing MSA: " + msaName);
							e.printStackTrace(System.err);
							if (e instanceof ServerException)
								System.err.println("Server cause: " + ((ServerException)e).getData());
						}
				}
			}, threadName).start();
		}
	}
	
	private static boolean createTreeForMSA(ObjectStorage storage, String token, String ws, File tempDir, 
			File dataDir, String msaName, Set<String> treeNames) throws Exception {
		String treeId = msaName + ".tree";
		if (treeNames.contains(treeId))
			return false;
		String msaRef = ws + "/" + msaName;
		TreeForAlignmentBuilder builder = new TreeForAlignmentBuilder().init(tempDir, dataDir, storage);
		builder.run(token, new ConstructTreeForAlignmentParams().withMsaRef(msaRef)
				.withTreeMethod("FastTree").withOutWorkspace(ws).withOutTreeId(treeId), "", ws + "/" + treeId);
		//Tree tree = storage.getObjects(token, Arrays.asList(new ObjectIdentity().withRef(ws + "/" + treeId)))
		//		.get(0).getData().asClassInstance(Tree.class);
		//System.out.println(tree.getTree());
		//System.out.println(tree.getDefaultNodeLabels());
		return true;
	}
}
