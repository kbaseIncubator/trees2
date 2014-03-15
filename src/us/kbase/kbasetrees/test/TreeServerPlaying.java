package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import us.kbase.common.service.Tuple7;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.KBaseTreesClient;
import us.kbase.kbasetrees.KBaseTreesServer;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspace.GetModuleInfoParams;
import us.kbase.workspace.ListModuleVersionsParams;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.WorkspaceClient;

public class TreeServerPlaying {
	private static String ws2url = "http://140.221.84.209:7058/";
	private static final String jobSrvUrl = "http://140.221.84.180:7083";
	private static String pwd = "2qz3gm7c";

	public static void main(String[] args) throws Exception {
		test();
	}
	
	private static void test() throws Exception {
		int port = 9999;
		Server jettyServer = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		jettyServer.setHandler(context);
		context.addServlet(new ServletHolder(new KBaseTreesServer()),"/*");
		jettyServer.start();
		KBaseTreesClient cl = new KBaseTreesClient(new URL("http://localhost:" + port), "rsutormin", pwd);
		cl.setAuthAllowedForHttp(true);
		String jobId = cl.constructSpeciesTree(new ConstructSpeciesTreeParams().withOutWorkspace("rsutormin")
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
				System.out.println("Ws error: " + wasError);
				break;
			}
			Thread.sleep(5000);
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms.");
		jettyServer.stop();
	}
	
	private static UserAndJobStateClient createJobClient(String user, String password) throws Exception {
		UserAndJobStateClient ret = new UserAndJobStateClient(new URL(jobSrvUrl), user, password);
		ret.setAuthAllowedForHttp(true);
		return ret;
	}
	
	private static void reg() throws Exception {
		WorkspaceClient wc = new WorkspaceClient(new URL(ws2url), "rsutormin", pwd);
		String module = "KBaseTrees";
		wc.setAuthAllowedForHttp(true);
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
		//wc.get
		System.out.println(wc.listModuleVersions(new ListModuleVersionsParams().withMod(module)));
		System.out.println(wc.getModuleInfo(new GetModuleInfoParams().withMod(module)));
		System.out.println(wc.releaseModule(module));
	}
}
