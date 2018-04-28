package model;
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
		super(cardNumber, text);
		
	}
	/**
	 * Updates the amount of get out of prison cards, owned by a player.
	 * @param player
	 */
	public void performAction(Player player) {
		player.getAccount().updatePrisonCard(1);
	}
	
}
