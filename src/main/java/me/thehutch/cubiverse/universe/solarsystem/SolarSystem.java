package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import java.util.Set;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public final class SolarSystem extends WorldComponent {

	//Data Keys
	public static final DefaultedKey<? extends Star> SOLAR_SYSTEM_STAR = new DefaultedKeyImpl<>("SystemStar", new MainSequenceStar());
	public static final DefaultedKey<THashMap<Vector3, Planet>> SOLAR_SYSTEM_PLANETS = new DefaultedKeyImpl<>("SystemPlanets", new THashMap<Vector3, Planet>());
	//Maximum radius in chunks a solar system can be
	public static final double MAX_SYSTEM_SIZE = 8192.0;

	public String getName() {
		return getOwner().getName();
	}

	public Star getStar() {
		return getData().get(SOLAR_SYSTEM_STAR);
	}

	public Planet getPlanetAt(Vector3 pos) {
		return getPlanets().get(pos);
	}

	public THashMap<Vector3, Planet> getPlanets() {
		return getData().get(SOLAR_SYSTEM_PLANETS);
	}

	public Set<Vector3> getPlanetLocations() {
		return getPlanets().keySet();
	}
	
	public Planet getClosestPlanet(Vector3 pos) {
		return getClosestPlanet(pos, MAX_SYSTEM_SIZE);
	}

	public Planet getClosestPlanet(Vector3 pos, double range) {
		Vector3 closestVector = null;
		range *= range;
		for (Vector3 vec : getPlanetLocations()) {
			double distance = pos.distanceSquared(vec);
			if (distance <= range) {
				range = distance;
				closestVector = vec;
			}
		}
		return getPlanetAt(closestVector);
	}

	@Override
	public void onTick(float dt) {
		getStar().tick(dt);
		for (Planet planet : getPlanets().values()) {
			planet.tick(dt);
		}
	}

	@Override
	public boolean isDetachable() {
		return false;
	}
}