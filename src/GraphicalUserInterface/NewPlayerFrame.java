package GraphicalUserInterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Monopoly.Board;
import Monopoly.Piece;
import Monopoly.Player;
/**
 * A class to represent a New Player frame
 * @author Michael Treacher
 *
 */
public class NewPlayerFrame extends JFrame {
	
	private int noOfPlayers;
	private JTextField textField;
	private JPanel jRadioButtons;
	private final JLabel tokenTitle = new JLabel("Token:");
	private ButtonGroup group;
	private Piece selectedPiece;
	private JButton okButton = new JButton("Ok");
	private int count = 0;
	private JLabel nameTitle = new JLabel("Player "+(count+ 1)+ "'s Name:");
	private List<Piece> pieces = Arrays.asList(Piece.values());
	private JRadioButton invisButton = new JRadioButton();
	private JRadioButton buttonToRemove;
	/**
	 * Constructs a new player frame 
	 * @param noOfPlayers Number of players
	 */
	public NewPlayerFrame(final int noOfPlayers){
		this.setLayout(null);
		this.setSize(210,300);
		this.setPreferredSize(getSize());
		this.setResizable(false);
		this.okButton.setBounds((getWidth()/2)-60, getHeight()-65, 70, 20);
		this.nameTitle.setBounds(20, 10, 150, 20);
		this.tokenTitle.setBounds(20, 60, 150, 20);
		this.noOfPlayers = noOfPlayers;
		jRadioButtons = new JPanel();
		setupRadioButtons();
		setupButton();
		textField = new JTextField();
		textField.setBounds(28, 30, 150, 20);
		getContentPane().add(nameTitle);
		getContentPane().add(textField);
		getContentPane().add(tokenTitle);
		
	}
	
	public void setupButton(){
		okButton.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						if(invisButton.isSelected()){JOptionPane.showMessageDialog(NewPlayerFrame.this, "Please select a token.");}
						else if(textField.getText().length() <= 0 || textField.getText().length() >= 13){JOptionPane.showMessageDialog(NewPlayerFrame.this, "Please choose a name with less then 13 characters ");}
						else{
						Board.players.add(new Player(selectedPiece,textField.getText(),1000));
						textField.setText("");
						buttonToRemove.setEnabled(false);
						invisButton.setSelected(true);
						count++;
						nameTitle.setText("Player "+(count + 1)+ "'s Name:");
						}
						if(count == noOfPlayers){dispose();}
					}
					
				}
				);
		getContentPane().add(okButton);
	}
	/**
	 * Checks if the list of players is full
	 * @return
	 */
	public boolean listFull(){
		return count == noOfPlayers;
	}
	
	
	public void setupRadioButtons(){
		//jRadioButtons.removeAll();
		validate();
		jRadioButtons.setLayout(new GridLayout(0,1));
		jRadioButtons.setBounds(50, 85, 150, 150);
		group = new ButtonGroup();

		for(Piece piece : pieces){
			final Piece p = piece;
			final JRadioButton option = new JRadioButton(piece.toString(),false);
			option.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					selectedPiece = p;
					buttonToRemove = option;
				}
			}
			);
			group.add(option);
			jRadioButtons.add(option);
		}
		
		invisButton.setVisible(false);
		group.add(invisButton);
		jRadioButtons.add(invisButton);
		getContentPane().add(jRadioButtons);
	}
	


}
