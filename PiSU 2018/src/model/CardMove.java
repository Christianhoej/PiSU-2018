package model;
import controller.GameController;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods & attributes for chancecards that directs a player to move to a specific field.
 * @author 
 *
 */
import model.Game;
public class CardMove extends ChanceCard {

	private int moveTo;
	private String text;
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 * @param test
	 */
	public CardMove(int cardNumber, String text,int fieldNumber) {
		super(cardNumber,text);
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	/**
	 * Override
	 * Sets the players position to the specific field from the chancecard.
	 */
	@Override
	public void performAction(GameController gameController) {
		gameController.cardMoveToField(this);
		
//		if(super.cardNumber<=21) {
//			//nearest ferry + double rent
//			moveToFerryDouble(gameController);
//		}
//		else if(super.cardNumber<=23) {
//			//Move -3
//			gameController.getGame().getCurrentPlayer().setPosition(gameController.getGame().getCurrentPlayer().getPosition()-3);
//			//land on field
//			gameController.getGame().getFields().get(gameController.getGame().getCurrentPlayer().getPosition()).landOnField(gameController);;
//		}
//		else if(super.cardNumber==24) {
//			//Start
//			gameController.getGame().getCurrentPlayer().setPosition(0);//1 alt efter hvordan position kalkuleres
//			//landOnField
//			gameController.getGame().getFields().get(0).landOnField(gameController);
//		}
//		else if(super.cardNumber == 25) {
//			//rådhuspladsen
//			gameController.getGame().getCurrentPlayer().setPosition(39);//40 alt efter hvordan position kalkuleres
//			// landOnField
//			gameController.getGame().getFields().get(39).landOnField(gameController);
//
//		}
//		else if(super.cardNumber==26) {
//			//molslinje
//			gameController.getGame().getCurrentPlayer().setPosition(25);//26 alt efter hvordan position kalkuleres
//			// landOnField
//			gameController.getGame().getFields().get(25).landOnField(gameController);
//
//		}
//		else if (super.cardNumber == 27) {
//			//Move to Grønningen
//			gameController.getGame().getCurrentPlayer().setPosition(24);//25 alt efter hvordan position kalkuleres
//			//landOnField
//			gameController.getGame().getFields().get(24).landOnField(gameController);
//
//		}
//		else {
//			//Move to Frederiksberg Allé
//			gameController.getGame().getCurrentPlayer().setPosition(11);//12 alt efter hvordan position kalkuleres
//			//landOnField
//			gameController.getGame().getFields().get(11).landOnField(gameController);
//		}
	}
	private void moveToFerryDouble(GameController gameController) {
		Game game = gameController.getGame();
		int position = game.getCurrentPlayer().getPosition();
		Player player = game.getCurrentPlayer();
		int arrayPositionOfFerry = 0;

		if ((position)<6 || (position)>=36) {
			int oldPosition = position;
			game.getCurrentPlayer().setPosition(5); // Obs skal lige høre hvordan position skal gemmes/ dvs. enten skal den være 5 eller 6 når der flyttes.
			arrayPositionOfFerry = 5;
			//If player passes start
			if ((oldPosition)>(position))
				game.getCurrentPlayer().getAccount().updateCash(4000);
		}
		else if ((position) < 16) {

			game.getCurrentPlayer().setPosition(15);
			arrayPositionOfFerry = 15;
		}
		else if ((position) < 26) { 

			game.getCurrentPlayer().setPosition(25);
			arrayPositionOfFerry = 25;
		}

		else if ((position) < 36) {

			game.getCurrentPlayer().setPosition(35);
			arrayPositionOfFerry = 35;
		}

		if(!game.getFields().get(arrayPositionOfFerry).getOwner().equals(null)) {//.equals or == null test
			gameController.ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), player);
			gameController.ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), player);
		}

		else {
			game.getFields().get(arrayPositionOfFerry).landOnField(gameController);
		}



	}

}

