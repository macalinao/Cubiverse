package me.thehutch.cubiverse.universe.solarsystem.stars;

import me.thehutch.cubiverse.universe.solarsystem.Star;

/**
 * @author thehutch
 */
public class MainSequenceStar extends Star {

	public MainSequenceStar(String name) {
		super(name, 64, 5000000, 5800);
	}

	@Override
	public Star getNextStageStar() {
		return new WhiteDwarf();
	}
}