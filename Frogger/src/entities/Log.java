package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every log that shows up in the river.<br>
 * It extends the abstract class entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Log extends Entity{

	/**
	 * Defines the log's width, height, initial position, speed and direction in which the alligator has to move to.
	 * @param game Instance of the game so the log can rely on games variables.
	 * @param pos Defines the initial position of the log in the river.
	 * @param width Used to define the log's width.
	 */
	public Log(Game game,int pos,int width) {
		super(game,0,115+34*pos,width,log_height);
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one;
		if(y==217||y==149){
			x=-width;
			speed=game.getDefaultSpeed();
		}
		else{
			x=game.getWidht()+width;
			speed=-game.getDefaultSpeed();
		}
		//setting the rectangle variables used to collision detection
		bounds.x=8;
		bounds.y=6;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}

	/**
	 * Upgrades the x position of the log on the screen;
	 */
	@Override
	public void tick() {
		x+=speed;
	}
	
	/**
	 * Draws the log on the screen according to its x and y positions defined by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.log,(int)x,(int)y,width,height,null);
	}
}