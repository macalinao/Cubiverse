package me.thehutch.cubiverse.universe.solarsystem.stars;

import me.thehutch.cubiverse.universe.SpaceObject;

/**
 * @author thehutch
 */
public class Star extends SpaceObject {

	private final long lifespan;
	private long currentAge;

	public Star(String name, int size, long lifespan) {
		super(name, size);
		this.lifespan = lifespan;
	}

	public long getLifespan() {
		return lifespan;
	}

	public long getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(long age) {
		this.currentAge = age;
	}
}