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

	public static boolean lost;
	

	private JFrame background;
	private ImagePanel back;
	public static boolean playedOnce;	
	public static int score;
	public static int maxScore;
	public static int[] row;
	public static int[] column;
	public static MovingImage[][] grid;

	private MovingImage block;
	private ArrayList<MovingImage> fallingBlocks;
//	private MovingImage fallingBlock;

	public Game(){
		//		grid = new int[15][9];
		//		for (int row = 0; row < 15; row++){
		//			for (int col = 0; col < 9; col++){
		//				grid[row][col] = 
		//			}
		//		}
		//		row = new int[15];
		//		for (int i : row)
		//			row[i] = i;
		//		column = new int[9];
		//		for (int i : column)
		//			column[i] = i;

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

	public void setBlockPosition(double x, double y, MovingImage a) {
		grid[(int)x][(int)y] = a; /*fill the appropriate slot in the grid array with the inputted MovingImage*/
		a.setPosition(y*36 + 31,x*36 + 31); /*35 because every block is 35 wide, 100 as a placeholder for the offset of the grid itself from the edge of the game screen*/
	}



	public void initiate(){
		if(!playedOnce){
			background = new JFrame("HackRU Game"); 
			background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when the window is closed
			background.setResizable(false); //don't allow the user to resize the window
			background.setSize(new Dimension(800,624));
			background.setVisible(true);
			back = new ImagePanel("bgandgrid.png");
			background.add(back);
			back.addMouseListener(this);
		}
		lost = false;
		playedOnce = true;
		grid = new MovingImage[15][9]; /*creating a 15x9 that represents the grid*/
//		block = new MovingImage("RedBlx.png",31,31);
		fallingBlocks = new ArrayList<MovingImage>();
		setInitialBlocks();
		draw();
	}

	public void draw(){
		while(true){
			match();
			back.updateImages(grid);
			if(lost){
				break;
			}
		}		

		
	}


	public void drop(){
		for (int row = 0; row < 14; row++){
			for (int col = 0; col < 9; col++) {
				if (grid[row][col] != null && grid[row+1][col] == null){
					setBlockPosition(row+1,col,grid[row][col]);
					grid[row][col] = null;
				}	
			}
		}
		for (int col = 0; col < 9; col++) {
			if (grid[0][col] != null) {
				lost = true;
				break;
			}
		}

	}

	public int randomCol(){
		Random ran = new Random();
		int a = ran.nextInt(9);
		return a;
	}

	public void newBlock(){
		Random ran = new Random();
		int n = ran.nextInt(5);
		int x = randomCol();
		if (n == 0){
			fallingBlocks.add(new MovingImage(n, "BrownBlx.png", 1, 1));
			setBlockPosition(0, x, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 1){
			fallingBlocks.add(new MovingImage(n, "BlueBlx.png", 1, 1));
			setBlockPosition(0, x, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 2){
			fallingBlocks.add(new MovingImage(n, "GreenBlk.png", 1, 1));
			setBlockPosition(0, x, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 3){
			fallingBlocks.add(new MovingImage(n, "PinkBlx.png", 1, 1));
			setBlockPosition(0, x, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 4){
			fallingBlocks.add(new MovingImage(n, "RedBlx.png", 1, 1));
			setBlockPosition(0, x, fallingBlocks.get(fallingBlocks.size()-1));
		}
	}
	
	public void setInitialBlocks(){
		for (int row = 14; row > 10; row--){
			for (int col = 0; col < 9; col++){
				randomColor(row, col);
			}
		}
	}
	
	public void randomColor(double x, double y){
		Random ran = new Random();
		int n = ran.nextInt(5);
		if (n == 0){
			fallingBlocks.add(new MovingImage(n, "BrownBlx.png", 1, 1));
			setBlockPosition(x, y, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 1){
			fallingBlocks.add(new MovingImage(n, "BlueBlx.png", 1, 1));
			setBlockPosition(x, y, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 2){
			fallingBlocks.add(new MovingImage(n, "GreenBlk.png", 1, 1));
			setBlockPosition(x, y, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 3){
			fallingBlocks.add(new MovingImage(n, "PinkBlx.png", 1, 1));
			setBlockPosition(x, y, fallingBlocks.get(fallingBlocks.size()-1));
		}
		else if (n == 4){
			fallingBlocks.add(new MovingImage(n, "RedBlx.png", 1, 1));
			setBlockPosition(x, y, fallingBlocks.get(fallingBlocks.size()-1));
		}
	}
	
	public void match() {
		for (int row = 14; row >= 0; row--) {
			if (grid[row][0] == null && grid[row][1] == null && grid[row][2] == null && grid[row][3] == null && grid[row][4] == null && grid[row][5] == null && grid[row][6] == null && grid[row][7] == null && grid[row][8] == null) {
				break;
			}
			for (int col = 0; col < 9; col++) {
				if (grid[row][col] == null) {
					continue;
				}
				if (row-1 > 0 && grid[row-1][col] != null && grid[row][col].getImage() == grid[row-1][col].getImage() && row-2 > 0 && grid[row-2][col] != null && grid[row][col].getImage() == grid[row-2][col].getImage()) {
					if (row-3 > 0) {
						for (int rowTest = row-3; grid[rowTest][col] != null && grid[row][col].getImage() == grid[rowTest][col].getImage(); rowTest--) {
							fallingBlocks.set(fallingBlocks.indexOf(grid[rowTest][col]), null);
							grid[rowTest][col] = null;
							drop();
						}
					}
					if (col+1<9 && grid[row][col+1] != null && grid[row][col].getImage() == grid[row][col+1].getImage() && col+2<9 && grid[row][col+2] != null && grid[row][col].getImage() == grid[row][col+2].getImage()) {
						for (int colTest = col+3; grid[row][colTest] != null && grid[row][col].getImage() == grid[row][colTest].getImage(); colTest++) {
							fallingBlocks.set(fallingBlocks.indexOf(grid[row][colTest]), null);
							grid[row][colTest] = null;
							drop();
						}
						fallingBlocks.set(fallingBlocks.indexOf(grid[row][col]), null);
						grid[row][col] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row][col+1]), null);
						grid[row][col+1] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row][col+2]), null);
						grid[row][col+2] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row-1][col]), null);
						grid[row-1][col] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row-2][col]), null);
						grid[row-2][col] = null;
						drop();
					} else {
						fallingBlocks.set(fallingBlocks.indexOf(grid[row][col]), null);
						grid[row][col] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row-1][col]), null);
						grid[row-1][col] = null;
						fallingBlocks.set(fallingBlocks.indexOf(grid[row-2][col]), null);
						grid[row-2][col] = null;
						drop();
					}
				}
				else if (col+1<9 && grid[row][col+1] != null && grid[row][col].getImage() == grid[row][col+1].getImage() && col+2<9 && grid[row][col+2] != null && grid[row][col].getImage() == grid[row][col+2].getImage()) {
					if (col+3<9) {
						for (int colTest = col+3; grid[row][colTest] != null && grid[row][col].getImage() == grid[row][colTest].getImage(); colTest++) {
							grid[row][colTest] = null;
							drop();
						}
					}
					grid[row][col] = null;
					grid[row][col+1] = null;
					grid[row][col+2] = null;
					drop();
				}
			}
		}
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
	//Called when the mouse is released
	public void mouseClicked(MouseEvent e)
	{
          int row = e.getY();
          int col  = e.getX();
          if (row < 1299 && col < 358)
               if ((col - 30) % 36 != 0 && ((row-30) % 36 !=0))
               {
                    row = (row-30) / 36;
                    col = (col-30) / 36;
               }
      
          if (grid[row][col] != null)
        	  grid[row][col].changeColor();
                    
		newBlock();
		drop();
	}

}



