package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is the class responsible to actually loads the image the the folder which contains the sprite sheets of the game.<br>
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class ImageLoader {
	
	/**
	 * Loads and returns the image that is saved in resource folder.
	 * @param fileName Name of the file that is in the resource folder.
	 * @return Image saved of the file.
	 */
	public static BufferedImage loadImage(String fileName){
		try {
			//return the buffered image
			return ImageIO.read(ImageLoader.class.getResource("/SpriteSheets/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		System.exit(1);
		}
		return null;
	}	
}