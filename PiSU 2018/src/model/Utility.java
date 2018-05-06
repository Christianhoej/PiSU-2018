package model;

import controller.GameController;

/**
 * Contains methods for the utilities on the gameboard.
 * 
 * @author Josephine
 *
 */

public class Utility extends Property {

	/**
	 * Constructor
	 * 
	 * @param fieldNumber - fieldsnumber of the utility. 
	 */
	public Utility(int fieldNumber) {
		super(fieldNumber);

	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		notifyChange();
	}

	/**
	 * Overrides the original landOnfield method. Offers th utility to the player
	 * landing on it.
	 */
	@Override
	public void landOnField(GameController gameController) {
		gameController.offerToBuyProperty(this);
		notifyChange();

	}
}
