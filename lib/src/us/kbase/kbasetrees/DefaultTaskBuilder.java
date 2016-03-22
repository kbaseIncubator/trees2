package us.kbase.kbasetrees;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.GetObjectInfoNewParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceClient;

public abstract class DefaultTaskBuilder<T> {

	protected File tempDir;
	protected File dataDir;
	protected ObjectStorage storage;

	public abstract Class<T> getInputDataType();

	public abstract String getTaskDescription();

	public abstract String getOutRef(T inputData);

	public abstract void run(String token, T inputData, String outRef) throws Exception;

	public void init(String wsUrl, Map<String, String> configParams) {
		init(getDirParam(configParams, KBaseTreesServer.CFG_PROP_TEMP_DIR), 
		        getDirParam(configParams, KBaseTreesServer.CFG_PROP_DATA_DIR),
				createDefaultObjectStorage(wsUrl));
	}
	
	public static ObjectStorage createDefaultObjectStorage(final String wsUrl) {
		return new ObjectStorage() {
			
			@Override
			public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
					String authToken, SaveObjectsParams params) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.saveObjects(params);
			}
			
			@Override
			public List<ObjectData> getObjects(String authToken,
					List<ObjectIdentity> objectIds) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.getObjects(objectIds);
			}
			
			@Override
			public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> listObjects(
					String authToken, ListObjectsParams params) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.listObjects(params);
			}
			
			@Override
		    public List<ObjectData> getObjectSubset(String authToken, List<SubObjectIdentity> objectIds) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.getObjectSubset(objectIds);
		    }

			@Override
		    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> getObjectInfoNew(
		    		String authToken, GetObjectInfoNewParams params) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.getObjectInfoNew(params);
		    }
		};
	}

	public DefaultTaskBuilder<T> init(File tempDir, File dataDir, ObjectStorage ws) {
		this.tempDir = tempDir;
		if (!tempDir.exists())
			tempDir.mkdir();
		this.dataDir = dataDir;
		if (!dataDir.exists())
			throw new IllegalStateException("Directory " + dataDir + " doesn't exist");
		this.storage = ws;
		return this;
	}

	public static File getDirParam(Map<String, String> configParams, String param) {
		String tempDirPath = configParams.get(param);
		if (tempDirPath == null)
			throw new IllegalStateException("Parameter " + param + " is not defined in configuration");
		return new File(tempDirPath);
	}

	protected File getTempDir() {
		return tempDir;
	}
	
	protected File getDataDir() {
		return dataDir;
	}
	
	protected ObjectStorage getStorage() {
		return storage;
	}
	
	protected File getBinDir() {
		return new File("bin");
	}
	
	protected String getOsSuffix() {
		String osName = System.getProperty("os.name").toLowerCase();
		String suffix;
		if (osName.contains("linux")) {
			suffix = "linux";
		} else if (osName.contains("mac os x")) {
			suffix = "macosx";
		} else {
			throw new IllegalStateException("Unsupported OS type: " + osName);
		}
		return suffix;
	}
}
