package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods for when a player i receiving money following a draw chancecard. 
 * @author Christian
 *
 */

public class CardReceive extends ChanceCard {

	private int amount;
	
	/**
	 * Constructor
	 * @param cardNumber
	 * @param text
	 * @param amount
	 */
	public CardReceive(int cardNumber, String text, int amount){
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
	 * Overrides
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.cardReceiveMoney(this);
	}	
}
