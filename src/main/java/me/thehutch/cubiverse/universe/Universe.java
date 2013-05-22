package me.thehutch.cubiverse.universe;

import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import java.util.logging.Level;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
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
		System.out.println("Generating planets for Solar System...");
		for(final Point position : system.getPlanetLocations()) {
			System.out.format("Generating Planet At %s", position);
			final THashSet<Chunk> planetChunks = getChunksAroundLocation(position, system.getSize());
			for(final Chunk chunk : planetChunks) {
				System.out.format("Generating Chunk %s", chunk.getBase());
				CuboidBlockMaterialBuffer buffer = chunk.getCuboid(false);
				buffer.forEach(new CuboidBlockMaterialProcedure() {
					@Override
					public boolean execute(int x, int y, int z, short id, short data) {
						Block block = chunk.getBlock(x, y, z);
						if (position.distanceSquared(block.getPosition()) < system.getPlanet(position).getRadius()) {
							System.out.format("Block at %s set to SOLID", block.getPosition());
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
						chunks.add(chunkPos.getChunk(LoadOption.LOAD_GEN));
					}					
				}
			}
		}
		return chunks;
	}
}