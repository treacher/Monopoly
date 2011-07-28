package GraphicalUserInterface;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import Monopoly.Board;
import Monopoly.Cell;
import Monopoly.Player;
import Monopoly.Street;

/**
 * 
 * helper class for testing MonopolyCanvas
 * @author Sean
 *
 */
public class CanvasTester {
	
	public static void main(String[] args){
		
		
	
	addHouses();

	JFrame frame = new JFrame();
	frame.setSize(800,800);
	frame.setPreferredSize(new Dimension(800,800));
	
	
	//MonopolyCanvas m = new MonopolyCanvas();
	//m.setBounds(0, 0, 800, 800);
	
	///frame.add(m);
	frame.setVisible(true);

	
	
	}
	public static void addHouses(){
		
		
		ArrayList<Street> streets = new ArrayList<Street>();
		//take out just the properties
		for(Cell c : Board.cells){
			if (c instanceof Street){
				Street s = (Street) c;
				streets.add(s);
			}		
		}
		
		for (Street s : streets){
			s.addHouse(5);
		}
	}
}
