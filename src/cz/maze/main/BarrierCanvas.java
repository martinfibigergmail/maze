package cz.maze.main;

	import javax.imageio.ImageIO;
	import java.awt.*;
	import java.io.File;
	import java.util.ArrayList;
	import java.util.stream.Collector;
	
	public class BarrierCanvas extends Canvas{ 
	    public ArrayList buttons= new ArrayList();
	    public String display="0";
	    int x;
	    int y;   
	    public BarrierCanvas(){
	    }
	
	    
	    
	    
	    
	    
	    
	    
	        public void paint(Graphics g){
	            Graphics2D g2 = (Graphics2D)g;
	            g2.setColor(Color.BLACK);
	           g2.drawLine(0, 0, 30, 0);
	          	   
	          	   
	        }

	}		

		

