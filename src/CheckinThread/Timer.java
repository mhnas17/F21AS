package CheckinThread;

public class Timer implements Runnable{

	private int time;
	private WaitingQueue so;
	
	public Timer(int time,WaitingQueue so ) {
		
		this.time= time;
		this.so = so;
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
	so.setTimerFinished();
	System.out.println("Flights have departed no more check ins");
  }

}
	
	
	

