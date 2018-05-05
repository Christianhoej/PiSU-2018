package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GameController;
import controller.MiniMatador;
import model.Account;
import model.Fields;
import model.Game;
import model.Player;
import model.Property;
import model.Tax;
import model.Utility;

public class GameControllerTest {
	GameController gameController;
	GameController payMoneyTest;
	GameController moveToFieldTest;
	GameController taxTest;
	GameController birthdayTest;
	GameController receiveMoneyTest;
	GameController realEstateTest;
	Game game;
	Tax tax;
//	Utility utility;
	Property property;
	Player p1;
	
	
	/**
	 * Tests the method paySameTypeRealEstate
	 * tests when a player lands on a real estate where all properties in the same colour, 
	 * is owned by the same player, but with no houses build on it. 
	 */
	@Test
	public void realEstateTest() {
		game = MiniMatador.createGame();
		realEstateTest = new GameController(game);
		p1 = new Player();
		Player p2 = new Player();
		property = (Property)game.getFields().get(1);
		property.setOwner(p2);
		realEstateTest.paySameTypeRealEstate(property, p1);
		//Tests that p2 receives money
		int actual=p2.getAccount().getCash();
		int expected=30100;
		assertEquals(actual, expected);
		//Tests that p1 pays money.
		int actual2=p1.getAccount().getCash();
		int expected2=29900;
		assertEquals(actual2, expected2);	
	}
	
	
	/**
	 * Tests the receiveMoney method.
	 * Tests whether the player receives the right amount of money.
	 * The method is used in multiple other methods. 
	 */
	@Test
	public void receiveMoneyTest() {
		receiveMoneyTest = new GameController(game);
		p1 = new Player();
		int amount = 10000;
		receiveMoneyTest.receiveMoney(p1, amount);
		int actual=p1.getAccount().getCash();
		int expected=40000;
		assertEquals(actual, expected);
	}
	
	/**
	 * Tests the method "playerBirthday".
	 * When a player draws the birthday chancecard, he must receive 200kr from all the other players.
	 */
	@Test
	public void birthdayTest(){
		game = MiniMatador.createGame();
		birthdayTest = new GameController(game);
		p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		game.addPlayer(p1);
		game.addPlayer(p2);
		game.addPlayer(p3);
		birthdayTest.playerBirthday(p1);
		//Tests p1's cash
		int actual=p1.getAccount().getCash();
		int expected=30400;
		assertEquals(actual, expected);
		//Tests p2's cash
		int actual2=p2.getAccount().getCash();
		int expected2=29800;
		assertEquals(actual2, expected2);
	}
	
//	@Test
//	public void taxTest(){
//		taxTest = new GameController(game);
//		game=MiniMatador.createGame();
////		fields = MiniMatador.setFields(game);
//		p1 = new Player();
//		game.addPlayer(p1);
//		tax = new Tax(4);
////		int price = 2000;
////		(tax)(game.getFields().get(38));
//		int actual=p1.getAccount().getCash();
//		int expected =26000;
//		assertEquals(actual, expected);
//		//Virker ikke 	
//	}
	
	/**
	 * Tests the moveToField method.
	 * moveToField must move the players current location to the new given position.
	 * This new position can come from a Chancecard in the game.
	 */
	@Test
	public void moveToFieldTest(){
		game = MiniMatador.createGame();
		moveToFieldTest = new GameController(game);
		p1 = new Player();
		game.addPlayer(p1);
		p1.setPosition(4);
		int move=10;
		moveToFieldTest.moveToField(p1,move);
		int actual = p1.getPosition();
		int expected=10;
		assertEquals(actual,expected);	
	}
	
	/**
	 * Tests whether the player pay the right amount of money.
	 * The method is used in several other methods.
	 */
	@Test
	public void payMoneytest() {
		payMoneyTest = new GameController(game);
		p1 = new Player();
		payMoneyTest.payMoney(p1, 1000);
		int actual=p1.getAccount().getCash();
		int expected=29000;
		assertEquals(actual, expected);
	}

}
