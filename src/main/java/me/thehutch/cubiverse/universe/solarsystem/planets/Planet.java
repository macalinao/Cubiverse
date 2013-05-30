package me.thehutch.cubiverse.universe.solarsystem.planets;

import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.SpaceComponent;

/**
 * @author thehutch
 */
public class Planet extends SpaceComponent {

	//Defaults
	public static final Climate DEFAULT_CLIMATE = Climate.NORMAL;
	//Planet Data
	private double distanceToStar;
	private Climate climate;

	@Override
	public void onAttached() {
		super.onAttached();
		this.setName(DEFAULT_NAME + " PLANET");
		this.setClimate(DEFAULT_CLIMATE);
		this.setDistanceToStar(-1);
	}

	public final double getDistanceToStar() {
		return distanceToStar;
	}

	public final Planet setDistanceToStar(double distanceToStar) {
		this.distanceToStar = distanceToStar;
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