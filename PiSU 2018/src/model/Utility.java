package model;

import gui_main.GUI;
import model.Dice;

public class Utility extends Property {
	private GUI gui;
	String[] guiMessages = Txt.file("GameMessages.txt");


	public Utility(int fieldNumber) {
		super(fieldNumber);

	}

	/**
	 * Overrides the original landOnfield method. 
	 */
	@Override
	public void landOnField(Game game) {
		//If the utility is for sale
		if (forSale) {
			//Asks whether the player wants to buy the utility or not
			String playerChoice = gui.getUserSelection(game.getCurrentPlayer().getName()+ guiMessages[30] + getFieldName() + guiMessages[31] + getPrice(), guiMessages[32], guiMessages[33]);
			//If the player wants to buy the utility.
			if (playerChoice.equals(guiMessages[34])) {
				setForSale(false);
				setOwner(game.getCurrentPlayer());
				game.getCurrentPlayer().getAccount().updateCash(-getPrice());
				game.getCurrentPlayer().addOwnedProperties(getFieldNumber());
			}
			//If the player doesn't want to buy the utility, it is up for auction
			else auction(game);
		}

		//If the utility is not for sale
		else if (forSale==false) {
			//If the player owns the utility himself.
			if (getOwner().equals(game.getCurrentPlayer())) {
				gui.showMessage(toString() + guiMessages[35]);
			}
			//If the owner is in prison
			else if (getOwner().getInPrison()!= 0) { 
				gui.showMessage(toString() + guiMessages[36]);
			}
			//If the utility is pawned. 
			else if (getMortage()) {
				gui.showMessage(toString() + guiMessages[37]);
			}
			//If the utility is owned by another player, who is not in prison.
			else {

				int colourCount = 0;
				int ownerCount = 0;
				for (int i = 0; i<game.getFields().size(); i++) {
					if (game.getFields().get(i).getColourSystem().equals(getColourSystem())) {
						colourCount++;
						if (game.getFields().get(i).getOwner().equals(getOwner())){
							ownerCount++;
						}
					}

					if (colourCount == 2) {
						switch(ownerCount) {
						case 1: getOwner().getAccount().updateCash(getRent()*game.getDice().getFaceValue()); // PRIS FOR EN TYPE * øjenværdi --> HUSK DER SKAL ÆNDRES SÅ DER TÆLLES FOR TO TERNINGER
						game.getCurrentPlayer().getAccount().updateCash(-getRent()*game.getDice().getFaceValue()); //MINUSPRIS FOR EN TYPE
						gui.showMessage(guiMessages[38] + getOwner() +guiMessages[39] + 1);
						break;
						case 2: getOwner().getAccount().updateCash(getRent()*game.getDice().getFaceValue()); // PRIS FOR TO TYPER
						game.getCurrentPlayer().getAccount().updateCash(-getRent()*game.getDice().getFaceValue()); // MINUS PRIS FOR TO TYPER
						gui.showMessage(guiMessages[40] + getOwner() + guiMessages[41] + 2);
						break;
						}

					} else {


						switch(ownerCount){
						case 1: getOwner().getAccount().updateCash(1); // PRIS FOR EN TYPE
						game.getCurrentPlayer().getAccount().updateCash(-1); //MINUSPRIS FOR EN TYPE
						gui.showMessage(guiMessages[42] + getOwner() + guiMessages[43] + 1);
						break;
						case 2: getOwner().getAccount().updateCash(2); // PRIS FOR TO TYPER
						game.getCurrentPlayer().getAccount().updateCash(-2); // MINUS PRIS FOR TO TYPER
						gui.showMessage(guiMessages[44] + getOwner() + guiMessages[45] + 2);
						break;
						case 3: getOwner().getAccount().updateCash(3);
						game.getCurrentPlayer().getAccount().updateCash(-3);
						gui.showMessage(guiMessages[46] + getOwner() + guiMessages[47] + 3);
						break;
						case 4: getOwner().getAccount().updateCash(4);
						game.getCurrentPlayer().getAccount().updateCash(-4);
						gui.showMessage(guiMessages[48] + getOwner() +guiMessages[49] + 4);
						break;
						} // skal opdateret fra txt filen af rederileje ^^VIGTIGT
					}
				}	
			}
		}
	}
}



