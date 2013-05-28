package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.SpaceObject;

/**
 * @author thehutch
 */
public class Planet extends SpaceObject {

	private final int distanceToStar;
	private final Climate climate;

	public Planet(String name, int radius, int distanceToStar, Climate climate) {
		super(name, radius);
		this.distanceToStar = distanceToStar;
		this.climate = climate;
	}

	public int getDistanceToStar() {
		return distanceToStar;
	}

	public Climate getClimate() {
		return climate;
	}
}