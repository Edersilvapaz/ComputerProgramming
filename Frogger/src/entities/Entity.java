package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Game;

/**
 * This is the main class for the entities in the game, every entity that the game has will extend this one.<br>
 * This class contains every variables, methods and objects that are common to every different entity object in the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public abstract class Entity {
	
	/*
	 * This class is an abstract class, that means it is not possible to instantiate it, just extending it using other subclasses
	 */
	
	/**
	 * Default value for the player width.
	 */
	public static final int player_width = 28;
	/**
	 * Default value for the player height.
	 */
	public static final int player_height= 28;
	/**
	 * Default value for the car width.
	 */
	public static final int car_width = 40;
	/**
	 * Default value for the car height.
	 */
	public static final int car_height = 28;
	/**
	 * Default value for truck and bus width.
	 */
	public static final int truck_width = 74;
	/**
	 * Default value for truck and bus height.
	 */
	public static final int truck_height = 28;
	/**
	 * Default value for log height.
	 */
	public static final int log_height = 20;
	/**
	 * Default value for the turtle width.
	 */
	public static final int turtle_width = 25;
	/**
	 * Default value for the turtle height.
	 */
	public static final int turtle_height = 20;
	/**
	 * Default value for the river alligator width.
	 */
	public static final int alli_width = 80;
	/**
	 * Default value for the river alligator width.
	 */
	public static final int alli_height = 35;
	/**
	 * Default value for the river bank alligator width.
	 */
	public static final int alliBank_width = 23;
	/**
	 * Default value for the river bank alligator height.
	 */
	public static final int alliBank_height = 19;
	
	//Here all the common variables are defined
	protected float x,y; //Store entity's position on the screen
	protected int width,height; //Store entity's size
	protected float speed; //Speed and direction in which the entity moves on the screen
	protected int counter; //used to upgrade entity's variable according to the time passed
	protected int anim; //Used to animate the entity
	protected int image; //Used to draw the right image to the object according to its movement (from left to right or vice versa)
	
	//Here all the common objects are defined
	protected Game game; //Used to rely on game private variables, like width and height
	protected Random r;	//Used in case any random number needs to be generated in the entity tick() method
	protected Rectangle bounds; //Used to implement collision detection
	
	//protected variables, objects and methods work like private ones, except that they can be used by the classes that extend this one 
	
	/**
	 * Defines the entity object initial position and size.<br>
	 * Creates the random object.<br>
	 * Creates the rectangle object used in the collision detection.<br>
	 * Records an instance of the game so that all the entities objects in the game can rely on the game variables.
	 * @param game Instance of the game.
	 * @param x Entity object initial x position on the screen.
	 * @param y Entity object initial y position on the screen.
	 * @param width Entity object width.
	 * @param height Entity object height.
	 */
	public Entity(Game game,float x,float y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game=game;
		r = new Random();
		bounds = new Rectangle(0,0,width,height);
	}
	
	/**
	 * Upgrade the entity object variables.
	 */
	public abstract void tick();
	
	/**
	 * Draws the entity object on the screen according to its position and respective image.
	 * @param g Graphic object used to draw the images.
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Getter for the entity's x position.
	 * @return Entity object x position on the screen.
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Getter for the entity's y position.
	 * @return Entity object y position on the screen.
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Getter for the entity's width.
	 * @return Entity object width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter for the entity's height.
	 * @return Entity object height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Getter for the rectangle object used in collision detection.
	 * @return Entity's rectangle object that defines the are that the enemy holds in the screen.
	 */
	public Rectangle getBounds() {
		return new Rectangle(bounds.x+(int)x,bounds.y+(int)y,bounds.width,bounds.height);
	}
	
	/**
	 * Getter for the main game object.
	 * @return Instance of the game so the game private variables can be accessed.
	 */
	public Game getGame(){
		return game;
	}
}