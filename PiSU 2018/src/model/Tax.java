package model;

import controller.GameController;
/**@author Gunn + Yoss 
 * 
 */
import gui_main.GUI;

/**
 * Contains methods for the tax-field. This includes methods for the price. 
 * 
 * @author
 *
 */

public class Tax extends Fields {

	private GUI gui;
	private int price;

	/**
	 * Constructor
	 * 
	 * @param fieldNumber - fieldnumber of the tax field. 
	 */
	public Tax(int fieldNumber) {
		super(fieldNumber);
		if (fieldNumber == 4) {
			price = 4000;
		} else {
			price = 2000;
		}
	}

	public int getPrice() {
		return price;
	}

	/**
	 * toString of the option of paying 4000kr or 10%
	 */
	@Override
	public String toString() {
		if (fieldNumber == 4) {
			return " vil du helst betale 10% af dine samlede værdier eller ";
		} else {
			return "";
		}
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Method for when a player lands on a tax field. 
	 */
	@Override
	public void landOnField(GameController gameController) {
		gameController.payTax(this);
	}
}
