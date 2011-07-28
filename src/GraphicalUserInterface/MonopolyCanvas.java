package GraphicalUserInterface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Monopoly.Board;
import Monopoly.Cell;
import Monopoly.Piece;
import Monopoly.Player;
import Monopoly.Property;
import Monopoly.Street;

/**
 * 
 * 
 * @author Sean Anderson
 *
 */


public class MonopolyCanvas extends Canvas{

	private int hint = Image.SCALE_SMOOTH;

	private BufferedImage board;
	private BufferedImage house;
	private BufferedImage hotel;

	private final String BOARDFILE = "src/images/monopoly_board2.jpg";
	private final String HOUSEFILE = "src/tokens/monopoly_house.png";
	private final String HOTELFILE = "src/tokens/monopoly_hotel.png";

	private double scale;

	private final int BOARDSIZE = 3000;
	//corner cells are square
	private final int CELLHEIGHT = 407;
	private final int CELLWIDTH = 244;

	private int numInnerCells = 9;

	private Graphics bufferGraphics;

	private int currentBoardSize;
	
	private MonopolyBoardFrame parent;

	private Image offScreen;

	//fields required for the toolTip methods to work
	private boolean toolTip = false;
	private Cell toolTipProperty;
	private int toolTipX;
	private int toolTipY;

	private ArrayList<Street> streets = new ArrayList<Street>();
	//private ArrayList<Player> players// = new ArrayList<Player>();
	//private ArrayList<Cell> cells = new ArrayList<Cell>();


