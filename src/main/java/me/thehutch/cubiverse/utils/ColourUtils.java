package me.thehutch.cubiverse.utils;

import java.awt.Color;

/**
 * @author thehutch
 */
public class ColourUtils {

	public static Color convertWavelengthToRGB(float wavelength) {
		if (wavelength < 380) {
			return new Color(0.0f, 0.0f, 0.0f);
		}
		if (wavelength < 440) {
			return new Color(-(wavelength - 440.0f) / (440.0f - 380.0f), 0.0f, 1.0f);
		}
		if (wavelength < 490) {
			return new Color(0.0f, (440.0f - wavelength) / (490.0f - 440.0f), 1.0f);
		}
		if (wavelength < 510) {
			return new Color(0.0f, 1.0f, -(wavelength - 510.0f) / (510.0f - 490.0f));
		}
		if (wavelength < 580) {
			return new Color((wavelength - 510.0f) / (510.0f - 490.0f), 1.0f, 0.0f);
		}
		if (wavelength < 645) {
			return new Color(1.0f, -(wavelength - 645.0f) / (645.0f - 580.0f), 0.0f);
		}
		if (wavelength < 780) {
			return new Color(1.0f, 0.0f, 0.0f);
		}
		return new Color(0.0f, 0.0f, 0.0f);
	}
}