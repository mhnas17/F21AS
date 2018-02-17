package core;

import name_plane.Name;
import Exceptions.*;

public class Booking {

	public String bookingreference;
	private Name passengername;
	private String flightcode;
	private boolean checkedin;

	/**
	 * @param bookingreference
	 * @param passengername
	 * @param flightcode
	 * @param checkedin
	 * @throws InvalidBookingReference 
	 * @throws InvalidFlightCode  
	 */
	public Booking(String bookingreference, Name passengername, String flightcode, boolean checkedin) throws InvalidBookingReference, InvalidFlightCode{
 
		setBookingreference(bookingreference);
		setPassengername(passengername);
		setFlightcode(flightcode);
		setCheckedin(checkedin);
	}

	/**
	 * @return
	 */
	public String getBookingreference() {
		return bookingreference;
	}

	/**
	 * @param bookingreference
	 * @throws InvalidBookingReference
	 */
	public void setBookingreference(String bookingreference) throws InvalidBookingReference {

		validbookingreference(bookingreference);
		this.bookingreference = bookingreference;
	}

	public Name getPassengername() {
		return passengername;
	}

	/**
	 * @param passengername
	 */
	public void setPassengername(Name passengername) {
		this.passengername = passengername;
	}

	/**
	 * @return
	 */
	public String getFlightcode() {
		
		return flightcode;
	}

	/**
	 * @param flightcode
	 * @throws invalidFlightCode 
	 */
	public void setFlightcode(String flightcode) throws InvalidFlightCode {
		
		if(!validflightcode(flightcode)) throw new InvalidFlightCode(flightcode);
		this.flightcode = flightcode;
		
	}

	/**
	 * @return
	 */
	public boolean isCheckedin() {
		return checkedin;
	}

	/**
	 * @param checkedin
	 */
	public void setCheckedin(boolean checkedin) {
		this.checkedin = checkedin;
	}
 
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

	private boolean isNotNumber(char ch) {
		return ch < '0' || ch > '9';
	}

	private boolean isULetter(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}

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
	
	// o phil tona pairnei
	
}
