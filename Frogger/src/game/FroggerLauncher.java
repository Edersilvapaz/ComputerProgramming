package game;

/**
 * This program simulates an alternative version of the game Frogger, an Arcade game created in 1981.<br>
 * The original game can be accessed on the web site: http://www.bigmoneyarcade.com/games/frogger.<p> 
 * References:	The game was created following: <pre>
 * 	- Subjects shown on the book "An Introduction to programming using java"
 *	- The you tube channel "Java Game Development Series" -  https://www.youtube.com/playlist?list=PLWms45O3n--6KCNAEETGiVTEFvnqA7qCi
 *	- The you tube channel "New Beginner 2D Programming" - https://www.youtube.com/playlist?list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ</pre>
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 * @version 1.0 Created: 03/31/2016
 */
public class FroggerLauncher {
	/**
	 * Launcher of the game.<br>
	 * Creates a new game object and start it.
	 * @param args Not used in the game
	 */
	public static void main(String[] args){
		Game game = new Game("FROGGER",400,560); //The name that shows at the top of the game window and its size are defined here
		game.start();
	}
}