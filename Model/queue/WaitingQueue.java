package queue;

import java.util.LinkedList;
import java.util.Observable;

import data_structure_maps.BookingMap;
import data_structure_maps.FlightMap;
import data_structure_maps.LuggageMap;
import exceptions.NegativeNumbers;
import objects.Passenger;

public class WaitingQueue extends Observable {

	private boolean empty;
	private boolean done;
	private boolean timer = false;

	private String reportGet;
	private String reportRemove = "Passengers that Showed up but didn't check in\n"
			+ "=======================================\n";

	private String passenger;
	private boolean flag = false;
    private LinkedList<Passenger> queue;

	/**
	 * create new linked list 
	 */
	public WaitingQueue() {

		queue = new LinkedList<>();
		empty = true;
	}

	
	/**
	 * removes one passenger from the queue
	 * @param book booking map
	 * @param lug luggage map
	 * @param fl flight map
	 * @return passenger object
	 * @throws NegativeNumbers
	 */
	public synchronized Passenger get(BookingMap book, LuggageMap lug, FlightMap fl) throws NegativeNumbers {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Passenger n = queue.pollFirst();

		book.getValue(n.getBookingreference() + n.getName().getLastName()).setCheckedin(true);

		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode())
				.setAccum_weight(n.getWeight());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode())
				.setAccum_volume(n.getVolume());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode())
				.setAccum_excessfees(n.getExcessfees());
		lug.getValue(book.getValue(n.getBookingreference() + n.getName().getLastName()).getFlightcode())
				.setAccum_numberofpassengers(1);

		reportGet = n.getName().getFullName() + " is droping off 1 bag of " + ((int) n.getWeight()) + "kg.";

		if (n.getExcessfees() != 0) {

			reportGet += "\nA baggage fee of $" + ((int) n.getExcessfees()) + " is due.";
		}

		System.out.println(Thread.currentThread().getName() + " Got: " + n.getName().getFullName() + " " + n.getWeight()
				+ " " + n.getWidth() + " " + n.getLength() + " " + n.getHeight());

		if (queue.isEmpty()) {
			empty = true;
		}

		notifyAll();
		flag = true;
		getReport();
		return n;
	}

	
	/**
	 * add passenger in the queue 
	 * @param n passenger object
	 */
	public synchronized void put(Passenger n) {

		while (queue.size() >= 5) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// generates random numbers for weight and dimension of luggage
		n.setLength(Math.round(Math.random() * 100 + 1));
		n.setHeight(Math.round(Math.random() * 100 + 1));
		n.setWeight(Math.round(Math.random() * 100 + 1));
		n.setWidth(Math.round(Math.random() * 100 + 1));

		queue.addLast(n);
		System.out.println("Put: " + n.getName().getFullName());
		empty = false;

		notifyAll();
		getReport();
		flag = false;

	}

	public synchronized boolean returnFlag() {
		return flag;
	}

	/**
	 * set done to true when there are no more passengers to enter the queue
	 */
	public synchronized void setDone() {
		done = true;
	}

	/**
	 * @return true when there are no more passengers to enter the queue
	 */
	public synchronized boolean getDone() {
		return done;
	}

	/**
	 * sets timer to true when the flight has departed
	 */
	public synchronized void setTimerFinished() {
		timer = true;

	}

	/**
	 * @return true when the flight has departed
	 */
	public synchronized boolean getTimerFinish() {
		return timer;
	}

	/**
	 * @return the size of the queue
	 */
	public synchronized int getQueueSize() {
		return queue.size();
	}

	/**
	 * creates the queue report which will be displayed on the GUI
	 */
	public synchronized void getReport() {
		passenger = "There are currently " + queue.size() + " people waiting in the queue:\n\n";
		passenger += "|Booking Reference |     |Bag Weight in kg|     |Bag Dimension in cm|     |   Full Passenger Name|\n";

		for (Passenger n : queue) {
			passenger += String.format("%15s\t\t%-10s\t   %-15s\t            %s\n", n.getBookingreference(),
					((int) n.getWeight()),
					((int) n.getHeight()) + "x" + ((int) n.getLength()) + "x" + ((int) n.getWidth()),
					n.getName().getFullName());

		}
		notifier(passenger);
	}

	/**
	 * @return the queue report that will be displayed in the GUI
	 */
	public synchronized String getQueueReport() {
		return passenger;
	}

	/**
	 * notifies the observers 
	 * @param x report to be displayed
	 */
	public synchronized void notifier(String x) {
		setChanged();
		notifyObservers(x);
		clearChanged();
	}

	/**
	 * @return the report to be displayed at the check in desks in the gui
	 */
	public synchronized String checkInReport() {
		return reportGet;
	}

	/**
	 * @return returns the passenger object at the head of the queue
	 */
	public synchronized Passenger getNextPassenger() {
		return queue.element();
	}

	/**
	 * removes the passenger at the head of the queue
	 */
	public synchronized void removeFirst() {

		reportRemove += queue.element().getName().getFullName() + "\n";

		queue.remove();
		getReport();

	}

	/**
	 * @return the report of the passengers that have missed their flight
	 */
	public synchronized String removeReport() {
		return reportRemove;
	}
}