package model;

import controller.GameController; 
import gui_main.GUI;

public class Utility extends Property {
	private GUI gui;
	String[] guiMessages = Txt.fileString("GameMessages.txt");


	public Utility(int fieldNumber) {
		super(fieldNumber);

	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	/**
	 * Overrides the original landOnfield method. 
	 */
	@Override
	public void landOnField(GameController gameController) {
		gameController.offerToBuyProperty(this);
		notifyChange();
		
//		Player player = gameController.getGame().getCurrentPlayer();
//		//If the utility is for sale
//		if (forSale) {
//			//Asks whether the player wants to buy the utility or not
//			System.out.println();
//			
//			String playerChoice = gui.getUserSelection(player.getName()+ guiMessages[30] + getFieldName() + guiMessages[31] + getPrice(), guiMessages[32], guiMessages[33]);
//			//If the player wants to buy the utility.
//			if (playerChoice.equals(guiMessages[34])) {
//				setForSale(false);
//				setOwner(player);
//				player.getAccount().updateCash(-getPrice());
//				player.addOwnedProperties(getFieldNumber());
//			}
//			//If the player doesn't want to buy the utility, it is up for auction
//			else gameController.auction(player, this);
//		}
//
//		//If the utility is not for sale
//		else if (forSale==false) {
//			//If the player owns the utility himself.
//			if (getOwner().equals(player)) {
//				gui.showMessage(toString() + guiMessages[35]);
//			}
//			//If the owner is in prison
//			else if (getOwner().getInPrison()!= 0) { 
//				gui.showMessage(toString() + guiMessages[36]);
//			}
//			//If the utility is pawned. 
//			else if (getMortage()) {
//				gui.showMessage(toString() + guiMessages[37]);
//			}
//			//If the utility is owned by another player, who is not in prison.
//			else {
//				gameController.ownedUtilitiesSameType(this, player);
//
//			}	
//		}
	}
}




