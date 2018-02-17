package core;

import java.util.HashMap;

public class BookingMap {

	private HashMap<String, Booking> bookings;
	
	public BookingMap(){
		
		bookings = new HashMap<String, Booking>();
	}
	
	public void add(String key,Booking value) {
		bookings.put(key, value);
	}
	
}
