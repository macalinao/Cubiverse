package me.thehutch.cubiverse.universe;

import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import org.spout.api.Spout;
import org.spout.api.generator.EmptyWorldGenerator;
import org.spout.api.geo.LoadOption;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;
import org.spout.api.material.BlockMaterial;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;
import org.spout.api.util.cuboid.procedure.CuboidBlockMaterialProcedure;

/**
 * @author thehutch
 */
public class Universe {

	//Universal Gravitational Constant
	public static final float GRAV_CONST = 0.05f;
	//Worlds
	private final THashMap<String, SolarSystem> solarSystems;

	public Universe() {
		this.solarSystems = new THashMap<>();
	}

	public SolarSystem createNewSolarSystem(String name) {
		World newWorld = Spout.getEngine().loadWorld(name, new EmptyWorldGenerator());
		SolarSystem system = new SolarSystem(newWorld);
		generateSolarSystem(system);
		this.solarSystems.put(name, system);
		return system;
	}

	public SolarSystem getSolarSystem(String name) {
		return solarSystems.get(name);
	}

	private void generateSolarSystem(final SolarSystem system) {
		THashMap<Point, Planet> planets = system.getPlanets();
		for (final Point pos : planets.keySet()) {
			System.out.println(String.format("Generating Planet At %s", pos.toChunkString()));
			final THashSet<Chunk> planetChunks = getChunksAroundLocation(pos, planets.get(pos).getRadius());
			final int planetRadius = (int) Math.pow(system.getPlanet(pos).getRadius() * 16, 2);
			for (final Chunk chunk : planetChunks) {
				System.out.println(String.format("Generating Chunk %s", chunk.getBase().toChunkString()));
				CuboidBlockMaterialBuffer buffer = chunk.getCuboid(false);
				buffer.forEach(new CuboidBlockMaterialProcedure() {
					@Override
					public boolean execute(int x, int y, int z, short id, short data) {
						Block block = chunk.getWorld().getBlock(x, y, z);
						if (pos.distanceSquared(block.getPosition()) < planetRadius) {
							//System.out.println(String.format("Setting Block At %s to SOLID_GREEN", block.getPosition().toBlockString()));
							block.setMaterial(BlockMaterial.SOLID_GREEN);
							return true;
						}
						return false;
					}
				});
			}
		}
	}

	private THashSet<Chunk> getChunksAroundLocation(Point center, int radius) {
		THashSet<Chunk> chunks = new THashSet<>();
		radius *= radius;
		for (int dx = -(radius); dx < radius; dx++) {
			for (int dy = -(radius); dy < radius; dy++) {
				for (int dz = -(radius); dz < radius; dz++) {
					Point chunkPos = center.add(dx, dy, dz);
					if (chunkPos.distanceSquared(center) < radius) {
						chunks.add(chunkPos.getWorld().getChunk(chunkPos.getFloorX(), chunkPos.getFloorZ(), chunkPos.getFloorZ(), LoadOption.LOAD_GEN));
					}
				}
			}
		}
		return chunks;
	}
}