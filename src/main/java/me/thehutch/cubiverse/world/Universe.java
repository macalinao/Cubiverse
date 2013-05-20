package me.thehutch.cubiverse.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.thehutch.cubiverse.world.planet.Planet;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
/**
 * @author thehutch
 */
public class Universe {

	public static final double GRAVITATIONAL_CONSTANT = 0.5f;

	private final Map<Point, Planet> planets;
	private final World universe;

	public Universe(World world) {
		this.planets = new ConcurrentHashMap<>();
		this.universe = world;
	}

	public void addPlanet(Point point, Planet planet) {
		this.planets.put(point, planet);
	}

	public void addPlanet(Point point, String name, double mass, double radius) {
		this.addPlanet(point, new Planet(name, radius, mass));
	}

	public void load() {
		
	}

	public void save() {
		
	}

	public World getUniverse() {
		return universe;
	}
}