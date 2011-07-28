package Monopoly;
/**
 * Jail cell, where players are sent if they are in jail
 * players can also be 'visiting', where they may still
 * roll their next turn
 * @author Sean Anderson
 *
 */
public class Jail extends Special{
	/**
	 * Constructs a Jail Cell
	 * @param ID
	 * @param name
	 */
	public Jail(int ID, String name) {
		super(ID, name);
		
	}

	/*
	 * (non-Javadoc)
	 * @see Cell#trigger(Player)
	 */
	public Option trigger(Player player) {		
	
		if (player.isInJail())
			return new GetOutOfJailOption(player);
		
		return null;
	}

}
