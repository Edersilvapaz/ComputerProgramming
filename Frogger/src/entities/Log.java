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
	
	/*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected;
	 */

	/**
	 * Defines the log's width, height, initial position, speed and direction in which the alligator has to move to.
	 * @param game Instance of the game so the log can rely on games variables.
	 * @param pos Defines the initial position of the log in the river.
	 * @param width Used to define the log's width.
	 */
	public Log(Game game,int pos,int width) {
		//Every log will have a fixed height size and a width defined by the width variable passed to it;
		//Its y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed;
		//This position is defined according to the pos variable that is passed to it;
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
	}

	/**
	 * Upgrades the x position of the log on the screen;
	 */
	@Override
	public void tick() {
		x+=speed;
	}

	//The render method draws the log figure on the screen according the object's variables;
	/**
	 * Draws the log on the screen according to its x and y positions defined by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.log,(int)x,(int)y,width,height,null);
	}
}