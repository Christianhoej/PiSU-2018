package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Contains the method for adding a get out of prison card, to a player. 
 * @author 
 */
public class CardGetOutOfPrison extends ChanceCard {
		/**
		 * Constructor 
		 * @param cardNumber
		 * @param text
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
	 * Updates the amount of get out of prison cards, owned by a player.
	 * @param player
	 */
	@Override
	public void performAction(GameController gameController) {	
		gameController.playerGetsPrisonCard(this);
	}
	//Bør vi kunne låse kortene så de ikke indgår i kortbunken før de er brugt igen?
	
}
