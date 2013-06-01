package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class SolarSystem extends WorldComponent {

	//Defaults
	public static final String DEFAULT_SOLAR_SYSTEM_NAME = "DEFAULT_SOLAR_SYSTEM";
	public static final Class<? extends Star> DEFAULT_STAR = MainSequenceStar.class;
	//Planets in the solar system
	private THashMap<Vector3, Planet> planets;

	@Override
	public void onAttached() {
		planets = new THashMap<>();
		if (getOwner().getType(Star.class) == null) {
			getOwner().add(DEFAULT_STAR);
		}
	}

	public int getNumOfPlanets() {
		return planets.size();
	}

	public Star getStar() {
		return getOwner().get(Star.class);
	}

	public void setStar(Class<? extends Star> star) {
		getOwner().detach(Star.class);
		getOwner().add(star);
	}

	public Planet getPlanet(Vector3 location) {
		return planets.get(location);
	}

	public THashMap<Vector3, Planet> getPlanets() {
		return planets;
	}

	public Planet getClosestPlanet(Vector3 pos) {
		return getClosestPlanet(pos, Double.MAX_VALUE);
	}

	public Planet getClosestPlanet(Vector3 pos, double maxDistance) {
		Vector3 closestVector = null;
		double closestDistance = Double.MAX_VALUE;
		for (Vector3 vec : planets.keySet()) {
			double distance = pos.distanceSquared(vec);
			if (distance < closestDistance && distance <= maxDistance) {
				closestDistance = distance;
				closestVector = vec;
			}
		}
		return getPlanet(closestVector);
	}
}