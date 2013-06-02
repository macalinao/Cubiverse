package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import java.util.Collection;
import java.util.Set;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public final class SolarSystem extends WorldComponent {

	//Defaults
	public static final String DEFAULT_SOLAR_SYSTEM_NAME = "DEFAULT_SOLAR_SYSTEM";
	public static final Star DEFAULT_STAR = new MainSequenceStar("Sun");
	//Maximum radius in chunks a solar system can be
	public static final double MAX_SYSTEM_SIZE = 8192.0;
	//Planets in the solar system
	private THashMap<Vector3, Planet> planets;
	//System star
	private Star star;

	@Override
	public void onAttached() {
		this.planets = new THashMap<>();
		if (getStar() == null) {
			setStar(DEFAULT_STAR);
		}
	}

	public Star getStar() {
		return star;
	}

	public SolarSystem setStar(Star star) {
		this.star = star;
		return this;
	}

	public Planet getPlanetAt(Vector3 vec) {
		return planets.get(vec);
	}

	public Collection<Planet> getPlanets() {
		return planets.values();
	}

	public Set<Vector3> getPlanetLocations() {
		return planets.keySet();
	}

	public Planet getClosestPlanet(Vector3 pos) {
		return getClosestPlanet(pos, MAX_SYSTEM_SIZE);
	}

	public Planet getClosestPlanet(Vector3 pos, double range) {
		Vector3 closestVector = null;
		double closestDistance = range * range;
		for (Vector3 vec : getPlanetLocations()) {
			double distance = pos.distanceSquared(vec);
			if (distance <= closestDistance) {
				closestDistance = distance;
				closestVector = vec;
			}
		}
		return getPlanetAt(closestVector);
	}

	@Override
	public void onTick(float dt) {
		getStar().onTick(dt);
		for (Planet planet : planets.values()) {
			planet.onTick(dt);
		}
	}

	@Override
	public boolean isDetachable() {
		return false;
	}
}