package src.dev.graphics;

/**
 * This class specifies SpriteSheets objects, this was created to make it easier to crop the images we want from a larger image;
 */

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	//Constructor of the game
	//It just makes a copy of the buffered image passed to it to its own buffered image instance;
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	//This is the method used to crop an specific image from the entire sprite sheet;
	public BufferedImage crop(int x,int y,int width,int height){
		return sheet.getSubimage(x, y, width, height);
	}
}