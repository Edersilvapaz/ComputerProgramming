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

public class Alligator extends Entity{
	
	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
	public Alligator(Game game,int pos) {
		//Every alligator will have the same size;
		//Its y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed;
		//This position is defined according to the pos variable that is passed to it;
		super(game,0,100+34*pos,alli_width,alli_height);
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one;
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction; 
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
	
	//The tick method upgrades the x position of the entity and uses the counter and anim variable to animate the alligator figure on the screen;
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

	//The render method draws and animates the alligator figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.alligator[image][anim],(int)x,(int)y,width,height,null);
	}
}