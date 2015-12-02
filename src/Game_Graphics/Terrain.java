package Game_Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;

public class Terrain {
	
	private SpriteSheet ss;
	private BufferedImage background = null;
	public Terrain(Game game)
	{
		ss = game.getBG();
		background = ss.grabImage(1, 1, 652, 500);
		
	}
	public void render(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
	}
}