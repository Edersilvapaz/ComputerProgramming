package testing.entities;

import static org.junit.Assert.*;
import org.junit.Test;
import dev.entities.Player;
import dev.game.Game;

/**
 * The PlayerTester class is the jUnit that tests all the concepts and of the Player Class.
 * @author Eder
 */
public class PlayerTester {
	
	/**
	 * This method tests if the Player constructor initiates the Player object's width, height, x and y positions correctly.<br>
	 * It also tests the getters and setters for position and size of the player.
	 */
	@Test
	public void testPlaterCreation() {
		
		//Hard coding the values for position and size of the player
		int gameWidth = 100;
		int gameHeight = 100;
		int playerWidth = 28;
		int playerHeight = 28;
		int initialYPosition = (100-28)/2;
		int initialXPosition = 100-75;
		
		//Creating a new player object
		Game game = new Game("",gameWidth,gameHeight);
		Player player = new Player(game);
		
		//Testing if the position and size of the player object was rightly created
		assertEquals(player.getWidth(),playerWidth,10);
		assertEquals(player.getHeight(),playerHeight,10);
		assertEquals(player.getX(),initialYPosition,10);
		assertEquals(player.getY(),initialXPosition,10);
	}
	
	/**
	 * This method tests if the Player moves correctly depending on what key is pressed on the keyboard.
	 */
	@Test
	public void testPlayerMoving(){
		
	}
}