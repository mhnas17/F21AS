package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class LuggageMap {

	private HashMap<String, Luggage> checkindata; 
	
	
	public LuggageMap(){ 
		checkindata = new HashMap<String, Luggage>();
		
	}
	
	public void add(String key,Luggage value) {
		checkindata.put(key, value);
	}
			
	public int getPassengersPerFlight(Flight e) {

		int s = checkindata.get(e.getFlightcode()).getAccum_numberofpassengers();
		return s;

	}

	public double getLuggageWeightPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_weight();
		return s;

	}

	public double getLuggageVolumePerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_volume();
		return s;

	}

	public double getExcessFeesPerFlight(Flight e) {

		double s = checkindata.get(e.getFlightcode()).getAccum_excessfees();
		return s;

	}

	public int getExcessPassengersPerFlight(Flight e) {

		return 0;
	}

	public double getExcessWeightPerFlight() {

		return 0;
	}

	public double getExcessVolumePerFlight() {

		return 0;
	}
	
	
	
	public Luggage getValue(String k) {
		return checkindata.get(k);
	}
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
