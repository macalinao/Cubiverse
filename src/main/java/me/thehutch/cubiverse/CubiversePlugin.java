package me.thehutch.cubiverse;

import me.thehutch.cubiverse.universe.Universe;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.PluginLogger;

public class CubiversePlugin extends CommonPlugin {

	private static CubiversePlugin instance;
	private static Universe universe;

	@Override
	public void onLoad() {
		instance = this;
		((PluginLogger)getLogger()).setTag(new ChatArguments(ChatStyle.RESET, "[", ChatStyle.DARK_CYAN, "Cubiverse", ChatStyle.RESET, "] "));

		getLogger().info("loaded");
	}

	@Override
	public void onEnable() {
		getEngine().getEventManager().registerEvents(new CubiverseListener(this), this);

		switch (getEngine().getPlatform()) {
			case CLIENT:
				if (getEngine().debugMode()) {
					setupUniverse();
				}
				break;
			case SERVER:
				setupUniverse();
				break;
			default:
				break;
		}
		universe = new Universe();
		universe.createNewSolarSystem("HelloCubiverse");

		/*
		World world = system.getWorld();
		Point spawn = world.getSpawnPoint().getPosition();
		final int cx = spawn.getChunkX();
		final int cz = spawn.getChunkZ();

		Entity e = world.createAndSpawnEntity(spawn, LoadOption.LOAD_GEN, ObserverComponent.class);
		e.setObserver(new FlatIterator(cx, 0, cz, 16, 3));
		world.setSpawnPoint(new Transform(new Point(world, 0, 0, 0), Quaternion.IDENTITY, Vector3.ONE));*/

		getLogger().info("enabled");
	}

	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("disabled");
	}

	private void setupUniverse() {
		/*
		World world = getEngine().loadWorld("default", new FlatWorldGenerator());

		final int radius = 3;
		final boolean newWorld = world.getAge() <= 0 ;

		Point point = world.getSpawnPoint().getPosition();
		final int cx = point.getChunkX();
		final int cz = point.getChunkZ();
		
		Entity e = world.createAndSpawnEntity(point, LoadOption.LOAD_GEN, ObserverComponent.class);
		e.setObserver(new FlatIterator(cx, 0, cz, 16, radius));
		if (newWorld) {
			world.setSpawnPoint(new Transform(new Point(world, 0, 0, 0), Quaternion.IDENTITY, Vector3.ONE));
		}*/
	}

	public static CubiversePlugin getInstance() {
		return instance;
	}

	public static Universe getUniverse() {
		return universe;
	}
}