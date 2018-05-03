package model;

import java.util.Random;

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
	protected int amount;


	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 */
	public ChanceCard (int cardNumber, String text) {
		this.cardNumber = cardNumber;
		this.text = text;
		this.amount=0;


	}

	public static ChanceCard[] shuffleDeck(ChanceCard[] chanceDeck) {

		Random rand = new Random();
		
		for(int i = chanceDeck.length-1; i>0; i--) {
			
			int index = rand.nextInt(i+1);
			ChanceCard card = chanceDeck[index];
			chanceDeck[index] = chanceDeck[i];
			chanceDeck[index] = card;
		}
		return chanceDeck;
//		
//		ChanceCard[] chanceCards = new ChanceCard[chanceDeck.length];
//
//		int i = 0;
//
//		
//		
//		
//		while(i < chanceDeck.length) {
//			int value =(int)(Math.random()*chanceDeck.length);
//			if(chanceCards[value]== null){
//				chanceCards[value] = chanceDeck[i];
//				i++;
//			}
//		}
		
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

	public void performAction(GameController gameController) {
		// TODO Auto-generated method stub

	}

}
