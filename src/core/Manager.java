package core;


import java.io.*;

import booking.Booking;
import booking.BookingLists;
import booking.BookingMap;
import flight.Flight;
import flight.FlightMap;
import gui.Gui;
import luggage.Luggage;
import luggage.LuggageMap;
import support.Name;
import support.Plane;


public class Manager {

	private FlightMap entries;
	private LuggageMap lug;
	private BookingMap book;
	private BookingLists lists;
	private Gui gui;
	private Luggage l;
	/** Reads the two csv files and creates the hashmaps and starts the Gui
	 * @throws Exception
	 */
	public Manager() throws Exception {

		entries = new FlightMap();
		lug = new LuggageMap();
		book = new BookingMap();
		lists = new BookingLists();
		
		BufferedReader buff = null;
		String data[] = new String[4];
		try {
			Plane p1 = null;
			buff = new BufferedReader(new FileReader("flights.csv"));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				// split line into parts
				data = inputLine.split(",");
				// create staff object
				String planeType = data[3];
				if (planeType.equals("A1")) {
					p1 = new Plane(10, 200, 3.75);
				} else if (planeType.equals("A2")) {
					p1 = new Plane(10, 200, 3.75);
				} else {
					p1 = new Plane(13, 260, 4.875);
				}
				Flight s = new Flight(data[0], data[1], data[2], p1);
				l = new Luggage(0, 0, 0, 0);
				// add to list
				entries.add(data[0], s);
				lug.add(data[0], l);
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

			buff = new BufferedReader(new FileReader("bookings.csv"));
			String inputLine = buff.readLine(); // read first line
			while (inputLine != null) {
				// split line into parts
				data = inputLine.split(",");
				// create staff object
				Name n = new Name(data[1]);
				Booking b = new Booking(data[0], n, data[2], Boolean.parseBoolean(data[3]));
				// add to list
				book.add(data[0] + n.getLastName(), b);
				lists.addBookingReferences(data[0]);
				// lists.sysoutBooking();
				Name name = new Name(data[1]);
				lists.addLastName(name.getLastName());
				// lists.sysoutName();
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

	public void showGui() {
		gui = new Gui(book, lug, lists, entries,l);
	}

	public void report() {
		lug.getReport(entries);
	}

	public static void main(String[] args) throws Exception {

		Manager p = new Manager();
		p.showGui();

	}

}
