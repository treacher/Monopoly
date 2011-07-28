package Monopoly;
/**
 * abstract super class for Options
 * 
 * @author Sean Anderson
 *
 */
public abstract class Option {
	
	protected boolean autoFire = false;

	
	/**
	 * will activate the option
	 * and return a verbose description
	 * 
	 * @param p
	 * @return description of what happened
	 */
	public abstract String activate();
		
	

	/**
	 * method to tell if this option should be
	 * activate automatically
	 * 
	 * @return autoFire
	 */
	public boolean autoFire() {		
		return autoFire;
	}

}
