package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every Truck that shows up on the road;
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Truck extends Entity{
	
	/**
	 * Defines the truck's width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of game so that the truck can rely on the game variables.
	 * @param pos Defines the initial position of the truck on the screen.
	 */
	public Truck(Game game,int pos,float speed) {
		super(game,0,315+34*pos,truck_width,truck_height,speed);
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction
		//It also defines randomly what image is going to be used to draw the truck
		if(y==417||y==349){
			this.speed = game.getDefaultSpeed()+speed;
			x=-width;
			image = r.nextInt(4)+4;
		}
		else{
			this.speed = -(game.getDefaultSpeed()+speed);
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
	 * Upgrade the truck's x position.
	 */
	@Override
	public void tick() {
		x+=speed;
	}

	/**
	 * Draws the truck image on the screen according to its x and y positions and its respective image defined by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.truck[image],(int)x,(int)y,width,height,null);
	}
}