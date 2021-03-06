package model;

import java.awt.Color;
import java.util.ArrayList;

import designpatterns.Subject;

/**
 * Contains methods and attributtes which is needed in the game, for controlling
 * a players properties, cash, and whether the player is in prison or is broke. 
 * 
 * @author Christian
 *
 */
public class Player extends Subject {

	private String name;
	private Account account;
	protected int position = 0;
	private boolean broke;
	private int inPrison;

	private ArrayList<Property> ownedProperties = new ArrayList<Property>();

	private int playerID;
	private Color colour;

	/**
	 * Sets the players accont 
	 * @param amount - the amount which the account is set with. 
	 */
	public void setAccount(int amount) {
		this.account = new Account(amount);
		notifyChange();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		notifyChange();
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setColour(Color colour) {
		this.colour = colour;
		notifyChange();
	}

	public Color getColour() {
		return colour;
	}

	public Account getAccount() {
		return account;
	}

	/**
	 * Sets the players position
	 * @param position - the new position
	 */
	public void setPosition(int position) {
		this.position = position;
		notifyChange();
	}

	public int getPosition() {
		return position;
	}

	public boolean isBroke() {
		return broke;
	}

	public void setBroke(boolean broke) {
		this.broke = broke;
		notifyChange();
	}

	public int getInPrison() {
		return inPrison;
	}

	/**
	 * 
	 * @param inPrison
	 *            - value, that determines if and how long a player has been in
	 *            prison. 0 - player is not in prison 1 - player is in prison 2 -
	 *            player is in prison and has been there for one turn 3 - player is
	 *            in prison and has been there for two turns 4 - player is in prison
	 *            and has been there for three turns
	 * 
	 */
	public void setInPrison(int inPrison) {
		this.inPrison = inPrison;
		notifyChange();
	}

	/**
	 * Adds a property to the list of properties, owned by a player.
	 * 
	 * @param property
	 */
	public void addOwnedProperties(Property property) {
		ownedProperties.add(property);
		notifyChange();
	}

	/**
	 * Removes a property from the list of properties, owned by a player.
	 * 
	 * @param property - the property which is removed 
	 */
	public void removeOwnedProperties(Property property) {
		ownedProperties.remove(property);
		notifyChange();
	}
	
	/**
	 * Removes all properties from the player. 
	 */
	public void removeAllOwnedProperties() {
		ownedProperties.removeAll(ownedProperties);
	}

	public ArrayList<Property> getOwnedProperties() {
		return ownedProperties;
	}

}