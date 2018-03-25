package exceptions;

public class InvalidFlightCode extends Exception {

	public InvalidFlightCode(String b) {
		super("Invalid Flight Code : " + b);
	}

}

