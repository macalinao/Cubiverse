package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import me.thehutch.cubiverse.data.Climate;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class SolarSystem extends WorldComponent {

	private static final int MIN_DISTANCE = 2048;
	private THashMap<Vector3, Planet> planets;
	private Star star;

	@Override
	public void onAttached() {
		planets = new THashMap<>();
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
		int distanceToStar = (int) location.length() - getStar().getRadius() - radius;
		if (distanceToStar > MIN_DISTANCE) {
			Planet planet = new Planet(name, radius, distanceToStar, Climate.NORMAL);
			planets.put(location, planet);
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