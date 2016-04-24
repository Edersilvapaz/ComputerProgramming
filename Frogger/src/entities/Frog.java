package entities;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;

/**
 * This is the class that defines the frogs that are used by the player.<br>
 * It extends the abstract class entity.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Frog extends Entity{
	
	private boolean death;
	private boolean sank = false;
	private boolean gotHit = false;
	
	//These boolean variables are used make the frog move on the screen
	private boolean move = false;
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	//These variable define how many pixels the frog moves in each tick() method call
	private int necessaryMovements=8;
	private float moveX = 27.40f/necessaryMovements;
	private float moveY = 34f/necessaryMovements;
	
	/**
	 * Defines the player size, initial position on the screen and initial life amount.
	 * @param game Instance of game so that the player can rely on the game variables.
	 */
	public Frog(Game game) {
		//Player will start always at the middle bottom of the screen and will have a fixed size
		super(game,(game.getWidht()-player_width)/2,game.getHeight()-75,player_width,player_height);
		counter=necessaryMovements; //here the counter is initiated to make sure it will not move in the beginning of the game
		//setting the rectangle variables used to collision detection
		bounds.x=6;
		bounds.y=6;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}
	
	/**
	 * Checks if one of the keys used to move the player have been pressed and move the player according to it.<br>
	 * Defines which frog image has to be drawn by the render() method the movement done as well.
	 */
	@Override
	public void tick() {
		
		death = gotHit || sank;
		
		/*
		 * Every time a moving button is pressed and the counter is equal to 8:
		 * 		1.	The move boolean variable is set to false so the action on the keyboard will be detected only once
		 * 		2.	If it if possible to move to the direction commanded, the respective other moving boolean variable is set to true
		 * 			The counter is set to zero
		 * 			The anim variable is set to draw the right image on the screen
		 * 		3.	GO TO LINE 89 FOR THE REST OF DESCRIPTION
		 * 		4.	When the key is released, the move boolean variable is set to true, allowing the program to act when a new key is pressed
		 */
		
		if(game.getKeyManager().Up()||game.getKeyManager().Down()||game.getKeyManager().Left()||game.getKeyManager().Right() ){
			if(move && counter==necessaryMovements){
				if( game.getKeyManager().Up() && y>80 ){
					moveUp=true;
					counter=0;
					anim=0;
				}
				else if( game.getKeyManager().Down() && y<480 ){
					moveDown=true;
					counter=0;
					anim=1;
				}
				else if( game.getKeyManager().Left() && x>25 ){
					moveLeft=true;
					counter=0;
					anim=2;
				}
				else if( game.getKeyManager().Right() && x<340){
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
		 * 		3.1.	The x or y position is upgraded according to the direction needed and the amount defined by moveX or moveY
		 * 		3.2.	The counter is increased
		 * 		3.3.	When the counter is up to the value of necessary movements () defined by the necessaryMovements variable
		 * 				The respective moving variable is set to false, ending the moving process
		 * OBS.: the moving boolean variable are used to make sure that a movement will be initiate and finished without
		 * interference of another clicking on the keyboard
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
		
		//These if statements use the counter variable and the image variable to animate the frog image
		if(counter==2)image=1;
		if(counter==5)image=2;
		if(counter==8)image=0;
	}

	/**
	 * Draws the player on the screen according to its x and y position and the respective image according<br>
	 * to it movement defined by the tick() method.
	 */
	@Override
	public void render(Graphics g) {
		if(!death){
			g.drawImage(Assets.frog[anim][image],(int)x,(int)y,width,height,null);
		}else{
			g.drawImage(Assets.frogDeath[0],(int)x,(int)y,width,height,null);
		}
	}
	
	/**
	 * Define what happens when the player dies, either by being hit by a vehicle, sinking in<br>
	 * the river or time's up.
	 */
	public void goToInitialPosition(){
		x = (game.getWidht()-player_width)/2;
		y = game.getHeight()-75;
		anim=0;
	}
	
	/**
	 * Takes the frog objects to the position define by the parameters x and y.
	 * @param x X position
	 * @param y Y position
	 */
	public void goToPosition(float x,float y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Test if the frog is in movement.
	 * @return True if the frog is moving, false if it is not
	 */
	public boolean isStopped(){
		return !moveUp&&!moveDown;
	}
	
	public boolean isDead(){
		return death;
	}
	
	public void gotHit(){
		gotHit=true;
	}
	
	public void sank(){
		sank=true;
	}
	
	/**
	 * Moves the frog according to the speed passed as a parameter.
	 * @param speed Speed in which the frog should move
	 */
	public void setX(float speed){
		x+=speed;
	}
}