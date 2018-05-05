package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class. Includes methods
 * for chancecards of when a player must pay money.
 * 
 * @author Christian, Yoss +
 *
 */
public class CardPay extends ChanceCard {

	private int amount;

	/**
	 * Constructor
	 * 
	 * @param cardNumber
	 * @param text
	 * @param amount
	 */
	public CardPay(int cardNumber, String text, int amount) {
		this.cardNumber = cardNumber;
		this.text = text;
		this.amount = amount;
	}

	@Override
	public int getCardNumber() {
		return cardNumber;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	/**
	 * Overrides For the chancecard of when a player must pay for each hotel and
	 * house he/she owns.
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.cardPay(this);
	}
}
