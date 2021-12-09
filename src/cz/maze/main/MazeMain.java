package cz.maze.main;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

public class MazeMain {

	public static void main(String[] args) {
		
        JFrame f = new JFrame();     
        f.setUndecorated(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);//no layout manager
        ClosingWindowListenerAdapter w = new ClosingWindowListenerAdapter();
        f.addWindowListener(w);
        f.setVisible(true);
        BarrierCanvas myCanvas = new BarrierCanvas(f);
        
        
        myCanvas.setLocation(f.getInsets().left,f.getInsets().top);
        myCanvas.setSize(f.getWidth()-f.getInsets().left - f.getInsets().right, f.getHeight()-f.getInsets().top - f.getInsets().bottom);      
        f.add(myCanvas);

        
        run(myCanvas);
			
  
	}
	
	static boolean running = true;
	
	public static void run(BarrierCanvas canvas) {
		long lastFrame = System.currentTimeMillis();
		while(running) {
			if(lastFrame +3 < System.currentTimeMillis()) {
				//System.out.println("here");
				lastFrame = System.currentTimeMillis();	
			
			
				canvas.newFrame();
			}
		
		}	
	}
}

