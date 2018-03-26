package objects;

import java.io.*;

import controller.CheckinControler;
import data_structure_lists.BookingLists;
import data_structure_lists.PassengerList;
import data_structure_maps.BookingMap;
import data_structure_maps.FlightMap;
import data_structure_maps.LuggageMap;
import gui.Gui;
import queue.WaitingQueue;
import report_logs.CheckedInReport;
import report_logs.Log;
import report_logs.QueueReport;
import threads.CheckInDesk;
import threads.EnteringQueue;
import threads.Timer;

public class Manager {

	private FlightMap entries;
	private LuggageMap lug;
	private BookingMap book;
	private BookingLists lists;
	private Gui gui;
	private Luggage l;
	private PassengerList passengerlist;
	private Passenger passenger;

	/**
	 * Reads the two csv files and creates the hash maps and starts the Gui
	 * 
	 * @throws Exception
	 */
	public Manager(String flight, String booking) throws Exception {

		entries = new FlightMap();
		lug = new LuggageMap();
		book = new BookingMap();
		lists = new BookingLists();
		passengerlist = new PassengerList();

		// read csv file that contains flight details, creates flight objects and stores
		// them in the flight map
		BufferedReader buff = null;
		String data[] = new String[4];
		try {
			PlaneType p1 = null;
			buff = new BufferedReader(new FileReader(flight));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				if (inputLine.length() != 0) { // skip empty lines
					// split line into parts
					data = inputLine.split(",");
					// define plane type
					String planeType = data[3];
					if (planeType.equals("A1")) {
						p1 = new PlaneType(30, 200, 3.75);
					} else if (planeType.equals("A2")) {
						p1 = new PlaneType(40, 200, 3.75);
					} else {
						p1 = new PlaneType(50, 260, 4.875);
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

		// read csv file that contains bookings and passenger details, fills the maps
		// and lists that contain passenger details
		try {

			buff = new BufferedReader(new FileReader(booking));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				if (inputLine.length() != 0) { // skip empty lines
					// split line into parts
					data = inputLine.split(",");
					PassengerName name = new PassengerName(data[1]);
					// create booking object
					Booking b = new Booking(data[0], name, data[2], Boolean.parseBoolean(data[3]));
					// add to booking hash map
					book.add(data[0] + name.getLastName(), b);
					// add booking references to a list
					lists.addBookingReferences(data[0]);
					// add last names to a list
					lists.addLastName(name.getLastName());
					// creates passenger object
					passenger = new Passenger(data[0], name, 0, 0, 0, 0);
					// adds passenger to passenger list
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

	/**
	 * @return return the the passenger array list
	 */
	public PassengerList getPassengerList() {

		return passengerlist;
	}

	/**
	 * @return the map that contains passenger objects
	 */
	public BookingMap getBookingMap() {
		return book;
	}

	/**
	 * @return the map that contains objects which store the accumulated volume,
	 *         passengers number and weight for each flight
	 */
	public LuggageMap getLuggageMap() {
		return lug;
	}

	/**
	 * @return map that contains flight objects
	 */
	public FlightMap getFlightMap() {
		return entries;
	}

	/**
	 * return report for the accumulated details for each flight
	 */
	public void report() {
		lug.getReport(entries);
	}

	/**
	 * creates a total report of checked in passengers , passengers that missed
	 * their flights and status of flight
	 * 
	 * @param r CheckedInReport
	 * @param q QueueReport
	 * @param lug LuggageMap
	 * @param p FlightMap
	 * @param so WaitingQueue
	 * @return
	 */
	public String getFinalReport(CheckedInReport r, QueueReport q, LuggageMap lug, FlightMap p, WaitingQueue so) {
		String finalReport = r.getCheckedinReport() + "\n" + q.getQueueReport() + "\n" + lug.getReport(p) + "\n"
				+ so.removeReport();
		System.out.println(finalReport);
		return finalReport;
	}

}
