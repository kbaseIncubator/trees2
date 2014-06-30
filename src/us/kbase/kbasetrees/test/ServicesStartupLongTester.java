package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthUser;
import us.kbase.common.mongo.exceptions.InvalidHostException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.ServerException;
import us.kbase.common.service.Tuple7;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.common.test.TestException;
import us.kbase.kbasetrees.KBaseTreesClient;
import us.kbase.kbasetrees.KBaseTreesServer;
import us.kbase.kbasetrees.MSA;
import us.kbase.typedobj.core.TempFilesManager;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceServer;
import us.kbase.workspace.test.WorkspaceTestCommon;

public class ServicesStartupLongTester {
	private static File rootTempDir = null;
	private static File mongoDir = null;
	private static Process mongodProcess = null;
	private static File wsDir = null;
	protected static String defaultWokspace = "trees_ws";
	protected static String wsUrl = null;
	private static WorkspaceServer wsServer = null;
	protected static WorkspaceClient wsClient = null;
	private static File queueDir = null;
	private static File treesDir = null;
	private static KBaseTreesServer treesServer = null;
	protected static KBaseTreesClient treesClient = null;
	protected static String user = null;
	protected static AuthUser auth = null;
	protected static UserAndJobStateClient ujsClient = null;

	private static List<TempFilesManager> tfms = new LinkedList<TempFilesManager>();

	/*********************** Preparation of all services ***********************/

	private static File getTempFilesDir() {
		File ret = rootTempDir;
		if (!ret.exists())
			ret.mkdirs();
		return ret;
	}
	
	private static File generateTempDir(String prefix) {
		long suffix = System.currentTimeMillis();
		while (true) {
			File ret = new File(getTempFilesDir(), prefix + suffix);
			if (ret.exists()) {
				suffix++;
			} else {
				ret.mkdirs();
				return ret;
			}
		}
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		Map<String, String> testCfg = new Ini(new File("test.cfg")).get("trees");
		rootTempDir = new File(testCfg.get("scratch"));
		mongoDir = generateTempDir("mongo");
		int mongoPort = searchForFreePort();
		mongodProcess = startMongo(mongoDir, mongoPort);
		user = testCfg.get("test.user1");
		String p1 = testCfg.get("test.pwd1");
		wsDir = generateTempDir("ws");
		wsServer = startupWorkspaceServer(mongoPort, wsDir);
		int wsPort = wsServer.getServerPort();
		wsUrl = "http://localhost:" + wsPort;
		System.out.println("Started test workspace server on port " + wsPort);
		try {
			wsClient = new WorkspaceClient(new URL(wsUrl), user, p1);
			wsClient.setAuthAllowedForHttp(true);
		} catch (UnauthorizedException ue) {
			throw new TestException("Unable to login with test.user1: " + user +
					"\nPlease check the credentials in the test configuration.", ue);
		}
		auth = AuthService.login(user, p1);
		registerModuleAndWorkspace();
		queueDir = generateTempDir("queue");
		treesDir = generateTempDir("trees");
		String ujsUrl = testCfg.get("jobstatus.srv.url");
		treesServer = startupTreesServer(wsPort, treesDir, queueDir, ujsUrl);
		int treesPort = treesServer.getServerPort();
		System.out.println("Started test trees server on port " + wsPort);
		try {
			treesClient = new KBaseTreesClient(new URL("http://localhost:" + treesPort), user, p1);
			treesClient.setAuthAllowedForHttp(true);
		} catch (UnauthorizedException ue) {
			throw new TestException("Unable to login with test.user1: " + user +
					"\nPlease check the credentials in the test configuration.", ue);
		}
		try {
			ujsClient = new UserAndJobStateClient(new URL(ujsUrl), user, p1);
			ujsClient.setAuthAllowedForHttp(true);
		} catch (UnauthorizedException ue) {
			throw new TestException("Unable to login with test.user1: " + user +
					"\nPlease check the credentials in the test configuration.", ue);
		}
		System.out.println("Starting tests");
	}

	/*********************** Mongodb startup ***********************/

	private static int searchForFreePort() throws Exception {
		ServerSocket ss = new ServerSocket(0);
		int port = ss.getLocalPort();
		ss.close();
		return port;
	}
	