class ImagePanel extends JPanel {

	private Image background;
	private MovingImage[][] grid;
//	private MovingImage block;
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

		grid = new MovingImage[15][9];
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
		//Paint each image in the foreground where it should go
		for (int row = 0; row < 15; row++){
			for (int col = 0; col < 9; col++) {
				if (grid[row][col] != null)
					g.drawImage(grid[row][col].getImage(), (int)(grid[row][col].getX()), (int)(grid[row][col].getY()), null);
			}
		}
					
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
		if (Game.lost){
			setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("You lose!",150,300);
		}

		g.drawString(    "Instructions:", 400,150);
		g.drawString(    "Click boxes to change color",400,200);
		g.drawString(    "Match colors together",400,230);
		g.drawString(    "Don't let colors get to the top!",400,260);
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
	public void updateImages(MovingImage[][] newGrid)
	{
		grid = newGrid;
		repaint();	//This repaints stuff... you don't need to know how it works
	}

}


class MovingImage
{
	private int cycle;			//Cycle
	private Image image;		//The picture
	private double x;			//X position
	private double y;			//Y position

	//Construct a new Moving Image with image, x position, and y position given
	public MovingImage(int cyc, Image img, double xPos, double yPos)
	{
		cycle = cyc;
		image = img;
		x = xPos;
		y = yPos;
	}

	//Construct a new Moving Image with image (from file path), x position, and y position given
	public MovingImage(int cyc, String path, double xPos, double yPos)
	{
		this(cyc, new ImageIcon(path).getImage(), xPos, yPos);	
		//easiest way to make an image from a file path in Swing
	}

	//Sets the color of the block to the next color of the cycle
	public void changeColor()
	{
	     cycle = (cycle + 1) % 5;
	     if (cycle == 0)
	          setImage("Brownblx.png");
	     else if (cycle == 1)
	          setImage("Blueblx.png");
	     else if (cycle == 2)
	          setImage("Greenblk.png");
	     else if (cycle == 3)
	          setImage("Pinkblx.png");
	     else if (cycle == 4)
	          setImage("RedBlx.png");
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
