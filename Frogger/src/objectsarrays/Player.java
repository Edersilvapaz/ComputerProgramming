package objectsarrays;

import java.util.ArrayList;
import entities.Frog;
import game.Game;

/**
 * 
 * @author Eder
 */
public class Player {
	
	private ArrayList<Frog> frog = new ArrayList<Frog>();
	
	/**
	 * 
	 * @param game
	 */
	public Player(Game game){
		for(int x=0 ; x<5 ; x++)
			frog.add(new Frog(game));
	}
	
	/**
	 * 
	 */
	public void Death(){
		for(int x=0 ; x<frog.size() ; x++)
			frog.get(x).goToInitialPosition();
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Frog getFrog(int index){
		return frog.get(index);
	}
}