package objectsarrays;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Fly;

public class Flies {
	
	ArrayList<Fly> flies;
	
	public Flies(){
		flies = new ArrayList<Fly>();
	}
	
	public void tick(){
		for(int x=0 ; x<flies.size() ; x++){
			flies.get(x).tick();
		}
	}
	
	public void render(Graphics g){
		for(int x=0 ; x<flies.size() ; x++){
			flies.get(x).render(g);
		}
	}
	
	public void addFly(Fly fly){
		flies.add(fly);
	}
	
	public void removeFly(Fly fly){
		flies.remove(fly);
	}
	
	public ArrayList<Fly> getFliesList(){
		return flies;
	}
	
	public void clear(){
		flies.clear();
	}
}