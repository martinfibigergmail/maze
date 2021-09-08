package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;

public class FireElemental extends Entity{

	public FireElemental (){
		damage = 0;
		health = 2;
		name = "FireElemental";
	
	} 
	
	public void move (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
		if((entityPosition.getNorth() != null 
				 && entityPosition.getNorth().equals(playersPosition)) 
			|| 
				(		   entityPosition.getNorth() != null 
						&& entityPosition.getNorth().getNorth() != null 
						&& entityPosition.getNorth().getNorth().equals(playersPosition))) {
			
			FireBall north = new FireBall();
			int direction = 0;
			barrierCanvas.getEntityDirectory().add(north);
			north.entityPosition=entityPosition;
			north.scheduleShooting(player, barrierCanvas, direction);
			
			this.death(barrierCanvas);
			}
		
		if((entityPosition.getSouth() != null 
				 && entityPosition.getSouth().equals(playersPosition)) 
			|| 
				(		   entityPosition.getSouth() != null 
						&& entityPosition.getSouth().getSouth() != null 
						&& entityPosition.getSouth().getSouth().equals(playersPosition))) {
			
			FireBall south = new FireBall();
			int direction = 1;
			barrierCanvas.getEntityDirectory().add(south);
			south.entityPosition=entityPosition;
			south.scheduleShooting(player, barrierCanvas, direction);
			
			this.death(barrierCanvas);
			}
	
		if((entityPosition.getWest() != null 
				 && entityPosition.getWest().equals(playersPosition)) 
			|| 
				(		   entityPosition.getWest() != null 
						&& entityPosition.getWest().getWest() != null 
						&& entityPosition.getWest().getWest().equals(playersPosition))){
			
			FireBall west = new FireBall();
				int direction = 2;
				barrierCanvas.getEntityDirectory().add(west);
				west.entityPosition=entityPosition;
				west.scheduleShooting(player, barrierCanvas, direction);
			
				this.death(barrierCanvas);
				}
	
		if((entityPosition.getEast() != null 
				 && entityPosition.getEast().equals(playersPosition)) 
			|| 
				(		   entityPosition.getEast() != null 
						&& entityPosition.getEast().getEast() != null 
						&& entityPosition.getEast().getEast().equals(playersPosition))) {
			
			FireBall east = new FireBall();
			barrierCanvas.getEntityDirectory().add(east);
			east.entityPosition=entityPosition;
			int direction = 3;
			east.scheduleShooting(player, barrierCanvas, direction);
			
			this.death(barrierCanvas);
			} 
	}
	
	
	public void scheduleAttack(Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
		
	}
	
	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
	
		try {
		
			int width;
			int height;
			double doubleX;
			double doubleY;
			InputStream imageInputStreamFireElemental = BarrierCanvas.class.getResourceAsStream("fireElemental.png");
			BufferedImage imgFireElemental = ImageIO.read(imageInputStreamFireElemental);
			width = canvasWidth/6;
			height = canvasHeight/6;
	
			doubleX = width * segmentWidth;
			doubleY = height * segmentHeight;
	
			int x = (int)doubleX;
			int y = (int)doubleY;
			g2.drawImage(imgFireElemental, x ,y ,width ,height, null);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
