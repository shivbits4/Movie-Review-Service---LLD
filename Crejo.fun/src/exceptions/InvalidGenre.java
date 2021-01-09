package exceptions;

public class InvalidGenre extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidGenre(String exp) {
		super(exp);
	}

}
