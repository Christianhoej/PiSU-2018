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
		if (fieldNumber == 10) 
			return (", du er på besøg i fængslet! ");
		 else if (fieldNumber==20)
			return (", du har fået gratis parkering! Du sidder en tur over!");
		 else 
			return (", du har landet på start! ");
		

	}
}
