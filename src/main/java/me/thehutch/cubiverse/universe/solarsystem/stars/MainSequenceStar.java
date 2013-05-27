package me.thehutch.cubiverse.universe.solarsystem.stars;

/**
 * @author thehutch
 */
public class MainSequenceStar extends Star {

	//TODO change these values
	private static final long LIFESPAN = 123456789;
	private static final int RADIUS = 128;

	public MainSequenceStar(String name) {
		super(name, RADIUS, LIFESPAN);
	}
}