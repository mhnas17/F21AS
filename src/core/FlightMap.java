package core;

import java.util.HashMap;

public class FlightMap {

	private HashMap <String, Flight> flights;
	
	
	public FlightMap(){
		
		flights = new HashMap<String, Flight>();
		
	}
	
	public void add(String key,Flight value) {
		flights.put(key, value);
	}

	public boolean getFlight() {
		return flights.isEmpty();
	}
		
}
