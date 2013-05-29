package me.thehutch.cubiverse.universe.generator;

import java.util.Random;
import me.thehutch.cubiverse.materials.CubiverseMaterials;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.math.Vector3;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * @author thehutch
 */
public class SolarSystemGenerator implements WorldGenerator {

	private static final int CELL_SIZE = 16 * 16;
	private static final int MAX_PLANET_SIZE = CELL_SIZE / 2;
	private static final int MIN_PLANET_SIZE = CELL_SIZE / 8;

	@Override
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		Vector3 chunkPos = new Vector3(chunkX, chunkY, chunkZ).multiply(16);
		Vector3 cellPos = chunkPos.divide(CELL_SIZE);
		Vector3 cellFlooredPos = cellPos.floor();
		Vector3 cellLocalVector = cellPos.subtract(cellFlooredPos);
		Vector3 cellCenterWorldSpace = cellLocalVector.add(0.5f, 0.5f, 0.5f).multiply(CELL_SIZE);

		long hash = ((long) cellCenterWorldSpace.getX()) * (7919 ^ (long) cellCenterWorldSpace.getY() * 7901) ^ ((long) cellCenterWorldSpace.getZ() * 7907);
		hash *= hash + 101;

		Random rand = new Random(hash);
		double rDouble = rand.nextDouble();
		if (rDouble > 0.5) {
			int xAxis = rand.nextInt(CELL_SIZE) - MAX_PLANET_SIZE;
			int yAxis = rand.nextInt(CELL_SIZE) - MAX_PLANET_SIZE;
			int zAxis = rand.nextInt(CELL_SIZE) - MAX_PLANET_SIZE;
			int radius = rand.nextInt(MAX_PLANET_SIZE - MIN_PLANET_SIZE) + MIN_PLANET_SIZE;

			Vector3 planetCenter = cellCenterWorldSpace.add(xAxis, yAxis, zAxis);
			Vector3 distanceVec = chunkPos.subtract(planetCenter);

			System.out.println("Distance = " + distanceVec.length() + "  |  Radius = " + radius);
			System.out.println("Location of planet center = " + planetCenter);
			System.out.println("Location of chunk = " + chunkPos.divide(16));

			if (distanceVec.lengthSquared() < radius * radius) {
				System.out.println("Planet chunk generated at " + chunkPos.toString());
				blockData.flood(CubiverseMaterials.MOLTEN_ROCK);
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