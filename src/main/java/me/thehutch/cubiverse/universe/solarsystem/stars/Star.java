package me.thehutch.cubiverse.universe.solarsystem.stars;

import java.awt.Color;
import me.thehutch.cubiverse.universe.SpaceObject;
import me.thehutch.cubiverse.utils.Utils;

/**
 * @author thehutch
 */
public abstract class Star extends SpaceObject {

	//Defaults
	public static final int DEFAULT_LIFESPAN = 630720000; // ~365 days (in ticks)
	public static final int DEFAULT_SURFACE_TERMPATURE = 5800; // ~Temperature of Sun (Kelvin)
	//Star Age Data
	private final long lifespan;
	private long currentAge;
	//Star Data
	private final double surfaceArea;
	private double surfaceTemperature;
	private double luminosity;

	public Star() {
		this(DEFAULT_NAME + "_STAR", DEFAULT_RADIUS * 2);
	}
	
	public Star(String name, int radius) {
		this(name, radius, DEFAULT_SURFACE_TERMPATURE, DEFAULT_LIFESPAN);
	}

	public Star(String name, int radius, int surfaceTemperature, long lifespan) {
		super(name, radius);
		this.lifespan = lifespan;
		this.surfaceArea = 4 * Math.PI * radius * radius;
		this.setSurfaceTemperature(surfaceTemperature);
	}

	public final long getLifespan() {
		return lifespan;
	}

	public final double getLuminosity() {
		return luminosity;
	}

	public final long getCurrentAge() {
		return currentAge;
	}

	public final Star setCurrentAge(long age) {
		this.currentAge = age;
		return this;
	}

	public final double getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public final Star setSurfaceTemperature(double surfaceTemperature) {
		this.surfaceTemperature = surfaceTemperature;
		this.luminosity = surfaceArea * 5.67010E-8 * Math.pow(surfaceTemperature, 4);
		return this;
	}

	public final Color getColour() {
		double λ = 2.898E-3 / getSurfaceTemperature();
		return Utils.convertWavelengthToRGB((float) λ);
	}

	@Override
	public void onTick(float dt) {
		if (currentAge + dt >= getLifespan()) {
			//Initiate star's next phase
		} else {
			currentAge += dt;
		}
	}

	public abstract Class<? extends Star> getNextStageStar();

	/*
	 * TODO
	 *
	 * Add method for event on start birth/death
	 * 
	 */
}