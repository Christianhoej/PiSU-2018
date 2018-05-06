package model;

import java.util.Random;

import controller.GameController;

/**
 * Contains methods methods & attributes for the chancecards, which are
 * extended/inherited in the classes, CardMove, CardPay, CardReceive &
 * CardPrison. The class includes the method for shuffling the deck of
 * chancecards.
 * 
 * @author Christian ++
 *
 */

public class ChanceCard {

	protected String text;
	protected int cardNumber;
	protected int amount;
	private ChanceCard[] chanceDeck;

	/**
	 * Constructor
	 */
	public ChanceCard() {
		// this.cardNumber = cardNumber;
		// this.text = text;
		this.amount = 0;
	}

	/**
	 * Method for shuffling the deck of chancecards. 
	 * This makes the chancecards lay in a random order.
	 * 
	 * @param chanceDeck - the array of all the chancecards
	 */
	public void shuffleDeck(ChanceCard[] chanceDeck) {
		Random rand = new Random();
		for (int i = chanceDeck.length - 1; i > 0; i--) {

			int index = rand.nextInt(i + 1);
			ChanceCard card = chanceDeck[index];
			chanceDeck[index] = chanceDeck[i];
			chanceDeck[index] = card;
		}
	}

	public ChanceCard[] getShuffleDeck() {
		return chanceDeck;
	}

	public String toString() {
		return text;
	}

	public int getAmount() {
		return amount;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	/**
	 * Is overridden in the sub-classes
	 * @param gameController
	 */
	public void performAction(GameController gameController) {
	}

}
