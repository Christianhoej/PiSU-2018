package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian, Yoss +
 *
 */
public class CardPay extends ChanceCard {

	private int amount;
	
	/**
	 * Constructor
	 * @param cardNumber
	 * @param text
	 * @param amount
	 */
	public CardPay(int cardNumber, String text, int amount) {
		super( cardNumber, text);
		this.amount = amount;
	}
	/**
	 * Overrides
	 * For the chancecard of when a player must pay for each hotel and house he/she owns. 
	 */
	@Override
	public void performAction(GameController gameController) {

		if(super.cardNumber==18) { //kortet betaler per hus og hotel
			int housePrice = 800;
			int hotelPrice = 2300;
			
				gameController.getGame().getCurrentPlayer().getAccount().updateCash(payPerHouseAndHotel(gameController.getGame(),housePrice,hotelPrice));
					
		}
		else if(super.cardNumber==19) { //kortet betaler per hus og hotel
			int housePrice = 500;
			int hotelPrice = 2000;
			
			gameController.getGame().getCurrentPlayer().getAccount().updateCash(payPerHouseAndHotel(gameController.getGame(),housePrice,hotelPrice));
		}
		
		else
		gameController.getGame().getCurrentPlayer().getAccount().updateCash(amount);
		
	}
	public int getAmount() {
		return amount;
	}
	/**
	 * 
	 * @param game
	 * @param housePrice
	 * @param hotelPrice
	 * @return
	 */
	private int payPerHouseAndHotel(Game game, int housePrice, int hotelPrice) {
		int houses=0;
		int hotels=0;
		int[]array = game.getCurrentPlayer().getOwnedHouses();
		for (int i = 0; i< array.length; i++) {//antal huse og hoteller findes
			if (array[i]==5)
			hotels++;
			else
				houses+= array[i];
		}
		
		return -(houses*housePrice + hotels*hotelPrice);
	}
	
}
