package exceptions;

public class InvalidFlightCode extends Exception {

	/**
	 * @param b invalid flight code
	 */
	public InvalidFlightCode(String b) {
		super("Invalid Flight Code : " + b);
	}

}

