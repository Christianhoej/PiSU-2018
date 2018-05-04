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
import gui_fields.GUI_Player;
import model.Fields;

import static org.junit.Assert.assertEquals;

import javax.security.auth.login.AccountException;

import org.junit.*;

//Lortet virker ikke

public class testCases {

	Account updateCashTest;
	Tax taxTest;
	GameController payMoneyTest;
	GameController auctionTest;
	GameController moveToFieldTest;
	Game game;



	@Before
	public void setUp() throws Exception{
		//payTest=new CardPay(4, "Dette er en test", 1000);
		taxTest = new Tax(2);
		moveToFieldTest = new GameController(game);	
		payMoneyTest = new GameController(game);
	}

	/**
	 * Test updateCash
	 */
	@Test
	public void testUpdateCash(){
		Player p1 = new Player();
		p1.getAccount().updateCash(1000);
		int actual=p1.getAccount().getCash();
		int expected=31000;
		assertEquals(actual,expected);
	}

	@Test
	public void testMoveToField(){
		Player p1 = new Player();
		int move=6;
		p1.setPosition(4);
		moveToFieldTest.moveToField(p1,move);
		int actual = p1.getPosition();
		int expected=6;
		assertEquals(actual,expected);	
	}
	/**
	 * Tests whether the player is able to pay money
	 */
	@Test
	public void testPayMoney() {
		Player p1 = new Player();
		payMoneyTest.payMoney(p1, 1000);
		int actual=p1.getAccount().getCash();
		int expected=29000;
		assertEquals(actual, expected);
	}
}
