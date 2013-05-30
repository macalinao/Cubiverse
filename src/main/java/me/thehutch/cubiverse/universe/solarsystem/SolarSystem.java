package me.thehutch.cubiverse.universe.solarsystem;

import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import gnu.trove.map.hash.THashMap;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class SolarSystem extends WorldComponent {

	//Defaults
	public static final String DEFAULT_SOLAR_SYSTEM_NAME = "DEFAULT_SOLAR_SYSTEM";
	public static final Class<? extends Star> DEFAULT_STAR = MainSequenceStar.class;
	//2 ^ 16 chunks
	public static final int SYSTEM_RADIUS = 65536;
	//The minimum chunk distance between planets
	private static final int MIN_DISTANCE = 16;
	//Planets in the solar system
	private THashMap<Vector3, Planet> planets;

	@Override
	public void onAttached() {
		planets = new THashMap<>();
		getOwner().add(MainSequenceStar.class);
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

	/*
	 public boolean createPlanet(String name, int radius, Vector3 location) {
	 double distanceToStar = location.lengthSquared() - getStar().getRadius() - radius;
	 if (distanceToStar > MIN_DISTANCE) {
	 Planet planet = new Planet(name, radius, distanceToStar, Climate.NORMAL);
	 planets.put(location, planet);
	 System.out.println("New planet '" + name + "' created at " + location.toString() +
	 " with a distance of " + distanceToStar + " from the system star");
	 return true;
	 }
	 return false;
	 }*/
}