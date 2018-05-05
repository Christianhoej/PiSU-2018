package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class Contains methods
 * for when a player must go to prison, following a drawn chancecard.
 * 
 * @author 
 */
public class CardPrison extends ChanceCard {

	/**
	 * Constructor
	 * @param cardNumber
	 * @param text
	 */
	public CardPrison(int cardNumber, String text) {
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
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.cardGoToJail(this);

	}

}
