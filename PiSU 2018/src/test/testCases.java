package test;

import model.Account;
import model.Tax;
import test.TestAfProperty;
import model.Player;

import static org.junit.Assert.assertEquals;

import org.junit.*;



public class testCases {

	Account updateCashTest;
	Tax taxTest;
	
	
	
	@Before
	public void setUp() throws Exception{
		updateCashTest=new Account(30000); 
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
		//Implementeres
	}
	
	@Test
	public void 
	
	
}
