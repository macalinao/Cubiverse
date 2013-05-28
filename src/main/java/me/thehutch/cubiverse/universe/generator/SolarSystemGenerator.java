package me.thehutch.cubiverse.universe.generator;

import me.thehutch.cubiverse.universe.Universe;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import net.royawesome.jlibnoise.NoiseQuality;
import net.royawesome.jlibnoise.module.modifier.ScalePoint;
import net.royawesome.jlibnoise.module.source.Perlin;
import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * @author thehutch
 */
public class SolarSystemGenerator implements WorldGenerator {

	private static final Perlin PERLIN = new Perlin();
	private static final ScalePoint NOISE = new ScalePoint();

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
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world) {
		SolarSystem solarSystem = Universe.getSolarSystem(world.getName());

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
	 */
}