package eternialogic.amp.performance;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

// Manages server-time processes, such as Time, player list, lights, ect.
public class AmpServerManager {
	Thread thread;
	
	public void serverThread(){
		// set name
		Thread.currentThread().setName("AmpServerThread");
		
		// loop with the server
		DedicatedServer server = (DedicatedServer)MinecraftServer.getServer();
		while(server.isServerRunning()){
			FMLCommonHandler.instance().onPreServerTick();
			server.updateTimeLightAndEntities();
			server.executePendingCommands();
			FMLCommonHandler.instance().onPostServerTick();
			
			try {
				Thread.sleep(49); // 20 ticks /sec
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(){
		// make thread and run it.
		thread = new Thread(new Runnable(){public void run(){serverThread();}});
		thread.start();
	}
	
}
