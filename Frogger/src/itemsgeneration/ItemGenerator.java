package itemsgeneration;

import java.util.Random;
import entities.Alligator;
import entities.Bus;
import entities.Car;
import entities.Log;
import entities.Taxi;
import entities.Truck;
import entities.Turtle;
import game.Game;
import objectsarrays.RiverItems;
import objectsarrays.Vehicles;

public class ItemGenerator {
	
	private int[] counter = new int[10];
	private Random r = new Random();
	private Game game;
	
	public ItemGenerator(Game game){
		this.game=game;
	}
	
	public void tick(){
		for(int x=0 ; x<counter.length ; x++)
			counter[x]++;
	}
	
	public void fase1(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==180){
			counter[0]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,0));
			else
				riverItems.addLog(new Log(game,0,r.nextInt(41)+100));
		}
		
		if(counter[1]==120){
			counter[1]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,1));
			else
				riverItems.addTurtle(new Turtle(game,1,r.nextInt(3)+2));
		}
		
		if(counter[2]==140){
			counter[2]=0;
			if(r.nextInt(7)==3)
				riverItems.addAlligator(new Alligator(game,2));
			else
				riverItems.addLog(new Log(game,2,r.nextInt(41)+100));
		}
		
		if(counter[3]==90){
			counter[3]=0;
			if(r.nextInt(4)==3)
				riverItems.addAlligator(new Alligator(game,3));
			else
				riverItems.addTurtle(new Turtle(game,3,r.nextInt(2)+2));
		}
		
		if(counter[4]==100){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+80));
		}
		
		if(counter[5]==120){
			counter[5]=0;
			vehicles.addCar(new Car(game,4));
		}
		
		if(counter[6]==160){
			counter[6]=0;
			vehicles.addTruck(new Truck(game,3));
		}
		
		if(counter[7]>=100){
			if(counter[7]==100){
				vehicles.addCar(new Car(game,2));
			}
			if(counter[7]==140){
				vehicles.addCar(new Car(game,2));
				counter[7]=0;
			}
		}
		
		if(counter[8]==150){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1));
		}
		
		if(counter[9]==160){
			counter[9]=0;
			vehicles.addTaxi(new Taxi(game,0));
		}
	}
}