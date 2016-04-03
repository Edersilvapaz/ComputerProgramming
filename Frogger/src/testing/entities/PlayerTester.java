package testing.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.junit.Test;

import dev.entities.Player;
import dev.game.Game;

/**
 * jUnit that tests all the concepts and of the Player Class.
 * @author Eder
 */
public class PlayerTester {
	
	/**
	 * This method tests if the Player constructor initiates the Player object's width, height, x and y positions correctly.<br>
	 * It also tests the getters and setters for position and size of the player.
	 */
	@Test
	public void testCreation() {
		
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
	 * movement no matter if the key that commanded the movement is still pressed or not.
	 */
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move up.
	 */
	@Test
	public void testMovingUp(){
		
		//Hard coding the values that the player should move on every tick
		float initialYPosition = 560-75;
		float moveY = 34f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving up - Hard Coding a key event of the W key
		Button button = new Button("");
		KeyEvent key = new KeyEvent(button,1,20,1,KeyEvent.VK_W,'w');
		game.getKeyManager().keyPressed(key);
		game.getKeyManager().tick();
		player.tick();
		game.getKeyManager().keyReleased(key);
		game.getKeyManager().tick();
		
		//This for loop ticks the player method 20 times while its movement up is allowed
		for( int i=1 ; i<=20 ; i++ ){
			if(i<=8){
				initialYPosition-=moveY;
				assertEquals(player.getY(),initialYPosition,0.0);
			}else
				assertEquals(player.getY(),initialYPosition,0.0);				
			player.tick(); //moves the player 20 times up
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move down.
	 */
	@Test
	public void testMovingDown(){
		
		//Hard coding the values that the player should move on every tick
		float initialYPosition = 200-75;
		float moveY = 34f/8;
		
		//Creating a new player object
		Game game = new Game("",400,200);
		Player player = new Player(game);
		
		//Moving Down - Hard coding a key event of S key
		Button button = new Button("");
		KeyEvent key = new KeyEvent(button,1,20,1,KeyEvent.VK_S,'s');
		game.getKeyManager().keyPressed(key);
		game.getKeyManager().tick();
		player.tick();
		game.getKeyManager().keyReleased(key);
		game.getKeyManager().tick();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){
			if(i<=8){
				initialYPosition+=moveY;
				assertEquals(player.getY(),initialYPosition,0.0);
			}else
				assertEquals(player.getY(),initialYPosition,0.0);				
			player.tick(); //moves the player 20 times down
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move right.
	 */
	@Test
	public void testMovingRight(){
		
		//Hard coding the values that the player should move on every tick
		float initialXPosition = (400-28)/2;
		float moveX = 27.40f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving Down - Hard coding a key event of the D key
		Button button = new Button("");
		KeyEvent key = new KeyEvent(button,1,10,1,KeyEvent.VK_D,'d');
		game.getKeyManager().keyPressed(key);
		game.getKeyManager().tick();
		player.tick();
		game.getKeyManager().keyReleased(key);
		game.getKeyManager().tick();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){			
			if(i<=8){
				initialXPosition+=moveX;
				assertEquals(player.getX(),initialXPosition,0.0);
			}else
				assertEquals(player.getX(),initialXPosition,0.0);
			player.tick(); //Move the player 20 times right
		}
	}
	
	/**
	 * This method tests if the Player moves correctly when is it commanded to move left.
	 */
	@Test
	public void testMovingLeft(){
		
		//Hard coding the values that the player should move on every tick
		float initialXPosition = (400-28)/2;
		float moveX = 27.40f/8;
		
		//Creating a new player object
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Moving Down - Hard coding a key event of the key A
		Button button = new Button("");
		KeyEvent key = new KeyEvent(button,1,10,1,KeyEvent.VK_A,'a');
		game.getKeyManager().keyPressed(key);
		game.getKeyManager().tick();
		player.tick();
		game.getKeyManager().keyReleased(key);
		game.getKeyManager().tick();
		
		//This for loop ticks the player method 20 times while its movement down is allowed
		for( int i=1 ; i<=20 ; i++ ){
			if(i<=8){
				initialXPosition-=moveX;
				assertEquals(player.getX(),initialXPosition,0.0);
			}else
				assertEquals(player.getX(),initialXPosition,0.0);				
			player.tick(); //moves the player 20 times left
		}
	}
	
	/**
	 * The player is not supposed to move up when its position is lesser than or equal to 80.<br>
	 * This test makes the player move to up until it stops moving.<br>
	 * Then, test if the y position where it stopped is lesser than or equal to 80.
	 */
	@Test
	public void testMovingOutOfBoundsUp(){
		
		//Used to check when the player is not moving anymore 
		float currentLocation;
		float lastLocation;
		
		//Create the new player
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Hard code the key event of the key W
		Button button = new Button("");
		KeyEvent keyUp = new KeyEvent(button,1,10,1,KeyEvent.VK_W,'w');
		game.getKeyManager().keyPressed(keyUp);
		game.getKeyManager().tick();
		
		//makes the player move until its last position and current position are the same, that means that it got to its moving bounds
		while(true){
			
			//loop for necessary due to its 8 tick() moving pattern
			lastLocation = player.getY();
			for( int i=0 ; i<8 ; i++ ){
				player.tick();
			}
			currentLocation = player.getY();
			
			//Reset the moving of the player
			game.getKeyManager().keyReleased(keyUp);
			game.getKeyManager().tick();
			player.tick();
			game.getKeyManager().keyPressed(keyUp);
			game.getKeyManager().tick();

			if(lastLocation==currentLocation)break;
		}
		
		//Test if the player last position stopped moving when it got out of the moving bounds
		assertTrue(currentLocation<=80);
	}
	
	/**
	 * The player is not supposed to move down when its y position is greater than or equal to 480.<br>
	 * This test makes the player move to down until it stops moving.<br>
	 * Then, test if the y position where it stopped is greater than or equal to 480.
	 */
	@Test
	public void testMovingOutOfBoundsDown(){
		
		//Used to check when the player is not moving anymore 
		float currentLocation;
		float lastLocation;
		
		//Create the new player
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Hard code the key event of the key S
		Button button = new Button("");
		KeyEvent keyUp = new KeyEvent(button,1,10,1,KeyEvent.VK_S,'s');
		game.getKeyManager().keyPressed(keyUp);
		game.getKeyManager().tick();
		
		//makes the player move until its last position and current position are the same, that means that it got to its moving bounds
		while(true){
			
			//loop for necessary due to its 8 tick() moving pattern
			lastLocation = player.getY();
			for( int i=0 ; i<8 ; i++ ){
				player.tick();
			}
			currentLocation = player.getY();
			
			//Reset the moving of the player
			game.getKeyManager().keyReleased(keyUp);
			game.getKeyManager().tick();
			player.tick();
			game.getKeyManager().keyPressed(keyUp);
			game.getKeyManager().tick();

			if(lastLocation==currentLocation)break;
		}
		
		//Test if the player last position stopped moving when it got out of the moving bounds
		assertTrue(currentLocation>=480);
	}
	
	/**
	 * The player is not supposed to move right when its x position is greater than or equal to 340.<br>
	 * This test makes the player move to the right until it stops moving.<br>
	 * Then, test if the x position where it stopped is greater than or equal to 340.
	 */
	@Test
	public void testMovingOutOfBoundsRigh(){
		
		//Used to check when the player is not moving anymore 
		float currentLocation;
		float lastLocation;
		
		//Create the new player
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Hard code the key event of the key D
		Button button = new Button("");
		KeyEvent keyUp = new KeyEvent(button,1,10,1,KeyEvent.VK_D,'d');
		game.getKeyManager().keyPressed(keyUp);
		game.getKeyManager().tick();
		
		//makes the player move until its last position and current position are the same, that means that it got to its moving bounds
		while(true){
			
			//loop for necessary due to its 8 tick() moving pattern
			lastLocation = player.getX();
			for( int i=0 ; i<8 ; i++ ){
				player.tick();
			}
			currentLocation = player.getX();
			
			//Reset the moving of the player
			game.getKeyManager().keyReleased(keyUp);
			game.getKeyManager().tick();
			player.tick();
			game.getKeyManager().keyPressed(keyUp);
			game.getKeyManager().tick();

			if(lastLocation==currentLocation)break;
		}
		
		//Test if the player last position stopped moving when it got out of the moving bounds
		assertTrue(currentLocation>=340);
	}
	
	/**
	 * The player is not supposed to move left when its x position is lesser than or equal to 25.<br>
	 * This test makes the player move to the left until it stops moving.<br>
	 * Then, test if the x position where it stopped is lesser than or equal to 25.
	 */
	@Test
	public void testMovingOutOfBoundsLeft(){
		
		//Used to check when the player is not moving anymore 
		float currentLocation;
		float lastLocation;
		
		//Create the new player
		Game game = new Game("",400,560);
		Player player = new Player(game);
		
		//Hard code the key event of the key A
		Button button = new Button("");
		KeyEvent keyUp = new KeyEvent(button,1,10,1,KeyEvent.VK_A,'a');
		game.getKeyManager().keyPressed(keyUp);
		game.getKeyManager().tick();
		
		//makes the player move until its last position and current position are the same, that means that it got to its moving bounds
		while(true){
			
			//loop for necessary due to its 8 tick() moving pattern
			lastLocation = player.getX();
			for( int i=0 ; i<8 ; i++ ){
				player.tick();
			}
			currentLocation = player.getX();
			
			//Reset the moving of the player
			game.getKeyManager().keyReleased(keyUp);
			game.getKeyManager().tick();
			player.tick();
			game.getKeyManager().keyPressed(keyUp);
			game.getKeyManager().tick();

			if(lastLocation==currentLocation)break;
		}
		
		//Test if the player last position stopped moving when it got out of the moving bounds
		assertTrue(currentLocation<=25);
	}
}