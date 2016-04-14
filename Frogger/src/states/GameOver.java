package states;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import score.Score;

/**
 * This is the class that defines the game over screen.
 * @author Eder Paz ; Logan Wedel ; Neil Blake
 */
public class GameOver extends GameStates{
	
	private boolean reachedHighScores;
	
	/**
	 * Makes a copy of the game object so that the state can rely on the game variables.
	 * @param game Game instance
	 */
	public GameOver(Game game) {
		super(game);
	}

	/**
	 * Evaluates the position where the mouse was clicked on the game window and change the state of the game according to it.
	 */
	@Override
	public void tick() {
		if(game.getMouseManager().isLeftPressed()){
			if(reachedHighScores){
				if(game.getMouseManager().getMouseX()>=165 && game.getMouseManager().getMouseX()<=235){
					if(game.getMouseManager().getMouseY()>=525 && game.getMouseManager().getMouseY()<=543){
						Score.updateHighScores(game.getKeyManager().getInitials(),game.PlayingState().getScore());
						GameStates.setGameStateTo(game.getMenuState());
						GameStates.setChangeState(false);
					}
				}
			}else{
				if(game.getMouseManager().getMouseX()>=65 && game.getMouseManager().getMouseX()<=335){
					if(game.getMouseManager().getMouseY()>=525 && game.getMouseManager().getMouseY()<=543){
						GameStates.setGameStateTo(game.getMenuState());
						GameStates.setChangeState(false);
					}
				}
			}
		}else{
			GameStates.setChangeState(true);
		}
	}

	/**
	 * Draws the the menus and the player's initials that are being typed, if the player reached a high score.
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(menuFont);
		g.drawString("GAME OVER",125,200);
		g.drawString("Your Score: "+game.PlayingState().getScore(),100,250);
		if(reachedHighScores){
			g.drawString("Congratulations!",100,300);
			g.drawString("Type your initials: "+game.getKeyManager().getInitials(),50,350);
			g.drawString("SAVE",165,525+18);
		}else{
			g.drawString("BACK TO MAIN MENU",70,525+18);
		}
	}
	
	/**
	 * Check if the score sent to it is within the high score values recorded in the high scores file.
	 */
	public void checkScore(int score){
		reachedHighScores=false;
		for(int x=0 ; x<Score.score.length ; x++){
			if(game.PlayingState().getScore()>Score.score[x])
				reachedHighScores=true;
		}
	}
}