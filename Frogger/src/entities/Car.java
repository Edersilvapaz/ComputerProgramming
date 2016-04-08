package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every car that shows up on the road.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Car extends Entity{
	
	 /*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected
	 */
	
	/**
	 * Defines the car's width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of the game so the car can rely on the game variables.
	 * @param pos Defines the initial position of the car on the road.
	 */
	public Car(Game game,int pos) {
		//The y position will be in one of the five lines that the road has, when y is 315,349,383,417, or 451 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,315+34*pos,car_width,car_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction
		//It also defines randomly what image is going to be used to draw the car
		if(y==417||y==451){
			speed = game.getDefaultSpeed()+0.2f;
			x=-width;
			image = r.nextInt(4)+4;
		}
		else{
			speed = -(game.getDefaultSpeed()+0.2f);
			x=game.getWidht()+width;
			image = r.nextInt(4);
		}
		
		//setting the rectangle variables used to collision detection
		bounds.x=3;
		bounds.y=3;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}
	
	/**
	 * Upgrade the car's x position.
	 */
	@Override
	public void tick() {
		x+=speed;
	}
	
	/**
	 * Draws the car on the screen according to its x, y position and the image defined by the constructor.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.car[image],(int)x,(int)y,width,height,null);
	}
}