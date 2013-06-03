package me.thehutch.cubiverse.input;

import org.spout.api.component.entity.SceneComponent;
import org.spout.api.entity.Player;
import org.spout.api.entity.state.PlayerInputState;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.input.InputExecutor;
import org.spout.api.math.QuaternionMath;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class CubiverseInputExecutor implements InputExecutor {

	private final Player player;

	public CubiverseInputExecutor(Player player) {
		this.player = player;
	}

	@Override
	public void execute(float dt, Transform playerTransform) {
		PlayerInputState inputState = player.input();
		SceneComponent sc = player.getScene();
		Transform ts = sc.getTransform();

		Vector3 offset = Vector3.ZERO;
		float speed = 100;
		if (inputState.getForward()) {
			offset = offset.subtract(ts.forwardVector().multiply(speed).multiply(dt));
		} else if (inputState.getBackward()) {
			offset = offset.add(ts.forwardVector().multiply(speed).multiply(dt));
		}
		if (inputState.getLeft()) {
			offset = offset.subtract(ts.rightVector().multiply(speed).multiply(dt));
		} else if (inputState.getRight()) {
			offset = offset.add(ts.rightVector().multiply(speed).multiply(dt));
		}
		if (inputState.getJump()) {
			offset = offset.add(ts.upVector().multiply(speed).multiply(dt));
		} else if (inputState.getCrouch()) {
			offset = offset.subtract(ts.upVector().multiply(speed).multiply(dt));
		}

		ts.translateAndSetRotation(offset, QuaternionMath.rotation(inputState.pitch(), inputState.yaw(), ts.getRotation().getRoll()));
		sc.setTransform(ts);
	}
}
