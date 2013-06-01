package me.thehutch.cubiverse.universe.generator;

import java.util.Random;
import me.thehutch.cubiverse.materials.CubiverseMaterials;
import net.royawesome.jlibnoise.NoiseQuality;
import net.royawesome.jlibnoise.module.source.Perlin;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.generator.WorldGeneratorUtils;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.math.GenericMath;
import org.spout.api.math.Vector3;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * @author thehutch
 */
public class SolarSystemGenerator implements WorldGenerator {

	//Cell size (8 chunks)
	private static final int CELL_SIZE = 16 * 16;
	//Maximum planet radius
	private static final int MAX_PLANET_RADIUS = CELL_SIZE / 2;
	//Minimum planet radius
	private static final int MIN_PLANET_RADIUS = CELL_SIZE / 4;
	//Percent chance of planet being generated
	private static final float PLANET_ODD = 0.325f;
	//Noise
	private static final Perlin PERLIN = new Perlin();

	static {
		PERLIN.setFrequency(0.2f);
		PERLIN.setLacunarity(2);
		PERLIN.setOctaveCount(8);
		PERLIN.setPersistence(0.2);
		PERLIN.setNoiseQuality(NoiseQuality.BEST);
	}

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		// Random number generator
		final Random random = new Random();
		// World seed
		final long seed = world.getSeed();
		PERLIN.setSeed((int) seed);

		// Base (origin) of the buffer in world space
		final int originX = chunkX << Chunk.BLOCKS.BITS;
		final int originY = chunkY << Chunk.BLOCKS.BITS;
		final int originZ = chunkZ << Chunk.BLOCKS.BITS;
		// Size of the buffer in world space
		final Vector3 size = blockData.getSize();
		final int sizeX = size.getFloorX();
		final int sizeY = size.getFloorY();
		final int sizeZ = size.getFloorZ();
		//Generate noise
		double[][][] noise = WorldGeneratorUtils.fastNoise(PERLIN, sizeX, sizeY, sizeZ, 4, chunkX, chunkY, chunkZ);

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

