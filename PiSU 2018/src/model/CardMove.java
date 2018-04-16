package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods & attributes for cards that directs a player to move to a specific field.
 * @author Christian ++
 *
 */
public class CardMove extends ChanceCard {

	private int test;
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 * @param test
	 */
	public CardMove(int cardNumber, String text,int test) {
		super(cardNumber,text);
	}
	
	
	
}