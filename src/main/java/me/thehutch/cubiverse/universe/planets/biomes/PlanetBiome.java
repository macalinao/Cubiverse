package me.thehutch.cubiverse.universe.planets.biomes;

import org.spout.api.generator.biome.Biome;
/**
 * @author thehutch
 */
public abstract class PlanetBiome extends Biome {

	private final int biomeId;

	protected PlanetBiome(int biomeId) {
		this.biomeId = biomeId;
	}

	public int getBiomeId() {
		return biomeId;
	}
}