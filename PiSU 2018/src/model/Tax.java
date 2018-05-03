package model;

import controller.GameController;
/**@author Gunn + Yoss 
 * 
 */
import gui_main.GUI;

public class Tax extends Fields{

	String[] guiMessages = Txt.fileString("GameMessages.txt");
	private GUI gui;
	private int price;
	public Tax(int fieldNumber) {
		super(fieldNumber);
		if (fieldNumber == 4) {
			price = 4000;
		}
		else {
			price = 2000;
		}
	}

	
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		if (fieldNumber == 4) {
			return " vil du helst betale 10% af dine samlede værdier eller ";
		}
		else {
			return "";
		}
	}
	
	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	@Override
	public void landOnField(GameController gameController) {
		gameController.payTax(this);
	}
}




