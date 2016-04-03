package dev.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import dev.entities.*;
import dev.game.Game;
import dev.linkedlists.*;

/**
 * This is the class that all the actual game information, everything that happens on the screen when the game is running is defined here.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class GameState extends State{
	
	//This variable is used to generate all the random values in the game
	private Random r = new Random();
	
	//Here all the game objects are defined 
	private Game game; //game instance to use game variables
	private Player player; //player instance
	private Vehicles vehicles; //linked lists which summarizes all the vehicles from the road
	private RiverItems riverItems; //linked lists which summarizes all the items from the river
	private AlligatorBank alligator; //instance of the alligator that stays on the river bank
	
	//These are the variables used to generate the items on the screen
	private int gen; //used to decide which kind of object within the linked list to create
	private int position; //used to decide the random position of each created object
	
	//This variables are used to manage the uploading the score 
	private boolean[] scorePermition = new boolean[11]; //Array used to decide when to upgrade the score
	private int[] playerPosition = new int[11]; //Array which stores all the y position the player needs to get to in order to increase score
	
	/**
	 * Creates a new instance of all objects and instantiate the game object passed to it.<br>
	 * Initialize the variables to record the player score correctly.
	 * @param game Game instance so that the game state can rely on the game variables.
	 */
	public GameState(Game game) {
		super(game);
		this.game=game;
		player = new Player(game);
		vehicles = new Vehicles(game);
		riverItems = new RiverItems(game);
		alligator = new AlligatorBank(game);
		game.setDefaultSpeed(1.5f);
		
		for(int i=0 ; i<playerPosition.length ; i++){
			playerPosition[i]=111+i*34;
			scorePermition[i]=true;
		}
	}
	
	/**
	 * Generates random and automatically all the objects that cross the screen according to time.<br>
	 * Keeps track of and updates the player score.<br>
	 * Calls the tick() methods of all the object of the game state.
	 */
	@Override
	public void tick() {
		
		counter++;
		
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
			if(player.getY()==playerPosition[i] && scorePermition[i]==true){
				player.setScore(player.getScore()+20);
				scorePermition[i]=false;
			}
		}
		
		//Ticking all the objects that the game state contains
		player.tick();
		alligator.tick();
		vehicles.tick();
		riverItems.tick();
	}
	
	/**
	 * Draws the player score on the screen.<br>
	 * Call the render() method of all objects in the game state.
	 */
	@Override
	public void render(Graphics g) {
		
		//Draw the player score
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Score: "+player.getScore(),10,game.getHeight()-20);
		
		//Render all the objects
		riverItems.render(g);
		alligator.render(g);
		player.render(g);
		vehicles.render(g);
	}
}