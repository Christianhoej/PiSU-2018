package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GameController;
import controller.MiniMatador;
import model.Account;
import model.Fields;
import model.Game;
import model.Player;
import model.Tax;

public class GameControllerTest {
	GameController gameController;
	GameController payMoneyTest;
	GameController moveToFieldTest;
	GameController taxTest;
	GameController birthdayTest;
	Account updateCashTest;
	Game game;
	Tax tax;
	
	Player p1;

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
		int actual=p1.getAccount().getCash();
		int expected=30400;
		assertEquals(actual, expected);
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
	 */
	@Test
	public void testMoveToField(){
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
	 */
	@Test
	public void testPayMoney() {
		payMoneyTest = new GameController(game);
		p1 = new Player();
		payMoneyTest.payMoney(p1, 1000);
		int actual=p1.getAccount().getCash();
		int expected=29000;
		assertEquals(actual, expected);
	}

}
