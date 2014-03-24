package us.kbase.kbasetrees.cpputil;
public class KBTreeUtilLibLoader {
	static {
        System.loadLibrary("KBTreeUtil");
    }
	static public String doubleCheck() { return "yes, KBTreeUtilLoader here.\n"; }
}