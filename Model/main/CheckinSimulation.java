package main;
import controller.CheckinControler;
import gui.Gui;
import objects.Manager;
import queue.WaitingQueue;
import report_logs.CheckedInReport;
import report_logs.QueueReport;
import threads.EnteringQueue;
import threads.Timer;

public class CheckinSimulation {

	public static void main(String[] args) throws Exception {
		
		// create new manager
		Manager p = new Manager("Resources/flights.csv", "Resources/bookings.csv");
		// create waiting queue
		WaitingQueue so = new WaitingQueue();
		// create Queue report
		QueueReport q = new QueueReport(so);
		// create new checked in report
		CheckedInReport r = new CheckedInReport(so);
		// create and start timer for first flight
		Thread timer1 = new Thread(new Timer(15, p.getFlightMap().getFlight("A1320"),so));
		timer1.start();
		// create and start timer for second flight
		Thread timer2 = new Thread(new Timer(17, p.getFlightMap().getFlight("B2430"),so));
		timer2.start();
		// create and start timer for third flight
		Thread timer3 = new Thread(new Timer(20, p.getFlightMap().getFlight("C3340"),so));
		timer3.start();
		// create and start thread that feeds the queue
		Thread eq = new Thread(new EnteringQueue(so, p.getPassengerList()));
		eq.start();		
		// create new gui
		Gui gui = new Gui(so, p,q,r);
		
		// register observers to flights
		p.getFlightMap().getFlight("A1320").addObserver(gui);
		p.getFlightMap().getFlight("B2430").addObserver(gui);
		p.getFlightMap().getFlight("C3340").addObserver(gui);
		
		//create new controller
		CheckinControler controller = new CheckinControler(gui);
		
		}
	}


