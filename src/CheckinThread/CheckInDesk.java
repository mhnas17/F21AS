package CheckinThread;

import java.util.ArrayList;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.LuggageMap;
import core.Passenger;
import core.FlightMap;

/**
 * Third attempt at solving the producer/consumer problem.
 */
public class CheckInDesk implements Runnable {
	private WaitingQueue so;
	private BookingMap bm;
	private LuggageMap lm;
	private FlightMap fl;
	ArrayList<Thread> threads = new ArrayList<>();

	public CheckInDesk(WaitingQueue so, BookingMap bm, LuggageMap lm, FlightMap fl, ArrayList<Thread> threads) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl = fl;
		this.threads = threads;
	}

	public void run() {
		for (Thread p : threads) {
			while (((!so.getDone() || so.getQueueSize() != 0) && !so.getTimerFinish()) && !p.isInterrupted()) {

				try {

					Passenger number = so.get(bm, lm, fl);

				} catch (NegativeNumbers e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1500);

				} catch (InterruptedException e) {
				}

			}
		}
	}

}
