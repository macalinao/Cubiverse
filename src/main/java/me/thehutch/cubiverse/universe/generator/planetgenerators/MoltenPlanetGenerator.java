package me.thehutch.cubiverse.universe.generator.planetgenerators;
/**
 * @author thehutch
 */
public class MoltenPlanetGenerator {
	
	/*
	private static final Perlin PERLIN = new Perlin();
	private static final ScalePoint NOISE = new ScalePoint();

	static {
		PERLIN.setFrequency(0.015f);
		PERLIN.setNoiseQuality(NoiseQuality.BEST);

		NOISE.SetSourceModule(0, PERLIN);
	}

	public MoltenPlanetGenerator() {
	}

	@Override
	public void generate(final Planet planet, final Point center, final THashSet<Chunk> chunks) {
		CuboidBlockMaterialBuffer cubiodBuffer;
		System.out.println("Planet Volume = " + chunks.size());
		for(final Chunk chunk : chunks) {
			cubiodBuffer = chunk.getCuboid(false);

			

			cubiodBuffer.forEach(new CuboidBlockMaterialProcedure() {
				@Override
				public boolean execute(int x, int y, int z, short id, short data) {
					Block b = chunk.getBlock(x, y, z);
					Point blockPos = b.getPosition();
					if (blockPos.distance(center) < planet.getRadius() * 16) {

						final double r = Math.sqrt(x*x + y*y + z*z);
						final double theta = TrigMath.acos(z / r);
						final double phi = TrigMath.atan(y / x);

						final double noise = NOISE.GetValue(theta, phi, 0.0);
						

						b.setMaterial(BlockMaterial.SOLID_RED);
						return true;
					}
					return false;
				}
			});
		}
	}*/
}