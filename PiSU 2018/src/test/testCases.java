package test;

import model.Account;
import model.Tax;
import test.TestAfProperty;
import model.Player;
import model.Property;
import model.CardMove;
import model.CardPay;
import model.ChanceCard;
import model.Game;
import controller.GameController;
import controller.MiniMatador;
import gui_fields.GUI_Player;
import model.Fields;

import static org.junit.Assert.assertEquals;

import javax.security.auth.login.AccountException;

import org.junit.*;

//Lortet virker ikke

public class testCases {
	GameController gameController;
	GameController payMoneyTest;
//	GameController auctionTest;
	GameController moveToFieldTest;
	GameController taxTest;
	Account updateCashTest;
	Game game;
	Tax tax;
	Fields fieldNumber;
	MiniMatador fields;
	
	Player p1;



	@Before
	public void setUp() throws Exception{

		
//		moveToFieldTest = new GameController(game);	
		payMoneyTest = new GameController(game);
	}

//	@Test
//	public void 
//	
//	
	@Test
	public void taxTest(){
		taxTest = new GameController(game);
		game=MiniMatador.createGame();
//		fields = MiniMatador.setFields(game);
		p1 = new Player();
		game.addPlayer(p1);
		tax = new Tax(20);
//		int price = 2000;
//		(tax)(game.getFields().get(38));
		int actual=p1.getAccount().getCash();
		int expected = 30000;
		assertEquals(actual, expected);
	}
	
	
	/**
	 * Test updateCash
	 */
	@Test
	public void testUpdateCash(){
		p1 = new Player();
		p1.getAccount().updateCash(1000);
		int actual=p1.getAccount().getCash();
		int expected=31000;
		assertEquals(actual,expected);
	}

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
		p1 = new Player();
		payMoneyTest.payMoney(p1, 1000);
		int actual=p1.getAccount().getCash();
		int expected=29000;
		assertEquals(actual, expected);
	}
}
