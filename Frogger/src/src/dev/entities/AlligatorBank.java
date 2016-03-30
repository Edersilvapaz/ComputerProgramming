package src.dev.entities;

/**
 * This is the class that defines every alligator that shows up in the river bank;
 */

import java.awt.Graphics;
import src.dev.game.Game;
import src.dev.graphics.Assets;

/*
 * This class extends the abstract class Entity, this means that every variable, object or method
 * that it contains can be used by this one, as long as it is defined as protected;
 */

public class AlligatorBank extends Entity{
	
	//The alligator in the river banks will change its position while the game is running,
	//this variable is how many time the alligator stays in a respective bank;
	private final int time = 600;
	
	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
	public AlligatorBank(Game game) {
		//Every alligator in the bank will have the same size;
		//Its y will be fixed in the river bank height;
		//Its x position will be in one of the river banks, defined randomly by the r object and the expression on line 29;
		super(game,0,82,alliBank_width,alliBank_height);
		x=25f+82.2f*r.nextInt(5);
	}
	
	//The tick method uses the counter variable to give a new random x position to the entity
	//and upgrade the anim variable to animate the alligator figure on the screen;
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
		else if(counter==time-70)
			anim=1;
		else if(counter==time-65)
			anim=0;
		else if(counter==time-60)
			anim=3;
	}

	//The render method draws and animates the alligator figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.alligatorBank[anim],(int)x,(int)y,width,height,null);
	}
}