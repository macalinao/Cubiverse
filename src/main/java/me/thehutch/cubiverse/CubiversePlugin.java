package me.thehutch.cubiverse;

import me.thehutch.cubiverse.universe.Universe;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.PluginLogger;

public class CubiversePlugin extends CommonPlugin {

	private static CubiversePlugin instance;

	@Override
	public void onLoad() {
		instance = this;
		((PluginLogger)getLogger()).setTag(new ChatArguments(ChatStyle.RESET, "[", ChatStyle.DARK_CYAN, "Cubiverse", ChatStyle.RESET, "] "));

		getLogger().info("loaded");
	}

	@Override
	public void onEnable() {
		getEngine().getEventManager().registerEvents(new CubiverseListener(this), this);
		
		boolean newWorld = Universe.createNewSolarSystem("HelloCubiverse");

		System.out.println(String.format("New world created '%s' : %s", Universe.getSolarSystem("HelloCubiverse").getOwner().getName(), newWorld));

		getLogger().info("enabled");
	}

	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("disabled");
	}

	public static CubiversePlugin getInstance() {
		return instance;
	}
}