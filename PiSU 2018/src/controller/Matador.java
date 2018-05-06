package controller;

import model.Chance; 
import model.Game;
import model.GoToPrison;
import model.NeutralField;
import model.RealEstate;
import model.Tax;
import model.Txt;
import model.Utility;
import model.Property;

public class Matador {
	
	static int[][] rent = Txt.fileInt2D("RentPrices.txt");
	static int[][] price = Txt.fileInt2D("Prices.txt");
	static int[][] mortage = Txt.fileInt2D("MortagePrices.txt");
	static String[][] fieldName = Txt.fileString2D("fieldNames.txt");
	static int [][]buildingPrices = Txt.fileInt2D("BuildingPrices.txt");
	
	public static Game createGame() {
		Game game = new Game();
		setFields(game);
		addColor(game);
		
		
		return game;
	}
	
	
	public static void setFields(Game game){
		int i=0;
		NeutralField n = new NeutralField(i); 
		n.setFieldName(fieldName[i][0]);
		game.addField(n);
		
		RealEstate r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		Chance c = new Chance(++i);
		c.setFieldName(fieldName[i][1]);
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);

		Tax t = new Tax(++i);
		t.setFieldName(fieldName[i][0]);
		game.addField(t);
		
		
		Utility u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		i++;
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		n  = new NeutralField(++i);
		n.setFieldName(fieldName[i][1]);
		game.addField(n);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		
		u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		i++;
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		n = new NeutralField(++i);
		n.setFieldName(fieldName[i][1]);
		game.addField(n);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		i++;
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		GoToPrison p = new GoToPrison(++i);
		p.setFieldName(fieldName[i][1]);
		game.addField(p);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		i++;
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		u = new Utility(++i);
		u.setPrice(price[i][0]);
		u.setRent(rent[i][0]);
		u.setMortgagePrice(mortage[i][0]);
		u.setFieldName(fieldName[i][1]);
		game.addField(u);
		
		i++;
		game.addField(c);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
		
		t = new Tax(++i);
		t.setFieldName(fieldName[i][0]);
		game.addField(t);
		
		r = new RealEstate(++i);
		r.setPrice(price[i][0]);
		r.setRent(rent[i][0]);
		r.setMortgagePrice(mortage[i][0]);
		r.setFieldName(fieldName[i][0]);
		r.setBuildingPrice(buildingPrices[i][0]);
		game.addField(r);
	}
	
	public static void addColor(Game game) {
		((Property)game.getFields().get(1)).setColourSystem("blue");
		((Property)game.getFields().get(3)).setColourSystem("blue");
		((Property)game.getFields().get(5)).setColourSystem("ship");
		((Property)game.getFields().get(6)).setColourSystem("pink");
		((Property)game.getFields().get(8)).setColourSystem("pink");
		((Property)game.getFields().get(9)).setColourSystem("pink");
		((Property)game.getFields().get(11)).setColourSystem("green");
		((Property)game.getFields().get(12)).setColourSystem("darkgreen");
		((Property)game.getFields().get(13)).setColourSystem("green");
		((Property)game.getFields().get(14)).setColourSystem("green");
		((Property)game.getFields().get(15)).setColourSystem("ship");
		((Property)game.getFields().get(16)).setColourSystem("grey");
		((Property)game.getFields().get(18)).setColourSystem("grey");
		((Property)game.getFields().get(19)).setColourSystem("grey");
		((Property)game.getFields().get(21)).setColourSystem("red");
		((Property)game.getFields().get(23)).setColourSystem("red");
		((Property)game.getFields().get(24)).setColourSystem("red");
		((Property)game.getFields().get(25)).setColourSystem("ship");
		((Property)game.getFields().get(26)).setColourSystem("white");
		((Property)game.getFields().get(27)).setColourSystem("white");
		((Property)game.getFields().get(28)).setColourSystem("darkgreen");
		((Property)game.getFields().get(29)).setColourSystem("white");
		((Property)game.getFields().get(31)).setColourSystem("yellow");
		((Property)game.getFields().get(32)).setColourSystem("yellow");
		((Property)game.getFields().get(34)).setColourSystem("yellow");
		((Property)game.getFields().get(35)).setColourSystem("ship");
		((Property)game.getFields().get(37)).setColourSystem("purple");
		((Property)game.getFields().get(39)).setColourSystem("purple");
	}
	
	
	
	public static void main(String[] args) {
		Game game = createGame();
		GameController controller = new GameController(game);
		
		controller.runGame();
		
	}

}
