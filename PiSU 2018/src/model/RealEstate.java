package model;

import gui_main.GUI;

public class RealEstate extends Property {

	private GUI gui;
	String[] guiMessages = Txt.file("GameMessages.txt");
	public RealEstate(int position ) {
		super(position);
	}

	@Override
	public void landOnField(Game game) { 
		//If the property is for sale
		if (forSale) {
			//Vil spilleren købe den ellers skal den sættes på auktion 

			String playerChoice = gui.getUserSelection(game.getCurrentPlayer().getName()+ guiMessages[8] + getFieldName() + guiMessages[9] + getPrice(), guiMessages[10], guiMessages[11]);

			if (playerChoice.equals(guiMessages[12])) {
				setForSale(false);
				setOwner(game.getCurrentPlayer());
				game.getCurrentPlayer().getAccount().updateCash(-getPrice());
				game.getCurrentPlayer().addOwnedProperties(getFieldNumber());
			}
			else auction(game);

		}


		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else if (forSale==false) {
			if (owner.equals(player)) {
				gui.showMessage(toString() + guiMessages[13]);
			}
			else if (owner.getInPrison()!= 0) { 
				gui.showMessage(toString() + guiMessages[14]);
			}
			else if (getMortage()) {
				gui.showMessage(toString() + guiMessages[15]);
			}
			else {


				int colourCount = 0;
				int ownerCount = 0;
				for (int i = 0; i<game.getFields().size(); i++) {
					if (game.getFields().get(i).getColourSystem().equals(getColourSystem())){
						colourCount++;
						if (game.getFields().get(i).getOwner().equals(getOwner())){
							ownerCount++;
						}
					}

					if (colourCount == ownerCount) {

						switch(getHouses()){
						case 1: getOwner().getAccount().updateCash(1); //PRIS FOR ET HUS
						game.getCurrentPlayer().getAccount().updateCash(-1); //MINUSPRIS FOR ET HUS
						gui.showMessage(guiMessages[16] + getOwner() +guiMessages[17] + 1);
						break;
						case 2: getOwner().getAccount().updateCash(2); // PRIS FOR TO HUSE
						game.getCurrentPlayer().getAccount().updateCash(-2); // MINUS PRIS FOR TO HUSE
						gui.showMessage(guiMessages[18] + getOwner() +guiMessages[19] + 2);
						break;
						case 3: getOwner().getAccount().updateCash(3);
						game.getCurrentPlayer().getAccount().updateCash(-3);
						gui.showMessage(guiMessages[20] + getOwner() +guiMessages[21] + 3);
						break;
						case 4: getOwner().getAccount().updateCash(4);
						game.getCurrentPlayer().getAccount().updateCash(-4);
						gui.showMessage(guiMessages[22] + getOwner() +guiMessages[23]);
						break;
						case 5: getOwner().getAccount().updateCash(5); // FOR HOTEL
						game.getCurrentPlayer().getAccount().updateCash(-5); // MINUS HOTEL
						gui.showMessage(guiMessages[24] + getOwner() + guiMessages[25] + 5);
						break;
						}

					} else { 
						getOwner().getAccount().updateCash(getRent());
						game.getCurrentPlayer().getAccount().updateCash(-getRent());

					}	
				}
			}

		}

	}
}


