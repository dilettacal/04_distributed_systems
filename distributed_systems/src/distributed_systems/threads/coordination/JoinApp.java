package distributed_systems.threads.coordination;

class Mythread1 extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 20; ++i) {
			System.out.println("Mythread1: " + i);
			try {
				Thread.sleep(10); //Thread in a waiting state
			} catch (InterruptedException e) {
			}
		}
	}
}


class Mythread2 extends Thread{
	
	@Override
	public void run() {
		for (int i = 0; i < 10; ++i) {
			System.out.println("Mythread2: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}

public class JoinApp {

	public static void main(String[] args) {
		Mythread1 t1 = new Mythread1();
		Mythread2 t2 = new Mythread2();
		
		t1.start();
		t2.start();
		
		try {
			//Wait for threads to die
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}
		
		System.out.println("The end");
	}
}
