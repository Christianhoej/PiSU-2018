package model;

import controller.GameController;

/**
 * Includes methods and attributtes of the neutral fields on the board.
 * 
 * @author Yoss
 *
 */
public class NeutralField extends Fields {

	/**
	 * Constructor
	 * 
	 * @param fieldNumber
	 */
	public NeutralField(int fieldNumber) {
		super(fieldNumber);
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public void landOnField(GameController gameController) {
		gameController.neutralField(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
