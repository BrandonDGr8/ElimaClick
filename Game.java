import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class Game{
	private static JFrame background;
	
	public static void main(String[] args){
		initiate();
	}

	public static void initiate(){
		background = new JFrame("HackRU Game"); 
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when the window is closed
		background.setResizable(false); //don't allow the user to resize the window
		background.setSize(new Dimension(800,600));
		background.setVisible(true);
	}
}
