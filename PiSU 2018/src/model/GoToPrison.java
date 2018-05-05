package model;

import controller.GameController;

/**
 * Includes the methods for when a player is sent directly to prison.
 * 
 * @author Christian, Yoss, Gunn
 *
 */
public class GoToPrison extends Fields {

	/**
	 * Constructor
	 * 
	 * @param fieldNumber
	 */
	public GoToPrison(int fieldNumber) {
		super(fieldNumber);
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * When a player lands on the go to prison field. Sets the players new position.
	 * +1 to the int controlling how long a player has been prison.
	 */
	@Override
	public void landOnField(GameController gameController) {
		gameController.getGame().getCurrentPlayer().setPosition(10);
		gameController.getGame().getCurrentPlayer().setInPrison(1);
	}

}
