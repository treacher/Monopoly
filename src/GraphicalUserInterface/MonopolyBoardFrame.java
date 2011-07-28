package GraphicalUserInterface;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;

import Monopoly.*;


/**
 * MonopolyBoardFrame which models the frame in which the game is inside.
 * @author Michael Treacher
 *
 */
public class MonopolyBoardFrame extends JFrame {

	public static final Color COLOR_THEME = new Color(185,211,238);
	public static final Color LIGHT_THEME = new Color(198,226,255);
	static List<Player> players;
	
	final int CANVAS_WIDTH = 224; // starting canvas width
	
	final int CANVAS_HEIGHT = 168; // starting canvas height
	
	final int BORDER_HEIGHT_ERROR = 61; // have to take this away from the bottom component so it fits properly on the frame
	
	final int BORDER_WIDTH_ERROR = 16;
	
	final int BUTTON_PANEL_HEIGHT = 35;
	
	private MonopolyCanvas monopolyCanvas;
	private static JTextArea statusTextArea;
	private JScrollPane scrollPane;
	private SidePanel sidePanel;
	private ButtonPanel buttonPanel;
	
	/**
	 * Constructs the MonopolyBoardFrame
	 */
	public MonopolyBoardFrame(){
		super("Monopoly Game");
		 
		  this.setMinimumSize(new Dimension(824,768));
		  setPreferredSize(new Dimension(824,768));
		  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		  getContentPane().setLayout(null);
		  setupBoard();
		  setupMonopolyCanvas();
		  setupSidePanel();
		  setupTextArea();
		  setupButtonPanel();
		  setupMenu();
		  setupListeners();
		  setSize(new Dimension(824,768));
		  setVisible(true);
		  pack();
	}
	/**
	 * Sets up the button panel
	 */
	public void setupButtonPanel(){
		buttonPanel = new ButtonPanel();
		buttonPanel.setBackground(COLOR_THEME);
		add(buttonPanel);
	}
	/**
	 * Get the button panel
	 * @return the button panel
	 */
	public ButtonPanel getButtonPanel(){
		return this.buttonPanel;
	}
	/**
	 * Get the side panel
	 * @return the side panel
	 */
	public SidePanel getSidePanel(){
		return sidePanel;
	}
	/**
	 * Setup the board
	 */
	public void setupBoard(){
		new Board();
	}
	/**
	 * Sets up the text area for the monopoly game
	 */
	public void setupTextArea(){
		statusTextArea = new JTextArea();
		scrollPane = new JScrollPane(statusTextArea);
		statusTextArea.setEditable(false);
		// Makes a black border
		statusTextArea.setBackground(LIGHT_THEME);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(scrollPane);

	}
	/**
	 * Sets up the side panel
	 */
	public void setupSidePanel(){
		sidePanel = new SidePanel(this);
		sidePanel.setBackground(COLOR_THEME);;
		getContentPane().add(sidePanel);
	}
	/**
	 * Method to control the text input to the text area
	 * @param text - Text input
	 */
	public static void appendText(String text){
		statusTextArea.append(text + "\n");
		// makes sure its always at the end of the text so you see the latest updates of the game
		statusTextArea.setCaretPosition(statusTextArea.getDocument().getLength());
	}
	/**
	 * Sets up all the listener objects for the Monopoly frame
	 */
	public void setupListeners(){
		 // add component listener. For resizing
		  addComponentListener(
					new ComponentListener(){
						
						public void componentResized(ComponentEvent e) {

							int newCanvasWidth = MonopolyBoardFrame.this.getWidth() - CANVAS_WIDTH;
							int newCanvasHeight = MonopolyBoardFrame.this.getHeight() - CANVAS_HEIGHT;
							// change size of canvas when resized
							monopolyCanvas.setSize(Math.min(newCanvasWidth,newCanvasHeight),Math.min(newCanvasWidth,newCanvasHeight)); 
							// change the size of the scroll pane when resized
							scrollPane.setBounds(0,newCanvasHeight,newCanvasWidth,((MonopolyBoardFrame.this.getHeight() - (newCanvasHeight) - BORDER_HEIGHT_ERROR))-BUTTON_PANEL_HEIGHT);
							// change the size of the sidePanel when resized
							sidePanel.setBounds(newCanvasWidth, 0, (MonopolyBoardFrame.this.getWidth() - newCanvasWidth)-BORDER_WIDTH_ERROR, (MonopolyBoardFrame.this.getHeight() - BORDER_HEIGHT_ERROR));
							// change the size of the buttonPanel when resized
							buttonPanel.setBounds(0,(MonopolyBoardFrame.this.getHeight()- BORDER_HEIGHT_ERROR)-BUTTON_PANEL_HEIGHT,newCanvasWidth,BUTTON_PANEL_HEIGHT);
							setSize(Math.min(newCanvasWidth,newCanvasHeight) + CANVAS_WIDTH,Math.min(newCanvasWidth,newCanvasHeight) + CANVAS_HEIGHT);
						
						}
						
						public void componentHidden(ComponentEvent e) {/* not implemented*/}
						
						public void componentMoved(ComponentEvent e) {/* not implemented*/}
						
						public void componentShown(ComponentEvent e) {/* not implemented*/}
					} 
				  );
				  // Set window closing event
				  addWindowListener(new WindowAdapter(){
				      public void windowClosing(WindowEvent we){
				        exit();
				      }
				    });
	}
	
