package core;


import java.io.*;

import CheckinThread.WaitingQueue;
import CheckinThread.EnteringQueue;
import CheckinThread.Timer;
import CheckinThread.CheckInDesk;

import GUI.Gui;
import ReportLogs.CheckedInReport;
import ReportLogs.Log;
import name_plane.Name;
import name_plane.Plane;


public class Manager {

	private FlightMap entries;
	private LuggageMap lug;
	private BookingMap book;
	private BookingLists lists;
	private Gui gui;
	private Luggage l;
	private PassengerList passengerlist;
	private Passenger passenger;
	
	/** Reads the two csv files and creates the hashmaps and starts the Gui
	 * @throws Exception
	 */
	public Manager(String flight,String booking) throws Exception {

		entries = new FlightMap();
		lug = new LuggageMap();
		book = new BookingMap();
		lists = new BookingLists();
		passengerlist = new PassengerList();
		
		BufferedReader buff = null;
		String data[] = new String[4];
		try {
			Plane p1 = null;
			buff = new BufferedReader(new FileReader(flight));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				if (inputLine.length() != 0) {	// skip empty lines
					// split line into parts
					data = inputLine.split(",");
					// define plane type
					String planeType = data[3];
					if (planeType.equals("A1")) {
						p1 = new Plane(10, 200, 3.75);
					} else if (planeType.equals("A2")) {
						p1 = new Plane(10, 200, 3.75);
					} else {
						p1 = new Plane(13, 260, 4.875);
					}
					// create flight object
					Flight s = new Flight(data[0], data[1], data[2], p1);
					l = new Luggage(0, 0, 0, 0);
					// add to flights hashmap
					entries.add(data[0], s);
					// add to luggage hashmap
					lug.add(data[0], l);
				}
				// read next line
				inputLine = buff.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		finally {
			try {
				buff.close();
			} catch (IOException ioe) {
				// don't do anything
			}
		}
		try {

			buff = new BufferedReader(new FileReader(booking));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				if (inputLine.length() != 0) {	// skip empty lines
					// split line into parts
					data = inputLine.split(",");				
					Name name = new Name(data[1]);
					// create booking object
					Booking b = new Booking(data[0], name, data[2], Boolean.parseBoolean(data[3]));
					// add to booking hash map
					book.add(data[0] + name.getLastName(), b);
					// add booking references to a list
					lists.addBookingReferences(data[0]);
					// add last names to a list
					lists.addLastName(name.getLastName());
					//creates passenger object
					passenger = new Passenger(data[0],name,0,0,0,0);
	                //adds passenger to passenger list
					passengerlist.add(passenger);
					}
				// read next line
				inputLine = buff.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		finally {
			try {
				buff.close();
			} catch (IOException ioe) {
				
			}
		}

	}
	
	public PassengerList getPassengerList(){
		
		return passengerlist;
	}
	
    public BookingMap getBookingMap(){
		return book;
	}
    
    public LuggageMap getLuggageMap() {
    	return lug;
    }
    
    public FlightMap getFlightMap() {
    	return entries;
    }

	public void showGui(WaitingQueue so,Manager p) {
		gui = new Gui(so,p);
	}

	public void report() {
		lug.getReport(entries);
	}

	public static void main(String[] args) throws Exception {

		Manager p = new Manager("flights.csv","bookings.csv");
		WaitingQueue so = new WaitingQueue();
		CheckedInReport r=new CheckedInReport(so);
        Thread timer = new Thread (new Timer(20,so));
		timer.start();
		Thread eq = new Thread(new EnteringQueue(so,p.getPassengerList()));
		eq.start();
		/*CheckInDesk s1 = new CheckInDesk(so,p.getBookingMap(),p.getLuggageMap(),p.getFlightMap());		
		Thread ci = new Thread(s1,"1");
		ci.start();
		CheckInDesk s2 = new CheckInDesk(so,p.getBookingMap(),p.getLuggageMap(),p.getFlightMap());
		Thread cii = new Thread(s2,"2");
		cii.start();*/
		p.showGui(so,p);
		
		Log.log(r.getCheckedInReport());
		
	
		
		

	}

}
