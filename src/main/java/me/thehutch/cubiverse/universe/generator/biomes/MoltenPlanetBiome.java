package me.thehutch.cubiverse.universe.generator.biomes;

import me.thehutch.cubiverse.data.Climate;

/**
 * @author thehutch
 */
public class MoltenPlanetBiome extends CubiverseBiome {

	public MoltenPlanetBiome(int biomeId) {
		super(biomeId, Climate.HOT);
	}

	@Override
	public String getName() {
		return "MoltenPlanetBiome";
	}
}