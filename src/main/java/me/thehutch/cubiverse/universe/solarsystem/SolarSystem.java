package me.thehutch.cubiverse.universe.solarsystem;

import gnu.trove.map.hash.THashMap;
import java.util.Collection;
import java.util.Random;
import java.util.Set;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.MainSequenceStar;
import me.thehutch.cubiverse.universe.solarsystem.stars.Stars;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
/**
 * @author thehutch
 */
public class SolarSystem {

	private final World world;
	private final int size, numOfPlanets;
	private final THashMap<Point, Planet> planets;

	private Star star;

	public SolarSystem(World world) {
		this(world, 64, new Random().nextInt(7) + 3);
	}

	public SolarSystem(World world, int size, int numOfPlanets) {
		this(world, new MainSequenceStar(Stars.generateStarName()), size, numOfPlanets);
	}

	public SolarSystem(World world, Star star, int size, int numOfPlanets) {
		this.world = world;
		this.size = size;
		this.numOfPlanets = numOfPlanets;
		this.star = star;
		this.planets = new THashMap<>();

		//Load planets
		planets.put(new Point(world, 0, 0, 0), new Planet("Test_Planet", 500, 2));
	}

	public World getWorld() {
		return world;
	}

	/**
	 * Get the size of the solar system (diameter), measured in chunks
	 *
	 * @return
	 *			The diameter of the solar system in chunks
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the number of planets found in the solar system
	 * @return
	 */
	public int getNumOfPlanets() {
		return numOfPlanets;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public Planet getPlanet(Point location) {
		return planets.get(location);
	}

	public Set<Point> getPlanetLocations() {
		return planets.keySet();
	}

	public THashMap<Point, Planet> getPlanets() {
		return planets;
	}
}