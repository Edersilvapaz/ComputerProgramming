package collisiondetection;

import java.util.ArrayList;
import entities.*;

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
	public static boolean frogAndCars(Frog frog,ArrayList<Car> car){
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
	public static boolean frogAndTrucks(Frog frog,ArrayList<Truck> truck){
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
	public static boolean frogAndBuses(Frog frog,ArrayList<Bus> bus){
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
	public static boolean frogAndTaxis(Frog frog,ArrayList<Taxi> taxi){
		for(int x=0 ; x<taxi.size() ; x++){
			if(taxi.get(x)!=null){
				if(taxi.get(x).getBounds().intersects(frog.getBounds()))
					return true;
			}
		}
		return false;
	}

}