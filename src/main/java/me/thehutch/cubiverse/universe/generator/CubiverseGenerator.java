package me.thehutch.cubiverse.universe.generator;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.generator.biome.BiomeGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.material.BlockMaterial;
import org.spout.api.math.Vector3;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;
/**
 * @author thehutch
 */
public abstract class CubiverseGenerator extends BiomeGenerator implements WorldGenerator {

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		Vector3 chunkPos = new Vector3(chunkX, chunkY, chunkZ);
		if (chunkPos.lengthSquared() > SolarSystem.SYSTEM_RADIUS * SolarSystem.SYSTEM_RADIUS) {
			blockData.flood(BlockMaterial.UNBREAKABLE);
		} else {
			super.generate(blockData, chunkX, chunkY, chunkZ, world);
		}
	}

	@Override
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}
}