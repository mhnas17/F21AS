package data_structure_lists;

import java.util.ArrayList;

public class BookingLists {
	
	private ArrayList<String> lastnames;
	private ArrayList<String> bookingreferences;
	
	public BookingLists(){
		lastnames = new ArrayList<>();
		bookingreferences =  new ArrayList<>();
	}
	
	public void addLastName(String s) {
		lastnames.add(s);
	}
	
	public void addBookingReferences(String s) {
		bookingreferences.add(s);
	}
	
	public void sysoutName() {
		System.out.println(lastnames);
	}
	
	public void sysoutBooking() {
		System.out.println(bookingreferences);
	}
	
	public Boolean searchNames(String s) {
		return lastnames.contains(s);
	}
	
	public Boolean searchBookings(String s) {
		return bookingreferences.contains(s);
	}

}
