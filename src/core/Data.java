package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Data {

	
	HashMap <String, Booking> bookings = new HashMap <String, Booking> ();
	HashMap <String,Flight> flights = new HashMap <String,Flight>();
	HashMap <String,Luggage> checkindata = new HashMap <String,Luggage>();
	
	
	/** top-level process to read,.... 
	 * @param filename  The name of the file containing the text
	 */
	public void process(String filename1, String filename2){

		//read text file1
		readtextfile(filename1); 
		readtextfile(filename2);
			
	}
	
	/**
	 * reads file with given name, extracting data, creating objects and
	 * adding them to the maps.
	 * Blank Lines are skipped
	 * @param filename the name of the input file       
	 */
	public void readtextfile(String filename) {
		try {
			File inputfile = new File(filename);
			Scanner scanner = new Scanner(inputfile);
			while (scanner.hasNextLine()) {
				// read first line and process it
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {// ignored if empty line
					processLine(inputLine);
				}

			}
		}
		// if the file is not found, stop with system exit
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
	}
	
	/**
	 * Processes line, extracts data, creates object and adds to map.
	 * Will crash if the name is entered without a space
	 * @param line the line to to be processed
	 */
	private void processLine(String line) {
		try {
			
			
		}
		// ignore lines in error and try to carry on
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in " + line + " " + nfe.getMessage();
			System.out.println(error);
		}

		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enogh items  in '" + line + "' index position: " + air.getMessage();
			System.out.println(error);
		}

	}
		
	public int getPassengersPerFlight(Flight e){
					
		int s = checkindata.get(e.getFlightcode()).getAccum_numberofpassengers();
		return s;
				
	}
	
	
	public double getLuggageWeightPerFlight(Flight e){
		
		double  s = checkindata.get(e.getFlightcode()).getAccum_weight();
		return s;
		
	}

   public double getLuggageVolumePerFlight(Flight e){
		
	   double  s = checkindata.get(e.getFlightcode()).getAccum_volume();
		return s;
		
	}
	
   public double getExcessFeesPerFlight(Flight e){
	
	   double  s = checkindata.get(e.getFlightcode()).getAccum_excessfees();
		return s;
	
    }
	
   
   public int getExcessPassengersPerFlight(Flight e){
		
	   
		return 0;
	}
	
   
   public double getExcessWeightPerFlight(){
		
		return 0;
	}
   
   public double getExcessVolumePerFlight(){
		
		return 0;
	}
   
   public void writeToFile(String filename, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			fw.write("The report\n");
			fw.write(report);
			fw.close();
		}
		// message and stop if file not found
		catch (FileNotFoundException fnf) {
			System.out.println(filename + "not found");
			System.exit(0);
		}
		// stack trace here because we don't expect to come here
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
   
   
}
