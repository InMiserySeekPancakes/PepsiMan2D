package Game_Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Controller;
import Game.Game;
import Game.Physics;
import Game_Classes.EntityA;

public class Player implements EntityA{
	private double x;
	private double y;
	
	private double velX = 0;
	private double velY = 0;
	private Textures t;
	private Game game;
	private boolean subtractLives = true;
	private Controller c;
	public Player(double x, double y, Textures t, Game game, Controller c)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		this.game = game;
		this.c = c;
		
	}
	public void tick()
	{
		x+= velX;
		y+= velY;
		
		if (x <=0) x = 0;
		if (x >= 640-64) x = 640-64;
		if (y <= 0) y = 0;
		if (y >= 480 - 64) y = 480 - 64;
		/*
		if (Physics.collision(this, game.e))
		{
			if(subtractLives)
			{
				game.setLives(game.getLives() - 1);
				subtractLives  = false;
				game.setEnemy_killed(game.getEnemy_killed() +1);
				subtractLives = false;
			}
		}
		
		else
		{
			subtractLives = true;
		}
		*/
		if (game.getLives() <= 0)
		{
			//end game
			System.out.println("GAME OVER");
			System.exit(0);
		}
	}
	public void render(Graphics g)
	{
		g.drawImage(t.player, (int)x, (int)y, null);
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public void setY(double y)
	{
		this.y = y;
	}
	public void setVelX(double velX)
	{
		this.velX = velX;
	}
	public void setVelY(double velY)
	{
		this.velY = velY;
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 64, 64);
	}

}
