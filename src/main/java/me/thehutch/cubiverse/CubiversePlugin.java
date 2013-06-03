package me.thehutch.cubiverse;

import me.thehutch.cubiverse.universe.Universe;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.PluginLogger;

public class CubiversePlugin extends CommonPlugin {

	@Override
	public void onLoad() {
		((PluginLogger)getLogger()).setTag(null);
		getLogger().info("loaded");
	}

	@Override
	public void onEnable() {
		//Events
		getEngine().getEventManager().registerEvents(new CubiverseListener(this), this);

		Universe.createSolarSystem("Sol");
		getLogger().info("enabled");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled");
	}
}