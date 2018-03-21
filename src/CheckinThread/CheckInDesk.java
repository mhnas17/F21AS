package CheckinThread;

import java.util.ArrayList;
import java.util.Observable;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.LuggageMap;
import core.Passenger;
import core.FlightMap;

/**
 * Third attempt at solving the producer/consumer problem.
 */
public class CheckInDesk extends Observable implements Runnable {
	private WaitingQueue so;
	private BookingMap bm;
	private LuggageMap lm;
	private FlightMap fl;
	private String report;

	public CheckInDesk(WaitingQueue so, BookingMap bm,LuggageMap lm,FlightMap fl) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl=fl;
	}

	public void run() {
		while ((!so.getDone()|| so.getQueueSize()!=0) && !so.getTimerFinish()) {
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
			}
			try {				
						
				Passenger number = so.get(bm,lm,fl);
				
				report =so.getReport();
				notifier();	
				
			} catch (NegativeNumbers e) {
				e.printStackTrace();
			}

		}
		System.out.println("Flights have departed no more check ins");
		//System.exit(0);
	}
	
	public synchronized void notifier() {
						
		setChanged();
		notifyObservers();
    	    clearChanged();
    	
	}
	
	public synchronized String queueReport() {
		return report;
	}
}
