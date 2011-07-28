package Monopoly;
/**
 * GoToJail Cell, if a player lands on this, they will
 * be sentDirectly to jail, without passing Go
 * 
 * @author Sean Anderson
 *
 */
public class GoToJail extends Special{
	/**
	 * Constructs a GoToJail Cell
	 * @param ID
	 * @param name
	 */
	public GoToJail(int ID, String name) {
		super(ID, name);
	
	}
	/*
	 * (non-Javadoc)
	 * @see Cell#trigger(Player)
	 */
	public Option trigger(Player player) {
		player.goToJail();
		return null;
	}

}
