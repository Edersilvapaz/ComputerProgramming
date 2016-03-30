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

public class Turtle extends Entity{
	
	//Every time turtles show up on the river, they will do it in a certain amount, defined by this variable;
	private int amountTurtle;
	
	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
	public Turtle(Game game,int pos,int amountTurtle) {
		//Every turtle will have the same size;
		//Its y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed;
		//This position is defined according to the pos variable that is passed to it;
		super(game,0,115+34*pos,turtle_width,turtle_height);
		
		//The width of the object is redefined by the amount of turtle to draw so that the real hit box can be implemented;
		this.amountTurtle=amountTurtle;
		width*=amountTurtle;
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one;
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction;
		if(y==217||y==149){
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
		if(counter==20){
			counter=0;
			if(anim==0)
				anim=1;
			else
				anim=0;
		}
	}

	//The render method draws the log figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		//this if statement draw the amount of turtle needed according to the side they are moving to;
		if(speed<0){
			for(int i=0 ; i<amountTurtle ; i++ )
				g.drawImage(Assets.turtle[image][anim],(int)(x-i*turtle_width),(int)y,turtle_width,height,null);
		}else{
			for(int i=0 ; i<amountTurtle ; i++ )
				g.drawImage(Assets.turtle[image][anim],(int)(x+i*turtle_width),(int)y,turtle_width,height,null);
		}
	}
}