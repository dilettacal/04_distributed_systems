/**
 * 
 */
package distributed_systems.threads.synchronization;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @author dltcls
 * @see Manuale di Java 8 - D. S. Cesari
 */

class Clicker implements Runnable{
	private long click = 0L;
	private Thread t;
	private volatile boolean running = true;
	
	public Clicker(int p) {
		t = new Thread(this);
		t.setPriority(p);
	}

	public long getClick() {
		return click;
	}


	public void setClick(long click) {
		this.click = click;
	}

	@Override
	public void run() {
		while(running) {
			click++;
		}
	}
	
	public void stopThread() {
		running = false;
	}
	
	public void startThread() {
		t.start();
	}
	
}

public class ThreadRace {

	public static void main(String[] args) {
		//main thread gets max prio
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Clicker high = new Clicker(Thread.NORM_PRIORITY + 2);
		Clicker low  = new Clicker(Thread.NORM_PRIORITY - 2);
		low.startThread();
		high.startThread();
		try {
			//main thread sleeps --> CPU for other threads
			Thread.sleep(10000);
		}catch (Exception e) {}
		
		low.stopThread();
		high.stopThread();
		System.out.println(low.getClick() + " vs. " + high.getClick());
		
	}

}
