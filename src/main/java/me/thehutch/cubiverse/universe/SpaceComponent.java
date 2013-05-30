package me.thehutch.cubiverse.universe;

import org.spout.api.component.type.WorldComponent;

/**
 * @author thehutch
 */
public abstract class SpaceComponent extends WorldComponent {

	//Defaults
	public static final String DEFAULT_NAME = "DEFAULT";
	public static final int DEFAULT_RADIUS = 32;
	//SpaceComponent Data
	private String name;
	private int radius;

	@Override
	public void onAttached() {
		this.setName(DEFAULT_NAME);
		this.setRadius(DEFAULT_RADIUS);
	}

	public final String getName() {
		return name;
	}

	public final SpaceComponent setName(String name) {
		this.name = name;
		return this;
	}

	public final int getRadius() {
		return radius;
	}

	public final SpaceComponent setRadius(int radius) {
		this.radius = radius;
		return this;
	}
}