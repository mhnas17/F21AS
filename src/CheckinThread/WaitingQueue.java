package CheckinThread;

import java.util.LinkedList;

import core.BookingMap;
import core.Passenger;

public class WaitingQueue {
	
	private boolean empty;
	private boolean done;
	
	private LinkedList<Passenger> queue;

	public WaitingQueue() {
		
		queue = new LinkedList<>();
		empty = true;
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized  Passenger get(BookingMap book) {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Passenger n = queue.pollFirst();
		
		book.getValue(n.getBookingreference() + n.getLastname()).setCheckedin(true);
		
		System.out.println("Got: " + n.getLastname() +" " +  Thread.currentThread());
		
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
		n.setLength(Math.random()*100+1);
		n.setHeight(Math.random()*100+1);
		n.setWeight(Math.random()*100+1);
		n.setWidth(Math.random()*100+1);
		
		queue.addLast(n);
		System.out.println("Put: " + n.getLastname());
		empty = false;
		notifyAll();
		
	}

	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}
	
	
	public int getQueueSize(){
		return queue.size();
	}
}
