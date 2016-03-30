package src.dev.entities;

/**
 * This is the class that defines the player object, i.e., the frog;
 */

import java.awt.Graphics;
import src.dev.game.Game;
import src.dev.graphics.Assets;

/*
 * This class extends the abstract class Entity, this means that every variable, object or method
 * that it contains can be used by this one, as long as it is defined as protected;
 */

public class Player extends Entity{
	
	//These boolean variables are used make the frog move on the screen;
	private boolean move = true;
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	//These variable define how many pixels the frog moves in each iteration;
	private int necessaryMovements=8;
	private float moveX = 27.40f/necessaryMovements;
	private float moveY = 34f/necessaryMovements;

	//these variable store the life and the score of the player
	private int score;
	private int life;
	
	//Constructor method:
	//The super method calls the constructor of the super class that this one is extending, in this case Entity;
	public Player(Game game) {
		//Player will start always at the middle bottom of the screen and will have a fixed size;
		super(game,(game.getWidht()-player_width)/2,game.getHeight()-75,player_width,player_height);
		counter=necessaryMovements; //here the counter is initiated to make sure it will not move in the beginning of the game;
	}

	@Override
	public void tick() {

		/*
		 * Every time a moving button is pressed and the counter is equal to 8:
		 * 		1.	The move boolean variable is set to false so the action on the keyboard will be detected only once;
		 * 		2.	If it if possible to move to the direction commanded, the respective other moving boolean variable is set to true;
		 * 			The counter is set to zero;
		 * 			The anim variable is set to draw the right image on the screen;
		 * 		3.	GO TO LINE 83 FOR THE REST OF DESCRIPTION;
		 * 		4.	When the key is released, the move boolean variable is set to true, allowing the program to act when a new key is pressed;
		 */
		
		if(game.getKeyManager().up||game.getKeyManager().down||game.getKeyManager().left||game.getKeyManager().right){
			if(move && counter==necessaryMovements){
				if(game.getKeyManager().up && y>80){
					moveUp=true;
					counter=0;
					anim=0;
				}
				if(game.getKeyManager().down && y<480){
					moveDown=true;
					counter=0;
					anim=1;
				}
				if(game.getKeyManager().left && x>25){
					moveLeft=true;
					counter=0;
					anim=2;
				}
				if(game.getKeyManager().right && x<340){
					moveRight=true;
					counter=0;
					anim=3;
				}
			}
			move = false;
		}else{
			move=true;
		}
		
		/*
		 * When one of the moving variables is true:
		 * 		3.1.	The x or y position is upgraded according to the direction needed and the amount defined by moveX or moveY;
		 * 		3.2.	The counter is increased;
		 * 		3.3.	When the counter is up to the value of necessary movements () defined by the necessaryMovements variable,
		 * 				The respective moving variable is set to false, ending the moving process;
		 * OBS.: the moving boolean variable are used to make sure that a movement will be initiate and finished without
		 * interference of another clicking on the keyboard;
		 */
		
		if(moveUp==true && counter!=necessaryMovements){
			y-=moveY;
			counter++;
		}else
			moveUp=false;
		
		if(moveDown==true && counter!=necessaryMovements){
			y+=moveY;
			counter++;
		}else
			moveDown = false;
		
		if(moveLeft==true && counter!=necessaryMovements){
			x-=moveX;
			counter++;
		}else
			moveLeft = false;
		
		if(moveRight==true && counter!=necessaryMovements){
			x+=moveX;
			counter++;
		}else
			moveRight = false;
		
		//These if statements use the counter variable and the image variable to animate the frog image;
		if(counter==2)image=1;
		if(counter==5)image=2;
		if(counter==8)image=0;
	}

	//The render method draws the car figure on the screen according the object's variables;
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.frog[anim][image],(int)x,(int)y,width,height,null);
	}
	
	//This section is for the getters and setters necessary to upgrade the player's life and score in the gameState method;
	
	public void setScore(int score){
		this.score=score;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setLife(int life){
		this.life=life;
	}
	
	public int getLife(){
		return life;
	}
}