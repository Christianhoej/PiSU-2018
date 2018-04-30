package model;

import controller.GameController;

/**
 * Inheriting methods & attributes from the ChanceCard class
 * Contains methods for when a player must go to prison, following a drawn chancecard. 
 * 
 * @author Christian ++
 *
 */
public class CardPrison extends ChanceCard {

	private int fieldNumber;
	
	public CardPrison(int cardNumber, String text) {
		super(cardNumber, text);
		fieldNumber = 10;
	
	}
	@Override
	public void performAction(GameController gameController) {
		gameController.getGame().getCurrentPlayer().setInPrison(1);		
		gameController.getGame().getCurrentPlayer().setPosition(fieldNumber);

	}
	
}
