package me.thehutch.cubiverse.universe;

import me.thehutch.cubiverse.universe.generator.SolarSystemGenerator;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.Spout;
import org.spout.api.component.world.SkydomeComponent;
import org.spout.api.geo.World;
import org.spout.api.model.Model;

/**
 * @author thehutch
 */
public class Universe {

	private Universe() {
	}

	public static boolean createSolarSystem(String name) {
		return createSolarSystem(name, SolarSystem.SOLAR_SYSTEM_STAR.getDefaultValue());
	}

	public static boolean createSolarSystem(String name, Star star) {
		if (Spout.getEngine().getWorld(name) == null) {
			World newWorld = Spout.getEngine().loadWorld(name, new SolarSystemGenerator());
			newWorld.add(SolarSystem.class);
			newWorld.add(SkydomeComponent.class).setModel((Model) Spout.getFileSystem().getResource("model://Cubiverse/terrain/sky/sky.spm"));
			return true;
		}
		return false;
	}

	public static SolarSystem getSolarSystem(String name) {
		return Spout.getEngine().getWorld(name).get(SolarSystem.class);
	}
}