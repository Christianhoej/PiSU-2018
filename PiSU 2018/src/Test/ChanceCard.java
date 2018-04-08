package Test;

/**
 * Contains methods methods & attributes for the chancecards, which are extended/inherited in
 * the classes, CardMove, CardPay, CardReceive & CardPrison
 * @author Christian ++
 *
 */

// Bør klassen være abstract?
public class ChanceCard {

	protected String text;
	protected int cardNumber;
	
	
	public ChanceCard (int cardNumber, String text) {
		this.cardNumber = cardNumber;
		this.text = text;
	}
	
	
}
