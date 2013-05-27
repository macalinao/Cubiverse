package me.thehutch.cubiverse.universe;

import me.thehutch.cubiverse.universe.generator.SolarSystemGenerator;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import org.spout.api.Spout;
import org.spout.api.geo.World;

/**
 * @author thehutch
 */
public class Universe {

	//Universal Gravitational Constant
	public static final float GRAV_CONST = 0.05f;

	private Universe() {
	}

	public static boolean createNewSolarSystem(String name) {
		if (Spout.getEngine().getWorld(name) == null) {
			World newWorld = Spout.getEngine().loadWorld(name, new SolarSystemGenerator());
			newWorld.add(SolarSystem.class);
			return true;
		}
		return false;
	}

	public static SolarSystem getSolarSystem(String name) {
		return Spout.getEngine().getWorld(name).get(SolarSystem.class);
	}
}