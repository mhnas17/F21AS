package exceptions;

public class NegativeNumbers extends Exception {

	public NegativeNumbers(double b) {
		super("Negative Number : " + b);
	}

}