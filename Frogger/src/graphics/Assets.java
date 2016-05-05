package graphics;

import java.awt.image.BufferedImage;
/**
 * This is the class the loads and holds all the images used on the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Assets {
	
	//Here all the items size all stored on the sprite, they are not objects sizes in the game;
	private static int frog_death_size = 16;
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
	private static int fly_width = 18;
	private static int fly_height = 22;
	
	/**
	 * Image of the background of the game.
	 */
	public static BufferedImage bgnd;
	/**
	 * Image of the log.
	 */
	public static BufferedImage log;
	/**
	 * Images of the river bank alligator.
	 */
	public static BufferedImage[] alligatorBank = new BufferedImage[4];
	/**
	 * Images of the game taxis.
	 */
	public static BufferedImage[] taxi = new BufferedImage[2];
	/**
	 * Images of the game cars.
	 */
	public static BufferedImage[] car = new BufferedImage[8];
	/**
	 * Images of the game trucks.
	 */
	public static BufferedImage[] truck = new BufferedImage[8];
	/**
	 * Images of the game buses.
	 */
	public static BufferedImage[] bus = new BufferedImage[4];
	/**
	 * Images of the frog.
	 */
	public static BufferedImage[][] frog = new BufferedImage[4][3];
	/**
	 * Images used for the frog's death.
	 */
	public static BufferedImage[] frogDeath = new BufferedImage[15];
	/**
	 * Images of the game turtles.
	 */
	public static BufferedImage[][] turtle = new BufferedImage[2][2];
	/**
	 * Images of the game alligators.
	 */
	public static BufferedImage[][] alligator = new BufferedImage[2][2];
	
	/**
	 * Images of the game fly.
	 */
	public static BufferedImage fly;
	
	/**
	 * Images of the game golden fly.
	 */
	public static BufferedImage goldenfly;
	
	/**
	 * Load all the Sprite sheets objects and images that are doing to be used in the game.<br>
	 */
	public static void init(){
		
		//Initialize all the game sprite sheets;
		SpriteSheet backgroung = new SpriteSheet(ImageLoader.loadImage("Background"));
		SpriteSheet frogs = new SpriteSheet(ImageLoader.loadImage("Frogs"));
		SpriteSheet vehicles = new SpriteSheet(ImageLoader.loadImage("Vehicles"));
		SpriteSheet riverItems = new SpriteSheet(ImageLoader.loadImage("River_Items"));
		SpriteSheet frogdeath = new SpriteSheet(ImageLoader.loadImage("Death"));
		SpriteSheet flies = new SpriteSheet(ImageLoader.loadImage("Fly"));
		
		//Initialize all the images of the game using the crop method;
		bgnd = backgroung.crop(0,0,400,560);
		log = riverItems.crop(0, 0,log_width,log_height);
		fly = flies.crop(0,0,fly_width,fly_height);
		goldenfly = flies.crop(18,0,fly_width,fly_height);
		
		for(int x=0 ; x<15 ; x++){
			if(x<7)
				frogDeath[x] = frogdeath.crop(x*frog_death_size,0,frog_death_size,frog_death_size);
			else
				frogDeath[x] = frogdeath.crop((x-7)*frog_size,frog_death_size,frog_size,frog_size);
		}
		
		
		
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