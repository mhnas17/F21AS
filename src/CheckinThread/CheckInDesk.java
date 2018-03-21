package CheckinThread;

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
	

	public CheckInDesk(WaitingQueue so, BookingMap bm,LuggageMap lm,FlightMap fl) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl=fl;
	}

	public void run() {
		
		while ((!so.getDone()||so.getQueueSize()!=0) && !so.getTimerFinish()) {
			 
			 try {				
					
					Passenger number = so.get(bm,lm,fl);
								
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
