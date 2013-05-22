package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.universe.CelestialObject;
import me.thehutch.cubiverse.universe.solarsystem.planets.biomes.PlanetBiome;
/**
 * @author thehutch
 */
public class Planet extends CelestialObject {

	private final PlanetBiome[] biomes;

	public Planet(String name, double mass, double radius, PlanetBiome[] biomes) {
		super(name, mass, radius);
		this.biomes = biomes;
	}

	public PlanetBiome[] getBiomes() {
		return biomes;
	}
}