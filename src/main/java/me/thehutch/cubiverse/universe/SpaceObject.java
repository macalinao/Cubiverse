package me.thehutch.cubiverse.universe;

import java.io.Serializable;
import org.spout.api.math.Vector3;
import org.spout.api.tickable.BasicTickable;

/**
 * @author thehutch
 */
public abstract class SpaceObject extends BasicTickable implements Serializable {

	//Serializer
	public static final long serialVersionUID = 1L;
	//Defaults
	public static final String DEFAULT_NAME = "DEFAULT";
	public static final int DEFAULT_RADIUS = 64;
	//SpaceComponent Data
	private final Vector3 location;
	private final String name;
	private final int radius;
	//Mass
	private double mass;

	public SpaceObject(String name, Vector3 location, int radius) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Cannot have a null or empty SpaceObject name");
		}
		if (radius <= 0) {
			throw new IllegalArgumentException("Cannot have a radius less than or equal to 0");
		}
		this.mass = 0;
		this.name = name;
		this.radius = radius;
		this.location = location;
	}

	public final String getName() {
		return name;
	}

	public final int getRadius() {
		return radius;
	}

	public final Vector3 getLocation() {
		return location;
	}

	public final double getMass() {
		return mass;
	}

	public final void addMass(double amount) {
		this.mass += amount;
	}

	public final void removeMass(double amount) {
		this.mass -= amount;
	}

	@Override
	public boolean canTick() {
		return true;
	}
}