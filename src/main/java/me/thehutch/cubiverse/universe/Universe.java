package me.thehutch.cubiverse.universe;

import gnu.trove.map.hash.THashMap;
import me.thehutch.cubiverse.data.Storable;
import me.thehutch.cubiverse.universe.planets.Planet;
import org.spout.api.component.impl.DatatableComponent;

import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;

/**
 * @author thehutch
 */
public class Universe implements Storable {

	public static final float GRAV_CONST = 0.05f;

	private final World world;
	private THashMap<Point, Planet> planets;

	public Universe(World world) {
		this.world = world;
		this.planets = new THashMap<>();
	}

	public World getWorld() {
		return world;
	}

	public Planet getPlanetAt(Point position) {
		return planets.get(position);
	}

	public Planet getClosestPlanetTo(Point position) {
		return null;
	}

	@Override
	public void save() {
		DatatableComponent data = getWorld().getData();
		data.put("planets", planets);
	}

	@Override
	public void load() {
		DatatableComponent data = getWorld().getData();
		this.planets = (THashMap<Point, Planet>)data.get("planets");
	}
}