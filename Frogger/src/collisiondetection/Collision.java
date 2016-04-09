package collisiondetection;

import java.util.ArrayList;
import entities.*;
import objectsarrays.*;

/**
 * 
 * @author Eder
 *
 */
public class Collision {
	
	/**
	 * 
	 * @param frog
	 * @param vehicles
	 * @return
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
	 * 
	 * @param frog
	 * @param log
	 * @return
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
	 * 
	 * @param frog
	 * @param turtle
	 * @return
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
	 * 
	 * @param frog
	 * @param alligator
	 * @return
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
	 * 
	 * @param frog
	 * @param vehicles
	 * @return
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
	 * 
	 * @param frog
	 * @param truck
	 * @return
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
	 * 
	 * @param frog
	 * @param bus
	 * @return
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
	 * 
	 * @param frog
	 * @param taxi
	 * @return
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