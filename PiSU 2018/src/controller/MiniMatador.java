package controller;

import board.Gameboard;
import gui_main.GUI;
import model.Chance;
import model.Fields;
import model.Game;
import model.GoToPrison;
import model.NeutralField;
import model.Property;
import model.RealEstate;
import model.Tax;
import model.Txt;
import model.Utility;

public class MiniMatador {
	
	int[][] rent = Txt.file2D("RentPrices.txt");
	public static Game createGame() {
		
		
	}
	
	
	public void setFields(){
		int i=0;
		NeutralField n = new NeutralField(i++); 
		
		RealEstate r = new RealEstate(i++);
		
		r.setRent(rent[i][0]);
		Chance c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		Tax t = new Tax(i++);
		
		Utility u = new Utility(i++);
		u.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		n  = new NeutralField(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		u = new Utility(i++);
		u.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		u = new Utility(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		n = new NeutralField(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		u = new Utility(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		u = new Utility(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		GoToPrison p = new GoToPrison(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		u = new Utility(i++);
		r.setRent(rent[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
		
		t = new Tax(i++);
		
		r = new RealEstate(i++);
		r.setRent(rent[i][0]);
	}
	
	
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		GameController gameCon = new GameController(game);
		
		gameCon.createPlayers();
		
	}

}
