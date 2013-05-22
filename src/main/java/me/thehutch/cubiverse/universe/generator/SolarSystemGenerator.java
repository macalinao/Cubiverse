package me.thehutch.cubiverse.universe.generator;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;
import org.spout.api.material.BlockMaterial;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;
/**
 * @author thehutch
 */
public class SolarSystemGenerator implements WorldGenerator {

	private final SolarSystem solarSystem;

	public SolarSystemGenerator(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		Point center = world.getChunk(0, 0, 0).getBase();
		Point chunkLocation = world.getChunk(chunkX, chunkY, chunkZ).getBase();

		if (center.distance(chunkLocation) < solarSystem.getSize()) {
			blockData.flood(BlockMaterial.AIR);
		}
	}

	@Override
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}

	@Override
	public Populator[] getPopulators() {
		return new Populator[0];
	}

	@Override
	public String getName() {
		return "Solar_System_Generator";
	}
}