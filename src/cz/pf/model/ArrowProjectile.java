package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;

public class ArrowProjectile extends Entity{
	public ArrowProjectile () {
		
	 health = 1;
	 damage = 0;
	 name = "arrowProjectile";
	 
	}
	
	public void scheduleShooting (Player player, BarrierCanvas barrierCanvas, int direction ) {
		
		Timer timer;
		int delay = 750;
		int period = 750;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				if(entityPosition == null) {
					timer.cancel();
					death(barrierCanvas);
					return;
				}
			
				if(direction == 0) {
					entityPosition = entityPosition.getNorth();
					shoot(player,barrierCanvas);
				}
				if(direction == 1) {
					entityPosition = entityPosition.getSouth();
					shoot(player,barrierCanvas);
				}
				if(direction == 2) {
					entityPosition = entityPosition.getWest();
					shoot(player,barrierCanvas);
				}
				if(direction == 3) {
					entityPosition = entityPosition.getEast();
					shoot(player,barrierCanvas);
				}
		
				barrierCanvas.repaint();
				timer.cancel();
				
			}
			
		}, delay, period);
		
	}
	public void shoot (Player player, BarrierCanvas barrierCanvas) {
		if(entityPosition.equals(null)) {
			this.death(barrierCanvas); 
		}
		if(entityPosition.equals(player.playersPosition )) {
			player.health = player.health-1;
			if(player.health < 0) {
				System.exit(0);
			}
		}
	}

	
	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
		
		int width;
		int height;
		double doubleX;
		double doubleY;
		try {
			InputStream imageInputStreamArrowProjectile = BarrierCanvas.class.getResourceAsStream("arrow.png");
			BufferedImage imgArrowProjectile= ImageIO.read(imageInputStreamArrowProjectile);
			width = canvasWidth/6;
			height = canvasHeight/6;
	
			doubleX = width * segmentWidth;
			doubleY = height * segmentHeight;
	
			int x = (int)doubleX;
			int y = (int)doubleY;

	
			g2.drawImage(imgArrowProjectile, x ,y ,width ,height, null);
	
	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
