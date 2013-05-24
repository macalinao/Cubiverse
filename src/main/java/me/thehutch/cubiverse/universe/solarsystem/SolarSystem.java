package me.thehutch.cubiverse.universe.solarsystem;

import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import gnu.trove.map.hash.THashMap;
import java.util.Collection;
import java.util.Set;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.math.Vector3;
/**
 * @author thehutch
 */
public class SolarSystem extends WorldComponent {

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

	public Set<Vector3> getPlanetLocations() {
		return planets.keySet();
	}

	public THashMap<Vector3, Planet> getPlanets() {
		return planets;
	}

	public void save() {
		Collection<Planet> planets = this.planets.values();
		getStar().save(getOwner().getData());
		for(Planet planet : planets) {
			planet.save(getOwner().getData());
		}
	}

	public void load() {
		Collection<Planet> planets = this.planets.values();
		getStar().load(getOwner().getData());
		for(Planet planet : planets) {
			planet.load(getOwner().getData());
		}
	}
}