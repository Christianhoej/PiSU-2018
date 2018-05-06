package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class. Contains the
 * method for adding a get out of prison card, to a player.
 * 
 * @author Oliver
 */
public class CardGetOutOfPrison extends ChanceCard {
	/**
	 * Constructor
	 * 
	 * @param cardNumber
	 * @param text
	 *            - text of the cardnumber
	 */
	public CardGetOutOfPrison(int cardNumber, String text) {
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
	 * Performs action when a player lands on a chancecard.
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.playerGetsPrisonCard(this);
	}

}
