package cz.pf.model;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import cz.maze.main.BarrierCanvas;

public class Entity {
	
	public Room entityPosition;
	public double timer;
	public int health;
	public String name;
	public int damage;
	
	
		public void move (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
			
			if(entityPosition.getWest()!=null && entityPosition.getWest().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player, barrierCanvas);
			}
			if(entityPosition.getEast()!=null && entityPosition.getEast().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player, barrierCanvas);
			}
			if(entityPosition.getSouth()!=null && entityPosition.getSouth().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player, barrierCanvas);
			}
			if(entityPosition.getNorth()!=null && entityPosition.getNorth().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player, barrierCanvas);
			}
			scheduleAttack( playersPosition, player, barrierCanvas);
		}
		public void scheduleReposition (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
	
			Timer timer;
			int delay = 750;
			int period = 750;
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					//System.out.println("Entity position:" + entityPosition.getDescription()); 
					//System.out.println("Players position at time of scheduling:" +playersPosition.getDescription());
					//System.out.println("Players actual position:" +player.playersPosition.getDescription());
					entityPosition = playersPosition;	
					barrierCanvas.repaint();
					scheduleAttack( player.playersPosition, player, barrierCanvas);
					timer.cancel();
					
				}
				
			}, delay, period);
			
		}
	    public void draw(Graphics2D g2, int canvasWidth, int canvasHeight, float segmentWidth, float segmentHeight ) {
			
		}
		public void scheduleAttack(Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
			if(entityPosition.equals(playersPosition)) {
				Timer timer;
				int delay = 1000;
				int period = 1000;
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {

					public void run() {
						if(entityPosition.equals(playersPosition)) {
							player.health = player.health-1;
							if (player.health == 0 ) {
								System.exit(0);
							}
							barrierCanvas.repaint();
						} else {
							
							timer.cancel();
						
						}
					}
				}, delay, period);
			}
		}
	}