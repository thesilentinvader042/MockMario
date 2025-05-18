package Game1;

import entity.Entity;

public class CollisionChecker 
{
	GPanel gp;
	
	public CollisionChecker(GPanel gp)
	{
		this.gp = gp;
	}
	
	public void checkTile(Entity entity)
	{
		int eLeftWorld = entity.worldX + entity.solid.x;
		int eRightWorld = entity.worldX + entity.solid.x + entity.solid.width;
		int eTopWorld = entity.worldY + entity.solid.y;
		int eBottomWorld = entity.worldY + entity.solid.y + entity.solid.height;
		
		int eLeftWorldCol = eLeftWorld/gp.tileSize;
		int eRightWorldCol = eRightWorld/gp.tileSize;
		int eTopWorldRow = eTopWorld/gp.tileSize;
		int eBottomWorldRow = eBottomWorld/gp.tileSize;
		
		boolean t1, t2;
		
		switch(entity.directionString)
		{
			case "up":
				eTopWorldRow = (eTopWorld - entity.speed)/gp.tileSize;
				t1 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eTopWorldRow][eLeftWorldCol]].collision;
				t2 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eTopWorldRow][eRightWorldCol]].collision;

				entity.collisionOn = t1 || t2;
				break;
			
			case "down":
				eBottomWorldRow = (eBottomWorld + entity.speed)/gp.tileSize;
				t1 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eBottomWorldRow][eLeftWorldCol]].collision;
				t2 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eBottomWorldRow][eRightWorldCol]].collision;
				
				entity.collisionOn = t1 || t2;
				break;
			
			case "left":
				eLeftWorldCol = (eLeftWorld - entity.speed)/gp.tileSize;
				t1 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eTopWorldRow][eLeftWorldCol]].collision;
				t2 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eBottomWorldRow][eLeftWorldCol]].collision;
				
				entity.collisionOn = t1 || t2;
				break;
				
			case "right":
				eRightWorldCol = (eRightWorld + entity.speed)/gp.tileSize;
				t1 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eTopWorldRow][eRightWorldCol]].collision;
				t2 = gp.tileManager.tileArray[gp.tileManager.mapTileNum[eBottomWorldRow][eRightWorldCol]].collision;
				
				entity.collisionOn = t1 || t2;
				break;
		}
	}
}
