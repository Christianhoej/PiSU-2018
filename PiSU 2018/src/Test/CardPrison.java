package Test;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Contains methods for when a player must go to prison, following a drawn chancecard. 
 * 
 * @author Christian ++
 *
 */
public class CardPrison extends ChanceCard {

	private int noget;
	
	public CardPrison(int cardNumber, String text, int noget) {
		super(cardNumber, text);
	}
	
}
