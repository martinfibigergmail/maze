package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;

	public class WaterWall extends Entity {
		
		public WaterWall (){
			damage = 1;
			health = 10000;
			name = "WaterWall";
			
		}
		
		public void move (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
			
			scheduleAttack( playersPosition, player, barrierCanvas);
			
		}
		public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
			
			try {
				
			int width;
			int height;
			double doubleX;
			double doubleY;
			InputStream imageInputStreamWaterWall = BarrierCanvas.class.getResourceAsStream("waterWall.png");
			BufferedImage imgWaterWall = ImageIO.read(imageInputStreamWaterWall);
			width = canvasWidth/6;
			height = canvasHeight/6;
			
			doubleX = width * segmentWidth;
			doubleY = height * segmentHeight;
			
			int x = (int)doubleX;
			int y = (int)doubleY;
		
			
			g2.drawImage(imgWaterWall, x ,y ,width ,height, null);
			
			
		}
			
			
			catch (IOException e) {
				e.printStackTrace();
				
			}
			
		}
	}

