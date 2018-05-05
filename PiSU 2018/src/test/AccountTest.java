package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;

public class AccountTest {
	Player p1;

	/**
	 * Test updateCash
	 */
	@Test
	public void updateCashTest(){
		p1 = new Player();
		p1.getAccount().updateCash(1000);
		int actual=p1.getAccount().getCash();
		int expected=31000;
		assertEquals(actual,expected);
	}

}
