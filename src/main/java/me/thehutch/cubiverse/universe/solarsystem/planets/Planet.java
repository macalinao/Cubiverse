package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Atmosphere;
import me.thehutch.cubiverse.universe.SpaceObject;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public final class Planet extends SpaceObject {

	//Defaults
	public static final int DEFAULT_ROTATION_TIME = 24000;
	//Planet Data
	private final double distanceToStar;
	private final int rotationTime;
	private final Atmosphere atmosphere;

	public Planet(String name, Vector3 location, int radius, double distanceToStar, int rotationTime, Atmosphere atmosphere) {
		super(name, location, radius);
		this.distanceToStar = distanceToStar;
		this.rotationTime = rotationTime;
		this.atmosphere = atmosphere;
	}

	public double getDistanceToStar() {
		return distanceToStar;
	}

	public int getRotationTime() {
		return rotationTime;
	}

	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	@Override
	public void onTick(float dt) {
		//TODO
	}

	@Override
	public String toString() {
		return String.format("Name: %s | Radius: %s | Climate: %s | Rotation Time: %s", getName(), getRadius(), getAtmosphere(), getRotationTime());
	}
}