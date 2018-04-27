package model;

import controller.GameController;

public abstract class Fields {

	protected int fieldNumber; 
	protected String colourSystem;
	protected String fieldName;
	protected Player player;
	protected boolean mortage;
	protected int houses;

	public Fields(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	protected void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}
	
	public String getColourSystem() {
		return colourSystem;
	}

	protected void setColourSystem(String colourSystem) {
		this.colourSystem = colourSystem;
	}
	
	public String getFieldName() {
		return fieldName;

	}
	
	public void setOwner(Player player) {
		this.player = player;
		player.addOwnedProperties(fieldNumber);
	}
	
	public Player getOwner() {
		return player;
	}
	
	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public boolean getMortage() {
		return mortage;
	}
	
	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	public int getHouses() {
		return houses;
	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public abstract String toString();


	public abstract void landOnField(Game game); 
		
	}


