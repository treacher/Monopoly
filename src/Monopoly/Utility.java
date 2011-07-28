package Monopoly;

import java.awt.Image;

/**
 * A class to represent Utility cells on the board game monopoly.
 * @author Michael Treacher
 *
 */
public class Utility extends Property {
	/**
	 * Constructs a Utility cell
	 * @param ID
	 * @param name
	 * @param propertyValue
	 */
	public Utility(int ID, String name, int propertyValue,Color color, Image img) {
		super(ID, name, propertyValue,color,img);
	}

	/*
	 * (non-Javadoc)
	 * @see Property#rent()
	 */
	public int rent(Player p) {
		int owned = 1; // start at 1 as including this property
		for(Property prop : this.getColor().getSet()){
			if(prop == this || prop == null){continue;} // if the property is this one then skip it as we already know its owned by this person
			if(this.getOwner().equals(prop.getOwner())){owned++;} // checks if the owner of this owns other utilitys.
		}
		//Utility rent = value of last dice roll * 4 * number of utilities owned
		return (p.getRoll1() + p.getRoll2()) * 4 * owned;
		
	}

	

}
