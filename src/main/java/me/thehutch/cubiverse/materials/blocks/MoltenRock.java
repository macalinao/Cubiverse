package me.thehutch.cubiverse.materials.blocks;

import me.thehutch.cubiverse.materials.CubiverseBlockMaterial;
import org.spout.api.collision.CollisionStrategy;

/**
 * @author thehutch
 */
public class MoltenRock extends CubiverseBlockMaterial {

	public MoltenRock() {
		super("Molten Rock", "model://Cubiverse/materials/blocks/moltenrock/moltenrock.spm", 0.5);
		this.setHardness(50).setOpaque().setCollision(CollisionStrategy.SOLID);
	}
}