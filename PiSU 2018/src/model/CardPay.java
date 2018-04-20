package model;
/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian ++
 *
 */
public class CardPay extends ChanceCard {

	private int amount;
	
	public CardPay(int cardNumber, String text, int amount) {
		super( cardNumber, text);
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	
}
