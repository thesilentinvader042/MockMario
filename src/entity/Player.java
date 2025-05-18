package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game1.GPanel;
import Game1.KeyHandle;

public class Player extends Entity 
{
	GPanel gP;
	KeyHandle keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GPanel gp, KeyHandle keyH)
	{
		this.gP = gp;
		this.keyH = keyH;
		
		this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
		this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
		
		// actual colliding area of player
		solid = new Rectangle();
		solid.x = 8;
		solid.y = 16;
		solid.width = 44;
		solid.height = 44;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues()
	{
		worldX = gP.tileSize*23;
		worldY = gP.tileSize*21;
		speed = 4;
		directionString = "def";
	}
	
	public void getPlayerImage() 
	{
		try 
		{
			defImage = ImageIO.read(getClass().getResourceAsStream("/player/Sprite1.png"));
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite5.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite6.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite2.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite3.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite7.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite9.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite 11.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite 12.png"));
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if(keyH.up || keyH.down || keyH.left || keyH.right)
		{		
				if(keyH.up)
				{
					directionString = "up";
				}
				else if(keyH.down)
				{
					directionString = "down";
				}
				else if(keyH.left)
				{
					directionString = "left";
				}
				else if(keyH.right)
				{
					directionString = "right";
				}
				
				gP.cChecker.checkTile(this);
				
				if(!collisionOn)
				{
					switch(directionString)
					{
						case "up": worldY -= speed; break;
						case "down": worldY += speed; break;
						case "left": worldX -= speed; break;
						case "right": worldX += speed; break;
					}
				}
				
				spriteCount++;
				if(spriteCount > 5)
				{
					spriteNum = spriteNum == 1 ? 0 : 1;
					spriteCount = 0;
				}
		}
		
		else
			directionString = "def";
	}
	
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.cyan); // sets the color for the objects to be drawn on the screen
//		
//		g2.fillRect(x, y, gP.tileSize, gP.tileSize);
		
		BufferedImage image = null;
		
			switch(directionString)
			{
				case "up":
					image = spriteNum == 0 ? up1 : up2;
					break;
					
				case "down":
					image = spriteNum == 0 ? down1 : down2;
					break;
					
				case "left":
					image = spriteNum == 0 ? left1 : left2;
					break;
					
				case "right":
					image = spriteNum == 0 ? right1 : right2;
					break;
					
				default :
					image = defImage;
			}
		
		g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);
	}
}
