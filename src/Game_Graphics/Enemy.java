package Game_Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Game.Controller;
import Game.Game;
import Game.Physics;
import Game_Classes.Entity;
import Game_Classes.EntityB;

public class Enemy extends Entity implements EntityB
{
	private double x;
	private double y;
	private Game game;
	private Textures t;
	private Random r = new Random();
	private Player p;
	private boolean subtractLives = true;
	private Controller c;
	private boolean updateVar;
	
	public Enemy(double x, double y, Textures t, Game game, Controller c)
	{
		super(x, y, t, game, c);
	}
	public void tick()
	{
		x-= r.nextInt(3) +  1 ;
		
		if (Physics.bulletCollision(this, game.b))
		{
			if (updateVar)
			{
				System.out.println("COLLISION DETECTED");
				c.removeEnemy(this);
				updateVar = false;
			}
		}
		else
			updateVar = true;
		
		if (Physics.playerCollision(this, p))
		{
			if(subtractLives )
			{
				game.setLives(game.getLives() - 1);
				subtractLives  = false;
				c.removeEnemy(this);
				game.setEnemy_killed(game.getEnemy_killed() +1);
				subtractLives = false;
			}
		}
		else
		{
			subtractLives = true;
		}
		
		if (x <= -50) 
		{
			x = game.WIDTH * game.SCALE + 50 + r.nextInt(200);
			y = r.nextInt(game.HEIGHT * game.SCALE - 90);
			game.setEnemy_lost(game.getEnemy_lost() + 1);
			System.out.println(game.getEnemy_lost());
		}
		if(game.getEnemy_lost() >= 5)
		{
			//end game
			System.out.println("GAME OVER");
			System.exit(0);
		}
	}
	public void render(Graphics g)
	{
		g.drawImage(t.enemy, (int)x, (int)y, null);
	}
	public double getX() 
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 44, 84);
	}
	public Enemy getEnemy()
	{
		return this;
	}

}
