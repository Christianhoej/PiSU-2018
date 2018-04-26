package model;

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
	public void landOnField(Game game) {
		if(price == 4000) {
			String playerChoice = gui.getUserSelection(game.getCurrentPlayer().getName()+ guiMessages[26] + price + guiMessages[27] , guiMessages[28], guiMessages[29]);
			if(playerChoice.equals(price)) {
				game.getCurrentPlayer().getAccount().updateCash(-price);

			} else {
				game.getCurrentPlayer().getAccount().updateCash((int) (-game.getCurrentPlayer().getTotalValue()*0.1)); //trækker 10% fra account
			}
		} else {
			game.getCurrentPlayer().getAccount().updateCash(-price);
		
		}
	}
}




