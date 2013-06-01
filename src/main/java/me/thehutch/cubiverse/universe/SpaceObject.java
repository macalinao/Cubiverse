package me.thehutch.cubiverse.universe;

import java.io.Serializable;
import org.spout.api.tickable.BasicTickable;

/**
 * @author thehutch
 */
public abstract class SpaceObject extends BasicTickable implements Serializable {

	//Defaults
	public static final String DEFAULT_NAME = "DEFAULT";
	public static final int DEFAULT_RADIUS = 64;
	//SpaceComponent Data
	private final String name;
	private final int radius;

	public SpaceObject(String name, int radius) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Cannot have a null or empty SpaceObject name");
		}
		if (radius <= 0) {
			throw new IllegalArgumentException("Cannot have a radius less than or equal to 0");
		}
		this.name = name;
		this.radius = radius;
	}

	public final String getName() {
		return name;
	}

	public final int getRadius() {
		return radius;
	}

	@Override
	public boolean canTick() {
		return true;
	}
}