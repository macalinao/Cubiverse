package me.thehutch.cubiverse.universe.generator;

import java.util.Random;
import me.thehutch.cubiverse.materials.CubiverseMaterials;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.math.GenericMath;
import org.spout.api.math.Vector3;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * @author thehutch
 */
public class SolarSystemGenerator implements WorldGenerator {

	//Cell size (24 chunks)
	private static final int CELL_SIZE = 16 * 16;
	//Maximum star radius
	//private static final int MAX_STAR_RADIUS = CELL_SIZE / 2;
	//Minimum star radius
	//private static final int MIN_STAR_RADIUS = CELL_SIZE / 4;
	//Maximum planet radius
	private static final int MAX_PLANET_RADIUS = CELL_SIZE / 2;
	//Minimum planet radius
	private static final int MIN_PLANET_RADIUS = CELL_SIZE / 4;
	//Percent chance of planet being generated
	private static final float PLANET_ODD = 0.5f;

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		// Base (origin) of the buffer in world space
		final int originX = chunkX << Chunk.BLOCKS.BITS;
		final int originY = chunkY << Chunk.BLOCKS.BITS;
		final int originZ = chunkZ << Chunk.BLOCKS.BITS;
		//Check if chunk distance is greater than the maximum SolarSystem size
		if (originX * originX + originY * originY + originZ * originZ > SolarSystem.MAX_SYSTEM_SIZE * SolarSystem.MAX_SYSTEM_SIZE * 16) {
			return;
		}
		// Random number generator
		final Random random = new Random();
		// World seed
		final long seed = world.getSeed();
		// Size of the buffer in world space
		final Vector3 size = blockData.getSize();
		final int sizeX = size.getFloorX();
		final int sizeY = size.getFloorY();
		final int sizeZ = size.getFloorZ();
		// Iterate over all of the blocks in the buffer
		for (int dx = 0; dx < sizeX; dx++) {
			final int x = originX + dx;
			final float cellX = x / (float) CELL_SIZE;
			final int cellOriginX = GenericMath.floor(cellX);
			final float cellCenterX = (cellOriginX + 0.5f) * CELL_SIZE;
			for (int dy = 0; dy < sizeY; dy++) {
				final int y = originY + dy;
				final float cellY = y / (float) CELL_SIZE;
				final int cellOriginY = GenericMath.floor(cellY);
				final float cellCenterY = (cellOriginY + 0.5f) * CELL_SIZE;
				for (int dz = 0; dz < sizeZ; dz++) {
					final int z = originZ + dz;
					final float cellZ = z / (float) CELL_SIZE;
					final int cellOriginZ = GenericMath.floor(cellZ);
					final float cellCenterZ = (cellOriginZ + 0.5f) * CELL_SIZE;
					// Seed the random from the hash of the world coordinates of the center of the cell and world seed
					final long hash = (long) cellCenterX * 7919 ^ (long) cellCenterY * 7901 ^ (long) cellCenterZ * 7907 ^ seed * 7883;
					random.setSeed(hash * (hash + 101));
					//Generate planet
					if (random.nextFloat() > PLANET_ODD) {
						continue;
					}
					// Radius of the planet
					final int radius = random.nextInt(MAX_PLANET_RADIUS - MIN_PLANET_RADIUS + 1) + MIN_PLANET_RADIUS;
					// Max offset the planet center can have before overlapping on adjacent cells
					final int maxOffset = CELL_SIZE / 2 - radius;
					// Offsets from the cell center for the planet center
					final int offsetX = random.nextInt(maxOffset * 2 + 1) - maxOffset;
					final int offsetY = random.nextInt(maxOffset * 2 + 1) - maxOffset;
					final int offsetZ = random.nextInt(maxOffset * 2 + 1) - maxOffset;
					// Planet center coordinates in world space
					final float planetX = cellCenterX + offsetX;
					final float planetY = cellCenterY + offsetY;
					final float planetZ = cellCenterZ + offsetZ;
					// Distance between the current block and the planet center
					final float planetBlockDX = planetX - x;
					final float planetBlockDY = planetY - y;
					final float planetBlockDZ = planetZ - z;
					// Verify that the current block coordinates are inside the planet
					double distance = planetBlockDX * planetBlockDX + planetBlockDY * planetBlockDY + planetBlockDZ * planetBlockDZ;
					if (distance <= radius * radius) {
						blockData.set(x, y, z, CubiverseMaterials.MOLTEN_ROCK);
					}
				}
			}
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
		return "SolarSystemGenerator";
	}
}