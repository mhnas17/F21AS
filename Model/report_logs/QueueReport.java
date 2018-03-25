package report_logs;

import java.util.Observable;
import java.util.Observer;

import data_structure_lists.PassengerList;
import objects.Manager;
import queue.WaitingQueue;

import java.util.Observable;
import java.util.Observer;

public class QueueReport implements Observer{
	private Manager p;
	private WaitingQueue wait;
	
	private String report="";
	//private PassengerList custList = new PassengerList();
	
	public QueueReport(WaitingQueue wait) {
		this.wait = wait;
		// this.custList=custList;
		wait.addObserver(this);
	}
	// This is a String report containing the people left in the queue after the closure of the desks
	public void setQueueReport(String x) {
		report=x;
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
