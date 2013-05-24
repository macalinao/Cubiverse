package me.thehutch.cubiverse.universe.generator;

import me.thehutch.cubiverse.CubiversePlugin;
import net.royawesome.jlibnoise.NoiseQuality;
import net.royawesome.jlibnoise.module.combiner.Add;
import net.royawesome.jlibnoise.module.combiner.Multiply;
import net.royawesome.jlibnoise.module.modifier.Clamp;
import net.royawesome.jlibnoise.module.modifier.ScalePoint;
import net.royawesome.jlibnoise.module.modifier.Turbulence;
import net.royawesome.jlibnoise.module.source.Perlin;
import org.spout.api.generator.WorldGeneratorUtils;
import org.spout.api.generator.biome.BiomeGenerator;
import org.spout.api.generator.biome.BiomeManager;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.material.BlockMaterial;
import org.spout.api.math.Vector3;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * @author thehutch
 */
public class SolarSystemGenerator extends BiomeGenerator {

	private static final Perlin ELEVATION = new Perlin();
	private static final Perlin ROUGHNESS = new Perlin();
	private static final Perlin DETAIL = new Perlin();
	private static final Turbulence TURBULENCE = new Turbulence();
	private static final ScalePoint SCALE = new ScalePoint();
	private static final Clamp FINAL = new Clamp();

	static {
		ELEVATION.setFrequency(0.012);
		ELEVATION.setLacunarity(1);
		ELEVATION.setNoiseQuality(NoiseQuality.BEST);
		ELEVATION.setPersistence(0.7);
		ELEVATION.setOctaveCount(1);

		ROUGHNESS.setFrequency(0.0318);
		ROUGHNESS.setLacunarity(1);
		ROUGHNESS.setNoiseQuality(NoiseQuality.BEST);
		ROUGHNESS.setPersistence(0.9);
		ROUGHNESS.setOctaveCount(1);

		DETAIL.setFrequency(0.042);
		DETAIL.setLacunarity(1);
		DETAIL.setNoiseQuality(NoiseQuality.BEST);
		DETAIL.setPersistence(0.7);
		DETAIL.setOctaveCount(1);

		final Multiply multiply = new Multiply();
		multiply.SetSourceModule(0, ROUGHNESS);
		multiply.SetSourceModule(1, DETAIL);

		final Add add = new Add();
		add.SetSourceModule(0, multiply);
		add.SetSourceModule(1, ELEVATION);

		TURBULENCE.SetSourceModule(0, add);
		TURBULENCE.setFrequency(0.01);
		TURBULENCE.setPower(8);
		TURBULENCE.setRoughness(1);

		SCALE.SetSourceModule(0, TURBULENCE);
		SCALE.setxScale(0.7);
		SCALE.setyScale(1);
		SCALE.setzScale(0.7);

		FINAL.SetSourceModule(0, SCALE);
		FINAL.setLowerBound(-1);
		FINAL.setUpperBound(1);
	}

	@Override
	protected void generateTerrain(CuboidBlockMaterialBuffer blockData, int x, int y, int z, BiomeManager manager, long seed) {

		CubiversePlugin.getUniverse().getSolarSystems();

		Vector3 chunkPos = new Vector3(x, y, z);
		Vector3 planetPos = new Vector3();
		//if (chunkPos.distance(planetPos) > size) {
		//	return;
		//}

		ELEVATION.setSeed((int) seed * 23);
		ROUGHNESS.setSeed((int) seed * 29);
		DETAIL.setSeed((int) seed * 17);
		TURBULENCE.setSeed((int) seed * 53);

		final Vector3 size = blockData.getSize();
		final int sizeX = size.getFloorX();
		final int sizeY = size.getFloorY();
		final int sizeZ = size.getFloorZ();
		final double[][][] noise = WorldGeneratorUtils.fastNoise(FINAL, sizeX, sizeY, sizeZ, 4, x, y, z);
		for (int xx = 0; xx < sizeX; xx++) {
			for (int yy = 0; yy < sizeX; yy++) {
				for (int zz = 0; zz < sizeX; zz++) {
					final int totalX = x + xx;
					final int totalY = y + yy;
					final int totalZ = z + zz;
					final double distance = Math.sqrt(totalX * totalX + totalY * totalY + totalZ * totalZ);
					if (distance == 0) {
						blockData.set(totalX, totalY, totalZ, BlockMaterial.SOLID_BROWN);
						continue;
					}
					//final double density = this.size / distance * (noise[xx][yy][zz] * 0.5 + 0.5);
					//if (density >= 1) {
					//	blockData.set(totalX, totalY, totalZ, BlockMaterial.SOLID_RED);
					//}
				}
			}
		}
	}

	@Override
	protected void registerBiomes() {
	}

	@Override
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}

	@Override
	public String getName() {
		return "SolarSystemGenerator";
	}

	/*
	 private final SolarSystem solarSystem;

	 public SolarSystemGenerator(SolarSystem solarSystem) {
	 this.solarSystem = solarSystem;
	 }

	 public void generate() {
	 THashMap<Point, Planet> planets = solarSystem.getPlanets();
	 for(Point point : planets.keySet()) {
	 final Planet planet = planets.get(point);
	 THashSet<Chunk> chunks = getChunksAroundLocation(point, planet.getRadius());
	 //new MoltenPlanetGenerator().generate(planet, point, chunks);
	 }
	 }

	 private THashSet<Chunk> getChunksAroundLocation(Point center, int radius) {
	 THashSet<Chunk> chunks = new THashSet<>();
	 for(int dx = -radius ; dx<radius ; dx++) {
	 for(int dy = -radius ; dy<radius ; dy++) {
	 for(int dz = -radius ; dz<radius ; dz++) {
	 Point chunkPos = center.add(dx, dy, dz);
	 chunks.add(chunkPos.getWorld().getChunk(chunkPos.getFloorX(), chunkPos.getFloorY(), chunkPos.getFloorZ(), LoadOption.LOAD_GEN));
	 }
	 }
	 }
	 return chunks;
	 }

	 private CuboidBlockMaterialBuffer getPlanetCuboid(Point center, int radius) {
	 int x, y, z;
	 Vector3 base = new Vector3(center.getX() - (radius / 2), center.getY() - (radius / 2), center.getZ() - (radius / 2));
	 return new CuboidBlockMaterialBuffer(base, new Vector3(radius, radius, radius));
	 }*/
}