	private static Process startMongo(File dbTempDir, int port) throws Exception {
		String kbRuntime = System.getenv("KB_RUNTIME");
		if (kbRuntime == null || kbRuntime.isEmpty())
			kbRuntime = "/kb/runtime/";
		File mongodBin = new File(new File(kbRuntime, "bin"), "mongod");
		Process ret = Runtime.getRuntime().exec(new String[] {
				mongodBin.getAbsolutePath(), "--dbpath", dbTempDir.getAbsolutePath(),
				"--port", "" + port});
		List<String> console = Collections.synchronizedList(new ArrayList<String>());
		startProcessStreamHolder(ret.getInputStream(), "MONGOD-OUT", console);
		startProcessStreamHolder(ret.getErrorStream(), "MONGOD-ERR", null);
		System.out.println("Main thread waiting for mongodb to start up");
		while (true) {
			Thread.sleep(1000);
			boolean ok = false;
			for (String l : console)
				if (l.contains("[initandlisten] waiting for connections on port " + port)) {
					ok = true;
					break;
				} else if (l.contains("dbexit: really exiting now")) {
					return null;
				}
			if (ok)
				break;
		}
		return ret;
	}
	
	private static Thread startProcessStreamHolder(final InputStream is, 
			final String prefix, final List<String> console) {
		Thread ret = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					while (true) {
						String line = br.readLine();
						if (line == null)
							break;
						if (console == null) { 
							System.out.println(prefix + ": " + line);
						} else {
							console.add(line);
						}
					}
					br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		ret.start();
		return ret;
	}

	/*********************** Workspace service startup ***********************/

