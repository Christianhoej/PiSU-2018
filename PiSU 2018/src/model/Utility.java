package model;

import gui_main.GUI;

public class Utility extends Property {

	private GUI gui;
	
	public Utility(int fieldNumber) {
		super(fieldNumber);
	}



	@Override
	public void landOnField(Player player, Player[] playerArray) {
		//If the property is for sale
		if (forSale) {
			//Vil spilleren købe den ellers skal den sættes på auktion 

			String playerChoice = gui.getUserSelection(player.getName()+ " vil du købe " + getFieldName() + " for " + price, "Ja", "Nej");


			if (playerChoice.equals("yes")) {
				setForSale(false);
				setOwner(player);
				player.getAccount().updateCash(-price);
				player.addOwnedProperties(fieldNumber);
			}
			else auction(player, playerArray);
		}
		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else if (forSale==false) {
			if (owner.equals(player)) {
				gui.showMessage(toString() + "Du er selv ejer af dette felt, og skal ikke betale noget.");
			}
			else if (owner.getInPrison()!= 0) { 
				gui.showMessage(toString() + "Ejeren er i fængsel, du slipper denne gang.");
			}
			else if (getMortage()) {
				gui.showMessage(toString() + "Grunden er pantsat, du slipper denne gang.");
			}
			else {
				gui.showMessage("Du er landet på " + owner +"'s ejendom og skal betale " + price);
				getOwner().getAccount().updateCash(price);
				player.getAccount().updateCash(-price);
				//Implementer: Tjek om spiller er broke. 
			}
		}
	}
}

