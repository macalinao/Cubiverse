package me.thehutch.cubiverse.components.entity.player;

import me.thehutch.cubiverse.CubiversePlugin;
import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import org.spout.api.Spout;
import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.scheduler.TaskPriority;

/**
 * @author thehutch
 */
public final class PlanetTrackerComponent extends EntityComponent {

	private Planet currentPlanet;
	private Player player;

	@Override
	public void onAttached() {
		if (!(getOwner() instanceof Player)) {
			throw new IllegalStateException("Can only attached PlayerPlanetComponent to a player");
		}
		player = (Player) getOwner();
		Spout.getScheduler().scheduleSyncRepeatingTask(CubiversePlugin.getInstance(), new Runnable() {
			@Override
			public void run() {
				Point playerChunkLoc = getPlayer().getChunk().getBase();
				setPlanet(playerChunkLoc.getWorld().get(SolarSystem.class).getClosestPlanet(playerChunkLoc, getPlayer().getViewDistance()));
				if (getPlanet() == null) {
					getPlayer().sendMessage("You are in outerspace");
				} else {
					getPlayer().sendMessage("You are on the planet: ", getPlanet().getName());
				}
			}
		}, 0, 5000, TaskPriority.NORMAL); //Every 5 seconds, closest planet is checked
	}

	public Player getPlayer() {
		return player;
	}

	public Planet getPlanet() {
		return currentPlanet;
	}

	public PlanetTrackerComponent setPlanet(Planet planet) {
		this.currentPlanet = planet;
		return this;
	}
}