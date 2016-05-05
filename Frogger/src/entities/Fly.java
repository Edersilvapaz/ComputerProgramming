package entities;

import java.awt.Graphics;

import game.Game;
import graphics.Assets;

public class Fly extends Entity{

	public Fly(Game game, float x, float y,int anim) {
		super(game, x, y,flySize,flySize,0);
		this.anim=anim;
		bounds.x=3;
		bounds.y=3;
		bounds.width=width-2*bounds.x;
		bounds.height=height-2*bounds.y;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(anim==0){
			g.drawImage(Assets.fly,(int)x,(int)y,(int)width,(int)height,null);
		}else{
			g.drawImage(Assets.goldenfly,(int)x,(int)y,(int)width,(int)height,null);
		}
	}
}