package collisiondetection;

import java.util.ArrayList;
import entities.*;
import objectsarrays.*;

/**
 * This is the class that holds all the methods for collision detection using rectangles objects
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Collision {
	
	/**
	 * Checks whether the frog collides with a vehicle.
	 * @param frog current frog the player is using
	 * @param vehicles List of all vehicles on the screen
	 * @return True if there is a collision, false if there is not
	 */
	public boolean frogAndVehicles(Frog frog,Vehicles vehicles){
		if(frogAndCars(frog,vehicles.getCars())) //Check for collisions with cars
			return true;
		if(frogAndTrucks(frog,vehicles.getTrucks())) //check for collisions with trucks
			return true;
		if(frogAndBuses(frog,vehicles.getBuses())) //check for collisions with buses
			return true;
		if(frogAndTaxis(frog,vehicles.getTaxis())) //check for collision with taxis
			return true;
		return false;
	}
	
	/**
	 * Checks whether or not the frog lands on the alligators mouth and gets eaten.
	 * @param frog Current frog that the player is playing with
	 * @param alligator List of alligators of the game
	 * @return True is there is a collision, false if there is not
	 */
	public boolean frogAndAlligatorMouth(Frog frog,ArrayList<Alligator> alligator){
		for(int x=0 ; x<alligator.size() ; x++){
			if(alligator.get(x)!=null){
				if(alligator.get(x).getHeadBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is a collision between a frog and the alligator in the river banks.
	 * @param frog Current frog that the player is playing with
	 * @param alligator Alligator on the river bank
	 * @return True is there is a collision, false if there is not
	 */
	public boolean frogAndAlligatorBank(Frog frog,AlligatorBank alligator){
		if(alligator.isInTheSurface())
			if(alligator.getBounds().intersects(frog.getBounds()))
					return true;
		return false;
	}
	
	/**
	 * Checks if there is a collision between a frog and the logs on the river.
	 * @param frog Current frog that the player is playing with
	 * @param log List of logs of the game
	 * @return If there is a collision, return the index of the log that the frog is colliding with
	 */
	public int frogAndLogs(Frog frog,ArrayList<Log> log){
		for(int x=0 ; x<log.size() ; x++){
			if(log.get(x)!=null){
				if(log.get(x).getBounds().intersects(frog.getBounds())){
					return x;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Checks if there is a collision between a frog and the turtles on the river.
	 * @param frog Current frog that the player is playing with
	 * @param turtle List of turtles of the game
	 * @return If there is a collision, return the index of the turtles that the frog is colliding with
	 */
	public int frogAndTurtles(Frog frog,ArrayList<Turtle> turtle){
		for(int x=0 ; x<turtle.size() ; x++){
			if(turtle.get(x)!=null){
				if(turtle.get(x).getBounds().intersects(frog.getBounds())){
					return x;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Checks if there is a collision between a frog and the alligator's back.
	 * @param frog Current frog that the player is plying with
	 * @param alligator List of alligators of the game
	 * @return If there is a collision, return the index of the alligator that the frog is colliding with
	 */
	public int frogAndAlligators(Frog frog,ArrayList<Alligator> alligator){
		for(int x=0 ; x<alligator.size() ; x++){
			if(alligator.get(x)!=null){
				if(alligator.get(x).getBounds().intersects(frog.getBounds())){
					return x;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Checks if there is a collision between a frog and the game cars.
	 * @param frog Current frog that the player is playing with
	 * @param car List of cars of the game
	 * @return True of there is a collision with any of the cars, false if there is not
	 */
	private boolean frogAndCars(Frog frog,ArrayList<Car> car){
		for(int x=0 ; x<car.size() ; x++){
			if(car.get(x)!=null){
				if(car.get(x).getBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is a collision between a frog and the game trucks.
	 * @param frog Current frog that the player is playing with
	 * @param truck List of trucks of the game
	 * @return True if there is a collision with any of the trucks, false if there is not
	 */
	private boolean frogAndTrucks(Frog frog,ArrayList<Truck> truck){
		for(int x=0 ; x<truck.size() ; x++){
			if(truck.get(x)!=null){
				if(truck.get(x).getBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is a collision between a frog and the game buses.
	 * @param frog Current frog that the player is playing with
	 * @param bus List of buses of the game
	 * @return True if there is a collision with and of the buses, false if there is not
	 */
	private boolean frogAndBuses(Frog frog,ArrayList<Bus> bus){
		for(int x=0 ; x<bus.size() ; x++){
			if(bus.get(x)!=null){
				if(bus.get(x).getBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is a collision between a frog and the game taxis.
	 * @param frog Current frog that the player is playing with
	 * @param taxi List of taxis of the game
	 * @return True if there is a collision with and of the taxis, false if there is not
	 */
	private boolean frogAndTaxis(Frog frog,ArrayList<Taxi> taxi){
		for(int x=0 ; x<taxi.size() ; x++){
			if(taxi.get(x)!=null){
				if(taxi.get(x).getBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}
}