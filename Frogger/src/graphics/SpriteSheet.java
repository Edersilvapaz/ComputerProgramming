package graphics;

/**
 * This class specifies SpriteSheets objects, this was created to make it easier to crop the images we want from a larger image;
 */

import java.awt.image.BufferedImage;

/**
 * This class holds the entire sprite sheet of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
	
	/**
	 * Makes a copy of the buffered image passed to it to its own buffered image instance.
	 * @param sheet Buffered image to be saved.
	 */
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	//This is the method used to crop an specific image from the entire sprite sheet;
	/**
	 * Used to load an smaller image that is contained inside the sprite sheet object image.
	 * @param x First x coordinate pixel of the image, at its top right.
	 * @param y First y coordinate pixel of the image, at its top right.
	 * @param width Width of the image that has to be cropped.
	 * @param height Height of the image that has to be cropped.
	 * @return The sub Image the is saved inside the sprite sheet object within the coordinates passed.
	 */
	public BufferedImage crop(int x,int y,int width,int height){
		return sheet.getSubimage(x, y, width, height);
	}
}