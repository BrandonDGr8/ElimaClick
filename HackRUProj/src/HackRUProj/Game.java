package HackRUProj;

import javax.swing.*;

import HackRUProj.MovingImage;
import HackRUProj.Game;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Game implements MouseListener{

	public static void main(String[] args){
		Game a = new Game();
	}

	private JFrame background;
	private ImagePanel back;
	public static boolean playedOnce;	
	public static int score;
	public static int maxScore;
	public static int[] row;
	public static int[] column;
	
	private MovingImage block;

	public Game(){
//		grid = new int[15][9];
//		for (int row = 0; row < 15; row++){
//			for (int col = 0; col < 9; col++){
//				grid[row][col] = 
//			}
//		}
		row = new int[15];
		for (int i : row)
			row[i] = i;
		column = new int[9];
		for (int i : column)
			column[i] = i;
		
		load(new File("Best.txt"));
		initiate();
	}
	
	public void load(File file)
	{
		try
		{
			Scanner reader = new Scanner(file);
			while(reader.hasNext())
			{
				int value = reader.nextInt();
				if(value > maxScore)
					maxScore = value;
			}
		}
		catch(IOException i )
		{
			System.out.println("Error. "+i);
		}
	}

	public void initiate(){
		if(!playedOnce){
			background = new JFrame("HackRU Game"); 
			background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when the window is closed
			background.setResizable(false); //don't allow the user to resize the window
			background.setSize(new Dimension(800,625));
			background.setVisible(true);
			back = new ImagePanel("bgandgrid.png");
			background.add(back);
			back.addMouseListener(this);
		}
		playedOnce = true;
		block = new MovingImage("RedBlx.png",31,31);
		back.updateImages(block);
	}
	
	
	public void drop(){
		
	}

	public int randomCol(){
		Random ran = new Random(9);
		int a = ran.nextInt(9);
		return a;
	}

	public void mouseExited(MouseEvent e)
	{

	}

	//Called when the mouse enters the game window
	public void mouseEntered(MouseEvent e)
	{

	}

	//Called when the mouse is released
	public void mouseReleased(MouseEvent e)
	{

	}

	//Called when the mouse is pressed
	public void mousePressed(MouseEvent e)
	{

	}

	//Called when the mouse is released
	public void mouseClicked(MouseEvent e)
	{

	}
}



class ImagePanel extends JPanel {

	private Image background;	
	private MovingImage block;//The background image
	//	private ArrayList<MovingImage> top;	//An array list of foreground images
	//	private ArrayList<MovingImage> bottom;
	//	private ArrayList<MovingImage> middle;
	//	private MovingImage plane;
	//	private ArrayList<MovingImage> smoke;
	//	private ArrayList<MovingImage> firstSmoke;
	//	private MovingImage explode;

	//Constructs a new ImagePanel with the background image specified by the file path given
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

		//		top = new ArrayList<MovingImage>();
		//		middle = new ArrayList<MovingImage>();
		//		bottom = new ArrayList<MovingImage>();
		//
		//		smoke = new ArrayList<MovingImage>();
		//		firstSmoke = new ArrayList<MovingImage>();
	}

	//This is called whenever the computer decides to repaint the window
	//It's a method in JPanel that I've overwritten to paint the background and foreground images
	public void paintComponent(Graphics g) 
	{
		//Paint the background with its upper left corner at the upper left corner of the panel
		g.drawImage(background, 0, 0, null); 
		g.drawImage(block.getImage(), (int)(block.getX()), (int)(block.getY()), null);
		//Paint each image in the foreground where it should go
		//		for(MovingImage img : top)
		//			g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
		//		for(MovingImage img : middle)
		//			g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
		//		for(MovingImage img : bottom)
		//			g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
		//		for(MovingImage img : firstSmoke)
		//			g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
		//		for(MovingImage img : smoke)
		//			g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
		//
		//		if(plane != null)
		//			g.drawImage(plane.getImage(), (int)(plane.getX()), (int)(plane.getY()), null);
		drawStrings(g);
	}

	public void drawStrings(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString(    "Score:     " + Game.score,400,50);
		if (Game.score > Game.maxScore)
			g.drawString("Highscore: " + Game.score,400,100);
		else
			g.drawString("Highscore: " + Game.maxScore,400,100);
		//		g.setColor(Color.WHITE);
		//		g.setFont(new Font("Arial",Font.BOLD,20));
		//		g.drawString("Distance: " + PlaneForm.distance,30,500);
		//		g.setFont(new Font("Arial",Font.BOLD,20));
		//		if (PlaneForm.distance > PlaneForm.maxDistance)
		//			g.drawString("Best: " + PlaneForm.distance,650,500);
		//		else
		//			g.drawString("Best: " + PlaneForm.maxDistance,650,500);
		//		if(PlaneForm.paused)
		//		{
		//			g.setColor(Color.WHITE);
		//			g.setFont(new Font("Chiller",Font.BOLD,72));
		//			g.drawString("Paused",275,260);
		//			g.setFont(new Font("Chiller",Font.BOLD,30));
		//			g.drawString("Click to unpause.",280,310);
		//		}
		//		if(PlaneForm.crashed){
		//			g.setColor(Color.BLACK);
		//			g.setFont(new Font("Arial",Font.BOLD,72));
		//			g.drawString("You Died!",275,260);
		//			g.setFont(new Font("Arial",Font.BOLD,30));
		//			g.drawString("Score: " + PlaneForm.distance,280,310);
		//		}
	}

	//Replaces the list of foreground images with the one given, and repaints the panel
	public void updateImages(MovingImage newBlock)
	{
		block = newBlock;
		repaint();	//This repaints stuff... you don't need to know how it works
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
