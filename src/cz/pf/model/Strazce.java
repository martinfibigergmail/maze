package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;

public class Strazce extends Entity {
	
	public Strazce (){
		damage = 2;
		health = 10;
		name = "Strazce";
		
	}
	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
		
		try {
			
		int width;
		int height;
		double doubleX;
		double doubleY;
		InputStream imageInputStreamStrazce = BarrierCanvas.class.getResourceAsStream("leopard.png");
		BufferedImage imgStrazce= ImageIO.read(imageInputStreamStrazce);
		width = canvasWidth/6;
		height = canvasHeight/6;
		
		doubleX = width * segmentWidth;
		doubleY = height * segmentHeight;
		
		int x = (int)doubleX;
		int y = (int)doubleY;
	
		
		g2.drawImage(imgStrazce, x ,y ,width ,height, null);
		
		
	}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
