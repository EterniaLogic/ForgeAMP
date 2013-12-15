package eternialogic.amp.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static boolean useThreadingSystem;
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		useThreadingSystem = config.get("Performance", "UseThreads", 1).getBoolean(true);
		
		
		
		config.save();
		
	}
	
}
