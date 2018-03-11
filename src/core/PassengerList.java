package core;

import java.util.ArrayList;

public class PassengerList {
	private ArrayList<Passenger> passengers;

	public PassengerList() {
		this.passengers = new ArrayList<> ();
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
	
	public Passenger get(int i) {
		return passengers.get(i);
	}
	
	
	public int getSize() {
		return passengers.size();
	}
}
