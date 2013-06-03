package me.thehutch.cubiverse.universe.solarsystem.stars;

/**
 * @author thehutch
 */
public class WhiteDwarf extends Star {

	private static final double COOLING_RATE = 0.05;
	private static final double TRANSITION_TEMPERATURE = 1500.0;

	public WhiteDwarf(String name) {
		super(name, DEFAULT_RADIUS / 8, DEFAULT_LIFESPAN * 24, 25000);
	}

	@Override
	public Class<? extends Star> getNextStageStar() {
		return null; //TODO: Black Dwarf
	}

	@Override
	public void onTick(float dt) {
		super.onTick(dt);
		if (getSurfaceTemperature() <= TRANSITION_TEMPERATURE) {
			//Transform to Black Dwarf
		} else {
			this.setSurfaceTemperature(getSurfaceTemperature() - COOLING_RATE * dt);
		}
	}
}