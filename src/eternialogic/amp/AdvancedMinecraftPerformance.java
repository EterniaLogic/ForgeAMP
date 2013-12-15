package eternialogic.amp;

import listener.AmpPlayerListener;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eternialogic.*;
import eternialogic.amp.config.*;
import eternialogic.amp.network.PacketHandler;
import eternialogic.amp.performance.AmpPerformance;
import eternialogic.amp.proxies.CommonProxy;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class AdvancedMinecraftPerformance {


	@Instance(ModInformation.ID)
	public static AdvancedMinecraftPerformance instance;

	@SidedProxy(clientSide = "eternialogic.amp.proxies.ClientProxy", serverSide = "eternialogic.amp.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static AmpPerformance performance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		proxy.initSounds();
		proxy.initRenderers();
		
		// go in and change server-side systems.
		performance = new AmpPerformance();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new AmpPlayerListener());
	}
	
	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
		performance.init();
	}
}
