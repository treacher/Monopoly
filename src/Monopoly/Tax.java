package Monopoly;
/**
 * Tax Cell, payer has to pay declared amount
 * 
 * @author Sean
 *
 */
public class Tax extends Special{
	
	private int price;

	public Tax(int ID, String name, int price) {
		super(ID, name);
		this.price = price;
	}

	/**
	 * 
	 * @return no option to return
	 */
	@Override
	public Option trigger(Player player) {
		 return new PayTaxOption(player, price);
	}

}
