package Monopoly;

import java.awt.Image;

import javax.imageio.ImageIO;

/**
 * Property class which is a subclass of the abstract class Cell,
 *
 * @author Michael Treacher
 *
 */
public abstract class Property extends Cell {


	private int propertyValue;
	private boolean mortgaged;
	private Color color;
	private Image propertyCard;
	private Player owner;
	/**
	 * Creates a property object
	 * @param ID
	 * @param name
	 * @param propertyValue
	 * @param cost
	 */
	public Property(int ID, String name,int propertyValue,Color color,Image propertyCard ) {
		super(ID, name);
		this.propertyValue= propertyValue;
		this.color = color;
		this.propertyCard = propertyCard;
	}
	/**
	 * Method that will calculate the rent for the property objects
	 * @return
	 */
	public abstract int rent(Player p);
	/*
	 * (non-Javadoc)
	 * @see Cell#activate()
	 */
	public Option trigger(Player player){
		return null;
		 
	}
	
	/**
	 * Gets the owner of the property
	 * @return
	 */
	public Player getOwner(){
		return owner;
	}
	/**
	 * Sets the owner of the property
	 * @param play
	 */
	public void setOwner(Player play){
		owner = play;
	}
	/**
	 * Gets the mortgaged status of the property
	 * @return
	 */
	public boolean isMortgaged(){
		return mortgaged;
	}
	/**
	 * Un mortgages property
	 */
	public void setMortgage(boolean bool){
		mortgaged = bool;
	}
	/**
	 * Gets the property value
	 * @return
	 */
	public int getPropertyValue(){
		return propertyValue;
	}
	/**
	 * Gets the color group this property belongs to
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
	/*
	 * (non-Javadoc)
	 * @see Monopoly.Cell#toString()
	 */
	public String toString(){
		String status = "";
		
		if (isMortgaged())
			//will show if property is mortgaged
			status += " (Mortgaged)";
		
		
		return super.toString() + status;
	}
	/**
	 * Gets the property card
	 * @return Property card image
	 */
	public Image getPropertyCardImage() {
		return propertyCard;
	}

	
	

}
