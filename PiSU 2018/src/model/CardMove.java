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
	public CardMove(int cardNumber, String text,Fields fieldNumber) {
		super(cardNumber,text);
		moveTo = fieldNumber.getFieldNumber();
	}
	public void moveToField(Player player) {
		player.setPosition(moveTo);
	}
	
	
	
}