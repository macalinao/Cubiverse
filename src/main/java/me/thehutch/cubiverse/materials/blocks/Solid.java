package me.thehutch.cubiverse.materials.blocks;

import me.thehutch.cubiverse.materials.CubiverseBlockMaterial;
import org.spout.api.collision.CollisionStrategy;
/**
 * @author thehutch
 */
public class Solid extends CubiverseBlockMaterial {

	public Solid(String name, String model) {
		super(name, model);
		this.setCollision(CollisionStrategy.SOLID).setOpaque();
	}
}