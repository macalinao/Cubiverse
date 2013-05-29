package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class SolarSystem extends WorldComponent {

	//2 ^ 16 chunks
	public static final int SYSTEM_RADIUS = 65536;
	//The minimum distance required between planets
	private static final int MIN_DISTANCE = 16;
	//Planets in the solar system
	private THashMap<Vector3, Planet> planets;
	//The star centered at 0,0,0 in the solar system
	private Star star;

	@Override
	public void onAttached() {
		planets = new THashMap<>();
		star = new MainSequenceStar("Sun");
		createPlanet("Earth", 4, new Vector3(0, 32, 0)); //Test planet
	}

	public int getNumOfPlanets() {
		return planets.size();
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public Planet getPlanet(Vector3 location) {
		return planets.get(location);
	}

	public THashMap<Vector3, Planet> getPlanets() {
		return planets;
	}

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
	}

	@Override
	public void onTick(float dt) {
		super.onTick(dt);

		/*
		 Star systemStar = getStar();
		 if (systemStar.getCurrentAge() >= systemStar.getLifespan()) {
		 setStar(systemStar.getNextStageStar());
		 } else {
		 systemStar.incrementAge();
		 }*/
	}
}