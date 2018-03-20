package CheckinThread;

import java.util.LinkedList;
import java.util.Observable;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.LuggageMap;
import core.Passenger;
import core.FlightMap;

public class WaitingQueue{
	
	private boolean empty;
	private boolean done;
	private boolean timer;
	
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
		
		System.out.println("Got: " + n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight());
		//System.out.println("Flight details: " + lug.getReport(fl));
		
		
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
		n.setLength((double)Math.round(Math.random()*100+1));
		n.setHeight((double)Math.round(Math.random()*100+1));
		n.setWeight((double)Math.round(Math.random()*100+1));
		n.setWidth((double)Math.round(Math.random()*100+1));
		
		queue.addLast(n);
		System.out.println("Put: " + n.getName().getFullName());
		empty = false;
		notifyAll();
		
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
	
	public synchronized String getReport(){
		passenger="";
		for (Passenger n : queue){
			passenger+= n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight() + "\n";
		}		
		return passenger;
	}
	
	
	
	
}