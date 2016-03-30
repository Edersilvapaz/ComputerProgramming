package src.dev.entities;

/**
 * This is the class that defines every alligator that shows up in the river;
 */

import java.awt.Graphics;
import src.dev.game.Game;
import src.dev.graphics.Assets;

/*
 * This class extends the abstract class Entity, this means that every variable, object or method
 * that it contains can be used by this one, as long as it is defined as protected;
 */

public class Log extends Entity{

	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
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

	//The tick method upgrades the x position;
	@Override
	public void tick() {
		x+=speed;
	}

	//The render method draws the log figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.log,(int)x,(int)y,width,height,null);
	}
}