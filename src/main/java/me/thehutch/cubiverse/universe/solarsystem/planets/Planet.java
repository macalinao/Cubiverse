package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.SpaceComponent;

/**
 * @author thehutch
 */
public class Planet extends SpaceComponent {

	//Defaults
	public static final Climate DEFAULT_CLIMATE = Climate.NORMAL;
	public static final int DEFAULT_ROTATION_TIME = 24000; //20 minutes
	//Planet Data
	private double distanceToStar;
	private int rotationTime;
	private Climate climate;

	@Override
	public void onAttached() {
		super.onAttached();
		this.setName(DEFAULT_NAME + " PLANET");
		this.setClimate(DEFAULT_CLIMATE);
		this.setRotationTime(DEFAULT_ROTATION_TIME);
		this.setDistanceToStar(-1);
	}

	public final double getDistanceToStar() {
		return distanceToStar;
	}

	public final Planet setDistanceToStar(double distanceToStar) {
		this.distanceToStar = distanceToStar;
		return this;
	}

	public final int getRotationTime() {
		return rotationTime;
	}

	public final Planet setRotationTime(int rotationTime) {
		this.rotationTime = rotationTime;
		return this;
	}

	public final Climate getClimate() {
		return climate;
	}

	public final Planet setClimate(Climate climate) {
		this.climate = climate;
		return this;
	}
}