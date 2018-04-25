package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import gui_main.GUI;

public class Game {
	
	private Winner winner = new Winner();
	private Player player;
	private GUI gui;
	private int gameID;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int playerAmount;
	private String date;
	private ArrayList<Fields> fields = new ArrayList<Fields>();
	
	
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
	
	public void setGameDate(String date) {
		this.date = date;
	}
	
	public String getGameDate() {
		return date;
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
	
	private void loadGame() {
		//Kald til databaser om at loade
	}
	public void runGame() {
		setUpGame();
		
		while (winner.testIfWinner(player) == false){
			
		} 
		
	}
	private void setUpGame() {
		//if chose to load game
			loadGame();
		
	}
	
	
	/**
	 * 
	 * @param player
	 * @param playerArray
	 * @param antalSpillere
	 * 
	 * @author Gunn, Christian
	 */
	private void trade(Player player, Player[] playerArray, int antalSpillere) {
		int currentPlayer=-1;
		for (int i=0; i<playerArray.length; i++) {
			if (player.equals(playerArray[i])) {
				currentPlayer=i;
			}
		}
		Player[] otherPlayer = new Player[playerArray.length-1];
		for(int i=0; i<otherPlayer.length; i++) {
			if(otherPlayer[i].equals(playerArray[currentPlayer])) {
				continue;
			}
			else {
				otherPlayer[i] = playerArray[i];
			}
		}
		
		
		int tradingPlayer = -1;
		while(tradingPlayer==-1) {
			String input = gui.getUserString("Indtast navnet på den spiller, du gerne vil handle med: ");
			for(int i=0; i<playerArray.length; i++) {
				if (input.equals(playerArray[i])) {
					if(player.getName().equals(input)) {
						gui.showMessage("Du kan ikke handle med dig selv. Prøv igen");
					}
					else {
						tradingPlayer = i;
					}
				}
			}
			gui.showMessage("Du har ikke valgt et gyldigt navn. Prøv igen");
		}
		
		
		// Ikke færdig
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
