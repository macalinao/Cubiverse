package me.thehutch.cubiverse.universe.solarsystem.stars;

import java.awt.Color;
import me.thehutch.cubiverse.universe.SpaceComponent;
import me.thehutch.cubiverse.utils.Utils;

/**
 * @author thehutch
 */
public abstract class Star extends SpaceComponent {

	//Defaults
	public static final int DEFAULT_LIFESPAN = 630720000; // ~365 days (in ticks)
	public static final int DEFAULT_SURFACE_TERMPATURE = 5800; // ~Temperature of Sun (Kelvin)
	//Star Age Data
	private long lifespan;
	private long currentAge;
	//Star Data
	private int surfaceTemperature;
	private double luminosity;

	@Override
	public void onAttached() {
		super.onAttached();
		this.setName(DEFAULT_NAME + " STAR");
		this.setLifespan(DEFAULT_LIFESPAN);
		this.setCurrentAge(0);
		this.setSurfaceTemperature(DEFAULT_SURFACE_TERMPATURE);
	}

	public final long getLifespan() {
		return lifespan;
	}

	public final Star setLifespan(long lifespan) {
		this.lifespan = lifespan;
		return this;
	}

	public final long getCurrentAge() {
		return currentAge;
	}

	public final Star setCurrentAge(long age) {
		this.currentAge = age;
		return this;
	}

	public final int getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public final Star setSurfaceTemperature(int surfaceTemperature) {
		this.surfaceTemperature = surfaceTemperature;
		this.luminosity = 4 * Math.PI * (getRadius() * getRadius()) * 5.67010E-8 * Math.pow(getSurfaceTemperature(), 4);
		return this;
	}

	public final double getLuminosity() {
		return luminosity;
	}

	public final Color getColour() {
		float λ = (float) (2.898E-3 / getSurfaceTemperature());
		return Utils.convertWavelengthToRGB(λ);
	}

	public abstract Class<? extends Star> getNextStageStar();

	/*
	 * TODO
	 *
	 * Add method for event on start birth/death
	 * 
	 */
}