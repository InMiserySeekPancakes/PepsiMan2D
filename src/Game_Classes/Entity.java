package Game_Classes;

import Game.Controller;
import Game.Game;
import Game_Graphics.Textures;

public abstract class Entity 
{
	protected double y;
	protected double x;
	protected Game game;
	protected Controller c;
	protected Textures t;

	public Entity(double x, double y, Textures t, Game game, Controller c)
	{
		this.y = y;
		this.x = x;
		this.game = game;
		this.c = c;
		this.t = t;
	}

}
