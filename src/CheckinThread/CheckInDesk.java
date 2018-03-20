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
	
	//private String name;
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
				report =so.getReport();				
				Passenger number = so.get(bm,lm,fl);				
				//name = number.getName().getFullName();
				notifier();
			} catch (NegativeNumbers e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Flights have departed no more check ins");
		//System.exit(0);
	}
	
	public synchronized void notifier() {
		
				
		//System.out.println("hi");

		//update view display
		setChanged();
		notifyObservers();
    	clearChanged();
    	
	}
	
	public synchronized String queueReport() {
		return report;
	}
}
