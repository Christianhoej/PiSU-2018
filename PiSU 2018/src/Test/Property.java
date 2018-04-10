package Test;

import Test.Player; 
import Test.Account;
import board.Gameboard;
import board.TestBoard;
import gui_codebehind.GUI_BoardController;
import gui_main.GUI;

public class Property extends Fields {

	protected boolean forSale=true;
	protected int price;
	protected int rent;
	protected Player owner;
	private GUI gui;


	public Property(int fieldNumber, String fieldName, Player owner) {
		super(fieldNumber, fieldName);
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player player) {
		this.owner = player;
	}
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public String toString(){
		return "Du har landet på " + fieldName;
	}

	public void auction() {
		//Gui besked
		//gui.showmessage(getFieldName() + "er sat på auktion!");
		//Skal implementeres. 






	}

	@Override
	public void landOnField(Player player) {
		//If the property is for sale
		if (forSale) {
			//Vil spilleren købe den ellers skal den sættes på auktion 
			String playerChoice = gui.getUserSelection(player.getName()+ " vil du købe " + getFieldName() + " for " + price, "Ja", "Nej");


			if (playerChoice.equals("yes")) {
				setForSale(false);
				setOwner(player);
				player.subtractCash(price);
				player.addAssets(price);
				player.addOwnedProperties(fieldNumber);
			}
			else {
				
				
			}
			//Færdiggøres
		}
		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Købe den, 
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else if (forSale==false) {
			if (owner.isPrison()==true) { 
				//GUI.showMessage(toString() + "Ejeren er i fængsel, du slipper denne gang.");
			}
			else {
				gui.showMessage("Du er landet på " + owner +"'s ejendom og skal betale " + price);
				getOwner().getAccount().addCash(price);
				player.getAccount().subtractCash(price);
				//Implementer: Tjek om spiller er broke. 
			}

		}
	}
}
