package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines every alligator that shows up in the river bank.<br>
 * It extends the abstract class Entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class AlligatorBank extends Entity{
	

	/*
	 * This class extends the abstract class Entity, this means that every variable, object or method
	 * that it contains can be used by this one, as long as it is defined as protected
	 */
	
	private final int time = 1800; //Holds how much time the alligator stays in a respective bank
	
	/**
	 * Defines the river bank alligator's width, height.<br>
	 * Defines the random initial position of the river bank alligator in one of the river banks.
	 * @param game Instance of the game so that the bank alligator can rely on the game variables.
	 */
	public AlligatorBank(Game game) {
		//Its y will be fixed in the river bank height
		//Its x position will be in one of the river banks, defined randomly by the r object and the expression on line 31
		super(game,0,82,alliBank_width,alliBank_height);
		x=25f+82.2f*r.nextInt(5);
		
		//setting the rectangle variables used to collision detection
		bounds.x=1;
		bounds.y=1;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}
	
	/**
	 * Controls how much time the alligator is in one river bank and gives it a new random position.<br>
	 * Defines which alligator image should be drawn by the render() method. 
	 */
	@Override
	public void tick() {
		counter++;
		
		if(counter==time){
			counter=0;
			x=25f+82.2f*r.nextInt(5);
		}
		if(counter==0)
			anim=3;
		else if(counter==5)
			anim=0;
		else if(counter==10)
			anim=1;
		else if(counter==15)
			anim=2;
		else if(counter==255)
			anim=1;
		else if(counter==260)
			anim=0;
		else if(counter==265)
			anim=3;
	}
	
	/**
	 * Draws the river bank alligator on the screen according to its x, y position and the figure it is define by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.alligatorBank[anim],(int)x,(int)y,width,height,null);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isInTheSurface(){
		if(anim==2)
			return true;
		else
			return false;
	}
}