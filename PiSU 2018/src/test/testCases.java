package test;

import model.Account;

import static org.junit.Assert.assertEquals;

import org.junit.*;



public class testCases {

	Account accountTest;
	
	
	@Before
	public void setUp() throws Exception{
		accountTest=new Account(30000); 
	}
	
	/**
	 * Test updateCash
	 */
	@Test
	public void testUpdateCash(){
		accountTest.updateCash(1000);
		int actual=accountTest.getCash();
		int expected=31000;
		assertEquals(actual,expected);
	}
	
}
