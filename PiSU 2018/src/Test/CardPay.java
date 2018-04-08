package Test;
/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian ++
 *
 */
public class CardPay extends ChanceCard {

	private int noget;
	
	public CardPay(int cardNumber, String text, int noget) {
		super( cardNumber, text);
	}
	
}
