package Test;

import gui_main.GUI;

public class Tax extends Fields{

	private GUI gui;
	private int price;
	public Tax(int fieldNumber, String fieldName) {
		super(fieldNumber, fieldName);
		if (fieldNumber == 4) {
			price = 4000;
		}
		else {
			price = 2000;
		}
	}

	@Override
	public String toString() {
		return null;

	}
	@Override
	public void landOnField(Player player) {
		if(price == 4000) {
			String playerChoice = gui.getUserSelection(player.getName()+ " vil du helst betale " + price + " eller betale 10% af dine samlede værdier? " , "4000", "10%");
			if(playerChoice.equals(price)) {
				player.getAccount().updateCash(-price);

			} else {
				player.getAccount().updateCash((int) (-player.getTotalValue()*0.1)); //trækker 10% fra account
			}
		} else {
			player.getAccount().updateCash(-price);
		
		}
	}
}




