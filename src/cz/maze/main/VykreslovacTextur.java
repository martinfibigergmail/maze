package cz.maze.main;
import java.awt.Frame;


import javax.imageio.ImageIO;

public class VykreslovacTextur {

	public static void main(String[] args) {
        Frame f = new Frame();     
        f.setSize(498,605);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setLayout(null);//no layout manager


        ClosingWindowListenerAdapter w = new ClosingWindowListenerAdapter();

        // set reaction to closing window
        // add and create class that will handle window close event
        f.addWindowListener(w);

        // Define canvas class with paint method overrided
        // will be called when window is drawn
        BarrierCanvas myCanvas = new BarrierCanvas();
        
        f.setVisible(true);
        
        myCanvas.setLocation(f.getInsets().left,f.getInsets().top);
        myCanvas.setSize(f.getWidth()-f.getInsets().left - f.getInsets().right, f.getHeight()-f.getInsets().top - f.getInsets().bottom);      
        f.add(myCanvas);
       
        

			
  
	}

}
