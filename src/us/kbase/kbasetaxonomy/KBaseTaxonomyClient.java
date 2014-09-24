package us.kbase.kbasetaxonomy;

import java.io.File;
import java.net.URL;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;

/**
 * <p>Original spec-file module name: KBaseTaxonomy</p>
 * <pre>
 * </pre>
 */
public class KBaseTaxonomyClient {
    private JsonClientCaller caller;


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public KBaseTaxonomyClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }
}
