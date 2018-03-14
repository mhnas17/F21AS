package CheckinThread;

import core.BookingMap;
import core.Passenger;

/**
 * Third attempt at solving the producer/consumer problem.
 */
public class CheckInDesk implements Runnable {
	private WaitingQueue so;
	private BookingMap bm;

	public CheckInDesk(WaitingQueue so, BookingMap bm) {
		this.so = so;
		this.bm = bm;
	}

	public void run() {
		while ((!so.getDone()|| so.getQueueSize()!=0) && !so.getTimerFinish()) {
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
			}
			Passenger number = so.get(bm);

		}
		System.out.println("Flights have departed no more check ins");
		System.exit(0);
	}
}
