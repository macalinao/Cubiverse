package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.set.hash.THashSet;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.component.world.WorldComponent;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public final class SolarSystem extends WorldComponent {

	//Data Keys
	public static final DefaultedKey<? extends Star> SOLAR_SYSTEM_STAR = new DefaultedKeyImpl<>("SystemStar", null);
	public static final DefaultedKey<THashSet<Planet>> SOLAR_SYSTEM_PLANETS = new DefaultedKeyImpl<>("SystemPlanets", null);
	//Maximum radius in chunks a solar system can be
	public static final double MAX_SYSTEM_SIZE = 8192.0;

	public String getName() {
		return getOwner().getName();
	}

	public Star getStar() {
		return getDatatable().get(SOLAR_SYSTEM_STAR);
	}

	public THashSet<Planet> getPlanets() {
		return getDatatable().get(SOLAR_SYSTEM_PLANETS);
	}

	public Planet getPlanetAt(Vector3 location) {
		for(Planet planet : getPlanets()) {
			if (planet.getLocation().equals(location)) {
				return planet;
			}
		}
		return null;
	}

	public Planet getClosestPlanet(Vector3 pos) {
		return getClosestPlanet(pos, MAX_SYSTEM_SIZE);
	}

	public Planet getClosestPlanet(Vector3 pos, double range) {
		Vector3 closestVector = null;
		range *= range;
		for(Planet planet : getPlanets()) {
			double distance = pos.distanceSquared(planet.getLocation());
			if (distance <= range) {
				range = distance;
				closestVector = planet.getLocation();
			}
		}
		return getPlanetAt(closestVector);
	}

	@Override
	public void onTick(float dt) {
		//getStar().tick(dt);
		//for (Planet planet : getPlanets()) {
		//	planet.tick(dt);
		//}
	}

	@Override
	public boolean isDetachable() {
		return false;
	}
}