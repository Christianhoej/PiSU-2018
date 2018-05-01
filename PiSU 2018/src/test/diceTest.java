package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Dice;
import java.math.*;

public class diceTest {
	@Test
	public void diceTest() {


		Dice d = new Dice();
		Dice diceArray[] = new Dice[10000];
		int count[] = new int[6];¨
		
		for(int i=0; i<diceArray.length; i++) {
			diceArray[i]=(Dice)(Math.random()*6+1);
			
//			switch (i) {
//			case 2: count[0]++;
//			break;
//			case 3: count[1]++;
//			break;
//			case 4: count[2]++;
//			break;
//			case 5: count[3]++;
//			break;
//			case 6: count[4]++;
//			break;
//			case 7: count[5]++;
//			break;
//			case 8: count[6]++;
//			break;
//			case 9: count[7]++;
//			break;
//			case 10: count[8]++;
//			break;
//			case 11: count[9]++;
//			break;
//			case 12: count[10]++;
//			break;
//			}
		}
		for(int i=0; i<count.length; i++) {
			System.out.println("Terningerne har rullet " + count[i]  + diceArray);
		}
			
		
		

		/**
		 * Test om den giver den rigtige værdi
		 */
//		for(int i = 0;i < 10000;i++){
//			d.rollDice();
//			assertTrue(d.getFaceValue() >= 1 && d.getFaceValue() <= 6);
//			//assertTrue(d.getFaceValue() >= 1 && d.getFaceValue() <= 6);
//			//assertTrue(db.getDiceSum()>=2 && db.getDiceSum() <=12);
//		}
		
	}
}
