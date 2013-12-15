package eternialogic.amp.performance;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


// Initializes on mod startup.
public class AmpPerformance {
	public AmpPlayerManager playerManager;
	public AmpServerManager serverManager;
	public AmpModManager modManager;
	public static AmpPerformance _instance;
	
	void Peformance(){
		// initialize
		_instance = this;
		playerManager = new AmpPlayerManager();
	}
	
	// initialize server performance changes
	public void init(){
		// initialize out-view thread
		System.out.println("Starting AMP thread...");
		Thread AmpThread = new Thread(new Runnable(){
			public void run(){
				Thread.currentThread().setName("AmpMain");
				Logger l = Logger.getGlobal();
				
				// kill selected server threads and initialize
				//	custom performance system.
				System.out.println("Started AMP thread");
				while(true){
					Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
					Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
					for(Thread t : threadArray){
						l.log(Level.INFO, "'"+t.getName()+"'");
						if(t.getName().startsWith("Server thread")){
							
							l.log(Level.INFO, "[ForgeAMP] killing server thread...");
							
							//try{t.interrupt();} catch(Exception e){}
							//Thread.currentThread().setDaemon(true); // Make this thread primary
							l.log(Level.INFO, "[ForgeAMP] killed server thread");
							//startParts();
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
		});
		AmpThread.start();
	}
	
	private void startParts(){
		System.out.println("[ForgeAMP] initializing performance system...");
		serverManager.start();
		
	}
	
	public static AmpPerformance instance(){
		return _instance;
	}
}
