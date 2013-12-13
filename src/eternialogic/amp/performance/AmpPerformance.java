package eternialogic.amp.performance;

import java.util.Set;


// Initializes on mod startup.
public class AmpPerformance {
	void Peformance(){
		// initialize
	}
	
	// initialize server performance changes
	public void init(){
		// initialize out-view thread
		System.out.println("Starting AMP thread...");
		new Thread(new Runnable(){
			public void run(){
				System.out.println("Started AMP thread");
				Thread.currentThread().setName("AmpMain");
				Thread.currentThread().setDaemon(true); // Make this thread primary
				// Minecraft main thread
				// kill main minecraft thread!
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
				for(Thread t : threadArray){
					System.out.println("'"+t.getName()+"'");
					if(t.getName().equalsIgnoreCase("Minecraft main thread")){
						t.stop();
						System.out.println("killed main thread");
					}
				}
			}
		}).run();
	}
}
