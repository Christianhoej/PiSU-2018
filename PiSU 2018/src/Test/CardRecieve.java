package Test;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * includes methods for when a player must move his car, following a drawn chancecard. 
 * @author Christian ++
 *
 */
public class CardRecieve extends ChanceCard {

	private int noget;
	
	public CardRecieve(int cardNumber, String text, int noget) {
		super(cardNumber, text);
	}
	
}
