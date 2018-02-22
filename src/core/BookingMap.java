package core;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

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
	 * @return the object that corresponds to a specific booking reference
	 */
	public Booking getValue(String k) {
		return bookings.get(k);
	}
	/** checks if everyone has checked in 
	 * @param f
	 * @return
	 */
	public boolean getCheckInReport(BookingMap f) {
		boolean allCheckedIn=true;
		 Set<Entry<String,Booking>> hashSet=bookings.entrySet();
	        for(Entry entry:hashSet ) {
	        	Booking s = Booking.class.cast(entry.getValue());
	        	if(s.isCheckedin()==false) 
	        	{
	        		allCheckedIn=false;
	        		break;
	        	}
	        	
	        }
	                 
	       return allCheckedIn;
	}
	
}
