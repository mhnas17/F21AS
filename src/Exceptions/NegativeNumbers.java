package Exceptions;

public class NegativeNumbers extends Exception {

	public NegativeNumbers(double b) {
		super("Negative Number : " + b);
	}

}