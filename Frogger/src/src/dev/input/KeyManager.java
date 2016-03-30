package src.dev.input;

/**
 * This is the class that identifies all the actions that are performed on the keyboard;
 * It implements KeyListener which contains the methods keyPressed(), keyReleased(), and keyTyped() method();
 * These methods run every time a key is pressed on the keyboard regardless of what is happening on the game; 
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;					//This boolean array is responsible for identifying which key on the keyboard has been pressed;
	public boolean up,down,left,right;		//These are the keys that the game will use;
	
	//Constructor, initiate all the keys of the keyboard as false, by default parameters;
	public KeyManager(){
		keys = new boolean[256];
	}
	
	//This method upgrades the variable that the game uses,
	//Setting them to its respective value on the key boolean array;
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];		
	}
	
	//In this section are the methods that run every time a key is pressed on the keyboard,
	//They set the key array on the position of the key where the action took place to true or false, depending on the action performed;
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}