package com.chalmers.dimensions.model.powerup;

import java.io.Serializable;

import com.chalmers.dimensions.model.GameObject;
import com.chalmers.dimensions.model.PowerUpHandler;
import com.chalmers.dimensions.model.Vector3;
import com.chalmers.dimensions.util.Assets;

/**
 * This PowerUp increases the players speed.
 * 
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @param position
	 * @param size
	 * @param speed
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Assets.SPEEDPOWERUP_IMAGE,
				Assets.SPEEDPOWERUP_SOUND);
	}

	@Override
	public void use(PowerUpHandler powerUpHandler) {
		powerUpHandler.useSpeedPowerUp();
		playSound();
	}

	@Override
	public SpeedPowerUp clone() {
		return new SpeedPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof SpeedPowerUp) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		SpeedPowerUp p = (SpeedPowerUp) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}
}