package eternialogic.amp.performance;

import java.util.Set;


// Initializes on mod startup.
public class AmpPerformance {
	public AmpPlayerManager playerManager;
	void Peformance(){
		// initialize
		playerManager = new AmpPlayerManager();
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
				
				// kill selected server threads and initialize
				//	custom performance system.
				while(true){
					Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
					Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
					for(Thread t : threadArray){
						System.out.println("'"+t.getName()+"'");
						if(t.getName().equalsIgnoreCase("Server thread")){
							t.stop(); // MUST use
							System.out.println("[ForgeAMP] killed server thread");
							System.out.println("[ForgeAMP] initializing performance system...");
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).run();
	}
}
