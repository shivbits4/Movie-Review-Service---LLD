package exceptions;

public class InvalidUser extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUser(String exp) {
		super(exp);
	}
}
