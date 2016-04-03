package dev.states;

import java.awt.Font;
import java.awt.Graphics;
import dev.game.*;

/**
 * This is the class that summarizes all the information about the states of the game.<br>
 * A "game state" is a single situation that the game might go to, like each menu, each phase of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public abstract class State {
	
	/*
	 * This class is an abstract class, that means it is not possible to instantiate it, just extending it using other subclasses
	 */
	
	//Here all the common variables to the game states are defined
	protected int counter; //Used to upgrade the state variables, when it is needed
	protected Font font = new Font("SansSerif",Font.BOLD,20); //Temporary variable used to draw menu items
	protected Game game;
	
	private static State currentState = null; //currentState - stores the current state that is being ticked and rendered on the screen
	private static boolean changeState = true; //changeState - variable used to make the transition between state more precise
	
	/**
	 * Creates a new instance of the game to each game state so the the state can rely on the game variables.
	 * @param game Instance of the game so that the game state can rely on the game variables.
	 */
	public State(Game game){
		this.game = game;
	}
	
	/**
	 * Upgrade the game state variables.
	 */
	public abstract void tick();
	
	/**
	 * Draw the game state on the screen.
	 * @param g Graphics object used to draw images on the screen.
	 */
	public abstract void render(Graphics g);

	/**
	 * Sets the current state of the game.
	 * @param state State that the current game state has to be set to.
	 */
	public static void setState(State state){
		if(changeState){
			currentState = state;
		}
	}
	
	/**
	 * Getter for the current game state.
	 * @return Current running game state.
	 */
	public static State getState(){
		return currentState;
	}
	
	/**
	 * Getter for the boolean variable used to allow or not the game to change its state.
	 * @return Boolean variable.
	 */
	public static boolean changeState() {
		return changeState;
	}
	
	/**
	 * Setter for the boolean variable used to allow or not the game to change its state.
	 * @param b Boolean value in which the variable will be set to.
	 */
	public static void setChangeState(boolean b) {
		changeState = b;
	}
}