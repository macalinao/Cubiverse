package me.thehutch.cubiverse.events;

import me.thehutch.cubiverse.components.entity.PlanetTrackerComponent;
import me.thehutch.cubiverse.materials.CubiverseBlockMaterial;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.block.BlockChangeEvent;

/**
 * @author thehutch
 */
public class PlayerEvents implements Listener {

	@EventHandler(order = Order.EARLY_IGNORE_CANCELLED)
	public void onBlockBreak(BlockChangeEvent event) {
		if (event.getCause().getSource() instanceof Player) {
			PlanetTrackerComponent tracker = ((Player) event.getCause().getSource()).get(PlanetTrackerComponent.class);
			if (tracker != null) {
				if (event.getBlock().getMaterial() instanceof CubiverseBlockMaterial) {
					CubiverseBlockMaterial material = (CubiverseBlockMaterial) event.getBlock().getMaterial();
					//TODO change planet mass based on block broken
				}
			}
		}
	}
}