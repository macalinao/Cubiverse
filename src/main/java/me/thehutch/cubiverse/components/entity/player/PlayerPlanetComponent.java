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
public final class PlayerPlanetComponent extends EntityComponent {

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
				setPlanet(playerChunkLoc.getWorld().get(SolarSystem.class).getClosestPlanet(playerChunkLoc, getPlayer().getViewDistance() * 16));
			}
		}, 100, 60, TaskPriority.LOW); //Every 3 seconds, closest planet is checked for
	}

	public Player getPlayer() {
		return player;
	}

	public Planet getPlanet() {
		return currentPlanet;
	}

	public PlayerPlanetComponent setPlanet(Planet planet) {
		this.currentPlanet = planet;
		return this;
	}
}