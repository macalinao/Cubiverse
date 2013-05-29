package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.SpaceObject;

/**
 * @author thehutch
 */
public class Planet extends SpaceObject {

	private final double distanceToStar;
	private final Climate climate;

	public Planet(String name, int radius, double distanceToStar, Climate climate) {
		super(name, radius);
		this.distanceToStar = distanceToStar;
		this.climate = climate;
	}

	public double getDistanceToStar() {
		return distanceToStar;
	}

	public Climate getClimate() {
		return climate;
	}
}