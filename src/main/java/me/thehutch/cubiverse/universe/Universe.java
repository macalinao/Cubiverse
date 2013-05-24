package me.thehutch.cubiverse.universe;

import gnu.trove.map.hash.THashMap;
import java.util.ArrayList;
import java.util.List;
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
	//Worlds
	private final THashMap<String, World> solarSystems;

	public Universe() {
		this.solarSystems = new THashMap<>();
	}

	public boolean createNewSolarSystem(String name) {
		if (!solarSystems.contains(name)) {
			World newWorld = Spout.getEngine().loadWorld(name, new SolarSystemGenerator());
			newWorld.add(SolarSystem.class);
			this.solarSystems.put(name, newWorld);
			return true;
		}
		return false;
	}

	public SolarSystem getSolarSystem(String name) {
		return solarSystems.get(name).get(SolarSystem.class);
	}

	public List<SolarSystem> getSolarSystems() {
		List<SolarSystem> systems = new ArrayList<>();
		for(World w : solarSystems.values()) {
			systems.add(w.get(SolarSystem.class));
		}
		return systems;
	}
}