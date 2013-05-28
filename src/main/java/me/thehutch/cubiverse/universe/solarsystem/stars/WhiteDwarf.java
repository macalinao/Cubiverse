package me.thehutch.cubiverse.universe.solarsystem.stars;

import me.thehutch.cubiverse.universe.solarsystem.Star;

/**
 * @author thehutch
 */
public class WhiteDwarf extends Star {

	public WhiteDwarf() {
		super("White Dwarf", 128, 2000000000, 25000);
	}

	@Override
	public Star getNextStageStar() {
		return null;
	}
}