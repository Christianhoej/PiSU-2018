package model;

/**
 * Includes the method to test if a player if the winner.
 * 
 * @author
 *
 */
public class Winner {

	/**
	 * Tests the player array, to see how many is broke.
	 * 
	 * @param players
	 * @return
	 */
	public boolean testIfWinner(Player[] players) {
		int notbroke = 0;
		for (int i = 0; i < players.length; i++) {
			boolean w1 = players[i].isBroke();
			if (w1 == false) {
				notbroke++;
			}
		}
		if (notbroke == 1)
			return true;
		else
			return false;
	}

}
