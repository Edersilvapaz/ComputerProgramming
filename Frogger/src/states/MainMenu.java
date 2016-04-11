package states;

import java.awt.Color;
import java.awt.Graphics;
import game.Game;

public class MainMenu extends GameStates{

	public MainMenu(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		if(game.getMouseManager().isLeftPressed()){
			if(game.getMouseManager().getMouseX()>=118 && game.getMouseManager().getMouseX()<=282){
				if(game.getMouseManager().getMouseY()>=233 && game.getMouseManager().getMouseY()<=251){
					GameStates.setGameStateTo(game.getPlatingState());
					GameStates.setChangeState(false);
				}
			} 
			if(game.getMouseManager().getMouseX()>=113 && game.getMouseManager().getMouseX()<=287){
				if(game.getMouseManager().getMouseY()>=271 && game.getMouseManager().getMouseY()<=289){
					GameStates.setGameStateTo(game.getHighScoreState());
					GameStates.setChangeState(false);
				}
			} 
			if(game.getMouseManager().getMouseX()>=172 && game.getMouseManager().getMouseX()<=226){
				if(game.getMouseManager().getMouseY()>=309 && game.getMouseManager().getMouseY()<=327){
					game.stop(); //need changes
				}
			}
		}else{
			GameStates.setChangeState(true);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(menuFont);
		g.setColor(Color.WHITE);
		
		g.drawString("START GAME",(game.getWidht()-164)/2,(game.getHeight()-56)/2);		
		g.drawString("HIGH SCORES",(game.getWidht()-174)/2,(game.getHeight()+18)/2);
		g.drawString("EXIT",(game.getWidht()-58)/2,(game.getHeight()+94)/2);
	}
}