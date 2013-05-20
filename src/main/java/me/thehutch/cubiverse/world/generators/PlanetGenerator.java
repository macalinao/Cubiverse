package me.thehutch.cubiverse.world.generators;

import net.royawesome.jlibnoise.NoiseQuality;
import net.royawesome.jlibnoise.module.source.Perlin;
import net.royawesome.jlibnoise.model.Sphere;

import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;
/**
 * @author thehutch
 */
public abstract class PlanetGenerator implements WorldGenerator {

	private static final Perlin PERLIN = new Perlin();
	private static final Sphere SPHERE = new Sphere(PERLIN);
	
	static {
		PERLIN.setFrequency(0.015);
		PERLIN.setLacunarity(2.0);
		PERLIN.setNoiseQuality(NoiseQuality.BEST);
		PERLIN.setPersistence(0.5);
		PERLIN.setOctaveCount(16);

		
		
		
	}

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {

		

		//final Vector3 chunkPos = new Vector3(chunkX, chunkY, chunkZ);
		

		//final Vector3 size = blockData.getSize();
		//final int sizeX = size.getFloorX();
		//final int sizeY = size.getFloorY();
		//final int sizeZ = size.getFloorZ();
		//final double[][][] noise = WorldGeneratorUtils.fastNoise(SPHERES, sizeX, sizeY, sizeZ, 4, chunkX, chunkY, chunkZ);

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
		return "Planet Generator";
	}
}