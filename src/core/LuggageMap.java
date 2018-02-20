package core;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import name_plane.Plane;


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
	 * @return the total number of passengers in each flight
	 */
	public int getPassengersPerFlight(Flight e) {

		int s = checkindata.get(e.getFlightcode()).getAccum_numberofpassengers();
		return s;

	}

	/**
	 * @param e
	 * @return the total weight of bags in each flight
	 */
	public double getLuggageWeightPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_weight();
		return s;

	}

	/**
	 * @param e
	 * @return the total volume of bags in each flight
	 */
	public double getLuggageVolumePerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_volume();
		return s;

	}

	/**
	 * @param e
	 * @return the total excess fees collected in each flight
	 */
	public double getExcessFeesPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_excessfees();
		return s;

	}

	/**
	 * @param e
	 * @return the excess number of passengers in each flight
	 */
	public int getExcessPassengersPerFlight(Flight b) {

		int actual = checkindata.get(b.getFlightcode()).getAccum_numberofpassengers();
		int maximum  = b.getPlane().getMaxpassengers();
		if(actual>maximum){
			return actual - maximum;
		}
		else return 0;
	}

	/**
	 * @return the excess weight in each flight
	 */
	public double getExcessWeightPerFlight(Flight b) {
		double actual = checkindata.get(b.getFlightcode()).getAccum_weight();
		double maximum = b.getPlane().getMaxweight();		
		if(actual>maximum){
			return actual - maximum;
		}
		else return 0;
	}

	/**
	 * @return the excess volume in each flight
	 */
	public double getExcessVolumePerFlight(Flight b) {
		double actual = checkindata.get(b.getFlightcode()).getAccum_volume();
		double maximum = b.getPlane().getMaxvolume();		
		if(actual>maximum){
			return actual - maximum;
		}
		else return 0;
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
