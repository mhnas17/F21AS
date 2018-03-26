package threads;

import data_structure_lists.PassengerList;
import objects.Passenger;
import queue.WaitingQueue;

public class EnteringQueue implements Runnable {
	private WaitingQueue so;
	private PassengerList array;

	/**
	 * constructor 
	 * @param so waiting queue
	 * @param array array list of passengers
	 */
	public EnteringQueue(WaitingQueue so, PassengerList array) {
		this.so = so;
		this.array = array;
	}

	// Queue feeder. It is adding passenger to the queue
	public void run() {
		while (array.getSize() != 0) {

			Passenger p = array.getPassenger();
			so.put(p);
			array.removePassenger(p);

		}
		so.setDone();
	}

}
