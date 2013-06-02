package me.thehutch.cubiverse;

import me.thehutch.cubiverse.input.CubiverseInputExecutor;
import org.spout.api.Client;
import org.spout.api.Platform;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.engine.EngineStartEvent;
import org.spout.api.event.entity.EntitySpawnEvent;
import org.spout.api.event.player.PlayerJoinEvent;

/**
 * @author thehutch
 */
public class CubiverseListener implements Listener {

	private final CubiversePlugin plugin;

	public CubiverseListener(CubiversePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(order = Order.EARLY_IGNORE_CANCELLED)
	public void onPlayerJoin(PlayerJoinEvent event) {
		//TODO
	}

	@EventHandler(order = Order.EARLY)
	public void onEntitySpawn(EntitySpawnEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			//event.getEntity().add(GravityComponent.class);
		}
	}

	@EventHandler
	public void onGameStart(EngineStartEvent event) {
		if (plugin.getEngine().getPlatform() != Platform.CLIENT) {
			return;
		}
		Player player = ((Client) plugin.getEngine()).getPlayer();
		//player.add(GravityComponent.class);
		//player.add(PlanetTrackerComponent.class);

		((Client) player.getEngine()).getInputManager().addInputExecutor(new CubiverseInputExecutor(player));

	}
}