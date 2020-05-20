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
            	drawShadowBox(g2, x, y, boxWidth, boxHeight);

            	x =	0;
 	        	y = boxHeight * 2;
 	        	drawShadowBox(g2, x, y, boxWidth, boxHeight);
            	
            	x =	boxWidth * 2;
 	        	y = boxHeight * 2;
 	        	drawShadowBox(g2, x, y, boxWidth, boxHeight);
            	
            	x =	boxWidth * 2;
 	        	y = 0;
 	        	drawShadowBox(g2, x, y, boxWidth, boxHeight);
			}








			private void drawShadowBox(Graphics2D g2, int x, int y, int width, int height) {
				g2.setColor(Color.gray);
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
	        		g2.fillRect(x, y, width, height);
	        	}
			}

			

	}		

		

