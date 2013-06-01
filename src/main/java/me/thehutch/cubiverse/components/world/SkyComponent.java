package me.thehutch.cubiverse.components.world;

import java.util.logging.Level;
import me.thehutch.cubiverse.effects.CubiverseEffects;
import org.spout.api.Spout;
import org.spout.api.component.type.WorldComponent;
import org.spout.api.model.Model;

/**
 * @author thehutch
 */
public class SkyComponent extends WorldComponent {

	//Defaults
	public static final String DEFAULT_MODEL = "model://Cubiverse/materials/sky/skydome.spm";
	public static final byte MIN_SKYLIGHT = 2;
	public static final byte MAX_SKYLIGHT = 15;
	private String model;

	@Override
	public void onAttached() {
		if (getModel() == null) {
			Spout.getLogger().info("No Sky Model has been set, defaulting to " + DEFAULT_MODEL);
			setModel(DEFAULT_MODEL);
		}
		Spout.getLogger().log(Level.INFO, "Loading Sky Model {0}", model);
		Model model = getOwner().getEngine().getFileSystem().getResource(getModel());
		model.getRenderMaterial().addRenderEffect(CubiverseEffects.SKY);
		getOwner().getData().put("skydome", model);
	}

	public final String getModel() {
		return model;
	}

	public final SkyComponent setModel(String model) {
		this.model = model;
		return this;
	}
}