	/**
	 * Sets up the monopoly canvas
	 */
	public void setupMonopolyCanvas(){
		monopolyCanvas = new MonopolyCanvas(this);
	    getContentPane().add(monopolyCanvas);
	}
	/**
	 * Exit method for exiting the frame
	 */
	public void exit(){
		int option = JOptionPane.showConfirmDialog(MonopolyBoardFrame.this,"Are you sure you want to exit Monopoly?","Exit Game?",JOptionPane.YES_NO_OPTION); 
		if(option == JOptionPane.YES_OPTION) System.exit(0);
	}

	/**
	 * Sets up the menu bar for the frame
	 */
	public void setupMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.getPopupMenu().setLightWeightPopupEnabled(false);
		// set the short cut key to f
		file.setMnemonic('F');
		//New Game option
		JMenuItem newGame = new JMenuItem("New Game");
		// attach action listener to the JMenuItem
		newGame.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Board.players = new ArrayList<Player>();
						Board.currentPlayer = null;
						int noOfPlayers = getNumberOfPlayers();
						if(noOfPlayers == -1) return;
						final NewPlayerFrame players = new NewPlayerFrame(noOfPlayers);
						players.addWindowListener(
							new WindowAdapter(){
								
								public void windowDeactivated(WindowEvent arg0) {
									if (players.listFull()){
									Board.currentPlayer = Board.players.get(0);
									getSidePanel().setupPlayers();
									getSidePanel().nextPlayersTurn();
									getSidePanel().refresh();
									buttonPanel.buildPanel(MonopolyBoardFrame.this);
									monopolyCanvas.repaint();
									}
								}
							});
						players.setVisible(true);
						
						}
						
						
					}
					
				
		);
		// set the short cut key to n
		newGame.setMnemonic('N');
		// add the menu bar to the file menu
		file.add(newGame);
		//Exit Game option
		JMenuItem exitGame = new JMenuItem("Exit Game");
		exitGame.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						exit();
					}
				}
		);
		// set the short cut key to x
		exitGame.setMnemonic('x');
		file.add(exitGame);
		// add the menu bar to the file menu
		menubar.add(file);
		setJMenuBar(menubar);
		
		
	}
	/**
	 * Creats a Option pane for getting the amount of players
	 * @return amount of players
	 */
	public int getNumberOfPlayers(){
		String option = JOptionPane.showInputDialog("Enter how many players you want:");
		if(option == null){ return -1;} // do nothing but return 
		int noOfPlayers = 0;
		try{
		noOfPlayers = Integer.parseInt(option);
		}catch(Exception e){} // will trigger next conditional
		// makes sure you put a number between 2 - 7
		if(noOfPlayers < 2 || noOfPlayers > 7){
			JOptionPane.showMessageDialog(this, "Please chose a number between 2 and 7");
			return getNumberOfPlayers();
		}
		return noOfPlayers;
	}
	/**
	 * Gets the canvas
	 * @return
	 */
	public MonopolyCanvas getCanvas(){
		return monopolyCanvas;
	}
}
		
		
	

