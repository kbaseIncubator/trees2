//
//
// for problems compiling this code, see http://www.swig.org/Doc1.3/Java.html#dynamic_linking_problems

import KBTreeUtil.KBTree;
import KBTreeUtil.KBTreeUtil;

public class Example {
    public static void main(String argv[]) {
        System.loadLibrary("KBTreeUtil");
        String treeString = "((A,C)D,A)E;";
        KBTree t = new KBTree(treeString);
        System.out.println("node count="+t.getNodeCount());
    }
}