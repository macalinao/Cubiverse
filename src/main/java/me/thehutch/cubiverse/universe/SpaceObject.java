package me.thehutch.cubiverse.universe;

/**
 * @author thehutch
 */
public abstract class SpaceObject {

	private final String name;
	private int radius;

	public SpaceObject(String name, int radius) {
		this.name = name;
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public int getRadius() {
		return radius;
	}
}