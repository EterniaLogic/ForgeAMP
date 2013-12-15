package eternialogic.amp.performance;

import java.util.HashMap;
import java.util.Queue;

import eternialogic.amp.performance.data.AmpChunks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

// This manager helps centralize chunk systems around the player.
// EntityPlayer is monitored
public class AmpPlayerManager {
	private Thread thread;
	private Queue messages = new java.util.concurrent.ConcurrentLinkedQueue<String>();
	
	void AmpPlayerManager(){}
	
	public void loadPlayer(final EntityPlayer player){
		Thread t = new Thread(new Runnable(){
			public void run(){
				playerThread(player); // run a thread for each player.
			}
		});
	}
	
	// unload a player upon logout...
	public void unloadPlayer(final EntityPlayer player){
		
	}
	
	private void playerThread(EntityPlayer player){
		// set name
		Thread.currentThread().setName("AmpPlayerThread-"+player.getDisplayName());
		
		AmpChunks chunks = new AmpChunks();
		
		// loop until player has left
		DedicatedServer server = (DedicatedServer)MinecraftServer.getServer();
		while(server.getConfigurationManager().playerEntityList.contains(player)){
			
			// Populate chunk map for this player... (All within circle)
			// radius of circle
			int chunkRange = MinecraftServer.getServerConfigurationManager(server).getViewDistance();
			
			// loop through the number of total circles:
			for(int rad = chunkRange; rad<0; rad--){
				// get x and y... theta is figured out from range size
				for(int t = 0; t<rad; t++){
					int x = rad*(int)Math.cos(360/rad * t);
					int y = rad*(int)Math.sin(360/rad * t);
					chunks.loadChunk((WorldServer)player.worldObj, x, y); // load this chunk
				}
			}
			
			// Loop through chunks that the player "owns".
			
			try {
				Thread.sleep(49); // ~20 tps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
