package test;

import model.Account;
import model.Tax;
import test.TestAfProperty;
import model.Player;
import model.CardMove;
import model.CardPay;
import model.Game;
import controller.GameController;
import gui_fields.GUI_Player;

import static org.junit.Assert.assertEquals;

import javax.security.auth.login.AccountException;

import org.junit.*;



public class testCases {

	Account updateCashTest;
	Tax taxTest;
	CardPay payTest;
	
	
	
	@Before
	public void setUp() throws Exception{
		updateCashTest=new Account(30000); 
		Player p1 = new Player();
		payTest=new CardPay(4, "Dette er en test", 1000);
		taxTest = new Tax();
	}
	
	/**
	 * Test updateCash
	 */
	@Test
	public void testUpdateCash(){
		updateCashTest.updateCash(1000);
		int actual=updateCashTest.getCash();
		int expected=31000;
		assertEquals(actual,expected);
	}
	
	@Test
	public void testTax() {
		taxTest.landOnField();
		int actual=
	}
	
	@Test
	public void testCardPay() {
//		GUI_Player[] s = new GUI_Player[4];
		Player p1 = new Player();
		Game game1 = new Game();
		GameController payMoney = new GameController(game1); 
		payTest.performAction(payMoney);
		Account actual=p1.getAccount();
		int expected=29500;
		assertEquals(actual, expected);
		
	}
//
//private GameController payMoney(Player p1, int amount) {
//	// TODO Auto-generated method stub
//	return null;
//}
	
	
}
