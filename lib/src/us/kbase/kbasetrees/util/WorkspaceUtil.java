package us.kbase.kbasetrees.util;

import java.util.Map;

import us.kbase.common.service.Tuple11;

public class WorkspaceUtil {
	public static String getRefFromObjectInfo(Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info) {
		return info.getE7() + "/" + info.getE1() + "/" + info.getE5();
	}

}
