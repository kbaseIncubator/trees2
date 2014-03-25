package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import us.kbase.common.service.Tuple7;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.KBaseTreesClient;
import us.kbase.kbasetrees.SpeciesTree;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspace.GetModuleInfoParams;
import us.kbase.workspace.ListModuleVersionsParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.WorkspaceClient;

public class TreeServerPlaying {
	private static String ws2url = "http://140.221.84.209:7058/";
	private static final String jobSrvUrl = "http://140.221.84.180:7083";
	private static String userId = "nardevuser1";
	private static String pwd = "nardevuser2";
	private static String wsId = "nardevuser1:home";

	public static void main(String[] args) throws Exception {
		test();
		//runOneThread(0, true);
	}
	
	private static void test() throws Exception {
		for (int i = 0; i < 8; i++) {
			final int taskNum = i + 1;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						runOneThread(taskNum, false);
					} catch (Exception ex) {
						throw new IllegalStateException(ex);
					}
				}
			}).start();
			Thread.sleep(400);
		}
	}
	
	private static void runOneThread(int taskNum, boolean riboOnly) throws Exception {
		KBaseTreesClient cl = new KBaseTreesClient(new URL("http://140.221.85.58:8284/"), userId, pwd);
		cl.setAuthAllowedForHttp(true);
		String workspace = wsId;
		List<String> genomeRefs = Arrays.asList(new String[] {
				wsId + "/Shewanella_ANA_3_uid58347.genome",
				wsId + "/Shewanella_MR_7_uid58343.genome", 
				wsId + "/Shewanella_MR_4_uid58345.genome",
				wsId + "/Shewanella_baltica_BA175_uid52601.genome",
		});
		String jobId = cl.constructSpeciesTree(new ConstructSpeciesTreeParams().withOutWorkspace(workspace)
				.withNewGenomes(genomeRefs).withOutTreeId("SpeciesTree1").withUseRibosomalS9Only(riboOnly ? 1L : 0L));
		System.out.println("Job-id (task " + taskNum + "): " + jobId);
		long time = System.currentTimeMillis();
		UserAndJobStateClient jscl = createJobClient(userId, pwd);
		while (true) {
			Tuple7<String, String, String, Long, String, Long, Long> data = jscl.getJobStatus(jobId);
			String status = data.getE3();
    		Long complete = data.getE6();
    		Long wasError = data.getE7();
			System.out.println("Status (task " + taskNum + "): " + status);
			if (complete == 1L) {
				if (wasError == 0L) {
					String wsRef = jscl.getResults(jobId).getWorkspaceids().get(0);
					WorkspaceClient wc = createWorkspaceClient(userId, pwd);
					SpeciesTree tree = wc.getObjects(Arrays.asList(new ObjectIdentity().withRef(wsRef)))
							.get(0).getData().asClassInstance(SpeciesTree.class);
					System.out.println("Tree (task " + taskNum + "): " + tree.getSpeciesTree().trim());
					System.out.println("Labels (task " + taskNum + "): " + tree.getIdMap());
				}
				break;
			}
			Thread.sleep(60000);
		}
		System.out.println("Time (task " + taskNum + "): " + (System.currentTimeMillis() - time) + " ms.");
	}
	
	private static UserAndJobStateClient createJobClient(String user, String password) throws Exception {
		UserAndJobStateClient ret = new UserAndJobStateClient(new URL(jobSrvUrl), user, password);
		ret.setAuthAllowedForHttp(true);
		return ret;
	}

	public static WorkspaceClient createWorkspaceClient(String user, String password)
			throws UnauthorizedException, IOException, MalformedURLException {
		WorkspaceClient wc = new WorkspaceClient(new URL(ws2url), user, password);
		wc.setAuthAllowedForHttp(true);
		return wc;
	}

	private static void reg() throws Exception {
		WorkspaceClient wc = createWorkspaceClient("rsutormin", pwd);
		String module = "KBaseTrees";
		//wc.requestModuleOwnership(module);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(new File("KBaseTrees.spec")));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			sb.append(l).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
		System.out.println(wc.registerTypespec(new RegisterTypespecParams()
				.withSpec(sb.toString()).withNewTypes(Arrays.asList("SpeciesTree")).withDryrun(0L)));
		System.out.println(wc.listModuleVersions(new ListModuleVersionsParams().withMod(module)));
		System.out.println(wc.getModuleInfo(new GetModuleInfoParams().withMod(module)));
		System.out.println(wc.releaseModule(module));
	}
}
