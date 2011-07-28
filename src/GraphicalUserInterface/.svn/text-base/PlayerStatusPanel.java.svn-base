package GraphicalUserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Monopoly.Player;
/**
 * Class that represents a component for the monopoly game
 * @author Michael Treacher
 *
 */
public class PlayerStatusPanel extends JPanel {

	private Image icon;
	private Player player;
	private JLabel name,cash,image;
	private int width = 30;
	private int height = 30;
	/**
	 * Constructs a Player status panel
	 * @param icon Image for the icon
	 * @param player Player in which is being represented
	 */
	public PlayerStatusPanel(Image icon, Player player){
		this.icon = icon;
		this.player = player;
		setLayout(null);
		setupImage();
		setupNameLabel();
		setupCashLabel();
		setupListeners();
		setSize(208,38);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Method to update the cash label
	 */
	public void resetCashLabel(){
		cash.setText("$"+player.getMoneyAmount());
	}
	/**
	 * Sets up the image on the component
	 */
	public void setupImage(){
		image = new JLabel();
		image.setSize(new Dimension(width,height));
		ImageIcon imgIcon = null;
		imgIcon = new ImageIcon(icon.getScaledInstance(image.getWidth(), image.getHeight(),Image.SCALE_AREA_AVERAGING ));
		image.setIcon(imgIcon);
		add(image);
	}
	/**
	 * Sets up the name label on the component
	 */
	public void setupNameLabel(){
		name = new JLabel(player.getName());
		name.setBounds(width + 20,0,PlayerStatusPanel.this.getWidth(),(PlayerStatusPanel.this.getHeight()/2));
		name.setForeground(Color.black);
		name.setFont(new Font("sansserif",Font.BOLD,15));
		add(name);
	}
	/**
	 * Sets up the cash label on the component
	 */
	public void setupCashLabel(){
		cash = new JLabel("",JLabel.CENTER);
		cash.setText("$"+player.getMoneyAmount());
		cash.setFont(new Font("sansserif",Font.BOLD,17));
		cash.setForeground(Color.green);
		cash.setBounds(image.getWidth(),PlayerStatusPanel.this.getHeight()/2,PlayerStatusPanel.this.getWidth(),PlayerStatusPanel.this.getHeight()/2);
		add(cash);
	}
	/**
	 * Sets up the listeners for the component
	 */
	public void setupListeners(){
		 // add component listener. For resizing
		  addComponentListener(
					new ComponentListener(){
						
						public void componentResized(ComponentEvent e) {
							image.setBounds(10,5,PlayerStatusPanel.this.getWidth() / 4,PlayerStatusPanel.this.getHeight() - 10);
							name.setBounds(width + 20,0,PlayerStatusPanel.this.getWidth(),PlayerStatusPanel.this.getHeight()/2);
							cash.setBounds(image.getWidth(),PlayerStatusPanel.this.getHeight()/2,PlayerStatusPanel.this.getWidth(),PlayerStatusPanel.this.getHeight()/2);
							revalidate();
						}
						
						public void componentHidden(ComponentEvent e) {/* not implemented*/}
						
						public void componentMoved(ComponentEvent e) {/* not implemented*/}
						
						public void componentShown(ComponentEvent e) {/* not implemented*/}
					} 
				  );
	
	}
}
