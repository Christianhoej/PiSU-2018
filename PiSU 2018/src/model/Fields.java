package model;

import java.util.ArrayList;

import controller.GameController;
import designpatterns.Subject;

public abstract class Fields extends Subject {

	protected int fieldNumber; 
	protected String colourSystem;
	protected String fieldName;
	protected Player player;
	protected boolean mortage;
	protected int houses;
	protected int buildingPrice;

	public Fields(int fieldNumber) {
		this.fieldNumber = fieldNumber;
		notifyChange();
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	protected void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
		notifyChange();
	}
	
	public String getColourSystem() {
		return colourSystem;
	}

	public void setColourSystem(String colourSystem) {
		this.colourSystem = colourSystem;
	}
	
	public String getFieldName() {
		return fieldName;

	}
	
	public void setOwner(Player player) {
		this.player = player;
		player.addOwnedProperties(this);
		notifyChange();
	}
		
	public Player getOwner() {
		return player;
	}
	
	public void remowOwner(){
		this.player=null;
		notifyChange();
	}
	
	public void setMortage(boolean mortage) {
		this.mortage = mortage;
		notifyChange();
	}

	public boolean getMortage() {
		return mortage;
	}
	
	public void setHouses(int houses) {
		this.houses = houses;
		notifyChange();
	}
	
	public void sellHouse() {
		this.houses--;
		notifyChange();
	}
	
	public void buyHouse() {
		this.houses++;
		notifyChange();
	}
	
	public int getHouses() {
		return houses;
	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		notifyChange();
	}
	public void setBuildingPrice(int buildingPrice) {
		this.buildingPrice = buildingPrice;
	}
	public int getBuildingPrice() {
		return buildingPrice;
	}

	public abstract String toString();

	public void landOnField(GameController gameController) {
		// TODO Auto-generated method stub
		
	} 
		
	}


