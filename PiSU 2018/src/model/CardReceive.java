package model;

import controller.GameController;

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
	public int getCardNumber() {
		return cardNumber;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	@Override
	public int getAmount() {
		return amount;
	}
	
	@Override
	public void performAction(GameController gameController) {
		gameController.cardReceiveMoney(this);
//		Game game = gameController.getGame();
//		if (super.cardNumber == 1) { //Fødselsdag - Modtag 200 fra hver spiller.
//			game.getCurrentPlayer().getAccount().updateCash(game.getPlayers().size()*amount+amount); //all players are deducted 200, therefore the player to receive gets the extra "amount" which are then deducted in the loop below
//			for(int i = 0; i<(game.getPlayers().size()); i++) {
//				int ammountPaid = 0;
//				if(game.getPlayers().get(i).getAccount().getCash()>=200)
//				game.getPlayers().get(i).getAccount().updateCash(-amount);
//				
//				else 
//				ammountPaid = gameController.generateCash(game.getPlayers().get(i), 200);
//				
//			}
//
//		} 
//		else if(super.cardNumber == 10) { // Matador legatet: assetValue<15.000 Modtager 40.000
//			//Hensigten med assets/cash Det antages at cash/assets opdateres hver for sig og at assets også opdateres når der købes huse/hoteller.
//			int totValue = game.getCurrentPlayer().getAccount().getAssetValue()+ game.getCurrentPlayer().getAccount().getCash() ;
//			if (totValue < 15000)
//				game.getCurrentPlayer().getAccount().updateCash(amount);
//				
//		}else
//			game.getCurrentPlayer().getAccount().updateCash(amount);
	}	
}
