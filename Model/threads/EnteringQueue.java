package threads;

import java.util.Observable;

import data_structure_lists.PassengerList;
import objects.Passenger;
import queue.WaitingQueue;

/**
 * 
 */

public class EnteringQueue implements Runnable {
	private WaitingQueue so;
	private PassengerList array;

	public EnteringQueue(WaitingQueue so, PassengerList array) {
		this.so = so;
		this.array = array;
	}

	/* (non-Javadoc)
	 * run method of the entring the queue	
	 *  */
	public void run() {
		while (array.getSize() != 0) {
			/*try {
				Thread.sleep(1);

			} catch (InterruptedException e) {
				break;
			}*/

			Passenger p = array.getPassenger();
			so.put(p);
			array.removePassenger(p);

		}
		so.setDone();
	}

}
