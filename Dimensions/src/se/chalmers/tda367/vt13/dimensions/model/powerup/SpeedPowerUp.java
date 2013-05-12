package se.chalmers.tda367.vt13.dimensions.model.powerup;

import java.io.Serializable;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

/**
 * This powerup doubles the speed, if the players speed is equal or below 2
 * px/frame.
 * 
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp, Serializable {

	private final float speedModifier = 2;
	private static final long serialVersionUID = 1L;

	/**
	 * @param position
	 * @param size
	 * @param speed
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/SpeedPowerUpMini.png",
				"sound/SpeedPowerUp.mp3");
	}

	@Override
	public void use(GameWorld gm) {
		if (gm.getPlayer().getSpeed().getX() <= gm.getPlayer().getBaseXSpeed()) {
			gm.getPlayer().getSpeed()
					.setX(gm.getPlayer().getSpeed().getX() * speedModifier);
		}
		playSound();
	}
}
