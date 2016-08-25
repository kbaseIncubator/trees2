package us.kbase.kbasetrees.test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.ini4j.Ini;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.KBaseTreesServer;
import us.kbase.kbasetrees.Tree;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.FastaReader;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class KBaseTreesServerTest {
    private static AuthToken token = null;
    private static Map<String, String> config = null;
    private static WorkspaceClient wsClient = null;
    private static String wsName = null;
    private static String publicWsName = null;
    private static KBaseTreesServer impl = null;
    
    @BeforeClass
    public static void init() throws Exception {
        token = AuthService.validateToken(System.getenv("KB_AUTH_TOKEN"));
        String configFilePath = System.getenv("KB_DEPLOYMENT_CONFIG");
        File deploy = new File(configFilePath);
        Ini ini = new Ini(deploy);
        config = ini.get("KBaseTrees");
        wsClient = new WorkspaceClient(new URL(config.get("workspace.srv.url")), token);
        wsClient.setAuthAllowedForHttp(true);
        // These lines are necessary because we don't want to start linux syslog bridge service
        JsonServerSyslog.setStaticUseSyslog(false);
        JsonServerSyslog.setStaticMlogFile(new File(config.get("scratch"), "test.log").getAbsolutePath());
        impl = new KBaseTreesServer();
        impl.getConfig().put(KBaseTreesServer.CFG_PROP_PUBLIC_GENOMES_WS, getPublicWsName());
        // Store test genome (real data)
        FastaReader fr = new FastaReader(new File("test/data", "Shewanella_ANA_3_uid58347.fasta"));
        List<Feature> features = new ArrayList<Feature>();
        for (Map.Entry<String, String> entry : fr.readAll().entrySet())
            features.add(new Feature().withId(entry.getKey()).withProteinTranslation(entry.getValue())
                    .withType("cds"));
        String genomeName = "Shewanella_ANA_3_uid58347";
        String genomeObjId = genomeName + ".genome";
        Genome genome = new Genome().withScientificName(genomeName)
                .withFeatures(features).withId(genomeObjId).withDomain("Bacteria").withGeneticCode(11L);
        saveWsObject(getWsName(), "KBaseGenomes.Genome", genomeObjId, genome);
        // Sync genome objects (just fake wrappers with kbase-id as name)
        String genomeWsType = "KBaseGenomes.Genome";
        List<ObjectSaveData> objects = new ArrayList<ObjectSaveData>();
        List<String> genomeKbIds = Arrays.asList(
                "kb|g.371",    // Shewanella oneidensis MR-1
                "kb|g.372",    // Shewanella oneidensis MR-1
                "kb|g.852",    // Shewanella putrefaciens CN-32
                "kb|g.1032",   // Shewanella sp. W3-18-1 
                "kb|g.1283",   // Shewanella baltica OS195
                "kb|g.1305",   // Shewanella baltica OS185
                "kb|g.1346",   // Shewanella baltica OS223
                "kb|g.2626",   // Shewanella sp. MR-4
                "kb|g.2627",   // Shewanella sp. MR-7
                "kb|g.2990",   // Shewanella baltica OS117
                "kb|g.2992",   // Shewanella baltica OS678
                "kb|g.3779",   // Shewanella sp. ANA-3
                "kb|g.20848",  // Shewanella oneidensis MR-1
                "kb|g.25423",  // Shewanella sp. POL2
                "kb|g.26614",  // Shewanella putrefaciens 200
                "kb|g.26354",  // Shewanella baltica OS155
                "kb|g.27369",  // Shewanella baltica OS625
                "kb|g.27370",  // Shewanella baltica OS678
                "kb|g.210723", // Shewanella decolorationis S1201
                "kb|g.242813"  // Shewanella xiamenensis
                );
        for (String kbId : genomeKbIds) {
            Map<String, Object> data = new LinkedHashMap<String, Object>(4);
            data.put("id", kbId);
            data.put("scientific_name", "");
            data.put("domain", "");
            data.put("genetic_code", 11L);
            objects.add(new ObjectSaveData().withName(kbId).withType(genomeWsType).withData(new UObject(data)));
        }
        wsClient.saveObjects(new SaveObjectsParams().withWorkspace(getPublicWsName()).withObjects(objects));
    }
    
    private static String getWsName() throws Exception {
        if (wsName == null) {
            long suffix = System.currentTimeMillis();
            wsName = "test_KBaseTrees_" + suffix;
            wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(wsName));
        }
        return wsName;
    }

    private static String getPublicWsName() throws Exception {
        if (publicWsName == null) {
            long suffix = System.currentTimeMillis();
            publicWsName = "test_KBaseTrees_" + suffix;
            wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(publicWsName));
        }
        return publicWsName;
    }

    private static RpcContext getContext() {
        return new RpcContext().withProvenance(Arrays.asList(new ProvenanceAction()
            .withService("KBaseTrees").withMethod("please_never_use_it_in_production")
            .withMethodParams(new ArrayList<UObject>())));
    }
    
    @AfterClass
    public static void cleanup() {
        if (wsName != null) {
            try {
                wsClient.deleteWorkspace(new WorkspaceIdentity().withWorkspace(wsName));
                System.out.println("Test workspace was deleted");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (publicWsName != null) {
            try {
                wsClient.deleteWorkspace(new WorkspaceIdentity().withWorkspace(publicWsName));
                System.out.println("Test public workspace was deleted");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testExtractLeafNodeNames() throws Exception {
        List<String> nodes = impl.extractLeafNodeNames("(n)", getContext());
        Assert.assertEquals(1, nodes.size());
        Assert.assertEquals("n", nodes.get(0));
    }

    @Test
    public void testConstructSpeciesTree() throws Exception {
        String genomeName = "Shewanella_ANA_3_uid58347";
        String genomeId = genomeName + ".genome";
        String spTreeId = "sptree.1";
        String genomeRef = getWsName() + "/" + genomeId;
        impl.constructSpeciesTree(new ConstructSpeciesTreeParams().withNewGenomes(
                Arrays.asList(genomeRef)).withOutWorkspace(getWsName())
                .withOutTreeId(spTreeId).withUseRibosomalS9Only(0L).withNearestGenomeCount(20L), 
                token, getContext());
        String treeRef = getWsName() + "/" + spTreeId;
        Tree tree = getWsObject(treeRef, Tree.class);
        try {
            List<String> nodeIds = impl.extractLeafNodeNames(tree.getTree(), getContext());
            Assert.assertEquals(21, nodeIds.size());
            for (String nodeId : nodeIds) {
                String label = tree.getDefaultNodeLabels().get(nodeId);
                Map<String, List<String>> refs = tree.getWsRefs().get(nodeId);
                Assert.assertNotNull(label);
                if (nodeId.startsWith("user")) {
                    ProvenanceAction prv = getWsProvenance(treeRef).get(0);
                    String resolvedGenomeRef = prv.getResolvedWsObjects().get(0);
                    Assert.assertEquals(resolvedGenomeRef, refs.get("g").get(0));
                    Assert.assertEquals(genomeName, label);
                }
            }
        } catch (Exception ex) {
            System.err.println(tree.getTree());
            throw ex;
        }
    }
    
    @Test
    public void testDrawHtmlTree() throws Exception {
        String html = impl.drawHtmlTree("(n)", new TreeMap<String, String>(), getContext());
        Assert.assertTrue(html, html.contains("<body>"));
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
}