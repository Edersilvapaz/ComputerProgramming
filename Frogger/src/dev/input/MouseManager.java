package dev.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This is the class that identifies all the actions that are performed by the mouse.<br>
 * It holds boolean variables so that is easy to detected in the game if the mouse is used.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed, rightPressed;	//these variables detect clicks on the mouse buttons;
	private int mouseX, mouseY;					//these variables store the mouse position on the screen;
	
	/**
	 * Set the respective mouse button boolean variable to true.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = true;
		if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = true;
	}
	
	/**
	 * Set the respective mouse button boolean variable to true.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = false;
		if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = false;
	}
	
	/**
	 * Stores the mouse x and y position every time the mouse moves on the screen.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	/**
	 * Runs every time the mouse click and drags.<br>
	 * Not used in the game.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	/**
	 * Runs every time the mouse is clicked, that means pressing and releasing it.<br>
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
	 * Runs every time the mouse enters the screen.<br>
	 * Not used in the game.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	/**
	 * Runs every time the mouse exits the screen.<br>
	 * Not used in the game.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * Getter for the boolean variable that says if the left button of the mouse is pressed or not.
	 * @return True for pressed, false for not pressed.
	 */
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	/**
	 * Getter for the boolean variable that says if the right button of the mouse is pressed or not
	 * @return True for pressed, false for not pressed.
	 */
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	/**
	 * Getter for the mouse position on the screen.
	 * @return Mouse x position on the screen.
	 */
	public int getMouseX(){
		return mouseX;
	}
	
	/**
	 * Getter for the mouse position on the screen.
	 * @return Mouse y position on the screen.
	 */
	public int getMouseY(){
		return mouseY;
	}
}