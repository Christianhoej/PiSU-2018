package model;

import controller.GameController;
/**@author Gunn + Yoss 
 * 
 */
import gui_main.GUI;

public class Tax extends Fields{

	String[] guiMessages = Txt.file("GameMessages.txt");
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

	@Override
	public String toString() {
		return null;

	}
	@Override
	public void landOnField(GameController gameController) {
		Player player = gameController.getGame().getCurrentPlayer();
		if(price == 4000) {
			String playerChoice = gui.getUserSelection(player.getName()+ guiMessages[26] + price + guiMessages[27] , guiMessages[28], guiMessages[29]);
			if(playerChoice.equals(price)) {
				gameController.payMoney(player, price);

			} else {
				gameController.payMoney(player, (int) (-player.getTotalValue()*0.1));
			}
		} else {
			gameController.payMoney(player, price);
		}
	}
}




