package model;

//Bør klassen være abstract?
public abstract class Fields {
//asd
	protected int fieldNumber; 
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
	
	public abstract void landOnField(Player player, Player[] playerArray);

	public abstract String toString(); 
		
	}


