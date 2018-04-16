package model;

public class Winner {
	
	
	public boolean testIfWinner(Player[]players) {
		int notbroke = 0;
		for(int i = 0; i<players.length; i++) {
			boolean w1 = players[i].getBroke();
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

