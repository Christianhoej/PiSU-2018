package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Contains methods for when a player must go to prison, following a drawn chancecard. 
 * 
 * @author Christian ++
 *
 */
public class CardPrison extends ChanceCard {

	private int fieldNumber;
	
	public CardPrison(int cardNumber, String text, int action) {
		super(cardNumber, text, action);
		fieldNumber = 10;
	
	}
	public void moveToPrison(Player player) {
		player.setInPrison(1);		
		player.setPosition(fieldNumber);

	}
	
}
