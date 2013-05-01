package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

@SuppressWarnings("serial")
public class DimensionChangePowerUp extends GameObject implements PowerUp {

	public DimensionChangePowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position,size,speed, "data/SpeedPowerUpImg.png", "sound/SpeedPowerUp.mp3");
	}

	@Override
	public void use(GameWorld gw) {
		//model.setDimension(GameModel.Dimension.XZ);
	}

}