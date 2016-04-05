package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is the class responsible for creating and closing the window of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Display {
	
	/*
	 * A Jframe is the window itself
	 * A Canvas is the place where the items are drawn and measured from, not the actual window
	 */
	private JFrame frame=null;
	private Canvas canvas;
	
	private String title;
	private int width,height;
	
	/**
	 * The constructor defines the title, width and height of the game that was passed to it.
	 * @param title Title of the game
	 * @param width Width of the game window
	 * @param height Height of the game window
	 */
	public Display(String title,int width,int height){
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * This method creates the game frame and canvas, configures them and opens the window of the game.
	 */
	public void createDisplay(){
		if(frame==null){
			frame = new JFrame(title);								// set the title of the screen
			frame.setSize(width, height);							// set the width and height of the screen
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// set the window to close and the close button is pressed
			frame.setResizable(false);								// set the window to not resizable so the size of the screen can be changed during the game
			frame.setLocationRelativeTo(null);						// set the screen to open right in the middle of the computer screen
			frame.setVisible(true);									// makes the window visible
			
			canvas = new Canvas();									//Create a new canvas object
			canvas.setPreferredSize(new Dimension(width,height));	//
			canvas.setMaximumSize(new Dimension(width,height));		//Set the size of the canvas to the window width and height and fix it to that size
			canvas.setMinimumSize(new Dimension(width,height));		//
			canvas.setFocusable(false);
			
			frame.add(canvas);										// add the canvas to the window frame
			frame.pack();											// makes the frame pack all the information together
		}
	}
	
	/**
	 * This method closes the frame of the game by calling the dispose() method.
	 */
	public void close(){
		frame.dispose();
	}
	
	/**
	 * Getter for the game canvas.
	 * @return Canvas object
	 */
	public Canvas getCanvas(){
		return canvas;
	}
	
	/**
	 * Getter for the game frame.
	 * @return Frame object
	 */
	public JFrame getFrame(){
		return frame;
	}
}