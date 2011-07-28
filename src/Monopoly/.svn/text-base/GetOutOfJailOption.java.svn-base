package Monopoly;
/**
 * Get out of jail option
 * @author Sean && Michael
 *
 */
public class GetOutOfJailOption extends Option {
	
	private Player p;
	private int jailPrice = 50;
/**
 * Constructs a get out of jail option
 * @param p A player
 */
	public GetOutOfJailOption(Player p){
		this.p = p;
	}

	/*
	 * (non-Javadoc)
	 * @see Option#activate()
	 */
	public String activate() {
		if(p.getOutOfJail())		
			return p.toString() + " has paid " + jailPrice + " to get out of jail";
		
		else 
			return "Insufficient funds";
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Get out of jail";
	}

}
