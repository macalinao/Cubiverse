package me.thehutch.cubiverse.components.entity;

import org.spout.api.component.entity.EntityComponent;
import org.spout.api.component.entity.SceneComponent;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;

/**
 * @author thehutch
 */
public class GravityComponent extends EntityComponent {

	//Universal Gravitational Constant
	public static final double GRAV_CONST = 6.67384E-11;
	//Default Keys
	private static final DefaultedKey<Double> MASS = new DefaultedKeyImpl<>("Mass", 50.0);

	public double getMass() {
		return getDatatable().get(MASS);
	}

	public GravityComponent setMass(double mass) {
		getDatatable().put(MASS, mass);
		return this;
	}

	@Override
	public void onTick(float dt) {
		PlanetTrackerComponent tracker = getOwner().get(PlanetTrackerComponent.class);
		SceneComponent sc = getOwner().getScene();

		
		//TODO
		//SceneComponent sc = getOwner().getScene();
		//PlanetTrackerComponent tracker = getOwner().get(PlanetTrackerComponent.class);
		//SolarSystem solarSystem = getOwner().getWorld().get(SolarSystem.class);
		//Vector3 forceVector = getOwner().
		//double force = GravityComponent.calculateForce(getMass(), tracker.getPlanet().getMass(), 0);
	}
}