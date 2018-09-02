package distributed_systems.synchronization;

class Processor {

	public void produce() throws InterruptedException {
		// Intrinsic lock
		synchronized (this) {
			System.out.println("produce()");
			// Within synchronized, affects the locked object (this)
			wait();
			System.out.println("produce() 2nd call");
		}

	}

	public void consume() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("consume()");
			// notifies the waiting thread that it can wake up
			notify();
		}

	}
}

public class WaitNotifyApp {

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
