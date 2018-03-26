package report_logs;

import java.util.Observable;
import java.util.Observer;

import data_structure_lists.PassengerList;
import objects.Manager;
import queue.WaitingQueue;
import report_logs.QueueReport;

public class CheckedInReport implements Observer {

	private Manager p;
	private WaitingQueue wait;
	private String report = "Passengers that checked in their flights\n" + "=======================================\n";
	// private PassengerList custList = new PassengerList();

	/**
	 * Constuctor of the report
	 * 
	 * @param wait
	 */
	public CheckedInReport(WaitingQueue wait) {
		this.wait = wait;
		// this.custList=custList;
		wait.addObserver(this);
	}

	/**
	 * Sets the report of the people that checke into flights
	 * 
	 */
	public void setCheckedInReport() {
		// String s="";

		if (wait.checkInReport() != null) {
			CharSequence cs = wait.checkInReport() + "\n";
			if (!report.contains(cs)) {
				report += wait.checkInReport() + "\n";

			}
		}
	}

	/**
	 * Returns the report of the checked in passengers
	 * 
	 * @return String report
	 */
	public String getCheckedinReport() {
		return report;
	}

	/*
	 * Update method
	 */
	@Override

	public synchronized void update(Observable o, Object arg) {
		setCheckedInReport();
		// System.out.println(getCheckedinReport());

	}

}
