package model;

import java.awt.Color;

import designpatterns.Subject;

public class Player extends Subject {

	private String name;
	private Account account;
	protected int position=0;
	private boolean broke;
	private int inPrison;
	private int[] ownedProperties = new int[40];
	private int[] ownedHouses = new int[40];
	private int playerID;
	private Color colour;
	private boolean current;

	public Player() {
		this.account = new Account(30000);

	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	public Color getColour() {
		return colour;
	}

	// Smartere at kalde account direkte, istedet for at lave "hjælpe metoder" til at gøre det samme
	// --> Undgå redundens
	public Account getAccount() {
		return account;
	}

	public void setPosition(int position) {
		if (this.position > position && inPrison <1)
	    account.updateCash(4000);
		
		this.position = position;
		
	}

	public int getPosition() {
		return position;
	}

	public boolean isBroke() {
		return broke;
	}

	public void setBroke(boolean broke) {
		this.broke = broke;
	}

	public int getTotalValue() {
		return account.getCash()+account.getAssetValue();
	}

	public int getInPrison() {
		return inPrison;
	}

	/**
	 * 
	 * @param inPrison - 	value, that determines if and how long a player has been in prison.
	 * 						0 - player is not in prison
	 * 						1 - player is in prison
	 * 						2 - player is in prison and has been there for one turn
	 * 						3 - player is in prison and has been there for two turns
	 * 						4 - player is in prison and has been there for three turns
	 * 
	 */
	public void setInPrison(int inPrison) {
		this.inPrison=inPrison;
	}

	public void addOwnedProperties(int fieldNumber) {
		ownedProperties[fieldNumber] = 1;
	}

	public void removeOwnedProperties(int fieldNumber) {
		ownedProperties[fieldNumber] = 0;
	}

	public int[] getOwnedProperties() {
		return ownedProperties;
	}
	
	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	public boolean getCurrent() {
		return current;
	}
	
	public void addHouses(int fieldNumber){
		ownedHouses[fieldNumber] += 1;
	}
	
	public void removeHouses(int fieldNumber) {
		ownedHouses[fieldNumber] -= 1;
	}
	
	public void pawnField(int fieldNumber) {
		ownedHouses[fieldNumber] = -1;
	}
	
	public int[] getOwnedHouses() {
		return ownedHouses;
	}
	
}

//Delvist taget fra CDIO3 - not done - Yoss
