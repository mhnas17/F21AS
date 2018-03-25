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
		
		
		Manager p = new Manager("Resources/flights.csv", "Resources/bookings.csv");
		WaitingQueue so = new WaitingQueue();
		QueueReport q = new QueueReport(so);
		CheckedInReport r = new CheckedInReport(so);
		Thread timer1 = new Thread(new Timer(6, p.getFlightMap().getFlight("A1320")));
		timer1.start();
		Thread timer2 = new Thread(new Timer(12, p.getFlightMap().getFlight("B2430")));
		timer2.start();
		Thread timer3 = new Thread(new Timer(18, p.getFlightMap().getFlight("C3340")));
		timer3.start();
		Thread eq = new Thread(new EnteringQueue(so, p.getPassengerList()));
		eq.start();		
		Gui gui = new Gui(so, p,q,r);
		p.getFlightMap().getFlight("A1320").addObserver(gui);
		p.getFlightMap().getFlight("B2430").addObserver(gui);
		p.getFlightMap().getFlight("C3340").addObserver(gui);
		CheckinControler controller = new CheckinControler(gui);
		
		}
	}


