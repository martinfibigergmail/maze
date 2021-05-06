package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;


public class Archer extends Entity{
	
	public Archer (){
	damage = 1;
	health = 2;
	name = "skeletalArcher";
	}
	
	public void move (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
		
	
		if((entityPosition.getNorth() != null 
				 && entityPosition.getNorth().equals(playersPosition)) 
			|| 
				(		   entityPosition.getNorth() != null 
						&& entityPosition.getNorth().getNorth() != null 
						&& entityPosition.getNorth().getNorth().equals(playersPosition))) {
			
			ArrowProjectile north = new ArrowProjectile();
			int direction = 0;
			barrierCanvas.getEntityDirectory().add(north);
			north.entityPosition=entityPosition;
			north.scheduleShooting(player, barrierCanvas, direction);
			
			}
		
		if((entityPosition.getSouth() != null 
				 && entityPosition.getSouth().equals(playersPosition)) 
			|| 
				(		   entityPosition.getSouth() != null 
						&& entityPosition.getSouth().getSouth() != null 
						&& entityPosition.getSouth().getSouth().equals(playersPosition))) {
			
			ArrowProjectile south = new ArrowProjectile();
			int direction = 1;
			barrierCanvas.getEntityDirectory().add(south);
			south.entityPosition=entityPosition;
			south.scheduleShooting(player, barrierCanvas, direction);
			
			}
	
		if(		(entityPosition.getWest() != null 
				 && entityPosition.getWest().equals(playersPosition)) 
			|| 
				(		   entityPosition.getWest() != null 
						&& entityPosition.getWest().getWest() != null 
						&& entityPosition.getWest().getWest().equals(playersPosition))){
			
					ArrowProjectile west = new ArrowProjectile();
					int direction = 2;
					barrierCanvas.getEntityDirectory().add(west);
					west.entityPosition=entityPosition;
					west.scheduleShooting(player, barrierCanvas, direction);
			
			}
	
		if((entityPosition.getEast() != null 
				 && entityPosition.getEast().equals(playersPosition)) 
			|| 
				(		   entityPosition.getEast() != null 
						&& entityPosition.getEast().getEast() != null 
						&& entityPosition.getEast().getEast().equals(playersPosition))) {
			
			ArrowProjectile east = new ArrowProjectile();
			barrierCanvas.getEntityDirectory().add(east);
			east.entityPosition=entityPosition;
			int direction = 3;
			east.scheduleShooting(player, barrierCanvas, direction);
			
			}
}

	
	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
		
		int width;
		int height;
		double doubleX;
		double doubleY;
		try {
			InputStream imageInputStreamArcher = BarrierCanvas.class.getResourceAsStream("Archer.png");
			BufferedImage imgArcher= ImageIO.read(imageInputStreamArcher);
			width = canvasWidth/6;
			height = canvasHeight/6;
	
			doubleX = width * segmentWidth;
			doubleY = height * segmentHeight;
	
			int x = (int)doubleX;
			int y = (int)doubleY;

	
			g2.drawImage(imgArcher, x ,y ,width ,height, null);
	
	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
