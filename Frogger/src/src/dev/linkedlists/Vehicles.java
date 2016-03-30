package src.dev.linkedlists;

/**
 * This class holds the linked lists that helps the creation of the objects of the game;
 * A linked list is an array the can be extended and shrunk according to necessity, that way, each vehicle object will make part of a linked list
 * so that we can create as much objects of an specific class as we want in the game;
 */

import java.awt.Graphics;
import java.util.LinkedList;
import src.dev.entities.*;
import src.dev.game.Game;

public class Vehicles {
	
	//Initiates the linked lists that will hold each of the vehicles objects
	private LinkedList<Car> car = new LinkedList<Car>();
	private LinkedList<Truck> truck = new LinkedList<Truck>();
	private LinkedList<Bus> bus = new LinkedList<Bus>();
	private LinkedList<Taxi> taxi = new LinkedList<Taxi>();
	
	//Create an instance of game so that the game variables can be used;
	Game game;
	
	//Constructor
	//Makes a copy of the game object passed to it;
	public Vehicles(Game game){
		this.game=game;
	}
	
	//The tick() method goes through every position available on the linked lists and tests the position of the object on the screen
	//If it is already completely out of the screen, the respective object is removed, if it is not, its tick() method is called
	public void tick(){
		for(int i=0 ; i<car.size() ; i++ ){
			if(	car.get(i).getX()>game.getWidht()+Entity.car_width+1 || car.get(i).getX()<-(Entity.car_width+1) )
				removeCar(car.get(i));
			else
				car.get(i).tick();
		}
		
		for(int i=0 ; i<truck.size() ; i++ ){
			if(truck.get(i).getX()>game.getWidht()+Entity.truck_width+1||truck.get(i).getX()<-(Entity.truck_width+1))
				removeTruck(truck.get(i));
			else
				truck.get(i).tick();
		}
		
		for(int i=0 ; i<bus.size() ; i++ ){
			if(bus.get(i).getX()>game.getWidht()+Entity.truck_width+1||bus.get(i).getX()<-(Entity.truck_width+1))
				removeBus(bus.get(i));
			else
				bus.get(i).tick();
		}
		
		for(int i=0; i<taxi.size() ; i++){
			if(taxi.get(i).getX()>game.getWidht()+Entity.car_width+1||taxi.get(i).getX()<-(Entity.car_width+1))
				removeTaxi(taxi.get(i));
			else
				taxi.get(i).tick();
		}
	}
	
	//The render method goes through every position available on the linked lists and call the objects render method;
	public void render(Graphics g){
		for(int i=0 ; i<car.size() ; i++ )
			car.get(i).render(g);
		
		for(int i=0 ; i<truck.size() ; i++ )
			truck.get(i).render(g);
		
		for(int i=0 ; i<bus.size() ; i++ )
			bus.get(i).render(g);
		
		for(int i=0 ; i<taxi.size() ; i++ )
			taxi.get(i).render(g);
	}
	
	//This section holds the add() methods used to add objects to the linked lists
	
	public void addCar(Car car){
		this.car.add(car);
	}
	
	public void addTruck(Truck truck){
		this.truck.add(truck);
	}
	
	public void addBus(Bus bus){
		this.bus.add(bus);
	}
	
	public void addTaxi(Taxi taxi){
		this.taxi.add(taxi);
	}
	
	//This section is for the remove() methods used to remove objects from the linked lists
	
	public void removeCar(Car car){
		this.car.remove(car);
	}
	
	public void removeTruck(Truck truck){
		this.truck.remove(truck);
	}
	
	public void removeBus(Bus bus){
		this.bus.remove(bus);
	}
	
	public void removeTaxi(Taxi taxi){
		this.taxi.remove(taxi);
	}
}