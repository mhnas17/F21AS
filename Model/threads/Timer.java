package threads;

import objects.Flight;
import objects.Manager;
import queue.WaitingQueue;
import report_logs.CheckedInReport;
import report_logs.QueueReport;

public class Timer implements Runnable{

	private int time;
	private Flight fl;
	private WaitingQueue wait;
	
	/*public Timer(int time,WaitingQueue so ) {
		
		this.time= time;
		this.so = so;
	}*/
	
	public Timer(int time,Flight fl,WaitingQueue wait) {
		
		this.time= time;
		this.fl = fl;
		this.wait = wait;
		
	}
	
	@Override
	public void run() {
		this.runTimer();
		
	}
public void runTimer() {
	
	while (time>0){
        System.out.println("Remaining: "+time+" seconds");
        try {
          time--;
          Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
         }
         catch (InterruptedException e) {
             
         }
       }
	fl.setTimerFinished(wait);
	
	System.out.println("Flight has departed no more check ins");
  }

}
	