package core;

import java.util.HashMap;

public class LuggageMap {

	private HashMap<String, Luggage> checkindata; 
	
	
	public LuggageMap(){ 
		checkindata = new HashMap<String, Luggage>();
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
	
}
