package me.thehutch.cubiverse.materials.blocks;

import me.thehutch.cubiverse.materials.CubiverseBlockMaterial;
import org.spout.api.collision.CollisionStrategy;

/**
 * @author thehutch
 */
public class IceRock extends CubiverseBlockMaterial {

	public IceRock() {
		super("Ice Rock", "model://Cubiverse/materials/blocks/ice/ice.spm", 0.25);
		this.setHardness(50).setOpaque().setCollision(CollisionStrategy.SOLID);
	}
}