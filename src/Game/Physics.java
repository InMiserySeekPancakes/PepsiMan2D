package Game;

import java.util.LinkedList;

import Game_Classes.EntityA;
import Game_Classes.EntityB;
import Game_Graphics.Bullet;
import Game_Graphics.Enemy;
import Game_Graphics.Player;

public class Physics 
{
	public static boolean collision(Bullet b, LinkedList<Enemy> e)
	{
		for(int i = 0; i < e.size(); i++)
		{
			if(b.getBounds().intersects(e.get(i).getBounds()))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean bulletCollision(Enemy b, LinkedList<Bullet> b2)
	{
		for(int i = 0; i < b2.size(); i++)
		{
			if(b.getBounds().intersects(b2.get(i).getBounds()))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean playerCollision(Enemy b, Player p)
	{
		if(b.getBounds().intersects(p.getBounds()))
		{
			return true;
		}
		else return false;
	}
	
}