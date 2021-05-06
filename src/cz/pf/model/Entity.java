package cz.pf.model;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import cz.maze.main.BarrierCanvas;
import cz.pf.Sound;

public class Entity {
	
	public Room entityPosition;
	public double timer;
	public int health;
	public String name;
	public int damage;
	public Sound slash1;
	public Sound slash2;
	public Entity() {
		slash1 = new Sound();
		slash1.file = "LeopardStrike1.wav";
        slash2 = new Sound();
        slash2.file = "LeopardStrike2.wav";
	}
	
		public void death (BarrierCanvas barrierCanvas) {
				
		}
		
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
		
		public void shoot (Room playersPosition,Player player, BarrierCanvas barrierCanvas) {
			
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
						if(entityPosition.equals(player.playersPosition)) {
							player.health = player.health-damage;
							Random slashSound = new Random();
							int slash;
							slash = 1 + slashSound.nextInt(2);
							if(slash == 1) {
								slash1.play();
							}
							if(slash == 2) {
								slash2.play();
							}
							if (player.health <= 0 ) {
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