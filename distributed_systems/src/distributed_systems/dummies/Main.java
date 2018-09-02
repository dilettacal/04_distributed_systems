package distributed_systems.dummies;


class RunnerClass1{
	public void startRunning() {
		for (int i = 0; i < 10; ++i) {
			System.out.println("Mythread1 is executing: " + i);
		}
	}
}

class RunnerClass2 {
	public void startRunning() {
		for (int i = 0; i < 10; ++i) {
			System.out.println("Mythread2 is running: " + i);
		}
	}
}

/**
 * Dummy sequential program
 * @author dltcls
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//Instantiation
		RunnerClass1 t1 = new RunnerClass1();
		RunnerClass2 t2 = new RunnerClass2();
		
		t1.startRunning();
		t2.startRunning();

	}

}
