package CheckinThread;

import core.PassengerList;

import java.util.Observable;

import core.Passenger;

/**
 * Third attempt at solving the producer/consumer problem.
 */

public class EnteringQueue implements Runnable {
	private WaitingQueue so;
	private PassengerList array;

	public EnteringQueue(WaitingQueue so, PassengerList array) {
		this.so = so;
		this.array = array;
	}

	public void run() {
		while (array.getSize() != 0) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				break;
			}

			Passenger p = array.getPassenger();
			so.put(p);
			array.removePassenger(p);

		}
		so.setDone();
	}

}
