package me.thehutch.cubiverse.universe.solarsystem.stars;

/**
 * @author thehutch
 */
public class WhiteDwarf extends Star {

	@Override
	public void onAttached() {
		super.onAttached();
		this.setName("White Dwarf");
		this.setRadius(24);
		this.setLifespan(DEFAULT_LIFESPAN * 24);
		this.setSurfaceTemperature(25000);
	}

	@Override
	public Class<? extends Star> getNextStageStar() {
		return null; //TODO: Black Dwarf
	}
}