package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods & attributes for cards that directs a player to move to a specific field.
 * @author Christian ++
 *
 */
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
	@Override
	public void performAction(Game game) {
		game.getCurrentPlayer().setPosition(moveTo);
		//Rederi:
		//Find playerposition
		//Find dem i arrayet
	}
	private void moveToDocks(Game game) {
		int position = game.getCurrentPlayer().getPosition();
		if (position<6 || position>36) {
			game.getCurrentPlayer().setPosition(6);
			for ( int i = 0; i<game.getPlayerAmount();i++) {
			game.getPlayers(i)
			}
		}
	}
	
	
	
}