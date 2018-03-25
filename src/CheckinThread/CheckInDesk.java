package CheckinThread;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.swing.internal.plaf.metal.resources.metal;

import Exceptions.NegativeNumbers;
import ReportLogs.QueueReport;
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
	private String deniedBoardingReport="People that were denied boarding\n"+"=====================================\n";
	Map<String, Thread> threads = new ConcurrentHashMap<String, Thread>();
	
	public CheckInDesk(WaitingQueue so, BookingMap bm, LuggageMap lm, FlightMap fl, Map<String, Thread> threads) {
		this.so = so;
		this.bm = bm;
		this.lm = lm;
		this.fl = fl;
		this.threads = threads;
		
	}
	
	public void run() {
		while (((!so.getDone() || so.getQueueSize() != 0) )) {
			try {
				Thread.sleep(1500);

			} catch (InterruptedException e) {
				break;
			}
			
			try {
				if (!fl.getFlight("A1320").getTimerFinish() && so.getQueueSize()!=0 && bm.getValue(so.getNextPassenger().getBookingreference() + so.getNextPassenger().getName().getLastName()).getFlightcode().equals("A1320")) {
					Passenger number = so.get(bm, lm, fl);
				}
				else if  (!fl.getFlight("B2430").getTimerFinish() && so.getQueueSize()!=0 && bm.getValue(so.getNextPassenger().getBookingreference() + so.getNextPassenger().getName().getLastName()).getFlightcode().equals("B2430")) {
					Passenger number = so.get(bm, lm, fl);
				}
				else if (!fl.getFlight("C3340").getTimerFinish() && so.getQueueSize()!=0 && bm.getValue(so.getNextPassenger().getBookingreference() + so.getNextPassenger().getName().getLastName()).getFlightcode().equals("C3340")) {
					Passenger number = so.get(bm, lm, fl);
				}
				else if(fl.getFlight("A1320").getTimerFinish()&&fl.getFlight("B2430").getTimerFinish()&&fl.getFlight("C3340").getTimerFinish()){
					break;
				}
				else if((fl.getFlight("A1320").getTimerFinish()||fl.getFlight("B2430").getTimerFinish()||fl.getFlight("C3340").getTimerFinish()) && so.getQueueSize()!=0) {
					System.out.println("out"+so.getNextPassenger().getName().getFullName());
					//deniedBoardingReport+=so.getNextPassenger().getName().getFullName()+"\n";
					so.removeFirst();
				}// anything under here needs to be rechecked
				else {
					break;
				}
			} catch (NegativeNumbers e) {
				e.printStackTrace();
			}

		}
	}
	
	

}

			
		
	


			