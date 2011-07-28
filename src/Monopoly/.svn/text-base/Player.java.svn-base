package Monopoly;
import java.util.ArrayList;

import GraphicalUserInterface.MonopolyBoardFrame;

/** 
 * Class to represent each player in the game
 * A player will always know when they PassGo and pay themselves
 * Will deal with rolling and in-jail situations.
 * 
 * @author Sean Anderson
 *
 */
public class Player {
	
	static int PASS_GO = 200;
	static int BAIL_PRICE = 50;
	static int BOARD_SIZE = 39;
	
	private String name;
	
	private int roll1;
	private int roll2;
	
	private Piece piece;
	private int money;
	
	//used to count how many turns a player is in jail
	private int jailCount = 0;
	private boolean inJail = false;
	
	private ArrayList<Property> properties = new ArrayList<Property>();
	
	private boolean hasRolled = false;
	
	
	//represents the players current position on the board
	private int position = 0;
	private int oldPosition = 0;

	/**
	 * Constructs a player object
	 * @param p Players piece
	 * @param startingMoney initial amount of money
	 */
	public Player(Piece p,String name, int startingMoney){
		this.piece = p;
		money = startingMoney;	
		this.name = name;
	}
	/**
	 * Gets the amount of money the player currently has
	 * @return Amount of money player has
	 */
	public int getMoneyAmount(){
		return money;
	}
	/**
	 * Gets if the player has rolled this turn
	 * @return
	 */
	public boolean hasRolled(){
		return hasRolled;
	}
	/**
	 * sets the has rolled boolean
	 * @param bool Boolean
	 */
	public void setRolled(boolean bool){
		hasRolled = bool;
	}
	
	/**
	 * Method to simulate a player rolling 
	 * and will advance the player the amount rolled
	 * aswell as deal with inJail status
	 * 
	 * @param roll value of each dice
	 * @return current positionID on board
	 */
	public int roll(int die1, int die2){
		setOldPosition(position);
		if (inJail){
			//rolled a double, get out of jail
			if (die1 == die2){
				inJail = false;		
				MonopolyBoardFrame.appendText(advancePlayer(die1 + die2));
			}				
			else
				jailCount--;			
		}	
		//Player isn't in jail
		else
			MonopolyBoardFrame.appendText(advancePlayer(die1 + die2));
			
		
		roll1 = die1;
		roll2 = die2;
		
		return position;
	}
	/**
	 * Get players name
	 * @return Players name
	 */
	public String getName(){
		return name;
	}
	/**
	 * simple method to return players current monies
	 * @return players balance
	 */
	public int getBalance(){
		return money;
	}
	/**
	 * adds to players balance, may be negative value to take money away from player
	 * 
	 * @param amount to add
	 * @return if the player has insufficient funds false is returned
	 */
	public String addMoney(int amount){
		money += amount;
		return name + " receives " + amount;
	}
	/**
	 * Removes money from Player and Pays another player
	 * 
	 * @param amountPlayer
	 * @return String declaring payment to player
	 */
	public String payPlayer(int amount, Player other){
		
		other.addMoney(amount);
		money -=(amount);
		
		return name + " pays " + other + " " + amount;
		
	}
	/**
	 * method to check if player is incarcerated
	 * @return jail status
	 */
	public boolean isInJail(){
		jailCount--;
		if (jailCount < 0){
			inJail = false;
		}			
		
		return inJail;
	}
	/**
	 * sends player directly to jail for 3 turns, unless they buy out
	 */
	public String goToJail(){
		inJail = true;
		jailCount = 3;
		//jailCellPosition
		position = 10;
		return name + " has gone to jail!";
	}
	/**
	 * 
	 * @return
	 */
	public boolean getOutOfJail(){
		if (money < BAIL_PRICE)
			return false;
		money -= 50;
		this.inJail = false;
		return true;
	}
	/**
	 * get the players position on the game board
	 * @return positionID
	 */
	public int getPosition(){
		return position;
	}
	
	
	/**
	 * advances a player an amount of spaces
	 * also will add PassGo salary
	 * @param spaces
	 * @return players current position
	 */
	public String advancePlayer(int spaces){
		position += spaces;
		
		if (position > BOARD_SIZE){
			position = position - BOARD_SIZE - 1;
			//player passed Go
			money += PASS_GO;
			MonopolyBoardFrame.appendText(name + " passed go");
		}
		
		return name.toString() + " has advanced " + spaces +" spaces. "+name.toString()+" has landed on " + Board.cells.get(position).toString();
	}
	/**
	 * Mortgage a property
	 * @param p
	 * @return
	 */
	public boolean mortgageProperty(Property p){
		p.setOwner(this);
		p.setMortgage(true);
		return properties.add(p);
	}
	
	/**
	 * Player buys a property and deducts the cost from the players monies
	 * 
	 * @param Property to be bought
	 * @return whether purchase was successful
	 */
	public boolean buyProperty(Property p,int price){
		if (price > money){
			return false;
		}
		else{			
			//if buying off another player
			if(p.getOwner() != null && !this.equals(p.getOwner())){
				p.getOwner().addMoney(price);
				p.getOwner().properties.remove(p);
			}
			
			money -= price;
			p.setOwner(this);
			
			return properties.add(p);
		}	
	}
	
	/**
	 * returns the players last roll1
	 * 
	 * @return roll1
	 */
	public int getRoll1(){
		return roll1;
	}
	/**
	 * returns players last roll2
	 * 
	 * @return roll2
	 */
	public int getRoll2(){
		return roll2;
	}
	/**
	 * Gets the players piece
	 * @return
	 */
	public Piece getPiece(){
		return piece;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		String output = "";
		if(inJail){
			output = "(In Jail) ";
		}	
		return output + name.toString();
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o){
		if (o == null || o.getClass() != getClass() )
			return false;
		
		Player p = (Player) o;
		return piece.equals(p.piece);
	}
	/**
	 * Gets the players properties
	 * @return
	 */
	public ArrayList<Property> getProperties(){
		return properties;
	}
	/**
	 * Sells all the assets that the player owns;
	 */
	public void sellAssets(){
		for (Property p : properties)
			p.setOwner(null);
		properties.removeAll(properties);
	}
	/**
	 * Sets the old Position
	 * @param oldPosition
	 */
	public void setOldPosition(int oldPosition) {
		this.oldPosition = oldPosition;
	}
	/**
	 * Gets the old position
	 * @return
	 */
	public int getOldPosition() {
		return oldPosition;
	}
	public void setPosition(int pos){
		if(pos > 40 || pos < 0) return ;
		this.position = pos;
		
	}

}
