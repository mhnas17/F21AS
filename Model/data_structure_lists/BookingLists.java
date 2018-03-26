package data_structure_lists;

import java.util.ArrayList;

public class BookingLists {
	// array list that contains the last names for all the passengers
	private ArrayList<String> lastnames;
	// array list that contains the booking references for all the passengers
	private ArrayList<String> bookingreferences;

	/**
	 * Array list constructor
	 */
	public BookingLists() {
		lastnames = new ArrayList<>();
		bookingreferences = new ArrayList<>();
	}

	/**
	 * adds last name to array list
	 * 
	 * @param s last name of passenger
	 */
	public void addLastName(String s) {
		lastnames.add(s);
	}

	/**
	 * adds the booking reference to array list
	 * @param s booking reference of passenger
	 */
	public void addBookingReferences(String s) {
		bookingreferences.add(s);
	}

	/**
	 * prints the names contained in the array list
	 */
	public void sysoutName() {
		System.out.println(lastnames);
	}

	/**
	 * prints the booking references contained in the array list
	 */
	public void sysoutBooking() {
		System.out.println(bookingreferences);
	}

	/**
	 * @param s last name of passenger
	 * @return true if the last name provided in the parameter exists in the array
	 *         list
	 */
	public Boolean searchNames(String s) {
		return lastnames.contains(s);
	}

	/**
	 * @param s booking reference
	 * @return true if the booking reference provided in the parameter exists in the
	 *         array list
	 */
	public Boolean searchBookings(String s) {
		return bookingreferences.contains(s);
	}

}
