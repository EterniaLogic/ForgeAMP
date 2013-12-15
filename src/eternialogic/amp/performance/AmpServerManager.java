package eternialogic.amp.performance;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

// Manages server-time processes, such as Time, player list, lights, ect.
public class AmpServerManager {
	
	public void serverTick(){
		while(MinecraftServer.getServer().isServerRunning()){
			DedicatedServer server = (DedicatedServer)MinecraftServer.getServer();
			server.updateTimeLightAndEntities();
			server.executePendingCommands();
			
			try {
				Thread.sleep(49); // 20 ticks /sec
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
