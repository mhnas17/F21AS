package core;

import java.util.ArrayList;
import java.util.Random;

public class PassengerList {
	private ArrayList<Passenger> passengers;
	private Random random;

	public PassengerList() {
		      passengers = new ArrayList<> ();
		      random = new Random();
	}
	
	public void add(Passenger p) {
		passengers.add(p);
	}
	
	public Passenger find(String br) {
		for (Passenger c : passengers) {
			if (c.getBookingreference()  == br){
				return c;
			}
		}
		return null;
	}
	
	public Passenger getPassenger() {
		int x = random.nextInt(passengers.size());
		return passengers.get(x);
	}
	
	public void removePassenger(Passenger p) {
		passengers.remove(p);
	}
	
	
	public int getSize() {
		return passengers.size();
	}
}
