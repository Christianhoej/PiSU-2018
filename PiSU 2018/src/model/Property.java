package model;

import controller.GameController;
/**@au
 * 
 */
import gui_main.GUI;

/**
 * Contains methods and attributes of the games properties. 
 * Includes methods for price, rent, mortgage, owner and colourSystem.
 * 
 * @author
 *
 */

public class Property extends Fields {

	protected boolean forSale = true;
	protected int price;
	protected int rent;
	protected boolean mortage;
	protected int mortagePrice;
	protected Player owner;
	protected String colourSystem;

	String[] guiMessages = Txt.fileString("GameMessages.txt");

	/**
	 * Constructor
	 * 
	 * @param fieldNumber
	 */
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
		notifyChange();
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
		notifyChange();
	}

	public Player getOwner() {
		return owner;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
		notifyChange();
	}

	public boolean getMortage() {
		return mortage;
	}

	public void setMortagePrice(int mortagePrice) {
		this.mortagePrice = mortagePrice;
		notifyChange();
	}

	public int getMortagePrice() {
		return mortagePrice;
	}

	/**
	 * Makes a player the owner of a property
	 * 
	 * @param player
	 */
	public void setOwner(Player player) {
		owner = player;
		setForSale(false);
		notifyChange();
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getColourSystem() {
		return colourSystem;
	}

	public void setColourSystem(String colourSystem) {
		this.colourSystem = colourSystem;
	}

	@Override
	public String toString() {
		return guiMessages[3] + fieldName;
	}

	@Override
	public void landOnField(GameController gameController) {
	}

}
