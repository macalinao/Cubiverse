package me.thehutch.cubiverse.components.entity;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import org.spout.api.component.impl.SceneComponent;
import org.spout.api.component.type.EntityComponent;

/**
 * @author thehutch
 */
public class GravityComponent extends EntityComponent {

	//Universal Gravitational Constant
	public static final double GRAV_CONST = 6.67384E-11;
	//Defaults
	public static final double DEFAULT_MASS = 1.0;
	private double mass;

	@Override
	public void onAttached() {
	}

	public final double getMass() {
		return mass;
	}

	public final GravityComponent setMass(double mass) {
		this.mass = mass;
		return this;
	}

	@Override
	public void onTick(float dt) {
		SceneComponent sc = getOwner().getScene();
		PlanetTrackerComponent tracker = getOwner().get(PlanetTrackerComponent.class);

		SolarSystem solarSystem = getOwner().getWorld().get(SolarSystem.class);
		//Vector3 forceVector = getOwner().

		double force = GravityComponent.calculateForce(getMass(), tracker.getPlanet().getMass(), 0);
	}

	@Override
	public boolean isDetachable() {
		return false;
	}

	public static double calculateForce(double massEntity, double massPlanet, double distance) {
		return (GRAV_CONST * massPlanet * massEntity) / (distance * distance);
	}
}