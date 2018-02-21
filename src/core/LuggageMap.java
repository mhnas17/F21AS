package core;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		else {
			return 0;
			}
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
	 * @return the luggage object that corresponds to the key provided in the parameter
	 */
	public Luggage getValue(String k) {
		return checkindata.get(k);
	}
	
	/** first for each is for the general details of the flights
	 * @param f
	 * @return a String with the desired information so it can be printed out 
	 */
	public String getReport(FlightMap f) {
		String report="| Flight | Total Volume(m^3) | Total Weight(kg) | Total excess fees(£) | Passengers |\n";
		 Set<Entry<String,Luggage>> hashSet=checkindata.entrySet();
	        for(Entry entry:hashSet ) {
	        	Luggage s = Luggage.class.cast(entry.getValue());
	        		report+= String.format("  %-6s",entry.getKey()) +"       "+ String.format("%-12.3f",s.getAccum_volume())+"    " + String.format("       %-12.2f",s.getAccum_weight())+ String.format("      %-17.2f",s.getAccum_excessfees()) + String.format("    %-11d", s.getAccum_numberofpassengers()) +"\n";
	        		
	        }
	        report+="\n";
	        report+="Flights that have exceeded Capacity\n";
	        report+="===================================\n";
	        report+="|Flight| Exceeding passengers|Exceeding weight(kg)|Excceeding Volume(m^3)|\n";
	        // this for each checks and tests for whether there are any exceeded values of flights. prints everything regardless in a table
	        for(Entry entry:hashSet ) {
	        	Luggage s = Luggage.class.cast(entry.getValue());
	        	String flightNum = entry.getKey().toString();	
	        	report+=  String.format("  %-5s",flightNum) + String.format("           %-5d",getExcessPassengersPerFlight(f.getFlight(flightNum)))+ String.format("              %-5.2f",getExcessWeightPerFlight(f.getFlight(flightNum)))+ String.format("                %-5.3f",getExcessVolumePerFlight(f.getFlight(flightNum)))+"\n"; 
	        	
	        }
	       
	    //System.out.println(report); printing the report on the console for testing the format of the table
		return report;
	}
	
	/**Writes an output file of the info we want file
	 * @param filename
	 * @param data
	 */
	public void writeToFile(String filename, String data) {		
		 FileWriter filewriter;
		 try {
		    filewriter = new FileWriter(filename);		    
		    filewriter.write(data);
		 	filewriter.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}
	

}
