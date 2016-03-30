package src.dev.graphics;

/**
 * This is the class responsible to actually loads the image the the folder which contains the sprites of the game;
 * It just contains the static method that load the image with the name passed through the string variable called path
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * The method used in line 21 loads the image from the folder that was specified in the java build path;
 */

public class ImageLoader {

	public static BufferedImage loadImage(String path){
		try {
			//return the buffered image
			return ImageIO.read(ImageLoader.class.getResource("/"+path+".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}	
}