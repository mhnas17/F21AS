package core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import Exceptions.NegativeNumbers;

public class Queue {
	
	private BlockingQueue<Passenger> queue;
	
	Queue(){
		
		queue = new ArrayBlockingQueue<Passenger>(5);
	}
	
	public Passenger getPassenger() throws InterruptedException{
		
		Passenger p = queue.take();
		p.setLength(Math.random()*100+1);
		p.setHeight(Math.random()*100+1);
		p.setWeight(Math.random()*100+1);
		p.setWidth(Math.random()*100+1);;
		return p;
	}
	
	public void checkin(Passenger p,BookingMap book) throws NumberFormatException{
		
		// sets passenger as checked in
		book.getValue(p.getBookingreference() + p.getLastname()).setCheckedin(true);
	}	
	
	public void addtoqueue(Passenger p) throws InterruptedException{
		
		queue.put(p);
		
	}
	
	public void remove(){
		queue.poll();
	}
	
	public int size(){
		return queue.size();
	}
	
	
	

}
