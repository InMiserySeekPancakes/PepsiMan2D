package Game_Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game_Classes.EntityB;

public class EnemyBullet implements EntityB 
{
	private double x;
	private double y;
	private Textures t;
	
	public EnemyBullet(double x, double y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
	}
	public void tick()
	{
		x -= 12;
	}
	public void render(Graphics g)
	{
		g.drawImage(t.enemybullet, (int)x, (int)y, null);
	}
	public double getX()
	{
		return x;
	}
	@Override
	public double getY() 
	{
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 32);
	}

}
