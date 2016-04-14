package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Game;
import graphics.Assets;

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
	
	private Rectangle headBounds;
	
	/**
	 * Defines the alligator's width, height, initial position, speed and direction in which the alligator has to move to.
	 * @param game Instance of the game so the alligator can rely on games variables.
	 * @param pos Defines the initial position of the alligator in the river
	 */
	public Alligator(Game game,int pos) {
		//Its y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,100+34*pos,alli_width,alli_height);
		headBounds = new Rectangle();
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction 
		if(y==202||y==134){
			x=-width;
			image=1;
			speed = game.getDefaultSpeed();
			//setting the rectangle variables used to collision detection
			bounds.x=10;
			headBounds.x=60;
			
		}else{
			x=game.getWidht()+width;
			image=0;
			speed=-game.getDefaultSpeed();
			//setting the rectangle variables used to collision detection
			headBounds.x=10;
			bounds.x=30;
		}
		
		//setting the rectangle variables used to collision detection
		bounds.width=width-40;
		bounds.y=17;
		bounds.height=height-25;
		headBounds.y=17;
		headBounds.width=10;
		headBounds.height=10;
	}
	
	/**
	 * Upgrades the x position of the alligator and defines which alligator image should be drawn by the render() method;
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
	
	/**
	 * Getter for the rectangle object of the alligator head.
	 * @return
	 */
	public Rectangle getHeadBounds(){
		return new Rectangle(headBounds.x+(int)x,headBounds.y+(int)y,headBounds.width,headBounds.height);
	}
}