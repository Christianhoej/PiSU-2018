package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Test.Player;
import Test.Winner;
import junit.framework.Assert;

class TestWinner {
	Player p1 = new Player("A", 0, null, true, false, false);
	Player p2 = new Player("b", 0, null, true, false, false);
	Player p3 = new Player("c", 0, null, true, false, false);
	Player p4 = new Player("D", 0, null, false, false, false);
	Player [] player = {p1,p2,p3,p4};
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	void testTestIfWinner() {
		Winner winner = new Winner();
//		boolean testV = winner.testIfWinner(player);
		assertTrue(winner.testIfWinner(player));
	}
}
