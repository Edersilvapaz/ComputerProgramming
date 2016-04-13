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
		g.drawString("Your Score: "+game.PlayingState().getScore(),100,300);
		if(reachedHighScores){
			g.fillRect(0, 0, 50, 50);
		}
	}
	
	/**
	 * 
	 */
	public void checkScore(int score){
		reachedHighScores=false;
		for(int x=0 ; x<Score.score.length ; x++){
			if(Score.score[x]>game.PlayingState().getScore())
				reachedHighScores=true;
		}
	}
}