package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Dice;
import java.math.*;

public class diceTest {
	
	@Test
	public void diceTest() {


		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice diceArray[] = new Dice[10000];
		int count[] = new int[11];
		int slag[] = new int[] {2,3,4,5,6,7,8,9,10,11,12};
		
		for(int i=0; i<diceArray.length; i++) {			
			switch(d1.rollDice()+ d2.rollDice()) {
			case 2: count[1]++;
			break;
			case 3: count[2]++;
			break;
			case 4: count[3]++;
			break;
			case 5: count[4]++;
			break;
			case 6: count[5]++;
			break;
			case 7: count[6]++;
			break;
			case 8: count[7]++;
			break;
			case 9: count[8]++;
			break;
			case 10: count[9]++;
			break;
			case 11: count[10]++;
			break;
			case 12: count[11]++;
			break;
			}
		}
		for(int i=0; i<count.length && i<slag.length; i++) {
			System.out.println("Terningerne har rullet " + slag[i] + " " + count[i] + " gange");
		}		
	}
}
