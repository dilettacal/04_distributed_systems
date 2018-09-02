package distributed_systems.threads.synchronization;

public class SynchBlocksApp {
	
	private static int counter1 = 0;
	private static int counter2 = 0;
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	
	/*
	 * These methods use the java intrisic class lock 
	 * --> Synchronized blocks!
	 * Solution:
	 * 1. Synchronized block on the class synchronized(ClassName.class)
	 * --> Same situation! Intrinsic lock is used. This should not be used in 2 different locations
	 * 2. Synchronize on different object locks (Object)
	 * 
	public synchronized static void add() { counter1++; }
	public synchronized static void add2() { counter2++; }
	
	*/
	
	public static void add() {
		//synchronized(SynchBlocksApp.class) {
		synchronized(lock1) {
			counter1++;
		}
		
	}
	
	public static void add2() {
		synchronized(lock2) {
			counter2++;
		}
		
	}
	public static void compute() {
		for (int i = 0; i < 100; i++) {
			add();
			add2();
		}
	}

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
			}		
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				compute();
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Counter 1: " + counter1); //200
		System.out.println("Counter 2: " + counter2); //200
	}

}
