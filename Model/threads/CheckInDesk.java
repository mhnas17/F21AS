package threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import data_structure_maps.BookingMap;
import data_structure_maps.FlightMap;
import data_structure_maps.LuggageMap;
import exceptions.NegativeNumbers;
import objects.Passenger;
import queue.WaitingQueue;

public class CheckInDesk implements Runnable {
	private WaitingQueue so;
	private BookingMap bm;
	private LuggageMap lm;
	private FlightMap fl;
	
	
	// map that contains check in desk threads
	Map<String, Thread> threads = new ConcurrentHashMap<String, Thread>();

	/**
	 * check in desk constructor
	 * @param so waiting queue
	 * @param bm map that contains bookings 
	 * @param lm map that contains accumulated flight details e.g. weight, volume etc
	 * @param fl map that contails flights
	 * @param threads active threads 
	 */
	public CheckInDesk(WaitingQueue so, BookingMap bm, LuggageMap lm, FlightMap fl, Map<String, Thread> threads) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl = fl;
		this.threads = threads;

	}

	// thread run method. It is facilitates the check in process
	public void run() {
		while (((!so.getDone() || so.getQueueSize() != 0))) {

			try {
				Thread.sleep(1600);

			} catch (InterruptedException e) {
				break;
			}

			try {
				if (!fl.getFlight("A1320").getTimerFinish()
						&& so.getQueueSize() != 0 && bm
								.getValue(so.getNextPassenger().getBookingreference()
										+ so.getNextPassenger().getName().getLastName())
								.getFlightcode().equals("A1320")) {
					Passenger number = so.get(bm, lm, fl);
				} else if (!fl.getFlight("B2430").getTimerFinish()
						&& so.getQueueSize() != 0 && bm
								.getValue(so.getNextPassenger().getBookingreference()
										+ so.getNextPassenger().getName().getLastName())
								.getFlightcode().equals("B2430")) {
					Passenger number = so.get(bm, lm, fl);
				} else if (!fl.getFlight("C3340").getTimerFinish()
						&& so.getQueueSize() != 0 && bm
								.getValue(so.getNextPassenger().getBookingreference()
										+ so.getNextPassenger().getName().getLastName())
								.getFlightcode().equals("C3340")) {
					Passenger number = so.get(bm, lm, fl);
				}

				else if ((fl.getFlight("A1320").getTimerFinish() || fl.getFlight("B2430").getTimerFinish()
						|| fl.getFlight("C3340").getTimerFinish()) && so.getQueueSize() != 0) {
					System.out.println("out " + so.getNextPassenger().getName().getFullName());
					so.removeFirst();
				} else if (fl.getFlight("A1320").getTimerFinish() && fl.getFlight("B2430").getTimerFinish()
						&& fl.getFlight("C3340").getTimerFinish()) {
					break;
				}
				
				else {
					break;
				}
			} catch (NegativeNumbers e) {
				e.printStackTrace();
			}

		}
	}

}

			
		
	


			