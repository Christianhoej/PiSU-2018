package model;

/**
 * Contains methods methods & attributes for the chancecards, which are extended/inherited in
 * the classes, CardMove, CardPay, CardReceive & CardPrison
 * @author Christian ++
 *
 */

public class ChanceCard {
 
	protected String text;
	protected int cardNumber;
	private int action; //int der tilkendegiver hændelse i Chance
	
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 */
	public ChanceCard (int cardNumber, String text, int action) {
		this.cardNumber = cardNumber;
		this.text = text;
		this.action=action;
		
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
	public int getAction() {
		return action; 
	}
	
}
