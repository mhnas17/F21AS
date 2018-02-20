package core;

import java.util.InputMismatchException;
import java.io.*;
import Exceptions.InvalidBookingReference;
import Exceptions.InvalidFlightCode;
import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;
import java.util.*;
import java.util.HashMap;

public class Manager {

	private FlightMap entries;
	private LuggageMap lug;
	private BookingMap book;
	private BookingLists lists;
	private NewGui gui;
	
	public Manager() throws InvalidFlightCode, InvalidBookingReference {

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
					p1 = new Plane(140, 1300, 900);
				} else if (planeType.equals("A2")) {
					p1 = new Plane(200, 1500, 1000);
				} else {
					p1 = new Plane(250, 1800, 1200);
				}
				Flight s = new Flight(data[0], data[1], data[2], p1);
				Luggage l = new Luggage(0, 0, 0, 0);
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
				//lists.sysoutBooking();
				Name name = new Name(data[1]);
				lists.addLastName(name.getLastName());
				//lists.sysoutName();
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
		gui = new NewGui(book, lug, lists);
	}
	
	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		// Data s= new Data();
		// s.process("textfile1", "textfile2");
		// NewGui demo = new NewGui();
		// Booking b = new Booking("AF115", new Name("Minadakis George"), "A1320",
		// false);
		// Flight c = new Flight("A13201", "London", "AEGEAN", new Plane(140, 300,
		// 500));
		Manager p = new Manager();
		p.showGui();

	}

}
