package core;

import java.util.HashMap;

/**
 * instantiate a hashmap. The key is the flight code reference and the value is a flight object
 * @author stamtiniakos
 *
 */
public class FlightMap {

	private HashMap <String, Flight> flights;
	
	
	/**
	 * hasmap constructor
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
	 * prints the hash map ????
	 */
	public void getFlight() {
		System.out.println(flights);
	}
		
}
