package ReportLogs;

import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;
import CheckinThread.WaitingQueue;
import core.Manager;
import core.PassengerList;

public class QueueReport implements Observer{
	private Manager p;
	private WaitingQueue wait;
	private String r = "Passengers that Showed up but didn't check in\n" + "=======================================\n";
	private String report="";
	//private PassengerList custList = new PassengerList();
	
	public QueueReport(WaitingQueue wait) {
		this.wait = wait;
		// this.custList=custList;
		wait.addObserver(this);
	}
	// This is a String report containing the people left in the queue after the closure of the desks
	public void setQueueReport(String x) {
		report=r+ x+"dong";
	}
	public String getQueueReport(){
		return report;
		}
	
	@Override
	public synchronized void  update(Observable o, Object arg) {
		setQueueReport(arg.toString()); 
		//System.out.println(getQueueReport());
		
	}

}
