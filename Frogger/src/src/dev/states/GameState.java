package src.dev.states;

/**
 * This is the class that all the actual game information, everything that happens on the screen when the game is running is defined here
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import src.dev.entities.*;
import src.dev.game.Game;
import src.dev.linkedlists.*;

/*
 * This class extends the abstract class State, this means that every variable, object or method
 * that it contains can be used by this one, as long as it is defined as protected;
 */

public class GameState extends State{
	
	//This variable is used to generate all the random values in the game
	private Random r = new Random();
	
	//Here all the game objects are defined 
	private Game game;					//game instance to use game variables;
	private Player player;				//player instance;
	private Vehicles vehicles;			//linked lists which summarizes all the vehicles from the road;
	private RiverItems riverItems;		//linked lists which summarizes all the items from the river;
	private AlligatorBank alligator;	//instance of the alligator that stays on the river bank;
	
	//These are the variables used to generate the items on the screen
	private int gen;			//used to decide which kind of object within the linked list to create
	private int vehiclePos;		//used to decide the random position of each created object
	
	//This variables are used to manage the uploading the score 
	private boolean[] posPerm = new boolean[11];		//Array used to decide when to upgrade the score
	private int[] pos = new int[11];					//Array which stores all the y position the player needs to get to in order to increase score
	
	//Constructor of the GameState;
	//It creates a new instance of all objects an instantiate the game object passed to it;
	//It also initializes the posPerm variables to true and store in the pos array the positions to be achieved;
	public GameState(Game game) {
		super(game);
		this.game=game;
		player = new Player(game);
		vehicles = new Vehicles(game);
		riverItems = new RiverItems(game);
		alligator = new AlligatorBank(game);
		game.setDefaultSpeed(1.5f);
		
		for(int i=0 ; i<pos.length ; i++){
			pos[i]=111+i*34;
			posPerm[i]=true;
		}
	}
	
	//The tick() method is responsible for generating the objects that cross the screen and identifying when to upgrade the player's score;
	@Override
	public void tick() {
		
		counter++;
		
		/*
		 * Every time counter is equal to 30:
		 * 		1.	Reset counter to 0;
		 * 		2.	Generate a new random number between 0 and 3;
		 * 		3.	Increase the position variable
		 * 		4.	According to the number generated, create the an object in the currently position value
		 * 		OBS.: The position variable keeps going from 0 to 4 so that in every iteration an object is created in a different line
		 */
		
		if(counter==30){
			counter=0;
			gen = r.nextInt(4);
			
			vehiclePos++;
			if(vehiclePos==5)
				vehiclePos=0;
			
			if(gen==0 || gen==1)
				riverItems.addLog(new Log(game,vehiclePos,r.nextInt(81)+50));
			else if(gen==2)
				riverItems.addTurtle(new Turtle(game,vehiclePos,r.nextInt(2)+2));
			else
				riverItems.addAlligator(new Alligator(game,vehiclePos));
			
			if(gen==0)
				vehicles.addCar(new Car(game,vehiclePos));
			else if(gen==1)
				vehicles.addTruck(new Truck(game,vehiclePos));
			else if(gen==2)
				vehicles.addBus(new Bus(game,vehiclePos));
			else
				vehicles.addTaxi(new Taxi(game,vehiclePos));
		}
		
		/*
		 * This loop goes through all the position the frog can reach in the y axis;
		 * If the frog is there and its respective boolean variable is equal to true,
		 * it updates the player score by 20 and sets the boolean variable to false so
		 * that getting to that position again wont increase anything on the project;
		 */
		for(int i=0 ; i<pos.length ; i++){
			if(player.getY()==pos[i] && posPerm[i]==true){
				player.setScore(player.getScore()+20);
				posPerm[i]=false;
			}
		}
		
		//Ticking all the objects that the game state contains
		player.tick();
		alligator.tick();
		vehicles.tick();
		riverItems.tick();
	}
	
	//The render method is responsible for drawing the player score on the screen and rendering all the object from the game state
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