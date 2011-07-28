package Monopoly;
/**
 * auto firing option that pays tax when landed on
 * 
 * @author Sean
 *
 */
public class PayTaxOption extends Option {

	private int amount;
	private Player p;
	
	public PayTaxOption(Player p, int price) {
		autoFire = true;
		this.amount = price;
		this.p = p;
	}

	@Override
	public String activate() {
		p.addMoney(-(amount));	
		return toString();
	}
	
	public String toString(){
		return p.toString()+" pays: " + amount + " in Tax.";
	}

}
