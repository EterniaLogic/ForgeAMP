package eternialogic.amp.performance.data;

import java.util.*;
import net.minecraft.world.chunk.Chunk;

// Modified Chunk holder, connected to a world and only loads
// chunks that are around a player or chunkloader.
public class AmpChunks {
	java.util.Hashtable<Long, Chunk> chunkData;
	
	// use object to store base type
	void AmpChunks(Object o){
		chunkData = new java.util.Hashtable<Long, Chunk>();
	}
	
	// load a chunk for this object
	void loadChunk(Chunk c){
		
	}
	
	// unload this chunk
	void unloadChunk(Chunk c){
		
	}
}
