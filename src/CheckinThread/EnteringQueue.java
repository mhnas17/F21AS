package CheckinThread;

import core.PassengerList;

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
		for (int i = 0; i < array.getSize(); i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			so.put(array.getPassenger(i));
		}
		so.setDone();
	}
}
