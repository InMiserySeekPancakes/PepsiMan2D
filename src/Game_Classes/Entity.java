package Game_Classes;

import Game.Controller;
import Game.Game;
import Game_Graphics.Textures;

public class Entity 
{
	private double y;
	private double x;
	private Object game;
	private Controller c;
	private Textures t;

	public Entity(double x, double y, Textures t, Game game, Controller c)
	{
		this.y = y;
		this.x = x;
		this.game = game;
		this.c = c;
		this.t = t;
	}

}
