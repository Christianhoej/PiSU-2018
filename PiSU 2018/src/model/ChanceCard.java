package model;

import controller.GameController;

/**
 * Contains methods methods & attributes for the chancecards, which are extended/inherited in
 * the classes, CardMove, CardPay, CardReceive & CardPrison.
 * The class includes the method for shuffling the deck of chancecards. 
 * @author Christian ++
 *
 */

public class ChanceCard {
 
	protected String text;
	protected int cardNumber;

	
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 */
	public ChanceCard (int cardNumber, String text) {
		this.cardNumber = cardNumber;
		this.text = text;

		
	}
	
	public ChanceCard[] shuffleDeck(ChanceCard[] chanceDeck) {
		 
		 ChanceCard[] chanceCards = new ChanceCard[chanceDeck.length];
			
			int i = 0;

			while(i < chanceDeck.length) {
				int value =(int)(Math.random()*chanceDeck.length);
				if(chanceCards[value]== null){
					chanceCards[value] = chanceDeck[i];
					i++;
				}
			}
			return chanceCards;
							
		}
	public int getCardNumber() {
		
	return cardNumber;
//	}
//	
//	public void performAction(Player player, int amount, Player[] playerArray) {
//		
//	}
//
//	public void performAction(Player currentPlayer, Player[] playerArray) {
//		
//	}
//
//	public void performAction(Player player) {
		
	}

	public void performAction(GameController gameController) {
		// TODO Auto-generated method stub
		
	}
	
}
