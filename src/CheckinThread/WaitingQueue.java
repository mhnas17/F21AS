package CheckinThread;

import java.util.LinkedList;
import java.util.Observable;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.LuggageMap;
import core.Passenger;
import core.FlightMap;

public class WaitingQueue extends Observable{
	
	private boolean empty;
	private boolean done;
	private boolean timer;
	
	private String reportGet;
	private String reportPut;
	
	private String passenger;
	
	private LinkedList<Passenger> queue;

	public WaitingQueue() {
		
		queue = new LinkedList<>();
		empty = true;
	}
	
	
	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized  Passenger get(BookingMap book,LuggageMap lug,FlightMap fl) throws NegativeNumbers {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Passenger n = queue.pollFirst();		
		
		book.getValue(n.getBookingreference() + n.getName().getLastName()).setCheckedin(true);
		
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode()).setAccum_weight(n.getWeight());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode()).setAccum_volume(n.getVolume());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode()).setAccum_excessfees(n.getExcessfees());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode()).setAccum_numberofpassengers(1);
		
		reportGet = "Got: " + n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight();
		System.out.println("Got: " + n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight());
				
		if(queue.isEmpty()){
			empty = true;
		}
				
		notifyAll();
				
		return n;
	}

	// wait while number there
	// when waiting over, put number
	// set empty to false and notify waiting methods
	//!empty
	public synchronized void put(Passenger n) {
		
		while (queue.size()>=5) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// generates random numbers for weight and dimension of luggage
		n.setLength(Math.round(Math.random()*100+1));
		n.setHeight(Math.round(Math.random()*100+1));
		n.setWeight(Math.round(Math.random()*100+1));
		n.setWidth(Math.round(Math.random()*100+1));
		
		queue.addLast(n);
		System.out.println("Put: " + n.getName().getFullName());
		empty = false;
	
		
		notifyAll();
		getReport();
		
	}

	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}
	
	public void setTimerFinished() {
		timer =true;
	}
	
	public boolean getTimerFinish() {
		return timer;
	}
	
	public int getQueueSize(){
		return queue.size();
	}
	
	public synchronized void getReport(){
		passenger="";
		for (Passenger n : queue){
			passenger+= n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight() + "\n";
		}
		notifier(passenger);
	}
	
	public synchronized void notifier(String x) {		
		setChanged();
		notifyObservers(x);
    	clearChanged();    	
	}	
	
	public synchronized String checkInReport() {
		return reportGet;
	}
}
