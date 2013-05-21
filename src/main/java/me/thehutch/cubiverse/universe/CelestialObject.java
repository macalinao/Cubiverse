package me.thehutch.cubiverse.universe;

/**
 * @author thehutch
 */
public abstract class CelestialObject {

	private final String name;
	private double mass, radius;

	public CelestialObject(String name, double mass, double radius) {
		this.name = name;
		this.mass = mass;
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}