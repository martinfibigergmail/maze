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
	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
		
		int width;
		int height;
		double doubleX;
		double doubleY;
		try {
			InputStream imageInputStreamArcher = BarrierCanvas.class.getResourceAsStream("knight.png");
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
