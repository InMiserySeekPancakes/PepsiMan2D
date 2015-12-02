package Game_Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Controller;
import Game.Game;
import Game.Physics;
import Game_Classes.Entity;
import Game_Classes.EntityA;

public class Bullet extends Entity implements EntityA
{
	private double x;
	private double y;
	private Textures t;
	private Game game;
	private Controller c;
	private boolean updateVar;
	
	public Bullet(double x, double y, Textures t, Game game, Controller c)
	{
		super(x, y, t, game, c);
		
	}
	public void tick()
	{
		x += 15;
		
		if (Physics.collision(this, game.e))
		{
			if (updateVar)
			{
				System.out.println("COLLISION DETECTED");
				c.removeBullet(this);
				game.setEnemy_killed(game.getEnemy_killed() +1);

				updateVar = false;
			}
		}
		else
			updateVar = true;
	}
	public void render(Graphics g)
	{
		g.drawImage(t.bullet, (int)x, (int)y, null);
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 64, 32);
	}

}
