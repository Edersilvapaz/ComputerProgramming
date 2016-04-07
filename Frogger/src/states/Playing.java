package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
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
	private Game game; //game instance to use game variables
	private Player player;
	private Vehicles vehicles; //linked lists which summarizes all the vehicles from the road
	private RiverItems riverItems; //linked lists which summarizes all the items from the river
	private AlligatorBank alligator; //instance of the alligator that stays on the river bank
	
	//These are the variables used to generate the items on the screen
	private int gen; //used to decide which kind of object within the linked list to create
	private int position; //used to decide the random position of each created object
	private int timer; //used to implement the timer of each phase.
	private int playerSelect;
	
	//these variable store the life and the score of the player
	private int score;
	private int life;
	
	//This variables are used to manage the uploading the score 
	private boolean[] scorePermition = new boolean[11]; //Array used to decide when to upgrade the score
	private int[] playerPosition = new int[11]; //Array which stores all the y position the player needs to get to in order to increase score
	
	/**
	 * Creates a new instance of all objects and instantiate the game object passed to it.<br>
	 * Initialize the variables to record the player score correctly.<br>
	 * Initialize the timer of the game.
	 * @param game Game instance so that the game state can rely on the game variables.
	 */
	public Playing(Game game) {
		super(game);
		this.game=game;
		
		player = new Player();
		for(int x=0; x<5 ; x++)
			player.addFrog(new Frog(game));
		
		vehicles = new Vehicles();
		riverItems = new RiverItems();
		alligator = new AlligatorBank(game);
		game.setDefaultSpeed(1.5f);
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
		
		timer=3600;
		life=3;
		playerSelect=0;
	}
	
	/**
	 * Generates random and automatically all the objects that cross the screen according to time.<br>
	 * Keeps track of and updates the player score.<br>
	 * Calls the tick() methods of all the object of the game state.
	 */
	@Override
	public void tick() {
		timer--;
		counter++;
		
		//If the time of the level is up, kill the player and go back to the beginning of the level;
		if(timer==0){
			player.getFrog(playerSelect).Death();
			timer=3600;
		}
		
		/*
		 * Every time counter is equal to 30
		 * 		1.	Reset counter to 0
		 * 		2.	Generate a new random number between 0 and 3
		 * 		3.	Increase the position variable
		 * 		4.	According to the number generated, create the an object in the currently position value
		 * 		OBS.: The position variable keeps going from 0 to 4 so that in every iteration an object is created in a different line
		 */
		
		if(counter==30){
			counter=0;
			gen = r.nextInt(4);
			
			position++;
			if(position==5)
				position=0;
			
			if(gen==0 || gen==1)
				riverItems.addLog(new Log(game,position,r.nextInt(81)+50));
			else if(gen==2)
				riverItems.addTurtle(new Turtle(game,position,r.nextInt(2)+2));
			else
				riverItems.addAlligator(new Alligator(game,position));
			
			if(gen==0)
				vehicles.addCar(new Car(game,position));
			else if(gen==1)
				vehicles.addTruck(new Truck(game,position));
			else if(gen==2)
				vehicles.addBus(new Bus(game,position));
			else
				vehicles.addTaxi(new Taxi(game,position));
		}
		
		/*
		 * This loop goes through all the position the frog can reach in the y axis
		 * If the frog is there and its respective boolean variable is equal to true
		 * it updates the player score by 20 and sets the boolean variable to false so
		 * that getting to that position again wont increase anything on the player's score
		 */
		for(int i=0 ; i<playerPosition.length ; i++){
			if(player.getFrog(playerSelect).getY()==playerPosition[i] && scorePermition[i]==true){
				score+=20;
				scorePermition[i]=false;
			}
		}
		
		//Ticking all the objects that the game state contains
		alligator.tick();
		player.getFrog(playerSelect).tick();
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
		else if(timer<2400)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.GREEN);
		
		g.fillRect(100,game.getHeight()-20,(int)(timer/12.7f),15);
		
		//Draw the highest score obtained in the game
		g.setColor(Color.WHITE);
		g.drawString("Highest Score: "+Score.initials[0]+" - "+Score.score[0],135,game.getHeight()-28);
		
		//Render all the objects
		riverItems.render(g);
		alligator.render(g);
		player.getFrog(playerSelect).render(g);
		vehicles.render(g);
	}
}