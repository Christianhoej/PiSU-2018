package controller;

import model.Chance;
import model.Game;
import model.GoToPrison;
import model.NeutralField;
import model.RealEstate;
import model.Tax;
import model.Txt;
import model.Utility;

public class MiniMatador {
	
	static int[][] rent = Txt.fileInt2D("RentPrices.txt");
	static int[][] price = Txt.fileInt2D("Prices.txt");
	
	
	public static Game createGame() {
		Game game = new Game();
		setFields();
		
		
		
		return game;
	}
	
	
	public static void setFields(){
		int i=0;
		NeutralField n = new NeutralField(i++); 
		
		RealEstate r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		r.setFieldName(fieldName);
		
		Chance c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		Tax t = new Tax(i++);
		
		Utility u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		n  = new NeutralField(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		n = new NeutralField(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		GoToPrison p = new GoToPrison(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		u = new Utility(i++);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		c = new Chance(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
		
		t = new Tax(i++);
		
		r = new RealEstate(i++);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortagePrice(mortage[i][0]);
	}
	
	public void addColour(Game game) {
		game.getFields().get(1).setColourSystem("blue");
		game.getFields().get(3).setColourSystem("blue");
		game.getFields().get(5).setColourSystem("ship");
		game.getFields().get(6).setColourSystem("pink");
		game.getFields().get(8).setColourSystem("pink");
		game.getFields().get(9).setColourSystem("pink");
		game.getFields().get(11).setColourSystem("green");
		game.getFields().get(12).setColourSystem("darkgreen");
		game.getFields().get(13).setColourSystem("green");
		game.getFields().get(14).setColourSystem("green");
		game.getFields().get(15).setColourSystem("ship");
		game.getFields().get(16).setColourSystem("grey");
		game.getFields().get(18).setColourSystem("grey");
		game.getFields().get(19).setColourSystem("grey");
		game.getFields().get(21).setColourSystem("red");
		game.getFields().get(23).setColourSystem("red");
		game.getFields().get(24).setColourSystem("red");
		game.getFields().get(25).setColourSystem("ship");
		game.getFields().get(26).setColourSystem("white");
		game.getFields().get(27).setColourSystem("white");
		game.getFields().get(28).setColourSystem("darkgreen");
		game.getFields().get(29).setColourSystem("white");
		game.getFields().get(31).setColourSystem("yellow");
		game.getFields().get(32).setColourSystem("yellow");
		game.getFields().get(34).setColourSystem("yellow");
		game.getFields().get(35).setColourSystem("ship");
		game.getFields().get(37).setColourSystem("purple");
		game.getFields().get(39).setColourSystem("purple");
	}
	
	
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		GameController gameCon = new GameController(game);
		
		gameCon.createPlayers();
		
	}

}
