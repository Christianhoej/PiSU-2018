package model;

import java.sql.Timestamp;
import java.util.ArrayList;

import board.Chance;
import board.Ejendom;
import board.Fængsel;
import board.Passiv;
import board.Start;
import gui_main.GUI;

public class Game {
	
	private Winner winner = new Winner();
	private Player player;
	private GUI gui;
	private int gameID;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Property> properties = new ArrayList<Property>();
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
		field.setFieldNumber(fields.size());;
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
	
	
	
	
	public void samlFelter(GUI gui){
		fields.addFields(new Start(0, gui)); 
		felter[1] = new Ejendom(1, gui); 
		felter[2] = new Ejendom(2, gui);
		felter[3] = new Chance(3, gui);
		felter[4] = new Ejendom(4, gui);
		felter[5] = new Ejendom(5, gui);
		felter[6] = new Passiv(6, gui); 
		felter[7] = new Ejendom(7, gui);
		felter[8] = new Ejendom(8, gui);
		felter[9] = felter[3];
		felter[10] = new Ejendom(10, gui); 
		felter[11] = new Ejendom(11, gui); 
		felter[12] = new Passiv(12, gui); 
		felter[13] = new Ejendom(13, gui);
		felter[14] = new Ejendom(14, gui);
		felter[15] = felter[3];
		felter[16] = new Ejendom(16, gui);
		felter[17] = new Ejendom(17, gui);
		felter[18] = new Fængsel(18, gui);
		felter[19] = new Ejendom(19, gui);
		felter[20] = new Ejendom(20, gui);
		felter[21] = felter[3]; 
		felter[22] = new Ejendom(22, gui);
		felter[23] = new Ejendom(23, gui);
		
	}
}
