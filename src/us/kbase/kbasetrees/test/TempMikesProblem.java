package us.kbase.kbasetrees.test;

import java.net.URL;
import java.util.List;

import us.kbase.tree.TreeClient;

public class TempMikesProblem {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			TreeClient cl = new TreeClient(new URL("http://140.221.84.184:7047"));
			List<String> ret = cl.extractLeafNodeNames("(k);");
			System.out.println(i + "\t" + ret);
		}
	}
}
