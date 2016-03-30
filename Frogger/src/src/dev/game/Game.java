package src.dev.game;

/**
 * This class is the base of the entire game.
 * It contains the methods to create a new game, opens the game windows and contains the game loop that keeps the game running.
 */

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import src.dev.display.Display;
import src.dev.graphics.Assets;
import src.dev.input.KeyManager;
import src.dev.input.MouseManager;
import src.dev.states.GameState;
import src.dev.states.State;

/*
 * "implements Runnable" allow the Game class to work wit threads: start, stop, and run them.
 * A thread is what is used to make things run at the same time during a program and is the base of the game.
 * This game will make use of one single thread.
 */

public class Game implements Runnable{
	
	//The game has defined width, height and title, which are defined through the start() method;
	//It also has a defaultSpeed which rules the speed that the game runs on;
	private int width,height;
	private float defaultSpeed;
	private String title;
	
	
	//											"display" creates and configures the display of the game;
	//These are the main variables of the game:	"running" is responsible for keeping the game loop running;
	//											"thread" is the thread that the game runs on;
	private Display display;
	private boolean running = false;
	private Thread thread;
	
	//These are the variables responsible for drawing the game assets on the screen;
	//BuffersStrategy buffers the entire image on the screen so that we have a stable image;
	//Graphics is the responsible for actually drawing the assets on the screen;
	private BufferStrategy bs;
	private Graphics g;
	
	//KeyManager and MouseManager are the classes responsible for detecting all the action performed on the keyboard or the mouse;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//the game will hold an instance of each state that it might go through;
	private State gameState;
	
	
	//In the Game constructor the width, height and title of the game is defined;
	//The objects responsible for the keyboard and mouse readings are also created;
	public Game(String title,int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	//This method is responsible for starting the game
	//It creates a new thread and starts it;
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//This method ends the game by stopping the thread through the join() method;
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		display.close();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//This method is called when the game thread is started;
	//It is responsible for initiating the display, the assets and the states of the game;
	//It also contains the MAIN GAME LOOP;
	public void run(){
		
		init();//initiate display, assets and game states;
		
		/*
		 * This is the game loop, it controls the game so it upgrade its variables and draw its figures on the screen 60 times per second;
		 * 
		 * 1. The amount of time that each tick* will have is defined on line 117, dividing the amount of nanoseconds in a second by the amount of frames
		 * 	we want in the game, in this case: 60;
		 * 
		 * After that, every time the while loop runs:
		 * 
		 * 2. The now variable gets how many time the game is running (line 125);
		 * 3. The delta variable is increased by how many time the game has been running since the last time the loop ran in nanoseconds
		 * 	divided by how many time each tick* has in the game (line 126);
		 * 4. The lastTime variable gets the current running time for the next time the game loop will run (line 128); 
		 * 5. If the delta variable holds a value greater than 1, it means that the time that each tick* will have is passed and
		 * 	it is time to tick* and render* the game;
		 * 
		 * TICK() - This method is responsible for upgrading the variables of the game, like positions and counter;
		 * RENDER(Graphics g) -  This method is responsible for drawing the game on the screen;
		 * EACH CLASS THAT PLAYS A ROLE ON THE GAME WILL HAVE ITS OWN TICK() AND RENDER() METHOD, AND ALL OF THEM ARE CALLED TOGETHER
		 * THROUGH THE GAME CLASS TICK() AND REDNER();
		 *  
		 */
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Tickts and Frames: "+ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();//When the game loop is done, closes the game;
		
	}
	
	//This is the method responsible for creating and defining the screen for the game;
	//It also adds the key and the mouse listener to the game, initiates the assets of the game,
	//creates all states objects of the game and sets the very first state of the game;
	private void init(){
		display = new Display(title,width,height);
		
		//The key and mouse listener are added to the game window so that they can be used in the game;
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();//initiate the assets of the game;
		
		//here the states objects of the game are created and the first state to be used in the game is defined;
		gameState = new GameState(this);
		State.setState(gameState);
	}
	
	
	//This is the method that makes the game upgrade its variables,
	//It gets the current running state and ticks it;
	//It also tick the key manager so it can upgrade when a key is pressed on the keyboard;
	private void tick(){
		keyManager.tick();
		if(State.getState()!=null)
			State.getState().tick();
	}
	
	//This is the method that draw the game on the window
	//It gets the current running state and renders it;
	private void render(){
		
		//get the current buffer strategy saved on the canvas of the game;
		bs = display.getCanvas().getBufferStrategy();
		
		//if there isn't any buffer strategy created yet, creates a new one;
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		//The graphics gets the buffered image that has to be drawn on the screen;
		g = bs.getDrawGraphics();
		
		//The graphics cleans the screen so that only the current buffered image is going to be shown;
		g.clearRect(0,0,width,height);
		
		//This draws the background on the screen;
		g.drawImage(Assets.bgnd,0,0,width,height,null);
		
		//This renders the current running state of the game;
		if(State.getState()!=null)
			State.getState().render(g);
		
		//Get the buffered image generated by the buffered strategy and draws it on the screen;
		bs.show();
		g.dispose();
	}
	
	//This sections is for getters and setters needed on the game, once all of the variables in these class are private;
	
	public int getWidht(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public float getDefaultSpeed(){
		return defaultSpeed;
	}
	
	public void setDefaultSpeed(float speed){
		defaultSpeed=speed;
	}
}