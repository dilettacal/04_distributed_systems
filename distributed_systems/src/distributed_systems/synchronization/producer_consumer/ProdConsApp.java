package distributed_systems.synchronization.producer_consumer;

import java.util.ArrayList;
import java.util.List;

class Processor{
	
	private List<Integer> list = new ArrayList<Integer>();
	private final int MAX = 5; //how many items
	private final int MIN = 0;
	private final Object lock = new Object();
	private int value = 0;

	public void produce() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == MAX) {
					System.out.println("Waiting for removing items from list...");
					//se non si specifica, blocca la classe
					lock.wait(); //call on synchronized object!
				} else {
					System.out.println("Adding: " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
		
	}

	public void consume() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == MIN) {
					System.out.println("Waiting for adding to the list...");
					lock.wait();
				} else {
					//System.out.println("Removed: " + list.remove(list.size()-1));
					System.out.println("Removed: " + list.remove(--value));
					lock.notify();
				}
				
				Thread.sleep(500);
			}
		}
		
	}
	
}
public class ProdConsApp {

	public static void main(String[] args) {
			Processor processor = new Processor();
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						processor.produce();
					} catch (InterruptedException e) {
					}

				}
			});

			Thread t2 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						processor.consume();
					} catch (InterruptedException e) {
					}

				}
			});
			
			t1.start();
			t2.start();
			
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
			}

		}

	}

