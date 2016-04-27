package states;

import java.awt.Color;
import java.awt.Graphics;
import collisiondetection.Collision;
import entities.AlligatorBank;
import game.Game;
import graphics.Assets;
import itemsgeneration.ItemGenerator;
import objectsarrays.Player;
import objectsarrays.RiverItems;
import objectsarrays.Vehicles;
import score.Score;

/**
 * This is the class that all the actual game information, everything that happens on the screen when the game is running is defined here.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Playing extends GameStates{
	
	//Here all the game objects are defined 
	private Player player; //player object
	private Vehicles vehicles; //linked lists which summarizes all the vehicles from the road
	private RiverItems riverItems; //linked lists which summarizes all the items from the river
	private AlligatorBank alligator; //instance of the alligator that stays on the river bank
	private Collision collisionDetector; //instance of the collision class
	private ItemGenerator itemsGenerator;
	
	//These are the variables used to generate the items on the screen
	private int timer; //used to implement the timer of each phase.
	private int frogIndex; //used to which between frogs during the game
	
	//these variable store the life and the score of the player
	private int life;
	private int score;
	
	//This variables are used to manage the uploading the score 
	private boolean[] scorePermition = new boolean[11]; //Array used to decide when to upgrade the score
	private int[] playerPosition = new int[11]; //Array which stores all the y position the player needs to get to in order to increase score
	
	/**
	 * Creates a new instance of all objects and instantiate the game object passed to it.<br>
	 * Initiate all the objects needed or the game.
	 * @param game Game instance so that the game state can rely on the game variables.
	 */
	public Playing(Game game) {
		super(game);
		this.game=game;
		player = new Player(game);		
		vehicles = new Vehicles();
		riverItems = new RiverItems();
		alligator = new AlligatorBank(game);
		collisionDetector = new Collision();
		itemsGenerator = new ItemGenerator(game);
		game.setDefaultSpeed(1.5f);
		
		levelBegin();
	}
	
	/**
	 * Keeps track of everything that can happen in the game.<br>
	 * Calls the tick() methods of all the object of the game state.
	 */
	@Override
	public void tick() {
		checkLives(); //check for the end of lives
		
		timer--;
		
		itemsGenerator.fase1(vehicles,riverItems);
		
		updateScore(); //check is the score needs to be updated
		
		checkTimer(); //check for the end of the game timer
		
		if(!player.getFrog(frogIndex).isDead())checkForCollisions(); //check for collisions in the game
		
		checkFrogPosition(); //check if the frog got to a river bank position
		
		//Ticking all the objects that the game state contains
		if(!player.getFrog(frogIndex).isDead()){
			itemsGenerator.tick();
			vehicles.tick();
			riverItems.tick();
			alligator.tick();
			if(timer<=7200)player.getFrog(frogIndex).tick();
		}else{
			player.getFrog(frogIndex).death();
		}
	}
	
	/**
	 * Draws the player score and life on the screen as well as the highest score already achieved and the timer bar.<br>
	 * Call the render() method of all objects in the game state.
	 */
	@Override
	public void render(Graphics g) {
		
		//Draw the player score
		g.setFont(playingFont);
		g.setColor(Color.WHITE);
		g.drawString("Score: "+score,2,game.getHeight()-28);
		
		//Draw the player life on the screen
		for(int x=0 ; x<life ; x++)
			g.drawImage(Assets.frog[0][0],3+30*x,game.getHeight()-23,20,20,null);
		
		//Draw the timer bar on the screen
		if(timer<600)
			g.setColor(Color.RED);
		else if(timer<3000)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.GREEN);
		
		if(timer<=7200)g.fillRect(100,game.getHeight()-20,(int)(timer/25.0f),15);
		
		//Draw the highest score obtained in the game
		g.setColor(Color.WHITE);
		g.drawString("Highest Score: "+Score.initials[0]+" - "+Score.score[0],135,game.getHeight()-28);
		
		//Render all the objects
		riverItems.render(g);
		alligator.render(g);
		vehicles.render(g);
		player.getFrog(frogIndex).render(g);
		
		for(int x=0 ; x<5 ; x++){
			if(player.getFrog(x).getY()==77)
				player.getFrog(x).render(g); //render just the frogs that are on the river bank
		}
		
		if(timer>7320)
			g.drawString("READY",game.getWidht()/2-30,game.getHeight()/2-10);
		else if(timer>7260)
			g.drawString("SET",game.getWidht()/2-17,game.getHeight()/2-10);
		else if(timer>7200)
			g.drawString("GO!!!!",game.getWidht()/2-17,game.getHeight()/2-10);
	}
	
	/**
	 * Starts the game.<br>
	 * Initialize the variables to record the player score correctly.<br>
	 * Initialize the timer, life and score variables.<br>
	 * Set the frogs to their initial position.<br>
	 * Clear all the objects that have already been created.
	 */
	public void levelBegin(){
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
		
		for(int x=0 ; x<5 ; x++)
			player.getFrog(x).goToInitialPosition();
		
		timer=7380;
		frogIndex=0;
		life=3;
		score=0;
		
		game.getKeyManager().resetInitials();
		
		vehicles.clear();
		riverItems.clear();		
	}
	
	/**
	 * Called every time a frog reaches a river bank,
	 * call the next frog to the game and resets the variables used for score tracking. 
	 */
	private void changeFrog(){
		int x;
		for(x=0 ; x<5 ; x++){
			if(player.getFrog(x).getY()!=77){
				frogIndex=x;
				break;
			}
		}
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
		
		//If every frog reached its river bank, start a new level
		if(x==5){
			levelBegin();
		}
	}
	
	/**
	 * Check the frog position when it gets to the river bank, if its position is within the range,<br>
	 * Move the frog to the river bank position and go to the next frog.
	 */
	private void checkFrogPosition(){
		//every time a frog gets to a bank, start to play with the next frog
		if(player.getFrog(frogIndex).getY()==77){
			if(Math.abs(player.getFrog(frogIndex).getX()-21.6f)<10){
				player.getFrog(frogIndex).goToPosition(21.6f,77);
				changeFrog();
			}else if(Math.abs(player.getFrog(frogIndex).getX()-103.79f)<10){
				player.getFrog(frogIndex).goToPosition(103.79f,77);
				changeFrog();
			}else if(Math.abs(player.getFrog(frogIndex).getX()-186)<10){
				player.getFrog(frogIndex).goToPosition(186,77);
				changeFrog();
			}else if(Math.abs(player.getFrog(frogIndex).getX()-268.3f)<10){
				player.getFrog(frogIndex).goToPosition(268.3f,77);
				changeFrog();
			}else if(Math.abs(player.getFrog(frogIndex).getX()-350.4f)<50){
				player.getFrog(frogIndex).goToPosition(350.4f,77);
				changeFrog();
			}
		}
	}
	
	/**
	 * Goes through all the positions that the frog can reach in the y axis.<br>
	 * If the frog is there and its respective boolean variable is equal to true<br>
	 * it updates the player score by 20 and sets the boolean variable to false so<br>
	 * that getting to that position again wont increase anything on the player's score.
	 */
	private void updateScore(){
		for(int i=0 ; i<playerPosition.length ; i++){
			if(player.getFrog(frogIndex).getY()==playerPosition[i] && scorePermition[i]==true){
				score+=5;
				scorePermition[i]=false;
			}
		}		
	}
	
	/**
	 * Check the timer of the game.
	 */
	private void checkTimer(){
		//If the time of the level is up, kill the player and go back to the beginning of the level
		if(timer==0){
			player.Death();
			life--;
			timer=7380;
			frogIndex=0;
		}
	}
	
	/**
	 * Check how many lives the player still have,<br>
	 * If they are take, take the game to the game over state.
	 */
	private void checkLives(){
		if(life==0 && !player.getFrog(frogIndex).isDead()){
			game.GameOverState().checkScore(score);
			GameStates.setGameStateTo(game.getGameOverState());
			GameStates.setChangeState(false);
		}else{
			GameStates.setChangeState(true);
		}
	}
	
	/**
	 * Checks all the collisions that might happen on the game and act accordingly to the collision that took place.
	 */
	private void checkForCollisions(){
		
		//Collision with vehicles already detected
		if(player.getFrog(frogIndex).isStopped()&&collisionDetector.frogAndVehicles(player.getFrog(frogIndex),vehicles)){
			life--;
			player.getFrog(frogIndex).gotHit();
		}
		
		//Test for collisions; is there is, get the index number of the object that is colliding with the frog
		int logIndex = collisionDetector.frogAndLogs(player.getFrog(frogIndex),riverItems.getLogs());
		int turtleIndex = collisionDetector.frogAndTurtles(player.getFrog(frogIndex),riverItems.getTurtles());
		int alligatorIndex = collisionDetector.frogAndAlligators(player.getFrog(frogIndex),riverItems.getAlligators());
		
		//if there is any collision with a river object, move the frog at the same speed of the object
		if(player.getFrog(frogIndex).isStopped() && player.getFrog(frogIndex).getY()<260){
			if(logIndex >=0){
				player.getFrog(frogIndex).setX(riverItems.getLogs().get(logIndex).getSpeed());
			}else if(turtleIndex >=0){
				player.getFrog(frogIndex).setX(riverItems.getTurtles().get(turtleIndex).getSpeed());	
			}else if(alligatorIndex >=0){
				player.getFrog(frogIndex).setX(riverItems.getAlligators().get(alligatorIndex).getSpeed());
			}else if(collisionDetector.frogAndAlligatorMouth(player.getFrog(frogIndex),riverItems.getAlligators())){
				life--;
				player.getFrog(frogIndex).eaten();
			}else{
				life--;
				player.getFrog(frogIndex).sank();
			}
		}
		
		//If there is a collision between a frog and a alligator in the bank,
		//take the frog that is in the bank to the initial position
		for(int x=0 ; x<5 ; x++){
			if(collisionDetector.frogAndAlligatorBank(player.getFrog(x),alligator)){
				player.getFrog(x).goToInitialPosition();
			}
		}
	}
	
	/**
	 * Getter for the player score.
	 * @return Player score value.
	 */
	public int getScore(){
		return score;
	}
}