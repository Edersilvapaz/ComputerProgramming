package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every Bus that shows up on the road.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Bus extends Entity{
	
	/*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected
	 */
	
	/**
	 * Defines the bus' width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of the game so that the bus can rely on the game's variables.
	 * @param pos Defines the initial position of the bus on the road.
	 */
	public Bus(Game game,int pos) {
		//The y position will be in one of the five lines that the road has, when y is 315,349,383,417, or 451 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,315+34*pos,truck_width,truck_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction 
		if(y==417||y==451){
			speed = game.getDefaultSpeed()+0.15f;
			x=-width;
			image = r.nextInt(2)+2;
		}
		else{
			speed = -(game.getDefaultSpeed()+0.15f);
			x=game.getWidht()+width;
			image = r.nextInt(2);
		}
		
		//setting the rectangle variables used to collision detection
		bounds.x=3;
		bounds.y=3;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}
	
	/**
	 * Upgrade the bus x position.
	 */
	@Override
	public void tick() {
		x+=speed;
	}
	
	/**
	 * Draws the alligator on the screen according to its x, y position and the image defined the the constructor.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bus[image],(int)x,(int)y,width,height,null);
	}	
}