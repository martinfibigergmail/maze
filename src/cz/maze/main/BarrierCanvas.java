package cz.maze.main;

	import javax.imageio.ImageIO;

import cz.pf.Main;
import cz.pf.model.Item;
import cz.pf.model.Room;

import java.awt.*;
	import java.io.File;
	import java.util.ArrayList;
	import java.util.stream.Collector;
	
	public class BarrierCanvas extends Canvas{ 
	    public ArrayList buttons= new ArrayList();
	    public String display="0";
	    int x;
	    int y;
		public Room playersPosition; 
	    Room map;
	    public BarrierCanvas(){
	    	Item key = new Item("žlutej klíč");
	    	Main support= new Main();
	        map=support.createMap(key);
	        
	       playersPosition = map;

	    }

	    
	    
	    
	    
	    
	    
	    
	        public void paint(Graphics g){
	        	int screenWidth;
	        	int width;
	            int height;
	            int screenHeight;
	            int x;
	            int y;
	            
	        	screenWidth= this.getWidth();
	        	System.out.println(screenWidth);
	        	
	        	screenHeight= this.getHeight();
	        	System.out.println(screenHeight);
	        	width = screenWidth / 2;
	        	x =	width - 50;
	        	height = screenHeight / 2;	
	        	y = height - 50;
	            Graphics2D g2 = (Graphics2D)g;
	            g2.setColor(Color.black);
	           g2.drawRect(0, 0, screenWidth-1, screenHeight-1);
	           
	           g2.drawOval(x, y, 100, 100);
	           g2.setColor(Color.red);
               g2.fillOval(x, y, 100, 100);
               
	        	
	        	if(playersPosition.getNorth()==null) {
	        		 width = screenWidth / 3;
		 	        	x =	width;
		 	        	height = screenHeight / 3;	
		 	        	y = 0;

	        	
	        		g2.setColor(Color.black);
	        		g2.fillRect(x, y, width, height);
	        	}
	            if(playersPosition.getEast()==null) {
	            	 width = screenWidth / 3;
	 	        	x =	2*width;
	 	        	height = screenHeight / 3;	
	 	        	y = height;

	            	g2.setColor(Color.black);
	            	g2.fillRect(x, y, width, height);
	            }
	            if(playersPosition.getWest()==null) { 
	            	 width = screenWidth / 3;
		 	        	x =	0;
		 	        	height = screenHeight / 3;	
		 	        	y = height;


	               g2.setColor(Color.black);
	               g2.fillRect(x, y, width, height);
	            }
	            if(playersPosition.getSouth()==null) {
	            	 width = screenWidth / 3;
		 	        	x =	width;
		 	        	height = screenHeight / 3;	
		 	        	y = 2 * height;


	            	g2.setColor(Color.black);
	            	g2.fillRect(x, y, width, height);
	            }
	         
               
	        }

	}		

		

