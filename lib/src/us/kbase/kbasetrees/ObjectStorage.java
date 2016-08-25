package us.kbase.kbasetrees;

import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple9;
import us.kbase.workspace.GetObjectInfoNewParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceIdentity;

public interface ObjectStorage {

	public List<ObjectData> getObjects(AuthToken authToken, List<ObjectIdentity> objectIds) throws Exception;
    
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> saveObjects(
            AuthToken authToken, SaveObjectsParams params) throws Exception;
    
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> listObjects(
            AuthToken authToken, ListObjectsParams params) throws Exception;

    public List<ObjectData> getObjectSubset(AuthToken authToken, List<SubObjectIdentity> objectIds) throws Exception;
    
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> getObjectInfoNew(
            AuthToken authToken, GetObjectInfoNewParams params) throws Exception;
    
    public Tuple9<Long, String, String, String, Long, String, String, String, Map<String,String>> getWorkspaceInfo(
            AuthToken authToken, WorkspaceIdentity wsi) throws Exception;
}
