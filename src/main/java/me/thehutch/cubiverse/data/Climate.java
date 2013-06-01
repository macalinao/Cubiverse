package me.thehutch.cubiverse.data;

/**
 * @author thehutch
 */
public enum Climate {

	//boolean melting, boolean freezing, boolean snow, boolean rain
	HOT(false),
	NORMAL(true),
	COLD(false);
	private final boolean supportsLife;

	private Climate(boolean supportsLife) {
		this.supportsLife = supportsLife;
	}

	public boolean supportsLife() {
		return supportsLife;
	}

	public static Climate getClimateFromPlanetDistance(double distance) {
		if (distance < 2048) {
			return Climate.HOT;
		}
		if (distance < 4096) {
			return Climate.NORMAL;
		}
		return Climate.COLD;
	}
}