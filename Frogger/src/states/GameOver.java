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
		
		if(game.getKeyManager().Down()){
			GameStates.setGameStateTo(game.getMenuState());
			GameStates.setChangeState(false);
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
			g.drawString("Type your initials: "+game.getKeyManager().getInitials(),10,350);
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