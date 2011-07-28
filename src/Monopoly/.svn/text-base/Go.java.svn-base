package Monopoly;
/**
 * Class to represent the Go location
 * 
 * @author Sean Anderson
 *
 */
public class Go extends Special{
	
	final static int salary = 200;
/**
 * Constructs a Go Cell
 * @param ID
 * @param name
 */
	public Go(int ID, String name) {
		super(ID, name);
		
	}
	/*
	 * (non-Javadoc)
	 * @see Cell#trigger(Player)
	 */
	public Option trigger(Player player) {
		player.addMoney(salary);
		return null;
	}
	

}
