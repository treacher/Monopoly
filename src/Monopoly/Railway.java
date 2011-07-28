package Monopoly;

import java.awt.Image;

/**
 *  A class to represent Railway cells on the board game monopoly.
 *  
 * @author Michael Treacher
 *
 */
public class Railway extends Property {

	public Railway(int ID, String name, int propertyValue,Color color,Image img) {
		super(ID, name, propertyValue,color,img);
	}

	/*
	 * (non-Javadoc)
	 * @see Property#rent()
	 */
	public int rent(Player p) {
		int owned = 1; // start at 1 as including this property
		for(Property prop : this.getColor().getSet()){
			if(prop == null){continue;}
			if(prop == this || prop.getOwner() == null){continue;} // if the property is this one then skip it as we already know its owned by this person
			if(this.getOwner().equals(prop.getOwner())){owned++;} // checks if the owner of this owns other stations.
		}
		//Station rent = base rate * number of stations owned
		return 25 * owned;
	}

}
