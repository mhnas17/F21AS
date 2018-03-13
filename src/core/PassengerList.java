package core;

import java.util.ArrayList;

public class PassengerList {
	private ArrayList<Passenger> passengers;

	public PassengerList() {
		      passengers = new ArrayList<> ();
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
	
	public Passenger getPassenger(int i) {
		return passengers.get(i);
	}
	
	
	public int getSize() {
		return passengers.size();
	}
}
