/**
 * 
 */
package distributed_systems.threads.synchronization;

/**
 * @author dltcls
 * @see Manuale di Java 8 - D. S. Cesari
 */

class CallMe{
	//(1)
	synchronized public void call(String msg) {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (Exception e ) {}
		System.out.println("]");
	}
}

class Caller implements Runnable{
	private String msg;
	private CallMe target;
	
	public Caller(CallMe t, String s) {
		target = t;
		msg = s;
		new Thread(this).start();
	}

	@Override
	public void run() {
		//(2)
		//synchronized(target){
		target.call(msg);
		//}
	}
	
}

public class Synch {

	public static void main(String[] args) {
		CallMe target = new CallMe();
		new Caller(target, "Hello");
		new Caller(target,"Synchronized");
		new Caller(target, "World");
	}
	/*
	 * Output w/o synchronization (1) or (2):
	 * [Synchronized[Hello[World]
	   ]
       ]
	 * 
	 * Output w synchronization:
	 *  [Hello]
		[Synchronized]
		[World]

	 */

}
