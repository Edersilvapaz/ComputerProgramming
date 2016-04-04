package dev.linkedlists;

import java.awt.Graphics;
import java.util.ArrayList;
import dev.entities.Alligator;
import dev.entities.Log;
import dev.entities.Turtle;
import dev.game.Game;

/**
 * This class holds the linked lists that helps the creation of the objects of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class RiverItems {
	
	//Initiate the linked lists that will hold every river item on the game
	private ArrayList<Log> log = new ArrayList<Log>();
	private ArrayList<Turtle> turtle = new ArrayList<Turtle>();
	private ArrayList<Alligator> alligator = new ArrayList<Alligator>();
	
	//Create an instance of game so that the game variables can be used;
	Game game;
	
	/**
	 * Makes a copy of the game object passed to it.
	 * @param game Instance of game so that it can rely on the game variables.
	 */
	public RiverItems(Game game){
		this.game = game;
	}
	
	/**
	 * Goes through every position available on the linked links and tests the position of the object on the screen.<br>
	 * If it is already completely out of the screen, the respective object is removed, is it is not, its tick() method is called
	 */
	public void tick(){
		for(int i=0 ; i<log.size() ; i++ ){
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
	
	/**
	 * Goes through every position available on the linked lists and call the objects render() method.
	 * @param g Graphics object used to draw images on the game window.
	 */
	public void render(Graphics g){
		for(int i = 0 ; i<log.size() ; i++ )
			log.get(i).render(g);
		
		for(int i=0 ; i<turtle.size() ; i++ )
			turtle.get(i).render(g);
		
		for(int i=0 ; i<alligator.size() ; i++)
			alligator.get(i).render(g);
	}
	
	/**
	 * Adds a new log on the log linked list.
	 * @param log Log object to be added to the list.
	 */
	public void addLog(Log log){
		this.log.add(log);
	}
	
	/**
	 * Adds a new turtle line to the turtle linked list.
	 * @param turtle Turtle object to be added to the list.
	 */
	public void addTurtle(Turtle turtle){
		this.turtle.add(turtle);
	}
	
	/**
	 * Adds a new alligator to the alligator to the alligator linked list.
	 * @param alligator Alligator object to be added to the list.
	 */
	public void addAlligator(Alligator alligator){
		this.alligator.add(alligator);
	}
	
	/**
	 * Removes a log from the log linked list.
	 * @param log Log the be removed from the link.
	 */
	public void removeLog(Log log){
		this.log.remove(log);
	}
	
	/**
	 * Removes a turtle line from the turtle linked list.
	 * @param turtle Turtle list to be removed from the link.
	 */
	public void removeTurtle(Turtle turtle){
		this.turtle.remove(turtle);
	}
	
	/**
	 * Removes an alligator from the alligator linked list.
	 * @param alligator Alligator to be removed from the linked list.
	 */
	public void removeAlligator(Alligator alligator){
		this.alligator.remove(alligator);
	}
}