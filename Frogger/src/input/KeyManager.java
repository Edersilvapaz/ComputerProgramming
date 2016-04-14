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
	
	private Game game;
	private boolean[] keys;					//This boolean array is responsible for identifying which key on the keyboard has been pressed;
	private boolean up,down,left,right;		//These are the keys that the game will use;
	private String initials;
	
	/**
	 * Initiate the boolean array that is used to store which key that action took place on.
	 */
	public KeyManager(Game game){
		this.game=game;
		keys = new boolean[256];
		initials = new String("   ");
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
	 * Not used in the game.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if(GameStates.getState()==game.GameOverState()){
			for(int x=0 ; x<initials.length() ; x++){
				if(initials.charAt(x)==' '){
					
				}
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
	 * 
	 * @return
	 */
	public String getInitials(){
		return initials;
	}
}