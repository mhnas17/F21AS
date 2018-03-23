package ReportLogs;
import java.util.Observable;
import java.util.Observer;
import CheckinThread.WaitingQueue;
import core.Manager;
import core.PassengerList;
import ReportLogs.QueueReport;


public class CheckedInReport implements Observer{

	private Manager p;
	private WaitingQueue wait;
	private String report="Passengers that checked in their flights\n"+"=======================================\n";
	//private PassengerList custList = new PassengerList();
	
	public CheckedInReport(WaitingQueue wait) {
		this.wait=wait;
		//this.custList=custList;
		wait.addObserver(this);
	}
	
	public void setCheckedInReport() {
		// String s="";

		if (wait.checkInReport() != null) {
			CharSequence cs = wait.checkInReport() + "got 1\n";
			if (!report.contains(cs)) {
				report += wait.checkInReport() + "got 1\n";

			}
		}
	}

	public String getCheckedinReport() {
		return report;
	}

	@Override
	// The problem so far is that this is noitified whether we put someone in queue
	// or we get. We only want it to work when someone is checke in
	public synchronized void update(Observable o, Object arg) {
		setCheckedInReport();
		//System.out.println(getCheckedinReport());

	}

}
