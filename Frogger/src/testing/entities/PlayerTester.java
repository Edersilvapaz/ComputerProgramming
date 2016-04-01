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
		float initialYPosition = 100-75;
		float initialXPosition = (100-28)/2;
		
		//Creating a new player object
		Game game = new Game("",gameWidth,gameHeight);
		Player player = new Player(game);
		
		//Testing if the position and size of the player object was rightly created
		assertEquals(player.getWidth(),playerWidth);
		assertEquals(player.getHeight(),playerHeight);
		assertEquals(player.getX(),initialXPosition,0.0);
		assertEquals(player.getY(),initialYPosition,0.0);
	}

	/*
	 * Every time the player is commanded to move, regardless of the direction, it will take 8 ticks to complete the
	 * movement and then stop, waiting for the keys to be released and a new command to be done.
	 */
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move up.
	 */
	@Test
	public void testPlayerMovingUp(){
		
		//Hard coding the values that the player should move on every tick
		float initialYPosition = 560-75;
		float moveY = 34f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving up
		game.getKeyManager().Up();
		
		//This for loop ticks the player method 20 times while its movement up is allowed
		for( int i=1 ; i<=20 ; i++ ){
			player.tick(); //moves the player 20 times up
			if(i<=8){
				initialYPosition-=moveY;
				assertEquals(player.getY(),initialYPosition,0.0);
			}else
				assertEquals(player.getY(),initialYPosition,0.0);				
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move down.
	 */
	@Test
	public void testPlayerMovingDown(){
		
		//Hard coding the values that the player should move on every tick
		float initialYPosition = 200-75;
		float moveY = 34f/8;
		
		//Creating a new player object
		Game game = new Game("",400,200);
		Player player = new Player(game);
		
		//Moving Down
		game.getKeyManager().Down();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){
			player.tick(); //moves the player 20 times down
			if(i<=8){
				initialYPosition+=moveY;
				assertEquals(player.getY(),initialYPosition,0.0);
			}else
				assertEquals(player.getY(),initialYPosition,0.0);				
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move right.
	 */
	@Test
	public void testPlayerMovingRight(){
		
		//Hard coding the values that the player should move on every tick
		float initialXPosition = (400-28)/2;
		float moveX = 27.40f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving Down
		game.getKeyManager().Right();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){
			player.tick(); //moves the player 20 times down
			if(i<=8){
				initialXPosition+=moveX;
				assertEquals(player.getX(),initialXPosition,0.0);
			}else
				assertEquals(player.getX(),initialXPosition,0.0);				
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move left.
	 */
	@Test
	public void testPlayerMovingLeft(){
		
		//Hard coding the values that the player should move on every tick
		float initialXPosition = (400-28)/2;
		float moveX = 27.40f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving Down
		game.getKeyManager().Left();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){
			player.tick(); //moves the player 20 times down
			if(i<=8){
				initialXPosition-=moveX;
				assertEquals(player.getX(),initialXPosition,0.0);
			}else
				assertEquals(player.getX(),initialXPosition,0.0);				
		}
	}
	
	/*
	 * The player is supposed to move only when its x variable is between 25 and 340, and when its y variable is between 80 and 480
	 */
	
	/**
	 * This methods tests if the player moves when its position gets to its moving bounds
	 */
	@Test
	public void testPlayerMovingOutOfBounds(){
		
	}
}