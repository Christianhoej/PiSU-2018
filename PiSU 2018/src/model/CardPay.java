package model;
/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian ++
 *
 */
public class CardPay extends ChanceCard {

	private int amountToPay;
	
	public CardPay(int cardNumber, String text, int amountToPay) {
		super( cardNumber, text);
		this.amountToPay = amountToPay;
	}
	public int getAmountToPay() {
		return amountToPay;
	}
	
}
