package dev.entities;

import java.awt.Graphics;
import dev.game.Game;
import dev.graphics.Assets;

/**
 * This is the class that defines every alligator that shows up in the river.<br> 
 * It extends the abstract class entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Alligator extends Entity{
	
	/*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected
	 */
		
	/**
	 * Defines the alligator's width, height, initial position, speed and direction in which the alligator has to move to.
	 * @param game Instance of the game so the alligator can rely on games variables.
	 * @param pos Defines the initial position of the alligator in the river
	 */
	public Alligator(Game game,int pos) {
		//Its y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,100+34*pos,alli_width,alli_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction 
		if(y==202||y==134){
			x=-width;
			image=1;
			speed = game.getDefaultSpeed();
		}else{
			x=game.getWidht()+width;
			image=0;
			speed=-game.getDefaultSpeed();
		}
	}
	
	/**
	 * Upgrades the x position of the entity and animates the alligator figure on the screen;
	 */
	@Override
	public void tick() {
		x+=speed;
		counter++;
		if(counter==60)
			anim=1;
		if(counter==90){
			anim=0;
			counter=0;
		}
	}
	
	/**
	 * Draws the alligator on the screen according to its x, y position and the figure it is define by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.alligator[image][anim],(int)x,(int)y,width,height,null);
	}
}