package me.thehutch.cubiverse.world.planet;
/**
 * @author thehutch
 */
public class Planet {

	private final String name;
	private double radius;
	private double mass;

	public Planet(String name, double radius, double mass) {
		this.name = name;
		this.radius = radius;
		this.mass = mass;
	}

	public String getName() {
		return name;
	}

	public double getMass() {
		return mass;
	}

	public double getRadius() {
		return radius;
	}
}