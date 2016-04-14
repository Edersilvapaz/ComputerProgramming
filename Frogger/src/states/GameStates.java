package states;

import java.awt.Font;
import java.awt.Graphics;

import game.*;

/**
 * This is the class that summarizes all the information about the states of the game.<br>
 * A "game state" is a single situation that the game might go to, like each menu, each phase of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public abstract class GameStates {
	
	/*
	 * This class is an abstract class, that means it is not possible to instantiate it, just extending it using other subclasses
	 */
	
	//Here all the common variables to the game states are defined
	protected int[] counter = new int[10]; //Used to upgrade the state variables, when it is needed
	protected Font playingFont = new Font("SansSerif",Font.BOLD,18); //Temporary variable used to draw menu items
	protected Font menuFont = new Font("SansSerif",Font.BOLD,25);
	protected Game game;
	
	private static GameStates currentState = null; //stores the current state that is being ticked and rendered on the screen
	private static GameStates lastState = null; //stores the last state used in the game
	private static boolean changeState = true; //variable used to make the transition between state more precise
	
	/**
	 * Creates a new instance of the game to each game state so the the state can rely on the game variables.
	 * @param game Instance of the game so that the game state can rely on the game variables.
	 */
	public GameStates(Game game){
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
	public static void setGameStateTo(GameStates state){
		if(changeState){
			lastState = currentState;
			currentState = state;
		}
	}
	
	/**
	 * This brings the player to the screen that was the last state of the player in the game.
	 */
	public static void backToLastState(){
		currentState = lastState;
	}
	
	/**
	 * Getter for the current game state.
	 * @return Current running game state.
	 */
	public static GameStates getState(){
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