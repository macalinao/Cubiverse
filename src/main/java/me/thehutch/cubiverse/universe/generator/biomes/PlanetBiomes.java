package me.thehutch.cubiverse.universe.generator.biomes;

import gnu.trove.map.hash.THashMap;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author thehutch
 */
public class PlanetBiomes {

	//Planet Biomes
	public static final PlanetBiome MOLTEN_PLANET = new MoltenPlanetBiome(0);

	//Planet Biomes By Name
	private static final THashMap<String, PlanetBiome> BY_NAME = new THashMap<>();

	static {
		for(Field field : PlanetBiomes.class.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				final Object obj = field.get(null);
				if (obj instanceof PlanetBiome) {
					BY_NAME.put(field.getName().toLowerCase(), (PlanetBiome)obj);
				}
			} catch (IllegalAccessException | IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static PlanetBiome byName(String name) {
		return BY_NAME.get(name);
	}

	public static Collection<PlanetBiome> getBiomes() {
		return BY_NAME.values();
	}
}