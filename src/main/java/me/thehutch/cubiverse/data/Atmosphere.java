package me.thehutch.cubiverse.data;

import java.awt.Color;
import java.io.Serializable;
/**
 * @author thehutch
 */
public class Atmosphere implements Serializable {

	public static long serialVersionUID = 1L;
	//Defaults
	public static final Atmosphere EARTH_ATMOS = new Atmosphere(Color.blue, Color.red, Color.yellow, true);
	public static final Atmosphere EMPTY_ATMOS = new Atmosphere(Color.black, Color.black, Color.black, false);
	public static final Atmosphere POISONOUS_ATMOS = new Atmosphere(Color.green, Color.magenta, Color.magenta, false);
	//Data
	private final boolean breathable;
	private final Color daySkyColor, sunsetSkyColor, sunriseSkyColor;

	public Atmosphere(Color dayColor, Color sunsetColor, Color sunriseColor, boolean breathable) {
		this.daySkyColor = dayColor;
		this.sunsetSkyColor = sunsetColor;
		this.sunriseSkyColor = sunriseColor;
		this.breathable = breathable;
	}

	public boolean isBreathable() {
		return breathable;
	}

	public Color getDaySkyColor() {
		return daySkyColor;
	}

	public Color getSunsetSkyColor() {
		return sunsetSkyColor;
	}

	public Color getSunriseSkyColor() {
		return sunriseSkyColor;
	}

	@Override
	public String toString() {
		return String.format("[Breathable: %s | DaySkyColor: %s | SunsetSkyColor: %s | SunriseSkyColor: %s |]", isBreathable(), getDaySkyColor(), getSunsetSkyColor(), getSunriseSkyColor());
	}
}