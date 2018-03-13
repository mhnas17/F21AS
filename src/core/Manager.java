package core;


import java.io.*;

import CheckinThread.WaitingQueue;
import CheckinThread.EnteringQueue;
import CheckinThread.CheckInDesk;

import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;


public class Manager {

	private FlightMap entries;
	private LuggageMap lug;
	private BookingMap book;
	private BookingLists lists;
	private NewGui gui;
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
					Name n = new Name(data[1]);
					// create booking object
					Booking b = new Booking(data[0], n, data[2], Boolean.parseBoolean(data[3]));
					// add to booking hash map
					book.add(data[0] + n.getLastName(), b);
					// add booking references to a list
					lists.addBookingReferences(data[0]);
					// add last names to a list
					lists.addLastName(n.getLastName());
					//creates passenger object
					passenger = new Passenger(data[0],n.getLastName(),0,0,0,0);
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
				// don't do anything
			}
		}

	}
	
	public PassengerList getPassengerList(){
		
		return passengerlist;
	}
	
    public BookingMap getBookingMap(){
		
		return book;
	}

	public void showGui() {
		// create a gui object
		gui = new NewGui(book, lug, lists, entries,l);
	}

	public void report() {
		lug.getReport(entries);
	}

	public static void main(String[] args) throws Exception {

		Manager p = new Manager("flights.csv","bookings.csv");
		
		
        WaitingQueue so = new WaitingQueue();
		
		Thread eq = new Thread(new EnteringQueue(so,p.getPassengerList()));
		eq.start();
		Thread ci = new Thread(new CheckInDesk(so,p.getBookingMap()));
		ci.start();
		
		
		// p.showGui();
		
	
		
		

	}

}
