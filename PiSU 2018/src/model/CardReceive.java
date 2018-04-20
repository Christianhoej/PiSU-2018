package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * includes methods for when a player must move his car, following a drawn chancecard. 
 * @author Christian ++
 *
 */

public class CardReceive extends ChanceCard {

	private int amount;
	
	public CardReceive(int cardNumber, String text, int amount){
		super(cardNumber, text);
		this.amount = amount;
	}
	
	public void receiveMoney(Player player) {

		player.getAccount().updateCash(amount);
	}

	public int getAmount() {
		return amount;
	}
	
	
}
