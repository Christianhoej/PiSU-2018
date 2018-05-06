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
import model.RealEstate;
import model.Tax;
import model.Utility;
/**
 * 
 * @author Christian & Oliver
 *
 */
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
	RealEstate realEstate;
	Player p1;
	
	
//	/**
//	 * Tests the method paySameTypeRealEstate
//	 * tests when a player lands on a real estate where all properties in the same colour, 
//	 * is owned by the same player, but with no houses build on it. 
//	 */
//	@Test
//	public void realEstateTest() {
//		game = MiniMatador.createGame();
//		realEstateTest = new GameController(game);
//		Player p1 = new Player();
//		Player p2 = new Player();
//		realEstate = (RealEstate)game.getFields().get(1);
//		realEstate.setOwner(p2);
//		realEstateTest.paySameTypeRealEstate(realEstate, p1);
//		//Tests that p2 receives money
//		int actual=p2.getAccount().getCash();
//		int expected=30100;
//		assertEquals(actual, expected);
//		//Tests that p1 pays money.
//		int actual2=p1.getAccount().getCash();
//		int expected2=29900;
//		assertEquals(actual2, expected2);	
//	}
	
	
	/**
	 * Tests the receiveMoney method.
	 * Tests whether the player receives the right amount of money.
	 * The method is used in multiple other methods. 
	 */
	@Test
	public void receiveMoneyTest() {
		receiveMoneyTest = new GameController(game);
		p1 = new Player();
		p1.setAccount(30000);
		int amount = 10000;
		receiveMoneyTest.receiveMoney(p1, amount);
		int actual=p1.getAccount().getCash();
		int expected=40000;
		assertEquals(actual, expected);
	}
	
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
		moveToFieldTest.moveToField(p1,move, false);
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
		game = MiniMatador.createGame();
		payMoneyTest = new GameController(game);
		p1 = new Player();
		p1.setAccount(30000);
		Player p2 = new Player();
		p2.setAccount(30000);
		payMoneyTest.payMoneyToPlayer(p1, 1000, p2);
		//Tests that p1 pays money
		int actual=p1.getAccount().getCash();
		int expected=29000;
		assertEquals(actual, expected);
		//tests tha p2 receives money
		int actual2=p2.getAccount().getCash();
		int expected2=31000;
		assertEquals(actual, expected);
	}

}
