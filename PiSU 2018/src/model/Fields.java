package model;

import java.util.ArrayList;
import controller.GameController;
import designpatterns.Subject;

/**
 * Methods and attributes for creating the gameboard. 
 * This is the class that makes it possible to make the gameboard
 * 
 * @author
 *
 */

public abstract class Fields extends Subject {

	protected int fieldNumber;
	protected String fieldName;

	/**
	 * Constructor
	 * 
	 * @param fieldNumber
	 */
	public Fields(int fieldNumber) {
		this.fieldNumber = fieldNumber;
		notifyChange();
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	protected void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
		notifyChange();
	}

	public String getFieldName() {
		return fieldName;

	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		notifyChange();
	}

	public abstract String toString();

	/**
	 * Is laterovevridden. 
	 * @param gameController
	 */
	public void landOnField(GameController gameController) {

	}

}
