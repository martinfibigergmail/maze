package cz.pf.model;
import java.util.Timer;
import java.util.TimerTask;

public class Entity {
	
	public Room entityPosition;
	public double timer;
	public int health;
	public String name;
	public int damage;
	
	
		public void move (Room playersPosition,Player player) {
			
			if(entityPosition.getWest()!=null && entityPosition.getWest().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player);
			}
			if(entityPosition.getWest()!=null && entityPosition.getEast().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player);
			}
			if(entityPosition.getWest()!=null && entityPosition.getSouth().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player);
			}
			if(entityPosition.getWest()!=null && entityPosition.getNorth().equals(playersPosition)) {
				this.scheduleReposition( playersPosition, player);
			}
			scheduleAttack( playersPosition, player);
		}
		public void scheduleReposition (Room playersPosition,Player player) {
	
			Timer timer;
			int delay = 500;
			int period = 500;
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					
					entityPosition = playersPosition;	
					timer.cancel();
				}
			}, delay, period);
			
		}
		public void scheduleAttack(Room playersPosition,Player player) {
			if(entityPosition.equals(playersPosition)) {
				Timer timer;
				int delay = 500;
				int period = 500;
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {

					public void run() {
						if(entityPosition.equals(playersPosition)) {
							player.health = player.health-1;
							
						}
						timer.cancel();
					}
				}, delay, period);
			}
		}
	}