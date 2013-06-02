package me.thehutch.cubiverse.materials.blocks;

import me.thehutch.cubiverse.materials.CubiverseBlockMaterial;
import org.spout.api.collision.CollisionStrategy;

/**
 * @author thehutch
 */
public class StarBlock extends CubiverseBlockMaterial {

	public StarBlock() {
		super("Star", "model://Cubiverse/materials/blocks/star/star.spm", 10);
		this.setHardness(250).setOpaque().setCollision(CollisionStrategy.SOLID);
	}
}