package Exceptions;

public class InvalidBookingReference extends Exception {

	public InvalidBookingReference(String b) {
		super("Invalid Booking Reference : " + b);
	}

}
