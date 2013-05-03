package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameWorld {

	public enum Dimension {
		XY, XZ, YZ
	}

	/**
	 * I'm thinking there is more events the controller is interested in later,
	 * for example reaching a checkpoint or finish the level so I made this an enum. Open for suggetions though //Simon
	 */
	public enum WorldEvent {
		GAME_OVER;
	}

	private List<GameObject> gameObjects;
	private Player player;
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();


	/**
	 * Constructor.
	 * 
	 * @param gameObjects
	 *            the list of GameObjects
	 * @param player
	 *            the player in the game
	 */
	@Deprecated
	public GameWorld(List<GameObject> gameObjects, Player player) {
		this(gameObjects, player, -0.75f);
	}

	public GameWorld(List<GameObject> gameObjects, Player player, float gravity) {
		this.gameObjects = gameObjects;
		this.player = player;
		currentDimension = Dimension.XY;
		this.gravity = gravity;
		this.baseGravity = gravity;
		// createDimensionTimer(3000);
	}

	/**
	 * Changes dimension after specified time. For testing only.
	 * 
	 * @param interval
	 *            How often the dimension should change
	 */
	public void createDimensionTimer(int interval) {
		javax.swing.Timer timer = new Timer(interval, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentDimension == Dimension.XY) {
					currentDimension = Dimension.XZ;
				} else {
					currentDimension = Dimension.XY;
				}
			}
		});
		timer.start();
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * Add a game object to the gameObjects list.
	 * 
	 * @param gameObject
	 *            the GameObject to be added to the list
	 */
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	/**
	 * Update all the GameObjects in the gameObjects list, and update the
	 * player.
	 */
	public void updateModel() {
		player.calculateSpeed(this);
		movePlayer();
	}

	public void setDimension(Dimension dimension) {
		currentDimension = dimension;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float g) {
		gravity = g;
	}

	public void resetGravity() {
		gravity = baseGravity;
	}

	public Dimension getDimension() {
		return currentDimension;
	}

	private void notifyWorldListeners(WorldEvent worldEvent) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChange(worldEvent);
		}
	}

	public void addWorldListener(WorldListener newListener) {
		listeners.add(newListener);
	}

	/**
	 * Move the player with its speed. Check for collisions and adjust player
	 * accordingly. Last position of the player is used to make sure the player
	 * is only getting grounded when falling or going horizontal.
	 */
	private void movePlayer() {
		Vector3 lastPosition = player.getPosition().clone();
		player.getPosition().add(player.getSpeed());
		boolean platformCollision = false;
		for (GameObject gameObject : gameObjects) {
			if (checkCollision(player, gameObject)) {
				if (gameObject instanceof Platform
						&& lastPosition.getY() >= (gameObject.getPosition()
								.getY() + gameObject.getSize().getY())) {
					player.setIsGrounded(true);
					platformCollision = true;
					adjustPosition(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					// gameObjects.remove(gameObject); TODO get exception if
					// removing?
				} else if (gameObject instanceof Obstacle) {
					notifyWorldListeners(WorldEvent.GAME_OVER);
				}
			}
		}

		if (!platformCollision) {
			player.setIsGrounded(false);
		}
	}

	private void moveObject(GameObject gameObject) {
		gameObject.getPosition().add(gameObject.getSpeed());
	}

	private boolean checkCollision(GameObject object, GameObject otherObject) {
		return !(object.getPosition().getX() > otherObject.getPosition().getX()
				+ otherObject.getSize().getX()
				|| object.getPosition().getX() + object.getSize().getX() < otherObject
						.getPosition().getX()
				|| object.getPosition().getY() > otherObject.getPosition()
						.getY() + otherObject.getSize().getY() || object
				.getPosition().getY() + object.getSize().getY() < otherObject
				.getPosition().getY());
	}

	private void adjustPosition(GameObject object, GameObject otherObject) {
		if (object.getSpeed().getY() < 0) {
			float yOverlap = (otherObject.getPosition().getY() + otherObject
					.getSize().getY()) - object.getPosition().getY();
			object.getPosition().setY(object.getPosition().getY() + yOverlap);
		}
	}

}
