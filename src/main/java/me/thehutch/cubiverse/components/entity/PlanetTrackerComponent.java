package me.thehutch.cubiverse.components.entity;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;

import org.spout.api.Engine;
import org.spout.api.component.type.EntityComponent;
import org.spout.api.geo.discrete.Point;
import org.spout.api.scheduler.TaskPriority;

/**
 * @author thehutch
 */
public class PlanetTrackerComponent extends EntityComponent {

	private Engine engine;
	private Planet currentPlanet;

	@Override
	public void onAttached() {
		engine = getOwner().getEngine();
		engine.getScheduler().scheduleSyncRepeatingTask(engine.getPluginManager().getPlugin("Cubiverse"), new Runnable() {
			@Override
			public void run() {
				Point entityChunk = getOwner().getChunk().getBase();
				currentPlanet = entityChunk.getWorld().get(SolarSystem.class).getClosestPlanet(entityChunk, getOwner().getViewDistance());
			}
		}, 0, 5000, TaskPriority.NORMAL); //Every 5 seconds, closest planet is checked
	}

	public Planet getPlanet() {
		return currentPlanet;
	}

	@Override
	public boolean isDetachable() {
		return false;
	}
}