package me.thehutch.cubiverse.universe.generator.biomes;

import me.thehutch.cubiverse.data.Climate;
import org.spout.api.generator.biome.Biome;
/**
 * @author thehutch
 */
public abstract class CubiverseBiome extends Biome {

	private final int biomeId;
	private final Climate climate;

	protected CubiverseBiome(int biomeId, Climate climate) {
		this.biomeId = biomeId;
		this.climate = climate;
	}

	public int getBiomeId() {
		return biomeId;
	}

	public Climate getClimate() {
		return climate;
	}
}