package me.thehutch.cubiverse;

import me.thehutch.cubiverse.input.CubiverseInputExecutor;
import me.thehutch.cubiverse.world.generators.PlanetGenerator;
import org.spout.api.Client;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.component.impl.ObserverComponent;
import org.spout.api.entity.Entity;
import org.spout.api.generator.FlatWorldGenerator;
import org.spout.api.geo.LoadOption;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.input.InputManager;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.PluginLogger;
import org.spout.api.util.FlatIterator;

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
		getLogger().info("enabled");
		//getEngine().stop("Finished loading");
	}

	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("disabled");
	}

	private void setupUniverse() {
		//PlanetGenerator generator = new PlanetGenerator();
		World world = getEngine().loadWorld("default", new FlatWorldGenerator());

		final int radius = 3;
		final boolean newWorld = world.getAge() <= 0 ;

		Point point = world.getSpawnPoint().getPosition();
		final int cx = point.getBlockX() >> Chunk.BLOCKS.BITS;
		final int cz = point.getBlockZ() >> Chunk.BLOCKS.BITS;

		Entity e = world.createAndSpawnEntity(point, LoadOption.LOAD_GEN, ObserverComponent.class);
		e.setObserver(new FlatIterator(cx, 0, cz, 16, radius));
		if (newWorld) {
			world.setSpawnPoint(new Transform(new Point(world, 0, 0, 0), Quaternion.IDENTITY, Vector3.ONE));
		}
	}

	public static CubiversePlugin getInstance() {
		return instance;
	}
}