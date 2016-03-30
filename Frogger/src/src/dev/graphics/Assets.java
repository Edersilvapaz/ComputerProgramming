package src.dev.graphics;

/**
 * This is the class the initializes and keeps all the images used on the game
 */

import java.awt.image.BufferedImage;

public class Assets {
	
	//Here all the items size all stored on the sprite, they are not objects sizes in the game;
	private static int frog_size = 14;
	private static int car_width = 15;
	private static int car_height = 10;
	private static int truck_width = 27;
	private static int truck_height = 10;
	private static int log_width = 42;
	private static int log_height = 10;
	private static int turtle_width = 15;
	private static int turtle_height = 11;
	private static int alli_width = 47;
	private static int alli_height = 17;
	private static int alliBank_width = 15;
	private static int alliBank_height = 10;
	
	//Here all the buffered image objects used in the game are initialized;
	public static BufferedImage bgnd;
	public static BufferedImage log;
	public static BufferedImage[] alligatorBank = new BufferedImage[4];
	public static BufferedImage[] taxi = new BufferedImage[2];
	public static BufferedImage[] car = new BufferedImage[8];
	public static BufferedImage[] truck = new BufferedImage[8];
	public static BufferedImage[] bus = new BufferedImage[4];
	public static BufferedImage[][] frog = new BufferedImage[4][3];
	public static BufferedImage[][] turtle = new BufferedImage[2][2];
	public static BufferedImage[][] alligator = new BufferedImage[2][2];
	
	//This is the method that initializes all the SpriteSheet objects that are going to be used;
	//It also makes use of the crop() method to associate the specific bufferedImage object to its image; 
	public static void init(){
		
		//Initialize all the game sprite sheets;
		SpriteSheet backgroung = new SpriteSheet(ImageLoader.loadImage("Background"));
		SpriteSheet frogs = new SpriteSheet(ImageLoader.loadImage("Frogs"));
		SpriteSheet vehicles = new SpriteSheet(ImageLoader.loadImage("Vehicles"));
		SpriteSheet riverItems = new SpriteSheet(ImageLoader.loadImage("River_Items"));
		
		//Initialize all the images of the game using the crop method;
		bgnd = backgroung.crop(0,0,400,560);
		log = riverItems.crop(0, 0,log_width,log_height);
		
		for(int i=0 ; i<=1 ; i++ ){
			taxi[i] = vehicles.crop(120+i*car_width,0,car_width,car_height);
		}
		
		for(int i=0 ; i<=3 ; i++ ){
			car[i] = vehicles.crop(car_width*i,0,car_width,car_height);
			car[i+4] = vehicles.crop(car_width*(i+4),0,car_width,car_height);
			truck[i] = vehicles.crop(truck_width*i,10,truck_width,truck_height);
			truck[i+4] = vehicles.crop(truck_width*(i+4),10, truck_width, truck_height);
		}
		
		for(int x=0 ; x<=3 ; x++){
			alligatorBank[x] = riverItems.crop(42+x*alliBank_width,0,alliBank_width,alliBank_height);
			bus[x] = vehicles.crop(x*truck_width,20,truck_width,truck_height);
			for(int y=0 ; y<=2 ; y++)
				frog[x][y] = frogs.crop(frog_size*x,frog_size*y,frog_size,frog_size);
		}
		
		for(int x=0 ; x<=1 ; x++){
			for(int y=0 ; y<=1 ; y++){
				turtle[x][y] = riverItems.crop(x*turtle_width,45+y*turtle_height,turtle_width,turtle_height);
				alligator[x][y] = riverItems.crop(x*alli_width,11+y*alli_height,alli_width,alli_height);
			}
		}
	}
}