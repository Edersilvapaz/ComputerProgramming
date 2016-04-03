package dev.linkedlists;

import java.awt.Graphics;
import java.util.LinkedList;
import dev.entities.*;
import dev.game.Game;

/**
 * This class holds the linked lists that helps the creation of the objects of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Vehicles {
	
	//Initiates the linked lists that will hold each of the vehicles objects
	private LinkedList<Car> car = new LinkedList<Car>();
	private LinkedList<Truck> truck = new LinkedList<Truck>();
	private LinkedList<Bus> bus = new LinkedList<Bus>();
	private LinkedList<Taxi> taxi = new LinkedList<Taxi>();
	
	//Create an instance of game so that the game variables can be used
	Game game;
	
	/**
	 * Makes a copy of the game object passed to it.
	 * @param game Instance of game so that it can rely on the game variables.
	 */
	public Vehicles(Game game){
		this.game=game;
	}
	
	/**
	 * Goes through every position available on the linked links and tests the position of the object on the screen.<br>
	 * If it is already completely out of the screen, the respective object is removed, is it is not, its tick() method is called
	 */
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
	
	/**
	 * Goes through every position available on the linked lists and call the objects render() method.
	 * @param g Graphics object used to draw images on the game window.
	 */
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
	
	/**
	 * Adds a new car the the car linked list.
	 * @param car Car object to be added to the list.
	 */
	public void addCar(Car car){
		this.car.add(car);
	}
	
	/**
	 * Adds a new truck to the truck linked list.
	 * @param truck Truck object to be added to the list.
	 */
	public void addTruck(Truck truck){
		this.truck.add(truck);
	}
	
	/**
	 * Adds a new bus to the bus linked list.
	 * @param bus Bus object to be added to the list.
	 */
	public void addBus(Bus bus){
		this.bus.add(bus);
	}
	
	/**
	 * Adds a new taxi to the taxi linked list.
	 * @param taxi Taxi object to be added to the list.
	 */
	public void addTaxi(Taxi taxi){
		this.taxi.add(taxi);
	}
	
	/**
	 * Removes a car from car linked list.
	 * @param car Car object to be removed from the linked list.
	 */
	public void removeCar(Car car){
		this.car.remove(car);
	}
	
	/**
	 * Removes a truck from the truck linked list.
	 * @param truck Truck object to be removed from the linked list.
	 */
	public void removeTruck(Truck truck){
		this.truck.remove(truck);
	}
	
	/**
	 * Removes a bus from the bus linked list.
	 * @param bus Bus object to be removed from the linked list.
	 */
	public void removeBus(Bus bus){
		this.bus.remove(bus);
	}
	
	/**
	 * Removes a taxi from the taxi linked list.
	 * @param taxi Taxi to be removed from the linked list.
	 */
	public void removeTaxi(Taxi taxi){
		this.taxi.remove(taxi);
	}
}