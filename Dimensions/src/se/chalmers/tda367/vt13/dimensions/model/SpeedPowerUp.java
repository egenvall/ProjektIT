package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class for the speed PowerUp. Increases
 * the players speed when used.
 * @author Carl Fredriksson
 */
public class SpeedPowerUp extends GameObject implements PowerUp {

	// Instance variables
	
	// Public methods
	public SpeedPowerUp(Vector3 position, Vector3 speed, double height, double width) {
		setPosition(position);
		setSpeed(speed);
		setHeight(height);
		setWidth(width);
	}
	
	@Override
	public void use(Player player) {
		if (player.getSpeed().getX() <= 2) {
			player.getSpeed().setX(player.getSpeed().getX() * 2);
		}
	}

	@Override
	public void update() {
		move();
	}

	// Private methods
	/**
	 * Move the PowerUp.
	 */
	private void move() {
		getPosition().add(getSpeed());
	}
	
}