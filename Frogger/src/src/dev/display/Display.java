package src.dev.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is the class the creates and defines the window that the game runs on;
 */

public class Display {
	
	/*
	 * A Jframe is the window itself;
	 * A Canvas is the place where the items are drawn and measured from, not the actual window
	 */
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width,height;
	
	public Display(String title,int width,int height){
		
		//Define the width, height, and title of the game;
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}

	private void createDisplay(){
		frame = new JFrame(title);								// set the title of the screen;
		frame.setSize(width, height);							// set the width and height of the screen;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// set the window to close and the close button is pressed;
		frame.setResizable(false);								// set the window to not resizable so the size of the screen can be changed during the game;
		frame.setLocationRelativeTo(null);						// set the screen to open right in the middle of the computer screen;
		frame.setVisible(true);									// makes the window visible;
		
		canvas = new Canvas();									//Create a new canvas object;
		canvas.setPreferredSize(new Dimension(width,height));	//
		canvas.setMaximumSize(new Dimension(width,height));		//Set the size of the canvas to the window width and height and fix it to that size;
		canvas.setMinimumSize(new Dimension(width,height));		//
		canvas.setFocusable(false);
		
		frame.add(canvas);										// add the canvas to the window frame;
		frame.pack();											// makes the frame pack all the information together
	}
	
	//method used to finish and close the window;
	public void close(){
		frame.dispose();
	}
	
	//Getters for the window canvas and Frame, used in the game class;
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}