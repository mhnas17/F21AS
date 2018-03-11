package core;

public class CheckinProcessor implements Runnable{

	private Queue queue;
	private BookingMap book;
	
	public CheckinProcessor(Queue q){
		
	this.queue=q;
	
	}
	
	@Override
	public void run() {
		try{
			Thread.sleep(1000);
			while(true){
				//queue.checkin(queue.getPassenger(), book);
				queue.remove();
				System.out.println("checked in "+queue.getPassenger().getLastname());
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}		
	}

}
