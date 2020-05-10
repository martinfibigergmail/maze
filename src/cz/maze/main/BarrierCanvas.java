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
	    Room map;
	    public BarrierCanvas(){
	    	Item key = new Item("žlutej klíč");
	    	Main support= new Main();
	        map=support.createMap(key);
	    }
	
	    
	    
	    
	    
	    
	    
	    
	        public void paint(Graphics g){
	        	int width;
	        	width= this.getWidth();
	        	System.out.println(width);
	        	int height;
	        	height= this.getHeight();
	        	System.out.println(height);
	           
	            Graphics2D g2 = (Graphics2D)g;
	            g2.setColor(Color.BLACK);
	           g2.drawRect(0, 0, width-1, height-1);
	        

	        }

	}		

		

