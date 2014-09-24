package us.kbase.kbasetrees.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UObject;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class WorkspaceUtil {
	public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info) {
		return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
	}

	public static <T> T getObject(WorkspaceClient client, String ref, Class<T> type) throws Exception {
		return client.getObjects(Arrays.asList(new ObjectIdentity().withRef(ref))).get(0).getData().asClassInstance(type);
	}
	
	public static void saveObject(WorkspaceClient client, String wsName, String type, String objName, Object data) throws Exception {
		client.saveObjects(new SaveObjectsParams().withWorkspace(wsName)
				.withObjects(Arrays.asList(new ObjectSaveData()
				.withType(type).withName(objName).withData(new UObject(data)))));
	}
	
	@SuppressWarnings("unchecked")
	public static String getRefFromObjectInfo(Object objInfo) {
		Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info =
				(Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>)objInfo;
		return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
	}

	public static List<Tuple2<String, String>> listAllObjectsRefAndName(WorkspaceClient client, 
			String wsName, String wsType) throws IOException, JsonClientException {
		List<Tuple2<String, String>> ret = new ArrayList<Tuple2<String, String>>();
		for (int partNum = 0; ; partNum++) {
			int sizeOfPart = 0;
			for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info : 
				client.listObjects(new ListObjectsParams().withWorkspaces(Arrays.asList(wsName))
						.withType(wsType).withLimit(10000L).withSkip(partNum * 10000L))) {
				String ref = getRefFromObjectInfo(info);
				String objectName = info.getE2();
				ret.add(new Tuple2<String, String>().withE1(ref).withE2(objectName));
				sizeOfPart++;
			}
			if (sizeOfPart == 0)
				break;
		}
		return ret;
	}
}
