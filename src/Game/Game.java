package Game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

import Game_Classes.EntityA;
import Game_Classes.EntityB;
import Game_Graphics.Bullet;
import Game_Graphics.Enemy;
import Game_Graphics.EnemyBullet;
import Game_Graphics.Player;
import Game_Graphics.SpriteSheet;
import Game_Graphics.Terrain;
import Game_Graphics.Textures;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	private static final String NAME = "Game";
	
	private JFrame frame;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private SpriteSheet spriteSheet = new SpriteSheet("/sprite_sheet.png");
	private SpriteSheet background = new SpriteSheet("/background.png");
	
	private int enemy_count = 1;
	private int enemy_killed = 0;
	private int enemy_lost = 0;
	private int lives = 5;
	
	public Player player = null;
	private Terrain bg = null;
	private Controller c = null;
	private Textures t = null;

	public LinkedList<Enemy> e;
	public LinkedList<Bullet> b;
	
	
	
	public Game()
	{
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	public void init()
	{
		requestFocus();
		addKeyListener(new KeyInput(this));
		
		t = new Textures(this);
		bg = new Terrain(this);
		c = new Controller(this, t);
		c.createEnemies(enemy_count);
		e = c.getEnemy();
		b = c.getBullet();
		player = new Player(0, 0, t, this, c);

		
		
	}
	public int getEnemy_spawn() {
		return enemy_count;
	}
	public void setEnemy_spawn(int enemy_spawn) {
		this.enemy_count = enemy_spawn;
	}
	public int getEnemy_killed() {
		return enemy_killed;
	}
	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	public int getEnemy_lost() {
		return enemy_lost;
	}
	public void setEnemy_lost(int enemy_lost) {
		this.enemy_lost = enemy_lost;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public synchronized void start() 
	{
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop() 
	{
		running = false;
	}
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int frames = 0;
		int ticks = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -=1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender)
			{
			frames++;
			render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				System.out.println(frames + ", " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	public LinkedList<Enemy> getEnemy() {
		return e;
	}
	public void setEnemy(LinkedList<Enemy> e) {
		this.e = e;
	}
	public Controller getC() {
		return c;
	}
	public void setC(Controller c) {
		this.c = c;
	}
	public void tick()
	{

		
		tickCount++;
		player.tick();
		c.tick();
		
		if(enemy_lost >3)
		{
			//end game
		}
		if(enemy_killed >= enemy_count)
		{
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemies(enemy_count);
		}
		
	}
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//g.drawImage(player, 100, 100, this);
		bg.render(g);
		c.render(g);
		player.render(g);
		g.setColor(Color.black);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();

	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
		{
			player.setVelY(-8);
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{
			player.setVelX(-8);
		}
		else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
		{
			player.setVelY(8);
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{
			player.setVelX(8);
		}
		else if(key == KeyEvent.VK_ESCAPE)
		{
			//menu
		}
		else if(key == KeyEvent.VK_SPACE)
		{
			c.addBullet(new Bullet(player.getX() + 16, player.getY() + 16, t, this, c));
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
		{
			player.setVelY(0);
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{
			player.setVelX(0);
		}
		else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
		{
			player.setVelY(0);
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{
			player.setVelX(0);
		}
	}

	public static void main(String[] args)
	{
		new Game().start();
	}
	public SpriteSheet getSpriteSheet()
	{
		return spriteSheet;
	}
	public SpriteSheet getBG()
	{
		return background;
	}
	
}
