package me.thehutch.cubiverse.materials;

import me.thehutch.cubiverse.CubiversePlugin;
import org.spout.api.Engine;
import org.spout.api.Platform;
import org.spout.api.component.type.BlockComponent;
import org.spout.api.material.BlockMaterial;
/**
 * @author thehutch
 */
public abstract class CubiverseBlockMaterial extends BlockMaterial {

	private float resistance;
	

	public CubiverseBlockMaterial(String name, String model, Class<? extends BlockComponent>... components) {
		super((short)0, name, model, components);
		if (getEngine().getPlatform() == Platform.CLIENT) {
			if (!getModel().getRenderMaterial().getEntityEffects().contains(null)) {
				//getModel().getRenderMaterial().addRenderEffect();
			}
		}
	}

	public final Engine getEngine() {
		return CubiversePlugin.getInstance().getEngine();
	}
}