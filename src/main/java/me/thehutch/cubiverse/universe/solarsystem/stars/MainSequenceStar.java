package me.thehutch.cubiverse.universe.solarsystem.stars;
/**
 * @author thehutch
 */
public class MainSequenceStar extends Star {

	public MainSequenceStar() {
		this(DEFAULT_NAME + "_MAIN_SEQUENCE");
	}

	public MainSequenceStar(String name) {
		super(name, DEFAULT_RADIUS, DEFAULT_LIFESPAN, 5800);
	}

	@Override
	public Class<? extends Star> getNextStageStar() {
		return WhiteDwarf.class;
	}
}