package Game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import Game_Classes.EntityA;
import Game_Classes.EntityB;
import Game_Graphics.Bullet;
import Game_Graphics.Enemy;
import Game_Graphics.EnemyBullet;
import Game_Graphics.Textures;

public class Controller 
{
	private LinkedList<Bullet> bullet = new LinkedList<Bullet>();
	private LinkedList<Enemy> enemy = new LinkedList<Enemy>();
	
	Random r = new Random();

	Game game;
	private Textures t;
	private int countlost = 0;
	private Bullet tempBullet;
	private Enemy tempEnemy;
	
	public Controller(Game game, Textures t)
	{
		this.game = game;
		this.t = t;
	}
	public void createEnemies(int num)
	{
		for(int i = 0; i < num; i++)
		{
			addEnemy(new Enemy(game.WIDTH * game.SCALE + 100, r.nextInt(game.HEIGHT * game.SCALE - 90), t, game, this));
		}
	}
	public void tick()
	{
		for (int i = 0; i < enemy.size(); i++)
		{
			tempEnemy = enemy.get(i);
			tempEnemy.tick();
			
			if(tempEnemy.getX() >= 700)
				removeEnemy(tempEnemy);
		}
		for (int i = 0; i < bullet.size(); i++)
		{
			tempBullet = bullet.get(i);
			
			if(tempBullet.getX() <= -100)
			{
				removeBullet(tempBullet);
				countlost ++;
				game.setEnemy_lost(countlost);
			}
			
			tempBullet.tick();
		}
	}
	public void render(Graphics g)
	{
		if(!enemy.isEmpty()){
			for (int i = 0; i < enemy.size(); i++)
			{
				tempEnemy = enemy.get(i);
				tempEnemy.render(g);
			}			
		}
		for (int i = 0; i < bullet.size(); i++)
		{
			tempBullet = bullet.get(i);
			tempBullet.render(g);
		}
	}
	public void addEnemy(Enemy block)
	{
		enemy.add(block);
	}
	public void removeEnemy(Enemy block)
	{
		enemy.remove(block);
	}
	public void addBullet(Bullet block)
	{
		bullet.add(block);
	}
	public void removeBullet(Bullet block)
	{
		bullet.remove(block);
	}
	public Enemy getTempEnemy()
	{
		return tempEnemy;
	}
	public Bullet getTempBullet()
	{
		return tempBullet;
	}
	public LinkedList<Enemy> getEnemy()
	{
		return enemy;
	}
	public LinkedList<Bullet> getBullet()
	{
		return bullet;
	}
}
