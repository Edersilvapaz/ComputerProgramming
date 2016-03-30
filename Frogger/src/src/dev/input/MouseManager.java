package src.dev.input;

/**
 * This is the class that identifies all the actions that are performed by the mouse;
 * It implements MouseListener and MouseMotionListener which contains the methods:
 * mousePressed(), mouseReleased(), and mouseMoved();
 * These methods run every time an action is performed by the mouse regardless of what is happening on the game;
 * Other methods are provided (line 62 to 80), but the game is not going to make use of them; 
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed, rightPressed;	//these variables detect clicks on the mouse buttons;
	private int mouseX, mouseY;					//these variables store the mouse position on the screen;
	
	public MouseManager(){
		
	}
	
	//In this section are the methods that run every time one of the buttons of the mouse is pressed,
	//They set the respective mouse button boolean variable to true or false, depending on the action performed;
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = true;
		if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			leftPressed = false;
		if(e.getButton()==MouseEvent.BUTTON3)
			rightPressed = false;
	}
	
	//This method upgrade the mouse position on the screen every time the mouse is moved through it;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	//This sections is for getters of the variables that detect clicks and position of the mouse so they can be used inn the game;
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//This sections is for the methods provided to work with the mouse that the game does not use;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}