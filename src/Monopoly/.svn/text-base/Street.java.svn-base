package Monopoly;

import java.awt.Image;

/**
 * Class to represent street objects which is a subclass of property.
 * @author Michael Treacher
 *
 */
public class Street extends Property {

	private int houses = 0;// 6 houses represents a hotel.
	private int rent;
	private int housePrice;
	private int hotelPrice;
	/**
	 * Constructs a street object
	 * @param ID
	 * @param name
	 * @param propertyValue
	 * @param color
	 * @param rent
	 * @param housePrice
	 * @param hotelPrice
	 */
	public Street(int ID, String name, int propertyValue,Color color,int rent,int housePrice,int hotelPrice,Image img) {
		super(ID, name, propertyValue,color,img);
		this.rent = rent;
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		color.addStreet(this);
	}


	/**
	 * Adds a house to a property iff there is not more than 5 houses.
	 */
	public boolean addHouse(int amount){
		if(this.getOwner().getMoneyAmount() >= housePrice * amount){
			if(amount == 0 || (houses + amount) > 6){
				return false;
			}	
			houses += amount;
			this.getOwner().addMoney(-(housePrice * amount));
			return true;
		}
		return false;
	}
	/**
	 * Gets the amount of houses on the property
	 * @return
	 */
	public int getHouses(){
		return houses;
	}


	/*
	 * (non-Javadoc)
	 * @see Property#rent()
	 */
	public int rent(Player p) {
		int hotels = 0;
		int numberOfHouses = houses;
		if(houses == 6){
			hotels++;
			numberOfHouses = 0;
		}
		return rent  + (25 * numberOfHouses) + (200 * hotels);
	}
	/**
	 * Gets the price of one house
	 * @return
	 */
	public int getHousePrice(){
		return housePrice;
	}
	/**
	 * Gets the price of one hotel
	 * @return
	 */
	public int getHotelPrice(){
		return hotelPrice;
	}

	public boolean streetSetOwnedBy(Player p){
		boolean ownsAll = true;

		for(Property street : this.getColor().getSet()){
			if (street.getOwner() == null || !street.getOwner().equals(p))
				ownsAll = false;
		}
		return ownsAll;		
	}

	@Override
	public String toString(){
		String status = "";

		//will show if property is mortgaged
		if (houses > 0 && houses < 6)		
			status += " (" + houses + " houses)";

		else if (houses == 6)
			status += " (1 hotel)";


		return super.toString() + status;
	}

}
