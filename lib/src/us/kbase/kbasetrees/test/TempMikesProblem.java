package us.kbase.kbasetrees.test;

import java.net.URL;
import java.util.List;

import org.junit.Test;

import us.kbase.kbasetrees.KBaseTreesClient;
import us.kbase.tree.TreeClient;

public class TempMikesProblem {

	public static void main(String[] args) throws Exception {
		new TempMikesProblem().testPerlConnectionBug();
	}
	
	@Test
	public void testPerlConnectionBug() throws Exception {
		for (int i = 0; i < 20; i++) {
			TreeClient cl = new TreeClient(new URL("http://140.221.84.184:7047"));
			List<String> ret = cl.extractLeafNodeNames("(k);");
			//System.out.println(i + "\t" + ret);
		}
		for (int i = 0; i < 20; i++) {
			KBaseTreesClient cl = new KBaseTreesClient(new URL("http://140.221.85.58:8284/"));
			List<String> ret = cl.extractLeafNodeNames("(k);");
			//System.out.println(i + "\t" + ret);
		}
	}
}
