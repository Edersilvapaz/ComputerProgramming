package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import graphics.Assets;
import input.KeyManager;
import input.MouseManager;
import score.Score;
import states.GameOver;
import states.GameStates;
import states.HighScores;
import states.MainMenu;
import states.Playing;

/**
 * This class is the base of the entire game.<br>
 * It contains the methods to create a new game, opens the game windows and contains the game loop that keeps the game running.
 * @author Eder Paz; Neil Blake; Logan Wedel
 */
public class Game implements Runnable{
	/*
	 * "implements Runnable" allow the Game class to work wit threads: start, stop, and run them
	 * A thread is what is used to make things run at the same time during a program and is the base of the game
	 * This game will make use of one single thread
	 */
	
	private int width,height;
	private float defaultSpeed; //Rules the defaultSpeed of the game
	private String title;
	
	private Display display; //creates and configures the display of the game
	private boolean running = false; //responsible for keeping the game loop running
	private Thread thread;
	
	
	private BufferStrategy bs; //buffers the entire image on the screen so that we have a stable image
	private Graphics g; //responsible for actually drawing the assets on the screen
	
	private KeyManager keyManager; //object that read the keyboard actions
	private MouseManager mouseManager; //object that read the mouse actions
	
	//the game will hold an instance of each state that it might go through
	private GameStates playingState;
	private GameStates mainMenuState;
	private GameStates highScoreState;
	private GameStates gameOverState;	
	
	/**
	 * The game title, width and height are defined.<br>
	 * The objects for reading from the keyboard and the mouse are also initiated here.
	 * @param title Defines the name of the game
	 * @param width Defines the width of the game screen
	 * @param height Defines the height of the game screen
	 */
	public Game(String title,int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager(this);
		mouseManager = new MouseManager();
	}
	
	/**
	 * This methods actually starts the game.<br>
	 * It creates a new thread and starts it.
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * This game ends the game by stopping the game thread.<br>
	 */
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
	
	/**
	 * This method is called when the game thread is started, right after the start() method.<br>
	 * It initiates the display, the assets and the states of the game.<br>
	 * It also contains the MAIN GAME LOOP;
	 */
	public void run(){

		init();//initiate display, assets and game states
		
		/*
		 * This is the game loop, it controls the game so it upgrade its variables and draw its figures on the screen 60 times per second
		 * 
		 * 1. The amount of time that each tick* will have is defined on line 117, dividing the amount of nanoseconds in a second by the amount of frames
		 * 	we want in the game, in this case: 60
		 * 
		 * After that, every time the while loop runs:
		 * 
		 * 2. The now variable gets how many time the game is running (line 125)
		 * 3. The delta variable is increased by how many time the game has been running since the last time the loop ran in nanoseconds
		 * 	divided by how many time each tick* has in the game (line 126)
		 * 4. The lastTime variable gets the current running time for the next time the game loop will run (line 128) 
		 * 5. If the delta variable holds a value greater than 1, it means that the time that each tick* will have is passed and
		 * 	it is time to tick* and render* the game
		 * 
		 * TICK() - This method is responsible for upgrading the variables of the game, like positions and counter
		 * RENDER(Graphics g) -  This method is responsible for drawing the game on the screen
		 * EACH CLASS THAT PLAYS A ROLE ON THE GAME WILL HAVE ITS OWN TICK() AND RENDER() METHOD, AND ALL OF THEM ARE CALLED TOGETHER
		 * THROUGH THE GAME CLASS TICK() AND REDNER()
		 *  
		 */
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				delta--;
			}
		}
		
		stop();//When the game loop is done, closes the game
		
	}
	
	/**
	 * This methods is responsible for creating and defining the window for the game.<br>
	 * It also adds the key and mouse listeners to the game frame and initiates the assets of the game,<br>
	 * and creates all state objects and sets the very first state of the game.
	 */
	private void init(){
		//Creates the the Display object and makes it appear on the screen
		display = new Display(title,width,height);
		display.createDisplay();
		
		//The key and mouse listener are added to the game window so that they can be used in the game
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		
		
		Assets.init(); //initiate the assets of the game
		//Score.init(); //initiate the score tracking of the game
		
		//here the states objects of the game are created and the first state to be used in the game is defined
		playingState = new Playing(this);
		mainMenuState = new MainMenu(this);
		highScoreState = new HighScores(this);
		gameOverState = new GameOver(this);
		GameStates.setGameStateTo(mainMenuState);
	}
	
	/**
	 * This method makes the game upgrade its variables.<br>
	 * It ticks the current running game state and the key manager.
	 */
	private void tick(){
		keyManager.tick();
		if(GameStates.getState()!=null)
			GameStates.getState().tick();
	}
	
	/**
	 * This method draws the game on its window.<br>
	 * It renders the current running game state.
	 */
	private void render(){
		
		//get the current buffer strategy saved on the canvas of the game
		bs = display.getCanvas().getBufferStrategy();
		
		//if there isn't any buffer strategy created yet, creates a new one
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		//The graphics gets the buffered image that has to be drawn on the screen
		g = bs.getDrawGraphics();
		
		//The graphics cleans the screen so that only the current buffered image is going to be shown
		g.clearRect(0,0,width,height);
		
		//This draws the background on the screen
		g.drawImage(Assets.bgnd,0,0,width,height,null);
		
		//This renders the current running state of the game
		if(GameStates.getState()!=null)
			GameStates.getState().render(g);
		
		//Get the buffered image generated by the buffered strategy and draws it on the screen
		bs.show();
		g.dispose();
	}
	
	/**
	 * Getter for the game width.
	 * @return The width of the game window
	 */
	public int getWidht(){
		return width;
	}
	
	/**
	 * Getter for the game height.
	 * @return The height of the game window
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Getter for the object that reads the keyboard inputs.
	 * @return KeyManager class instance.
	 */
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	/**
	 * Getter for the object that reads the mouse inputs.
	 * @return MouseManager class instance.
	 */
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	/**
	 * Getter for the default speed of the game.
	 * @return Game default speed variable
	 */
	public float getDefaultSpeed(){
		return defaultSpeed;
	}
	
	/**
	 * Setter for the default speed of the game.
	 * @param speed Value in which the game default speed is going to be set to.
	 */
	public void setDefaultSpeed(float speed){
		defaultSpeed=speed;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameStates getPlayingState(){
		return playingState;
	}
	
	/**
	 * 
	 * @return
	 */
	public Playing PlayingState(){
		return (Playing) playingState;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameStates getMenuState(){
		return mainMenuState;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameStates getHighScoreState(){
		return highScoreState;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameStates getGameOverState(){
		return gameOverState;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameOver GameOverState(){
		return (GameOver) gameOverState;
	}
}