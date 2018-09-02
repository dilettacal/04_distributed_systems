package distributed_systems.start_threads;

class MyRunner1 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; ++i) {
			System.out.println("MyRunner1 is executing: " + i);
		}
		
	}
	
}

class MyRunner2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; ++i) {
			System.out.println("MyRunner2 is running: " + i);
		}
	}
	
}

/**
 * Dummy implementation of thread
 * @author dltcls
 *
 */
public class RunnableApp {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunner1());
		Thread t2 = new Thread(new MyRunner2());
		
		t1.start(); 
		t2.start();
		
		
	}

}
