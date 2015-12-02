package Game;

import java.awt.Rectangle;

public class GameObject 
{
	public double x, y;
	
	public GameObject(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public Rectangle getBounds(double w, double h)
	{
		return new Rectangle((int)x, (int)y, (int)w, (int)h);
	}

}