	/**
	 * Constructs a monopoly board canvas
	 * 
	 * @param ArrayList of cells
	 * @param ArrayList of current players
	 */
	public MonopolyCanvas(MonopolyBoardFrame parent){
		this.parent = parent;

		for(Cell c : Board.cells){
			if (c instanceof Street){
				Street s = (Street) c;
				streets.add(s);
			}		
		}
		try {
			board = ImageIO.read(new File(BOARDFILE));
			house = ImageIO.read(new File(HOUSEFILE));
			hotel = ImageIO.read(new File(HOTELFILE));

			this.setSize(100,100);
			currentBoardSize = Math.min(this.getHeight(), this.getWidth());

		} catch (IOException e) {System.out.println("Problem reading cell images");}
		setupMouseListener();
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	public void update(Graphics g){
		paint(g);
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){

		// Double buffering up and running ;)

		currentBoardSize = Math.min(this.getHeight(), this.getWidth());
		offScreen = createImage(currentBoardSize,currentBoardSize);
		bufferGraphics = offScreen.getGraphics();

		scale =  (double)BOARDSIZE / currentBoardSize;

		bufferGraphics.drawImage(board.getScaledInstance(currentBoardSize, currentBoardSize, hint),0,0,null);

		drawHouses(bufferGraphics);




		for (Player p : Board.players){
			drawPlayers(bufferGraphics, p);
		}

		if(toolTip)

			drawToolTip(bufferGraphics);
		
		g.drawImage(offScreen, 0, 0, null);



		//System.out.println(currentBoardSize);
		//System.out.println(BOARDSIZE);

		//testFindCell(g);

	}

	/**
	 * Sets up the mouse listener on the board to listen for click events.
	 * If you click on a property it will show a property card.
	 */
	public void setupMouseListener(){
		addMouseListener(
				new MouseAdapter(){
					
					
					//create a property card from a user click
					public void mouseClicked(MouseEvent me){
						Cell cellToFind = findProperty(me.getX(),me.getY());


						if(cellToFind!=null){
							Property prop = (Property)cellToFind;
							PropertyCardFrame card = new PropertyCardFrame(prop);
							card.addWindowListener(
										new WindowAdapter(){
											public void windowClosed(WindowEvent we){
												parent.getSidePanel().displayPlayersPropertys();
												parent.getSidePanel().revalidate();
												repaint();
											}
											public void windowClosing(WindowEvent we){
												parent.getSidePanel().displayPlayersPropertys();
												parent.getSidePanel().revalidate();
												repaint();
											}
										});
							}
							}
					//undraw the toolTip if we lose focus
					public void mouseExited(MouseEvent me){
						toolTip = false;	
						repaint();
					}
					
								
						});
					
				
		//for displaying toolTip on mouse hover
		addMouseMotionListener(
				new MouseMotionAdapter(){

					public void mouseMoved(MouseEvent me){
						Cell cellToFind = findProperty(me.getX(),me.getY());
						
						if (cellToFind == null || !cellToFind.equals(toolTipProperty))
							toolTip = false;
						
						if(!toolTip){

						if(cellToFind!=null){
							Property prop = (Property)cellToFind;

							toolTip = true;
							toolTipProperty =  prop;
							toolTipX = me.getX();
							toolTipY = me.getY();

						}
						//turn off tool tip
						else
							toolTip = false;

						repaint();
						}
					}

					
				}


		);
	}
	/**
	 * returns the cell at an x,y location
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return
	 */
	public Cell findProperty(int x, int y){

		int cellHeight = (int) (CELLHEIGHT / scale);
		int cellWidth = (int)(CELLWIDTH / scale);

		Rectangle innerBoard = new Rectangle(cellHeight, cellHeight, cellWidth * numInnerCells, cellWidth * numInnerCells);

		if (innerBoard.contains(x,y)){
			return null;
		}

		//each corner, as they are different sizes from inner cells
		Rectangle topLeft = new Rectangle(0,0, cellHeight, cellHeight);
		Rectangle topRight = new Rectangle(currentBoardSize - cellHeight, 0, cellHeight, cellHeight);
		Rectangle bottomLeft = new Rectangle(0, currentBoardSize - cellHeight, cellHeight , cellHeight);
		Rectangle bottomRight = new Rectangle(currentBoardSize - cellHeight, currentBoardSize - cellHeight, cellHeight, cellHeight);

		//search corner cells
		if (topLeft.contains(x,y) || topRight.contains(x,y) || bottomLeft.contains(x,y) || bottomRight.contains(x,y) ){
			//do nothing
			return null;
		}
		//check north & south cells
		else if (y >= currentBoardSize - cellHeight || y <= cellHeight){		

			for (int i = 1; i < 30; i++){
				//skip to north side
				if(i == 10)
					i = 21;


				Point p = findCell(i);

				Rectangle r = new Rectangle(p.x, p.y, cellWidth, cellHeight);

				if (r.contains(x,y) && Board.cells.get(i) instanceof Property)
					return Board.cells.get(i);			
			}
		}

		//check east and west cells
		else if (x >= currentBoardSize - cellHeight || x <= cellHeight){		

			for (int i = 11; i < 40; i++){
				//skip to west side
				if(i == 20)
					i = 31;


				Point p = findCell(i);

				Rectangle r = new Rectangle(p.x, p.y, cellHeight, cellWidth);

				if (r.contains(x,y) && Board.cells.get(i) instanceof Property)
					return Board.cells.get(i);			
			}
		}
		return null;
	}


	/**
	 * takes a cell number (0 - 40) and finds its
	 * top-left corner, overly complex as corner squares are different sizes
	 * 
	 * @param cellNumber
	 */
	private Point findCell(int cellNumber){

		Point output = null;
		int cellHeight = (int) (CELLHEIGHT / scale);
		int cellWidth = (int)(CELLWIDTH / scale);


		//figure out 4 inner corners, as they have different widths
		Point topLeft = new Point(0, 0);
		Point topRight = new Point(currentBoardSize - cellHeight, 0);
		Point bottomLeft = new Point(0, currentBoardSize - cellHeight);
		Point bottomRight = new Point(currentBoardSize - cellHeight, currentBoardSize - cellHeight);

		//search corner cells, then inner cells
		if (cellNumber == 0)
			output = bottomRight;

		else if (cellNumber == 10)
			output = bottomLeft;

		else if (cellNumber == 20)
			output = topLeft;

		else if (cellNumber == 30)
			output = topRight;
		//south side inner cells
		else if(cellNumber < 10){
			bottomRight.translate((-cellWidth * cellNumber),0);
			output = bottomRight;
		}
		//west side inner cells
		else if(cellNumber < 20){
			int increment = cellNumber % 10;
			bottomLeft.translate(0, (-cellWidth * increment));
			output = bottomLeft;
		}
		//north side inner cells
		else if(cellNumber < 30){
			int increment = cellNumber % 10;
			increment--;
			topLeft.translate(cellHeight + (cellWidth * increment), 0);
			output = topLeft;
		}
		//east side inner cells
		else if(cellNumber < 40){
			int increment = cellNumber % 10;
			increment--;
			topRight.translate(0, cellHeight + (cellWidth * increment));
			output = topRight;
		}

		return output;

	}

	/*
	private void testFindCell(Graphics g){
		for (int i = 0 ; i < 40 ; i++){
			Point p = findCell(i);
			g.setColor(Color.BLACK);
			g.fillOval(p.x, p.y, 20, 20);

			String cell = new Integer(i).toString();
			g.setColor(Color.WHITE);
			g.drawString(cell, p.x, p.y);
		}
	}
**/


	
	/**
	 * Draws each streets houses or hotels
	 * takes into account North/South/East/West walls and rotations

	 * @param Graphics to be drawn onto
	 */
	private void drawHouses(Graphics g){

		int cellWidth = (int)(CELLWIDTH / scale);
		int cellHeight = (int)(CELLHEIGHT / scale);

		int houseSize = cellHeight/8;
		int houseY = cellWidth / 5;

		for (Street s : streets){

			Point loc = findCell(s.getID());

			int corner = 0;

			//check which side of the board the cell is on
			//South side
			if (s.getID() <= 10){
				loc.translate(0, 0);
				corner = loc.x;
			}
			//West Side
			else if (s.getID() <= 20){
				loc.translate(cellHeight - houseSize, cellWidth - houseSize);
				corner = loc.y;
			}
			//North Side
			else if (s.getID() <= 30){
				loc.translate(0, cellHeight - houseY);
				corner = loc.y;
			}
			//East Side
			else
				loc.translate(0, cellWidth - houseSize);

			//6 houses represent a hotel
			if (s.getHouses() >= 6){

				if (s.getID() <= 10 || s.getID() <= 30 && s.getID() >= 20)
					loc.translate(houseSize*2, 0);
				else
					loc.translate(0, -houseSize*2);

				g.drawImage(hotel.getScaledInstance(houseSize, houseSize, hint),loc.x,loc.y,null);				
			}

			else {

				for(int i = 0; i < s.getHouses(); i++){

					g.drawImage(house.getScaledInstance(houseSize, houseSize, hint),loc.x,loc.y,null);

					//if north or south side
					if (s.getID() <= 10 || s.getID() <= 30 && s.getID() >= 20)
						loc.translate(houseSize, 0);
					else
						loc.translate(0, -houseSize);
				}
			}
		}

	}
	/**
	 * Starts to animate the players movement
	 */
	public void animatePlayer(){
		new AnimatorThread().start();
	}

	/**
	 * draws a specified Player on the board
	 * 
	 * @param Graphics to be drawn too
	 * @param player to be drawn
	 */
	public void drawPlayers(Graphics g, Player p){

		//find a way to get player images
		int cellSize = (int)(CELLWIDTH / scale);


		int playerSize =  cellSize;


		int playerX = 0;
		int playerY = cellSize / 2;



		Point loc = findCell(p.getPosition());


		//move to center of the cell
		//check if cell is vertical or horizontal
		if (p.getPosition() <= 10 || (p.getPosition() < 30 && p.getPosition() >= 20))
			loc.translate(playerX, playerY);
		else
			loc.translate(playerY, playerX);

		//draw in proportion to the cell
		//need to get player image
		Piece.getImage(p);

		g.drawImage(Piece.getImage(p).getScaledInstance(playerSize, playerSize, hint),loc.x,loc.y,null);

	}
	
	
	/**
	 * Draws a tool tip to hovered location by user
	 * 
	 * 
	 * @param g
	 */
	public void drawToolTip(Graphics g){

		Player player = null;

		for (Player p : Board.players){
			if (p.getPosition() == toolTipProperty.getID())
				player = p;
		}

		int toolTipHeight = 10;	
		int toolTipWidth = toolTipProperty.toString().length() * 7;
		
		//make sure the box wont go off the side of the canvas
		if (toolTipX + toolTipWidth >= currentBoardSize)
			toolTipX -= (toolTipX + toolTipWidth) - currentBoardSize;
		
		if (toolTipY + toolTipHeight >= currentBoardSize)
			toolTipY -= (toolTipY + toolTipHeight) - currentBoardSize;


		g.setColor(Color.cyan);
		g.fillRect(toolTipX, toolTipY, toolTipWidth, toolTipHeight);

		g.setColor(Color.black);
		g.drawString(toolTipProperty.toString(), toolTipX, toolTipY + toolTipHeight);

		//draw players name
		if (player != null){
			g.setColor(Color.cyan);
			g.fillRect(toolTipX, toolTipY+ toolTipHeight, player.toString().length() * 7, toolTipHeight*2);
			
			g.setColor(Color.black);
			g.drawString(player.toString(), toolTipX, toolTipY + toolTipHeight * 2);
		}
	}


	/**
	 * 
	 * New thread for creating an animation for player moving around the board
	 * 
	 */
	class AnimatorThread extends Thread{
		public void run(){
			int origin = Board.currentPlayer.getOldPosition();
			int destination = Board.currentPlayer.getPosition();
			while(origin != destination){
				try{Thread.sleep(240);}catch(Exception ex){}
				origin++;
				if(origin == 40) origin = 0;
				Board.currentPlayer.setPosition(origin);
				repaint();



			}
		}
	}


}
