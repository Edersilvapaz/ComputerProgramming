package testing.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import dev.entities.Player;
import dev.game.Game;

public class PlayerTester {

	@Test
	public void testPlaterCreation() {
		int gameWidth = 100;
		int gameHeight = 100;
		Game game = new Game("",gameWidth,gameHeight);
		Player player = new Player(game);
		assertEquals(player.getWidth(),28,10);
		assertEquals(player.getHeight(),10,10);
	}
}