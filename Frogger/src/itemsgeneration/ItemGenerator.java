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
	
	public void resetCounters(){
		for(int x=0 ; x<counter.length ; x++)
			counter[x]=0;
	}
	
	public void tick(){
		for(int x=0 ; x<counter.length ; x++)
			counter[x]++;
	}
	
	public void fase1(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==100){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,80,0.2f));
		}
		
		if(counter[1]==250){
			counter[1]=0;
			riverItems.addLog(new Log(game,1,r.nextInt(41)+120,0.2f));
		}
		
		if(counter[2]==300){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,0.1f));
		}
		
		if(counter[3]==200){
			counter[3]=0;
			riverItems.addLog(new Log(game,3,r.nextInt(51)+80,0.0f));
		}
		
		if(counter[4]==250){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,0.0f));
		}
		
		if(counter[5]==220){
			counter[5]=0;
			vehicles.addCar(new Car(game,4,0.0f));
		}
		
		if(counter[6]==200){
			counter[6]=0;
			vehicles.addBus(new Bus(game,3,0.1f));
		}
		
		if(counter[7]>=200){
			if(counter[7]==200){
				vehicles.addCar(new Car(game,2,0.0f));
			}
			if(counter[7]==245){
				vehicles.addCar(new Car(game,2,0.0f));
				counter[7]=0;
			}
		}
		
		if(counter[8]==220){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1,0.0f));
		}
		
		if(counter[9]>=200){
			if(counter[9]==200){
				vehicles.addCar(new Car(game,0,0.1f));
			}
			if(counter[9]==260){
				vehicles.addCar(new Car(game,0,0.1f));
				counter[9]=0;
			}
		}
	}
	
	public void fase2(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==100){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,80,0.2f));
		}
		
		if(counter[1]==250){
			counter[1]=0;
			riverItems.addTurtle(new Turtle(game,1,r.nextInt(3)+3,0.2f));
		}
		
		if(counter[2]==300){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,0.1f));
		}
		
		if(counter[3]==200){
			counter[3]=0;
			riverItems.addTurtle(new Turtle(game,3,r.nextInt(3)+3,0.3f));
		}
		
		if(counter[4]==250){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,0.0f));
		}
		
		if(counter[5]==220){
			counter[5]=0;
			vehicles.addCar(new Car(game,4,0.2f));
		}
		
		if(counter[6]==200){
			counter[6]=0;
			vehicles.addBus(new Bus(game,3,0.3f));
		}
		
		if(counter[7]>=200){
			if(counter[7]==200){
				vehicles.addTaxi(new Taxi(game,2,0.7f));
			}
			if(counter[7]==245){
				vehicles.addTaxi(new Taxi(game,2,0.7f));
				counter[7]=0;
			}
		}
		
		if(counter[8]==220){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1,0.0f));
		}
		
		if(counter[9]>=200){
			if(counter[9]==200){
				vehicles.addCar(new Car(game,0,0.1f));
			}
			if(counter[9]==260){
				vehicles.addCar(new Car(game,0,0.1f));
				counter[9]=0;
			}
		}
	}
	
	public void fase3(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==80){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,100,1.2f));
		}
		
		if(counter[1]==130){
			counter[1]=0;
			riverItems.addTurtle(new Turtle(game,1,r.nextInt(2)+2,1.0f));
		}
		
		if(counter[2]==170){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,0.9f));
		}
		
		if(counter[3]==120){
			counter[3]=0;
			riverItems.addTurtle(new Turtle(game,3,r.nextInt(2)+2,0.3f));
		}
		
		if(counter[4]==190){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,0.7f));
		}
		
		if(counter[5]==140){
			counter[5]=0;
			vehicles.addTruck(new Truck(game,4,0.9f));
		}
		
		if(counter[6]==170){
			counter[6]=0;
			vehicles.addBus(new Bus(game,3,0.5f));
		}
		
		if(counter[7]>=130){
			if(counter[7]==200){
				vehicles.addTaxi(new Taxi(game,2,0.9f));
			}
			if(counter[7]==245){
				vehicles.addTaxi(new Taxi(game,2,0.9f));
				counter[7]=0;
			}
		}
		
		if(counter[8]==220){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1,0.3f));
		}
		
		if(counter[9]>=200){
			if(counter[9]==200){
				vehicles.addCar(new Car(game,0,0.5f));
			}
			if(counter[9]==260){
				vehicles.addCar(new Car(game,0,0.5f));
				counter[9]=0;
			}
		}
	}
	
	public void fase4(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==80){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,100,1.2f));
		}
		
		if(counter[1]==80){
			counter[1]=0;
			if(r.nextInt(11)>7)
				riverItems.addAlligator(new Alligator(game,1,1.5f));
			else
				riverItems.addTurtle(new Turtle(game,1,r.nextInt(2)+2,1.5f));
		}
		
		if(counter[2]==170){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,0.9f));
		}
		
		if(counter[3]==100){
			counter[3]=0;
			if(r.nextInt(11)>7)
				riverItems.addAlligator(new Alligator(game,3,1.0f));
			else
				riverItems.addTurtle(new Turtle(game,3,r.nextInt(2)+2,1.0f));
		}
		
		if(counter[4]==190){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,0.7f));
		}
		
		if(counter[5]==140){
			counter[5]=0;
			vehicles.addTruck(new Truck(game,4,1.2f));
		}
		
		if(counter[6]==130){
			counter[6]=0;
			vehicles.addBus(new Bus(game,3,0.9f));
		}
		
		if(counter[7]>=130){
			if(counter[7]==130){
				vehicles.addTaxi(new Taxi(game,2,2.0f));
			}
			if(counter[7]==160){
				vehicles.addTaxi(new Taxi(game,2,2.0f));
				counter[7]=0;
			}
		}
		
		if(counter[8]==170){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1,0.9f));
		}
		
		if(counter[9]>=160){
			if(counter[9]==160){
				vehicles.addCar(new Car(game,0,1.5f));
			}
			if(counter[9]==190){
				vehicles.addCar(new Car(game,0,1.5f));
			}
			if(counter[9]==220){
				vehicles.addCar(new Car(game,0,1.5f));
				counter[9]=0;
			}
		}
	}
	
	public void fase5(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==70){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,100,1.9f));
		}
		
		if(counter[1]==50){
			counter[1]=0;
			if(r.nextInt(11)>7)
				riverItems.addAlligator(new Alligator(game,1,2.4f));
			else
				riverItems.addTurtle(new Turtle(game,1,r.nextInt(2)+2,2.4f));
		}
		
		if(counter[2]==150){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,1.6f));
		}
		
		if(counter[3]==80){
			counter[3]=0;
			if(r.nextInt(11)>7)
				riverItems.addAlligator(new Alligator(game,3,1.9f));
			else
				riverItems.addTurtle(new Turtle(game,3,r.nextInt(2)+2,1.7f));
		}
		
		if(counter[4]==150){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,1.5f));
		}
		
		if(counter[5]==120){
			counter[5]=0;
			vehicles.addTruck(new Truck(game,4,2.0f));
		}
		
		if(counter[6]==115){
			counter[6]=0;
			vehicles.addBus(new Bus(game,3,1.7f));
		}
		
		if(counter[7]>=100){
			if(counter[7]==120){
				vehicles.addTaxi(new Taxi(game,2,2.8f));
			}
			if(counter[7]==140){
				vehicles.addTaxi(new Taxi(game,2,2.8f));
				counter[7]=0;
			}
		}
		
		if(counter[8]==150){
			counter[8]=0;
			vehicles.addBus(new Bus(game,1,1.6f));
		}
		
		if(counter[9]>=150){
			if(counter[9]==160){
				vehicles.addCar(new Car(game,0,2.3f));
			}
			if(counter[9]==190){
				vehicles.addCar(new Car(game,0,2.3f));
			}
			if(counter[9]==220){
				vehicles.addCar(new Car(game,0,2.3f));
				counter[9]=0;
			}
		}
	}
	
	public void fase6(Vehicles vehicles,RiverItems riverItems){
		if(counter[0]==70){
			counter[0]=0;
			riverItems.addLog(new Log(game,0,100,1.9f));
		}
		
		if(counter[1]==50){
			counter[1]=0;
			riverItems.addLog(new Log(game,1,100,2.4f));
		}
		
		if(counter[2]==150){
			counter[2]=0;
			riverItems.addLog(new Log(game,2,r.nextInt(41)+150,1.6f));
		}
		
		if(counter[3]==80){
			counter[3]=0;
			riverItems.addLog(new Log(game,3,150,1.7f));
		}
		
		if(counter[4]==150){
			counter[4]=0;
			riverItems.addLog(new Log(game,4,r.nextInt(41)+100,1.5f));
		}
	}
}