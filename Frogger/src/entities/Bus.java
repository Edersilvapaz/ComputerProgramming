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
	
	/**
	 * Defines the bus' width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of the game so that the bus can rely on the game's variables.
	 * @param pos Defines the initial position of the bus on the road.
	 */
	public Bus(Game game,int pos,float speed) {
		super(game,0,315+34*pos,truck_width,truck_height,speed);
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction 
		if(y==417||y==349){
			this.speed = game.getDefaultSpeed()+speed;
			x=-width;
			image = r.nextInt(2)+2;
		}
		else{
			this.speed = -(game.getDefaultSpeed()+speed);
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