package cz.maze.main;

	import javax.imageio.ImageIO;

import cz.pf.Main;
import cz.pf.model.Item;
import cz.pf.model.LockerRoom;
import cz.pf.model.Room;

import java.awt.*;
	import java.io.File;
	import java.util.ArrayList;
	import java.util.stream.Collector;
	import java.awt.event.KeyListener;
	import java.awt.event.KeyEvent;
	
	
	public class BarrierCanvas extends Canvas implements KeyListener{
	    public ArrayList buttons= new ArrayList();
	    public String display="0";
	    public int x;
	    public int y;
		public Room playersPosition; 
	    Room map;
	    public BarrierCanvas(){
	    	Item key = new Item("žlutej klíč",Color.YELLOW);
	    	Main support= new Main();
	        map=support.createMap(key);
	        
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
		public void keyPressed(KeyEvent event){
			if(!actions.contains(event.getKeyCode())) {
				actions.add(new Integer(event.getKeyCode()));
				
			
			}
			System.out.println("Pressed" + event.getKeyCode() );
		}
		@Override
		public void  keyReleased(KeyEvent event) {
			
				if(actions.contains(KeyEvent.VK_W)) {
				
					if(playersPosition.getNorth()!=null) {
						playersPosition=playersPosition.getNorth();
					}
					
				}
				
				if(actions.contains(KeyEvent.VK_S)){
					
					if(playersPosition.getSouth()!=null) {
						playersPosition=playersPosition.getSouth();
					}
					
				}
				
				if(actions.contains(KeyEvent.VK_A)){
					
					if(playersPosition.getWest()!=null) {
						playersPosition=playersPosition.getWest();
					}
					
				}
				
				if(actions.contains(KeyEvent.VK_D)){
					
					if(playersPosition.getEast()!=null) {
						playersPosition=playersPosition.getEast();
					}
				
				}
			repaint();
			actions.remove(new Integer(event.getKeyCode()));	
		}
	
		
		
	    
		

        public void paint(Graphics g){
        	
        	Graphics2D g2 = (Graphics2D) g;
        	int canvasWidth = this.getWidth();
        	int canvasHeight = this.getHeight();
        	
        	drawRectangle(g2,  canvasWidth,  canvasHeight); 
        	
        	drawPlayer(g2, canvasWidth, canvasHeight);
            drawNorthernRoom(g2, canvasWidth, canvasHeight);
            drawEasternRoom(g2, canvasWidth, canvasHeight);
            drawWesternRoom(g2, canvasWidth, canvasHeight);
            drawSouthRoom(g2, canvasWidth, canvasHeight);
            
        	drawShadowCorners(g2, canvasWidth, canvasHeight);
        
        }
		private void drawRectangle(Graphics2D g2, int canvasWidth, int canvasHeight) {
			 g2.setColor(Color.black);
	         g2.drawRect(0, 0, canvasWidth-1, canvasHeight-1);
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
			
		}  

	    
	    
	    
	    
	    
	    
	     







			private void drawShadowCorners(Graphics2D g2, int canvasWidth, int canvasHeight) {
            	int boxWidth = canvasWidth / 3;
	            int boxHeight = canvasHeight / 3;	
	            
	            int x =	0;
				int y =  0;
				
				if((!(playersPosition.getWest()!=null && playersPosition.getWest().getNorth()!=null))
				 && (!(playersPosition.getNorth()!=null && playersPosition.getNorth().getWest()!=null))) {
					
					drawShadowBox(g2, x, y, boxWidth, boxHeight);
				}
				
				
				x =	0;
 	        	y = boxHeight * 2;
				
				if(!((playersPosition.getWest()!=null && playersPosition.getWest().getSouth()!=null)) 
				&& (! (playersPosition.getSouth()!=null && playersPosition.getSouth().getWest()!=null))){
					
					drawShadowBox(g2, x, y, boxWidth, boxHeight);
				}
            	
 	        	
 	        	x =	boxWidth * 2;
 	        	y = 0;
 	        	
 	        	if(!((playersPosition.getEast()!=null && playersPosition.getEast().getNorth()!=null)) 
 						&& (! (playersPosition.getNorth()!=null && playersPosition.getNorth().getEast()!=null))){
 							
 							drawShadowBox(g2, x, y, boxWidth, boxHeight);
 						}
 	        	
 	        	
 	        	x =	boxWidth * 2;
 	        	y = boxHeight * 2;
 	        	
 	        	if(!((playersPosition.getEast()!=null && playersPosition.getEast().getSouth()!=null)) 
 						&& (! (playersPosition.getSouth()!=null && playersPosition.getSouth().getEast()!=null))){
 							
 							drawShadowBox(g2, x, y, boxWidth, boxHeight);
 						}
 	        	}


			





			private void drawShadowBox(Graphics2D g2, int x, int y, int width, int height) {
				g2.setColor(Color.black);
				g2.fillRect(x, y, width, height);
			}








			private void drawSouthRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
				int width;
				int height;
				int x;
				int y;
				if(playersPosition.getSouth()==null) {
	            	 width = canvasWidth / 3;
		 	        	x =	width;
		 	        	height = canvasHeight / 3;	
		 	        	y = 2 * height;


	            	g2.setColor(Color.black);
	            	
	            	if(playersPosition instanceof LockerRoom) {
	            		LockerRoom lockerRoom = (LockerRoom)playersPosition;
	            		if(lockerRoom.getLockedSouth()!=null) {
	            			
	            		g2.setColor(Color.gray);
	            		}
	            	}
	            	g2.fillRect(x, y, width, height);
	            	
	            	
	            }
			}








			private void drawWesternRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
				int width;
				int height;
				int x;
				int y;
				if(playersPosition.getWest()==null) { 
	            	 width = canvasWidth / 3;
		 	        	x =	0;
		 	        	height = canvasHeight / 3;	
		 	        	y = height;


	               g2.setColor(Color.black);
	               
	               if(playersPosition instanceof LockerRoom) {
	            		LockerRoom lockerRoom = (LockerRoom)playersPosition;
	            		if(lockerRoom.getLockedWest()!=null) {
	            			
	            			g2.setColor(Color.gray);
	            		}
	            	}
	               g2.fillRect(x, y, width, height);
	               
	              
	            }
			}








			private void drawEasternRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
				int width;
				int height;
				int x;
				int y;
				if(playersPosition.getEast()==null) {
	            	 width = canvasWidth / 3;
	 	        	x =	2*width;
	 	        	height = canvasHeight / 3;	
	 	        	y = height;

	            	g2.setColor(Color.black);
	            	
	            	if(playersPosition instanceof LockerRoom) {
	            		LockerRoom lockerRoom = (LockerRoom)playersPosition;
	            		if(lockerRoom.getLockedEast()!=null) {
	            			
	            			g2.setColor(Color.gray);
	            		}
	            	}
	            	g2.fillRect(x, y, width, height);
	            	
	            	
	            }
			}








			private void drawNorthernRoom(Graphics2D g2, int canvasWidth, int canvasHeight) {
				int width;
				int height;
				int x;
				int y;
				if(playersPosition.getNorth()==null) {
	        		 width = canvasWidth / 3;
		 	        	x =	width;
		 	        	height = canvasHeight / 3;	
		 	        	y = 0;

	        	
	        		g2.setColor(Color.black);
	        		
	        		if(playersPosition instanceof LockerRoom) {
	            		LockerRoom lockerRoom = (LockerRoom)playersPosition;
	            		if(lockerRoom.getLockedNorth()!=null) {
	            			
	            			g2.setColor(Color.gray);
	            		}
	            	}
	        		
	        		g2.fillRect(x, y, width, height);
	        		
	        		
	        	}
			}

			

			

	}		