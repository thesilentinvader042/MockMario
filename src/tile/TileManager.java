package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Game1.GPanel;

public class TileManager 
{
	GPanel gPanel;
	public Tile[] tileArray;
	public int mapTileNum[][];
	
	public TileManager(GPanel gPanel) 
	{
		this.gPanel = gPanel;
		
		tileArray = new Tile[10];
		mapTileNum = new int[gPanel.maxWorldRow][gPanel.maxWorldCol];
		
		getTileImage();
		loadMap("/maps/map1.txt");
	}
	
	//getResourceAsStream() is a method of Class<?> class of java
	// getClass() function is a function of Class class which returns a Class object that represents the runtime class of "this" object.
	
	public void getTileImage() 
	{
		try
		{
			tileArray[0] = new Tile();
			tileArray[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass03.png"));
			
			tileArray[1] = new Tile();
			tileArray[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tileArray[1].collision = true;
			
			tileArray[2] = new Tile();
			tileArray[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));
			tileArray[2].collision = true;
			
			tileArray[3] = new Tile();
			tileArray[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tileArray[4] = new Tile();
			tileArray[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2_0.png"));
			tileArray[4].collision = true;
			
			tileArray[5] = new Tile();
			tileArray[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String s) 
	{
		try
		{
			InputStream iStream = getClass().getResourceAsStream(s);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
			
			for(int i = 0; i < gPanel.maxWorldRow; i++)
			{
				String string = bReader.readLine();
				String numString[] = string.split(" ");
				
				for(int j = 0; j < gPanel.maxWorldCol; j++)
					mapTileNum[i][j] = Integer.parseInt(numString[j]);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2)
	{
		int i = 0, j = 0, worldY, worldX, screenY, screenX;
		
		while(i < gPanel.maxWorldRow && j < gPanel.maxWorldCol)
		{
				int tn =mapTileNum[i][j];
				worldY = i * gPanel.tileSize;
				screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
				worldX =  j * gPanel.tileSize;
				screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
				
//				if(worldX  > gPanel.player.worldX - gPanel.player.screenX &&
//					worldX  < gPanel.player.worldX + (gPanel.screenWidth - gPanel.player.screenX) &&
//					worldY > gPanel.player.worldY - gPanel.player.screenY &&
//					worldX  < gPanel.player.worldY + (gPanel.screenHeight - gPanel.player.screenY))
				{
					g2.drawImage(tileArray[tn].image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, gPanel);
				}
				
				j++;
				
				if(j == gPanel.maxWorldCol)
				{
					j = 0; i++;
				}
		}
		
	}
	
}