package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity // This stores variables that will be used in player monster and NPC classes.
{
	public int worldX, worldY, speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, defImage;
	public String directionString;
	
	public int spriteCount = 0;
	public int spriteNum = 0;
	
	public Rectangle solid;
	public boolean collisionOn = false;
}

//ImageObserver is an interface that has methods for handling notification of state of image loading. It can use this for redisplay as needed. JFrame and Applet both implement ImageObserver interface.
//
//To keep users informed regarding the loading of an image
//
//ImageObserver interface – Enables the monitoring of the loading process so that users can be informed and the image can be used asap once it is loaded.
//
//Loading an image asynchronously – how to know when the image is ready.
//
//An image is ready – getImage() method returns, long before anything is known about the image.
//
//imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
//Note: java.awt.Component implements ImageObserver, all the subclasses do as well!
//
//g.drawImage(imge, 0,0, this) -- this refers to the ImageObserver instance.
//
//imageUpdate() – Called by the ImageObserver whenever necessary. You do not call it explicitly!
//
//If the image is complete, returns false.
//If the image is not complete and needs to be updated, returns true.