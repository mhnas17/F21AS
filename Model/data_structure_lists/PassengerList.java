package data_structure_lists;

import java.util.ArrayList;
import java.util.Random;
import objects.Passenger;

public class PassengerList {
	private ArrayList<Passenger> passengers;
	private Random random;

	/**
	 * constructor
	 */
	public PassengerList() {
		passengers = new ArrayList<>();
		random = new Random();
	}

	/**
	 * @param ppassenger object adds passenger object to the array list of
	 * passengers
	 */
	public void add(Passenger p) {
		passengers.add(p);
	}

	/**
	 * @param br booking reference
	 * @return passenger object that corresponds to booking reference
	 */
	public Passenger find(String br) {
		for (Passenger c : passengers) {
			if (c.getBookingreference() == br) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @return random passenger from the array list
	 */
	public Passenger getPassenger() {
		int x = random.nextInt(passengers.size());
		return passengers.get(x);
	}

	/**
	 * @param p passenger removes from the array list the passenger object that is
	 * provided in the parameter
	 */
	public void removePassenger(Passenger p) {
		passengers.remove(p);
	}

	/**
	 * @return the size of the array list of passengers
	 */
	public int getSize() {
		return passengers.size();
	}
}
