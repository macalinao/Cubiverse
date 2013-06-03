package me.thehutch.cubiverse.components.world;

import java.util.logging.Level;
import me.thehutch.cubiverse.effects.CubiverseEffects;
import org.spout.api.Spout;
import org.spout.api.component.world.WorldComponent;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.model.Model;

/**
 * @author thehutch
 */
public class SkyComponent extends WorldComponent {

	//Defaults
	private static final DefaultedKey<String> MODEL = new DefaultedKeyImpl<>("SkyDomeModel", "model://Cubiverse/materials/sky/skydome.spm");

	@Override
	public void onAttached() {
		Spout.getLogger().log(Level.INFO, "Loading Sky Model {0}", getModel());
		Model model = getOwner().getEngine().getFileSystem().getResource(getModel());
		model.getRenderMaterial().addRenderEffect(CubiverseEffects.SKY);
	}

	public final String getModel() {
		return getDatatable().get(MODEL);
	}

	public final SkyComponent setModel(String model) {
		getDatatable().put(MODEL, model);
		return this;
	}
}