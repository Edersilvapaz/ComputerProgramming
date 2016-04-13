package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import collisiondetection.Collision;
import entities.*;
import game.Game;
import graphics.Assets;
import objectsarrays.*;
import score.Score;

/**
 * This is the class that all the actual game information, everything that happens on the screen when the game is running is defined here.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Playing extends GameStates{
	
	//This variable is used to generate all the random values in the game
	private Random r = new Random();
	
	//Here all the game objects are defined 
	private Player player; //player object
	private Vehicles vehicles; //linked lists which summarizes all the vehicles from the road
	private RiverItems riverItems; //linked lists which summarizes all the items from the river
	private AlligatorBank alligator; //instance of the alligator that stays on the river bank
	private Collision collisionDetector; //
	
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
		game.setDefaultSpeed(1.5f);
		
		levelBegin();
	}
	
	/**
	 * Generates random and automatically all the objects that cross the screen according to time.<br>
	 * Keeps track of and updates the player score.<br>
	 * Calls the tick() methods of all the object of the game state.
	 */
	@Override
	public void tick() {
		for(int x=0 ; x<counter.length ; x++)
			counter[x]++;
		
		timer--;
		
		generateRoadAndRiverObjects(); //Generate objects vehicles and river items, if necessary
		
		updateScore(); //check is the score needs to be updated
		
		checkTimer(); //check for the end of the game timer
		
		checkLives(); //check for the end of lives
		
		checkForCollisions(); //check for collisions in the game
		
		checkFrogPosition(); //check if the frog got to a river bank position
		
		//Ticking all the objects that the game state contains
		alligator.tick();
		if(timer<=7200)player.getFrog(frogIndex).tick();;
		vehicles.tick();
		riverItems.tick();
	}
	
	/**
	 * Draws the player score and life on the screen.<br>
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
		player.getFrog(frogIndex).render(g);
		for(int x=0 ; x<5 ; x++){
			if(player.getFrog(x).getY()==77)
				player.getFrog(x).render(g); //render just the frogs that are on the river bank
		}
		vehicles.render(g);
		
		if(timer>7320)
			g.drawString("READY",game.getWidht()/2-30,game.getHeight()/2-10);
		else if(timer>7260)
			g.drawString("SET",game.getWidht()/2-17,game.getHeight()/2-10);
		else if(timer>7200)
			g.drawString("GO!!!!",game.getWidht()/2-17,game.getHeight()/2-10);
	}
	
	/**
	 * Starts every different level of the game.<br>
	 * Initialize the variables to record the player score correctly.<br>
	 * Initialize the timer of the game.
	 */
	private void levelBegin(){
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
		
		for(int x=0 ; x<5 ; x++)
			player.getFrog(x).goToInitialPosition();
		
		timer=7380;
		frogIndex=0;
		life=3;
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
		
		//If every frog reached its river bank, start a new level
		if(x==5){
			levelBegin();
		}
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
	}
	
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
	 * 
	 */
	private void generateRoadAndRiverObjects(){		
		if(counter[0]==180){
			counter[0]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,0));
			else
				riverItems.addLog(new Log(game,0,r.nextInt(41)+100));
		}
		
		if(counter[1]==120){
			counter[1]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,1));
			else
				riverItems.addTurtle(new Turtle(game,1,r.nextInt(3)+2));
		}
		
		if(counter[2]==140){
			counter[2]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,2));
			else
				riverItems.addLog(new Log(game,2,r.nextInt(41)+100));
		}
		
		if(counter[3]==90){
			counter[3]=0;
			if(r.nextInt(4)==3)
				riverItems.addAlligator(new Alligator(game,3));
			else
				riverItems.addTurtle(new Turtle(game,3,r.nextInt(2)+2));
		}
		
		if(counter[4]==100){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+80));
		}
		
		if(counter[5]==120){
			counter[5]=0;
			vehicles.addCar(new Car(game,4));
		}
		
		if(counter[6]==160){
			counter[6]=0;
			vehicles.addTruck(new Truck(game,3));
		}
		
		if(counter[7]>=100){
			if(counter[7]==100){
				vehicles.addCar(new Car(game,2));
			}
			if(counter[7]==140){
				vehicles.addCar(new Car(game,2));
				counter[7]=0;
			}
		}
		
		if(counter[8]==150){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1));
		}
		
		if(counter[9]==160){
			counter[9]=0;
			vehicles.addTaxi(new Taxi(game,0));
		}
	}
	
	/**
	 * 
	 */
	private void updateScore(){
		/*
		 * This loop goes through all the position the frog can reach in the y axis
		 * If the frog is there and its respective boolean variable is equal to true
		 * it updates the player score by 20 and sets the boolean variable to false so
		 * that getting to that position again wont increase anything on the player's score
		 */
		for(int i=0 ; i<playerPosition.length ; i++){
			if(player.getFrog(frogIndex).getY()==playerPosition[i] && scorePermition[i]==true){
				score+=5;
				scorePermition[i]=false;
			}
		}		
	}
	
	/**
	 * 
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
	 * 
	 */
	private void checkLives(){
		if(life==0){
			levelBegin();
			game.GameOverState().checkScore(score);
			GameStates.setGameStateTo(game.getGameOverState());
			GameStates.setChangeState(false);
		}else{
			GameStates.setChangeState(true);
		}
	}
	/**
	 * 
	 */
	private void checkForCollisions(){
		
		//Collision with vehicles already detected
		if(player.getFrog(frogIndex).isStopped()&&collisionDetector.frogAndVehicles(player.getFrog(frogIndex),vehicles)){
			life--;
			player.getFrog(frogIndex).goToInitialPosition();
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
				player.getFrog(frogIndex).goToInitialPosition();
			}else{
				life--;
				player.getFrog(frogIndex).goToInitialPosition();
			}
		}
		
		//
		for(int x=0 ; x<5 ; x++){
			if(collisionDetector.frogAndAlligatorBank(player.getFrog(x),alligator)){
				player.getFrog(x).goToInitialPosition();
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getScore(){
		return score;
	}
}