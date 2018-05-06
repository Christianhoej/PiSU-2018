package model;
import controller.GameController;


/**
 Inheriting methods & a

ttributes from the ChanceCard class
 * Includes methods & attributes for chancecards that directs a player to move to a specific field.
 * @author Josephine
 */
import model.Game;
public class CardMove extends ChanceCard {

	private String text;
	/**
	 * Constructor 
	 * @param cardNumber - number of the card
	 * @param text - Text of the card
	 * @param fieldNumber - the fieldnumber of which a player moves to. 
	 */
	public CardMove(int cardNumber, String text, int fieldNumber) {
		this.cardNumber = cardNumber;
		this.text = text;
	}

	@Override
	public int getCardNumber() {
		return cardNumber;
	}

	@Override
	public String toString() {
		return text;
	}
	/**
	 * Overrides
	 * Sets the players position to the specific field from the chancecard.
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.cardMoveToField(this);
	}
}