package model;

import controller.GameController; 

public class RealEstate extends Property {
	protected int houses;
	protected Player player;
	protected int buildingPrice;
	String[] guiMessages = Txt.fileString("GameMessages.txt");

	public RealEstate(int fieldNumber ) {
		super(fieldNumber);
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		notifyChange();
	}
	
	
	@Override
	public void landOnField(GameController gameController) { 
		gameController.offerToBuyProperty(this);
		notifyChange();
//		Player player = gameController.getGame().getCurrentPlayer();
//		//If the property is for sale
//		if (forSale) {
//			//Vil spilleren købe den ellers skal den sættes på auktion 
//
//			String playerChoice = gui.getUserSelection(player.getName()+ guiMessages[8] + getFieldName() + guiMessages[9] + getPrice(), guiMessages[10], guiMessages[11]);
//
//			if (playerChoice.equals(guiMessages[12])) {
//				setForSale(false);
//				setOwner(player);
//				gameController.payMoney(player, getPrice());
//				gameController.addOwnedProperties(player, fieldNumber);
//				notifyChange();
//			}
//			else gameController.auction(player, this);
//
//		}
//
//
//		//Hvis grunden ikke er til salg
//		//Spilleren kan, når han lander på grunden:
//		//Betale leje 
//		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
//		//Sætte ejendommen på auktion. 
//		else if (forSale==false) {
//			if (owner.equals(player)) {
//				gui.showMessage(toString() + guiMessages[13]);
//			}
//			else if (owner.getInPrison()!= 0) { 
//				gui.showMessage(toString() + guiMessages[14]);
//			}
//			else if (getMortage()) {
//				gui.showMessage(toString() + guiMessages[15]);
//			}
//			else {
//				gameController.ownedRealEstateSameColour(this, player);
//			}
//		}
	}
	public void setHouses(int houses) {
		this.houses = houses;
		notifyChange();
	}
	
	public int getHouses() {
		return houses;
	}
	public void sellHouse() {
		this.houses--;
		notifyChange();
	}
	public void setBuildingPrice(int buildingPrice) {
		this.buildingPrice = buildingPrice;
	}
	public int getBuildingPrice() {
		return buildingPrice;
	}
}