package listener;

import eternialogic.amp.AdvancedMinecraftPerformance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

// listens for Events
public class AmpListener {

	// listen to the people who are joining the server.
	@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event){
		EntityPlayer player = (EntityPlayer)event.entity;
		AdvancedMinecraftPerformance.performance.playerManager.loadPlayer(player);
		// add this player to the chunks list.
		
	}
}
