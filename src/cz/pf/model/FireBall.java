package cz.pf.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import cz.maze.main.BarrierCanvas;
import cz.maze.main.MazeMain;

public class FireBall extends Entity {

	int direction;

	public FireBall() {

		health = 1;
		damage = 0;
		name = "FireBall";

	}

	public void scheduleShooting(Player player, BarrierCanvas barrierCanvas, int direction) {

		Timer timer;
		int delay = 750;
		int period = 750;
		timer = new Timer();
		this.direction = direction;
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				if (entityPosition == null) {
					timer.cancel();
					synchronized (barrierCanvas) {
						death(barrierCanvas);
					}
					return;

				}
				if (direction == 0) {
					if (entityPosition.getNorth() == null) { synchronized (barrierCanvas) {
						FireElemental kid = new FireElemental  (); 
						barrierCanvas.getEntityDirectory().add(kid);
						kid.entityPosition=entityPosition;
						death(barrierCanvas);
						timer.cancel();
					
						}
				
			
					}
					entityPosition = entityPosition.getNorth();
					processHit(player, barrierCanvas, timer);

				}
				if (direction == 1) {
					if (entityPosition.getSouth() == null) { synchronized (barrierCanvas) {
						FireElemental kid = new FireElemental  (); 
						barrierCanvas.getEntityDirectory().add(kid);
						kid.entityPosition=entityPosition;
						death(barrierCanvas);
						timer.cancel();
						}
				
			
					}
					entityPosition = entityPosition.getSouth();
					processHit(player, barrierCanvas, timer);

				}
				if (direction == 2) {
					if (entityPosition.getWest() == null) { synchronized (barrierCanvas) {
						FireElemental kid = new FireElemental  (); 
						barrierCanvas.getEntityDirectory().add(kid);
						kid.entityPosition=entityPosition;
						death(barrierCanvas);
						timer.cancel();
						}
				
			
					}
					entityPosition = entityPosition.getWest();
					processHit(player, barrierCanvas, timer);

				}
				if (direction == 3) {
					if (entityPosition.getEast() == null) { synchronized (barrierCanvas) {
						FireElemental kid = new FireElemental  (); 
						barrierCanvas.getEntityDirectory().add(kid);
						kid.entityPosition=entityPosition;
						death(barrierCanvas);
						timer.cancel();
						}
				
			
					}
					entityPosition = entityPosition.getEast();
					processHit(player, barrierCanvas, timer);

				}
				if(MazeMain.running) {		
				barrierCanvas.repaint();
				}
			}

		}, delay, period);

	}

	public void processHit(Player player, BarrierCanvas barrierCanvas, Timer timer) {

		if (entityPosition == null) {
			synchronized (barrierCanvas) {
				this.death(barrierCanvas);
				timer.cancel();
			}
			return;
		}

		if (entityPosition.equals(player.playersPosition)) {
			player.health = player.health - 1; 

			if (player.health <= 0) {
				MazeMain.running = false; 
			}
			synchronized (barrierCanvas) {
				this.death(barrierCanvas);
				timer.cancel();
			}  
		}
	}

	public void move(Room playersPosition, Player player, BarrierCanvas barrierCanvas) {

	}

	public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight) {

		int width;
		int height;
		double doubleX;
		double doubleY;
		try {
			String notebook = null;

			if (direction == 0) {
				notebook = "fireboltNorth.png";
			}

			if (direction == 1) {
				notebook = "fireboltsouth.png";
			}
			if (direction == 2) {
				notebook = "fireboltWest.png";
			}

			if (direction == 3) {
				notebook = "fireboltEast.png";
			}

			InputStream imageInputStreamArrowProjectile = BarrierCanvas.class.getResourceAsStream(notebook);

			width = canvasWidth / 6;
			height = canvasHeight / 6;

			doubleX = width * segmentWidth;
			doubleY = height * segmentHeight;

			int x = (int) doubleX;
			int y = (int) doubleY;

			BufferedImage imgArrowProjectile = ImageIO.read(imageInputStreamArrowProjectile);
			g2.drawImage(imgArrowProjectile, x, y, width, height, null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
