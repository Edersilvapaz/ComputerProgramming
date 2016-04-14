package states;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import score.Score;

public class GameOver extends GameStates{
	
	private boolean reachedHighScores;
	
	public GameOver(Game game) {
		super(game);
	}

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
	 * 
	 */
	public void checkScore(int score){
		reachedHighScores=false;
		for(int x=0 ; x<Score.score.length ; x++){
			if(game.PlayingState().getScore()>Score.score[x])
				reachedHighScores=true;
		}
	}
}