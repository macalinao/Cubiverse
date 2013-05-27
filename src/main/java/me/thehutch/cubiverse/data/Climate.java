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
}