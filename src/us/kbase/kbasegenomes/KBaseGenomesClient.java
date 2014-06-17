package us.kbase.kbasegenomes;

import java.io.File;
import java.net.URL;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;

/**
 * <p>Original spec-file module name: KBaseGenomes</p>
 * <pre>
 * @author chenry,kkeller
 * </pre>
 */
public class KBaseGenomesClient {
    private JsonClientCaller caller;

    public KBaseGenomesClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    public URL getURL() {
        return caller.getURL();
    }

    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }
}
