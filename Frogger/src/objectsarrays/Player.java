package objectsarrays;

import java.util.ArrayList;
import entities.Frog;
import game.Game;

/**
 * This is a class setting up arrays for the player in the game.
 * @author Eder
 */
public class Player {
	
	private ArrayList<Frog> frog = new ArrayList<Frog>();
	
	/**
	 * This method allows the player to start a new game with a new frog.
	 * @param game
	 */
	public Player(Game game){
		for(int x=0 ; x<5 ; x++)
			frog.add(new Frog(game));
	}
	
	/**
	 * Sets the frog in the initial position after death.
	 */
	public void Death(){
		for(int x=0 ; x<frog.size() ; x++)
			frog.get(x).goToInitialPosition();
	}
	
	/**
	 * This gets the frog before the game starts or after death.
	 * @param index
	 * @return
	 */
	public Frog getFrog(int index){
		return frog.get(index);
	}
}