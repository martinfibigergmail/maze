package cz.maze.main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cz.pf.MainMap;
import cz.pf.Sound;
import cz.pf.model.Entity;
import cz.pf.model.Item;
import cz.pf.model.LockerRoom;
import cz.pf.model.Room;

import java.awt.*;
import cz.pf.model.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class BarrierCanvas extends Canvas implements KeyListener , MouseListener {
	
	
	
	boolean menu = false ;
	Item key;
	Room map;
	Player player = new Player();
	String deathList;
	public String endMessage;
	ArrayList<Entity> entityDirectory  = new ArrayList();
	Sound wallHurt;
	public void makeEnd(){
		endMessage= "Umřels";
		
	}
	
	public java.util.List<Entity> getEntityDirectory() {
		return entityDirectory; 
	}
	private BufferStrategy strategy;
	public BarrierCanvas(JFrame f) {
		setIgnoreRepaint(true);
		JPanel panel = (JPanel) f.getContentPane();
		panel.add(this);
		panel.setPreferredSize(f.getSize());
		createBufferStrategy(2);
		strategy = getBufferStrategy();
			wallHurt = new Sound();
			wallHurt.file = "WallHurt.wav";
		InputStream imageInputStreamKey = BarrierCanvas.class.getResourceAsStream("ItemKeyYellow.png");
		try {
			BufferedImage imgKey = ImageIO.read(imageInputStreamKey);
			Item key = new Item("žlutej klíč", Color.YELLOW, 1, imgKey);
			MainMap support = new MainMap();
			map = support.createMap(key,entityDirectory);
			this.key = key;
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		player.playersPosition = map;
		addKeyListener(this);

		if (player.playersPosition instanceof LockerRoom) {
			LockerRoom lockerRoom = (LockerRoom) player.playersPosition;

		}
		addMouseListener(this);
		
	}
	
		
		
		
	public ArrayList actions = new ArrayList();
	
	public void activateEntities() {
		synchronized(this) {
			ArrayList<Integer> entitiesToDelete= new ArrayList<Integer>();
			for(int round = 0;round<entityDirectory.size();round++) {
				entityDirectory.get(round).move(player.playersPosition, player,this);
				//Entity  entity = entityDirectory.get(round);
			}
		}
	}
	

	
	public void newFrame() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());		
		g.setColor(getForeground());
		//System.out.println("here");
		paint(g);
		g.dispose();
		strategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (!actions.contains(event.getKeyCode())) {
			actions.add( Integer.valueOf(event.getKeyCode()));
			

		}
		//System.out.println("Pressed" + event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event) {
		/** To do as members */
		Sound steps = new Sound();
        steps.file = "stepsImproved.wav";
        
        Sound doors = new Sound();
        doors.file = "door_sound.wav";
        
        if(endMessage == null){
        	
        
				if (actions.contains(KeyEvent.VK_W)) {
					
					if(player.playersPosition.getNorth() == null) {
						//deathList +="North in wall";
						player.health = player.health - 1;
						wallHurt.play();
						
						if(player.health == 0) {
							makeEnd();					
						}
					}
		
					if (player.playersPosition.getNorth() != null) {
						player.playersPosition = player.playersPosition.getNorth();
						steps.play();
						activateEntities();			
					}
					
				}
		
				if (actions.contains(KeyEvent.VK_S)) {
					
					if(player.playersPosition.getSouth() == null) {
						//deathList +="South in wall";
						player.health = player.health - 1;
						wallHurt.play();
						if(player.health == 0) {
							makeEnd();
							
						}
					}
		
					if (player.playersPosition.getSouth() != null) {
						player.playersPosition = player.playersPosition.getSouth();
						steps.play();
						activateEntities();	
					}
				}
		
				if (actions.contains(KeyEvent.VK_A)) {
					
					if(player.playersPosition.getWest() == null) {
						//deathList +="West in wall";
						player.health = player.health - 1;
						wallHurt.play();
						if(player.health == 0) {
							makeEnd();		
						}
					}
		
					if (player.playersPosition.getWest() != null) {
						player.playersPosition = player.playersPosition.getWest();
						steps.play();
						activateEntities();	
					}
				}
		
				if (actions.contains(KeyEvent.VK_D)) {
					
					if(player.playersPosition.getEast() == null) {
						//deathList +="East in wall";
						player.health = player.health - 1;
						wallHurt.play();
						if(player.health == 0) {
							makeEnd();		
						}
					}
		
					if (player.playersPosition.getEast() != null) {
						player.playersPosition = player.playersPosition.getEast();
		 				steps.play();
		 				activateEntities();	
					}
				}
		
				if (actions.contains(KeyEvent.VK_SPACE)) {
		
					if (player.playersPosition.getUp() != null) {
						player.playersPosition = player.playersPosition.getUp();
						steps.play();
						activateEntities();	
					}
				}
				
				if (actions.contains(KeyEvent.VK_SHIFT)) {
		
					if (player.playersPosition.getDown() != null) {
						player.playersPosition = player.playersPosition.getDown();
						steps.play();
						activateEntities();	
					}
				}
				
				if (actions.contains(KeyEvent.VK_E)) {
					
					if(player.getItems().size() != 7 && player.playersPosition.getItems().size() != 0 ) {
						player.getItems().add(player.playersPosition.getItems().get(0));
						player.playersPosition.getItems().remove(0);
					
					}
				}
				
				if (actions.contains(KeyEvent.VK_Q)) {
					
					if(player.selectedItem < player.getItems().size()) {
						player.playersPosition.getItems().add(player.getItems().get(player.selectedItem));
						player.getItems().remove(player.selectedItem);
				
					}
				}
				
				if (actions.contains(KeyEvent.VK_ESCAPE)) {
					if(menu==false) {
						menu = true ;
					}
					else {
						menu=false;
					}
				}
				
				
				
				
				if(actions.contains(KeyEvent.VK_1)) {
					player.selectedItem = 0;
					
				}
				
				if(actions.contains(KeyEvent.VK_2)) {
					player.selectedItem = 1;
				}
			
				if(actions.contains(KeyEvent.VK_3)) {
					player.selectedItem = 2;
				}
				
				if(actions.contains(KeyEvent.VK_4)) {
					player.selectedItem = 3;
				}
				
				if(actions.contains(KeyEvent.VK_5)) {
					player.selectedItem = 4;
					
				}
				
				if(actions.contains(KeyEvent.VK_6)) {
					player.selectedItem = 5;
				}
				
				if(actions.contains(KeyEvent.VK_7)) {
					player.selectedItem = 6;
				}
		        
				System.out.println(player.playersPosition.getDescription());
				
				if (player.playersPosition instanceof LockerRoom) {
					LockerRoom playersPositionLocked = (LockerRoom) player.playersPosition;
					if (player.getItems().contains(playersPositionLocked.getKeyEast())
							&& playersPositionLocked.getEast() == null) {
						playersPositionLocked.setEast(playersPositionLocked.getLockedEast());
						doors.play();
					}
				}
		
				if (player.playersPosition instanceof LockerRoom) {
					LockerRoom playersPositionLocked = (LockerRoom) player.playersPosition;
					if (player.getItems().contains(playersPositionLocked.getKeyWest())
							&& playersPositionLocked.getWest() == null) {
						playersPositionLocked.setWest(playersPositionLocked.getLockedWest());
						doors.play();
					}
				}
		
				if (player.playersPosition instanceof LockerRoom) {
					LockerRoom playersPositionLocked = (LockerRoom) player.playersPosition;
					if (player.getItems().contains(playersPositionLocked.getKeySouth())
							&& playersPositionLocked.getSouth() == null) {
						playersPositionLocked.setSouth(playersPositionLocked.getLockedSouth());
						doors.play();
					}
				}
		
				if (player.playersPosition instanceof LockerRoom) {
					LockerRoom playersPositionLocked = (LockerRoom) player.playersPosition;
					if (player.getItems().contains(playersPositionLocked.getKeyNorth())
							&& playersPositionLocked.getNorth() == null) {
						playersPositionLocked.setNorth(playersPositionLocked.getLockedNorth());
						doors.play();
					}
				}
		} else {
			
			if(actions.contains(KeyEvent.VK_K)) {
				System.exit(0);
			}
			if(actions.contains(KeyEvent.VK_N)) {
				
			}
		
		}
		actions.remove(Integer.valueOf(event.getKeyCode()));
		this.exit();
		//repaint();

	}
	
	
	public void paint(Graphics g) {
		
		
		InputStream imageInputStreamItem = BarrierCanvas.class.getResourceAsStream("Itembar.png");
		InputStream imageInputStreamLife = BarrierCanvas.class.getResourceAsStream("LifeBorderless.png");
		
		Graphics2D g2 = (Graphics2D) g;
		int canvasWidth = this.getWidth();
		int canvasHeight = this.getHeight();

		drawRectangle(g2, canvasWidth, canvasHeight);
		drawPlayer(g2, canvasWidth, canvasHeight);
		drawStairs(g2, canvasWidth, canvasHeight);
		drawMenu(g2, canvasWidth, canvasHeight);
		
		 Entity creature;
		for(int counter = 0;counter<entityDirectory.size(); counter ++) {
			creature = entityDirectory.get(counter);
			if (player.playersPosition.equals(creature.entityPosition)) {
				creature.draw(g2, canvasWidth, canvasHeight,2.5f,2.5f);
			
			}
				
			if (player.playersPosition.getNorth()!=null && player.playersPosition.getNorth().equals(creature.entityPosition)) {
				creature.draw(g2, canvasWidth, canvasHeight,2.5f, 0.5f);
				
			}

			if (player.playersPosition.getSouth()!=null && player.playersPosition.getSouth().equals(creature.entityPosition)) {
				creature.draw(g2, canvasWidth, canvasHeight, 2.5f,4.5f);
		
			}
		
	
			if (player.playersPosition.getWest()!=null && player.playersPosition.getWest().equals(creature.entityPosition)) {
				creature.draw(g2, canvasWidth, canvasHeight, 0.5f, 2.5f );
	
			}
		 

			if (player.playersPosition.getEast()!=null && player.playersPosition.getEast().equals(creature.entityPosition)) {
				creature.draw(g2, canvasWidth, canvasHeight, 4.5f,2.5f);

			}
			if (((player.playersPosition.getWest() != null && player.playersPosition.getWest().getNorth() != null 
				&& player.playersPosition.getWest().getNorth().equals(creature.entityPosition)))
					|| ((player.playersPosition.getNorth() != null && player.playersPosition.getNorth().getWest() != null 
						&& player.playersPosition.getNorth().getWest().equals(creature.entityPosition)))) {
				creature.draw(g2, canvasWidth, canvasHeight, 0.5f,0.5f);
			}
			if (((player.playersPosition.getEast() != null && player.playersPosition.getEast().getNorth() != null 
					&& player.playersPosition.getEast().getNorth().equals(creature.entityPosition)))
						|| ((player.playersPosition.getNorth() != null && player.playersPosition.getNorth().getEast() != null 
							&& player.playersPosition.getNorth().getEast().equals(creature.entityPosition)))) {
					creature.draw(g2, canvasWidth, canvasHeight, 4.5f,0.5f);
				}
			if (((player.playersPosition.getWest() != null && player.playersPosition.getWest().getSouth() != null 
					&& player.playersPosition.getWest().getSouth().equals(creature.entityPosition)))
						|| ((player.playersPosition.getSouth() != null && player.playersPosition.getSouth().getWest() != null 
							&& player.playersPosition.getSouth().getWest().equals(creature.entityPosition)))) {
					creature.draw(g2, canvasWidth, canvasHeight, 0.5f,4.5f);
				}
			if (((player.playersPosition.getEast() != null && player.playersPosition.getEast().getSouth() != null 
					&& player.playersPosition.getEast().getSouth().equals(creature.entityPosition)))
						|| ((player.playersPosition.getSouth() != null && player.playersPosition.getSouth().getEast() != null 
							&& player.playersPosition.getSouth().getEast().equals(creature.entityPosition)))) {
					creature.draw(g2, canvasWidth, canvasHeight, 4.5f,4.5f);
				}
		}			
				
				
			
		
		
		
		drawNorthernRoom(g2, canvasWidth, canvasHeight);
		drawEasternRoom(g2, canvasWidth, canvasHeight);
		drawWesternRoom(g2, canvasWidth, canvasHeight);
		drawSouthernRoom(g2, canvasWidth, canvasHeight);
		drawShadowCorners(g2, canvasWidth, canvasHeight);
		g2.setColor(Color.green);
		g2.drawString(player.playersPosition.description, canvasWidth / 20, canvasHeight - canvasHeight / 15);
		


		try {
			BufferedImage imgItem = ImageIO.read(imageInputStreamItem);
			BufferedImage imgLife = ImageIO.read(imageInputStreamLife);
			
			g.drawImage(imgItem, canvasWidth - imgItem.getWidth(),0,null);
			
			for(int hp = 0;hp < player.health; hp++ ) {
				g.drawImage(imgLife, hp*imgLife.getWidth(),0, null);
			}
		
			int Y = 5;
			for(int itemCounter = 0; itemCounter < player.getItems().size() ;itemCounter++,Y=Y+50) {
				//System.out.println(player.getItems().get(itemCounter).getDescription());
				//System.out.println(Y);
				g.drawImage(player.getItems().get(itemCounter).getItemImage(), canvasWidth-70, Y,null);
			}
			
			g.setColor(Color.RED);
			g.drawRect(canvasWidth-71, 4+49*player.selectedItem, 66, 47);
			g.drawRect(canvasWidth-72, 3+49*player.selectedItem, 68, 49);
			g.drawRect(canvasWidth-73, 2+49*player.selectedItem, 70, 51);
			
			if(endMessage != null) {
				g.setColor(Color.BLACK);
				g.fillRect(canvasWidth/3, canvasHeight/3, canvasWidth/3, canvasHeight/3);
				g.setColor(Color.RED);
				g.drawRect(canvasWidth/3, canvasHeight/3, canvasWidth/3, canvasHeight/3);
				g.drawRect(canvasWidth/3+1, canvasHeight/3+1, canvasWidth/3-2, canvasHeight/3-2);
				Font originalFont= g.getFont();
				Font endMenuFont = new Font("Verdana", Font.BOLD, canvasWidth/40); 
				g.setFont(endMenuFont);
				g.drawString(endMessage, canvasWidth/3+canvasWidth/40, canvasHeight/3+canvasHeight/10);
				g.setFont(originalFont);
				
			}
			
		}
		
		catch (IOException e) {
			e.printStackTrace();
		 }
		
		

	}

	private void drawRectangle(Graphics2D g2, int canvasWidth, int canvasHeight) {
		g2.setColor(Color.black);
		g2.drawRect(0, 0, canvasWidth - 1, canvasHeight - 1);
	}

	private void drawPlayer(Graphics2D g2, int canvasWidth, int canvasHeight) {
		//System.out.println("draw player");
		int width;
		int height;
		int x;
		int y;
		int playerTopMostPoint;
		int playerLeftMostPoint;
		int halfImageWidth;
		int halfImageHeight;
		width = canvasWidth / 2;
		playerLeftMostPoint = canvasWidth / 3;
		x = width - 50;
		height = canvasHeight / 2;
		playerTopMostPoint = canvasHeight / 3;
		y = height - 50;
		
		int playerImageWidth;		
		int playerImageHeight;
		playerImageWidth = canvasWidth / 9;
		playerImageHeight = canvasHeight / 6;
		halfImageWidth = playerImageWidth /2;
		halfImageHeight = playerImageHeight /2;
		playerLeftMostPoint = width - halfImageWidth; 
		playerTopMostPoint = height - halfImageHeight;
		
		
		try{
			InputStream imageInputStreamHero = BarrierCanvas.class.getResourceAsStream("adventurer.png");
			BufferedImage imgAdventurer = ImageIO.read(imageInputStreamHero);
			g2.drawImage(imgAdventurer, playerLeftMostPoint, playerTopMostPoint, playerImageWidth, playerImageHeight, null);
		
		} catch(IOException adventurerEx) {
			System.out.println("adventurerEx triggered"+adventurerEx.getMessage());
			System.exit(0);
		}
		
		
		/*g2.setColor(Color.black);
		g2.drawOval(x, y, 100, 100);
		g2.setColor(Color.red);
		g2.fillOval(x, y, 100, 100);*/  
		
		for (Item item : player.playersPosition.getItems()) {
			if (item.getType() == 1) {
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

		if (!((player.playersPosition.getWest() != null && player.playersPosition.getWest().getNorth() != null))
				&& (!(player.playersPosition.getNorth() != null && player.playersPosition.getNorth().getWest() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (player.playersPosition.getWest() instanceof LockerRoom) {
				LockerRoom lockerRoomWest = (LockerRoom) player.playersPosition.getWest();
				if ((player.playersPosition.getWest() != null && lockerRoomWest.getLockedNorth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");

				}
			}
			if (player.playersPosition.getNorth() instanceof LockerRoom) {
				LockerRoom lockerRoomNorth = (LockerRoom) player.playersPosition.getNorth();

				if ((player.playersPosition.getNorth() != null && lockerRoomNorth.getLockedWest() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					// System.out.println("jsem tu");
					g2.setColor(lockerRoomNorth.getKeyWest().getColor());/* to do:solve null point exception */
					g2.fillOval(x, y, boxWidth, boxHeight);

				}
			}
		}

		x = 0;
		y = boxHeight * 2;

		if (!((player.playersPosition.getWest() != null && player.playersPosition.getWest().getSouth() != null))
				&& (!(player.playersPosition.getSouth() != null && player.playersPosition.getSouth().getWest() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (player.playersPosition.getWest() instanceof LockerRoom) {
				LockerRoom lockerRoomWest = (LockerRoom) player.playersPosition.getWest();
				if ((player.playersPosition.getWest() != null && lockerRoomWest.getLockedSouth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					//System.out.println("jsem tu");

				}
			}
			if (player.playersPosition.getSouth() instanceof LockerRoom) {
				LockerRoom lockerRoomSouth = (LockerRoom) player.playersPosition.getSouth();

				if ((player.playersPosition.getSouth() != null && lockerRoomSouth.getLockedWest() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					// System.out.println("jsem tu");
					g2.setColor(lockerRoomSouth.getKeyWest().getColor());/* to do:solve null point exception */
					g2.fillOval(x, y, boxWidth, boxHeight);

				}
			}
		}

		x = boxWidth * 2;
		y = 0;

		if (!((player.playersPosition.getEast() != null && player.playersPosition.getEast().getNorth() != null))
				&& (!(player.playersPosition.getNorth() != null && player.playersPosition.getNorth().getEast() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (player.playersPosition.getEast() instanceof LockerRoom) {
				LockerRoom lockerRoomEast = (LockerRoom) player.playersPosition.getEast();
				if ((player.playersPosition.getEast() != null && lockerRoomEast.getLockedNorth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (player.playersPosition.getNorth() instanceof LockerRoom) {
				LockerRoom lockerRoomNorth = (LockerRoom) player.playersPosition.getNorth();

				if ((player.playersPosition.getNorth() != null && lockerRoomNorth.getLockedEast() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					// System.out.println("jsem tu");
					g2.setColor(lockerRoomNorth.getKeyEast().getColor());/* to do:solve null point exception */
					g2.fillOval(x, y, boxWidth, boxHeight);

				}
			}
		}

		x = boxWidth * 2;
		y = boxHeight * 2;

		if (!((player.playersPosition.getEast() != null && player.playersPosition.getEast().getSouth() != null))
				&& (!(player.playersPosition.getSouth() != null && player.playersPosition.getSouth().getEast() != null))) {
			g2.setColor(Color.black);
			drawBox(g2, x, y, boxWidth, boxHeight);

			if (player.playersPosition.getEast() instanceof LockerRoom) {
				LockerRoom lockerRoomEast = (LockerRoom) player.playersPosition.getEast();
				if ((player.playersPosition.getEast() != null && lockerRoomEast.getLockedSouth() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					System.out.println("jsem tu");

				}
			}
			if (player.playersPosition.getSouth() instanceof LockerRoom) {
				LockerRoom lockerRoomSouth = (LockerRoom) player.playersPosition.getSouth();

				if ((player.playersPosition.getSouth() != null && lockerRoomSouth.getLockedEast() != null)) {
					g2.setColor(Color.gray);
					g2.fillRect(x, y, boxWidth, boxHeight);
					// System.out.println("jsem tu");
					g2.setColor(lockerRoomSouth.getKeyEast().getColor());/* to do:solve null point exception */
					g2.fillOval(x, y, boxWidth, boxHeight);

				}
			}
		}

	}

	public void drawStairs(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		if (player.playersPosition.getDown() != null) {
			g2.setColor(Color.gray);
			width = canvasWidth / 3;
			x = width;
			height = canvasHeight / 3;
			y = height;

			g2.fillRect(x, y, width, height);
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
		if (player.playersPosition.getSouth() == null) {
			width = canvasWidth / 3;
			x = width;
			height = canvasHeight / 3;
			y = 2 * height;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (player.playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) player.playersPosition;
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
		if (player.playersPosition.getWest() == null) {
			width = canvasWidth / 3;
			x = 0;
			height = canvasHeight / 3;
			y = height;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (player.playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) player.playersPosition;
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
		if (player.playersPosition.getEast() == null) {
			width = canvasWidth / 3;
			x = 2 * width;
			height = canvasHeight / 3;
			y = height;

			g2.setColor(Color.black);

			Color keyColor = null;

			if (player.playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) player.playersPosition;
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
		if (player.playersPosition.getNorth() == null) {
			width = canvasWidth / 3;
			x = width;
			height = canvasHeight / 3;
			y = 0;

			g2.setColor(Color.black);

			Color keyColor = null;
			if (player.playersPosition instanceof LockerRoom) {
				LockerRoom lockerRoom = (LockerRoom) player.playersPosition;
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
	private void drawMenu(Graphics2D g2, int canvasWidth, int canvasHeight) {
		int width;
		int height;
		int x;
		int y;
		int widthButton;
		int heightButton;
		int xButton;
		int yButton1;
		int yButton2;
		int yButton3;
		
		width =  canvasWidth / 4;
		height =  canvasHeight / 2; 
		x = canvasWidth /2 - canvasWidth/8 ;
		y = canvasHeight /4 ;
		widthButton = width/2;
		heightButton = height/7;
		xButton = x+width/4;
		yButton1 = y+height/7;
		yButton2 = y+3*height/7;
		yButton3 = y+5*height/7;
		if(menu == true) {
			
			g2.setColor(Color.darkGray);
			g2.fillRect(x, y, width, height);
			g2.setColor(Color.lightGray);
			g2.fillRect(xButton, yButton1, widthButton, heightButton);
			g2.setColor(Color.lightGray);
			g2.fillRect(xButton, yButton2, widthButton, heightButton);
			g2.setColor(Color.lightGray);
			g2.fillRect(xButton, yButton3, widthButton, heightButton);

		} 
	
	}
	

	public void exit() {

		if (player.playersPosition.isExit() == true && player.getItems().contains(this.key)) {
			endMessage = "Vyhráls";
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
