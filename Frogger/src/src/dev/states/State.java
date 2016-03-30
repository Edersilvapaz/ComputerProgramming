package src.dev.states;

/**
 * This is the class that summarizes all the information about the states of the game;
 * A "game state" is a single situation that the game might go to, like each menu, each phase of the game;
 */

import java.awt.Font;
import java.awt.Graphics;
import src.dev.game.*;

/*
 * This class is an abstract class, that means it is not possible to instantiate it, just extending it using other subclasses;
 */

public abstract class State {
		
	//Here all the common variables to the game states are defined;
	//counter - used to upgrade the state variables, when it is needed;
	//font - temporary variable used to draw menu items;
	//currentState - stores the current state that is being ticked and rendered on the screen;
	//changeState - variable used to make the transition between state more precise;
	protected int counter;
	protected Font font = new Font("SansSerif",Font.BOLD,20);
	protected Game game;
	
	private static State currentState = null;
	private static boolean changeState = true;
	
	//Constructor of the game, is just creates a new instance of the game to each state so that the game variable can be used
	public State(Game game){
		this.game = game;
	}
	
	//This section is for the tick() and render() methods, there is nothing here because every game state overrides these methods
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	
	//This method is used to make the transitions between the states;
	public static void setState(State state){
		currentState = state;
	}
	
	//This method return the state that is being ticked and rendered at the moment;
	public static State getState(){
		return currentState;
	}
	
	//This method is used to focus on a state other than the one that is currently being ticked and rendered at the moment;
	public static State getState(State state){
		return state;
	}
	
	//These methods are the getter and setter for the changeState boolean variable
	
	public static boolean changeState() {
		return changeState;
	}

	public static void setChangeState(boolean b) {
		changeState = b;
	}
}