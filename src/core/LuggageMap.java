package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;


public class LuggageMap {

	/**
	 * instantiate a hashmap. 
	 * The key is the flight code and the value is a luggage object which contains accumulated weight, volume , number of passengers and excess fees for each flight.
	 * @author st88
	 */
	private HashMap<String, Luggage> checkindata; 
	
	/**
	 * hash map constructor
	 */
	public LuggageMap(){ 
		checkindata = new HashMap<String, Luggage>();
		
	}
	
	/**
	 * adds one Luggage object on the hash map
	 * @param key flight code
	 * @param value Flight Object
	 */
	public void add(String key,Luggage value) {
		checkindata.put(key, value);
	}
			
	/**
	 * @param e
	 * @return
	 */
	public int getPassengersPerFlight(Flight e) {

		int s = checkindata.get(e.getFlightcode()).getAccum_numberofpassengers();
		return s;

	}

	/**
	 * @param e
	 * @return
	 */
	public double getLuggageWeightPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_weight();
		return s;

	}

	/**
	 * @param e
	 * @return
	 */
	public double getLuggageVolumePerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_volume();
		return s;

	}

	/**
	 * @param e
	 * @return
	 */
	public double getExcessFeesPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_excessfees();
		return s;

	}

	/**
	 * @param e
	 * @return
	 */
	public int getExcessPassengersPerFlight(Flight e) {

		return 0;
	}

	/**
	 * @return
	 */
	public double getExcessWeightPerFlight() {

		return 0;
	}

	/**
	 * @return
	 */
	public double getExcessVolumePerFlight() {

		return 0;
	}
	
	
	
	/**
	 * @param k
	 * @return
	 */
	public Luggage getValue(String k) {
		return checkindata.get(k);
	}
	
	/**
	 * 
	 */
	public void getReport() {
		String report="|Flight|Total Volume|Total Weight|Total excess fees|Passengers\n";
		 Set<Entry<String,Luggage>> hashSet=checkindata.entrySet();
	        for(Entry entry:hashSet ) {
	        	Luggage s = Luggage.class.cast(entry.getValue());
	        		
	        		System.out.println("Key="+entry.getKey()+", Value="+ s.getAccum_numberofpassengers());
	        }
		//return report;
	}
	
	

}
