package CheckinThread;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.swing.internal.plaf.metal.resources.metal;

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
	//ArrayList<Thread> threads;
	Map<String,Thread> threads = new ConcurrentHashMap<String,Thread>();

	public CheckInDesk(WaitingQueue so, BookingMap bm, LuggageMap lm, FlightMap fl, Map<String,Thread> threads) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl = fl;
		this.threads = threads;
	}
	

	public void run() {
		//for (Thread p : threads.values()) {
			while (((!so.getDone() || so.getQueueSize() != 0) && !so.getTimerFinish())) {
				try {
					Thread.sleep(1500);

				} catch (InterruptedException e) {
					break;
				}
				try {
					

					Passenger number = so.get(bm, lm, fl);

				} catch (NegativeNumbers e) {
					e.printStackTrace();
				}
				

			}
		//}
	}
	
	public void stopThread(String x) {
		this.threads.get(x).interrupt();
	}

}
