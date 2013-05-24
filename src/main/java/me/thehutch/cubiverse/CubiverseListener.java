package me.thehutch.cubiverse;

import me.thehutch.cubiverse.input.CubiverseInputExecutor;
import org.spout.api.Client;
import org.spout.api.Platform;
import org.spout.api.component.impl.CameraComponent;
import org.spout.api.component.impl.InteractComponent;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.engine.EngineStartEvent;
/**
 * @author thehutch
 */
public class CubiverseListener implements Listener {

	private final CubiversePlugin plugin;

	public CubiverseListener(CubiversePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onGameStart(EngineStartEvent event) {
		if (plugin.getEngine().getPlatform() != Platform.CLIENT) {
			return;
		}
		Player player = ((Client) plugin.getEngine()).getPlayer();
		//player.teleport(new Point(player.getWorld(), 0, 0, 0));

		player.add(CameraComponent.class);
		player.add(InteractComponent.class);

		((Client) player.getEngine()).getInputManager().addInputExecutor(new CubiverseInputExecutor(player));

	}
}