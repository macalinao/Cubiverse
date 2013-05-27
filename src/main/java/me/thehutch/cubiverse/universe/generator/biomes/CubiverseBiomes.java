package me.thehutch.cubiverse.universe.generator.biomes;

import gnu.trove.map.hash.THashMap;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author thehutch
 */
public class CubiverseBiomes {

	//Planet Biomes
	public static final CubiverseBiome MOLTEN_PLANET = new MoltenPlanetBiome(0);
	//Space Biomes
	public static final CubiverseBiome SPACE_BIOME = new SpaceBiome(1337);
	public static final CubiverseBiome ASTEROID_BIOME = new AsteroidBiome(1338);
	//Planet Biomes By Name
	private static final THashMap<String, CubiverseBiome> BY_NAME = new THashMap<>();

	static {
		for (Field field : CubiverseBiomes.class.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				final Object obj = field.get(null);
				if (obj instanceof CubiverseBiome) {
					BY_NAME.put(field.getName().toLowerCase(), (CubiverseBiome) obj);
				}
			} catch (IllegalAccessException | IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static CubiverseBiome byName(String name) {
		return BY_NAME.get(name);
	}

	public static Collection<CubiverseBiome> getBiomes() {
		return BY_NAME.values();
	}
}