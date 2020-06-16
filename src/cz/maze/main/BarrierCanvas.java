package cz.maze.main;

import javax.imageio.ImageIO;

import cz.pf.MainMap;
import cz.pf.model.Item;
import cz.pf.model.LockerRoom;
import cz.pf.model.Room;

import java.awt.*;
    import cz.pf.model.Player;
	import java.io.File;
	import java.util.ArrayList;
	import java.util.stream.Collector;
	import java.awt.event.KeyListener;
	import java.awt.event.KeyEvent;
	
	
	public class BarrierCanvas extends Canvas implements KeyListener{
		public Room playersPosition; 
	    Room map;
	    Player player = new Player();
	    public BarrierCanvas(){
	    	Item key = new Item("žlutej klíč",Color.YELLOW, 1);
	    	MainMap support= new MainMap();
	        map = support.createMap(key);
	        
	       playersPosition = map;
	       addKeyListener(this);
	       
	       if(playersPosition instanceof LockerRoom) {
       		LockerRoom lockerRoom = (LockerRoom)playersPosition;
       		
       			
       		}
       			
       		
       		
       	}

	    
	    
	
		public ArrayList actions = new ArrayList();
		
		@Override
		public void keyTyped(KeyEvent e) {
		


	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (!actions.contains(event.getKeyCode())) {
			actions.add(new Integer(event.getKeyCode()));

		}
		System.out.println("Pressed" + event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event) {

		if (actions.contains(KeyEvent.VK_W)) {

			if (playersPosition.getNorth() != null) {
				playersPosition = playersPosition.getNorth();
			}

		}

		if (actions.contains(KeyEvent.VK_S)) {

			if (playersPosition.getSouth() != null) {
				playersPosition = playersPosition.getSouth();
			}

		}

		if (actions.contains(KeyEvent.VK_A)) {

			if (playersPosition.getWest() != null) {
				playersPosition = playersPosition.getWest();
			}

		}


		if (actions.contains(KeyEvent.VK_D)) {

			if (playersPosition.getEast() != null) {
				playersPosition = playersPosition.getEast();
			}

		}
		if (actions.contains(KeyEvent.VK_E)) {
			 playersPosition.getItems().size(); 
				 
			 for(int cycle = 0;cycle<playersPosition.getItems().size();cycle ++) {
				 if(playersPosition.getItems().get(cycle).getType()==1) {
					 player.getItems().add(playersPosition.getItems().get(cycle)); 
				 }
			 }
			 
		}
		repaint();
		actions.remove(new Integer(event.getKeyCode()));
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		int canvasWidth = this.getWidth();
		int canvasHeight = this.getHeight();

		drawRectangle(g2, canvasWidth, canvasHeight);

		drawPlayer(g2, canvasWidth, canvasHeight);
		drawNorthernRoom(g2, canvasWidth, canvasHeight);
		drawEasternRoom(g2, canvasWidth, canvasHeight);
		drawWesternRoom(g2, canvasWidth, canvasHeight);
		drawSouthernRoom(g2, canvasWidth, canvasHeight);

		drawShadowCorners(g2, canvasWidth, canvasHeight);

	}

	private void drawRectangle(Graphics2D g2, int canvasWidth, int canvasHeight) {
		g2.setColor(Color.black);
		g2.drawRect(0, 0, canvasWidth - 1, canvasHeight - 1);
	}

	private void drawPlayer(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		width = canvasWidth / 2;
    	x =	width - 50;
    	height = canvasHeight / 2;	
    	y = height - 50;
    	g2.setColor(Color.black);
        g2.drawOval(x, y, 100, 100);
        g2.setColor(Color.red);
        g2.fillOval(x, y, 100, 100);
		for(Item item : playersPosition.getItems()) {
			if(item.getType() == 1){
            	g2.setColor(item.getColor());	            	
            	g2.drawOval(x, y, 20, 20);
            }
		}
        
	}  
	

	private void drawShadowCorners(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int boxWidth = canvasWidth / 3;
		int boxHeight = canvasHeight / 3;

		int x = 0;
		int y = 0;
		
		
		if (!((playersPosition.getWest() != null && playersPosition.getWest().getNorth() != null))
				&& (!(playersPosition.getNorth() != null && playersPosition.getNorth().getWest() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (playersPosition.getWest() instanceof LockerRoom) {
				LockerRoom lockerRoomWest = (LockerRoom) playersPosition.getWest();
				if ((playersPosition.getWest() != null && lockerRoomWest.getLockedNorth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (playersPosition.getNorth() instanceof LockerRoom) {
				LockerRoom lockerRoomNorth = (LockerRoom) playersPosition.getNorth();

				if ((playersPosition.getNorth() != null && lockerRoomNorth.getLockedWest() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");
					g2.setColor(lockerRoomNorth.getKeyWest().getColor());/* to do:solve null point exception*/
					g2.fillOval(x, y, boxWidth, boxHeight);
					

				}
			}
		}

		x = 0;
		y = boxHeight * 2;

		if (!((playersPosition.getWest() != null && playersPosition.getWest().getSouth() != null))
				&& (!(playersPosition.getSouth() != null && playersPosition.getSouth().getWest() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (playersPosition.getWest() instanceof LockerRoom) {
				LockerRoom lockerRoomWest = (LockerRoom) playersPosition.getWest();
				if ((playersPosition.getWest() != null && lockerRoomWest.getLockedSouth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (playersPosition.getSouth() instanceof LockerRoom) {
				LockerRoom lockerRoomSouth = (LockerRoom) playersPosition.getSouth();

				if ((playersPosition.getSouth() != null && lockerRoomSouth.getLockedWest() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");
					g2.setColor(lockerRoomSouth.getKeyWest().getColor());/* to do:solve null point exception*/
					g2.fillOval(x, y, boxWidth, boxHeight);
					

				}
			}
		}

		x = boxWidth * 2;
		y = 0;

		if (!((playersPosition.getEast() != null && playersPosition.getEast().getNorth() != null))
				&& (!(playersPosition.getNorth() != null && playersPosition.getNorth().getEast() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (playersPosition.getEast() instanceof LockerRoom) {
				LockerRoom lockerRoomEast = (LockerRoom) playersPosition.getEast();
				if ((playersPosition.getEast() != null && lockerRoomEast.getLockedNorth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (playersPosition.getNorth() instanceof LockerRoom) {
				LockerRoom lockerRoomNorth = (LockerRoom) playersPosition.getNorth();

				if ((playersPosition.getNorth() != null && lockerRoomNorth.getLockedEast() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");
					g2.setColor(lockerRoomNorth.getKeyEast().getColor());/* to do:solve null point exception*/
					g2.fillOval(x, y, boxWidth, boxHeight);
					

				}
			}
		}

		x = boxWidth * 2;
		y = boxHeight * 2;

		if (!((playersPosition.getEast() != null && playersPosition.getEast().getSouth() != null))
				&& (!(playersPosition.getSouth() != null && playersPosition.getSouth().getEast() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (playersPosition.getEast() instanceof LockerRoom) {
				LockerRoom lockerRoomEast = (LockerRoom) playersPosition.getEast();
				if ((playersPosition.getEast() != null && lockerRoomEast.getLockedSouth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (playersPosition.getSouth() instanceof LockerRoom) {
				LockerRoom lockerRoomSouth = (LockerRoom) playersPosition.getSouth();

				if ((playersPosition.getSouth() != null && lockerRoomSouth.getLockedEast() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");
					g2.setColor(lockerRoomSouth.getKeyEast().getColor());/* to do:solve null point exception*/
					g2.fillOval(x, y, boxWidth, boxHeight);
					

				}
			}
		}

	}

	private void drawBox(Graphics2D g2, int x, int y, int width, int height) {
		g2.setColor(Color.black);
		g2.fillRect(x, y, width, height);
	}

	private void drawSouthernRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		if (playersPosition.getSouth() == null) {
			width = canvasWidth / 3;
			x = width;
			height = canvasHeight / 3;
			y = 2 * height;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) playersPosition;
				if (lockerRoom.getLockedSouth() != null) {

					g2.setColor(Color.gray);
				}

				if (lockerRoom.getKeySouth() != null) {
					keyColor = lockerRoom.getKeySouth().getColor();

				}
			}

			g2.fillRect(x, y, width, height);

			if (keyColor != null) {
				g2.setColor(keyColor);
				g2.fillOval(x, y, width, height);
			}

		}

	}

	private void drawWesternRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		if (playersPosition.getWest() == null) {
			width = canvasWidth / 3;
			x = 0;
			height = canvasHeight / 3;
			y = height;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) playersPosition;
				if (lockerRoom.getLockedWest() != null) {

					g2.setColor(Color.gray);
				}

				if (lockerRoom.getKeyWest() != null) {
					keyColor = lockerRoom.getKeyWest().getColor();

				}
			}
			g2.fillRect(x, y, width, height);

			if (keyColor != null) {
				g2.setColor(keyColor);
				g2.fillOval(x, y, width, height);
			}

		}

	}

	private void drawEasternRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		if (playersPosition.getEast() == null) {
			width = canvasWidth / 3;
			x = 2 * width;
			height = canvasHeight / 3;
			y = height;

			g2.setColor(Color.black);

			Color keyColor = null;

			if (playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) playersPosition;
				if (lockerRoom.getLockedEast() != null) {

					g2.setColor(Color.gray);
				}

				if (lockerRoom.getKeyEast() != null) {
					keyColor = lockerRoom.getKeyEast().getColor();

				}
			}
			g2.fillRect(x, y, width, height);

			if (keyColor != null) {
				g2.setColor(keyColor);
				g2.fillOval(x, y, width, height);

			}

		}
	}

	private void drawNorthernRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		if (playersPosition.getNorth() == null) {
			width = canvasWidth / 3;
			x = width;
			height = canvasHeight / 3;
			y = 0;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) playersPosition;
				if (lockerRoom.getLockedNorth() != null) {

					g2.setColor(Color.gray);
				}

				if (lockerRoom.getKeyNorth() != null) {
					keyColor = lockerRoom.getKeyNorth().getColor();

				}
			}
			g2.fillRect(x, y, width, height);

			if (keyColor != null) {
				g2.setColor(keyColor);
				g2.fillOval(x, y, width, height);
			}

		}

	}
}
