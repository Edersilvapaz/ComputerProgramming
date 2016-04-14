package states;

import java.awt.Color;
import java.awt.Graphics;
import game.Game;
import score.Score;

/**
 * This is the class that defines the screen that shows the highest scores of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class HighScores extends GameStates{
	
	/**
	 * Makes a copy of the game objects so that the state can rely on the game variables.
	 * @param game Game instance.
	 */
	public HighScores(Game game) {
		super(game);
	}
	
	/**
	 * Evaluates the position where the mouse was clicked on the game window and change the state of the game according to it.
	 */
	@Override
	public void tick() {
		if(game.getMouseManager().isLeftPressed()){
			if(game.getMouseManager().getMouseX()>=120 && game.getMouseManager().getMouseX()<=290){
				if(game.getMouseManager().getMouseY()>=526 && game.getMouseManager().getMouseY()<=544){
					GameStates.backToLastState();
					GameStates.setChangeState(false);
				}
			}
		}else{
			GameStates.setChangeState(true);
		}
	}
	
	/**
	 * Draw the list of the highest scores with initials and scores.<br>
	 * Draw the buttons and titles as well.
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(menuFont);
		g.drawString("HIGHEST SCORES",90,game.getHeight()/2-140);
		for(int x=0 ; x<Score.initials.length ; x++){
			g.drawString(Score.initials[x],100,240+50*x);
			g.drawString(Score.score[x]+"",250,240+50*x);
		}
		g.drawString("START MENU",120,544);
	}
}