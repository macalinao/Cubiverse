package me.thehutch.cubiverse.universe.solarsystem;

import java.awt.Color;
import me.thehutch.cubiverse.universe.SpaceObject;
import me.thehutch.cubiverse.utils.Utils;

/**
 * @author thehutch
 */
public abstract class Star extends SpaceObject {

	private final long lifespan;
	private int surfaceTemperature;
	private double luminosity;
	private long currentAge;

	public Star(String name, int radius, long lifespan, int surfaceTemperature) {
		super(name, radius);
		this.lifespan = lifespan;
		this.currentAge = 0;
		this.surfaceTemperature = surfaceTemperature;

		// L = SBConst * SA * T
		double sArea = 4 * Math.PI * (radius * radius);
		this.luminosity = 5.67010E-8 * sArea * surfaceTemperature;
	}

	public final int getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public final double getLuminosity() {
		return luminosity;
	}

	public final Color getColour() {
		float λ = (float) (2.898E-3 / getSurfaceTemperature());
		return Utils.convertWavelengthToRGB(λ);
	}

	public final long getLifespan() {
		return lifespan;
	}

	public final long getCurrentAge() {
		return currentAge;
	}

	public final void setCurrentAge(long age) {
		this.currentAge = age;
	}

	protected void incrementAge() {
		this.currentAge += 100;
	}

	public abstract Star getNextStageStar();
}