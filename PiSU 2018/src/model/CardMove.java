package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods & attributes for chancecards that directs a player to move to a specific field.
 * @author Christian ++
 *
 */
import model.Game;
public class CardMove extends ChanceCard {

	private int moveTo;
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 * @param test
	 */
	public CardMove(int cardNumber, String text,int fieldNumber) {
		super(cardNumber,text);
		moveTo = fieldNumber;
	}
	/**
	 * Override
	 * Sets the players position to the specific field from the chancecard.
	 */
	@Override
	public void performAction(Game game) {
		game.getCurrentPlayer().setPosition(moveTo);
		//Rederi:
		//Find playerposition
		//Find dem i arrayet
	}
	/**
	 * Sets the players position to the nearest dock, from where the player is originally positioned. 
	 * @param game
	 */
	private void moveToDocks(Game game) {
		int position = game.getCurrentPlayer().getPosition();
		if (position<6 || position>36) {
			game.getCurrentPlayer().setPosition(6);
			for ( int i = 0; i<game.getPlayerAmount();i++) {
			game.getPlayers();
			}
		}
	}
	
	
	
}