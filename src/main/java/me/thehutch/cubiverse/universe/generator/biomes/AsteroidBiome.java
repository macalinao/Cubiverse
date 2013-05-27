package me.thehutch.cubiverse.universe.generator.biomes;

import me.thehutch.cubiverse.data.Climate;

/**
 * @author thehutch
 */
public class AsteroidBiome extends CubiverseBiome {

	public AsteroidBiome(int biomeId) {
		super(biomeId, Climate.COLD);
	}

	@Override
	public String getName() {
		return "";
	}
}