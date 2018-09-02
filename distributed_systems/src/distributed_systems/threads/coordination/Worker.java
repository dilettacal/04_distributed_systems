package distributed_systems.threads.coordination;

import java.time.LocalTime;

public class Worker implements Runnable{

	//private volatile boolean isTerminated = false; //Read from RAM
	private boolean isTerminated = false;

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	@Override
	public void run() {
		//infinite loop if isTerminated set as volatile!
		while(!isTerminated) {
			System.out.println("Worker!");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(LocalTime.now());
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		t1.start();
		
		//Main thread
		Thread.sleep(4000);
		worker.setTerminated(true);
		System.out.println("The end!");
		System.out.println(LocalTime.now());
	}
}
