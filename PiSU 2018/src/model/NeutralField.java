package model;

import controller.GameController;

/**
 * Includes methods and attributtes of the neutral fields on the board.
 * 
 * @author Yoss
 *
 */
public class NeutralField extends Fields {

	String[] guiMessages = Txt.fileString("GameMessages.txt");

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
		if (fieldNumber == 10) {
			return (guiMessages[0]);
		} else if (fieldNumber == 20) {
			return (guiMessages[1]);
		} else {
			return (guiMessages[2]);
		}
	}
}
