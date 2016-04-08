package entities;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every turtle group that show up in the river.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Turtle extends Entity{
	
	//Every time turtles show up on the river, they will do it in a certain amount, defined by this variable
	private int amountTurtle;
	
	/**
	 * Defines the turtles line width, height, initial position, speed and direction in which the alligator has to move to.<br>
	 * Adjust the width of the turtle object to how many turtles are being asked to be created.
	 * @param game Instance of game so that the turtle line can rely on the game variables.
	 * @param pos Defines the turtle line initial position on the screen.
	 * @param amountTurtle Defines how many turtles needs to be created in the turtle line.
	 */
	public Turtle(Game game,int pos,int amountTurtle) {
		//The y position will be in one of the five lines that the lake has, when y is 100,134,168,202, or 236 and it will be fixed
		//This position is defined according to the pos variable that is passed to it
		super(game,0,115+34*pos,turtle_width,turtle_height);
		
		//The width of the object is redefined by the amount of turtle to draw so that the real hit box can be implemented
		this.amountTurtle=amountTurtle;
		width*=amountTurtle;
		
		//Depending on the y position, it will start in one of the sides of the screen and move to the other one
		//This if statement also defines the image variable so that the right image is printed on the screen according to its direction
		if(y==217||y==149){
			x=-width;
			image=1;
			speed = game.getDefaultSpeed();
		}else{
			x=game.getWidht()+width;
			image=0;
			speed=-game.getDefaultSpeed();
		}
		
		//setting the rectangle variables used to collision detection
		bounds.x=2;
		bounds.y=2;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}

	/**
	 * Upgrade the turtles line x position and define the turtle image that has to be drawn by the render() method.
	 */
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

	/**
	 * Draws the turtles line on the screen according to its x and y position and the direction the are moving to.
	 */
	@Override
	public void render(Graphics g) {
		//this if statement draw the amount of turtle needed according to the side they are moving to;
		for(int i=0 ; i<amountTurtle ; i++ )
			g.drawImage(Assets.turtle[image][anim],(int)(x+i*turtle_width),(int)y,turtle_width,height,null);
		g.setColor(Color.MAGENTA);
		g.fillRect(bounds.x+(int)x,bounds.y+(int)y,bounds.width,bounds.height);
	}
}