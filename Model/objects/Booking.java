package objects;

import exceptions.*;

public class Booking {

	public String bookingreference;
	private PassengerName passengername;
	private String flightcode;
	private boolean checkedin;

	/**
	 * Booking object constructor
	 * @param bookingreference the booking reference for each passenger
	 * @param passengername the name of the passenger
	 * @param flightcode the flight that the passenger is taking
	 * @param checkedin whether the passenger has checked in or not
	 * @throws InvalidBookingReference exception showing that the passenger has entered an invalid booking reference
	 * @throws InvalidFlightCode exception showing that the passenger has entered an invalid flight code
	 */
	public Booking(String bookingreference, PassengerName passengername, String flightcode, boolean checkedin) throws InvalidBookingReference, InvalidFlightCode{
 
		setBookingreference(bookingreference);
		setPassengername(passengername);
		setFlightcode(flightcode);
		setCheckedin(checkedin);
	}

	/**
	 * @return the booking refernce of the passneger
	 */
	public String getBookingreference() {
		return bookingreference;
	}

	/**
	 * sets the booking reference to the value provided in the parameter
	 * @param bookingreference 
	 * @throws InvalidBookingReference
	 */
	public void setBookingreference(String bookingreference) throws InvalidBookingReference {

		validbookingreference(bookingreference);
		this.bookingreference = bookingreference;
	}

	/**
	 * @return the name of the passenger 
	 */
	public PassengerName getPassengername() {
		return passengername;
	}

	/**
	 * sets the passenger name to the name provided in the parameter
	 * @param passengername
	 */
	public void setPassengername(PassengerName passengername) {
		this.passengername = passengername;
	}

	/**
	 * @return the flight code of the flight that the passenger is going to take
	 */
	public String getFlightcode() {
		
		return flightcode;
	}

	/**
	 * sets the flight code to the code provided in he parameter
	 * @param flightcode 
	 * @throws invalidFlightCode 
	 */
	public void setFlightcode(String flightcode) throws InvalidFlightCode {
		
		if(!validflightcode(flightcode)) throw new InvalidFlightCode(flightcode);
		this.flightcode = flightcode;
		
	}

	/**
	 * returns true if the passenger has checked in and false if he has not
	 * @return
	 */
	public boolean isCheckedin() {
		return checkedin;
	}

	/**
	 * sets the checked in status of the passenger to the boolean value provided in the parameter
	 * @param checkedin
	 */
	public void setCheckedin(boolean checkedin) {
		this.checkedin = checkedin;
	}
 
	/**
	 * takes as an input a booking reference and throws an exception if the booking reference is not in a valid format
	 * @param b
	 * @throws InvalidBookingReference
	 */
	public void validbookingreference(String b) throws InvalidBookingReference {
		int size = b.trim().length();
		if (size != 5) {
			throw new InvalidBookingReference("Must be 5 characters long.");
		}
		char ch = b.charAt(0);
		if (!isULetter(ch)) {
			throw new InvalidBookingReference("First 2 characters must be capital letters.");
		}
		ch = b.charAt(1);
		if (!isULetter(ch)) {
			throw new InvalidBookingReference("First 2 characters must be capital letters.");
		}
		for (int i = 2; i < size; i++) {
			ch = b.charAt(i);
			if (isNotNumber(ch)) {
				throw new InvalidBookingReference("Last 3 characters must be numbers.");
			}
		}
	}

	/**
	 * checks if a character is number
	 * @param ch
	 * @return true if the character is a number
	 */
	private boolean isNotNumber(char ch) {
		return ch < '0' || ch > '9';
	}

	/**
	 * checks if a character is a capital letter
	 * @param ch
	 * @return true if the character is a capital letter and false otherwise
	 */
	private boolean isULetter(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}

	/**
	 * checks if the flight code is in a valid format
	 * @param b
	 * @return true if is a valid format and false otherwise
	 */
	private boolean validflightcode(String b) {
		int size = b.trim().length();
		if (size != 5) {
			return false;
		}
		char ch = b.charAt(0);
		if (!isULetter(ch)) {
			return false;
		}

		for (int i = 1; i < size; i++) {
			ch = b.charAt(i);
			if (isNotNumber(ch)) {
				return false;
			}
			
		}
		return true;
	}
	
}
