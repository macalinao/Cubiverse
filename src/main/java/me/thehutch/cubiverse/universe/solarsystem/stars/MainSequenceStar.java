package me.thehutch.cubiverse.universe.solarsystem.stars;

/**
 * @author thehutch
 */
public class MainSequenceStar extends Star {

	@Override
	public void onAttached() {
		super.onAttached();
		this.setName("Main Sequence Star");
		this.setRadius(196);
		this.setLifespan(DEFAULT_LIFESPAN);
		this.setSurfaceTemperature(DEFAULT_SURFACE_TERMPATURE);
	}

	@Override
	public Class<? extends Star> getNextStageStar() {
		return WhiteDwarf.class;
	}
}