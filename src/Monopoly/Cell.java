package Monopoly;

import java.awt.Component;

/**
 * Abstract Class that represents a cell on the monopoly board
 * @author Michael Treacher
 *
 */
public abstract class Cell{
	private int ID;
	private String name;
	/**
	 * Abstract class constructor for subclasses that extend Cell
	 * @param ID
	 * @param name
	 */
	public Cell(int ID,String name){
	this.ID = ID;
	this.name = name;
	}
	/**
	 * When you land on a cell on the monopoly board this method is called
	 * This method takes care of the things that take place when you land on any type of cell
	 * for example if you landed on a Street it would calculate the rental price and take it away 
	 * from the player etc.
	 * @param player - the player that lands on the cell
	 * @return 
	 */
	public abstract Option trigger(Player player);
	
	public String getName(){
		return name;
	}
	
	public int getID(){return ID;}
	
	public String toString(){
		return name;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o){
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		Cell obj = (Cell)o;
		if(this.ID==obj.ID) return true;
		return false;
	}
}
