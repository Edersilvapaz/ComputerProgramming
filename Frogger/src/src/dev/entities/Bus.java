package src.dev.entities;

/**
 * This is the class that defines every Bus that shows up on the road;
 */

import java.awt.Graphics;
import src.dev.game.Game;
import src.dev.graphics.Assets;

/*
 * This class extends the abstract class Entity, this means that every variable, object or method
 * that it contains can be used by this one, as long as it is defined as protected;
 */

public class Bus extends Entity{
	
	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
	public Bus(Game game,int pos) {
		//Every bus will have the same size;
		//Its y position will be in one of the five lines that the road has, when y is 315,349,383,417, or 451 and it will be fixed;
		//This position is defined according to the pos variable that is passed to it;
		super(game,0,315+34*pos,truck_width,truck_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one;
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction; 
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
	}
	
	//The tick method upgrades the x position;
	@Override
	public void tick() {
		x+=speed;
	}
	
	//The render method draws the bus figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bus[image],(int)x,(int)y,width,height,null);
	}	
}