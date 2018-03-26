package threads;

import objects.Flight;
import queue.WaitingQueue;

public class Timer implements Runnable {

	private int time;
	private Flight fl;
	private WaitingQueue wait;

	/**
	 * @param time time left before the flight departs
	 * @param fl flight 
	 * @param wait waiting queue
	 */
	public Timer(int time, Flight fl, WaitingQueue wait) {

		this.time = time;
		this.fl = fl;
		this.wait = wait;
	}

	// Timer
	@Override
	public void run() {
		this.runTimer();
	}

	public void runTimer() {
		while (time > 0) {
			System.out.println("Remaining: " + time + " seconds");
			try {
				time--;
				Thread.sleep(1000L); // 1000L = 1000ms = 1 second
			} catch (InterruptedException e) {
			}
		}
		fl.setTimerFinished(wait);
		System.out.println("Flight has departed no more check ins");
	}
}
	