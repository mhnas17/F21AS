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
	private boolean timer = false;
	
	private String reportGet;
	private String reportRemove = "Passengers that Showed up but didn't check in\n" + "=======================================\n";
	
	
	
	private String passenger;
	
	private boolean flag=false;
	
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
		
		reportGet = n.getName().getFullName() +" is droping off 1 bag of " + n.getWeight()+"kg.";
		
		if(n.getExcessfees()!=0) {
			
			reportGet += "\nA baggage fee of $"+ n.getExcessfees() + " is due.";
		}
			
		System.out.println(Thread.currentThread().getName()+" Got: " + n.getName().getFullName() +" " +  n.getWeight()+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight());
				
		if(queue.isEmpty()){
			empty = true;
		}
				
		notifyAll();
		flag=true;
		getReport();	
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
		flag=false;
		
	}
	
	public boolean returnFlag() {
		return flag;
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
		passenger = "There are currently " + queue.size() + " people waiting in the queue:\n\n";
		//passenger+= "|Booking Reference |   |Passenger Initials|       |Bag Weight|       |Bag Dimension|\n";
		
		for (Passenger n : queue){
			
			passenger+= n.getBookingreference() +"    "+((int) n.getWeight())+"kg" +"    "+((int) n.getHeight())+"x"+((int) n.getLength())+"x"+((int) n.getWidth())+"    "+n.getName().getFullName()+"\n";                       
					
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
	
	public synchronized Passenger getNextPassenger() {
		return queue.element();
	}
	
	public synchronized void removeFirst() {
		
		reportRemove += queue.element().getName().getFullName()+"\n";
			
		queue.remove();
		
		getReport();
	}
	
	public String removeReport() {
		return reportRemove;
	}
}