package us.kbase.kbasetrees.exceptions;

/**
 *
 */
public class KBaseTreesException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public KBaseTreesException(String message) {
		super(message);
	}

	public KBaseTreesException(String message, Throwable e) {
		super(message,e);
	}
}
