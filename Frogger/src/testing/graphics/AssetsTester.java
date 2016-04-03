package testing.graphics;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import dev.graphics.Assets;

/**
 * jUnit that tests all the concepts of the Assets class
 * @author Eder
 */
public class AssetsTester {
	
	/**
	 * Tests if the background image is loaded correctly.
	 */
	@Test
	public void testBackgroundImage() {
		
		Assets.init();
		
		BufferedImage background = null;
		
		//Hard coding all the images available on the game
		try {
			background = ImageIO.read(AssetsTester.class.getResource("/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Testing if the background image in the assets class is what it is supposed to be
		for(int x=0 ; x<background.getWidth() ; x++ )
			for(int y=0 ; y<background.getHeight() ; y++)
				assertTrue(background.getRGB(x,y)==Assets.bgnd.getRGB(x,y)); //Test if the pixel in the x and y position are the same
	}
	
	/**
	 * Tests if the log image is loaded correctly.
	 */
	@Test
	public void testLogImage(){
		
		Assets.init();
		
		BufferedImage log = null;
		
		//Hard coding the log image
		try{
			log = ImageIO.read(AssetsTester.class.getResource("/River_Items.png"));
			log = log.getSubimage(0,0,42,10);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//Test if the log image in the assets class is what it is supposed to be
		for(int x=0 ; x<log.getWidth() ; x++ )
			for(int y=0 ; y<log.getHeight() ; y++ )
				assertTrue(log.getRGB(x,y)==Assets.log.getRGB(x,y)); //Test if the pixel in the x and y position are the same 
	}
	
	/**
	 * Tests if the river bank alligator images are loaded correctly.
	 */
	@Test
	public void testAlligatorBankImage(){
		
		Assets.init();
		
		BufferedImage riverItems = null;
		BufferedImage alligatorImage1 = null;
		BufferedImage alligatorImage2 = null;
		BufferedImage alligatorImage3 = null;
		BufferedImage alligatorImage4 = null;
		
		//Hard coding the alligators images
		try{
			riverItems = ImageIO.read(AssetsTester.class.getResource("/River_Items.png"));
			alligatorImage1 = riverItems.getSubimage(42,0,15,10);
			alligatorImage2 = riverItems.getSubimage(57,0,15,10);
			alligatorImage3 = riverItems.getSubimage(72,0,15,10);
			alligatorImage4 = riverItems.getSubimage(87,0,15,10);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//Test if the alligators images in the assets class are what they are supposed to be
		//first image
		for(int x=0 ; x<alligatorImage1.getWidth() ; x++)
			for(int y=0 ; y<alligatorImage1.getHeight() ; y++)
				assertTrue(alligatorImage1.getRGB(x,y)==Assets.alligatorBank[0].getRGB(x,y)); //Test if the pixel in the x and y position are the same
		//Second Image
		for(int x=0 ; x<alligatorImage2.getWidth() ; x++)
			for(int y=0 ; y<alligatorImage2.getHeight() ; y++)
				assertTrue(alligatorImage2.getRGB(x,y)==Assets.alligatorBank[1].getRGB(x,y)); //Test if the pixel in the x and y position are the same
		//Third Image
		for(int x=0 ; x<alligatorImage3.getWidth() ; x++)
			for(int y=0 ; y<alligatorImage3.getHeight() ; y++)
				assertTrue(alligatorImage3.getRGB(x,y)==Assets.alligatorBank[2].getRGB(x,y)); //Test if the pixel in the x and y position are the same
		//Forth Image
		for(int x=0 ; x<alligatorImage4.getWidth() ; x++)
			for(int y=0 ; y<alligatorImage4.getHeight() ; y++)
				assertTrue(alligatorImage4.getRGB(x,y)==Assets.alligatorBank[3].getRGB(x,y)); //Test if the pixel in the x and y position are the same
	}
	
	/**
	 * Test if the taxi images are loaded correctly.
	 */
	@Test
	public void testTaxiImage(){
		
		Assets.init();
		
		BufferedImage vehicles = null;
		BufferedImage taxiImage1 = null;
		BufferedImage taxiImage2 = null;
		
		//Hard coding the taxi images
		try{
			vehicles = ImageIO.read(AssetsTester.class.getResource("/Vehicles.png"));
			taxiImage1 = vehicles.getSubimage(120,0,15,10);
			taxiImage2 = vehicles.getSubimage(135,0,15,10);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//Tests if the taxi images in the assets class are the they are supposed to be
		//First image
		for(int x=0 ; x<taxiImage1.getWidth() ; x++ )
			for(int y=0 ; y<taxiImage1.getHeight() ; y++ )
				assertTrue(taxiImage1.getRGB(x,y)==Assets.taxi[0].getRGB(x,y));
		//Second image
		for(int x=0 ; x<taxiImage2.getWidth() ; x++ )
			for(int y=0 ; y<taxiImage2.getHeight() ; y++ )
				assertTrue(taxiImage2.getRGB(x,y)==Assets.taxi[1].getRGB(x,y));
	}
}