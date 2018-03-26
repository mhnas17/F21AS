package exceptions;

public class NegativeNumbers extends Exception {

	/**
	 * @param b negative number
	 */
	public NegativeNumbers(double b) {
		super("Negative Number : " + b);
	}

}