package HackRUProj;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Game{
	private static JFrame background;
	private static ImagePanel back;
	
	public static void main(String[] args){
		initiate();
	}

	public static void initiate(){
		background = new JFrame("HackRU Game"); 
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when the window is closed
		background.setResizable(false); //don't allow the user to resize the window
		background.setSize(new Dimension(800,600));
		background.setVisible(true);
		back = new ImagePanel("bg.png");
		background.add(back);
	}
	
	
}

class ImagePanel extends JPanel {
	private Image background;	
	
	public ImagePanel(String img) 
	{
		this(new ImageIcon(img).getImage());	
		//The easiest way to make images from file paths in Swing
	}

	//Constructs a new ImagePanel with the background image given
	public ImagePanel(Image img)
	{
		background = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));	
		//Get the size of the image
		//Thoroughly make the size of the panel equal to the size of the image
		//(Various layout managers will try to mess with the size of things to fit everything)
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}
	public void paintComponent(Graphics g) 
	{
		//Paint the background with its upper left corner at the upper left corner of the panel
		g.drawImage(background, 0, 0, null); 
		//Paint each image in the foreground where it should go
	}
}

class MovingImage
{
	private Image image;		//The picture
	private double x;			//X position
	private double y;			//Y position

	//Construct a new Moving Image with image, x position, and y position given
	public MovingImage(Image img, double xPos, double yPos)
	{
		image = img;
		x = xPos;
		y = yPos;
	}

	//Construct a new Moving Image with image (from file path), x position, and y position given
	public MovingImage(String path, double xPos, double yPos)
	{
		this(new ImageIcon(path).getImage(), xPos, yPos);	
		//easiest way to make an image from a file path in Swing
	}

	//They are set methods.  I don't feel like commenting them.
	public void setPosition(double xPos, double yPos)
	{
		x = xPos;
		y = yPos;
	}

	public void setImage(String path)
	{
		image = new ImageIcon(path).getImage();
	}

	public void setY(double newY)
	{
		y = newY;
	}

	public void setX(double newX)
	{
		x = newX;
	}

	//Get methods which I'm also not commenting
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public Image getImage()
	{
		return image;
	}
}