	private static WorkspaceServer startupWorkspaceServer(int mongoPort, File dir)
			throws InvalidHostException, UnknownHostException, IOException,
			NoSuchFieldException, IllegalAccessException, Exception,
			InterruptedException {
		System.setProperty(WorkspaceTestCommon.DB1, "ws_test_db_for_trees");
		System.setProperty(WorkspaceTestCommon.TYPEDB1, "ws_test_typedb_for_trees");
		System.setProperty(WorkspaceTestCommon.HOST, "localhost:" + mongoPort);
		System.setProperty(WorkspaceTestCommon.M_USER, "root");
		System.setProperty(WorkspaceTestCommon.M_PWD, "root");
		WorkspaceTestCommon.destroyAndSetupDB(1, "gridFS", null);
		
		//write the server config file:
		File iniFile = File.createTempFile("testWs", ".cfg", new File("./"));
		if (iniFile.exists()) {
			iniFile.delete();
		}
		System.out.println("Created temporary config file: " + iniFile.getAbsolutePath());
		Ini ini = new Ini();
		Section ws = ini.add("Workspace");
		ws.add("mongodb-host", "localhost:" + mongoPort);
		String dbName = WorkspaceTestCommon.getDB1();
		ws.add("mongodb-database", dbName);
		ws.add("mongodb-user", WorkspaceTestCommon.getMongoUser());
		ws.add("mongodb-pwd", WorkspaceTestCommon.getMongoPwd());
		ws.add("backend-secret", "");
		ws.add("ws-admin", user);
		ws.add("temp-dir", dir.getAbsolutePath());
		ini.store(iniFile);
		iniFile.deleteOnExit();
		
		//set up env
		Map<String, String> env = getenv();
		env.put("KB_DEPLOYMENT_CONFIG", iniFile.getAbsolutePath());
		env.put("KB_SERVICE_NAME", "Workspace");

		WorkspaceServer.clearConfigForTests();
		final WorkspaceServer server = new WorkspaceServer();
		tfms.add(server.getTempFilesManager());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server.startupServer();
				} catch (Exception e) {
					System.err.println("Can't start server:");
					e.printStackTrace();
				}
			}
		}).start();
		System.out.println("Main thread waiting for workspace server to start up");
		while (server.getServerPort() == null) {
			Thread.sleep(1000);
		}
		return server;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getenv() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, String> unmodifiable = System.getenv();
		Class<?> cu = unmodifiable.getClass();
		Field m = cu.getDeclaredField("m");
		m.setAccessible(true);
		return (Map<String, String>) m.get(unmodifiable);
	}

	/*********************** Modules/ws registration/creation ***********************/
	
	private static void registerModuleAndWorkspace() throws Exception {
		registerSpec("KBaseTrees", new FileInputStream("KBaseTrees.spec"), "SpeciesTree", "Tree", "MSA");
		wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(defaultWokspace).withGlobalread("r"));
		registerSpec("KBaseGenomes", ServicesStartupLongTester.class.getResourceAsStream("KBaseGenomes.properties"),
				"Genome", "ContigSet");
	}
	
	private static void registerSpec(String mod, InputStream is, String... types) throws Exception {
		StringBuilder specText = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			specText.append(l).append('\n');
		}
		br.close();
		wsClient.requestModuleOwnership(mod);
		administerCommand(wsClient, "approveModRequest", "module", mod);
		wsClient.registerTypespec(new RegisterTypespecParams()
			.withDryrun(0L).withSpec(specText.toString()).withNewTypes(Arrays.asList(types)));
		wsClient.releaseModule(mod);
	}
	
	protected static void administerCommand(WorkspaceClient client, String command, String... params) 
			throws IOException, JsonClientException {
		Map<String, String> releasemod = new HashMap<String, String>();
		releasemod.put("command", command);
		for (int i = 0; i < params.length / 2; i++)
			releasemod.put(params[i * 2], params[i * 2 + 1]);
		client.administer(new UObject(releasemod));
	}

	/*********************** Trees service startup ***********************/

	private static KBaseTreesServer startupTreesServer(int workspacePort, 
			File tempDir, File queueDir, String jssUrl) throws Exception {
		//write the server config file:
		File iniFile = File.createTempFile("testTrees", ".cfg", new File("./"));
		if (iniFile.exists()) {
			iniFile.delete();
		}
		Ini ini = new Ini();
		Section cfg = ini.add("trees");
		cfg.add(KBaseTreesServer.CFG_PROP_THREAD_COUNT, "1");
		cfg.add(KBaseTreesServer.CFG_PROP_QUEUE_DB_DIR, queueDir.getAbsolutePath());
		cfg.add(KBaseTreesServer.CFG_PROP_WS_SRV_URL, "http://localhost:" + workspacePort);
		cfg.add(KBaseTreesServer.CFG_PROP_JSS_SRV_URL, jssUrl);
		cfg.add(KBaseTreesServer.CFG_PROP_TEMP_DIR, tempDir.getAbsolutePath());
		cfg.add(KBaseTreesServer.CFG_PROP_DATA_DIR, new File("data").getAbsolutePath());
		ini.store(iniFile);
		iniFile.deleteOnExit();
		
		final KBaseTreesServer server = new KBaseTreesServer(iniFile);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server.startupServer();
				} catch (Exception e) {
					System.err.println("Can't start server:");
					e.printStackTrace();
				}
			}
		}).start();
		System.out.println("Main thread waiting for trees server to start up");
		while (server.getServerPort() == null) {
			Thread.sleep(1000);
		}
		return server;
	}

	/*********************** Shutdown and cleanup all parts ***********************/

	@AfterClass
	public static void tearDownClass() throws Exception {
		if (treesServer != null) {
			try {
				System.out.print("Killing trees server... ");
				treesServer.stopServer();
				System.out.println("Done");
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		if (wsServer != null) {
			try {
				System.out.print("Killing workspace server... ");
				wsServer.stopServer();
				System.out.println("Done");
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		if (mongodProcess != null) {
			try {
				System.out.print("Killing mongodb... ");
				mongodProcess.destroy();
				System.out.println("Done");
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		deleteWithTryCatch(treesDir);
		deleteWithTryCatch(queueDir);
		deleteWithTryCatch(wsDir);
		deleteWithTryCatch(mongoDir);
	}

	private static void deleteWithTryCatch(File dir) {
		if (dir != null) {
			try {
				deleteRecursively(dir);
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void deleteRecursively(File fileOrDir) {
		if (fileOrDir.isDirectory())
			for (File f : fileOrDir.listFiles()) 
				deleteRecursively(f);
		fileOrDir.delete();
	}

	/**************************** Utility Methods *****************************/
	
	protected static void saveWsObject(String wsName, String type, String objName, Object data) throws Exception {
		wsClient.saveObjects(new SaveObjectsParams().withWorkspace(wsName)
				.withObjects(Arrays.asList(new ObjectSaveData()
				.withType(type).withName(objName).withData(new UObject(data)))));
	}
	
	protected static <T> T getWsObject(String ref, Class<T> type) throws Exception {
		T ret = wsClient.getObjects(Arrays.asList(new ObjectIdentity().withRef(ref))).get(0).getData().asClassInstance(type);
		return ret;
	}

	protected static List<ProvenanceAction> getWsProvenance(String ref) throws Exception {
		return wsClient.getObjects(Arrays.asList(new ObjectIdentity().withRef(ref))).get(0).getProvenance();
	}

	protected static void waitForJob(String jobId) throws Exception {
		while (true) {
			Tuple7<String, String, String, Long, String, Long, Long> status = ujsClient.getJobStatus(jobId);
			boolean completed = status.getE6() == 1L;
			if (!completed) {
				Thread.sleep(1000);
				continue;
			}
			boolean isError = status.getE7() == 1L;
			if (isError) {
				System.err.println("Detailed error: " + ujsClient.getDetailedError(jobId));
				throw new IllegalStateException("Error in job execution (see console for detailes)");
			}
			break;
		}
	}

}
