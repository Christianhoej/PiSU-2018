package test;

import model.Player;

public class TestAfProperty {

	public static void main(String[] args) {
		
		Player p1 = new Player("Gunn");		
		Player p2 = new Player("Christian");
		Player p3 = new Player("Yoss");
		Player p4 = new Player("Adam");
		Player p5 = new Player("Janus");
		Player p6 = new Player("Oliver");
		Player[] player = {p1, p2, p3, p4, p5, p6};
		
		PropertyTest p = new PropertyTest(2, "CBS");
		
		
		
		p.landOnField(p4,	player);
	}
}
