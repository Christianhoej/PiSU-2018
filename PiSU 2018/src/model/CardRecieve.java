package model;

import model.Txt;

/**
 * Inheriting methods & attributes from the ChanceCard class
 * includes methods for when a player must move his car, following a drawn chancecard. 
 * @author Christian ++
 *
 */

public class CardRecieve extends ChanceCard {

	public CardRecieve(int cardNumber, String text) {
		super(cardNumber, text);
	}

	String[] fieldName = Txt.file("CardRecieve");
	private int amount;
	
	public void receiveMoney(Player player, int amount) {
		player.getAccount().updateCash(amount);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
