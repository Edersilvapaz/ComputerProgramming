package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every taxi that shows up on the road.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Taxi extends Entity{
	
	/**
	 * Defines the taxi's width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of game so that the taxi can rely on the game variables.
	 * @param pos Defines the initial position of the taxi on the screen.
	 */
	public Taxi(Game game,int pos,float speed) {
		super(game,0,315+34*pos,car_width,car_height,speed);
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction
		if(y==417||y==349){
			this.speed = game.getDefaultSpeed()+speed;
			x=-width;
			image = 1;
		}
		else{
			this.speed = -(game.getDefaultSpeed()+speed);
			x=game.getWidht()+width;
			image = 0;
		}
		//setting the rectangle variables used to collision detection
		bounds.x=3;
		bounds.y=3;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}

	/**
	 * Upgrade the taxi's x position.
	 */
	@Override
	public void tick() {
		x+=speed;
	}

	/**
	 * Draws the taxi on the screen according to its x and y positions.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.taxi[image],(int)x,(int)y,width,height,null);
	}	
}