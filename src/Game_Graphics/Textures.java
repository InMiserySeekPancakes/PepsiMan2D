package Game_Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import Game.*;

public class Textures 
{
	public BufferedImage player;
	public BufferedImage bullet;
	public BufferedImage enemy;
	public BufferedImage enemybullet;
	private SpriteSheet ss;
	private Game game;
	public Textures(Game game)
	{
		this.game = game;
		
		ss = game.getSpriteSheet();
		
		getTextures();
	}
	private void getTextures()
	{
		player = ss.grabImage(1, 1, 64, 64);
		bullet = ss.grabImage(3, 1, 64, 32);
		enemy = ss.grabImage(5, 1, 48, 88);
		enemybullet = ss.grabImage(1, 3, 64, 32);
	}

}
