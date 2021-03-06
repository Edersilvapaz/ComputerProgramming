package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Game;
import states.GameStates;

/**
 * This is the class that identifies all the actions that are performed on the keyboard.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class KeyManager implements KeyListener{
	
	private Game game;								//Instance of the game
	private boolean[] keys;							//This boolean array is responsible for identifying which key on the keyboard has been pressed;
	private boolean up,down,left,right;				//These are the keys that the game will use;
	StringBuilder builder = new StringBuilder(); 	//used to concatenate the chars typed on the keyboard into a single string
	
	/**
	 * Initiate the boolean array that is used to store which key that action took place on.<br>
	 * copies and instance of the game object so that it is possible to tests in with state the game is.
	 * @param game Instance of Game
	 */
	public KeyManager(Game game){
		this.game=game;
		keys = new boolean[256];
		builder = new StringBuilder();
	}
	
	/**
	 * Upgrades the variables that the game uses.<br>
	 * Set them to its respective value on the boolean array. 
	 */
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}
	
	/**
	 * Runs every time a key is pressed on the keyboard and stores its respective boolean variable to true. 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
	}
	
	/**
	 * Runs every time a key is released on the keyboard and stores its respective boolean variable to true.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;	
	}
	
	/**
	 * Runs every time a key is typed on the keyboard.<br>
	 * Concatenates the key pressed to the string builder object.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
		int letter = (int)e.getKeyChar();
		
		if(GameStates.getState()==game.GameOverState()){
			if((letter>=48 && letter<=57) || (letter>=97 && letter<=122)){
				if(builder.toString().length()<3)
					builder.append(e.getKeyChar());
			}else if(letter == 8){
				builder.setLength(builder.length()-1);
			}
		}
	}
	
	/**
	 * Getter for the boolean variable that indicates to move up.
	 * @return Boolean variable up.
	 */
	public boolean Up(){
		return up;
	}
	
	/**
	 * Getter for the boolean variable that indicates to move down.
	 * @return Boolean variable down.
	 */
	public boolean Down(){
		return down;
	}
	
	/**
	 * Getter for the boolean variable that indicates to move right.
	 * @return Boolean variable right.
	 */
	public boolean Right(){
		return right;
	}
	
	/**
	 * Getter for the boolean variable that indicates to move left.
	 * @return Boolean variable left.
	 */
	public boolean Left(){
		return left;
	}
	
	/**
	 * Getter for the player's initials
	 * @return Initials of the player as a string object
	 */
	public String getInitials(){
		return builder.toString().toUpperCase();
	}
	
	/**
	 * Resets the string builder object so that new initials can be typed when the game ends.
	 */
	public void resetInitials(){
		builder.setLength(0);
	}
}