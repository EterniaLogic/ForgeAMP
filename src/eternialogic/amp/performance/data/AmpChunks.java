package eternialogic.amp.performance.data;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

// Modified Chunk holder, connected to a world and only loads
// chunks that are around a player or chunkloader.
public class AmpChunks {
	private java.util.Hashtable<Long, Chunk> chunkData;
	
	// use object to store base type
	void AmpChunks(Object o){
		chunkData = new java.util.Hashtable<Long, Chunk>();
	}
	
	// update the chunk
	public void updateChunk(Long location){
		Set<TileEntity> TileEntities = (Set<TileEntity>) chunkData.get(location).chunkTileEntityMap.values();
		// loop through this chunk's tile entities and update if it can
		for(TileEntity t : TileEntities){
			if(t.canUpdate()){
				// update the entity
				t.updateEntity();
				t.updateContainingBlockInfo();
			}
		}
		
		// update normal entities
		
		chunkData.get(location).updateSkylight();
	}
	
	// load a chunk for this object
	public void loadChunk(Chunk c){
		//check to make sure the chunk is in the map
		if(!contains(c)){
			chunkData.put((long) (c.xPosition+c.xPosition^2), c);
		}
	}
	
	// unload this chunk
	public void unloadChunk(Chunk c){
		// remote c from the list if it exists.
		if(contains(c)){
			chunkData.remove(c);
		}
	}
	
	public boolean contains(Chunk c) {
		return chunkData.containsKey(c.xPosition+c.xPosition^2);
	}
}
