package me.thehutch.cubiverse.universe;

import me.thehutch.cubiverse.data.Storable;
import org.spout.api.component.impl.DatatableComponent;
/**
 * @author thehutch
 */
public abstract class CelestialObject implements Storable {

	private final String name;
	private double mass;
	private int radius;

	public CelestialObject(String name, double mass, int radius) {
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void save(DatatableComponent datatable) {
		
		
	}

	@Override
	public void load(DatatableComponent datatable) {
		
	}
}