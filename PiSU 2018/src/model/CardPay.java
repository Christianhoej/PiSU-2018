package model;
import model.Txt;
/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian, Yoss +
 *
 */
public class CardPay extends ChanceCard {
	
	String[] fieldName = Txt.file("CardPay");

	private int amount;
	
	public CardPay(int cardNumber, String text, int amount, int action) {
		super( cardNumber, text, action);
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	
}
