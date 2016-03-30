package src.dev.linkedlists;

/**
 * This class holds the linked lists that helps the creation of the objects of the game;
 * A linked list is an array the can be extended and shrunk according to necessity, that way, each vehicle object will make part of a linked list
 * so that we can create as much objects of an specific class as we want in the game;
 */

import java.awt.Graphics;
import java.util.LinkedList;
import src.dev.game.Game;
import src.dev.entities.*;

public class RiverItems {
	
	//Initiate the linked lists that will hold every river item on the game
	private LinkedList<Log> log = new LinkedList<Log>();
	private LinkedList<Turtle> turtle = new LinkedList<Turtle>();
	private LinkedList<Alligator> alligator = new LinkedList<Alligator>();
	
	//Create an instance of game so that the game variables can be used;
	Game game;
	
	//Constructor
	//Makes a copy of the game object passed to it;
	public RiverItems(Game game){
		this.game = game;
	}
	
	//The tick() method goes through every position available on the linked lists and tests the position of the object on the screen
	//If it is already completely out of the screen, the respective object is removed, if it is not, its tick() method is called
	public void tick(){
		for(int i = 0 ; i<log.size() ; i++ ){
			if(log.get(i).getX()>game.getWidht()+log.get(i).getWidth()+1 || log.get(i).getX()<-(log.get(i).getWidth()+1))
				removeLog(log.get(i));
			else
				log.get(i).tick();
		}
		
		for(int i=0 ; i<turtle.size() ; i++ ){
			if(turtle.get(i).getX()>game.getWidht()+turtle.get(i).getWidth()+1 || turtle.get(i).getX()<-(turtle.get(i).getWidth()+1))
				removeTurtle(turtle.get(i));
			else
				turtle.get(i).tick();
		}
		
		for(int i=0 ; i<alligator.size() ; i++ ){
			if(alligator.get(i).getX()>game.getWidht()+alligator.get(i).getX()+1 || alligator.get(i).getX()<-(alligator.get(i).getWidth()+1))
				removeAlligator(alligator.get(i));
			else
				alligator.get(i).tick();
		}
	}
	
	//The render method goes through every position available on the linked lists and call the objects render method;
	public void render(Graphics g){
		for(int i = 0 ; i<log.size() ; i++ )
			log.get(i).render(g);
		
		for(int i=0 ; i<turtle.size() ; i++ )
			turtle.get(i).render(g);
		
		for(int i=0 ; i<alligator.size() ; i++)
			alligator.get(i).render(g);
	}
	
	//This section holds the add() methods used to add objects to the linked lists
	
	public void addLog(Log log){
		this.log.add(log);
	}
	
	public void addTurtle(Turtle turtle){
		this.turtle.add(turtle);
	}
	
	public void addAlligator(Alligator alligator){
		this.alligator.add(alligator);
	}
	
	//This section is for the remove() methods used to remove objects from the linked lists
	
	public void removeLog(Log log){
		this.log.remove(log);
	}
	
	public void removeTurtle(Turtle turtle){
		this.turtle.remove(turtle);
	}
	
	public void removeAlligator(Alligator alligator){
		this.alligator.remove(alligator);
	}
}