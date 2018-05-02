package model;


import java.util.ArrayList;

public class Game {

	private int gameID;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int playerAmount;
	private String date;
	private ArrayList<Fields> fields = new ArrayList<Fields>();
	private Player currentPlayer;
	private Dice[] dices;

	public void setGameID(int gameID) {
		this.gameID=gameID;
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

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setGameDate(String date) {
		this.date = date;
	}

	public String getGameDate() {
		return date;
	}

	public Dice[] getDice() {
		return dices;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = new ArrayList<Player>(players);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void setFields(ArrayList<Fields> fields) {
		this.fields = fields;
	}

	public ArrayList<Fields> getFields(){
		return fields;
	}

	public void addField(Fields field) {
		fields.add(field);
	}








	public void setFields(){
		addField(new NeutralField(0)); 
		addField(new RealEstate(1));
		addField(new Chance(2));
		addField(new RealEstate(3));
		addField(new Tax(4));
		addField(new Utility(5));
		addField(new RealEstate(6));
		addField(new Chance(7));
		addField(new RealEstate(8));
		addField(new RealEstate(9));
		addField(new NeutralField(10));
		addField(new RealEstate(11));
		addField(new Utility(12));
		addField(new RealEstate(13));
		addField(new RealEstate(14));
		addField(new Utility(15));
		addField(new RealEstate(16));
		addField(new Chance(17));
		addField(new RealEstate(18));
		addField(new RealEstate(19));
		addField(new NeutralField(20));
		addField(new RealEstate(21));
		addField(new Chance(22));
		addField(new RealEstate(23));
		addField(new RealEstate(24));
		addField(new Utility(25));
		addField(new RealEstate(26));
		addField(new RealEstate(27));
		addField(new Utility(28));
		addField(new RealEstate(29));
		addField(new GoToPrison(30));
		addField(new RealEstate(31));
		addField(new RealEstate(32));
		addField(new Chance(33));
		addField(new RealEstate(34));
		addField(new Utility(35));
		addField(new Chance(36));
		addField(new RealEstate(37));		
		addField(new Tax(38));
		addField(new RealEstate(39));
	}

	



}
