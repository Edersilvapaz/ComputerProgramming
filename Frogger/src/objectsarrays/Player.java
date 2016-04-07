package objectsarrays;

import java.util.ArrayList;
import entities.Frog;

public class Player {
	
	private ArrayList<Frog> frog = new ArrayList<Frog>();
	
	public Frog getFrog(int index){
		return frog.get(index);
	}
	
	public void addFrog(Frog frog){
		this.frog.add(frog);
	}
}