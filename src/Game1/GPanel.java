package Game1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GPanel extends JPanel implements Runnable
{
	// Screen Settings
	final int originalSize = 16;
	final int scale = 4;
	
	public final int tileSize = originalSize*scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHeight = maxScreenRow * tileSize;
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldCol;
	
	public TileManager tileManager = new TileManager(this);
	KeyHandle keyH = new KeyHandle();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	int FPS = 60;
	
	public GPanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.GREEN);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); // this helps the GPanel to focus on Key input
		this.startGThread();
	}
	
	public void startGThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	// Here in the run method, we would develop the game loop
	// The run method is automatically called when a thread from the class which implements Runnable Interface is started.
	/* 2 ways to achieve the game loop:
	 * a] sleep() method by calculating nextDrawTime and remaining time; Must include try and catch blocks
	 * b] accumulator/delta method;
	 */
	public void run() 
	{
		double drawInt = 1e9/FPS;
		double delta;
		long lastTime = System.nanoTime();
		long currTime;
		
		while(gameThread != null)
		{
				update(); 
				repaint();// paintComponent() Method is called using the repaint() method;
				delta = 0;
				while(delta < drawInt)
				{
					currTime = System.nanoTime();
					delta = delta + (currTime - lastTime);
					lastTime = currTime;
				}
		}
	}
	
	public void update() 
	{
		player.update();
	}
	
	// Graphics class has many functions to draw objects on to the screen.
	/*Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, 
	 * coordinate transformations, color management and text layouts.
	 */
	public void paintComponent(Graphics g) // built in method in Java to draw things onto the JPanel object.
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileManager.draw(g2);
		player.draw(g2);
		
		g2.dispose(); // frees up space and system settings connected to g2 object
	}
}