package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.levels.NormalLevel;

public class PlayerTest {

	@Test
	public void testJump() {
		Player p = new Player(new Vector3(10, 100, 0), new Vector3(50, 50, 0),
				new Vector3(2, 2, 2), 15f, false);
		p.jump();
		assertEquals(2f, p.getSpeed().getY(), 0.1);
		p.setIsGrounded(true);
		p.jump();
		assertEquals(17f, p.getSpeed().getY(), 0.1);
		assertFalse(p.getIsGrounded());
	}

	@Test
	public void testOnPlatform() {
		NormalLevel lv = new NormalLevel("Level1", "test");
		Player player = new Player(new Vector3(10, 100, 0), new Vector3(50, 50,
				0), new Vector3(2, 0, 0), 15f, true);
		GameModel model = new GameModel(lv.getList(), player);
		model.getPlayer().getIsGrounded();
		assertTrue(model.getPlayer().getSpeed().getY() == 0);
		assertTrue(model.getPlayer().getIsGrounded());
	}

}
