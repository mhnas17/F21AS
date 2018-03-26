package exceptions;

public class InvalidBookingReference extends Exception {

	/**
	 * @param b invalid Booking Reference
	 */
	public InvalidBookingReference(String b) {
		super("Invalid Booking Reference : " + b);
	}

}
