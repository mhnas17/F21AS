package core;

import java.util.HashMap;

public class BookingMap {

	/**
	 * instantiate a hashmap. The key is the booking reference and the value is a booking object
	 */
	private HashMap<String, Booking> bookings;
	
	/**
	 * Hashmap constructor
	 */
	public BookingMap(){
		
		bookings = new HashMap<String, Booking>();
	}
	
	/**
	 * is adding one key value pair in the hash map
	 * @param key
	 * @param value
	 */
	public void add(String key,Booking value) {
		bookings.put(key, value);
	}
	
	/**
	 * @param k
	 * @return the value that corresponds to a specific key
	 */
	public Booking getValue(String k) {
		return bookings.get(k);
	}
	
}
