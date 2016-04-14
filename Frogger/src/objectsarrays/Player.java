package objectsarrays;

import java.util.ArrayList;
import entities.Frog;
import game.Game;

/**
 * This is a class sets up the frogs array for the player in the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Player {
	
	private ArrayList<Frog> frog = new ArrayList<Frog>();
	
	/**
	 * Adds five frog objects to the player's list of frogs.
	 * @param game Game instance so that the player can rely on the game's variables
	 */
	public Player(Game game){
		for(int x=0 ; x<5 ; x++)
			frog.add(new Frog(game));
	}
	
	/**
	 * Sets all the player frogs to the initial position.
	 */
	public void Death(){
		for(int x=0 ; x<frog.size() ; x++)
			frog.get(x).goToInitialPosition();
	}
	
	/**
	 * Getter for an specific frog in the frog list.
	 * @param index Index of the frog in the list to be returned
	 * @return Frog located on the index passed as a parameter
	 */
	public Frog getFrog(int index){
		return frog.get(index);
	}
}