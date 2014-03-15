package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

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
	private static String pwd = "xxxxx";

	public static void main(String[] args) throws Exception {
		test();
	}
	
	private static void test() throws Exception {
		KBaseTreesClient cl = new KBaseTreesClient(new URL("http://140.221.85.58:8284/"), "rsutormin", pwd);
		cl.setAuthAllowedForHttp(true);
		String workspace = "rsutormin";
		String jobId = cl.constructSpeciesTree(new ConstructSpeciesTreeParams().withOutWorkspace(workspace)
				.withNewGenomes(Collections.<String>emptyList()));
		System.out.println("Job id: " + jobId);
		long time = System.currentTimeMillis();
		UserAndJobStateClient jscl = createJobClient("rsutormin", pwd);
		while (true) {
			Tuple7<String, String, String, Long, String, Long, Long> data = jscl.getJobStatus(jobId);
			String status = data.getE3();
    		Long complete = data.getE6();
    		Long wasError = data.getE7();
			System.out.println("Task status: " + status);
			if (complete == 1L) {
				if (wasError == 0L) {
					String wsRef = jscl.getResults(jobId).getWorkspaceids().get(0);
					WorkspaceClient wc = createWorkspaceClient("rsutormin", pwd);
					SpeciesTree tree = wc.getObjects(Arrays.asList(new ObjectIdentity().withRef(wsRef)))
							.get(0).getData().asClassInstance(SpeciesTree.class);
					System.out.println("Tree: " + tree.getSpeciesTree());
				}
				break;
			}
			Thread.sleep(5000);
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms.");
	}
	
	private static UserAndJobStateClient createJobClient(String user, String password) throws Exception {
		UserAndJobStateClient ret = new UserAndJobStateClient(new URL(jobSrvUrl), user, password);
		ret.setAuthAllowedForHttp(true);
		return ret;
	}

	private static WorkspaceClient createWorkspaceClient(String user, String password)
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
