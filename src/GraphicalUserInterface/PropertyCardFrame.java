package GraphicalUserInterface;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Monopoly.Board;
import Monopoly.Color;
import Monopoly.Piece;
import Monopoly.Player;
import Monopoly.Property;

import Monopoly.Utility;

/**
 * Class to represent a property card
 * @author Michael Treacher
 *
 */
public class PropertyCardFrame extends JFrame {
	
	private JLabel label;
	private ButtonPanel panel;
	/**
	 * Constructs a property card frame
	 * @param property
	 */
	public PropertyCardFrame(Property property){
		super(property.toString());
		this.setLayout(null);
		this.panel = new ButtonPanel();
		setSize(523,420);
		this.setPreferredSize(new Dimension(523,420));
		panel.setBounds(0, this.getHeight() - 80, this.getWidth(), 50);
		panel.buildPropertyCardPanel(property,this);
		setResizable(false);
		addImage(property.getPropertyCardImage());
		add(panel);
		this.setVisible(true);
	}
	/**
	 * Adds the image to the Frame
	 */
	public void addImage(Image image){
		label = new JLabel(new ImageIcon(image.getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
		label.setBounds((this.getWidth()/2)- (200/2),30,200,300);
		add(label);
	}
	
	
	

	

	

}
