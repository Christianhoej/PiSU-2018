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

	String[] fieldName = Txt.file("CardReceive");
	
	public CardReceive(int cardNumber, String text, int amount){
		super(cardNumber, text);
		this.amount = amount;
	}
	@Override
	public void performAction(Player currentPlayer, Player[] playerArray) {
		if (super.cardNumber == 1) {
			currentPlayer.getAccount().updateCash(playerArray.length*amount+amount); //all players are deducted 200, therefore the player to receive gets the extra "amount"
			for(int i = 0; i<playerArray.length; i++) {
				playerArray[i].getAccount().updateCash(-200);
			}
		} else

		currentPlayer.getAccount().updateCash(amount);
	}

	public int getAmount() {
		return amount;
	}
	
	
}
