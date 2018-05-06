package model;

import java.util.ArrayList;
import designpatterns.Subject;

/**
 * This class collects all the needed information from the model package. The
 * class collects information in arrayLists, used in the GameController.
 * 
 * @author
 *
 */
public class Game extends Subject {

	private int gameID;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int playerAmount;
	private String date;
	private ArrayList<Fields> fields = new ArrayList<Fields>();
	private Player currentPlayer;
	private Dice dice = new Dice();

	public void setGameID(int gameID) {
		this.gameID = gameID;
		System.out.println(gameID);
	}

	public int getGameID() {
		return gameID;
	}

	public void setPlayerAmount(int playerAmount) {
		this.playerAmount = playerAmount;
	}

	public int getPlayerAmount() {
		return playerAmount;
	}

	/**
	 * sets the current player
	 * If the set player is not in the game, an exception os thrown. 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		if (currentPlayer != null /* && players.contains(currentPlayer) */) {
			this.currentPlayer = currentPlayer;
		} else {
			throw new IllegalArgumentException("Spilleren er ikke i spillet");
		}
		notifyChange();
	}

	public Player getCurrentPlayer() {
		if (currentPlayer == null) {
			currentPlayer = players.get(0);
		}
		return currentPlayer;
	}

	public void setGameDate(String date) {
		this.date = date;
	}

	public String getGameDate() {
		return date;
	}

	public Dice getDice() {
		return dice;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * sets the list of players, in a ArrayList.
	 * 
	 * @param players - the players added to the arraylist.
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = new ArrayList<Player>(players);
		notifyChange();
	}

	/**
	 * Adds player to ArrayList<Player> The player is also added to the game.
	 * 
	 * @param player - the player being added
	 *            
	 */
	public void addPlayer(Player player) {
		players.add(player);
		notifyChange();
	}

	/**
	 * Sets the fields in a ArrayList
	 * 
	 * @param fields - the ArrayList containing the fields. 
	 */
	public void setFields(ArrayList<Fields> fields) {
		this.fields = fields;
		notifyChange();
	}

	public ArrayList<Fields> getFields() {
		return fields;
	}

	/**
	 * Adds a field to the game
	 * 
	 * @param field - the field added to the game. 
	 */
	public void addField(Fields field) {
		fields.add(field);
		notifyChange();
	}
}
