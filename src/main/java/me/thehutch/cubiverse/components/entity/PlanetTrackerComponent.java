package me.thehutch.cubiverse.components.entity;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;

import org.spout.api.Engine;
import org.spout.api.component.entity.EntityComponent;
import org.spout.api.geo.discrete.Point;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.scheduler.TaskPriority;

/**
 * @author thehutch
 */
public class PlanetTrackerComponent extends EntityComponent {

	private static final DefaultedKey<Planet> PLANET = new DefaultedKeyImpl<>("Planet", null);

	@Override
	public void onAttached() {
		Engine engine = getOwner().getEngine();
		engine.getScheduler().scheduleSyncRepeatingTask(engine.getPluginManager().getPlugin("Cubiverse"), new Runnable() {
			@Override
			public void run() {
				Point entityChunk = getOwner().getChunk().getBase();
				getDatatable().put(PLANET, entityChunk.getWorld().get(SolarSystem.class).getClosestPlanet(entityChunk, getOwner().getViewDistance()));
			}
		}, 0, 5000, TaskPriority.NORMAL); //Every 5 seconds, closest planet is checked
	}

	public Planet getPlanet() {
		return getDatatable().get(PLANET);
	}

	@Override
	public boolean isDetachable() {
		return false;
	}
}