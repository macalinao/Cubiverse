package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.SpaceObject;

/**
 * @author thehutch
 */
public final class Planet extends SpaceObject {

	//Defaults
	public static final int DEFAULT_ROTATION_TIME = 24000;
	public static final Climate DEFAULT_CLIMATE = Climate.NORMAL;
	//Planet Data
	private final double distanceToStar;
	private final int rotationTime;
	private final Climate climate;

	public Planet(String name, int radius, double distanceToStar) {
		this(name, radius, distanceToStar, DEFAULT_ROTATION_TIME);
	}

	public Planet(String name, int radius, double distanceToStar, int rotationTime) {
		super(name, radius);
		this.distanceToStar = distanceToStar;
		this.rotationTime = rotationTime;
		this.climate = Climate.getClimateFromPlanetDistance(distanceToStar);
	}

	public double getDistanceToStar() {
		return distanceToStar;
	}

	public int getRotationTime() {
		return rotationTime;
	}

	public Climate getClimate() {
		return climate;
	}

	@Override
	public String toString() {
		return String.format("Name: %s | Radius: %s | Climate: %s | Rotation Time: %s", getName(), getRadius(), getClimate().name(), getRotationTime());
	}

	@Override
	public void onTick(float dt) {

	}
}