package src.dev.entities;

/**
 * This is the main class for the entities in the game, every entity that the game has will extend this one;
 * This class contains every variables, methods and objects that are common to every different entity object in the game;
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import src.dev.game.Game;

/*
 * This class is an abstract class, that means it is not possible to instantiate it, just extending it using other subclasses;
 */

public abstract class Entity {
	
	//Here all the default sizes for entity objects are defined;
	public static final int player_width = 28;
	public static final int player_height= 28;
	public static final int car_width = 40;
	public static final int car_height = 28;
	public static final int truck_width = 74;
	public static final int truck_height = 28;
	public static final int log_height = 20;
	public static final int turtle_width = 25;
	public static final int turtle_height = 20;
	public static final int alli_width = 80;
	public static final int alli_height = 35;
	public static final int alliBank_width = 23;
	public static final int alliBank_height = 19;
	
	//Here all the common variables are defined:
	//x and y - used to record the entity's position on the screen; 
	//width and height - size of the entity;
	//speed - speed in which the entity moves and the direction it moves on the screen;
	//counter - used to upgrade the entity's variable according to the time passed
	//anim - used to animate the entity;
	//image - used to draw the right image to the object according to its movement (from left to right or vice versa);
	protected float x,y;
	protected int width,height;
	protected float speed;
	protected int counter;
	protected int anim;
	protected int image;
	
	//Here all the common objects are defined:
	//game - used to make use of game private variables, like accessing its width and height
	//r - used in case any random number needs to be generated in the entity tick() method
	//bounds - Used to implement the collision detection in the game;
	protected Game game;
	protected Random r;
	protected Rectangle bounds;
	
	//protected variables, objects and methods work like private ones, except that they can be used by the classes that extend this one 
	
	//Entity constructor, it runs every time a new entity object is created
	//Defines the entity initial position, size;
	//Creates new random and rectangle objects;
	//Record an instance of the game object passed to it on its own game object;
	public Entity(Game game,float x,float y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game=game;
		r = new Random();
		bounds = new Rectangle(0,0,width,height);
	}
	
	//This section is for entity's tick() and render() method, nothing is done here once each class that extends this one will override these methods;
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	//This section is for getters and setters for the entities import variables, like position, size, and hit box;
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}