					if (distance < radius) {
						blockData.set(x, y, z, CubiverseMaterials.STAR);
					} else if (distance <= (radius * radius)) {
						final double noiseValue = noise[dx][dy][dz] + 0.375;
						if (noiseValue >= 0) {
							blockData.set(x, y, z, CubiverseMaterials.MOLTEN_ROCK);
						}
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




/*

* /*SolarSystem solarSystem = Universe.getSolarSystem(world.getName());
		Vector3 chunkPos = new Vector3(chunkX, chunkY, chunkZ);
		Vector3 planetPos = getPlanetChunkLocation(chunkPos, solarSystem);
		if (planetPos != null) {
			Planet planet = solarSystem.getPlanet(planetPos);
			blockData.flood(CubiverseMaterials.MOLTEN_ROCK);
			//System.out.println("Planet " + planet.getName() + " chunk found at: " + chunkPos.toString());
		}

	private Vector3 getPlanetChunkLocation(Vector3 loc, SolarSystem solarSystem) {
		Vector3 planetCore;
		for (Map.Entry<Vector3, Planet> entry : solarSystem.getPlanets().entrySet()) {
			planetCore = entry.getKey();
			System.out.println("Distance is " + planetCore.distance(loc));
			if (planetCore.distance(loc) <= entry.getValue().getRadius()) {
				System.out.println("Found a planet chunk at " + loc.toString());
				return planetCore;
			}
		}
		return null;
	}

	/*
	 private static BiomeSelectorLayer buildSelectorStack(double scale) {
	 final PerlinRangeLayer space = new PerlinRangeLayer(1).setOctaveCount(1).setFrequency(0.05 / scale);

	 final BiomeSelectorLayer moltenPlanet = space.clone()
	 .addElement(CubiverseBiomes.SPACE_BIOME, -1, -0.5f)
	 .addElement(CubiverseBiomes.MOLTEN_PLANET, -0.5f, 0.25f)
	 .addElement(CubiverseBiomes.ASTEROID_BIOME, 0.25f, 1f);

	 final BiomeSelectorLayer layers = new PerlinRangeLayer(3).
	 setOctaveCount(1).
	 setFrequency(0.05 / scale).
	 addElement(moltenPlanet, -1, 1);

	 return layers;
	 }
	 /*
	 private static final Perlin PERLIN = new Perlin();
	 private static final ScalePoint NOISE = new ScalePoint();
	 //Temp planet stats
	 private static final int ELEVATION = 48;
	 private static final int LOWER_SIZE = 42;
	 private static final int UPPER_SIZE = 58;
	 private static final int PLANET_RADIUS = 96;

	 static {
	 PERLIN.setFrequency(0.075f);
	 PERLIN.setLacunarity(1.5f);
	 PERLIN.setNoiseQuality(NoiseQuality.BEST);
	 PERLIN.setPersistence(0.325f);
	 PERLIN.setOctaveCount(28);

	 NOISE.SetSourceModule(0, PERLIN);
	 NOISE.setxScale(1);
	 NOISE.setyScale(1);
	 NOISE.setzScale(1);
	 }

	 @Override
	 protected void generateTerrain(CuboidBlockMaterialBuffer blockData, int x, int y, int z, BiomeManager manager, long seed) {

	 PERLIN.setSeed((int) seed * 42);

	 final Vector3 size = blockData.getSize();
	 final int sizeX = size.getFloorX();
	 final int sizeY = size.getFloorY();
	 final int sizeZ = size.getFloorZ();
	 final double[][][] noise = WorldGeneratorUtils.fastNoise(NOISE, sizeX, sizeY, sizeZ, 4, x, y, z);
	 for (int xx = 0; xx < sizeX; xx++) {
	 final int totalX = x + xx;
	 for (int zz = 0; zz < sizeZ; zz++) {
	 final int totalZ = z + zz;
	 final double distance = Math.sqrt(totalX * totalX + totalZ * totalZ);
	 for (int yy = 0; yy < sizeY; yy++) {
	 double density = noise[xx][yy][zz] * 0.5 + 0.5;
	 int totalY = y + yy;
	 if (totalY < ELEVATION) {
	 density += 1.0 / LOWER_SIZE * (totalY - ELEVATION);
	 } else {
	 density -= 1.0 / UPPER_SIZE * (totalY - ELEVATION);
	 }
	 density *= PLANET_RADIUS / distance;
	 if (density >= 1) {
	 blockData.set(totalX, totalY, totalZ, BlockMaterial.SOLID_RED);
	 } else {
	 blockData.set(totalX, totalY, totalZ, BlockMaterial.AIR);
	 }
	 }
	 }
	 }
	 }

	 @Override
	 protected void registerBiomes() {
	 setSelector(new LayeredBiomeSelector(buildSelectorStack(1), CubiverseBiomes.SPACE_BIOME) {
	 @Override
	 public Biome pickBiome(int x, int y, int z, long seed) {
	 int hash = x * 13371337 ^ y * 13377331 ^ (int) seed;
	 hash *= 1337;
	 int xNoise = hash >> 20 & 3;
	 int zNoise = hash >> 21 & 3;
	 if (xNoise == 3) {
	 xNoise = 1;
	 }
	 if (zNoise == 3) {
	 zNoise = 1;
	 }
	 return super.pickBiome(x + xNoise - 1, y, z + zNoise - 1, seed);
	 }
	 });
	 register(CubiverseBiomes.MOLTEN_PLANET);
	 register(CubiverseBiomes.ASTEROID_BIOME);
	 register(CubiverseBiomes.SPACE_BIOME);
	 }

	 @Override
	 public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
	 return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	 }

	 @Override
	 public String getName() {
	 return "SolarSystemGenerator";
	 }
	 */