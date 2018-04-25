package model;

import gui_main.GUI;
import model.Dice;

public class Utility extends Property {
  private int diceValue;
	private GUI gui;

	
	public Utility(int fieldNumber) {
		super(fieldNumber);

	

	}
	
	public int calRent() {
		//Implementer beregningen af lejen ved eje af flere færger
	}

	/**
	 * Overrides the original landOnfield method. 
	 */
	@Override
	public void landOnField(Player player, Player[] playerArray) {
		//If the utility is for sale
		if (forSale) {
			//Asks whether the player wants to buy the utility or not
			String playerChoice = gui.getUserSelection(player.getName()+ " vil du købe " + getFieldName() + " for " + price, "Ja", "Nej");
			//If the player wants to buy the utility.
			if (playerChoice.equals("yes")) {
				setForSale(false);
				setOwner(player);
				player.getAccount().updateCash(-price);
				player.addOwnedProperties(fieldNumber);
			}
			//If the player doesn't want to buy the utility, it is up for auction
			else auction(player, playerArray);
		}

		//If the utility is not for sale
		else if (forSale==false) {
			//If the player owns the utility himself.
			if (owner.equals(player)) {
				gui.showMessage(toString() + "Du er selv ejer af dette felt, og skal ikke betale noget.");
			}
			//If the owner is in prison
			else if (owner.getInPrison()!= 0) { 
				gui.showMessage(toString() + "Ejeren er i fængsel, du slipper denne gang.");
			}
			//If the utility is pawned. 
			else if (getMortage()) {
				gui.showMessage(toString() + "Grunden er pantsat, du slipper denne gang.");
			}
			//If the utility is owned by another player, who is not in prison.
			else {
				gui.showMessage("Du er landet på " + owner +"'s ejendom og skal betale " + rent);
				getOwner().getAccount().updateCash(rent);
				player.getAccount().updateCash(-rent);
				//Implementer: Tjek om spiller er broke. 				
			}
		}
	}
}

