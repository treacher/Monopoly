package Monopoly;
/**
 * FreeParking cell, where if a player lands on it
 * they will receive all the monies held by parking
 * 
 * @author Sean Anderson
 *
 */


public class FreeParking extends Special{
	
	private int parkingMoney = 0;

	public FreeParking(int ID, String name) {
		super(ID, name);		
	}
	
	/** 
	 * trigger method which will give the player all the money
	 * held by parking
	 * 
	 * @return no option to deal with
	 */
	@Override
	public Option trigger(Player player){
		player.addMoney(parkingMoney);		
		parkingMoney = 0;
		return null;
	}
	
	/**
	 * for when a player pays tax etc
	 * money is added to parking space
	 * 
	 */
	public void addCash(int amount){
		parkingMoney += amount;		
	}

}
