package model;

import model.Txt;

/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods for when a player i receiving money following a draw chancecard. 
 * @author Christian ++
 *
 */

public class CardReceive extends ChanceCard {

	private int amount;
	
	public CardReceive(int cardNumber, String text, int amount){
		super(cardNumber, text);
		this.amount = amount;
	}
	@Override
	public void performAction(Game game) {
		
		if (super.cardNumber == 1) {
			game.getCurrentPlayer().getAccount().updateCash(game.getPlayers().size()*amount+amount); //all players are deducted 200, therefore the player to receive gets the extra "amount"
			for(int i = 0; i<(game.getPlayers().size()); i++) {
				game.getPlayers().get(i).getAccount().updateCash(-amount);
			}

		} 
		else if(super.cardNumber == 10) { // Matador legatet: assetValue<15.000 Modtager 40.000
			//Hensigten med assets/cash Det antages at cash/assets opdateres hver for sig og at assets også opdateres når der købes huse/hoteller.
			int totValue = game.getCurrentPlayer().getAccount().getAssetValue()+ game.getCurrentPlayer().getAccount().getCash() ;
			if (totValue < 15000)
				game.getCurrentPlayer().getAccount().updateCash(amount);
				
		}else

		game.getCurrentPlayer().getAccount().updateCash(amount);
	}

	public int getAmount() {
		return amount;
	}
	
	
}
