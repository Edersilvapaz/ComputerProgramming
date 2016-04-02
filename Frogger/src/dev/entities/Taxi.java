package dev.entities;

import java.awt.Graphics;
import dev.game.Game;
import dev.graphics.Assets;

/**
 * This is the class that defines every taxi that shows up on the road.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Taxi extends Entity{
	
	/*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected
	 */
	
	/**
	 * Defines the taxi's width, height, initial position, speed and direction in which the bus has to move to.
	 * @param game Instance of game so that the taxi can rely on the game variables.
	 * @param pos Defines the initial position of the taxi on the screen.
	 */
	public Taxi(Game game,int pos) {
		//The y position will be in one of the five lines that the road has, when y is 315,349,383,417, or 451 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,315+34*pos,car_width,car_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction
		if(y==417||y==451){
			speed = game.getDefaultSpeed()+0.3f;
			x=-width;
			image = 1;
		}
		else{
			speed = -(game.getDefaultSpeed()+0.3f);
			x=game.getWidht()+width;
			image = 0;
		}
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