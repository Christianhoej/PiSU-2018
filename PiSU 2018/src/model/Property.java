package model;

import controller.GameController;
/**@au
 * 
 */
import gui_main.GUI;

public class Property extends Fields {

	protected boolean forSale = true;
	protected int price;
	protected int rent;
	protected boolean mortage;
	protected int mortagePrice;
	protected Player owner;
	private GUI gui;
	protected int houses;
	String[] guiMessages = Txt.file("GameMessages.txt");


	public Property(int fieldNumber) {
		super(fieldNumber);
	}

	public int getFieldNumber() {
		return fieldNumber;
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

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public boolean getMortage() {
		return mortage;
	}

	public void setMortagePrice(int mortagePrice) {
		this.mortagePrice = mortagePrice;
	}

	public int getMortagePrice () {
		return mortagePrice;
	}

	public void setOwner(Player player) {
		this.owner = player;
		setForSale(false);
	}
	public String getFieldName() {
		return fieldName;
	}
	
	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	public int getHouses() {
		return houses;
	}

	@Override
	public String toString(){
		return guiMessages[3] + fieldName;
	}
	
	@Override
	public void landOnField(Game game, Player player) { 
	}

}

