package core;

import java.util.HashMap;


public class FlightMap {
	
	/**
	 * instantiate a hashmap. The key is the flight code reference and the value is a flight object
	 * @author stamtiniakos
	 *
	 */
	private HashMap <String, Flight> flights;
	
	/**
	 * hash map constructor
	 */
	public FlightMap(){
		
		flights = new HashMap<String, Flight>();
		
	}
	
	/**
	 * adds a flight object on the hash map.
	 * @param key flight code key
	 * @param value flight object
	 */
	public void add(String key,Flight value) {
		flights.put(key, value);
	}

	/**
	 *returns the object flight from the hashmap ????
	 */
	public Flight getFlight(String x) {
		return flights.get(x) ;
	}
		
}
