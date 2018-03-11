package core;

public class CheckinScheduler implements Runnable {
	
	private Queue queue;
	private PassengerList p;
	
	public CheckinScheduler(Queue q,PassengerList p){
		
		this.queue = q;
		this.p=p;
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0;i< p.getSize();i++){
			try{
				
				queue.addtoqueue(p.get(i));
				System.out.println("added"+queue.getPassenger().getLastname());
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
		
	}

}

