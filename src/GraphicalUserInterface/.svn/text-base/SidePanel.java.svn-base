package GraphicalUserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

import Monopoly.Board;
import Monopoly.Piece;
import Monopoly.Player;
import Monopoly.Property;
/**
 * Class to represent the games Side PAnel
 * @author Michael Treacher
 *
 */
public class SidePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel playersTurn;
	private JLabel selectProperty;
	private JScrollPane propertiesPane;
	private JPanel properties;
	private JPanel buttons;
	private PlayerStatusGrid playerStatus;
	private JLabel playerStats;
	private CashUpdater updater;
	private MonopolyBoardFrame parent;
	
	/**
	 * Constructs the SidePanel for the monopoly board
	 */
	public SidePanel( MonopolyBoardFrame parent){
		this.parent = parent;
		setLayout(null);
		setupPlayerLabel();
		setupSelectPropertyLabel();
		setupPropertyScrollPanel();
		setupButtons();
		setupPlayerStatsLabel();
		setupPlayerStatus();
		setupListeners();
		setSize(208,707);
		updater = new CashUpdater();
		updater.start();
		
	}
	/**
	 * Sets up the buttons for the panel
	 */
	
	private void setupButtons(){
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttons.setBackground(MonopolyBoardFrame.COLOR_THEME);
		JButton viewYourPropertiesButton = new JButton("View Your Properties");
		viewYourPropertiesButton.addActionListener(
				new ActionListener(){
				
					public void actionPerformed(ActionEvent e) {
						displayPlayersPropertys();
						revalidate();
					}
					
				}
		);
		JButton viewOtherPropertiesButton = new JButton("View Other's Properties");
		viewOtherPropertiesButton.addActionListener(
				new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						
						if(Board.players!= null){
							properties.removeAll();
							repaint();
							for(Player player : Board.players){
								if(player.equals(Board.currentPlayer)) continue;
								for(Property property : player.getProperties()){
									final Property prop = property;
									JLabel label = null;
									if(!prop.isMortgaged())
										 label = new JLabel(property.getColor().getImage());
									else
										try {
											label = new JLabel(new ImageIcon(ImageIO.read(new File("src/property_cards/mortgaged.jpg")).getScaledInstance(30, 50, Image.SCALE_FAST)));
										} catch (IOException ex) {ex.printStackTrace();}
									
									label.setToolTipText(prop.toString());
									label.addMouseListener(new MouseAdapter(){

										@Override
										public void mouseClicked(MouseEvent arg0) {
											if(Board.currentPlayer.isInJail()){JOptionPane.showMessageDialog(getRootPane(), "You are in jail you can not do anything with others properties.");}
											PropertyCardFrame frame = new PropertyCardFrame(prop);
											frame.setLocationRelativeTo(getRootPane()); 
											frame.addWindowListener(
													new WindowAdapter(){
														public void windowClosed(WindowEvent we){
															displayPlayersPropertys();
															revalidate();
															parent.getCanvas().repaint();
														}
														
														public void windowClosing(WindowEvent we){
															displayPlayersPropertys();
															revalidate();
															parent.getCanvas().repaint();
														}
													}		
											);
											frame.setVisible(true);
										}											
								});
									properties.add(label);
								}
								revalidate();
								
							}
						}
					}
					
				}
		);
		buttons.add(viewYourPropertiesButton);
		buttons.add(viewOtherPropertiesButton);
	   add(buttons);
	}
	/**
	 * displays the players current properties
	 */
	public void displayPlayersPropertys(){
		if(Board.currentPlayer!= null){
			properties.removeAll();
			repaint();
			for(Property property: Board.currentPlayer.getProperties()){
				final Property prop = property;
				JLabel label = null;
				if(!prop.isMortgaged())
				 label = new JLabel(property.getColor().getImage());
				else
					try {
						label = new JLabel(new ImageIcon(ImageIO.read(new File("src/property_cards/mortgaged.jpg")).getScaledInstance(30, 50, Image.SCALE_FAST)));
					} catch (IOException e) {e.printStackTrace();}
				label.setToolTipText(prop.toString());
				label.addMouseListener(new MouseAdapter(){

					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(Board.currentPlayer.isInJail()){JOptionPane.showMessageDialog(getRootPane(), "You are in jail you can not do anything with your properties.");}
						PropertyCardFrame frame = new PropertyCardFrame(prop);
						frame.setLocationRelativeTo(getRootPane()); 
						frame.addWindowListener(
								new WindowAdapter(){
									public void windowClosed(WindowEvent we){
										displayPlayersPropertys();
										revalidate();	
										parent.getCanvas().repaint();
									}
									public void windowClosing(WindowEvent we){
										displayPlayersPropertys();
										revalidate();
										parent.getCanvas().repaint();
									}
								}			
						);
						frame.setVisible(true);
					}
					

					
				});
				properties.add(label);
			}
		
		}
	}
	/**
	 * Sets up the players into the player panel
	 * @param players The players in the game
	 * @throws Exception 
	 */
	public void setupPlayers(){
		playerStatus.removeAll();
		revalidate();
		for(Player player : Board.players){
			PlayerStatusPanel panel;
			try {
				panel = new PlayerStatusPanel(Piece.getImage(player),player);
				playerStatus.add(panel);
			} catch (Exception e) {System.out.println("Something bad happened at setupPlayers");}
		}
		
	}
	/**
	 * Gets the property grid
	 * @return  PropertyGrid
	 */
	public JPanel getPropertyGrid(){
		return properties;
	}
	/**
	 * Gets the player grid
	 * @return  PlayerGrid
	 */
	public JPanel getPlayerGrid(){
		return playerStatus;
	}
	/**
	 * Refreshes the player panels
	 */
	public void refresh(){
		int heightIncrease = 0 ;
		for(Component comp : playerStatus.getComponents()){
		comp.setBounds(0, heightIncrease, playerStatus.getWidth(), playerStatus.getHeight()/ 7);
		heightIncrease+=playerStatus.getHeight() / 7;
		}
	}
	/**
	 * Sets up the player status panel
	 */
	private void setupPlayerStatus(){
		playerStatus = new PlayerStatusGrid();
		playerStatus.setBackground(MonopolyBoardFrame.LIGHT_THEME);
		playerStatus.addComponentListener( 
				new ComponentListener(){
					/*
					 * (non-Javadoc)
					 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
					 */
					public void componentResized(ComponentEvent e) {
						int heightIncrease = 0 ;
						for(Component comp : playerStatus.getComponents()){
						comp.setBounds(0, heightIncrease, playerStatus.getWidth(), playerStatus.getHeight()/ 7);
						heightIncrease+=playerStatus.getHeight() / 7;
						}
						validate();
					}
					
					public void componentHidden(ComponentEvent e) {/* not implemented*/}
					
					public void componentMoved(ComponentEvent e) {/* not implemented*/}
					
					public void componentShown(ComponentEvent e) {/* not implemented*/}
				} 
			 );
				

		
		playerStatus.setLayout(null);
		playerStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(playerStatus);
	}
	/**
	 * Sets up the property scroll pane
	 */
	private void setupPropertyScrollPanel(){
		properties = new JPanel();
		properties.setBackground(MonopolyBoardFrame.LIGHT_THEME);
		propertiesPane = new JScrollPane(properties);
		properties.setLayout(new GridLayout(0,4,0,0));  
		propertiesPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		propertiesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // for scrolling through properties
		add(propertiesPane);
	}
	/**
	 * Set next players turn
	 */
	public void nextPlayersTurn(){
		playersTurn.setText(Board.currentPlayer.getName()+"'s turn");
		displayPlayersPropertys();
		repaint();
	}
	/**
	 * Sets up the player name label
	 * @param currentPlayer the current player
	 */
	private void setupPlayerLabel(){
		playersTurn = new JLabel("",SwingConstants.CENTER);
		playersTurn.setFont(new Font("sansserif",Font.BOLD,17));
		playersTurn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(playersTurn);
	}
	/**
	 * Sets up the select proprty label
	 */
	private void setupSelectPropertyLabel(){
		selectProperty = new JLabel("Select Properties:");
		selectProperty.setFont(new Font("sansserif",Font.BOLD,17));
		selectProperty.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(selectProperty);
	}
	/**
	 * Sets up the player stats label
	 */
	private void setupPlayerStatsLabel(){
		playerStats = new JLabel("Players:");
		playerStats.setFont(new Font("sansserif",Font.BOLD,17));
		playerStats.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(playerStats);
	}
	/**
	 * Creates the listeners for the Side Panel
	 */
	private void setupListeners(){
			 // add component listener. For resizing
			  addComponentListener(
						new ComponentListener(){
							/*
							 * (non-Javadoc)
							 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
							 */
							public void componentResized(ComponentEvent e) {
								 int panelSize = SidePanel.this.getHeight();
								 final int BIG_PANEL_SIZE = 5;
								 final int PANEL_DIVISOR = 15;
								playersTurn.setBounds(0,0,SidePanel.this.getWidth(),panelSize/PANEL_DIVISOR);
								selectProperty.setBounds(0, playersTurn.getY() + panelSize/PANEL_DIVISOR, SidePanel.this.getWidth(), panelSize/PANEL_DIVISOR);
								propertiesPane.setBounds(0,selectProperty.getY() + panelSize/PANEL_DIVISOR,SidePanel.this.getWidth(),((panelSize/PANEL_DIVISOR)*BIG_PANEL_SIZE));
								buttons.setBounds(0,propertiesPane.getY() + (((panelSize/PANEL_DIVISOR)*BIG_PANEL_SIZE)),SidePanel.this.getWidth(),panelSize/PANEL_DIVISOR+PANEL_DIVISOR + 5);
								playerStats.setBounds(0, buttons.getY() + panelSize/PANEL_DIVISOR+PANEL_DIVISOR + 5,SidePanel.this.getWidth(),panelSize/PANEL_DIVISOR );		
								playerStatus.setBounds(0, playerStats.getY() + panelSize/PANEL_DIVISOR,SidePanel.this.getWidth(),panelSize-(playerStats.getY() + panelSize/PANEL_DIVISOR));
								revalidate();
								
							}
							
							public void componentHidden(ComponentEvent e) {/* not implemented*/}
							
							public void componentMoved(ComponentEvent e) {/* not implemented*/}
							
							public void componentShown(ComponentEvent e) {/* not implemented*/}
						} 
					 );
	}
	/**
	 * Thread to update the cash label
	 * @author Michael Treacher
	 *
	 */
	private class CashUpdater extends Thread{
		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			/*
			 * Constantly updates the reset cash label through out the game
			 */
			while(true){
			for(PlayerStatusPanel panel : SidePanel.this.playerStatus.getPanels()){
				panel.resetCashLabel();
			}
			
			}
		}
	}
	
	

}
