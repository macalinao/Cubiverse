package me.thehutch.cubiverse.materials;

import org.spout.api.component.type.BlockComponent;
import org.spout.api.material.BlockMaterial;

/**
 * @author thehutch
 */
public abstract class CubiverseBlockMaterial extends BlockMaterial {

	private final double mass;

	public CubiverseBlockMaterial(String name, String model, double mass, Class<? extends BlockComponent>... components) {
		super((short) 0, name, model, components);
		this.mass = mass;
	}

	public double getMass() {
		return mass;
	}
}