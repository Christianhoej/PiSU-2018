package model;


import model.Txt;


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



	String[] fieldName = Txt.file("CardReceive");
	private int amount;
	
	public void receiveMoney(Player player, int amount) {

		player.getAccount().updateCash(amount);
	}

	public int getAmount() {
		return amount;
	}
	
	
}
